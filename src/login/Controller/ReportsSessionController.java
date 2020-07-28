/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import login.Helper.HelperViewsResources;
import login.Helper.MysqlConnect;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class ReportsSessionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    @FXML
    private Button feedetails;
    @FXML
    private Button monthlydetails;
    @FXML
    private Button monthlyreceivable;
    @FXML
    private Button monthlyrecived;
    @FXML
    private Button monthlyclassreceived;
    @FXML
    private Button monthlyclassreceiveable;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
         if(LoginController.isLogincheck()==true)
         {
             feedetails.setDisable(true);
             monthlydetails.setDisable(true);
             monthlyreceivable.setDisable(true);
             monthlyrecived.setDisable(true);
             monthlyclassreceived.setDisable(true);
             monthlyclassreceiveable.setDisable(true);
         }
        // TODO
    }    

    @FXML
    private void AllStudentsButton(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\AllStudents.jrxml");
            String qry ="SELECT\n" +
"     studentinfo.`studentid` AS studentinfo_studentid,\n" +
"     studentinfo.`FirstName` AS studentinfo_FirstName,\n" +
"     studentinfo.`LastName` AS studentinfo_LastName,\n" +
"     studentinfo.`Bform` AS studentinfo_Bform,\n" +
"     studentinfo.`Cnic` AS studentinfo_Cnic,\n" +
"     studentinfo.`Occupation` AS studentinfo_Occupation,\n" +
"     studentinfo.`Email` AS studentinfo_Email,\n" +
"     studentinfo.`cellno` AS studentinfo_cellno,\n" +
"     studentinfo.`Telephone` AS studentinfo_Telephone,\n" +
"     studentinfo.`Gender` AS studentinfo_Gender,\n" +
"     studentinfo.`DateofBirth` AS studentinfo_DateofBirth,\n" +
"     studentinfo.`BloodGroup` AS studentinfo_BloodGroup,\n" +
"     studentinfo.`Address` AS studentinfo_Address,\n" +
"     studentinfo.`City` AS studentinfo_City,\n" +
"     studentinfo.`Province` AS studentinfo_Province,\n" +
"     studentinfo.`DateofAdmission` AS studentinfo_DateofAdmission,\n" +
"     studentinfo.`Class` AS studentinfo_Class,\n" +
"     studentinfo.`Section` AS studentinfo_Section,\n" +
"     studentinfo.`Reference` AS studentinfo_Reference,\n" +
"     studentinfo.`Remarks` AS studentinfo_Remarks,\n" +
"     studentinfo.`Status` AS studentinfo_Status,\n" +
"     studentinfo.`Image` AS studentinfo_Image \n" +
"FROM\n" +
"     `studentinfo` studentinfo\n" +
"order by studentinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AllStaff(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\AllTeachers.jrxml");
            String qry ="SELECT\n" +
"     employeeinfo.`employeeid` AS employeeinfo_employeeid,\n" +
"     employeeinfo.`FirstName` AS employeeinfo_FirstName,\n" +
"     employeeinfo.`LastName` AS employeeinfo_LastName,\n" +
"     employeeinfo.`CNIC` AS employeeinfo_CNIC,\n" +
"     employeeinfo.`FatherOccupation` AS employeeinfo_FatherOccupation,\n" +
"     employeeinfo.`Email` AS employeeinfo_Email,\n" +
"     employeeinfo.`CellNo` AS employeeinfo_CellNo,\n" +
"     employeeinfo.`Telephone` AS employeeinfo_Telephone,\n" +
"     employeeinfo.`DateofBirth` AS employeeinfo_DateofBirth,\n" +
"     employeeinfo.`BloodGroup` AS employeeinfo_BloodGroup,\n" +
"     employeeinfo.`gender` AS employeeinfo_gender,\n" +
"     employeeinfo.`Address` AS employeeinfo_Address,\n" +
"     employeeinfo.`City` AS employeeinfo_City,\n" +
"     employeeinfo.`Province` AS employeeinfo_Province,\n" +
"     employeeinfo.`MarriedStatus` AS employeeinfo_MarriedStatus,\n" +
"     employeeinfo.`Post` AS employeeinfo_Post,\n" +
"     employeeinfo.`EmployeeStatus` AS employeeinfo_EmployeeStatus,\n" +
"     employeeinfo.`JoiningDate` AS employeeinfo_JoiningDate,\n" +
"     employeeinfo.`LeavingDate` AS employeeinfo_LeavingDate,\n" +
"     employeeinfo.`Remarks` AS employeeinfo_Remarks,\n" +
"     employeeinfo.`Image` AS employeeinfo_Image,\n" +
"     employeeinfo.`AreaOfIntrest` AS employeeinfo_AreaOfIntrest,\n" +
"     employeeinfo.`LevelOfTeaching` AS employeeinfo_LevelOfTeaching,\n" +
"     employeeinfo.`DataStatus` AS employeeinfo_DataStatus\n" +
"FROM\n" +
"     `employeeinfo` employeeinfo\n" +
"where employeeinfo.`DataStatus`='Staff'\n" +
"order by employeeinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AllEmployee(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\AllEmployees.jrxml");
            String qry ="SELECT\n" +
