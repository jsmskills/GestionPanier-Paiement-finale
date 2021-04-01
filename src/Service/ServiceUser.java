/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Utils.*;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceUser {

    private Connection con;
    private Statement ste;

    public ServiceUser() {
        try {
            con=Maconnexion.getInstance().getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicePaiement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public Utilisateur  Connecter(String password,String username){
       
    try {
         ste=con.createStatement();
        
         
     
    ResultSet rs=ste.executeQuery("select e.* from utiliisateur e where mail='"+username+"' and password='"+password+"'");
     while (rs.next()) { 
         String type =rs.getString("type");
         Utilisateur u=new Utilisateur(username, password);
         u.setType(type);
         u.setNom(rs.getString("nom"));
         u.setId(rs.getInt("id"));
           return u;
                
                    }
                
     } catch (SQLException ex) {
         System.out.println("probleme de connexion");
     }
    return null; 
    } 

public Utilisateur  getUserById(int id){
       
    try {
         ste=con.createStatement();
        
         
     
    ResultSet rs=ste.executeQuery("select e.* from utiliisateur e where id="+id);
     while (rs.next()) { 

         int idu =rs.getInt("id");

         Utilisateur u=new Utilisateur(idu);
        
           return u;
                
                    }
                
     } catch (SQLException ex) {
         System.out.println("probleme de connexion");
     }
    return null; 
    } 
   
    
}
