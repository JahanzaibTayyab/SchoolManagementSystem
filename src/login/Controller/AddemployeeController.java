/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import login.Helper.HelperViewsResources;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class AddemployeeController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;
    @FXML
    private Button student;
    @FXML
    private Button employee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    @FXML
    private void AddemplyoeeButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("EmployeeDetails");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Student Attendance");
               stage.show();
    }

    @FXML
    private void AddStaffButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("TeacherDetails");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Emplyoee Attendance");
               stage.show();
    }
    
}