"     employeeinfo.`employeeid` AS employeeinfo_employeeid,\n" +
"     employeeinfo.`FirstName` AS employeeinfo_FirstName,\n" +
"     employeeinfo.`LastName` AS employeeinfo_LastName,\n" +
"     employeeinfo.`CNIC` AS employeeinfo_CNIC,\n" +
"     employeeinfo.`FatherOccupation` AS employeeinfo_FatherOccupation,\n" +
"     employeeinfo.`Email` AS employeeinfo_Email,\n" +
"     employeeinfo.`CellNo` AS employeeinfo_CellNo,\n" +
"     employeeinfo.`Telephone` AS employeeinfo_Telephone,\n" +
"     employeeinfo.`DateofBirth` AS employeeinfo_DateofBirth,\n" +
"     employeeinfo.`BloodGroup` AS employeeinfo_BloodGroup,\n" +
"     employeeinfo.`gender` AS employeeinfo_gender,\n" +
"     employeeinfo.`Address` AS employeeinfo_Address,\n" +
"     employeeinfo.`City` AS employeeinfo_City,\n" +
"     employeeinfo.`Province` AS employeeinfo_Province,\n" +
"     employeeinfo.`MarriedStatus` AS employeeinfo_MarriedStatus,\n" +
"     employeeinfo.`Post` AS employeeinfo_Post,\n" +
"     employeeinfo.`EmployeeStatus` AS employeeinfo_EmployeeStatus,\n" +
"     employeeinfo.`JoiningDate` AS employeeinfo_JoiningDate,\n" +
"     employeeinfo.`LeavingDate` AS employeeinfo_LeavingDate,\n" +
"     employeeinfo.`Remarks` AS employeeinfo_Remarks,\n" +
"     employeeinfo.`Image` AS employeeinfo_Image,\n" +
"     employeeinfo.`AreaOfIntrest` AS employeeinfo_AreaOfIntrest,\n" +
"     employeeinfo.`LevelOfTeaching` AS employeeinfo_LevelOfTeaching,\n" +
"     employeeinfo.`DataStatus` AS employeeinfo_DataStatus\n" +
"FROM\n" +
"     `employeeinfo` employeeinfo\n" +
"where employeeinfo.`DataStatus`='Employee'\n" +
"order by employeeinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void FeeDetails(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\FeeDetailsReprts.jrxml");
            String qry ="SELECT\n" +
"     feedetails.`feecode` AS feedetails_feecode,\n" +
"     feedetails.`StudentID` AS feedetails_StudentID,\n" +
"     feedetails.`StudentName` AS feedetails_StudentName,\n" +
"     feedetails.`Month` AS feedetails_Month,\n" +
"     feedetails.`Year` AS feedetails_Year,\n" +
"     feedetails.`Classfee` AS feedetails_Classfee,\n" +
"     feedetails.`GeneratorFee` AS feedetails_GeneratorFee,\n" +
"     feedetails.`FunctionFee` AS feedetails_FunctionFee,\n" +
"     feedetails.`ExamsFee` AS feedetails_ExamsFee,\n" +
"     feedetails.`BooksFee` AS feedetails_BooksFee,\n" +
"     feedetails.`PartyFee` AS feedetails_PartyFee,\n" +
"     feedetails.`SportsFee` AS feedetails_SportsFee,\n" +
"     feedetails.`TripFee` AS feedetails_TripFee,\n" +
"     feedetails.`StationaryFee` AS feedetails_StationaryFee,\n" +
"     feedetails.`Others` AS feedetails_Others,\n" +
"     feedetails.`TotalDues` AS feedetails_TotalDues,\n" +
"     feedetails.`Balance` AS feedetails_Balance,\n" +
"     feedetails.`ClassName` AS feedetails_ClassName,\n" +
"     feedetails.`Section` AS feedetails_Section,\n" +
"     feedetails.`Paid` AS feedetails_Paid,\n" +
"     feedetails.`AdmissionFee` AS feedetails_AdmissionFee,\n" +
"     feedetails.`SecurityFee` AS feedetails_SecurityFee,\n" +
"     feedetails.`Lastmonthdues` AS feedetails_Lastmonthdues\n" +
"FROM\n" +
"     `feedetails` feedetails\n" +
"order by feedetails.`Month`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AllCurrentStudent(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\AllPresentStudents.jrxml");
            String qry ="SELECT\n" +
"     studentinfo.`studentid` AS studentinfo_studentid,\n" +
"     studentinfo.`FirstName` AS studentinfo_FirstName,\n" +
"     studentinfo.`LastName` AS studentinfo_LastName,\n" +
"     studentinfo.`Bform` AS studentinfo_Bform,\n" +
"     studentinfo.`Cnic` AS studentinfo_Cnic,\n" +
"     studentinfo.`Occupation` AS studentinfo_Occupation,\n" +
"     studentinfo.`Email` AS studentinfo_Email,\n" +
"     studentinfo.`cellno` AS studentinfo_cellno,\n" +
"     studentinfo.`Telephone` AS studentinfo_Telephone,\n" +
"     studentinfo.`Gender` AS studentinfo_Gender,\n" +
"     studentinfo.`DateofBirth` AS studentinfo_DateofBirth,\n" +
"     studentinfo.`BloodGroup` AS studentinfo_BloodGroup,\n" +
"     studentinfo.`Address` AS studentinfo_Address,\n" +
"     studentinfo.`City` AS studentinfo_City,\n" +
"     studentinfo.`Province` AS studentinfo_Province,\n" +
"     studentinfo.`DateofAdmission` AS studentinfo_DateofAdmission,\n" +
"     studentinfo.`Class` AS studentinfo_Class,\n" +
"     studentinfo.`Section` AS studentinfo_Section,\n" +
"     studentinfo.`Reference` AS studentinfo_Reference,\n" +
"     studentinfo.`Remarks` AS studentinfo_Remarks,\n" +
"     studentinfo.`Status` AS studentinfo_Status,\n" +
"     studentinfo.`Image` AS studentinfo_Image\n" +
"FROM\n" +
"     `studentinfo` studentinfo\n" +
"where studentinfo.`Status`='Student'\n" +
"order by studentinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ActiveStaff(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\AllActiveTeachers.jrxml");
            String qry ="SELECT\n" +
