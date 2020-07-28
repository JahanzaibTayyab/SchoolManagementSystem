/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXComboBox;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import login.Helper.HelperViewsResources;
import login.Helper.MysqlConnect;
import login.TableViews.StudentInfo;
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
public class CombinedFeeVoucherController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;
    @FXML
    private Button feegenerate;
    @FXML
    private Button printallvoucher;
    @FXML
    private Button printclassvoucher;
    @FXML
    private JFXComboBox<String> month;
    @FXML
    private JFXComboBox<String> year;
    @FXML
    private JFXComboBox<String> classes;

    /**
     * Initializes the controller class.
     */
     Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    ObservableList<String> monthlist =FXCollections.observableArrayList("Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec");
    ObservableList<String> yearlist =FXCollections.observableArrayList("2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030");
    ObservableList<StudentInfo> studentlist =FXCollections.observableArrayList();
    ObservableList<String> classlist =FXCollections.observableArrayList();
    @FXML
    private Button delete;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        printallvoucher.setDisable(true);
        printclassvoucher.setDisable(true);
        conn=MysqlConnect.ConnectDB();
        month.setItems(monthlist);
        year.setItems(yearlist);
        if(LoginController.isLogincheck()==true)
        {
            delete.setDisable(true);
        }
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT ClassName  from classinfo ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                classlist.add(rs.getString("ClassName"));
            }
            classes.setItems(classlist);  
            stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo where Status='Student'";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                studentlist.add(new StudentInfo(rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Class"),rs.getString("Section")));
            }
             stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    @FXML
    private void FeeGenerateButtonHandling(MouseEvent event) {
        
        int classfee=0,generatorfee=0,functionfee=0,examsfee=0,bookfee=0,partyfee=0,sportsfee=0,tripfee=0,othersfee=0,stationaryfee=0,totaldues=0,lastmonthdues=0;
        String monthname="",yearname="";
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Generate Fee");
                        Optional<ButtonType> action=alert.showAndWait();
         if(action.get()==ButtonType.OK)
         {
        for(int i=0;i<studentlist.size();i++)
        {
        try (            
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from classfee where ClassName like('"+studentlist.get(i).getStudentclass()+"%') and Section like ('"+studentlist.get(i).getSection()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                    classfee=Integer.parseInt(rs.getString("ClassFee"));
                    generatorfee=Integer.parseInt(rs.getString("GeneratorFee"));
                    functionfee=Integer.parseInt(rs.getString("FunctionFee"));
                    examsfee=Integer.parseInt(rs.getString("ExamsFee"));
                    bookfee=Integer.parseInt(rs.getString("BooksFee"));
                    partyfee=Integer.parseInt(rs.getString("PartyFee"));
                    sportsfee=Integer.parseInt(rs.getString("SportsFee"));
                    tripfee=Integer.parseInt(rs.getString("TripFee"));
                   othersfee=Integer.parseInt(rs.getString("OtherCharges"));
                   stationaryfee=Integer.parseInt(rs.getString("StationaryFee"));
            }
            stmt.close();
            rs.close();
        }
           catch (SQLException e) {
            e.printStackTrace();
        }
            String cmonth=month.getValue();
            int cyear=Integer.parseInt(year.getValue());
        switch(cmonth)
        {
            case "Jan":
                monthname="Dec";
                cyear=cyear-1;
                yearname=Integer.toString(cyear); 
                break;
            case "Feb":
                monthname="Jan";
                yearname=Integer.toString(cyear);
                break;
            case "Mar":
                monthname="Feb";
                yearname=Integer.toString(cyear);
                break;
            case "Apr":
                monthname="Mar";
                yearname=Integer.toString(cyear);
                break;
            case "May":
                monthname="Apr";
                yearname=Integer.toString(cyear);
                break;
            case "June":
                monthname="May";
                yearname=Integer.toString(cyear);
                break;
            case "July":
                monthname="June";
                yearname=Integer.toString(cyear);
                break;
            case "Aug":
                monthname="July";
                yearname=Integer.toString(cyear);
                break;
            case "Sep":
                monthname="Aug";
                yearname=Integer.toString(cyear);
                break;
            case "Oct":
                monthname="Sep";
                yearname=Integer.toString(cyear);
                break;
            case "Nov":
                monthname="Oct";
                yearname=Integer.toString(cyear);
                break;
            case "Dec":
                monthname="Nov";
                yearname=Integer.toString(cyear);
                break;        
        }
         try (            
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Balance from feedetails where StudentID like('"+studentlist.get(i).getStudentid()+"%') and Month like ('"+monthname+"%') and Year like ('"+yearname+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                    lastmonthdues=Integer.parseInt(rs.getString("Balance"));
            }
            stmt.close();
            rs.close();
        }
           catch (SQLException e) {
            e.printStackTrace();
        }
         totaldues=classfee+generatorfee+functionfee+examsfee+bookfee+partyfee+sportsfee+tripfee+othersfee+stationaryfee+lastmonthdues;
        Random rn=new Random();
        int ad=rn.nextInt(999999999);
        String feeinfo ="Insert into feedetails (feecode,StudentID,StudentName,Month,Year,Classfee,GeneratorFee,FunctionFee,ExamsFee"
                + ",BooksFee,PartyFee,SportsFee,TripFee,StationaryFee,Others,TotalDues,Balance,ClassName,Section,Paid,Lastmonthdues) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                try{
                pst=conn.prepareStatement(feeinfo);
                pst.setInt(1,ad);
                pst.setString(2,studentlist.get(i).getStudentid());
                pst.setString(3,studentlist.get(i).getName());
                pst.setString(4,month.getValue());
                pst.setString(5,year.getValue());
                pst.setInt(6,classfee);
                pst.setInt(7,generatorfee);
                pst.setInt(8,functionfee);
                pst.setInt(9,examsfee);
                pst.setInt(10,bookfee);
                pst.setInt(11,partyfee);
                pst.setInt(12,sportsfee);
                pst.setInt(13,tripfee);
                pst.setInt(14,stationaryfee);
                pst.setInt(15,othersfee);
                pst.setInt(16,totaldues);
                pst.setInt(17,totaldues);
                pst.setString(18,studentlist.get(i).getStudentclass());
                 pst.setString(19,studentlist.get(i).getSection());
                 pst.setInt(20,0);
                 pst.setInt(21,lastmonthdues);
             int row=pst.executeUpdate();
                 pst.close();
            }
            catch(Exception ex)
            {
                Alert alert1 =new Alert(Alert.AlertType.ERROR);
                        alert1.setHeaderText("Error");
                StringWriter sw=new StringWriter();
                PrintWriter pw=new PrintWriter(sw);
                ex.printStackTrace(pw);
                TextArea area=new TextArea(sw.toString());
                alert1.getDialogPane().setExpandableContent(area);
                 alert1.showAndWait();
                 return;
            }
        }
                printallvoucher.setDisable(false);
                 printclassvoucher.setDisable(false);
                     Alert alert1 =new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information");
                        alert1.setHeaderText("Fee Successfully Generated");
                        alert1.showAndWait();
                        
         }
    }

    @FXML
    private void PrintwholeschoolvoucherButtonHandling(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\FeeVoucher.jrxml");
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
"	where feedetails.`Month`like('"+month.getValue()+"%')\n" +
"	and  feedetails.`Year`like('"+year.getValue()+"%')\n";
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
    private void printclasswiseButtonHandling(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\FeeVoucher.jrxml");
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
"	and  feedetails.`Year` like('"+year.getValue()+"%')\n"+
"	and feedetails.`ClassName` like('"+classes.getValue()+"%')\n" ;
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
    private void DeleteButtonHandling(MouseEvent event) {
        Alert alert =new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warring");
                        alert.setHeaderText("Do you want Delete Fee");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                Parent root =new HelperViewsResources().loadFXML("MonthlyDeleteFeeDetails");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Delete Fee");
               stage.show();
            }
            }
    }
