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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import login.Helper.HelperViewsResources;
import login.Helper.MysqlConnect;
import login.TableViews.TeacherInfo;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class TeachersController implements Initializable {

    @FXML
    private AnchorPane Teacher;
    @FXML
    private JFXRadioButton allteacher;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;
    @FXML
    private TextField search;
    @FXML
    private TableView<TeacherInfo> teachertable;
    @FXML
    private PieChart teacherpiechart;
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
    private TableColumn<TeacherInfo, String> col_sr;
    @FXML
    private TableColumn<TeacherInfo, String> col_staffid;
    @FXML
    private TableColumn<TeacherInfo, String> col_name;
    @FXML
    private TableColumn<TeacherInfo, String> col_lname;
    @FXML
    private TableColumn<TeacherInfo, String> col_cnic;
    @FXML
    private TableColumn<TeacherInfo, String> col_cellno;
    @FXML
    private TableColumn<TeacherInfo, String> col_pstatus;
    @FXML
    private TableColumn<TeacherInfo, String> col_address;
    @FXML
    private TableColumn<TeacherInfo, String> col_post;
    @FXML
    private TableColumn<TeacherInfo, String> col_gender;
    @FXML
    private TableColumn<TeacherInfo, String> Col_Sstatus;
    @FXML
    private TableColumn<TeacherInfo, String> col_doj;
    @FXML
    private TableColumn<TeacherInfo, String> col_dol;
    @FXML
    private Button update1;

    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
      ObservableList<TeacherInfo> teacherlist =FXCollections.observableArrayList();
    @FXML
    private JFXRadioButton Unactive;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        userrestriction();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where DataStatus='Staff'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        piechartData();
        teacherpiechart.setData(piechartdata);
        col_sr.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_lname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        col_cnic.setCellValueFactory(new PropertyValueFactory<>("cnic"));
        col_cellno.setCellValueFactory(new PropertyValueFactory<>("cellno"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_pstatus.setCellValueFactory(new PropertyValueFactory<>("marriedstatus"));
        col_post.setCellValueFactory(new PropertyValueFactory<>("post"));
        Col_Sstatus.setCellValueFactory(new PropertyValueFactory<>("Currentstatus"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_doj.setCellValueFactory(new PropertyValueFactory<>("dateofjoining"));
        col_dol.setCellValueFactory(new PropertyValueFactory<>("dateofleaving"));
        col_staffid.setCellValueFactory(new PropertyValueFactory<>("staffid"));
        teachertable.setItems(teacherlist);
        // TODO
    }    

    @FXML
    private void AllTeacherRadioButtonHandling(MouseEvent event) {
        if(Active.isSelected())
        {
            teacherlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Active' and DataStatus='Staff'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        teachertable.setItems(teacherlist);
        }
        else if(Unactive.isSelected())
        {
            teacherlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Unactive' and DataStatus='Staff'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        teachertable.setItems(teacherlist);
        }
        else
        {
                refreshtable();
                piechartData();
        }
    }

    @FXML
    private void MaleTeacherRadioButtonHandling(MouseEvent event) {
        if(Active.isSelected())
        {
            teacherlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Active' and gender='Male' and DataStatus='Staff'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        teachertable.setItems(teacherlist);
        }
        else if(Unactive.isSelected())
        {
            teacherlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Unactive' and gender='Male' and DataStatus='Staff'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        teachertable.setItems(teacherlist);
        }
        else
        {
                teacherlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where  gender='Male' and DataStatus='Staff'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        teachertable.setItems(teacherlist);
        }
    }

    @FXML
    private void FemaleTeacherRadioButtonHandling(MouseEvent event) {
        if(Active.isSelected())
        {
            teacherlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Active' and gender='Female'and DataStatus='Staff'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        teachertable.setItems(teacherlist);
        }
        else if(Unactive.isSelected())
        {
            teacherlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Unactive' and gender='Female' and DataStatus='Staff'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        teachertable.setItems(teacherlist);
        }
        else
        {
                teacherlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where gender='Female' and DataStatus='Staff'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        teachertable.setItems(teacherlist);
        }
    }

    @FXML
    private void SearchTeacherbyidHandling(ActionEvent event) {
        teacherlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where employeeid like('"+search.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        teachertable.setItems(teacherlist);
    }

    @FXML
    private void ActiveTeacherRadioButtonHandling(MouseEvent event) {
        piechartdata.clear();
        int malepie=0,femalepie=0;
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(gender) As myCount from employeeinfo where EmployeeStatus='Active' and gender='Male'and DataStatus='Staff'";
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
            String SQL = "SELECT Count(Gender) As myCount from employeeinfo where EmployeeStatus='Active' and gender='Female' and DataStatus='Staff'";
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
         teacherpiechart.setData(piechartdata);
        if(male.isSelected())
        {
            teacherlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Active' and gender='Male'and DataStatus='Staff'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        teachertable.setItems(teacherlist);
        }
        else if(female.isSelected())
        {
            teacherlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Active' and gender='Female'and DataStatus='Staff'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        teachertable.setItems(teacherlist);
        }
        else if(allteacher.isSelected())
        {
                teacherlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Active' and DataStatus='Staff'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        teachertable.setItems(teacherlist);
        }
    }

    @FXML
    private void ClearTeacherRadioButtonHandling(MouseEvent event) {
        piechartdata.clear();
        int malepie=0,femalepie=0;
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(gender) As myCount from employeeinfo where EmployeeStatus='Unactive' and gender='Male' and DataStatus='Staff'";
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
            String SQL = "SELECT Count(Gender) As myCount from employeeinfo where EmployeeStatus='Unactive' and gender='Female' and DataStatus='Staff'";
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
         teacherpiechart.setData(piechartdata);
        if(male.isSelected())
        {
            teacherlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Unactive' and gender='Male' and DataStatus='Staff'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        teachertable.setItems(teacherlist);
        }
        else if(female.isSelected())
        {
            teacherlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Unactive' and gender='Female' and DataStatus='Staff'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        teachertable.setItems(teacherlist);
        }
        else if(allteacher.isSelected())
        {
                teacherlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where EmployeeStatus='Unactive'and DataStatus='Staff' and DataStatus='Staff'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        teachertable.setItems(teacherlist);
        }
    }
    @FXML
    private void RefreshTable(MouseEvent event) {
        refreshtable();
    }

    @FXML
    private void UpdateTeacherButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("UpdateTeacherDetails");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("UpdateTeacher");
               stage.show();
    }

    @FXML
    private void DeleteTeacherButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("DeleteTeacher");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("DeleteTeacher");
               stage.show();
    }
    public void piechartData()
    {
        int male=0,female=0;
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(Gender) As myCount from employeeinfo where Gender='Male'";
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
            String SQL = "SELECT Count(Gender) As myCount from employeeinfo where Gender='Female'";
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
        teacherlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where DataStatus='Staff' ";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                teacherlist.add(new TeacherInfo(rs.getString("employeeid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("CNIC"),rs.getString("CellNo"),rs.getString("MarriedStatus")
                ,rs.getString("Address"),rs.getString("Post"),rs.getString("LeavingDate"),rs.getString("gender"),rs.getString("EmployeeStatus"),rs.getString("JoiningDate"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        teachertable.setItems(teacherlist);
    }

    @FXML
    private void AllTeacherButton(ActionEvent event) {
    }

    @FXML
    private void FemaleTeacherButton(ActionEvent event) {
    }

    @FXML
    private void ActiveButton(ActionEvent event) {
    }

    @FXML
    private void UnactiveButton(ActionEvent event) {
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
