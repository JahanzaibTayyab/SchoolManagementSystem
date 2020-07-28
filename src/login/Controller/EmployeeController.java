/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import login.Helper.HelperViewsResources;
import login.Helper.MysqlConnect;
import login.TableViews.EmployeeInfo;
import login.TableViews.TeacherInfo;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class EmployeeController implements Initializable {

    @FXML
    private JFXRadioButton allemployee;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;
    @FXML
    private TextField search;
    @FXML
    private TableView<EmployeeInfo> employeeinformation;
    @FXML
    private PieChart employeepiechart;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private JFXRadioButton Active;
    ObservableList<PieChart.Data> piechartdata;
    @FXML
    private ToggleGroup check;
    @FXML
    private ToggleGroup check2;
    @FXML
    private TableColumn<EmployeeInfo,String> col_sr;
    @FXML
    private TableColumn<EmployeeInfo,String> col_employeeid;
    @FXML
    private TableColumn<EmployeeInfo,String> col_name;
    @FXML
    private TableColumn<EmployeeInfo,String> col_lname;
    @FXML
    private TableColumn<EmployeeInfo,String> col_cnic;
    @FXML
    private TableColumn<EmployeeInfo,String> col_cellno;
    @FXML
    private TableColumn<EmployeeInfo,String> col_status;
    @FXML
    private TableColumn<EmployeeInfo,String> col_address;
    @FXML
    private TableColumn<EmployeeInfo,String> col_post;
    @FXML
    private TableColumn<EmployeeInfo,String> col_gender;
    @FXML
    private TableColumn<EmployeeInfo,String> col_doj;
    @FXML
    private TableColumn<EmployeeInfo,String> col_dol;
    @FXML
    private TableColumn<EmployeeInfo,String> col_pstatus;
    @FXML
    private Button update1;
    @FXML
    private JFXRadioButton Unactive;

    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
      ObservableList<EmployeeInfo> emplyoeelist =FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        userrestriction();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
         piechartData();
        employeepiechart.setData(piechartdata);
        col_sr.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_lname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        col_cnic.setCellValueFactory(new PropertyValueFactory<>("cnic"));
        col_cellno.setCellValueFactory(new PropertyValueFactory<>("cellno"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_pstatus.setCellValueFactory(new PropertyValueFactory<>("marriedstatus"));
        col_post.setCellValueFactory(new PropertyValueFactory<>("post"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("Currentstatus"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_doj.setCellValueFactory(new PropertyValueFactory<>("dateofjoining"));
        col_dol.setCellValueFactory(new PropertyValueFactory<>("dateofleaving"));
        col_employeeid.setCellValueFactory(new PropertyValueFactory<>("staffid"));
        employeeinformation.setItems(emplyoeelist);
        // TODO
    }    

    @FXML
    private void AllEmplyeeradiobuttonHandling(MouseEvent event) {
        if(Active.isSelected())
        {
            emplyoeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Active' and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(emplyoeelist);
        }
        else if(Unactive.isSelected())
        {
            emplyoeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Unactive' and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(emplyoeelist);
        }
        else
        {
                refreshtable();
                piechartData();
        }
    }

    @FXML
    private void MaleEmplyeeradiobuttonHandling(MouseEvent event) {
        if(Active.isSelected())
        {
            emplyoeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Active' and gender='Male' and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(emplyoeelist);
        }
        else if(Unactive.isSelected())
        {
            emplyoeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Unactive' and gender='Male' and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(emplyoeelist);
        }
        else
        {
                emplyoeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where  gender='Male'and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(emplyoeelist);
        }
    }

    @FXML
    private void FemaleEmplyeeradiobuttonHandling(MouseEvent event) {
        if(Active.isSelected())
        {
            emplyoeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Active' and gender='Female'and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(emplyoeelist);
        }
        else if(Unactive.isSelected())
        {
            emplyoeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Unactive' and gender='Female' and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(emplyoeelist);
        }
        else
        {
                emplyoeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where gender='Female' and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(emplyoeelist);
        }
    }

    @FXML
    private void SearchemployeebyidHandling(ActionEvent event) {
       emplyoeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where employeeid like('"+search.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(emplyoeelist);
    }
    @FXML
    private void ActiveEmplyeeradiobuttonHandling(MouseEvent event) {
        piechartdata.clear();
        int malepie=0,femalepie=0;
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(gender) As myCount from employeeinfo where EmployeeStatus='Active' and gender='Male'and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 malepie=rs.getInt("myCount");
            }    
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(Gender) As myCount from employeeinfo where EmployeeStatus='Active' and gender='Female' and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 femalepie=rs.getInt("myCount");
            }   
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
         piechartdata = FXCollections.observableArrayList(
                        new PieChart.Data("Male",malepie),
                        new PieChart.Data("Female",femalepie) 
        );
         employeepiechart.setData(piechartdata);
        if(male.isSelected())
        {
            emplyoeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Active' and gender='Male' and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(emplyoeelist);
        }
        else if(female.isSelected())
        {
            emplyoeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Active' and gender='Female' and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(emplyoeelist);
        }
        else if(allemployee.isSelected())
        {
                emplyoeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Active'and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(emplyoeelist);
        }
    }

    @FXML
    private void ClearEmplyeeradiobuttonHandling(MouseEvent event) {
        piechartdata.clear();
        int malepie=0,femalepie=0;
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(gender) As myCount from employeeinfo where EmployeeStatus='Unactive' and gender='Male' and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 malepie=rs.getInt("myCount");
            }    
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(Gender) As myCount from employeeinfo where EmployeeStatus='Unactive' and gender='Female' and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 femalepie=rs.getInt("myCount");
            }   
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
         piechartdata = FXCollections.observableArrayList(
                        new PieChart.Data("Male",malepie),
                        new PieChart.Data("Female",femalepie) 
        );
         employeepiechart.setData(piechartdata);
        if(male.isSelected())
        {
            emplyoeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Unactive' and gender='Male' and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(emplyoeelist);
        }
        else if(female.isSelected())
        {
            emplyoeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Unactive' and gender='Female' and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(emplyoeelist);
        }
        else if(allemployee.isSelected())
        {
                emplyoeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Unactive' and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(emplyoeelist);
        }
    }

    @FXML
    private void UpdateEmplyeeButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("UpdateEmployeeDetails");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("UpdateEmployee");
               stage.show();
    }

    @FXML
    private void DeleteEmplyeeButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("DeleteEmployee");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("UpdateEmployee");
               stage.show();
    }
    public void piechartData()
    {
        int male=0,female=0;
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(Gender) As myCount from employeeinfo where Gender='Male' and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 male=rs.getInt("myCount");
            }    
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(Gender) As myCount from employeeinfo where Gender='Female' and DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) { 
                 female=rs.getInt("myCount");
            }   
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
         piechartdata = FXCollections.observableArrayList(
                        new PieChart.Data("Male",male),
                        new PieChart.Data("Female",female) 
        );
    }
    public void refreshtable()
    {
        emplyoeelist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where DataStatus='Employee'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                emplyoeelist.add(new EmployeeInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        employeeinformation.setItems(emplyoeelist);
    }

    @FXML
    private void RefreahButtonHandling(MouseEvent event) {
        refreshtable();
    }
    public void userrestriction()
    {
        if(LoginController.isLogincheck()==true)
        {
        update.setDisable(true);
        delete.setDisable(true);
        }
    }
    
}
