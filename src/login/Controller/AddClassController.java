/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import login.Helper.MysqlConnect;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class AddClassController implements Initializable {

    @FXML
    private JFXTextField classname;
    @FXML
    private JFXTextField section;
    @FXML
    private JFXComboBox<String> subject1;
    @FXML
    private JFXComboBox<String> subject2;
    @FXML
    private JFXComboBox<String> subject3;
    @FXML
    private JFXComboBox<String> subject4;
    @FXML
    private JFXComboBox<String> subject5;
    @FXML
    private JFXComboBox<String> subject6;
    @FXML
    private JFXComboBox<String> subject7;
    @FXML
    private JFXComboBox<String> subject8;
    @FXML
    private JFXComboBox<String> subject9;
    @FXML
    private JFXComboBox<String> subject10;
    @FXML
    private JFXComboBox<String> subject11;
    @FXML
    private JFXComboBox<String> subject12;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> Subject =FXCollections.observableArrayList();
   Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        addValuesincombobox();
        // TODO
    }    

    @FXML
    private void InsertButtonHandling(MouseEvent event) {
        if(emptyfieldvalidate())
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Add Class");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                 String classinfo ="INSERT INTO classinfo "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
                pst=conn.prepareStatement(classinfo);
                pst.setString(1,classname.getText());
                pst.setString(2,section.getText());
                pst.setString(3,subject1.getValue());
                pst.setString(4,subject2.getValue());
                pst.setString(5,subject3.getValue());
                pst.setString(6,subject4.getValue());
                pst.setString(7,subject5.getValue());
                pst.setString(8,subject6.getValue());
                pst.setString(9,subject7.getValue());
                pst.setString(10,subject8.getValue());
                pst.setString(11,subject9.getValue());
                pst.setString(12,subject10.getValue());
                pst.setString(13,subject11.getValue());
                pst.setString(14,subject12.getValue());
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
    }

    @FXML
    private void UpdateButtonHandling(MouseEvent event) {
        if(emptyfieldvalidate())
        {
            if(emptyfieldvalidate())
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Update");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                 String classinfo ="Update classinfo set ClassName=?,Section=? ,Subject1Name=? ,Subject2Name=? ,Subject3Name=? ,Subject4Name=? ,Subject5Name=? ,Subject6Name=?"
                         + ",Subject7Name=?,Subject8Name=?,Subject9Name=?,Subject10Name=?,Subject11Name=? ,Subject12Name=? where ClassName like('"+classname.getText()+"%') and Section like('"+section.getText()+"%')";
        try{
                pst=conn.prepareStatement(classinfo);
                pst.setString(1,classname.getText());
                pst.setString(2,section.getText());
               pst.setString(3,subject1.getValue());
                pst.setString(4,subject2.getValue());
                pst.setString(5,subject3.getValue());
                pst.setString(6,subject4.getValue());
                pst.setString(7,subject5.getValue());
                pst.setString(8,subject6.getValue());
                pst.setString(9,subject7.getValue());
                pst.setString(10,subject8.getValue());
                pst.setString(11,subject9.getValue());
                pst.setString(12,subject10.getValue());
                pst.setString(13,subject11.getValue());
                pst.setString(14,subject12.getValue());
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
        }
    }
    public void addValuesincombobox()
    {
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT SubjectName  from subjectinfo";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                Subject.add(rs.getString("SubjectName"));
            }
            subject1.setItems(Subject);
         subject2.setItems(Subject);
         subject3.setItems(Subject);
         subject4.setItems(Subject);
         subject5.setItems(Subject);
         subject6.setItems(Subject);
         subject7.setItems(Subject);
         subject8.setItems(Subject);
         subject9.setItems(Subject);
         subject10.setItems(Subject);
         subject11.setItems(Subject);
         subject12.setItems(Subject); 
            stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }  
}
    public boolean  emptyfieldvalidate()
    {
        if(classname.getText().isEmpty() || section.getText().isEmpty() 
                  )
             
        {
                        Alert alert =new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Validate Fields");
                        alert.setHeaderText("Fields cannot be empty");
                        alert.setContentText("Please enter the data");
                        alert.showAndWait();
                        return false;
        }
        return true;  
    }
}