"     employeeinfo.`employeeid` AS employeeinfo_employeeid,\n" +
"     employeeinfo.`FirstName` AS employeeinfo_FirstName,\n" +
"     employeeinfo.`LastName` AS employeeinfo_LastName,\n" +
"     employeeinfo.`CNIC` AS employeeinfo_CNIC,\n" +
"     employeeinfo.`FatherOccupation` AS employeeinfo_FatherOccupation,\n" +
"     employeeinfo.`Email` AS employeeinfo_Email,\n" +
"     employeeinfo.`CellNo` AS employeeinfo_CellNo,\n" +
"     employeeinfo.`Telephone` AS employeeinfo_Telephone,\n" +
"     employeeinfo.`DateofBirth` AS employeeinfo_DateofBirth,\n" +
"     employeeinfo.`BloodGroup` AS employeeinfo_BloodGroup,\n" +
"     employeeinfo.`gender` AS employeeinfo_gender,\n" +
"     employeeinfo.`Address` AS employeeinfo_Address,\n" +
"     employeeinfo.`City` AS employeeinfo_City,\n" +
"     employeeinfo.`Province` AS employeeinfo_Province,\n" +
"     employeeinfo.`MarriedStatus` AS employeeinfo_MarriedStatus,\n" +
"     employeeinfo.`Post` AS employeeinfo_Post,\n" +
"     employeeinfo.`EmployeeStatus` AS employeeinfo_EmployeeStatus,\n" +
"     employeeinfo.`JoiningDate` AS employeeinfo_JoiningDate,\n" +
"     employeeinfo.`LeavingDate` AS employeeinfo_LeavingDate,\n" +
"     employeeinfo.`Remarks` AS employeeinfo_Remarks,\n" +
"     employeeinfo.`Image` AS employeeinfo_Image,\n" +
"     employeeinfo.`AreaOfIntrest` AS employeeinfo_AreaOfIntrest,\n" +
"     employeeinfo.`LevelOfTeaching` AS employeeinfo_LevelOfTeaching,\n" +
"     employeeinfo.`DataStatus` AS employeeinfo_DataStatus\n" +
"FROM\n" +
"     `employeeinfo` employeeinfo\n" +
"where employeeinfo.`DataStatus`='Staff'\n" +
"and  employeeinfo.`EmployeeStatus`='Active'\n" +
"order by employeeinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ActiveEmployee(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\AllActiveEmployee.jrxml");
            String qry ="SELECT\n" +
"     employeeinfo.`employeeid` AS employeeinfo_employeeid,\n" +
"     employeeinfo.`FirstName` AS employeeinfo_FirstName,\n" +
"     employeeinfo.`LastName` AS employeeinfo_LastName,\n" +
"     employeeinfo.`CNIC` AS employeeinfo_CNIC,\n" +
"     employeeinfo.`FatherOccupation` AS employeeinfo_FatherOccupation,\n" +
"     employeeinfo.`Email` AS employeeinfo_Email,\n" +
"     employeeinfo.`CellNo` AS employeeinfo_CellNo,\n" +
"     employeeinfo.`Telephone` AS employeeinfo_Telephone,\n" +
"     employeeinfo.`DateofBirth` AS employeeinfo_DateofBirth,\n" +
"     employeeinfo.`BloodGroup` AS employeeinfo_BloodGroup,\n" +
"     employeeinfo.`gender` AS employeeinfo_gender,\n" +
"     employeeinfo.`Address` AS employeeinfo_Address,\n" +
"     employeeinfo.`City` AS employeeinfo_City,\n" +
"     employeeinfo.`Province` AS employeeinfo_Province,\n" +
"     employeeinfo.`MarriedStatus` AS employeeinfo_MarriedStatus,\n" +
"     employeeinfo.`Post` AS employeeinfo_Post,\n" +
"     employeeinfo.`EmployeeStatus` AS employeeinfo_EmployeeStatus,\n" +
"     employeeinfo.`JoiningDate` AS employeeinfo_JoiningDate,\n" +
"     employeeinfo.`LeavingDate` AS employeeinfo_LeavingDate,\n" +
"     employeeinfo.`Remarks` AS employeeinfo_Remarks,\n" +
"     employeeinfo.`Image` AS employeeinfo_Image,\n" +
"     employeeinfo.`AreaOfIntrest` AS employeeinfo_AreaOfIntrest,\n" +
"     employeeinfo.`LevelOfTeaching` AS employeeinfo_LevelOfTeaching,\n" +
"     employeeinfo.`DataStatus` AS employeeinfo_DataStatus\n" +
"FROM\n" +
"     `employeeinfo` employeeinfo\n" +
"where employeeinfo.`DataStatus`='Employee'\n" +
"and employeeinfo.`EmployeeStatus`='Active'\n" +  
"order by employeeinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void MonthlyFeeDetails(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("MonthlyFeeDetailsReports");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Reports");
               stage.show();
    }

    @FXML
    private void AllLeaveStudent(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\AllLeaveStudent.jrxml");
            String qry ="SELECT\n" +
"     studentinfo.`studentid` AS studentinfo_studentid,\n" +
"     studentinfo.`FirstName` AS studentinfo_FirstName,\n" +
"     studentinfo.`LastName` AS studentinfo_LastName,\n" +
"     studentinfo.`Bform` AS studentinfo_Bform,\n" +
"     studentinfo.`Cnic` AS studentinfo_Cnic,\n" +
"     studentinfo.`Occupation` AS studentinfo_Occupation,\n" +
"     studentinfo.`Email` AS studentinfo_Email,\n" +
"     studentinfo.`cellno` AS studentinfo_cellno,\n" +
"     studentinfo.`Telephone` AS studentinfo_Telephone,\n" +
"     studentinfo.`Gender` AS studentinfo_Gender,\n" +
"     studentinfo.`DateofBirth` AS studentinfo_DateofBirth,\n" +
"     studentinfo.`BloodGroup` AS studentinfo_BloodGroup,\n" +
"     studentinfo.`Address` AS studentinfo_Address,\n" +
"     studentinfo.`City` AS studentinfo_City,\n" +
"     studentinfo.`Province` AS studentinfo_Province,\n" +
"     studentinfo.`DateofAdmission` AS studentinfo_DateofAdmission,\n" +
"     studentinfo.`Class` AS studentinfo_Class,\n" +
"     studentinfo.`Section` AS studentinfo_Section,\n" +
"     studentinfo.`Reference` AS studentinfo_Reference,\n" +
"     studentinfo.`Remarks` AS studentinfo_Remarks,\n" +
"     studentinfo.`Status` AS studentinfo_Status,\n" +
"     studentinfo.`Image` AS studentinfo_Image\n" +
"FROM\n" +
"     `studentinfo` studentinfo\n" +
"where studentinfo.`Status`='Clear'\n" +
"order by studentinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void StaffAdressBook(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\TeacherAddressBook.jrxml");
            String qry ="SELECT\n" +
