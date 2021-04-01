/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpaiement;

import Service.ServiceCommande;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class StatFormationCommandeController implements Initializable {

    @FXML
    private Text titre;

    @FXML
    private Pane paneStat;
    public int idLivreur = 0;

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

}
