/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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
public class PayrollreportsController implements Initializable {

    @FXML
    private Button ssave;
    @FXML
    private Button sclear;
    @FXML
    private Button generateslip;
    @FXML
    private Button generateslip1;

    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        // TODO
    }    

    @FXML
    private void EmployeeSalaryReports(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\EmployeeSalaryReport.jrxml");
            String qry ="SELECT\n" +
"     employeesalary.`employeeid` AS employeesalary_employeeid,\n" +
"     employeesalary.`EmployeeName` AS employeesalary_EmployeeName,\n" +
"     employeesalary.`WorkingHours` AS employeesalary_WorkingHours,\n" +
"     employeesalary.`salary` AS employeesalary_salary,\n" +
"     employeesalary.`Date` AS employeesalary_Date,\n" +
"     employeesalary.`post` AS employeesalary_post,\n" +
"     employeesalary.`Dateofjoing` AS employeesalary_Dateofjoing\n" +
"FROM\n" +
"     `employeesalary` employeesalary\n" +
"order by employeesalary.`EmployeeName`";
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
    private void EmployeeAllownceReportButton(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\EmployeeAllowancereports.jrxml");
            String qry ="SELECT\n" +
"     employeeallowance.`employeeid` AS employeeallowance_employeeid,\n" +
"     employeeallowance.`EmployeeName` AS employeeallowance_EmployeeName,\n" +
"     employeeallowance.`Post` AS employeeallowance_Post,\n" +
"     employeeallowance.`OverTime` AS employeeallowance_OverTime,\n" +
"     employeeallowance.`Medical` AS employeeallowance_Medical,\n" +
"     employeeallowance.`Bounce` AS employeeallowance_Bounce,\n" +
"     employeeallowance.`HomeAllowance` AS employeeallowance_HomeAllowance,\n" +
"     employeeallowance.`TransportAllowance` AS employeeallowance_TransportAllowance,\n" +
"     employeeallowance.`Others` AS employeeallowance_Others,\n" +
"     employeeallowance.`TotalAllownace` AS employeeallowance_TotalAllownace,\n" +
"     employeeallowance.`Month` AS employeeallowance_Month,\n" +
"     employeeallowance.`Year` AS employeeallowance_Year,\n" +
"     employeeallowance.`TotalSalary` AS employeeallowance_TotalSalary\n" +
"FROM\n" +
"     `employeeallowance` employeeallowance\n" +
"order by employeeallowance.`Month`";
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
    private void EmployeeDeductionButton(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\EmployeeDeducationReports.jrxml");
            String qry ="SELECT\n" +
"     employededucation.`employeeid` AS employededucation_employeeid,\n" +
"     employededucation.`EmployeeName` AS employededucation_EmployeeName,\n" +
"     employededucation.`Post` AS employededucation_Post,\n" +
"     employededucation.`DeductionAmount` AS employededucation_DeductionAmount,\n" +
"     employededucation.`SalaryAfterDeducation` AS employededucation_SalaryAfterDeducation,\n" +
"     employededucation.`Reason` AS employededucation_Reason,\n" +
"     employededucation.`Month` AS employededucation_Month,\n" +
"     employededucation.`year` AS employededucation_year\n" +
"FROM\n" +
"     `employededucation` employededucation\n" +
"order by employededucation.`Month`";
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
    private void EmpoyeePaymentReports(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\EmployeePaymentsReports.jrxml");
            String qry ="SELECT\n" +
"     employeepayment.`employeeid` AS employeepayment_employeeid,\n" +
"     employeepayment.`EmployeeName` AS employeepayment_EmployeeName,\n" +
"     employeepayment.`Post` AS employeepayment_Post,\n" +
"     employeepayment.`Salary` AS employeepayment_Salary,\n" +
"     employeepayment.`PaidSalry` AS employeepayment_PaidSalry,\n" +
"     employeepayment.`Status` AS employeepayment_Status,\n" +
"     employeepayment.`month` AS employeepayment_month,\n" +
"     employeepayment.`year` AS employeepayment_year,\n" +
"     employeepayment.`balance` AS employeepayment_balance,\n" +
"     employeepayment.`TotalSalary` AS employeepayment_TotalSalary\n" +
"FROM\n" +
"     `employeepayment` employeepayment\n" +
"order by employeepayment.`month`";
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
    
}