"     employeeinfo.`employeeid` AS employeeinfo_employeeid,\n" +
"     employeeinfo.`FirstName` AS employeeinfo_FirstName,\n" +
"     employeeinfo.`LastName` AS employeeinfo_LastName,\n" +
"     employeeinfo.`CNIC` AS employeeinfo_CNIC,\n" +
"     employeeinfo.`FatherOccupation` AS employeeinfo_FatherOccupation,\n" +
"     employeeinfo.`Email` AS employeeinfo_Email,\n" +
"     employeeinfo.`CellNo` AS employeeinfo_CellNo,\n" +
"     employeeinfo.`Telephone` AS employeeinfo_Telephone,\n" +
"     employeeinfo.`DateofBirth` AS employeeinfo_DateofBirth,\n" +
"     employeeinfo.`BloodGroup` AS employeeinfo_BloodGroup,\n" +
"     employeeinfo.`gender` AS employeeinfo_gender,\n" +
"     employeeinfo.`Address` AS employeeinfo_Address,\n" +
"     employeeinfo.`City` AS employeeinfo_City,\n" +
"     employeeinfo.`Province` AS employeeinfo_Province,\n" +
"     employeeinfo.`MarriedStatus` AS employeeinfo_MarriedStatus,\n" +
"     employeeinfo.`Post` AS employeeinfo_Post,\n" +
"     employeeinfo.`EmployeeStatus` AS employeeinfo_EmployeeStatus,\n" +
"     employeeinfo.`JoiningDate` AS employeeinfo_JoiningDate,\n" +
"     employeeinfo.`LeavingDate` AS employeeinfo_LeavingDate,\n" +
"     employeeinfo.`Remarks` AS employeeinfo_Remarks,\n" +
"     employeeinfo.`Image` AS employeeinfo_Image,\n" +
"     employeeinfo.`AreaOfIntrest` AS employeeinfo_AreaOfIntrest,\n" +
"     employeeinfo.`LevelOfTeaching` AS employeeinfo_LevelOfTeaching,\n" +
"     employeeinfo.`DataStatus` AS employeeinfo_DataStatus\n" +
"FROM\n" +
"     `employeeinfo` employeeinfo\n" +
"where employeeinfo.`DataStatus`='Staff'\n" +
"order by employeeinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EmployeeAddressBook(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\EmployeeAddressBook.jrxml");
            String qry ="SELECT\n" +
"     employeeinfo.`employeeid` AS employeeinfo_employeeid,\n" +
"     employeeinfo.`FirstName` AS employeeinfo_FirstName,\n" +
"     employeeinfo.`LastName` AS employeeinfo_LastName,\n" +
"     employeeinfo.`CNIC` AS employeeinfo_CNIC,\n" +
"     employeeinfo.`FatherOccupation` AS employeeinfo_FatherOccupation,\n" +
"     employeeinfo.`Email` AS employeeinfo_Email,\n" +
"     employeeinfo.`CellNo` AS employeeinfo_CellNo,\n" +
"     employeeinfo.`Telephone` AS employeeinfo_Telephone,\n" +
"     employeeinfo.`DateofBirth` AS employeeinfo_DateofBirth,\n" +
"     employeeinfo.`BloodGroup` AS employeeinfo_BloodGroup,\n" +
"     employeeinfo.`gender` AS employeeinfo_gender,\n" +
"     employeeinfo.`Address` AS employeeinfo_Address,\n" +
"     employeeinfo.`City` AS employeeinfo_City,\n" +
"     employeeinfo.`Province` AS employeeinfo_Province,\n" +
"     employeeinfo.`MarriedStatus` AS employeeinfo_MarriedStatus,\n" +
"     employeeinfo.`Post` AS employeeinfo_Post,\n" +
"     employeeinfo.`EmployeeStatus` AS employeeinfo_EmployeeStatus,\n" +
"     employeeinfo.`JoiningDate` AS employeeinfo_JoiningDate,\n" +
"     employeeinfo.`LeavingDate` AS employeeinfo_LeavingDate,\n" +
"     employeeinfo.`Remarks` AS employeeinfo_Remarks,\n" +
"     employeeinfo.`Image` AS employeeinfo_Image,\n" +
"     employeeinfo.`AreaOfIntrest` AS employeeinfo_AreaOfIntrest,\n" +
"     employeeinfo.`LevelOfTeaching` AS employeeinfo_LevelOfTeaching,\n" +
"     employeeinfo.`DataStatus` AS employeeinfo_DataStatus\n" +
"FROM\n" +
"     `employeeinfo` employeeinfo\n" +
"where employeeinfo.`DataStatus`='Employee'\n" +
"order by employeeinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SubjectInfo(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\AllSubjects.jrxml");
            String qry ="SELECT\n" +
"     subjectinfo.`SubjectCode` AS subjectinfo_SubjectCode,\n" +
"     subjectinfo.`SubjectName` AS subjectinfo_SubjectName,\n" +
"     subjectinfo.`BookName` AS subjectinfo_BookName,\n" +
"     subjectinfo.`TeacherId` AS subjectinfo_TeacherId,\n" +
"     subjectinfo.`SubjectMarks` AS subjectinfo_SubjectMarks\n" +
"FROM\n" +
"     `subjectinfo` subjectinfo\n" +
"order by subjectinfo.`BookName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AllPassoutStudent(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\AllPassoutStudent.jrxml");
            String qry ="SELECT\n" +
"     studentinfo.`studentid` AS studentinfo_studentid,\n" +
"     studentinfo.`FirstName` AS studentinfo_FirstName,\n" +
"     studentinfo.`LastName` AS studentinfo_LastName,\n" +
"     studentinfo.`Bform` AS studentinfo_Bform,\n" +
"     studentinfo.`Cnic` AS studentinfo_Cnic,\n" +
"     studentinfo.`Occupation` AS studentinfo_Occupation,\n" +
"     studentinfo.`Email` AS studentinfo_Email,\n" +
"     studentinfo.`cellno` AS studentinfo_cellno,\n" +
"     studentinfo.`Telephone` AS studentinfo_Telephone,\n" +
"     studentinfo.`Gender` AS studentinfo_Gender,\n" +
"     studentinfo.`DateofBirth` AS studentinfo_DateofBirth,\n" +
"     studentinfo.`BloodGroup` AS studentinfo_BloodGroup,\n" +
"     studentinfo.`Address` AS studentinfo_Address,\n" +
"     studentinfo.`City` AS studentinfo_City,\n" +
"     studentinfo.`Province` AS studentinfo_Province,\n" +
"     studentinfo.`DateofAdmission` AS studentinfo_DateofAdmission,\n" +
"     studentinfo.`Class` AS studentinfo_Class,\n" +
"     studentinfo.`Section` AS studentinfo_Section,\n" +
"     studentinfo.`Reference` AS studentinfo_Reference,\n" +
"     studentinfo.`Remarks` AS studentinfo_Remarks,\n" +
"     studentinfo.`Status` AS studentinfo_Status,\n" +
"     studentinfo.`Image` AS studentinfo_Image\n" +
"FROM\n" +
"     `studentinfo` studentinfo\n" +
"	where studentinfo.`Status`='Passout'\n" +
"order by studentinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void UnActiveStaff(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\AllUnactiveTeachers.jrxml");
            String qry ="SELECT\n" +
