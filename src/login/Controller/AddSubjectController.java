/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import login.Helper.HelperViewsResources;
import login.Helper.MysqlConnect;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class AddSubjectController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;
    @FXML
    private JFXButton back;
    @FXML
    private JFXTextField subjectname;
    @FXML
    private JFXTextField teacherid;
    @FXML
    private JFXButton Add;
    @FXML
    private JFXButton Clear;
    @FXML
    private JFXButton Update;
    @FXML
    private JFXTextField subjectcode;
    @FXML
    private JFXTextField bookname;
    @FXML
    private JFXTextField subjectmarks;

    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    @FXML
    private JFXButton Clear1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         conn=MysqlConnect.ConnectDB();
        // TODO
    }    

    @FXML
    private void viewButtonHandling(MouseEvent event) {
    }

    @FXML
    private void InsertButtonHandling(MouseEvent event) {
        if(emptyfieldvalidate())
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Add Subject");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                String subjectinfo ="INSERT INTO subjectinfo (SubjectCode,SubjectName,BookName,TeacherId,SubjectMarks)"
                + "VALUES (?,?,?,?,?)";
        try{
                pst=conn.prepareStatement(subjectinfo);
                pst.setString(1,subjectcode.getText());
                pst.setString(2,subjectname.getText());
                pst.setString(3,bookname.getText());
                pst.setString(4,teacherid.getText());
                pst.setInt(5,Integer.parseInt(subjectmarks.getText()));
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
    private void resetButtonHandling(MouseEvent event) {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Clear the data");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                subjectcode.clear();
                subjectname.clear();
                bookname.clear();
                teacherid.clear();
                subjectmarks.clear();
            }
    }

    @FXML
    private void UpdateButtonHandling(MouseEvent event) {
        if(emptyfieldvalidate())
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Update ");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                String sql =" update subjectinfo  set SubjectCode=?,SubjectName=?,BookName=?,TeacherId=?,SubjectMarks=? where SubjectCode like('"+subjectcode.getText()+"%')";
        try{
                pst=conn.prepareStatement(sql);
                pst.setString(1,subjectcode.getText());
                pst.setString(2,subjectname.getText());
                pst.setString(3,bookname.getText());
                pst.setString(4,teacherid.getText());
                pst.setInt(5,Integer.parseInt(subjectmarks.getText()));
              int row=pst.executeUpdate();
                if(row==1)
                {
                     Alert alert1 =new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information");
                        alert1.setHeaderText("Data Successfully Updated");
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
    public boolean  emptyfieldvalidate()
    {
        if(subjectname.getText().isEmpty() || teacherid.getText().isEmpty() || subjectcode.getText().isEmpty() || bookname.getText().isEmpty() || 
                subjectmarks.getText().isEmpty() 
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

    @FXML
    private void DeleteButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("DeleteSubject");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Delete Subject");
               stage.show();
        
    }
    
}
