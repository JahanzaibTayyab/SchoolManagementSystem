/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.InputStream;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import login.Helper.MysqlConnect;
import login.TableViews.FeeDetailsInfo;
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
public class AdmissionInviduialFeeVoucherDetailsController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;
    @FXML
    private JFXButton Received;
    @FXML
    private JFXButton Refresh;
    @FXML
    private JFXTextField studentid;
    @FXML
    private TextField section;
    @FXML
    private TextField classfee;
    @FXML
    private TextField booksfee;
    @FXML
    private TextField functionfee;
    @FXML
    private TextField partyfee;
    @FXML
    private TextField sportfee;
    @FXML
    private TextField generatorfee;
    @FXML
    private TextField stationaryfee;
    @FXML
    private TextField otherscharges;
    @FXML
    private TextField examsfee;
    @FXML
    private TextField tripfee;
    @FXML
    private TextField lastmonthdues;
    @FXML
    private TextField Totaldues;
    @FXML
    private TextField totalbalance;
    @FXML
    private JFXTextField month;
    @FXML
    private JFXTextField year;
    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    int totalamount=0,Totalpaid=0,Totalbalance=0,lastmonth=0,remaing=0;
    String monthname="",yearname="";
    @FXML
    private TextField classes;
    ObservableList<String> monthlist =FXCollections.observableArrayList("Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec");
    ObservableList<String> yearlist =FXCollections.observableArrayList("2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030");
    ObservableList<String> classlist =FXCollections.observableArrayList();
    ObservableList<String> sectionlist =FXCollections.observableArrayList();
    @FXML
    private TextField admissionfee;
    @FXML
    private TextField security;
    @FXML
    private TextField name;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        Refresh.setDisable(true);
        // TODO
    }     
    @FXML
    private void UpdateButtonHandling(MouseEvent event) {
        int total=(Integer.parseInt(classfee.getText())+Integer.parseInt(generatorfee.getText())+Integer.parseInt(functionfee.getText())+Integer.parseInt(examsfee.getText())
                          +Integer.parseInt(partyfee.getText())+Integer.parseInt(sportfee.getText())+Integer.parseInt(tripfee.getText())
                        +Integer.parseInt(otherscharges.getText())+Integer.parseInt(booksfee.getText())+Integer.parseInt(stationaryfee.getText())+Integer.parseInt(lastmonthdues.getText())+Integer.parseInt(admissionfee.getText())+Integer.parseInt(security.getText()));
                Totaldues.setText(Integer.toString(total));
                totalbalance.setText(Integer.toString(total));
                totalbalance.setEditable(false);
                Totaldues.setEditable(false);
         Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Update Fee");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                String studentinfo ="Update feedetails set Classfee=?,GeneratorFee=?,FunctionFee=?,ExamsFee=?,BooksFee=?"
                        + ",PartyFee=?,SportsFee=?,TripFee=?,StationaryFee=?,Others=?,TotalDues=?,Balance=?,Lastmonthdues=?,AdmissionFee=?,SecurityFee=?"
                        + " where StudentID like('"+studentid.getText()+"%') and Month like ('"+monthname+"%') and Year like ('"+yearname+"%')";
                try{
                pst=conn.prepareStatement(studentinfo);
                pst.setInt(1,Integer.parseInt(classfee.getText()));
                if(generatorfee.getText().isEmpty())
                    pst.setInt(2,0);
                else   
                    pst.setInt(2,Integer.parseInt(generatorfee.getText()));
                if(functionfee.getText().isEmpty())
                    pst.setInt(3,0);
                else
                    pst.setInt(3,Integer.parseInt(functionfee.getText()));
                if(examsfee.getText().isEmpty())
                    pst.setInt(4,0);
                else
                    pst.setInt(4,Integer.parseInt(examsfee.getText()));
                if(partyfee.getText().isEmpty())
                    pst.setInt(6,0);
                else
                    pst.setInt(6,Integer.parseInt(partyfee.getText()));
                if(sportfee.getText().isEmpty())
                    pst.setInt(7,0);
                else
                    pst.setInt(7,Integer.parseInt(sportfee.getText()));
                if(tripfee.getText().isEmpty())
                    pst.setInt(8,0);
                else
                    pst.setInt(8,Integer.parseInt(tripfee.getText()));
                if(otherscharges.getText().isEmpty())
                    pst.setInt(10,0);
                else
                    pst.setInt(10,Integer.parseInt(otherscharges.getText()));
                if(booksfee.getText().isEmpty())
                    pst.setInt(5,0);
                else
                    pst.setInt(5,Integer.parseInt(booksfee.getText()));
                if(stationaryfee.getText().isEmpty())
                    pst.setInt(9,0);
                else
                    pst.setInt(9,Integer.parseInt(stationaryfee.getText()));
                if(lastmonthdues.getText().isEmpty())
                    pst.setInt(13,0);
                else
                    pst.setInt(13,Integer.parseInt(lastmonthdues.getText()));
                 pst.setInt(12,total);
                 pst.setInt(11,total);
                  pst.setInt(14,Integer.parseInt(admissionfee.getText()));
                   pst.setInt(15,Integer.parseInt(security.getText()));
              int row=pst.executeUpdate();
                if(row==1)
                {
                    Refresh.setDisable(false);
                    Alert alert1 =new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information");
                        alert1.setHeaderText("Data Successfully Updated");
                        alert1.showAndWait();
                }
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
            }          
            } 
    }

    @FXML
    private void PrintButtonHandlin(MouseEvent event) {
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
"	where feedetails.`Month`like ('"+monthname+"%')\n" +
"	and  feedetails.`Year`like ('"+yearname+"%')\n"+
"	and  feedetails.`StudentID` like ('"+studentid.getText()+"%')\n";
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
    private void StudentIdAction(ActionEvent event) {
        Calendar now = Calendar.getInstance();
        int cmonth=now.get(Calendar.MONTH);
        int cyear=now.get(Calendar.YEAR);
        yearname=Integer.toString(cyear); 
        switch(cmonth)
        {
            case 0:
                monthname="Jan";
                break;
            case 1:
                monthname="Feb";
                break;
            case 2:
                monthname="Mar";
                break;
            case 3:
                monthname="Apr";
                break;
            case 4:
                monthname="May";
                break;
            case 5:
                monthname="June";
                break;
            case 6:
                monthname="July";
                break;
            case 7:
                monthname="Aug";
                break;
            case 8:
                monthname="Sep";
                break;
            case 9:
                monthname="Oct";
                break;
            case 10:
                monthname="Nov";
                break;
            case 11:
                monthname="Dec";
                break;        
        }
         try (            
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails where StudentID like('"+studentid.getText()+"%') and Month like ('"+monthname+"%') and Year like ('"+yearname+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                    name.setText(rs.getString("StudentName"));
                        classes.setText(rs.getString("ClassName"));
                        section.setText(rs.getString("Section"));
                        name.setEditable(false);
                        classes.setEditable(false);
                        section.setEditable(false);
                    lastmonthdues.setText(rs.getString("Lastmonthdues"));
                    classfee.setText(rs.getString("Classfee"));
                    functionfee.setText(rs.getString("FunctionFee"));
                    partyfee.setText(rs.getString("PartyFee"));
                    sportfee.setText(rs.getString("SportsFee"));
                    generatorfee.setText(rs.getString("GeneratorFee"));
                    stationaryfee.setText(rs.getString("StationaryFee"));
                    otherscharges.setText(rs.getString("Others"));
                    examsfee.setText(rs.getString("ExamsFee"));
                    tripfee.setText(rs.getString("TripFee"));
                    Totaldues.setText(rs.getString("TotalDues"));
                    totalbalance.setText(rs.getString("Balance"));
                    month.setText(rs.getString("Month")); 
                    year.setText(rs.getString("Year")); 
            }
            stmt.close();
            rs.close();
        }
           catch (SQLException e) {
            e.printStackTrace();
        }
         try (            
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from admissionfee";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                    admissionfee.setText(rs.getString("AdmissionFee")); 
                    security.setText(rs.getString("Security")); 
            }
            stmt.close();
            rs.close();
        }
           catch (SQLException e) {
            e.printStackTrace();
        }
         try (            
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from booksprice where Class like ('"+classes.getText()+"%') and section like ('"+section.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                          booksfee.setText(rs.getString("price"));
            }
            stmt.close();
            rs.close();
            booksfee.setEditable(false);
        }
           catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
