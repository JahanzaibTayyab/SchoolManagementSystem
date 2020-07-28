/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import login.Helper.HelperViewsResources;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class AddEmployeesController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;
    @FXML
    private Button employee;
    @FXML
    private Button student;

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
               stage.setTitle("New Employee");
               stage.show();
    }

    @FXML
    private void AddStaffButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("TeacherDetails");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("New Staff");
               stage.show();
    }
    
}
