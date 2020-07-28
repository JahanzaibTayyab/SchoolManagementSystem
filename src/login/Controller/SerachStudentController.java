/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import login.Helper.HelperViewsResources;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class SerachStudentController implements Initializable {

    @FXML
    private JFXTextField studentid;
    @FXML
    private JFXButton search;
    @FXML
    private JFXButton dismiss;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SearchingStudentButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("SearchStudentInfo");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Student Information");
               stage.show();
    }

    @FXML
    private void DismissStudentButtonHandling(MouseEvent event) {
    }
    
}
