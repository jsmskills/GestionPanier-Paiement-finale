/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Formation;
import Entities.LigneCommande;
import Entities.LignePanier;
import Entities.Panier;
import Entities.commande;
import Entities.paiement;

import Services.IServiceCommande;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class ServiceCommande implements IServiceCommande{
    Connection cnx;

    public ServiceCommande() {
        try {
            cnx=Maconnexion.getInstance().getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     public int ajouter(commande c) {
        try {   
        
        String requeteInsert = "INSERT INTO commande (prix,idUser,idpanier,idpaiement) VALUES ("+c.getPrix()+", "+ c.getUser().getId()+", "+ c.getPanier().getId()+", "+ c.getPaiment().getId()+")";
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
    
    
    public int ajouterLigneCommande(LigneCommande p) {
        try {   
        
        String requeteInsert = "INSERT INTO ligne_commande(idCommande,idFormation) VALUES (?,?)";
        PreparedStatement pre=cnx.prepareStatement(requeteInsert,Statement.RETURN_GENERATED_KEYS);
     
         pre.setInt(1, p.getCommande().getRef());
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
    
    
    
//////////////////
    @Override
    public void AddCommande(commande c) {
        PreparedStatement pst ;
        
        try {
            String sql="INSERT INTO commande(nomformation,prix)VALUES(?,?)";
            pst=cnx.prepareStatement(sql);
           // pst.setString(1,c.getNomformation());
            pst.setFloat(2,c.getPrix());
            
            if(pst.executeUpdate()>0) 
                System.out.println("request send successfully!!!");
            else 
                System.out.println("failed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    ObservableList<commande>oblist2=FXCollections.observableArrayList();
    @Override
    public ObservableList<commande> AfficherCommande() throws SQLException {
        Statement stm=cnx.createStatement();
        String query="select* from `commande`";
        ResultSet rst=stm.executeQuery(query);
        commande c=new commande();
        while(rst.next()){
            c.setRef(rst.getInt("ref"));
         //   c.setNomformation(rst.getString("nomformation"));
            c.setPrix(rst.getInt("Prix"));
            
         oblist2.add(c);   
        }
        return oblist2;
        
        
    }
    
    
    public List<commande> AfficherTousCommandes()  {
        List<commande> lista=new ArrayList<>();
        Statement stm;
        try {
            stm = cnx.createStatement();
        
        String query="SELECT DISTINCT c.ref,c.prix,p.modepaiement, COUNT(DISTINCT lc.idLigne) " +
"FROM commande c " +
"inner join paiement p on p.id = c.idpaiement " +
"INNER JOIN ligne_commande lc on lc.idCommande = c.ref " +
"GROUP by c.ref,c.prix,p.modepaiement ";
        ResultSet rst=stm.executeQuery(query);
        
        while(rst.next()){
            commande c=new commande();
            c.setRef(rst.getInt("ref"));
         //   c.setNomformation(rst.getString("nomformation"));
            c.setPrix(rst.getInt("Prix"));
            c.setPaiment(new paiement(rst.getString("modepaiement"), ""));
            c.setNbrFormation(rst.getInt(4));
            lista.add(c);
            
           
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
        
        
    }
    

    @Override
    public void DeleteCommande(commande c) {
        int n2=0;
        PreparedStatement st;
        try {
            st=cnx.prepareStatement("DELETE FROM `commande` WHERE `ref`=?");
            st.setInt(1,c.getRef());
            n2=st.executeUpdate();
            if(n2>0)
                System.out.println("deleted");
            else 
                
                System.out.println("non-deleted");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void ModifyCommande(commande c) {
        String sql2="UPDATE commande SET nomformation=?,prix=?  WHERE ref=? ";
        
        try {
            PreparedStatement pstmt=cnx.prepareStatement(sql2);
           // pstmt.setString(1, c.getNomformation());
            pstmt.setFloat(2,c.getPrix());
            pstmt.setInt(3, c.getRef());
           
            if(pstmt.executeUpdate()>0){
                System.out.println("Parfait commande a ete modifie avec succees ");
                
                   
            }
            else 
                System.out.println("Echec de modification");
            pstmt.close();
            
                
            
            
            
        } catch (SQLException ex) {
            System.out.println("Modify commande=="+ex.getMessage());
        }
        
        
    }
    
    @Override
    public commande getCommandeById(int ref) {
         commande c=new commande();
        
        try {
            String sql="SELECT * FROM commande WHERE ref="+ref;
            Statement stm=cnx.createStatement();
            ResultSet rst=stm.executeQuery(sql);
            while(rst.next()){
           //     c.setNomformation(rst.getString("nom formation"));
                c.setPrix(rst.getInt("prix"));
               
                
            
            
            }
        } catch (SQLException ex) {
            System.out.println("error"+ex.getMessage());
        }
        return c;
    }
    
    public List<Formation> afficherFormationParCommande(int idcmd) {
        List<Formation> arr = new ArrayList<>();
        try {
            Statement ste = cnx.createStatement();
            String requete = "";
       
                requete = "SELECT distinct f.titre,f.description,f.prix FROM formation f INNER join ligne_commande lc on lc.idFormation=f.id where lc.idCommande="+idcmd;
            
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                String titre = rs.getString("titre");
                String description = rs.getString(2);
                              
                float prix = rs.getFloat(3);
               
                
Formation f=new Formation();
f.setPrix(prix);
f.setDescription(description);
f.setTitre(titre);

                arr.add(f);
            }
        } catch (SQLException ex) {
            System.out.println("probleme d'affichage");
        }
        return arr;
    }
    
        
     public List<Stat> afficherStatFormationCommande() {
        List<Stat> arr = new ArrayList<>();
        try {
            Statement ste = cnx.createStatement();
            String requete = "";
       
                requete = "select titre,COUNT(id) from ligne_commande lc inner join formation f on lc.idFormation=f.id  group BY titre";
            
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                String titre = rs.getString("titre");
                int number = rs.getInt(2);
               
                
                Stat l = new Stat(titre, number);
                arr.add(l);
            }
        } catch (SQLException ex) {
            System.out.println("probleme d'affichage");
        }
        return arr;
    }

    public class Stat {

        private String group;
        private int nombre;

        public Stat(String group, int nombre) {
            this.group = group;
            this.nombre = nombre;
        }

        public Stat(){
            
        }
        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public int getNombre() {
            return nombre;
        }

        public void setNombre(int nombre) {
            this.nombre = nombre;
        }

    }

           
                
        
    
    
}
