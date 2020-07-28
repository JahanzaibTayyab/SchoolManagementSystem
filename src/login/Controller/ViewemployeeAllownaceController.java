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
import login.TableViews.EmployeeSalaryInfo;
import login.TableViews.StudentInfo;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class ViewemployeeAllownaceController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;
    @FXML
    private TableView<EmployeeAllownacesInfo> employeeinformation;
    @FXML
    private TableColumn<EmployeeAllownacesInfo,String> col_id;
    @FXML
    private TableColumn<EmployeeAllownacesInfo,String> col_name;
    @FXML
    private TableColumn<EmployeeAllownacesInfo,String> col_post;
     @FXML
    private TableColumn<EmployeeAllownacesInfo,String> col_sr;
      @FXML
    private TableColumn<EmployeeAllownacesInfo,String> col_month;
    @FXML
    private TableColumn<EmployeeAllownacesInfo,String> col_year;
    @FXML
    private TableColumn<EmployeeAllownacesInfo,String> col_overtime;
    @FXML
    private TableColumn<EmployeeAllownacesInfo,String> col_medical;
    @FXML
    private TableColumn<EmployeeAllownacesInfo,String> col_bounce;
    @FXML
    private TableColumn<EmployeeAllownacesInfo,String> col_HAllownace;
    @FXML
    private TableColumn<EmployeeAllownacesInfo,String> col_TAllownace;
    @FXML
    private TableColumn<EmployeeAllownacesInfo,String> col_Others;
    @FXML
    private TableColumn<EmployeeAllownacesInfo,String> col_total;
    @FXML
    private TextField id;
    

    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    ObservableList<EmployeeAllownacesInfo> employeelist =FXCollections.observableArrayList();
    ObservableList<String> list =FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         conn=MysqlConnect.ConnectDB();
         col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_post.setCellValueFactory(new PropertyValueFactory<>("post"));
        col_month.setCellValueFactory(new PropertyValueFactory<>("month"));
        col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_overtime.setCellValueFactory(new PropertyValueFactory<>("Overtime"));
        col_medical.setCellValueFactory(new PropertyValueFactory<>("medcial"));
        col_bounce.setCellValueFactory(new PropertyValueFactory<>("bounce"));
         col_HAllownace.setCellValueFactory(new PropertyValueFactory<>("homeAllownace"));
         col_TAllownace.setCellValueFactory(new PropertyValueFactory<>("TransportAllownce"));
        col_Others.setCellValueFactory(new PropertyValueFactory<>("others"));
         col_total.setCellValueFactory(new PropertyValueFactory<>("Total"));
        col_sr.setCellValueFactory(new PropertyValueFactory<>("sr"));
        // TODO
        tablefill();
    }    

    @FXML
    private void ActionOnID(ActionEvent event) {
        employeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeallowance where employeeid like('"+id.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                employeelist.add(new EmployeeAllownacesInfo(rs.getString("employeeid"),rs.getString("EmployeeName"),rs.getString("post"),rs.getString("OverTime"),rs.getString("Medical"),rs.getString("Bounce")
                ,rs.getString("HomeAllowance"),rs.getString("TransportAllowance"),rs.getString("Others"),rs.getString("Month"),rs.getString("Year"),rs.getString("TotalAllownace"),i));
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
            String SQL = "SELECT * from employeeallowance";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                employeelist.add(new EmployeeAllownacesInfo(rs.getString("employeeid"),rs.getString("EmployeeName"),rs.getString("post"),rs.getString("OverTime"),rs.getString("Medical"),rs.getString("Bounce")
                ,rs.getString("HomeAllowance"),rs.getString("TransportAllowance"),rs.getString("Others"),rs.getString("Month"),rs.getString("Year"),rs.getString("TotalAllownace"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(employeelist); 
    }
         
}
