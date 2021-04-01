/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpaiement;


import Entities.Formation;
import Entities.LigneCommande;
import Entities.LignePanier;
import Entities.Panier;
import Entities.Utilisateur;
import Entities.commande;
import Entities.paiement;
import Service.ServiceCommande;
import Service.ServicePaiement;
import Service.ServicePanier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class CommanderController implements Initializable {

    @FXML
    private AnchorPane rt;
    @FXML
    private TableView<f> tablePanier;
    @FXML
    private TableColumn<f, String> columnTitre;
    @FXML
    private TableColumn<f, String> ColumnDescription;
    @FXML
    private TableColumn<f, Float> ColumnPrix;
    @FXML
    private TableColumn<f, String> ColumnSuppression;
    @FXML
    private Label prixTotal;
    @FXML
    private Button btnCommande;
    @FXML
    private Text decoText;
    @FXML
    private Text usernameText;
    @FXML
    private ImageView imagePanier;
    @FXML
    private Label labPanier;
    ObservableList<f> lista = FXCollections.observableArrayList();
    Panier panierNonCommande;
    ServicePanier servicepanier=new ServicePanier();
    ServicePaiement servicepaiement=new ServicePaiement();
    private int idUser=1;
    private String username="raniaa";
    
    @FXML
    private ComboBox<String> comboPaiement;
    ObservableList<String> listapaiement =FXCollections.observableArrayList( servicepaiement.AfficherModePaiement());
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboPaiement.setItems(listapaiement);
       usernameText.setText(username);
        calculNbreLignePanier();
       for(LignePanier lpanier: panierNonCommande.getLignespanier()){
          f p=new f(lpanier.getId(), lpanier.getFormation().getId(), lpanier.getFormation().getTitre(), lpanier.getFormation().getDescription(),lpanier.getFormation().getPrix());
           lista.add(p);
       }
       columnTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        ColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColumnPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        ColumnSuppression.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        tablePanier.setItems(lista);
        
    }
    ServiceCommande servicecommande=new ServiceCommande();

    @FXML
     private void NaviguerHandler(MouseEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Panier.fxml"));
        Parent root = loader.load();

        labPanier.getScene().setRoot(root);
    }
    
    @FXML
    private void CommandeHandleAction(ActionEvent event) throws IOException {
      if(lista.size()<1){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veillez remplir un panier", ButtonType.OK);
                    alert.show();
                    return;
      }
        if(comboPaiement.getValue()!=null){
        paiement paim=servicepaiement.AfficherPaiement(comboPaiement.getValue().toString());
        commande c=new commande(Float.parseFloat( prixTotal.getText()), new Utilisateur(idUser), panierNonCommande, paim);
        int cmdId= servicecommande.ajouter(c);
        c.setRef(cmdId);
           
        for (LignePanier lp:panierNonCommande.getLignespanier()){
            LigneCommande lc=new LigneCommande(c, lp.getFormation());
            servicecommande.ajouterLigneCommande(lc);
            
        }
        servicepanier.update(panierNonCommande, 1);
       }else{
           Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez sélectionner ou ajouter un mode de paiement", ButtonType.OK);
                    alert.show();
                    return;
       }
       FXMLLoader loader = new FXMLLoader(getClass().getResource("Panier.fxml"));
        Parent root = loader.load();

        labPanier.getScene().setRoot(root);
       }


    @FXML
    private void deconnexionHandleAction(MouseEvent event) throws IOException {
          
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Authentifaction.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            Stage ThisStage = (Stage) usernameText.getScene().getWindow();
            stage.show();
            ThisStage.close();
    }
    
    private void calculNbreLignePanier(){
         Panier panier= servicepanier.getPanierByUser(idUser);
   if(panier!=null){
       labPanier.setText(Integer.toString( panier.getLignespanier().size()));
       panierNonCommande=panier;
       prixTotal.setText(Float.toString( panier.getPrix()));
   }else{
       panierNonCommande=new Panier();
   }
    }

    @FXML
    private void AjouterModePaiement(ActionEvent event) throws IOException {
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) labPanier.getScene().getWindow()).close();
    }
    public class f{
   private int idPanier;     
    private int id; 
    private String titre;
    private String description;   
    private float prix;
    private Hyperlink supprimer;

        public f(int idPanier, int id, String titre, String description, float prix) {
            this.idPanier = idPanier;
            this.id = id;
            this.titre = titre;
            this.description = description;
            this.prix = prix;
            this.supprimer =new Hyperlink("supprimer");
            
            
                this.supprimer.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous vraiment supprimer cette formation du panier?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.YES) {
                        panierNonCommande.setPrix(panierNonCommande.getPrix()-prix);
                        servicepanier.update(panierNonCommande, 0);
                        servicepanier.deleteLignePanier(idPanier);
                        
                         calculNbreLignePanier();
                         lista.clear();
       for(LignePanier lpanier: panierNonCommande.getLignespanier()){
          f p=new f(lpanier.getId(), lpanier.getFormation().getId(), lpanier.getFormation().getTitre(), lpanier.getFormation().getDescription(),lpanier.getFormation().getPrix());
           lista.add(p);
       }
       tablePanier.setItems(lista);
                        
                    }
                }
                });
                }
            

            
            
        

        public int getIdPanier() {
            return idPanier;
        }

        public void setIdPanier(int idPanier) {
            this.idPanier = idPanier;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitre() {
            return titre;
        }

        public void setTitre(String titre) {
            this.titre = titre;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public float getPrix() {
            return prix;
        }

        public void setPrix(float prix) {
            this.prix = prix;
        }

        public Hyperlink getSupprimer() {
            return supprimer;
        }

        public void setSupprimer(Hyperlink supprimer) {
            this.supprimer = supprimer;
        }
    
    
            

    }
}
