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
import java.util.Optional;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class StudentFeeVoucherDetailsController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;
    @FXML
    private JFXButton Received;
    @FXML
    private JFXButton Refresh;
    @FXML
    private JFXTextField studentid;
    @FXML
    private TextField studentname;
    @FXML
    private TextField fathername;
    @FXML
    private TextField studentclass;
    @FXML
    private TextField section;
    @FXML
    private TextField feecode;
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
    private TableView<FeeDetailsInfo> studenttable;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_month;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_year;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_fee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_gfee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_ffee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_efee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_exams;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_sfee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_trip;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_bfee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_Sfee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_total;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_paid;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_balance;
     @FXML
    private TableColumn<FeeDetailsInfo,String> col_others;
    @FXML
    private TableColumn<FeeDetailsInfo,String> Lmonthdues;
    @FXML
    private TextField lastmonthdues;
    @FXML
    private TextField Totaldues;
    @FXML
    private TextField totalpaid;
    @FXML
    private TextField totalbalance;
    @FXML
    private JFXTextField month;
    @FXML
    private JFXTextField year;
     @FXML
    private TextField paidamount;

    /**
     * Initializes the controller class.
     */
    ObservableList<FeeDetailsInfo> feelist =FXCollections.observableArrayList();
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    @FXML
    private TextField totaldues;
    @FXML
    private TextField balance;
    int totalamount=0,Totalpaid=0,Totalbalance=0,lastmonth=0,remaing=0;
    String monthname="",yearname="";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        col_month.setCellValueFactory(new PropertyValueFactory<>("month"));
        col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_fee.setCellValueFactory(new PropertyValueFactory<>("classfee"));
        col_gfee.setCellValueFactory(new PropertyValueFactory<>("generator"));
        col_ffee.setCellValueFactory(new PropertyValueFactory<>("functionfee"));
        col_efee.setCellValueFactory(new PropertyValueFactory<>("examsfee"));
        col_exams.setCellValueFactory(new PropertyValueFactory<>("partyfee"));
        col_sfee.setCellValueFactory(new PropertyValueFactory<>("sportsfee"));
        col_trip.setCellValueFactory(new PropertyValueFactory<>("tripfee"));
        col_bfee.setCellValueFactory(new PropertyValueFactory<>("booksfee"));
        col_Sfee.setCellValueFactory(new PropertyValueFactory<>("stationaryfee"));
        col_others.setCellValueFactory(new PropertyValueFactory<>("others"));
        col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        col_paid.setCellValueFactory(new PropertyValueFactory<>("paid"));
        col_balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        Lmonthdues.setCellValueFactory(new PropertyValueFactory<>("Lastmonth"));
        studenttable.setItems(feelist);
        Received.setDisable(true);
        // TODO
    }    

    @FXML
    private void ReceivedButtonHandling(MouseEvent event) {
         Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Update Fee");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                String studentinfo ="Update feedetails set Paid=?,Balance=? where StudentID like('"+studentid.getText()+"%') and Month like ('"+monthname+"%') and Year like ('"+yearname+"%')";
                try{
                pst=conn.prepareStatement(studentinfo);
                pst.setInt(1,Integer.parseInt(paidamount.getText()));
                pst.setInt(2,remaing);
          pst.executeUpdate();
             int row=pst.executeUpdate();
                if(row==1)
                { 
                     Alert alert1 =new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information");
                        alert1.setHeaderText("Data Successfully Saved");
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
    private void RefresfButtonHandling(MouseEvent event) {
        feelist.clear();
        try (  
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails where StudentID like('"+studentid.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                feelist.add(new FeeDetailsInfo(rs.getString("StudentID"),rs.getString("StudentName"),rs.getString("Month")
                        ,rs.getString("Year"),rs.getString("ClassName"),rs.getString("Section"),rs.getString("Classfee"),rs.getString("GeneratorFee"),rs.getString("FunctionFee")
                        ,rs.getString("ExamsFee"),rs.getString("PartyFee"),rs.getString("SportsFee"),rs.getString("TripFee")
                        ,rs.getString("BooksFee"),rs.getString("StationaryFee"),rs.getString("Others"),rs.getString("TotalDues")
                        ,rs.getString("Paid"),rs.getString("Balance"),rs.getString("feecode"),rs.getString("Lastmonthdues")));
         
            }                    
             studenttable.setItems(feelist);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void PaidAmountAction(ActionEvent event) {
            int paid=Integer.parseInt(paidamount.getText());
        if(paid>totalamount)
                {
                    Alert alert1 =new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Warning");
                        alert1.setHeaderText("Paid amount cannot be graterthen total amount");
                        alert1.showAndWait();
                }
        else
        {
                 Received.setDisable(false);
         remaing=totalamount-Integer.parseInt(paidamount.getText());
            balance.setEditable(true);
           totalbalance.setEditable(true);
            totalpaid.setEditable(true);
            balance.setText(Integer.toString(remaing));
            totalbalance.setText(Integer.toString(remaing));
            totalpaid.setText(paidamount.getText());
            balance.setEditable(false);
           totalbalance.setEditable(false);
            totalpaid.setEditable(false);
        }
    }
     @FXML
    private void StudentIDAction(ActionEvent event) {
        feelist.clear();
        int cmonth,cyear; 
        try (
               
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails where StudentID like('"+studentid.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                feelist.add(new FeeDetailsInfo(rs.getString("StudentID"),rs.getString("StudentName"),rs.getString("Month")
                        ,rs.getString("Year"),rs.getString("ClassName"),rs.getString("Section"),rs.getString("Classfee"),rs.getString("GeneratorFee"),rs.getString("FunctionFee")
                        ,rs.getString("ExamsFee"),rs.getString("PartyFee"),rs.getString("SportsFee"),rs.getString("TripFee")
                        ,rs.getString("BooksFee"),rs.getString("StationaryFee"),rs.getString("Others"),rs.getString("TotalDues")
                        ,rs.getString("Paid"),rs.getString("Balance"),rs.getString("feecode"),rs.getString("Lastmonthdues")));
                         studentname.setText(rs.getString("StudentName"));
                        studentclass.setText(rs.getString("ClassName"));
                        section.setText(rs.getString("Section"));
                        studentname.setEditable(false);
                        studentclass.setEditable(false);
                        section.setEditable(false);
            }                    
             studenttable.setItems(feelist);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT LastName  from studentinfo where studentid like('"+studentid.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                    fathername.setText(rs.getString("LastName"));
            }
            stmt.close();
            rs.close();
            fathername.setEditable(false);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }  
    Calendar now = Calendar.getInstance();
        cmonth=now.get(Calendar.MONTH);
        cyear=now.get(Calendar.YEAR);
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
                    lastmonthdues.setText(rs.getString("Lastmonthdues"));
                    feecode.setText(rs.getString("feecode"));
                    classfee.setText(rs.getString("Classfee"));
                    booksfee.setText(rs.getString("BooksFee"));
                    functionfee.setText(rs.getString("FunctionFee"));
                    partyfee.setText(rs.getString("PartyFee"));
                    sportfee.setText(rs.getString("SportsFee"));
                    generatorfee.setText(rs.getString("GeneratorFee"));
                    stationaryfee.setText(rs.getString("StationaryFee"));
                    otherscharges.setText(rs.getString("Others"));
                    examsfee.setText(rs.getString("ExamsFee"));
                    tripfee.setText(rs.getString("TripFee"));
                    Totaldues.setText(rs.getString("TotalDues"));
                    totalpaid.setText(rs.getString("Paid"));
                    totalbalance.setText(rs.getString("Balance"));
                    totaldues.setText(rs.getString("TotalDues"));
                    balance.setText(rs.getString("Balance")); 
                    month.setText(rs.getString("Month")); 
                    year.setText(rs.getString("Year")); 
                    totalamount=Integer.parseInt(rs.getString("TotalDues"));
                    Totalbalance=Integer.parseInt(rs.getString("Balance"));
            }
            stmt.close();
            rs.close();
            lastmonthdues.setEditable(false);
                    feecode.setEditable(false);
                    classfee.setEditable(false);
                    booksfee.setEditable(false);
                    functionfee.setEditable(false);
                    partyfee.setEditable(false);
                    sportfee.setEditable(false);
                    generatorfee.setEditable(false);
                    stationaryfee.setEditable(false);
                    otherscharges.setEditable(false);
                    examsfee.setEditable(false);
                    tripfee.setEditable(false);
                    Totaldues.setEditable(false);
                    totalpaid.setEditable(false);
                    totalbalance.setEditable(false);
                    totaldues.setEditable(false);
                    balance.setEditable(false);
                    month.setEditable(false);
                    year.setEditable(false);
        }
           catch (SQLException e) {
            e.printStackTrace();
        }   
   
    }  
}
