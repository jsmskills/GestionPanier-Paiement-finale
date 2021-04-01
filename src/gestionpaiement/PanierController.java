/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpaiement;



import Entities.*;
import Service.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author rania
 */
public class PanierController implements Initializable {

      @FXML
    private AnchorPane rt;

    private Label labelTitre;

    private Label labelDateDebut;

    private Label labelDateFin;

    private Label labelPrix;

    private Text labelDescription;


    ServiceFormatioons ps=new ServiceFormatioons();
    
    @FXML
    private Text decoText;
    @FXML
    private Text usernameText;
    @FXML
    private ImageView imagePanier;
    @FXML
    private Label labPanier;
    ServicePanier servicepanier=new ServicePanier();
    Panier panierNonCommande;
    
    @FXML
    private ScrollPane panelFormations;
    @FXML
    private VBox panelAllPanier;
    @FXML
    private Button btnVider;
    private int idUser=1;
    private String username="raniaa";
 
   
    private void calculNbreLignePanier(){
         Panier panier= servicepanier.getPanierByUser(idUser);
    panelAllPanier.getChildren().clear();
         if(panier!=null){
       labPanier.setText(Integer.toString( panier.getLignespanier().size()));
       panierNonCommande=panier;
       
     
        for(LignePanier f: panier.getLignespanier()){
    panelAllPanier.getChildren().add(new Text("     ♠ "+ f.getFormation().getTitre()));
   }
        Button bt=new Button("passer la commande");
         bt.setStyle("-fx-background-color: #76aae3");
            bt.setTextFill(Color.WHITE);
            bt.setPrefWidth(137);
            
     EventHandler<ActionEvent> buttonHandlerPasserCommande = new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Commander.fxml"));
            Parent root = loader.load();
            
            labPanier.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     };
     bt.setOnAction(buttonHandlerPasserCommande);
        
   panelAllPanier.getChildren().add(bt); 

     
   panelAllPanier.setSpacing(10);
       
   }else{
       panierNonCommande=new Panier();
       labPanier.setText("0");
   }
   
     Button btStat=new Button("Statistiques");
         btStat.setStyle("-fx-background-color: #76aae3");
            btStat.setTextFill(Color.WHITE);
            btStat.setPrefWidth(137);
    
            
    EventHandler<ActionEvent> buttonHandlerStat = new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StatFormationCommande.fxml"));
            Parent root = loader.load();
            
             Stage stage = new Stage();
            stage.setTitle("Statistique");
            stage.setScene(new Scene(root));
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     };
     btStat.setOnAction(buttonHandlerStat);
      panelAllPanier.getChildren().add(btStat);
  
   
    }
    
   
    
    private void remplirPanelFormation(){
         VBox box = new VBox();
        panelFormations.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        
        List<Formation> listFormation= ps.afficherTs();
        
        for(Formation f : listFormation){
            Formation_Panier fp=new Formation_Panier(f.getId(), f.getTitre(), f.getDescription(), f.getPrix());
            fp.setFomation(f);
            VBox vboxTitre_Description_prix=new VBox();
            Text description=new Text(f.getDescription());
            description.setWrappingWidth(140);
            vboxTitre_Description_prix.getChildren().addAll(new Label(f.getTitre()),description,new Label(Float.toString(f.getPrix())));
            vboxTitre_Description_prix.setPrefWidth(200);
            vboxTitre_Description_prix.setPadding(new Insets(0, 20, 0, 20));
            
            HBox flowPane=new HBox();
            
           
        ImageView img=new ImageView();
        
         Image image = new Image("resource/formation.jpg");
         img.setFitHeight(80);
         img.setFitWidth(80);

        img.setImage(image);
        img.setCache(true);
         HBox hbImage=new HBox(img);
        
            
            
             flowPane.getChildren().addAll(hbImage,vboxTitre_Description_prix,fp.getBtn());

            
            
            box.getChildren().add(flowPane);
            box.setSpacing(30);
            
        }
        
        panelFormations.setContent(box);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
  calculNbreLignePanier();
        
usernameText.setText(username);

remplirPanelFormation();
    
    }

    @FXML
    private void deconnexionHandleAction(MouseEvent event)throws IOException{
       
            
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Authentifaction.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            Stage ThisStage = (Stage) usernameText.getScene().getWindow();
            stage.show();
            ThisStage.close();
            
            
    }

    @FXML
    private void CommandeViderAction(ActionEvent event) {
        
 servicepanier.deletePanier(panierNonCommande.getId());
         calculNbreLignePanier();
        
         
remplirPanelFormation();
    }


    public class Formation_Panier{
        private int id;
        private String titre;
        private String description;
        private float prix;
        private Formation fomation;
        private Button btn;

        public Formation_Panier(int id, String titre, String description, float prix) {
            this.id = id;
            this.titre = titre;
            this.description = description;
            this.prix = prix;
           btn=new Button();
            this.btn.setText("ajouter au panier");
            final int idd=id;
            btn.setStyle("-fx-background-color: #76aae3");
            btn.setTextFill(Color.WHITE);
            
    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
       if(panierNonCommande.getId()==0 && panierNonCommande.getLignespanier().size()<1){
            panierNonCommande.setPrix(getFomation().getPrix());
            panierNonCommande.setUser(new Utilisateur(idUser));
            
           int idpanierCree= servicepanier.ajouter(panierNonCommande);
           if(idpanierCree>0){
               panierNonCommande.setId(idpanierCree);
               LignePanier ln=new LignePanier(panierNonCommande, getFomation());
               int idligne=servicepanier.ajouterLignePanier(ln);
               ln.setId(idligne);
               panierNonCommande.getLignespanier().add(ln);
           }
        }else{
            for(LignePanier lpp:panierNonCommande.getLignespanier()){
            if(lpp.getFormation().getTitre().equals(getFomation().getTitre())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Formation dèja en panier", ButtonType.CLOSE);
                alert.show();
                return;
            }
        }
            
           panierNonCommande.setPrix(panierNonCommande.getPrix()+getFomation().getPrix());
           int idpanierCree= servicepanier.update(panierNonCommande,0);
           if(idpanierCree>0){

               LignePanier ln=new LignePanier(panierNonCommande, getFomation());
               int idligne=servicepanier.ajouterLignePanier(ln);
               ln.setId(idligne);
               panierNonCommande.getLignespanier().add(ln);
           }
        }
        calculNbreLignePanier();
        /*Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous finaliser votre commande?", ButtonType.OK, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Commander.fxml"));
        Parent root = loader.load();

        labPanier.getScene().setRoot(root);
        }*/
    }
    
   
};
      
    btn.setOnAction(buttonHandler);
            
        }

        public Formation getFomation() {
            return fomation;
        }

        public void setFomation(Formation fomation) {
            this.fomation = fomation;
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

        public Button getBtn() {
            return btn;
        }

        public void setBtn(Button btn) {
            this.btn = btn;
        }
        

        
    }
    
}
