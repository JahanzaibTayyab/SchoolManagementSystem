/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import login.Helper.HelperViewsResources;
import login.Helper.MysqlConnect;

/**
 *
 * @author Jahanzaib Tayyab
 */
public class LoginController implements Initializable {
    
    private final StringProperty string = new SimpleStringProperty();
    @FXML
    private AnchorPane Login;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton clickhere;
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;

    public String getString() {
        return string.get();
    }

    public void setString(String value) {
        string.set(value);
    }

    public StringProperty stringProperty() {
        return string;
    }
    public  static boolean logincheck=false;  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
    }    

    public boolean filedvalidate()
    {
        if(username.equals(" ") || password.equals(" "))
        {
            Alert alert =new Alert(AlertType.WARNING);
                        alert.setTitle("Information");
                        alert.setHeaderText("Check");
                        alert.setContentText("Fields cannot be empty");
                        alert.showAndWait();
                        return false;
        }
        return true;
    }
    @FXML
   private void Clickhere(MouseEvent event) throws IOException, SQLException {
       
                 
                    if(filedvalidate())
                    {                
            String sql ="Select * from admininfo where username=? and password=?";
        try{
                pst=conn.prepareStatement(sql);
                pst.setString(1,username.getText());
                pst.setString(2,password.getText());
                rs=pst.executeQuery();
            if(rs.next())
            {
                        logincheck=false;
                        Alert alert =new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText("Welcome Admin");
                        alert.setContentText("Login succusfully");
                        alert.showAndWait();
                        Login.setVisible(false);
                     Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/login/view/Home.fxml")));
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    stage.show();
            }
            else if(true)
            {
                String user ="Select * from userinfo where username=? and password=?";
        try{
                pst=conn.prepareStatement(user);
                pst.setString(1,username.getText());
                pst.setString(2,password.getText());
                rs=pst.executeQuery();
            if(rs.next())
            {
                        logincheck=true;
                        Alert alert =new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText("Welcome User");
                        alert.setContentText("Login succusfully");
                        alert.showAndWait();
                        Login.setVisible(false);
                     Parent root =new HelperViewsResources().loadFXML("Home");
                      Stage stage=new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Home");
                        stage.show();
            } 
            else
            {
                        Alert alert =new Alert(AlertType.WARNING);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("User name or password worong");
                        alert.showAndWait();
            }
            } 
                catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,ex);
            } 
            }
        }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,ex);
            }          
    }  
}

    public LoginController() {
    }

    public static boolean isLogincheck() {
        return logincheck;
    }

    public static void setLogincheck(boolean logincheck) {
        LoginController.logincheck = logincheck;
    }
    
}
