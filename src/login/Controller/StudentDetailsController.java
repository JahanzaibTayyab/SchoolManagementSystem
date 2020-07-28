/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class StudentDetailsController implements Initializable {

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
    private JFXComboBox<?> bloodgroup;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField city;
    @FXML
    private JFXTextField province;
    @FXML
    private JFXComboBox<?> gender;
    @FXML
    private JFXTextField contactno;
    @FXML
    private JFXTextField studentid;
    @FXML
    private JFXComboBox<?> classes;
    @FXML
    private JFXTextField testmarks;
    @FXML
    private JFXTextField totalmarks;
    @FXML
    private JFXTextField merit;
    @FXML
    private JFXTextField reference;
    @FXML
    private JFXTextField remarks;
    @FXML
    private JFXTextField section;
    @FXML
    private JFXDatePicker dateofadmission;
    @FXML
    private JFXComboBox<?> status;
    @FXML
    private Button save;
    @FXML
    private Button print;
    @FXML
    private Button clear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
