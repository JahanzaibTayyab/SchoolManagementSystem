/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class HomeController implements Initializable {

    @FXML
    private AnchorPane home;
    @FXML
    private Button student;
    @FXML
    private Button teacher;
    @FXML
    private Button admission;
    @FXML
    private Button dashboard;
    @FXML
    private Button resultcard;
    @FXML
    private Button feevoucher;
    @FXML
    private Button feedetails;
    @FXML
    private Button searchteacher;
    @FXML
    private Button addemployee;
    @FXML
    private Button reports;
    @FXML
    private Button Settings;
    @FXML
    private Button classfee;
    @FXML
    private Button addclass;
    @FXML
    private Button searchemployee;
    @FXML
    private Button aaddsubject;
    @FXML
    private Button payroll;
    @FXML
    private Button Attendance;
    @FXML
    private JFXButton logout;
    @FXML
    private Button employees;
    @FXML
    private Button Adduser;
    @FXML
    private Button backup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userrestriction();
        Attendance.setDisable(true);
        searchemployee.setDisable(true);
        // TODO
    }    

    private void Buttonevent(MouseEvent event) {
        
    }

    @FXML
    private void EventHandling(MouseEvent event) {
    }

    @FXML
    private void StudentButtonEventHandling(MouseEvent event) {
         Parent root =new HelperViewsResources().loadFXML("Students"); 
            Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Students");
               stage.showAndWait();
    }

    @FXML
    private void TeacherButtonEventHandling(MouseEvent event) {
         Parent root =new HelperViewsResources().loadFXML("Teachers");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Teachers");
               stage.show();
    }

    @FXML
    private void AdmissionButtonEventHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("AdmissionStudent");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("New Admission");
               stage.show();
    }

    @FXML
    private void ResultcardButtonEventHandling(MouseEvent event) {
        Desktop desktop=null;
        if(Desktop.isDesktopSupported())
        {
            desktop=Desktop.getDesktop();
        }
        try {      
            desktop.open(new File("E:\\SchoolManagementSystem\\src\\login\\Aggrements\\ResultGuide.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(ReportsSessionController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void FeeVoucherButtonEventHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("FeeVoucher");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Fee Voucher");
               stage.show();
    }

    @FXML
    private void FeeDetailsButtonEventHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("FeeDetails");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Fee Voucher");
               stage.show();
    }

    @FXML
    private void AddEmployeeEventHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("Addemployee");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("New Employee");
               stage.show();
    }

    @FXML
    private void ReportButtonEventHandling(MouseEvent event) {
         
    }

    @FXML
    private void ClassfeeButtonEventHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("ClassCharges");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Class Fee");
               stage.show();
    }

    @FXML
    private void AddClassButtonEventHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("AddClass");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Add Class");
               stage.show();
    }

    @FXML
    private void SearchButtonEventHandling(MouseEvent event) {
        /*Parent root =new HelperViewsResources().loadFXML("Searching");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Searching");
               stage.show();*/
    }

    @FXML
    private void AddSubjectButtonEventHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("AddSubject");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Add Subject");
               stage.show();
    }

    @FXML
    private void PayRollButtonEventHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("PayRoll");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Pay Roll");
               stage.show();
        
    }

    @FXML
    private void AttendanceButtonEventHandling(MouseEvent event) {
      /*  Parent root =new HelperViewsResources().loadFXML("Attendance");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Attendance");
               stage.show();*/
    }

    @FXML
    private void EmployeeButtonEventHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("Employee");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Employess");
               stage.show();
    }

    @FXML
    private void DashBoardButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("Dashboard");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Dashboard");
               stage.show();
    }

    @FXML
    private void ReportsActions(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("ReportsSession");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Reports");
               stage.show();
    }

    @FXML
    private void BooksPriceButtonHandling(MouseEvent event) {
        
    }

    @FXML
    private void BooksfeeButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("BooksFeeDetails");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Book Details");
               stage.show();
    }

    @FXML
    private void SettingButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("AdminInfo");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Admin Details");
               stage.show();
    }

    @FXML
    private void AddUserButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("UserInfo");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("User Details");
               stage.show();
    }

    @FXML
    private void BackupAndRestoreButtonHandling(MouseEvent event) {
        if(LoginController.isLogincheck()==true)
        {
             Parent root =new HelperViewsResources().loadFXML("UserBackup");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Backup");
               stage.show();
        }
        else
        {
        Parent root =new HelperViewsResources().loadFXML("Backupandrestore");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Backup & Restore");
               stage.show();
        }
    }
    public void userrestriction()
    {
        if(LoginController.isLogincheck()==true)
        {
        Adduser.setDisable(true);
        payroll.setDisable(true);
        Settings.setDisable(true);
        classfee.setDisable(true);
         addclass.setDisable(true);
        aaddsubject.setDisable(true);
        searchteacher.setDisable(true); 
        }
    }

    @FXML
    private void LogoutButtonAction(MouseEvent event) {
        home.setVisible(false);
        Parent root =new HelperViewsResources().loadFXML("Login");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Login");
               stage.show();
    }
}
