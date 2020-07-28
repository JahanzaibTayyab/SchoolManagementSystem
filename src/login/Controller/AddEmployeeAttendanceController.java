/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class AddEmployeeAttendanceController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;
    @FXML
    private Button add;
    @FXML
    private Button close;
    @FXML
    private JFXTextField studentid;
    @FXML
    private TextField employeename;
    @FXML
    private TableView<?> viewattendancetable;
    @FXML
    private JFXComboBox<String> attendancedetails;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> attendance =FXCollections.observableArrayList("Present","Absent","Leave");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        attendancedetails.setItems(attendance);
        // TODO
    }    

    @FXML
    private void AddButtonHandling(MouseEvent event) {
        if(emptyfieldvalidate())
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Add Student");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                
            }
        }
    }

    @FXML
    private void CloseButtonHandling(MouseEvent event) {
    }
    public boolean  emptyfieldvalidate()
    {
        if(studentid.getText().isEmpty() || employeename.getText().isEmpty() 
                  )
             
        {
                        Alert alert =new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Validate Fields");
                        alert.setHeaderText("Fields cannot be empty");
                        alert.setContentText("Please enter the data");
                        alert.showAndWait();
                        return false;
        }
        return true;  
    }
    
}
