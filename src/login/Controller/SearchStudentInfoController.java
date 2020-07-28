/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class SearchStudentInfoController implements Initializable {

    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField fathername;
    @FXML
    private JFXTextField bform;
    @FXML
    private JFXTextField fathercnic;
    @FXML
    private JFXTextField occupation;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField cellno;
    @FXML
    private JFXTextField telephone;
    @FXML
    private JFXDatePicker dob;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField city;
    @FXML
    private JFXTextField province;
    @FXML
    private JFXTextField gender;
    @FXML
    private JFXTextField bloodgroup;
    @FXML
    private TableView<?> feeinformation;
    @FXML
    private TableView<?> resultinformation;
    @FXML
    private BarChart<?, ?> attendence;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    XYChart.Series barchart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        barchart();
        attendence.getData().addAll(barchart);
        // TODO
    }    
    public void barchart()
    {
        barchart=new XYChart.Series<>();
        barchart.getData().add(new XYChart.Data<>("Jan",31));
        barchart.getData().add(new XYChart.Data<>("Feb",28));
        barchart.getData().add(new XYChart.Data<>("Mar",31));
        barchart.getData().add(new XYChart.Data<>("Apr",30));
        barchart.getData().add(new XYChart.Data<>("May",31));
        barchart.getData().add(new XYChart.Data<>("June",30));
        barchart.getData().add(new XYChart.Data<>("July",31));
        barchart.getData().add(new XYChart.Data<>("Aug",31));
        barchart.getData().add(new XYChart.Data<>("Sep",30));
        barchart.getData().add(new XYChart.Data<>("Oct",31));
        barchart.getData().add(new XYChart.Data<>("Nov",30));
        barchart.getData().add(new XYChart.Data<>("Dec",31));
    }
    
}
