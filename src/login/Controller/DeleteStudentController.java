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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import login.Helper.MysqlConnect;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class DeleteStudentController implements Initializable {

    @FXML
    private JFXTextField studentid;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton dismiss;

    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
// TODO
    }    

    @FXML
    private void DeleteButtonHandling(MouseEvent event) {
        if(emptyfieldvalidate())
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Delete Student");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                String sql ="Delete From studentinfo where studentid=?";
        try{
            
            pst=conn.prepareStatement(sql);
            pst.setString(1,studentid.getText());
               boolean numRows=pst.execute();
               if(numRows==false)
               {
                    Alert alert1 =new Alert(Alert.AlertType.CONFIRMATION);
                        alert1.setTitle("Conformation");
                        alert1.setHeaderText("Deleted");
                        alert1.showAndWait();
               }
            }
            catch (SQLException e) {
            e.printStackTrace();
        }
            }
        }
    }

    @FXML
    private void dismissButtonHandling(MouseEvent event) {
    }
    public boolean  emptyfieldvalidate()
    {
        if(studentid.getText().isEmpty() 
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
