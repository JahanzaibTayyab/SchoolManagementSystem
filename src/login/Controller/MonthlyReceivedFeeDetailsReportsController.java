/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import login.Controller.UpdateStudentInformationController;
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
public class MonthlyReceivedFeeDetailsReportsController implements Initializable {

    @FXML
    private JFXComboBox<String> month;
    @FXML
    private JFXComboBox<String> year;

    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    ObservableList<String> monthlist =FXCollections.observableArrayList("Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec");
    ObservableList<String> yearlist =FXCollections.observableArrayList("2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        month.setItems(monthlist);
        year.setItems(yearlist);
        // TODO
    }    

    @FXML
    private void PrintAction(MouseEvent event) {
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
"	where feedetails.`Month` like('"+month.getValue()+"%')\n" +
"	and feedetails.`Year` like('"+year.getValue()+"%')\n" +
"	and feedetails.`Balance`=0\n" +
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
    
}
