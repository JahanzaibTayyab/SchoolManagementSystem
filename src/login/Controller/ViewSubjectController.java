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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import login.Helper.MysqlConnect;
import login.TableViews.StudentInfo;
import login.TableViews.SubjectInfo;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class ViewSubjectController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;
    @FXML
    private TableColumn<SubjectInfo,String> col_sr;
    @FXML
    private TableColumn<SubjectInfo,String> col_name;
    @FXML
    private TableColumn<SubjectInfo,String> col_code;
    @FXML
    private TableColumn<SubjectInfo,String> col_TeacherID;
    @FXML
    private TableColumn<SubjectInfo,String> col_book;
    @FXML
    private TableColumn<SubjectInfo,String> col_marks;
    @FXML
    private TableView<SubjectInfo> subjecttable;

    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
      ObservableList<SubjectInfo> subjectlist =FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        try (
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from subjectinfo ";
            ResultSet rs = stmt.executeQuery(SQL);
            int i=1;
            while (rs.next()) {
                subjectlist.add(new SubjectInfo(rs.getString("TeacherId"),rs.getString("SubjectName"),rs.getString("SubjectCode"),rs.getString("BookName"),rs.getString("SubjectMarks"),i));
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        col_sr.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_code.setCellValueFactory(new PropertyValueFactory<>("subjectcode"));
        col_TeacherID.setCellValueFactory(new PropertyValueFactory<>("teacherid"));
        col_book.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        col_marks.setCellValueFactory(new PropertyValueFactory<>("marks"));
         subjecttable.setItems(subjectlist);
        // TODO
    }    
    
}
