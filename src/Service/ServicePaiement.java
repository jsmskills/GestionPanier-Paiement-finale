/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.paiement;
import Services.IServicePaiement;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author ASUS
 */
public class ServicePaiement implements IServicePaiement{
    Connection cnx;

    public ServicePaiement()  {
        try {
            cnx=Maconnexion.getInstance().getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicePaiement.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    

    @Override
    public  void AddPaiement(paiement p) {
        PreparedStatement pst ;
        
        try {
            String sql="INSERT INTO paiement(modepaiement,numcarte,cryptograme,dateexp)VALUES(?,?,?,?)";
            pst=cnx.prepareStatement(sql);
            pst.setString(1,p.getModepaiement());
            pst.setInt(2,p.getNumcarte());
            pst.setInt(3,p.getCryptograme());
            pst.setString(4,p.getDateexp());
            if(pst.executeUpdate()>0) 
                System.out.println("request send successfully!!!");
            else 
                System.out.println("failed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     ObservableList<paiement>oblist2=FXCollections.observableArrayList();
     
    @Override
    public ObservableList<paiement> AfficherPaiement()  {
        try{
        Statement stm=cnx.createStatement();
        String query="select* from `paiement`";
        ResultSet rst=stm.executeQuery(query);
        paiement P=new paiement();
        while(rst.next()){
            P.setId(rst.getInt("id"));
            P.setModepaiement(rst.getString("modepaiement"));
            P.setNumcarte(rst.getInt("numcarte"));
            P.setCryptograme(rst.getInt("cryptograme"));
            P.setDateexp(rst.getString("dateexp"));
         oblist2.add(P);   
        }
        return oblist2;
        }catch(Exception e){
            
        }
        return null;
    }
    
    
    public List<String> AfficherModePaiement()  {
       List<String> p=new ArrayList<String>();
        try{
        Statement stm=cnx.createStatement();
        String query="select distinct modepaiement from `paiement`";
        ResultSet rst=stm.executeQuery(query);
        paiement P=new paiement();
        while(rst.next()){
           
         p.add( rst.getString("modepaiement"));   
        }
        
        }catch(Exception e){
            
        }
        return p;
    }
    
    public paiement AfficherPaiement(String modepiment)  {
        try{
        Statement stm=cnx.createStatement();
        String query="select * from paiement where modepaiement='"+modepiment+"'";
        ResultSet rst=stm.executeQuery(query);
        paiement P=new paiement();
        while(rst.next()){
       P.setId(rst.getInt("id"));
            P.setModepaiement(rst.getString("modepaiement"));
            P.setNumcarte(rst.getInt("numcarte"));
            P.setCryptograme(rst.getInt("cryptograme"));
            P.setDateexp(rst.getString("dateexp")); 
            return  P;
        }
        
        }catch(Exception e){
            
        }
        return null;
    }

    @Override
    public void DeletePaiement(paiement p) {
        int n2=0;
        PreparedStatement st;
        try {
            st=cnx.prepareStatement("DELETE FROM `paiement` WHERE `id`=?");
            st.setInt(1,p.getId());
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
    public void ModifyPaiement(paiement p) {
        String sql2="UPDATE paiement SET modepaiement=?,numcarte=?,cryptograme=?,dateexp=?  WHERE id=? ";
        
        try {
            PreparedStatement pstmt=cnx.prepareStatement(sql2);
            pstmt.setString(1, p.getModepaiement());
            pstmt.setInt(2,p.getNumcarte());
            pstmt.setInt(3, p.getCryptograme());
            pstmt.setString(4,p.getDateexp());
            pstmt.setInt(5,p.getId());
            if(pstmt.executeUpdate()>0){
                System.out.println("Parfait paiement a ete modifie avec succees ");
                
                   
            }
            else 
                System.out.println("Echec de modification");
            pstmt.close();
            
                
            
            
            
        } catch (SQLException ex) {
            System.out.println("Modify paiement=="+ex.getMessage());
        }
    }

    @Override
    public paiement getPaiementById(int id) {
        paiement p=new paiement();
        
        try {
            String sql="SELECT * FROM paiement WHERE id="+id;
            Statement stm=cnx.createStatement();
            ResultSet rst=stm.executeQuery(sql);
            while(rst.next()){
                p.setModepaiement(rst.getString("modepaiement"));
                p.setNumcarte(rst.getInt("numcarte"));
                p.setCryptograme(rst.getInt("cryptograme"));
                p.setDateexp(rst.getString("dateexp"));
                
            
            
            }
        } catch (SQLException ex) {
            System.out.println("error"+ex.getMessage());
        }
        return p;
    }


}