"     employeeinfo.`employeeid` AS employeeinfo_employeeid,\n" +
"     employeeinfo.`FirstName` AS employeeinfo_FirstName,\n" +
"     employeeinfo.`LastName` AS employeeinfo_LastName,\n" +
"     employeeinfo.`CNIC` AS employeeinfo_CNIC,\n" +
"     employeeinfo.`FatherOccupation` AS employeeinfo_FatherOccupation,\n" +
"     employeeinfo.`Email` AS employeeinfo_Email,\n" +
"     employeeinfo.`CellNo` AS employeeinfo_CellNo,\n" +
"     employeeinfo.`Telephone` AS employeeinfo_Telephone,\n" +
"     employeeinfo.`DateofBirth` AS employeeinfo_DateofBirth,\n" +
"     employeeinfo.`BloodGroup` AS employeeinfo_BloodGroup,\n" +
"     employeeinfo.`gender` AS employeeinfo_gender,\n" +
"     employeeinfo.`Address` AS employeeinfo_Address,\n" +
"     employeeinfo.`City` AS employeeinfo_City,\n" +
"     employeeinfo.`Province` AS employeeinfo_Province,\n" +
"     employeeinfo.`MarriedStatus` AS employeeinfo_MarriedStatus,\n" +
"     employeeinfo.`Post` AS employeeinfo_Post,\n" +
"     employeeinfo.`EmployeeStatus` AS employeeinfo_EmployeeStatus,\n" +
"     employeeinfo.`JoiningDate` AS employeeinfo_JoiningDate,\n" +
"     employeeinfo.`LeavingDate` AS employeeinfo_LeavingDate,\n" +
"     employeeinfo.`Remarks` AS employeeinfo_Remarks,\n" +
"     employeeinfo.`Image` AS employeeinfo_Image,\n" +
"     employeeinfo.`AreaOfIntrest` AS employeeinfo_AreaOfIntrest,\n" +
"     employeeinfo.`LevelOfTeaching` AS employeeinfo_LevelOfTeaching,\n" +
"     employeeinfo.`DataStatus` AS employeeinfo_DataStatus\n" +
"FROM\n" +
"     `employeeinfo` employeeinfo\n" +
"where employeeinfo.`DataStatus`='Staff' and employeeinfo.`EmployeeStatus`='Unactive'\n" +
"order by employeeinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void UnActiveEmployee(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\AllUnactiveEmployee.jrxml");
            String qry ="SELECT\n" +
"     employeeinfo.`employeeid` AS employeeinfo_employeeid,\n" +
"     employeeinfo.`FirstName` AS employeeinfo_FirstName,\n" +
"     employeeinfo.`LastName` AS employeeinfo_LastName,\n" +
"     employeeinfo.`CNIC` AS employeeinfo_CNIC,\n" +
"     employeeinfo.`FatherOccupation` AS employeeinfo_FatherOccupation,\n" +
"     employeeinfo.`Email` AS employeeinfo_Email,\n" +
"     employeeinfo.`CellNo` AS employeeinfo_CellNo,\n" +
"     employeeinfo.`Telephone` AS employeeinfo_Telephone,\n" +
"     employeeinfo.`DateofBirth` AS employeeinfo_DateofBirth,\n" +
"     employeeinfo.`BloodGroup` AS employeeinfo_BloodGroup,\n" +
"     employeeinfo.`gender` AS employeeinfo_gender,\n" +
"     employeeinfo.`Address` AS employeeinfo_Address,\n" +
"     employeeinfo.`City` AS employeeinfo_City,\n" +
"     employeeinfo.`Province` AS employeeinfo_Province,\n" +
"     employeeinfo.`MarriedStatus` AS employeeinfo_MarriedStatus,\n" +
"     employeeinfo.`Post` AS employeeinfo_Post,\n" +
"     employeeinfo.`EmployeeStatus` AS employeeinfo_EmployeeStatus,\n" +
"     employeeinfo.`JoiningDate` AS employeeinfo_JoiningDate,\n" +
"     employeeinfo.`LeavingDate` AS employeeinfo_LeavingDate,\n" +
"     employeeinfo.`Remarks` AS employeeinfo_Remarks,\n" +
"     employeeinfo.`Image` AS employeeinfo_Image,\n" +
"     employeeinfo.`AreaOfIntrest` AS employeeinfo_AreaOfIntrest,\n" +
"     employeeinfo.`LevelOfTeaching` AS employeeinfo_LevelOfTeaching,\n" +
"     employeeinfo.`DataStatus` AS employeeinfo_DataStatus\n" +
"FROM\n" +
"     `employeeinfo` employeeinfo\n" +
"where employeeinfo.`DataStatus`='Employee' and employeeinfo.`EmployeeStatus`='Unactive'\n" +
"order by employeeinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GenderWiseStudents(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\GenderwiseStudent.jrxml");
            String qry ="SELECT\n" +
