/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class Maconnexion {
    final static String URL="jdbc:mysql://127.0.0.1:3306/e-skills";
  final static String LOGIN ="root";
  final static String PWD="";
  private Connection cnx;
  static Maconnexion instance=null;
  private Maconnexion() throws ClassNotFoundException{
     try{
         Class.forName("com.mysql.jdbc.Driver");
          cnx=DriverManager.getConnection(URL,LOGIN,PWD);  
          System.out.println("connexion etablie");
     }  catch (SQLException ex){
         System.out.println("pas de connexion"+ex.getMessage());
     }
  }
   public static Maconnexion getInstance() throws ClassNotFoundException{
        if (instance==null)
        {
            instance=new Maconnexion();
     }
        return instance;
     }
     public Connection getConnection(){
         return cnx;
    

    
    } 
    
}
