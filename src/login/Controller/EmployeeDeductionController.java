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
public class EmployeeDeductionController implements Initializable {

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
    private Button ssave;
    @FXML
    private Button sclear;
    @FXML
    private Button scalculate;
    @FXML
    private ToggleGroup radiocheck;
    @FXML
    private JFXTextField sreason;
    @FXML
    private RadioButton spercentage;
    @FXML
    private JFXTextField spercentagebox;
    @FXML
    private JFXTextField Totalsalary;
    @FXML
    private JFXTextField samountbox;
    @FXML
    private JFXTextField salaryafterdeduction;
    @FXML
    private JFXComboBox<String> smonth;
    @FXML
    private JFXComboBox<String> syear;
    @FXML
    private RadioButton samount;
    
    /**
     * Initializes the controller class.
     */
    ObservableList<String> month =FXCollections.observableArrayList("Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec");
    ObservableList<String> year =FXCollections.observableArrayList("2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030");
     Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    String post;
    @FXML
    private Button ssave1;
    int totaldeducationamount,afetrdamount;
    String csalary;
    @FXML
    private Pane pane;
    @FXML
    private ImageView image;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        addValuesincombobox();
        textfieldvalidator();
        // TODO
    }    

    @FXML
    private void StaffSaveButtonHandling(MouseEvent event) {
        if(true)
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Add Deducation");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                String studentinfo ="Insert into employededucation (employeeid,EmployeeName,Post,DeductionAmount,"
                        + "SalaryAfterDeducation,Reason,Month,year)values (?,?,?,?,?,?,?,?)";
                try{
                pst=conn.prepareStatement(studentinfo);
                pst.setString(1,staffid.getText());
                pst.setString(2,staffname.getText());
                pst.setString(3,post);
                pst.setInt(4,totaldeducationamount);
                pst.setInt(5,afetrdamount);
                pst.setString(6,sreason.getText());
                pst.setString(7,smonth.getValue());
                pst.setString(8,syear.getValue());
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

    @FXML
    private void StaffCalculateButton(MouseEvent event) {
        if(spercentage.isSelected())
        {
            
            totaldeducationamount=(int)(Double.parseDouble(csalary)*Integer.parseInt(spercentagebox.getText()))/100;
            afetrdamount=Integer.parseInt(csalary)-totaldeducationamount;
            Totalsalary.setText(Integer.toString(totaldeducationamount));
            salaryafterdeduction.setText(Integer.toString(afetrdamount));
            Totalsalary.setEditable(false);
            salaryafterdeduction.setEditable(false);  
        }
        else if(samount.isSelected())
        {
            totaldeducationamount=Integer.parseInt(samountbox.getText());
            afetrdamount=Integer.parseInt(csalary)-totaldeducationamount;
            Totalsalary.setText(Integer.toString(totaldeducationamount));
            salaryafterdeduction.setText(Integer.toString(afetrdamount));
            Totalsalary.setEditable(false);
            salaryafterdeduction.setEditable(false);
        }
    }

    public void addValuesincombobox()
    {
        smonth.setItems(month);
        syear.setItems(year);
        
    }

    @FXML
    private void IDAction(ActionEvent event) throws SQLException, IOException {
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
    }
    @FXML
    private void PercentageButton(MouseEvent event) {
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
    }

    @FXML
    private void AmountButton(MouseEvent event) {
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
    }
    @FXML
    private void ViewButtonHandling(MouseEvent event) {
        Parent root =new HelperViewsResources().loadFXML("ViewpayrollemployDeducation");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Employee Deducations");
               stage.show();
    }
    @FXML
    private void StaffUpdateButtonHandling(MouseEvent event) {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Update Deducation");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                String studentinfo ="Update employededucation  set set employeeid=?,EmployeeName=?,Post=?,DeductionAmount=?,"
                        + "SalaryAfterDeducation=?,Reason=?,Month=?,year=? where employeeid like('"+staffid.getText()+"%') and Month like('"+smonth.getValue()+"%') and Year like('"+syear.getValue()+"%')";
                try{
                pst=conn.prepareStatement(studentinfo);
                pst.setString(1,staffid.getText());
                pst.setString(2,staffname.getText());
                pst.setString(3,post);
                pst.setInt(4,totaldeducationamount);
                pst.setInt(5,afetrdamount);
                pst.setString(6,sreason.getText());
                pst.setString(7,smonth.getValue());
                pst.setString(8,syear.getValue());
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
    public boolean  emptyfieldvalidate()
    {
        if(staffid.getText().isEmpty() || spercentagebox.getText().isEmpty() || samountbox.getText().isEmpty())
             
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
        spercentagebox.getValidators().add(numValidate);
        samountbox.getValidators().add(numValidate);  
        numValidate.setMessage("Only digits");
        spercentagebox.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                    spercentagebox.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
        samountbox.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                     samountbox.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );  
    } 

    
}
