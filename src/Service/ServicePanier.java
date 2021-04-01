/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Formation;
import Entities.LignePanier;
import Entities.Panier;
import Entities.Utilisateur;
import Entities.paiement;
import Services.IServicePanier;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class ServicePanier  implements  IServicePanier{
Connection cnx;
 private Statement ste;
    public ServicePanier()  {
        try {
            cnx=Maconnexion.getInstance().getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicePaiement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public int update(Panier p,int commandee) {
        try {   
        
        String requeteInsert = "update panier set prix= "+p.getPrix()+", commandé=  "+commandee+" where id= "+p.getId();
        PreparedStatement pre=cnx.prepareStatement(requeteInsert,Statement.RETURN_GENERATED_KEYS);
     
        return pre.executeUpdate();

        

     } catch (SQLException ex) {
         
         System.out.println("erruer ajout");
     }
        return 0;
    }
    
    
    public int ajouter(Panier p) {
        try {   
        
        String requeteInsert = "INSERT INTO panier (prix,idUser,commandé) VALUES ("+p.getPrix()+", "+ p.getUser().getId()+",0)";
        PreparedStatement pre=cnx.prepareStatement(requeteInsert,Statement.RETURN_GENERATED_KEYS);
     
       int affectedRows = pre.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

     } catch (SQLException ex) {
         
         System.out.println("erruer ajout");
     }
        return 0;
    }
    
    
    public int ajouterLignePanier(LignePanier p) {
        try {   
        
        String requeteInsert = "INSERT INTO ligne_panier(idPanier,idFormation) VALUES (?,?)";
        PreparedStatement pre=cnx.prepareStatement(requeteInsert,Statement.RETURN_GENERATED_KEYS);
     
         pre.setInt(1, p.getPanier().getId());
         pre.setInt(2, p.getFormation().getId());

         
        
        int affectedRows = pre.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

     } catch (SQLException ex) {
         
         System.out.println("erruer ajout");
     }
        return 0;
    }
    
    public int deleteLignePanier(int idpLigne) {
        try {   
        
        String requeteInsert = "DELETE FROM ligne_panier where idLigne="+idpLigne;
        PreparedStatement pre=cnx.prepareStatement(requeteInsert);
     
         return pre.executeUpdate();


     } catch (SQLException ex) {
         
         System.out.println("erruer suppression");
     }
        return 0;
    }
    
    
    public int deletePanier(int id) {
        try {   
        
        String requeteInsert = "DELETE FROM panier where id="+id;
        PreparedStatement pre=cnx.prepareStatement(requeteInsert);
     
         return pre.executeUpdate();


     } catch (SQLException ex) {
         
         System.out.println("erruer suppression");
     }
        return 0;
    }
    
    
    public List<LignePanier>  getLignePanierByPanier(Panier pan){
       List<LignePanier> lpaniers=new ArrayList<>();
       ServiceFormatioons sformation=new ServiceFormatioons();
    try {
         ste=cnx.createStatement();
    ResultSet rs=ste.executeQuery("select e.* from ligne_panier e where idPanier="+pan.getId() );
     while (rs.next()) { 
       int idLigne=rs.getInt("idLigne");      
         Formation f=sformation.afficherParId( rs.getInt("idFormation"));
      
         LignePanier lpanier=new LignePanier(idLigne, pan, f);
         lpaniers.add(lpanier);
                    }
                
     } catch (SQLException ex) {
         System.out.println("probleme de connexion");
     }
    return lpaniers; 
    }
    
    public Panier  getPanierByUser(int idUser){
       
    try {
         ste=cnx.createStatement();
    ResultSet rs=ste.executeQuery("select e.* from panier e where commandé=0 and idUser="+idUser );
     while (rs.next()) { 
       int id=rs.getInt("id");
       float prix=rs.getFloat("prix");
         Utilisateur user=new Utilisateur(rs.getInt("idUser"));
         Panier p=new Panier(prix, user, false);
         p.setId(id);
         p.setLignespanier(getLignePanierByPanier(p));
              return p;
                    }
                
     } catch (SQLException ex) {
         System.out.println("probleme de connexion");
     }
    return null; 
    }
    
    
    ///////////
    
    List<Panier>list = new ArrayList<Panier>();
    @Override
    public List<Panier> getAllPanier() {
    
        Panier panier = new Panier();
                 String query="select* from `panier`";

        Statement stm;
    try {
        stm = cnx.createStatement();
        ResultSet rst=stm.executeQuery(query);
        while(rst.next()){
            panier.setId(rst.getInt("id"));
           // panier.setId_formation(rst.getInt("id_formation"));
            list.add(panier);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return list;
    
    }
    
    
}
