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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import login.Helper.HelperViewsResources;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class PayRollController implements Initializable {

    @FXML
    private Button addemployeesalary;
    @FXML
    private Button search;
    @FXML
    private Button allowance;
    @FXML
    private Button updatesalary;
    @FXML
    private Button deduction;
    @FXML
    private Button payment;
    @FXML
    private Button reports;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddemployeesalaryButtonhandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("AddEmployeeSalary");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Add Employee");
               stage.show();
        
    }

    @FXML
    private void SearchButtonhandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("Payrollsearch");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Search");
               stage.show();
        
    }

    @FXML
    private void AllowanceaButtonhandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("EmployeeAllownaces");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Allownace Details");
               stage.show();
    }

    @FXML
    private void UpdateSalaryButtonhandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("UpdateEmployeeSalary");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Update Salary");
               stage.show();
    }

    @FXML
    private void DeductionButtonhandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("EmployeeDeduction");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Employee Deducation");
               stage.show();
    }

    @FXML
    private void PaymentButtonhandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("EmployeePayment");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Payment information");
               stage.show();
    }

    @FXML
    private void ReportsButtonhandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("Payrollreports");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Payroll Reports");
               stage.show();
    }
    
}
