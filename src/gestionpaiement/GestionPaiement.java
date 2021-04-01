package gestionpaiement;
import Entities.paiement;
import Service.ServicePaiement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author A
 */
public class GestionPaiement extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Panier.fxml"));
         // Parent root = FXMLLoader.load(getClass().getResource("Back_ConsulterCommande.fxml"));

        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        
        
      
        
        
        ServicePaiement s = new ServicePaiement();
        
        
         List<paiement> paiement = s.AfficherPaiement() ;

       
       paiement paiementById = s.getPaiementById(50);
                System.out.println("Paiementby id est ="+paiementById);

      
        paiementById.setModepaiement("Master");
        paiementById.setNumcarte(22);
        paiementById.setDateexp("2022-07-08");
       
        
        
        paiement c1 = new paiement("Master", 44, 55, "2022-07-08");
        
        
        for (paiement p: paiement){
           ; System.out.println("paiement="+p.toString());
           
        }     
        
              
      
                launch(args);
    }
    
}
