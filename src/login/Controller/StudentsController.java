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
import java.text.MessageFormat;
import java.util.ResourceBundle;
import javafx.collections.*;
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
import login.TableViews.StudentInfo;
import java.awt.*;
import java.text.*;
import java.awt.print.*;
import javafx.print.Printer;
/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class StudentsController implements Initializable {

    @FXML
    private JFXRadioButton allstudent;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;
    @FXML
    private TextField search;
    @FXML
    private TableView<StudentInfo> studenttable;
    @FXML
    private PieChart studentpiechart;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button Add;
    @FXML
    private JFXRadioButton Passout;
    @FXML
    private JFXRadioButton clear;
     ObservableList<PieChart.Data> piechartdata;
    @FXML
    private ToggleGroup check;
    @FXML
    private ToggleGroup chechk2;
    @FXML
    private TableColumn<StudentInfo, String> col_id;
    @FXML
    private TableColumn<StudentInfo, String> col_name;
    @FXML
    private TableColumn<StudentInfo, String> col_lastname;
    @FXML
    private TableColumn<StudentInfo, String> col_bform;
    @FXML
    private TableColumn<StudentInfo, String> col_cellno;
    @FXML
    private TableColumn<StudentInfo, String> col_address;
    @FXML
    private TableColumn<StudentInfo, String> col_class;
    @FXML
    private TableColumn<StudentInfo, String> col_section;
    @FXML
    private TableColumn<StudentInfo, String> col_dob;
    @FXML
    private TableColumn<StudentInfo, String> col_gender;
     @FXML
    private TableColumn<StudentInfo, String> Col_sr;

    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
      ObservableList<StudentInfo> studentlist =FXCollections.observableArrayList();
    @FXML
    private Button update1;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        userrestriction();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo where Status='Student'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        piechartData();
        studentpiechart.setData(piechartdata);
        col_id.setCellValueFactory(new PropertyValueFactory<>("studentid"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        col_bform.setCellValueFactory(new PropertyValueFactory<>("bfrom"));
        col_cellno.setCellValueFactory(new PropertyValueFactory<>("cellno"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_class.setCellValueFactory(new PropertyValueFactory<>("studentclass"));
        col_section.setCellValueFactory(new PropertyValueFactory<>("section"));
        col_dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        Col_sr.setCellValueFactory(new PropertyValueFactory<>("id"));
        studenttable.setItems(studentlist);
        // TODO
    } 

    public StudentsController() {
    }
    @FXML
    private void AllStudentRadioButton(MouseEvent event) {
        if(Passout.isSelected()) 
        {
            studentlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo  where Status='Passout'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
             studenttable.setItems(studentlist);
        }
       else if(clear.isSelected())
        {
            studentlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo  where Status='Clear'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        studenttable.setItems(studentlist);
        }
        else
       {
                refreshtable();
                piechartData();
       }
    }

    @FXML
    private void MaleStudentRadioButton(MouseEvent event) {
        if(Passout.isSelected())
        {
            studentlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo  where Gender='Male' and Status='Passout'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        studenttable.setItems(studentlist);
        }
        else if(clear.isSelected())
        {
            studentlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo  where Gender='Male' and Status='Passout'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        }
        else
        {
        studentlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo  where Status='Student' and Gender='Male'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        studenttable.setItems(studentlist);
        }
    }

    @FXML
    private void FemaleStudentRadioButton(MouseEvent event) {
        if(Passout.isSelected())
        {
            studentlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo  where Gender='Female' and Status='Passout'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        studenttable.setItems(studentlist);
        }
        else if(clear.isSelected())
        {
            studentlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo  where Gender='Female' and Status='Clear'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
            studenttable.setItems(studentlist);
        }
        else
        {
        studentlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo  where Status='Student' and Gender='Female'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        studenttable.setItems(studentlist);
        }
    }
    @FXML
    private void PassoutStudentRadioButton(MouseEvent event) {
        piechartdata.clear();
        int malepie=0,femalepie=0;
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(Gender) As myCount from studentinfo where Gender='Male'and Status='Passout'";
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
            String SQL = "SELECT Count(Gender) As myCount from studentinfo where Gender='Female'and Status='Passout'";
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
         studentpiechart.setData(piechartdata);
        if(male.isSelected())
        {
            studentlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo  where Gender='Male' and Status='Passout'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        studenttable.setItems(studentlist);
        }
        else if(female.isSelected())
        {
            studentlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo  where Gender='Female' and Status='Passout'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        studenttable.setItems(studentlist);
        }
        else if(allstudent.isSelected())
        {
            studentlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo  where Status='Passout'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
             studenttable.setItems(studentlist);
        }
    }

    @FXML
    private void SearchStudentbyID(ActionEvent event) {
        studentlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo  where studentid like('"+search.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        studenttable.setItems(studentlist);
    }

    @FXML
    private void ClearStudentRadioButton(MouseEvent event) {
        piechartdata.clear();
        int malepie=0,femalepie=0;
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(Gender) As myCount from studentinfo where Gender='Male' and Status='Clear'";
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
            String SQL = "SELECT Count(Gender) As myCount from studentinfo where Gender='Female' and Status='Clear'";
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
         studentpiechart.setData(piechartdata);
        
        if(male.isSelected())
        {
            studentlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo  where Gender='Male' and Status='Clear'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        studenttable.setItems(studentlist);
        }
        else if(female.isSelected())
        {
            studentlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo  where Gender='Female' and Status='Clear'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        studenttable.setItems(studentlist);
        }
        else if(allstudent.isSelected())
        {
            studentlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo  where Status='Clear'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        studenttable.setItems(studentlist);
        }
    }

    @FXML
    private void UpdateStudentButtonEventHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("UpdateStudentInformation");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("UpdateStudent");
               stage.show();
    }

    @FXML
    private void DeleteStudentButtonEventHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("DeleteStudent");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("DeleteStudent");
               stage.show();
    }

    @FXML
    private void AddStudentButtonEventHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("AddStudent");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("AddStudent");
               stage.show();
    }
    public void piechartData()
    {
        int male=0,female=0;
        try (
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Count(Gender) As myCount from studentinfo where Gender='Male' and Status='Student'";
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
            String SQL = "SELECT Count(Gender) As myCount from studentinfo where Gender='Female' and  Status='Student'";
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
         studentpiechart.setData(piechartdata);
    }
    public void refreshtable()
    {
        studentlist.clear();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from studentinfo where Status='Student'";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                studentlist.add(new StudentInfo(i,rs.getString("studentid"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Bform"),rs.getString("cellno"),rs.getString("Address")
                ,rs.getString("Class"),rs.getString("Section"),rs.getString("DateofBirth"),rs.getString("Gender")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        studenttable.setItems(studentlist);
    }
    @FXML
    private void AllstudentButtonHandling(ActionEvent event) {
    }

    @FXML
    private void MaleStudentButtonHandling(ActionEvent event) {
    }

    @FXML
    private void FemaleStudentButtonHandling(ActionEvent event) {
    }

    @FXML
    private void PassoutStudentButton(ActionEvent event) {
    }
 
    @FXML
    private void LeaveStudentButton(ActionEvent event) {
    }

    @FXML
    private void RefreshButtonHandling(MouseEvent event) {
        refreshtable();
        piechartData();
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
