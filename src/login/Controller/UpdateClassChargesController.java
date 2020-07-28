/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class UpdateClassChargesController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;
    @FXML
    private JFXButton back;
    @FXML
    private JFXTextField classfee;
    @FXML
    private JFXTextField generatorfeeS;
    @FXML
    private JFXButton Add;
    @FXML
    private JFXButton Clear;
    @FXML
    private JFXButton Update;
    @FXML
    private JFXComboBox<?> slectclass;
    @FXML
    private JFXTextField functionfee;
    @FXML
    private JFXTextField partyfee;
    @FXML
    private JFXTextField othercharges;
    @FXML
    private JFXTextField sportfee;
    @FXML
    private JFXTextField tripfee;
    @FXML
    private JFXTextField examsfee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
