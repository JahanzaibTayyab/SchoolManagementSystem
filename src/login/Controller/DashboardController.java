/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import login.Helper.HelperViewsResources;
import login.Helper.MysqlConnect;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;
    @FXML
    private JFXTextField totalstudent;
    @FXML
    private JFXTextField malestudent;
    @FXML
    private JFXTextField femalestudent;
    @FXML
    private JFXTextField passoutstudent;
    @FXML
    private JFXTextField leavestudent;
    @FXML
    private JFXTextField currentstudent;
    @FXML
    private PieChart studentpiechart;
    @FXML
    private JFXTextField Totalstaff;
    @FXML
    private JFXTextField malestaff;
    @FXML
    private JFXTextField femalestaff;
    @FXML
    private JFXTextField leavestaff;
    @FXML
    private JFXTextField currentstaff;
    @FXML
    private PieChart staffpiechart;
    @FXML
    private JFXTextField TotalUser;
    @FXML
    private PieChart totaluser;
    @FXML
    private JFXTextField totalemployee;
    @FXML
    private JFXTextField maleemployee;
    @FXML
    private JFXTextField femaleemployee;
    @FXML
    private JFXTextField leaveemployee;
    @FXML
    private JFXTextField currentemployee;
    @FXML
    private PieChart employeepiechart;

    /**
     * Initializes the controller class.
     */
    ObservableList<PieChart.Data> studentchart;
    ObservableList<PieChart.Data> staffchart;
    ObservableList<PieChart.Data> employeechart;
    ObservableList<PieChart.Data> userchart;
    Connection conn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        piechartData();StaffpiechartData();EmployeepiechartData();userpiechartData();     
        // TODO
    }    
        public void piechartData()
    {
        int malepie=0,femalepie=0,totalstudentpie=0,currentstudentpie=0,passout=0,leave=0;
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(Gender) As myCount from studentinfo where Gender='Male'";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 malepie=rs.getInt("myCount");
                 malestudent.setText(rs.getString("myCount"));
                 malestudent.setEditable(false);
            }    
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(Gender) As myCount from studentinfo where Gender='Female'";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 femalepie=rs.getInt("myCount");
                 femalestudent.setText(rs.getString("myCount"));
                 femalestudent.setEditable(false);
            }   
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(studentid) As myCount from studentinfo ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 totalstudentpie=rs.getInt("myCount");
                 totalstudent.setText(rs.getString("myCount"));
                 totalstudent.setEditable(false);
            }   
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(studentid) As myCount from studentinfo where Status='Student'  ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 currentstudentpie=rs.getInt("myCount");
                 currentstudent.setText(rs.getString("myCount"));
                 currentstudent.setEditable(false);
            }   
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(studentid) As myCount from studentinfo where Status='Passout'  ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 passout=rs.getInt("myCount");
                 passoutstudent.setText(rs.getString("myCount"));
                 passoutstudent.setEditable(false);
            }   
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(studentid) As myCount from studentinfo where Status='Clear'  ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 leave=rs.getInt("myCount");
                 leavestudent.setText(rs.getString("myCount"));
                 leavestudent.setEditable(false);
            }   
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
         studentchart = FXCollections.observableArrayList(
                         new PieChart.Data("Student",currentstudentpie),
                        new PieChart.Data("Male",malepie),
                        new PieChart.Data("Female",femalepie) ,
                        new PieChart.Data("Passout",passout),
                         new PieChart.Data("Leave",leave)
        );
        studentpiechart.setData(studentchart);
    }
        public void StaffpiechartData()
    {
        int malepie=0,femalepie=0,staff=0,totalstaff=0,leave=0;
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(Gender) As myCount from employeeinfo where Gender='Male' and DataStatus='Staff' ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 malepie=rs.getInt("myCount");
                 malestaff.setText(rs.getString("myCount"));
                 malestaff.setEditable(false);
            }    
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(Gender) As myCount from employeeinfo where Gender='Female' and DataStatus='Staff' ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 femalepie=rs.getInt("myCount");
                 femalestaff.setText(rs.getString("myCount"));
                 femalestaff.setEditable(false);
            }   
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(employeeid) As myCount from employeeinfo where DataStatus='Staff' ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 totalstaff=rs.getInt("myCount");
                 Totalstaff.setText(rs.getString("myCount"));
                 Totalstaff.setEditable(false);
            }   
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(employeeid) As myCount from employeeinfo where EmployeeStatus='Active'  and DataStatus='Staff' ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 staff=rs.getInt("myCount");
                 currentstaff.setText(rs.getString("myCount"));
                 currentstaff.setEditable(false);
            }   
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(employeeid) As myCount from employeeinfo where EmployeeStatus='Unactive'  and DataStatus='Staff' ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 leave=rs.getInt("myCount");
                leavestaff.setText(rs.getString("myCount"));
                 leavestaff.setEditable(false);
            }   
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
         staffchart = FXCollections.observableArrayList(
                        new PieChart.Data("Male",malepie),
                        new PieChart.Data("Female",femalepie) ,
                         new PieChart.Data("Leave",leave),
                         new PieChart.Data("Staff",staff)
        );
                 staffpiechart.setData(staffchart);
    }
        public void EmployeepiechartData()
    {
        int malepie=0,femalepie=0,staff=0,totalstaff=0,leave=0;
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(Gender) As myCount from employeeinfo where Gender='Male' and DataStatus='Employee' ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 malepie=rs.getInt("myCount");
                 maleemployee.setText(rs.getString("myCount"));
                 maleemployee.setEditable(false);
            }    
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(Gender) As myCount from employeeinfo where Gender='Female' and DataStatus='Employee' ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 femalepie=rs.getInt("myCount");
                 femaleemployee.setText(rs.getString("myCount"));
                 femaleemployee.setEditable(false);
            }   
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(employeeid) As myCount from employeeinfo where DataStatus='Employee' ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 totalstaff=rs.getInt("myCount");
                 totalemployee.setText(rs.getString("myCount"));
                 totalemployee.setEditable(false);
            }   
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(employeeid) As myCount from employeeinfo where EmployeeStatus='Active'  and DataStatus='Employee' ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 staff=rs.getInt("myCount");
                 currentemployee.setText(rs.getString("myCount"));
                 currentemployee.setEditable(false);
            }   
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(employeeid) As myCount from employeeinfo where EmployeeStatus='Unactive'  and DataStatus='Employee' ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 leave=rs.getInt("myCount");
                leaveemployee.setText(rs.getString("myCount"));
                 leaveemployee.setEditable(false);
            }   
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
         employeechart = FXCollections.observableArrayList(
                        new PieChart.Data("Male",malepie),
                        new PieChart.Data("Female",femalepie) ,
                         new PieChart.Data("Leave",leave),
                         new PieChart.Data("Employee",staff)
        );
                 employeepiechart.setData(employeechart);
    }
    public void userpiechartData()
    {
        int user=0;
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(UserID) As myCount from userinfo ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 user=rs.getInt("myCount");
                 TotalUser.setText(rs.getString("myCount"));
                TotalUser.setEditable(false);
            }    
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
         userchart = FXCollections.observableArrayList(
                        new PieChart.Data("Users",user)
        );
                totaluser.setData(userchart);
    }  
}
