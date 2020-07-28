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
public class FeeVoucherController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;
    @FXML
    private Button individualvoucher;
    @FXML
    private Button classfee;
    @FXML
    private Button combinedvoucher;
    @FXML
    private Button specialvoucher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(LoginController.isLogincheck()==true)
        {
            classfee.setDisable(true);
        }
        // TODO
    }    

    @FXML
    private void PaidfeeentryButtonhandling(MouseEvent event) { 
            Parent root =new HelperViewsResources().loadFXML("StudentFeeVoucherDetails");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Fee Deatils");
               stage.show();
    }

    @FXML
    private void ClassFeeandotherFixedduesButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("FixedFeecharges");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Fixed Charges");
               stage.show();
    }

    @FXML
    private void GeneratemonthlyFeeButtonhandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("CombinedFeeVoucher");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Monthly Fee");
               stage.show();
    }

    @FXML
    private void IndividualVoucherButtonhandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("InvidualFeeVoucher");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Monthly Fee");
               stage.show();
       
    }
    
}