"     studentinfo.`studentid` AS studentinfo_studentid,\n" +
"     studentinfo.`FirstName` AS studentinfo_FirstName,\n" +
"     studentinfo.`LastName` AS studentinfo_LastName,\n" +
"     studentinfo.`Bform` AS studentinfo_Bform,\n" +
"     studentinfo.`Cnic` AS studentinfo_Cnic,\n" +
"     studentinfo.`Occupation` AS studentinfo_Occupation,\n" +
"     studentinfo.`Email` AS studentinfo_Email,\n" +
"     studentinfo.`cellno` AS studentinfo_cellno,\n" +
"     studentinfo.`Telephone` AS studentinfo_Telephone,\n" +
"     studentinfo.`Gender` AS studentinfo_Gender,\n" +
"     studentinfo.`DateofBirth` AS studentinfo_DateofBirth,\n" +
"     studentinfo.`BloodGroup` AS studentinfo_BloodGroup,\n" +
"     studentinfo.`Address` AS studentinfo_Address,\n" +
"     studentinfo.`City` AS studentinfo_City,\n" +
"     studentinfo.`Province` AS studentinfo_Province,\n" +
"     studentinfo.`DateofAdmission` AS studentinfo_DateofAdmission,\n" +
"     studentinfo.`Class` AS studentinfo_Class,\n" +
"     studentinfo.`Section` AS studentinfo_Section,\n" +
"     studentinfo.`Reference` AS studentinfo_Reference,\n" +
"     studentinfo.`Remarks` AS studentinfo_Remarks,\n" +
"     studentinfo.`Status` AS studentinfo_Status,\n" +
"     studentinfo.`Image` AS studentinfo_Image\n" +
"FROM\n" +
"     `studentinfo` studentinfo\n" +
"order by studentinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void StaffContactList(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\TeacherContactList.jrxml");
            String qry ="SELECT\n" +
"     employeeinfo.`employeeid` AS employeeinfo_employeeid,\n" +
"     employeeinfo.`FirstName` AS employeeinfo_FirstName,\n" +
"     employeeinfo.`LastName` AS employeeinfo_LastName,\n" +
"     employeeinfo.`CNIC` AS employeeinfo_CNIC,\n" +
"     employeeinfo.`FatherOccupation` AS employeeinfo_FatherOccupation,\n" +
"     employeeinfo.`Email` AS employeeinfo_Email,\n" +
"     employeeinfo.`CellNo` AS employeeinfo_CellNo,\n" +
"     employeeinfo.`Telephone` AS employeeinfo_Telephone,\n" +
"     employeeinfo.`DateofBirth` AS employeeinfo_DateofBirth,\n" +
"     employeeinfo.`BloodGroup` AS employeeinfo_BloodGroup,\n" +
"     employeeinfo.`gender` AS employeeinfo_gender,\n" +
"     employeeinfo.`Address` AS employeeinfo_Address,\n" +
"     employeeinfo.`City` AS employeeinfo_City,\n" +
"     employeeinfo.`Province` AS employeeinfo_Province,\n" +
"     employeeinfo.`MarriedStatus` AS employeeinfo_MarriedStatus,\n" +
"     employeeinfo.`Post` AS employeeinfo_Post,\n" +
"     employeeinfo.`EmployeeStatus` AS employeeinfo_EmployeeStatus,\n" +
"     employeeinfo.`JoiningDate` AS employeeinfo_JoiningDate,\n" +
"     employeeinfo.`LeavingDate` AS employeeinfo_LeavingDate,\n" +
"     employeeinfo.`Remarks` AS employeeinfo_Remarks,\n" +
"     employeeinfo.`Image` AS employeeinfo_Image,\n" +
"     employeeinfo.`AreaOfIntrest` AS employeeinfo_AreaOfIntrest,\n" +
"     employeeinfo.`LevelOfTeaching` AS employeeinfo_LevelOfTeaching,\n" +
"     employeeinfo.`DataStatus` AS employeeinfo_DataStatus\n" +
"FROM\n" +
"     `employeeinfo` employeeinfo\n" +
"where employeeinfo.`DataStatus`='Staff'\n" +
"order by employeeinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EmployeeContactList(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\EmployeeContactList.jrxml");
            String qry ="SELECT\n" +
"     employeeinfo.`employeeid` AS employeeinfo_employeeid,\n" +
"     employeeinfo.`FirstName` AS employeeinfo_FirstName,\n" +
"     employeeinfo.`LastName` AS employeeinfo_LastName,\n" +
"     employeeinfo.`CNIC` AS employeeinfo_CNIC,\n" +
"     employeeinfo.`FatherOccupation` AS employeeinfo_FatherOccupation,\n" +
"     employeeinfo.`Email` AS employeeinfo_Email,\n" +
"     employeeinfo.`CellNo` AS employeeinfo_CellNo,\n" +
"     employeeinfo.`Telephone` AS employeeinfo_Telephone,\n" +
"     employeeinfo.`DateofBirth` AS employeeinfo_DateofBirth,\n" +
"     employeeinfo.`BloodGroup` AS employeeinfo_BloodGroup,\n" +
"     employeeinfo.`gender` AS employeeinfo_gender,\n" +
"     employeeinfo.`Address` AS employeeinfo_Address,\n" +
"     employeeinfo.`City` AS employeeinfo_City,\n" +
"     employeeinfo.`Province` AS employeeinfo_Province,\n" +
"     employeeinfo.`MarriedStatus` AS employeeinfo_MarriedStatus,\n" +
"     employeeinfo.`Post` AS employeeinfo_Post,\n" +
"     employeeinfo.`EmployeeStatus` AS employeeinfo_EmployeeStatus,\n" +
"     employeeinfo.`JoiningDate` AS employeeinfo_JoiningDate,\n" +
"     employeeinfo.`LeavingDate` AS employeeinfo_LeavingDate,\n" +
"     employeeinfo.`Remarks` AS employeeinfo_Remarks,\n" +
"     employeeinfo.`Image` AS employeeinfo_Image,\n" +
"     employeeinfo.`AreaOfIntrest` AS employeeinfo_AreaOfIntrest,\n" +
"     employeeinfo.`LevelOfTeaching` AS employeeinfo_LevelOfTeaching,\n" +
"     employeeinfo.`DataStatus` AS employeeinfo_DataStatus\n" +
"FROM\n" +
"     `employeeinfo` employeeinfo\n" +
"where employeeinfo.`DataStatus`='Employee'\n" +
"order by employeeinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ClassWiseStudents(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\ClassWiseStudent.jrxml");
            String qry ="SELECT\n" +
