/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import login.Helper.HelperViewsResources;
import login.Helper.MysqlConnect;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class AddEmployeeSalaryController implements Initializable {

    @FXML
    private JFXTextField staffid;
    @FXML
    private JFXTextField staffname;
    @FXML
    private JFXTextField stafflastname;
    @FXML
    private JFXTextField scnic;
    @FXML
    private JFXTextField sphonenumber;
    @FXML
    private JFXTextField saddress;
    @FXML
    private JFXTextField scity;
    @FXML
    private JFXTextField sprovince;
    @FXML
    private JFXTextField sbloodgroup;
    @FXML
    private JFXTextField sjoiningdate;
    @FXML
    private JFXTextField steachinglevel;
    @FXML
    private JFXTextField sareaofintrest;
    @FXML
    private JFXTextField sworkinghours;
    @FXML
    private JFXTextField ssalary;
    @FXML
    private JFXTextField spost;
    @FXML
    private Button ssave;
    @FXML
    private Button sclear;
    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    @FXML
    private JFXDatePicker date;
    @FXML
    private Pane pane;
    @FXML
    private ImageView image;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        textfieldvalidator();
        // TODO
    }    

    @FXML
    private void StaffSaveButtonHandling(MouseEvent event) {
        if(emptyfieldvalidate())
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Add Salary");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                String studentinfo ="Insert into employeesalary (employeeid,EmployeeName,WorkingHours,salary,Date,post,Dateofjoing) values (?,?,?,?,?,?,?)";
                try{
                pst=conn.prepareStatement(studentinfo);
                pst.setString(1,staffid.getText());
                pst.setString(2,staffname.getText());
                pst.setInt(3,Integer.parseInt(sworkinghours.getText()));
                pst.setInt(4,Integer.parseInt(ssalary.getText()));
                pst.setDate(5,java.sql.Date.valueOf(date.getValue()));
                pst.setString(6,spost.getText());
                pst.setString(7,sjoiningdate.getText());
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
    private void StaffClearButtonHandling(MouseEvent event) {
    }
    public boolean  emptyfieldvalidate()
    {
        if(staffid.getText().isEmpty() 
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
    public void textfieldvalidator()
    {
        RequiredFieldValidator validator=new RequiredFieldValidator();
        NumberValidator numValidate =new NumberValidator();
        ssalary.getValidators().add(numValidate);
        sworkinghours.getValidators().add(numValidate);    
        numValidate.setMessage("Only digits");
        ssalary.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                     ssalary.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
        sworkinghours.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                     sworkinghours.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );    
    }

    @FXML
    private void EmployeeIdAction(ActionEvent event) throws IOException {
        try (
               
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeinfo where employeeid like('"+staffid.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                         staffname.setText(rs.getString("FirstName"));
                         stafflastname.setText(rs.getString("LastName"));
                         scnic.setText(rs.getString("CNIC"));
                            sphonenumber.setText(rs.getString("CellNo"));
                           saddress.setText(rs.getString("Address"));
                            scity.setText(rs.getString("City"));
                            sprovince.setText(rs.getString("Province"));
                            sbloodgroup.setText(rs.getString("BloodGroup"));
                            sjoiningdate.setText(rs.getString("JoiningDate"));
                            steachinglevel.setText(rs.getString("LevelOfTeaching"));
                            sareaofintrest.setText(rs.getString("AreaOfIntrest"));
                           spost.setText(rs.getString("Post"));
                           byte[] fileBytes=rs.getBytes("Image");
            pane.getChildren().clear();
            String path="E:\\SchoolManagementSystem\\src\\login\\images\\output.jpg";
            ByteArrayInputStream bis = new ByteArrayInputStream(fileBytes);
            BufferedImage bImage2 = ImageIO.read(bis);
            File f=new File(path);
            ImageIO.write(bImage2, "jpg", f );
            image.setImage(new Image(f.toURI().toString()));
            image.setFitHeight(130);
            image.setFitWidth(130);
            image.setPreserveRatio(true);
            pane.getChildren().add(image);
            }   
                            staffname.setEditable(false);
                         stafflastname.setEditable(false);
                         scnic.setEditable(false);
                            sphonenumber.setEditable(false);
                           saddress.setEditable(false);
                            scity.setEditable(false);
                            sprovince.setEditable(false);
                            sbloodgroup.setEditable(false);
                            sjoiningdate.setEditable(false);
                            steachinglevel.setEditable(false);
                            sareaofintrest.setEditable(false);
                           spost.setEditable(false);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ViewClickedButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("Viewpayrollemployeeinfo");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Employee Salaries");
               stage.show();
    }
    
}
