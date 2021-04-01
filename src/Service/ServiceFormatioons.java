/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Formation;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceFormatioons {
 private Connection con;
    private Statement ste;
    public ServiceFormatioons() {
     try {
            con=Maconnexion.getInstance().getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicePaiement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Formation> afficherTs()  {
        List<Formation> arr=new ArrayList<>();
     try {
         ste=con.createStatement();
     
    ResultSet rs=ste.executeQuery("select * from formation");
     while (rs.next()) {                
               int id=rs.getInt(1);             
               String titre= rs.getString("titre");
               String description= rs.getString("description");
               Date datedebut= rs.getDate("date-début");
               Date datefin= rs.getDate("date-fin");
               float prix= rs.getFloat("prix");
               int nbreplace= rs.getInt("nbre-place");
               Formation f=new Formation(id,titre, description, datedebut, datefin, prix, nbreplace);
               arr.add(f);

               
               
     }
     } catch (SQLException ex) {
         System.out.println("probleme d'affichage");
     }
    return arr; 
    }
    
    
      public Formation afficherParId(int idf)  {
        
     try {
         ste=con.createStatement();
     
    ResultSet rs=ste.executeQuery("select * from formation where id="+idf);
     while (rs.next()) {                
               int id=rs.getInt(1);             
               String titre= rs.getString("titre");
               String description= rs.getString("description");
               Date datedebut= rs.getDate("date-début");
               Date datefin= rs.getDate("date-fin");
               float prix= rs.getFloat("prix");
               int nbreplace= rs.getInt("nbre-place");
               Formation f=new Formation(id,titre, description, datedebut, datefin, prix, nbreplace);
               return f;

               
               
     }
     } catch (SQLException ex) {
         System.out.println("probleme d'affichage");
     }
    return null; 
    }
}
