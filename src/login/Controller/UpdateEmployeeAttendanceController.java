/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class UpdateEmployeeAttendanceController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;
    @FXML
    private Button update;
    @FXML
    private Button close;
    @FXML
    private JFXTextField studentid;
    @FXML
    private TextField employeename;
    @FXML
    private TableView<?> viewattendancetable;
     @FXML
    private JFXComboBox<String> attendence;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> attendance =FXCollections.observableArrayList("Present","Absent","Leave");
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        attendence.setItems(attendance);
        // TODO
    }    

    @FXML
    private void UpdateButtonHandling(MouseEvent event) {
    }

    @FXML
    private void CloseButtonHandling(MouseEvent event) {
    }
    
}
