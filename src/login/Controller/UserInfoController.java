/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import login.Helper.MysqlConnect;

/**
 * FXML Controller class
 *
 * @author Jahannzaib Tayyab
 */
public class UserInfoController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXTextField userid;
    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField lastname;

    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    List<String> lastfile;
    FileChooser fc ;
     private FileInputStream fis;
    File f;
    private Image imageslect;
    @FXML
    private Pane layout;
    @FXML
    private ImageView image;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lastfile=new ArrayList<>();
        lastfile.add("*.jpg");
        lastfile.add("*.png");
        conn=MysqlConnect.ConnectDB();
        // TODO
    }    

    @FXML
    private void UploadButtonHandling(MouseEvent event) {
        fc=new FileChooser();
         fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Select Picture",lastfile));
          f=fc.showOpenDialog(null);
         if(f!=null)
         {
            layout.getChildren().clear();
            imageslect= new Image(f.toURI().toString(),130,130,true,true);
            image=new ImageView(imageslect);
           
            image.setFitHeight(130);
            image.setFitWidth(130);
            image.setPreserveRatio(true);
            layout.getChildren().add(image);
        }
    }

    @FXML
    private void UpdateButtonHandling(MouseEvent event) throws FileNotFoundException {
        if(emptyfieldvalidate())
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Update ");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                fis=new FileInputStream(f);
                String studentinfo ="Update userinfo  set  UserID=?,Name=?,LName=?,username=?,password=?,Image=?";
                try{
                pst=conn.prepareStatement(studentinfo);
                pst.setString(1,userid.getText());
                pst.setString(2,firstname.getText());
                pst.setString(3,lastname.getText());
                pst.setString(4,username.getText());
                pst.setString(5,password.getText());
                pst.setBinaryStream(6,(InputStream)fis,(int)f.length());
          int check=pst.executeUpdate();
          pst.close();
                    if(check==1)
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
            }}   
    }

    @FXML
    private void idaction(ActionEvent event) throws IOException {
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT *  from userinfo where UserID like('"+userid.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                firstname.setText(rs.getString("Name"));
                lastname.setText(rs.getString("LName"));
                username.setText(rs.getString("username"));
                password.setText(rs.getString("password"));
                byte[] fileBytes=rs.getBytes("Image");
            layout.getChildren().clear();
            String path="E:\\SchoolManagementSystem\\src\\login\\images\\output.jpg";
            ByteArrayInputStream bis = new ByteArrayInputStream(fileBytes);
            BufferedImage bImage2 = ImageIO.read(bis);
             f=new File(path);
            ImageIO.write(bImage2, "jpg", f );
            image.setImage(new Image(f.toURI().toString()));
            image.setFitHeight(130);
            image.setFitWidth(130);
            image.setPreserveRatio(true);
            layout.getChildren().add(image);
            } 
            stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    @FXML
    private void AddButtonHandling(MouseEvent event) throws FileNotFoundException {
        if(emptyfieldvalidate())
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Add User");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                fis=new FileInputStream(f);
                String studentinfo ="Insert into userinfo values (?,?,?,?,?,?)";
                try{
                pst=conn.prepareStatement(studentinfo);
                pst.setString(1,userid.getText());
                pst.setString(2,firstname.getText());
                pst.setString(3,lastname.getText());
                pst.setString(4,username.getText());
                pst.setString(5,password.getText());
                pst.setBinaryStream(6,(InputStream)fis,(int)f.length());
          int check=pst.executeUpdate();
          pst.close();
                    if(check==1)
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
            }}
        
    }
    public boolean  emptyfieldvalidate()
    {
        if(firstname.getText().isEmpty() || username.getText().isEmpty() || password.getText().isEmpty() || userid.getText().isEmpty())
             
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
