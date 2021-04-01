/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpaiement;

import Service.ServiceCommande;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Back_StatCommandeController implements Initializable {


    @FXML
    private Pane paneStat;
    public int idLivreur = 0;
    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_Commandes;
    @FXML
    private Button btn_Stats;
    @FXML
    private Pane pnl_abonnement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadData();
    }

    public void loadData() {
        paneStat.getChildren().clear();
        ObservableList<PieChart.Data> lista = FXCollections.observableArrayList();
        ServiceCommande sdao = new ServiceCommande();
        List<ServiceCommande.Stat> listStat = sdao.afficherStatFormationCommande();
        Iterator iterator = listStat.iterator();
        while (iterator.hasNext()) {
            ServiceCommande.Stat stat = (ServiceCommande.Stat) iterator.next();
            lista.add(new PieChart.Data(stat.getGroup(), stat.getNombre()));
        }
        PieChart piechat = new PieChart(lista);
        piechat.setTitle("Formations les plus commandées");


        lista.forEach(data
                -> data.nameProperty().bind(
                        Bindings.concat(
                               (data.pieValueProperty().intValue()), " formation(s) ", data.getName() 
                        )
                )
        );

        paneStat.getChildren().add(piechat);

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

}
