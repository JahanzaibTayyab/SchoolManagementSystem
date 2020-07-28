/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import login.Helper.MysqlConnect;
import login.TableViews.FeeDetailsInfo;
import login.TableViews.StudentInfo;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class FeeDetailsController implements Initializable {

    @FXML
    private JFXRadioButton feereceived;
    @FXML
    private JFXRadioButton feereceiveable;
    @FXML
    private TextField search;
    @FXML
    private JFXComboBox<String> selectclass;
    @FXML
    private JFXComboBox<String> month;
    @FXML
    private JFXComboBox<String> year;
    @FXML
    private JFXRadioButton defaulterwholestudent;
    @FXML
    private TableView<FeeDetailsInfo> studenttable;
    @FXML
    private TextField totalamount;
    @FXML
    private TextField totalpaid;
    @FXML
    private TextField totalbalance;
    @FXML
    private ToggleGroup check;
    @FXML
    private ToggleGroup check2;
      @FXML
    private ComboBox<String> DeafulterClasses;

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXRadioButton ClassRadioButton;
    @FXML
    private ToggleGroup check3;
    @FXML
    private JFXRadioButton Date;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_sr;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_id;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_name;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_month;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_year;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_class;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_cfee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_gfee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_ffee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_efee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_pfee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_sfee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_tfee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_bfee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_ofee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_others;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_paid;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_balance;
    @FXML
    private TableColumn<FeeDetailsInfo,String> Col_sfee;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_section;
    @FXML
    private TableColumn<FeeDetailsInfo,String> col_lastmonth;
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
      ObservableList<FeeDetailsInfo> feelist =FXCollections.observableArrayList();
    ObservableList<String> monthlist =FXCollections.observableArrayList("Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec");
    ObservableList<String> yearlist =FXCollections.observableArrayList("2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030");
    ObservableList<String> classlist =FXCollections.observableArrayList(); 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails ";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                feelist.add(new FeeDetailsInfo(rs.getString("StudentID"),rs.getString("StudentName"),rs.getString("Month")
                        ,rs.getString("Year"),rs.getString("ClassName"),rs.getString("Section"),rs.getString("Classfee"),rs.getString("GeneratorFee"),rs.getString("FunctionFee")
                        ,rs.getString("ExamsFee"),rs.getString("PartyFee"),rs.getString("SportsFee"),rs.getString("TripFee")
                        ,rs.getString("BooksFee"),rs.getString("StationaryFee"),rs.getString("Others"),rs.getString("TotalDues")
                        ,rs.getString("Paid"),rs.getString("Balance"),i,rs.getString("Lastmonthdues")));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        col_sr.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("studenid"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_month.setCellValueFactory(new PropertyValueFactory<>("month"));
        col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_class.setCellValueFactory(new PropertyValueFactory<>("classes"));
        col_cfee.setCellValueFactory(new PropertyValueFactory<>("classfee"));
        col_gfee.setCellValueFactory(new PropertyValueFactory<>("generator"));
        col_ffee.setCellValueFactory(new PropertyValueFactory<>("functionfee"));
        col_efee.setCellValueFactory(new PropertyValueFactory<>("examsfee"));
        col_pfee.setCellValueFactory(new PropertyValueFactory<>("partyfee"));
        col_sfee.setCellValueFactory(new PropertyValueFactory<>("sportsfee"));
        col_tfee.setCellValueFactory(new PropertyValueFactory<>("tripfee"));
        col_bfee.setCellValueFactory(new PropertyValueFactory<>("booksfee"));
        Col_sfee.setCellValueFactory(new PropertyValueFactory<>("stationaryfee"));
        col_ofee.setCellValueFactory(new PropertyValueFactory<>("others"));
        col_others.setCellValueFactory(new PropertyValueFactory<>("total"));
        col_paid.setCellValueFactory(new PropertyValueFactory<>("paid"));
        col_balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        col_section.setCellValueFactory(new PropertyValueFactory<>("section"));
        col_lastmonth.setCellValueFactory(new PropertyValueFactory<>("Lastmonth"));
        studenttable.setItems(feelist);
        // TODO
        addValuesincombobox();
    }    
    @FXML
    private void SearchbarHandling(ActionEvent event) {
        feelist.clear();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails where StudentID like('"+search.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                feelist.add(new FeeDetailsInfo(rs.getString("StudentID"),rs.getString("StudentName"),rs.getString("Month")
                        ,rs.getString("Year"),rs.getString("ClassName"),rs.getString("Section"),rs.getString("Classfee"),rs.getString("GeneratorFee"),rs.getString("FunctionFee")
                        ,rs.getString("ExamsFee"),rs.getString("PartyFee"),rs.getString("SportsFee"),rs.getString("TripFee")
                        ,rs.getString("BooksFee"),rs.getString("StationaryFee"),rs.getString("Others"),rs.getString("TotalDues")
                        ,rs.getString("Paid"),rs.getString("Balance"),i,rs.getString("Lastmonthdues")));
                i++;
            }
             studenttable.setItems(feelist);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void ReceiveableButtonHandling(MouseEvent event) {
        if(ClassRadioButton.isSelected())
        {
            feelist.clear();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails where ClassName like('"+selectclass.getValue()+"%') and Balance > 0";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                feelist.add(new FeeDetailsInfo(rs.getString("StudentID"),rs.getString("StudentName"),rs.getString("Month")
                        ,rs.getString("Year"),rs.getString("ClassName"),rs.getString("Section"),rs.getString("Classfee"),rs.getString("GeneratorFee"),rs.getString("FunctionFee")
                        ,rs.getString("ExamsFee"),rs.getString("PartyFee"),rs.getString("SportsFee"),rs.getString("TripFee")
                        ,rs.getString("BooksFee"),rs.getString("StationaryFee"),rs.getString("Others"),rs.getString("TotalDues")
                        ,rs.getString("Paid"),rs.getString("Balance"),i,rs.getString("Lastmonthdues")));
                i++;
            }
             studenttable.setItems(feelist);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        }
        else if(Date.isSelected())
        {
            feelist.clear();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails where Month like('"+month.getValue()+"%') and Balance > 0 and Year like('"+year.getValue()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                feelist.add(new FeeDetailsInfo(rs.getString("StudentID"),rs.getString("StudentName"),rs.getString("Month")
                        ,rs.getString("Year"),rs.getString("ClassName"),rs.getString("Section"),rs.getString("Classfee"),rs.getString("GeneratorFee"),rs.getString("FunctionFee")
                        ,rs.getString("ExamsFee"),rs.getString("PartyFee"),rs.getString("SportsFee"),rs.getString("TripFee")
                        ,rs.getString("BooksFee"),rs.getString("StationaryFee"),rs.getString("Others"),rs.getString("TotalDues")
                        ,rs.getString("Paid"),rs.getString("Balance"),i,rs.getString("Lastmonthdues")));
                i++;
            }
             studenttable.setItems(feelist);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        }
        else
        {
            feelist.clear();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails where Balance > 0";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
               feelist.add(new FeeDetailsInfo(rs.getString("StudentID"),rs.getString("StudentName"),rs.getString("Month")
                        ,rs.getString("Year"),rs.getString("ClassName"),rs.getString("Section"),rs.getString("Classfee"),rs.getString("GeneratorFee"),rs.getString("FunctionFee")
                        ,rs.getString("ExamsFee"),rs.getString("PartyFee"),rs.getString("SportsFee"),rs.getString("TripFee")
                        ,rs.getString("BooksFee"),rs.getString("StationaryFee"),rs.getString("Others"),rs.getString("TotalDues")
                        ,rs.getString("Paid"),rs.getString("Balance"),i,rs.getString("Lastmonthdues")));
                i++;
            }
             studenttable.setItems(feelist);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        }
    }

    @FXML
    private void DefaultersStudentButtonHandling(MouseEvent event) {
        feelist.clear();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails where Balance > 0 ";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                feelist.add(new FeeDetailsInfo(rs.getString("StudentID"),rs.getString("StudentName"),rs.getString("Month")
                        ,rs.getString("Year"),rs.getString("ClassName"),rs.getString("Section"),rs.getString("Classfee"),rs.getString("GeneratorFee"),rs.getString("FunctionFee")
                        ,rs.getString("ExamsFee"),rs.getString("PartyFee"),rs.getString("SportsFee"),rs.getString("TripFee")
                        ,rs.getString("BooksFee"),rs.getString("StationaryFee"),rs.getString("Others"),rs.getString("TotalDues")
                        ,rs.getString("Paid"),rs.getString("Balance"),i,rs.getString("Lastmonthdues")));
                i++;
            }
             studenttable.setItems(feelist);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void SelectClassActionButtonHandling(ActionEvent event) {
        if(feereceived.isSelected())
        {
            feelist.clear();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails where Month like('"+selectclass.getValue()+"%') and  Balance=0";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                feelist.add(new FeeDetailsInfo(rs.getString("StudentID"),rs.getString("StudentName"),rs.getString("Month")
                        ,rs.getString("Year"),rs.getString("ClassName"),rs.getString("Section"),rs.getString("Classfee"),rs.getString("GeneratorFee"),rs.getString("FunctionFee")
                        ,rs.getString("ExamsFee"),rs.getString("PartyFee"),rs.getString("SportsFee"),rs.getString("TripFee")
                        ,rs.getString("BooksFee"),rs.getString("StationaryFee"),rs.getString("Others"),rs.getString("TotalDues")
                        ,rs.getString("Paid"),rs.getString("Balance"),i,rs.getString("Lastmonthdues")));
                i++;
            }
             studenttable.setItems(feelist);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        }
        else if(feereceiveable.isSelected())
        {
            feelist.clear();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails where ClassName like('"+selectclass.getValue()+"%') and  Balance > 0";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                feelist.add(new FeeDetailsInfo(rs.getString("StudentID"),rs.getString("StudentName"),rs.getString("Month")
                        ,rs.getString("Year"),rs.getString("ClassName"),rs.getString("Section"),rs.getString("Classfee"),rs.getString("GeneratorFee"),rs.getString("FunctionFee")
                        ,rs.getString("ExamsFee"),rs.getString("PartyFee"),rs.getString("SportsFee"),rs.getString("TripFee")
                        ,rs.getString("BooksFee"),rs.getString("StationaryFee"),rs.getString("Others"),rs.getString("TotalDues")
                        ,rs.getString("Paid"),rs.getString("Balance"),i,rs.getString("Lastmonthdues")));
                i++;
            }
             studenttable.setItems(feelist);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        }
        else
        {
            feelist.clear();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails where ClassName like('"+selectclass.getValue()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                feelist.add(new FeeDetailsInfo(rs.getString("StudentID"),rs.getString("StudentName"),rs.getString("Month")
                        ,rs.getString("Year"),rs.getString("ClassName"),rs.getString("Section"),rs.getString("Classfee"),rs.getString("GeneratorFee"),rs.getString("FunctionFee")
                        ,rs.getString("ExamsFee"),rs.getString("PartyFee"),rs.getString("SportsFee"),rs.getString("TripFee")
                        ,rs.getString("BooksFee"),rs.getString("StationaryFee"),rs.getString("Others"),rs.getString("TotalDues")
                        ,rs.getString("Paid"),rs.getString("Balance"),i,rs.getString("Lastmonthdues")));
                i++;
            }
             studenttable.setItems(feelist);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        }
    }
    @FXML
    private void ClassRadioButtonHandling(MouseEvent event) {
    }

    @FXML
    private void DateRadioButtonHAndling(MouseEvent event) {
    }
    @FXML
    private void MonthButtonHandling(ActionEvent event) {
    }

    @FXML
    private void yearButtonHandling(ActionEvent event) {
    }
    @FXML
    private void RefreshTableButton(MouseEvent event) {
        refredhtable();
    }
    public void refredhtable()
    {
        feelist.clear();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails ";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                feelist.add(new FeeDetailsInfo(rs.getString("StudentID"),rs.getString("StudentName"),rs.getString("Month")
                        ,rs.getString("Year"),rs.getString("ClassName"),rs.getString("Section"),rs.getString("Classfee"),rs.getString("GeneratorFee"),rs.getString("FunctionFee")
                        ,rs.getString("ExamsFee"),rs.getString("PartyFee"),rs.getString("SportsFee"),rs.getString("TripFee")
                        ,rs.getString("BooksFee"),rs.getString("StationaryFee"),rs.getString("Others"),rs.getString("TotalDues")
                        ,rs.getString("Paid"),rs.getString("Balance"),i,rs.getString("Lastmonthdues")));
                i++;
            }
             studenttable.setItems(feelist);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addValuesincombobox()
    {
        month.setItems(monthlist);
        year.setItems(yearlist);
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT ClassName  from classinfo ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                classlist.add(rs.getString("ClassName"));
            }
            selectclass.setItems(classlist); 
            DeafulterClasses.setItems(classlist);
            stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
         studenttable.setItems(feelist);
    }

    @FXML
    private void ReceivedButtonHandling(MouseEvent event) {
        if(ClassRadioButton.isSelected())
        {
            feelist.clear();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails where ClassName like('"+selectclass.getValue()+"%') and Balance=0";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                feelist.add(new FeeDetailsInfo(rs.getString("StudentID"),rs.getString("StudentName"),rs.getString("Month")
                        ,rs.getString("Year"),rs.getString("ClassName"),rs.getString("Section"),rs.getString("Classfee"),rs.getString("GeneratorFee"),rs.getString("FunctionFee")
                        ,rs.getString("ExamsFee"),rs.getString("PartyFee"),rs.getString("SportsFee"),rs.getString("TripFee")
                        ,rs.getString("BooksFee"),rs.getString("StationaryFee"),rs.getString("Others"),rs.getString("TotalDues")
                        ,rs.getString("Paid"),rs.getString("Balance"),i,rs.getString("Lastmonthdues")));
                i++;
            }
             studenttable.setItems(feelist);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        }
        else if(Date.isSelected())
        {
            feelist.clear();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails where Month like('"+month.getValue()+"%') and Balance=0 and Year like('"+year.getValue()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
               feelist.add(new FeeDetailsInfo(rs.getString("StudentID"),rs.getString("StudentName"),rs.getString("Month")
                        ,rs.getString("Year"),rs.getString("ClassName"),rs.getString("Section"),rs.getString("Classfee"),rs.getString("GeneratorFee"),rs.getString("FunctionFee")
                        ,rs.getString("ExamsFee"),rs.getString("PartyFee"),rs.getString("SportsFee"),rs.getString("TripFee")
                        ,rs.getString("BooksFee"),rs.getString("StationaryFee"),rs.getString("Others"),rs.getString("TotalDues")
                        ,rs.getString("Paid"),rs.getString("Balance"),i,rs.getString("Lastmonthdues")));
                i++;
            }
             studenttable.setItems(feelist);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        }
        else
        {
            feelist.clear();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails where Balance=0";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                feelist.add(new FeeDetailsInfo(rs.getString("StudentID"),rs.getString("StudentName"),rs.getString("Month")
                        ,rs.getString("Year"),rs.getString("ClassName"),rs.getString("Section"),rs.getString("Classfee"),rs.getString("GeneratorFee"),rs.getString("FunctionFee")
                        ,rs.getString("ExamsFee"),rs.getString("PartyFee"),rs.getString("SportsFee"),rs.getString("TripFee")
                        ,rs.getString("BooksFee"),rs.getString("StationaryFee"),rs.getString("Others"),rs.getString("TotalDues")
                        ,rs.getString("Paid"),rs.getString("Balance"),i,rs.getString("Lastmonthdues")));
                i++;
            }
             studenttable.setItems(feelist);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        }
    }

    @FXML
    private void DefaulterClassButtonHandling(ActionEvent event) {
        feelist.clear();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from feedetails where ClassName like('"+DeafulterClasses.getValue()+"%') and  Balance > 0";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                feelist.add(new FeeDetailsInfo(rs.getString("StudentID"),rs.getString("StudentName"),rs.getString("Month")
                        ,rs.getString("Year"),rs.getString("ClassName"),rs.getString("Section"),rs.getString("Classfee"),rs.getString("GeneratorFee"),rs.getString("FunctionFee")
                        ,rs.getString("ExamsFee"),rs.getString("PartyFee"),rs.getString("SportsFee"),rs.getString("TripFee")
                        ,rs.getString("BooksFee"),rs.getString("StationaryFee"),rs.getString("Others"),rs.getString("TotalDues")
                        ,rs.getString("Paid"),rs.getString("Balance"),i,rs.getString("Lastmonthdues")));
                i++;
            }
             studenttable.setItems(feelist);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

    

    
    
}
