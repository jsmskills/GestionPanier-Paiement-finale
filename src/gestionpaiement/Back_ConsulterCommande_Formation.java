/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpaiement;

import Entities.Formation;
import Entities.commande;
import Service.ServiceCommande;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Back_ConsulterCommande_Formation implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TableView<Formation> tableFormation;
    @FXML
    private TableColumn<Formation, String> tablecolumnTitr;
    @FXML
    private TableColumn<Formation, String> tablecolumnDescr;
    @FXML
    private TableColumn<Formation, Float> tablecolumnPrix;
    ServiceCommande scommand=new ServiceCommande();
    ObservableList<Formation> lista = FXCollections.observableArrayList(); 
    public int idcmd;
    @FXML
    private Button btn_Commandes;
    @FXML
    private Button btn_Stats;
    @FXML
    private ImageView retourhandle;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        // TODO
    }  
    
    public void remplir(){
           List<Formation> ls=scommand.afficherFormationParCommande(idcmd);
     for(Formation c: ls){
      lista.add(c);
     }
     
      tablecolumnTitr.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tablecolumnPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tablecolumnDescr.setCellValueFactory(new PropertyValueFactory<>("description"));
       
     
     tableFormation.setItems(lista);
    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
         
        if (event.getSource() == btn_Commandes) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Back_ConsulterCommande.fxml")));
            stage.setScene(scene);
            stage.show();
        }
        if (event.getSource() == btn_Stats) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Back_StatCommande.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void handleretourCmd(MouseEvent event) throws IOException {
         Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Back_ConsulterCommande.fxml")));
            stage.setScene(scene);
            stage.show();
    }
    
}
