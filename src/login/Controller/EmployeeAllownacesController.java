/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
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
public class EmployeeAllownacesController implements Initializable {

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
    private JFXTextField overtime;
    @FXML
    private JFXTextField medical;
    @FXML
    private JFXTextField bounce;
    @FXML
    private JFXTextField homeallowance;
    @FXML
    private JFXTextField transportallownace;
    @FXML
    private JFXTextField Totalsalary;
    @FXML
    private JFXTextField others;
    @FXML
    private Button ssave;
    @FXML
    private Button sclear;
    @FXML
    private JFXTextField TotalSalary;
    @FXML
    private Button scalculate;
    @FXML
    private JFXComboBox<String> smonth;
    @FXML
    private JFXComboBox<String> syear;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> month =FXCollections.observableArrayList("Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec");
    ObservableList<String> year =FXCollections.observableArrayList("2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030");
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    String post,csalary;
    int total,totalsalary;
    @FXML
    private Button ssave1;
    @FXML
    private Pane pane;
    @FXML
    private ImageView image;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
         textfieldvalidator();
        addValuesincombobox();
        // TODO
    }  
    
    @FXML
    private void StaffsaveButtonHandling(MouseEvent event) {
        if(emptyfieldvalidate())
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Add Allowance");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                String studentinfo ="Insert into employeeallowance (employeeid,EmployeeName,Post,OverTime,Medical,Bounce,"
                        + "HomeAllowance,TransportAllowance,Others,TotalAllownace,Month,Year,TotalSalary) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                try{
                pst=conn.prepareStatement(studentinfo);
                pst.setString(1,staffid.getText());
                pst.setString(2,staffname.getText());
                pst.setString(3,post);
                pst.setInt(4,Integer.parseInt(overtime.getText()));
                pst.setInt(5,Integer.parseInt(medical.getText()));
                pst.setInt(6,Integer.parseInt(bounce.getText()));
                pst.setInt(7,Integer.parseInt(homeallowance.getText()));
                pst.setInt(8,Integer.parseInt(transportallownace.getText()));
                pst.setInt(9,Integer.parseInt(others.getText()));
                pst.setInt(10,total);
                pst.setString(11,smonth.getValue());
                pst.setString(12,syear.getValue());
                pst.setInt(13,totalsalary);
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
    private void StaffUpdateButtonHandling(MouseEvent event) {
        if(emptyfieldvalidate())
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Add Student");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                String studentinfo ="Update employeeallowance set employeeid=?,EmployeeName=?,Post=?,OverTime=?,Medical=?,Bounce=?,"
                        + "HomeAllowance=?,TransportAllowance=?,Others=?,TotalAllownace=?,Month=?,Year=?,TotalSalary=? where employeeid like('"+staffid.getText()+"%') and Month like('"+smonth.getValue()+"%') and Year like('"+syear.getValue()+"%')";
                try{
                pst=conn.prepareStatement(studentinfo);
                pst.setString(1,staffid.getText());
                pst.setString(2,staffname.getText());
                pst.setString(3,post);
                pst.setInt(4,Integer.parseInt(overtime.getText()));
                pst.setInt(5,Integer.parseInt(medical.getText()));
                pst.setInt(6,Integer.parseInt(bounce.getText()));
                pst.setInt(7,Integer.parseInt(homeallowance.getText()));
                pst.setInt(8,Integer.parseInt(transportallownace.getText()));
                pst.setInt(9,Integer.parseInt(others.getText()));
                pst.setInt(10,total);
                pst.setString(11,smonth.getValue());
                pst.setString(12,syear.getValue());
                pst.setInt(13,totalsalary);
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
    private void staffclearButtonHandling(MouseEvent event) {
    }

    @FXML
    private void StaffCalculateButtonHandling(MouseEvent event) {
        if(emptyfieldvalidate())
        {
        total=(Integer.parseInt(overtime.getText())+Integer.parseInt(medical.getText())+Integer.parseInt(bounce.getText())+
                Integer.parseInt(homeallowance.getText())+Integer.parseInt(transportallownace.getText())+Integer.parseInt(others.getText()));
        try (
               
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT salary from employeesalary where employeeid like('"+staffid.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                            csalary=(rs.getString("salary"));
            }   
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        totalsalary=total+Integer.parseInt(csalary);
        Totalsalary.setText(Integer.toString(total));
        TotalSalary.setText(Integer.toString(totalsalary));
        Totalsalary.setEditable(false);
        TotalSalary.setEditable(false);
    }
    }
    @FXML
    private void IDAction(ActionEvent event) throws IOException {
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
                            post=rs.getString("Post");
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
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void ViewActionHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("ViewpayrollemploAllownace");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Employee Allowances");
               stage.show();
    }
    public void addValuesincombobox()
    {
        smonth.setItems(month);
        syear.setItems(year);
        
    }
    public boolean  emptyfieldvalidate()
    {
        if(staffid.getText().isEmpty() || overtime.getText().isEmpty() || medical.getText().isEmpty() || bounce.getText().isEmpty()
              || homeallowance.getText().isEmpty() || transportallownace.getText().isEmpty() || others.getText().isEmpty()    )
             
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
        overtime.getValidators().add(numValidate);
        medical.getValidators().add(numValidate);
         bounce.getValidators().add(numValidate);
         homeallowance.getValidators().add(numValidate);
         transportallownace.getValidators().add(numValidate);
         Totalsalary.getValidators().add(numValidate);
         others.getValidators().add(numValidate);
         TotalSalary.getValidators().add(numValidate);    
        numValidate.setMessage("Only digits");
        overtime.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                    overtime.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
        medical.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                     medical.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
        bounce.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                    bounce.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
        homeallowance.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                    homeallowance.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
        transportallownace.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                    transportallownace.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
        Totalsalary.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                    Totalsalary.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
        others.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                    others.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
        TotalSalary.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                    TotalSalary.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );   
    }     
}
