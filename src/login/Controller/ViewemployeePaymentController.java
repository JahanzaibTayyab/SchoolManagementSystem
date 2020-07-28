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
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import login.Helper.MysqlConnect;
import login.TableViews.EmployeeAllownacesInfo;
import login.TableViews.EmployeePaymentInfo;
import login.TableViews.EmployeeSalaryInfo;
import login.TableViews.StudentInfo;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class ViewemployeePaymentController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;
    @FXML
    private TableView<EmployeePaymentInfo> employeeinformation;
    @FXML
    private TableColumn<EmployeePaymentInfo,String> col_id;
    @FXML
    private TableColumn<EmployeePaymentInfo,String> col_name;
    @FXML
    private TableColumn<EmployeePaymentInfo,String> col_post;
     @FXML
    private TableColumn<EmployeePaymentInfo,String> col_sr;
      @FXML
    private TableColumn<EmployeePaymentInfo,String> col_month;
    @FXML
    private TableColumn<EmployeePaymentInfo,String> col_year;
    @FXML
    private TableColumn<EmployeePaymentInfo,String> col_total;
    @FXML
    private TableColumn<EmployeePaymentInfo,String> col_basicsalary;
    @FXML
    private TableColumn<EmployeePaymentInfo,String> col_paid;
    @FXML
    private TableColumn<EmployeePaymentInfo,String> col_balance;
    @FXML
    private TableColumn<EmployeePaymentInfo,String> col_status;
    @FXML
    private TextField id;
    

    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    ObservableList<EmployeePaymentInfo> employeelist =FXCollections.observableArrayList();
    ObservableList<String> list =FXCollections.observableArrayList();
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         conn=MysqlConnect.ConnectDB();
         col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_post.setCellValueFactory(new PropertyValueFactory<>("post"));
        col_month.setCellValueFactory(new PropertyValueFactory<>("month"));
        col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_basicsalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        col_paid.setCellValueFactory(new PropertyValueFactory<>("paid"));
        col_balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
         col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
         col_total.setCellValueFactory(new PropertyValueFactory<>("totalsalary"));
        col_sr.setCellValueFactory(new PropertyValueFactory<>("sr"));
        // TODO
        tablefill();
    }    

    @FXML
    private void ActionOnID(ActionEvent event) {
        employeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeepayment where employeeid like('"+id.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                employeelist.add(new EmployeePaymentInfo(rs.getString("employeeid"),rs.getString("EmployeeName"),rs.getString("post"),rs.getString("Salary"),rs.getString("TotalSalary"),rs.getString("PaidSalry")
                ,rs.getString("balance"),rs.getString("Status"),rs.getString("month"),rs.getString("year"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(employeelist); 
    }
    private void tablefill()
    {
        
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeepayment";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                employeelist.add(new EmployeePaymentInfo(rs.getString("employeeid"),rs.getString("EmployeeName"),rs.getString("post"),rs.getString("Salary"),rs.getString("TotalSalary"),rs.getString("PaidSalry")
                ,rs.getString("balance"),rs.getString("Status"),rs.getString("month"),rs.getString("year"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(employeelist); 
    }
         
}
