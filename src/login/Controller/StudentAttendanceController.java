/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import login.Helper.HelperViewsResources;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class StudentAttendanceController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddAttendanceButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("AddStudentAttendance");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Add Attendance");
               stage.show();
    }

    @FXML
    private void UpdateAttendanceAttendance(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("UpdateStudentAttendance");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Update Attendance");
               stage.show();
    }

    @FXML
    private void ViewAttendanceButtonHandling(MouseEvent event) {
    }
    
}