"     studentinfo.`studentid` AS studentinfo_studentid,\n" +
"     studentinfo.`FirstName` AS studentinfo_FirstName,\n" +
"     studentinfo.`LastName` AS studentinfo_LastName,\n" +
"     studentinfo.`Bform` AS studentinfo_Bform,\n" +
"     studentinfo.`Cnic` AS studentinfo_Cnic,\n" +
"     studentinfo.`Occupation` AS studentinfo_Occupation,\n" +
"     studentinfo.`Email` AS studentinfo_Email,\n" +
"     studentinfo.`cellno` AS studentinfo_cellno,\n" +
"     studentinfo.`Telephone` AS studentinfo_Telephone,\n" +
"     studentinfo.`Gender` AS studentinfo_Gender,\n" +
"     studentinfo.`DateofBirth` AS studentinfo_DateofBirth,\n" +
"     studentinfo.`BloodGroup` AS studentinfo_BloodGroup,\n" +
"     studentinfo.`Address` AS studentinfo_Address,\n" +
"     studentinfo.`City` AS studentinfo_City,\n" +
"     studentinfo.`Province` AS studentinfo_Province,\n" +
"     studentinfo.`DateofAdmission` AS studentinfo_DateofAdmission,\n" +
"     studentinfo.`Class` AS studentinfo_Class,\n" +
"     studentinfo.`Section` AS studentinfo_Section,\n" +
"     studentinfo.`Reference` AS studentinfo_Reference,\n" +
"     studentinfo.`Remarks` AS studentinfo_Remarks,\n" +
"     studentinfo.`Status` AS studentinfo_Status,\n" +
"     studentinfo.`Image` AS studentinfo_Image\n" +
"FROM\n" +
"     `studentinfo` studentinfo\n" +
"order by studentinfo.`Class`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void StaffAgreement(MouseEvent event) {
        Desktop desktop=null;
        if(Desktop.isDesktopSupported())
        {
            desktop=Desktop.getDesktop();
        }
        try {      
            desktop.open(new File("E:\\SchoolManagementSystem\\src\\login\\Aggrements\\teacher agreement .docx"));
        } catch (IOException ex) {
            Logger.getLogger(ReportsSessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EmployeeAggrement(MouseEvent event) {
        Desktop desktop=null;
        if(Desktop.isDesktopSupported())
        {
            desktop=Desktop.getDesktop();
        }
        try {      
            desktop.open(new File("E:\\SchoolManagementSystem\\src\\login\\Aggrements\\teacher agreement .docx"));
        } catch (IOException ex) {
            Logger.getLogger(ReportsSessionController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @FXML
    private void ClassInfo(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\ClassInfo.jrxml");
            String qry ="SELECT\n" +
"     classinfo.`ClassName` AS classinfo_ClassName,\n" +
"     classinfo.`Section` AS classinfo_Section,\n" +
"     classinfo.`Subject1Name` AS classinfo_Subject1Name,\n" +
"     classinfo.`Subject2Name` AS classinfo_Subject2Name,\n" +
"     classinfo.`Subject3Name` AS classinfo_Subject3Name,\n" +
"     classinfo.`Subject4Name` AS classinfo_Subject4Name,\n" +
"     classinfo.`Subject5Name` AS classinfo_Subject5Name,\n" +
"     classinfo.`Subject6Name` AS classinfo_Subject6Name,\n" +
"     classinfo.`Subject7Name` AS classinfo_Subject7Name,\n" +
"     classinfo.`Subject8Name` AS classinfo_Subject8Name,\n" +
"     classinfo.`Subject9Name` AS classinfo_Subject9Name,\n" +
"     classinfo.`Subject10Name` AS classinfo_Subject10Name,\n" +
"     classinfo.`Subject11Name` AS classinfo_Subject11Name,\n" +
"     classinfo.`Subject12Name` AS classinfo_Subject12Name\n" +
"FROM\n" +
"     `classinfo` classinfo\n" +
"order by classinfo.`ClassName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @FXML
    private void StudentForms(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\StudentsForms.jrxml");
            String qry ="SELECT\n" +
"     studentinfo.`studentid` AS studentinfo_studentid,\n" +
"     studentinfo.`FirstName` AS studentinfo_FirstName,\n" +
"     studentinfo.`LastName` AS studentinfo_LastName,\n" +
"     studentinfo.`Bform` AS studentinfo_Bform,\n" +
"     studentinfo.`Cnic` AS studentinfo_Cnic,\n" +
"     studentinfo.`Occupation` AS studentinfo_Occupation,\n" +
"     studentinfo.`Email` AS studentinfo_Email,\n" +
"     studentinfo.`cellno` AS studentinfo_cellno,\n" +
"     studentinfo.`Telephone` AS studentinfo_Telephone,\n" +
"     studentinfo.`Gender` AS studentinfo_Gender,\n" +
"     studentinfo.`DateofBirth` AS studentinfo_DateofBirth,\n" +
"     studentinfo.`BloodGroup` AS studentinfo_BloodGroup,\n" +
"     studentinfo.`Address` AS studentinfo_Address,\n" +
"     studentinfo.`City` AS studentinfo_City,\n" +
"     studentinfo.`Province` AS studentinfo_Province,\n" +
"     studentinfo.`DateofAdmission` AS studentinfo_DateofAdmission,\n" +
"     studentinfo.`Class` AS studentinfo_Class,\n" +
"     studentinfo.`Section` AS studentinfo_Section,\n" +
"     studentinfo.`Reference` AS studentinfo_Reference,\n" +
"     studentinfo.`Remarks` AS studentinfo_Remarks,\n" +
"     studentinfo.`Status` AS studentinfo_Status,\n" +
"     studentinfo.`Image` AS studentinfo_Image\n" +
"FROM\n" +
"     `studentinfo` studentinfo";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void StaffRegistration(MouseEvent event) {
        Desktop desktop=null;
        if(Desktop.isDesktopSupported())
        {
            desktop=Desktop.getDesktop();
        }
        try {      
            desktop.open(new File("E:\\SchoolManagementSystem\\src\\login\\Aggrements\\assass scholl tuter registration.docx"));
        } catch (IOException ex) {
            Logger.getLogger(ReportsSessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void EmpReg(MouseEvent event) {
        Desktop desktop=null;
        if(Desktop.isDesktopSupported())
        {
            desktop=Desktop.getDesktop();
        }
        try {      
            desktop.open(new File("E:\\SchoolManagementSystem\\src\\login\\Aggrements\\assass scholl tuter registration.docx"));
        } catch (IOException ex) {
            Logger.getLogger(ReportsSessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void AdmissionForms(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\AdmissionForm.jrxml");
            String qry ="SELECT\n" +
"     studentinfo.`studentid` AS studentinfo_studentid,\n" +
"     studentinfo.`FirstName` AS studentinfo_FirstName,\n" +
"     studentinfo.`LastName` AS studentinfo_LastName,\n" +
"     studentinfo.`Bform` AS studentinfo_Bform,\n" +
"     studentinfo.`Cnic` AS studentinfo_Cnic,\n" +
"     studentinfo.`Occupation` AS studentinfo_Occupation,\n" +
"     studentinfo.`Email` AS studentinfo_Email,\n" +
"     studentinfo.`cellno` AS studentinfo_cellno,\n" +
"     studentinfo.`Telephone` AS studentinfo_Telephone,\n" +
"     studentinfo.`Gender` AS studentinfo_Gender,\n" +
"     studentinfo.`DateofBirth` AS studentinfo_DateofBirth,\n" +
"     studentinfo.`BloodGroup` AS studentinfo_BloodGroup,\n" +
"     studentinfo.`Address` AS studentinfo_Address,\n" +
"     studentinfo.`City` AS studentinfo_City,\n" +
"     studentinfo.`Province` AS studentinfo_Province,\n" +
"     studentinfo.`DateofAdmission` AS studentinfo_DateofAdmission,\n" +
"     studentinfo.`Class` AS studentinfo_Class,\n" +
"     studentinfo.`Section` AS studentinfo_Section,\n" +
"     studentinfo.`Reference` AS studentinfo_Reference,\n" +
"     studentinfo.`Remarks` AS studentinfo_Remarks,\n" +
"     studentinfo.`Status` AS studentinfo_Status,\n" +
"     studentinfo.`Image` AS studentinfo_Image\n" +
"FROM\n" +
"     `studentinfo` studentinfo";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void StudentAddressBook(MouseEvent event) {
       try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\StudentAddress.jrxml");
            String qry ="SELECT\n" +
"     studentinfo.`studentid` AS studentinfo_studentid,\n" +
"     studentinfo.`FirstName` AS studentinfo_FirstName,\n" +
"     studentinfo.`LastName` AS studentinfo_LastName,\n" +
"     studentinfo.`Bform` AS studentinfo_Bform,\n" +
"     studentinfo.`Cnic` AS studentinfo_Cnic,\n" +
"     studentinfo.`Occupation` AS studentinfo_Occupation,\n" +
"     studentinfo.`Email` AS studentinfo_Email,\n" +
"     studentinfo.`cellno` AS studentinfo_cellno,\n" +
"     studentinfo.`Telephone` AS studentinfo_Telephone,\n" +
"     studentinfo.`Gender` AS studentinfo_Gender,\n" +
"     studentinfo.`DateofBirth` AS studentinfo_DateofBirth,\n" +
"     studentinfo.`BloodGroup` AS studentinfo_BloodGroup,\n" +
"     studentinfo.`Address` AS studentinfo_Address,\n" +
"     studentinfo.`City` AS studentinfo_City,\n" +
"     studentinfo.`Province` AS studentinfo_Province,\n" +
"     studentinfo.`DateofAdmission` AS studentinfo_DateofAdmission,\n" +
"     studentinfo.`Class` AS studentinfo_Class,\n" +
"     studentinfo.`Section` AS studentinfo_Section,\n" +
"     studentinfo.`Reference` AS studentinfo_Reference,\n" +
"     studentinfo.`Remarks` AS studentinfo_Remarks,\n" +
"     studentinfo.`Status` AS studentinfo_Status,\n" +
"     studentinfo.`Image` AS studentinfo_Image\n" +
"FROM\n" +
"     `studentinfo` studentinfo\n" +
"order by studentinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void StudentContactList(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\StudentContactList.jrxml");
            String qry ="SELECT\n" +
"     studentinfo.`studentid` AS studentinfo_studentid,\n" +
"     studentinfo.`FirstName` AS studentinfo_FirstName,\n" +
"     studentinfo.`LastName` AS studentinfo_LastName,\n" +
"     studentinfo.`Bform` AS studentinfo_Bform,\n" +
"     studentinfo.`Cnic` AS studentinfo_Cnic,\n" +
"     studentinfo.`Occupation` AS studentinfo_Occupation,\n" +
"     studentinfo.`Email` AS studentinfo_Email,\n" +
"     studentinfo.`cellno` AS studentinfo_cellno,\n" +
"     studentinfo.`Telephone` AS studentinfo_Telephone,\n" +
"     studentinfo.`Gender` AS studentinfo_Gender,\n" +
"     studentinfo.`DateofBirth` AS studentinfo_DateofBirth,\n" +
"     studentinfo.`BloodGroup` AS studentinfo_BloodGroup,\n" +
"     studentinfo.`Address` AS studentinfo_Address,\n" +
"     studentinfo.`City` AS studentinfo_City,\n" +
"     studentinfo.`Province` AS studentinfo_Province,\n" +
"     studentinfo.`DateofAdmission` AS studentinfo_DateofAdmission,\n" +
"     studentinfo.`Class` AS studentinfo_Class,\n" +
"     studentinfo.`Section` AS studentinfo_Section,\n" +
"     studentinfo.`Reference` AS studentinfo_Reference,\n" +
"     studentinfo.`Remarks` AS studentinfo_Remarks,\n" +
"     studentinfo.`Status` AS studentinfo_Status,\n" +
"     studentinfo.`Image` AS studentinfo_Image\n" +
"FROM\n" +
"     `studentinfo` studentinfo\n" +
"order by studentinfo.`FirstName`";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ReceiveableButton(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("MonthlyRecieveableFeeDetailsReports");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Reports");
               stage.show();
    }

    @FXML
    private void MonthlyReceived(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("MonthlyReceivedFeeDetailsReports");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Reports");
               stage.show();
    }

    @FXML
    private void MonthlyClassReceived(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("ClassMonthlyReceived");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Reports");
               stage.show();
    }

    @FXML
    private void MonthlyClassReceivable(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("ClassMonthlyReceivedable");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Reports");
               stage.show();
    }

    @FXML
    private void ClassVoucher(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("ClassMonthlyVoucher");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Reports");
               stage.show();
    }

    @FXML
    private void MonthlyVoucher(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("MonthlyVoucher");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Reports");
               stage.show();
    }
    
}
