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
import login.TableViews.EmployeeSalaryInfo;
import login.TableViews.StudentInfo;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class ViewpayrollemployeeinfoController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;
    @FXML
    private TableView<EmployeeSalaryInfo> employeeinformation;
    @FXML
    private TableColumn<EmployeeSalaryInfo,String> col_id;
    @FXML
    private TableColumn<EmployeeSalaryInfo,String> col_name;
    @FXML
    private TableColumn<EmployeeSalaryInfo,String> col_post;
    @FXML
    private TableColumn<EmployeeSalaryInfo,String> col_jod;
    @FXML
    private TableColumn<EmployeeSalaryInfo,String> col_hours;
    @FXML
    private TableColumn<EmployeeSalaryInfo,String> col_salary;
    @FXML
    private TableColumn<EmployeeSalaryInfo,String> col_service;
     @FXML
    private TableColumn<EmployeeSalaryInfo,String> col_sr;
    @FXML
    private TextField id;
    

    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    ObservableList<EmployeeSalaryInfo> employeelist =FXCollections.observableArrayList();
    ObservableList<String> list =FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         conn=MysqlConnect.ConnectDB();
         col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_post.setCellValueFactory(new PropertyValueFactory<>("post"));
        col_jod.setCellValueFactory(new PropertyValueFactory<>("joiningdate"));
        col_hours.setCellValueFactory(new PropertyValueFactory<>("hour"));
       col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        col_service.setCellValueFactory(new PropertyValueFactory<>("service"));
        col_sr.setCellValueFactory(new PropertyValueFactory<>("sr"));
        // TODO
        tablefill();
    }    

    @FXML
    private void ActionOnID(ActionEvent event) {
        employeelist.clear();
        String dateofj="";
        try (        
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeesalary where employeeid like('"+id.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                dateofj=rs.getString("Dateofjoing");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
            LocalDate today= LocalDate.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate birthday= LocalDate.parse(dateofj, formatter);
        Period p =Period.between(birthday, today);
        String month=Integer.toString(p.getMonths());
        String year=Integer.toString(p.getYears());
        String days=Integer.toString(p.getDays());
        String service="";
        service+=year;
        service+=" Years ";
        service+=month;
        service+=" Months ";
        service+=days;
        service+=" Days ";
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeesalary  where employeeid like('"+id.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                employeelist.add(new EmployeeSalaryInfo(rs.getString("employeeid"),rs.getString("EmployeeName"),rs.getString("post"),rs.getString("Dateofjoing"),rs.getString("WorkingHours"),rs.getString("salary")
                ,service,i));
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
        String dateofj="";
        int i=1;
        try (        
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeesalary ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                list.add(rs.getString("employeeid"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        for(int j=0;j<list.size();j++)
        {
            try (        
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeesalary where employeeid like('"+list.get(j)+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                dateofj=rs.getString("Dateofjoing");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
            LocalDate today= LocalDate.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate birthday= LocalDate.parse(dateofj, formatter);
        Period p =Period.between(birthday, today);
        String month=Integer.toString(p.getMonths());
        String year=Integer.toString(p.getYears());
        String days=Integer.toString(p.getDays());
        String service="";
        service+=year;
        service+=" Years ";
        service+=month;
        service+=" Months ";
        service+=days;
        service+=" Days ";
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeesalary  where employeeid like('"+list.get(j)+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                employeelist.add(new EmployeeSalaryInfo(rs.getString("employeeid"),rs.getString("EmployeeName"),rs.getString("post"),rs.getString("Dateofjoing"),rs.getString("WorkingHours"),rs.getString("salary")
                ,service,i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
        employeeinformation.setItems(employeelist);
    }
    
}
