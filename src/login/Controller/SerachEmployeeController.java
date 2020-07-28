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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class SerachEmployeeController implements Initializable {

    @FXML
    private JFXTextField employeeid;
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
    private void SearchButtonHandling(MouseEvent event) {
    }

    @FXML
    private void DismissButtonHandling(MouseEvent event) {
    }
    
}
