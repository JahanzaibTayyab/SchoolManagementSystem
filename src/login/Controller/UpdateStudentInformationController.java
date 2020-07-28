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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import login.Helper.MysqlConnect;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class UpdateStudentInformationController implements Initializable {

    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField fathername;
    @FXML
    private JFXTextField bform;
    @FXML
    private JFXTextField fathercnic;
    @FXML
    private JFXTextField occupation;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField cellno;
    @FXML
    private JFXTextField telephone;
    @FXML
    private JFXDatePicker dob;
    @FXML
    private ComboBox<String> bloodgroup;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField city;
    @FXML
    private JFXTextField province;
    @FXML
    private JFXTextField studentid;
    @FXML
    private JFXTextField reference;
    @FXML
    private JFXTextField remarks;
    @FXML
    private JFXComboBox<String> section;
    @FXML
    private JFXDatePicker dateofadmission;
    @FXML
    private Button save;
    @FXML
    private Button print;
    @FXML
    private Button clear;
    @FXML
    private Button upload;
    @FXML
    private Pane pane;
    @FXML
    private ImageView image;
     Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    List<String> lastfile;
    FileChooser fc ;
    private Image imageslect;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private ComboBox<String> selectclass;
    @FXML
    private ComboBox<String> status;
    private FileInputStream fis;
    File f;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> bloodgrouplist =FXCollections.observableArrayList("A+","B+","O+","AB+","AB-","B-","O-");
    ObservableList<String> genderlist =FXCollections.observableArrayList("Male","Female");
    ObservableList<String> statuslist =FXCollections.observableArrayList("Passout","Clear","Defaulter","Student");
    ObservableList<String> classlist =FXCollections.observableArrayList();
    ObservableList<String> sectionlist =FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        lastfile=new ArrayList<>();
        lastfile.add("*.jpg");
        lastfile.add("*.png");
        comboboxvalues();
         textfieldvalidator();
        // TODO
    }    
        
    @FXML
    private void UploadpictureButtonHandling(MouseEvent event) {
        fc=new FileChooser();
         fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Select Picture",lastfile));
          f=fc.showOpenDialog(null);
         if(f!=null)
         {
            pane.getChildren().clear();
            imageslect= new Image(f.toURI().toString(),130,130,true,true);
            image=new ImageView(imageslect);
           
            image.setFitHeight(130);
            image.setFitWidth(130);
            image.setPreserveRatio(true);
            pane.getChildren().add(image);
        }
    }

    @FXML
    private void UpdateStudenButtonHandling(MouseEvent event) throws FileNotFoundException {
        
        if(emptyfieldvalidate())
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Update Student");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                fis=new FileInputStream(f);
                String studentinfo ="Update studentinfo set studentid=?,FirstName=?,LastName=?,Bform=?,Cnic=?,Occupation=?,Email=?,cellno=?"
                        + ",Telephone=?,Gender=?,DateofBirth=?,BloodGroup=?,Address=?,City=?,Province=?,DateofAdmission=?,Class=?,Section=?"
                        + ",Reference=?,Remarks=?,Status=?,Image=? where studentid like('"+studentid.getText()+"%')";
                try{
                pst=conn.prepareStatement(studentinfo);
                pst.setString(1,studentid.getText());
                pst.setString(2,firstname.getText());
                pst.setString(3,fathername.getText());
                pst.setString(4,bform.getText());
                pst.setString(5,fathercnic.getText());
                pst.setString(6,occupation.getText());
                pst.setString(7,email.getText());
                pst.setString(8,cellno.getText());
               if(telephone.getText().isEmpty())
                    pst.setString(9,"null");
                else
                    pst.setString(9,telephone.getText());
                pst.setString(10,gender.getValue());
                pst.setDate(11,java.sql.Date.valueOf(dob.getValue()));
                pst.setString(12,bloodgroup.getValue());
                pst.setString(13,address.getText());
                pst.setString(14,city.getText());
                pst.setString(15,province.getText());
                pst.setDate(16,java.sql.Date.valueOf(dateofadmission.getValue()));
                pst.setString(17,selectclass.getValue());
                pst.setString(18,section.getValue());
                pst.setString(19,reference.getText());
                pst.setString(20,remarks.getText());
                pst.setString(21,status.getValue());
                pst.setBinaryStream(22,(InputStream)fis,(int)f.length());
          pst.executeUpdate();
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
    private void ClearStudentButtonHandling(MouseEvent event) {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Clear the data");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                firstname.clear();
                fathername.clear();
                bform.clear();
                fathercnic.clear();
                occupation.clear();
                cellno.clear();
                telephone.clear();
                address.clear();
                city.clear();
                province.clear();
            }
    }
     @FXML
    private void ClassComboBoxAction(ActionEvent event) {
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Section  from classinfo where ClassName like('"+selectclass.getValue()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                sectionlist.add(rs.getString("Section"));
            }
            section.setItems(sectionlist);  
            stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }  
    }
    public void comboboxvalues()
    {
        bloodgroup.setItems(bloodgrouplist);
        gender.setItems(genderlist);
        status.setItems(statuslist);
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT ClassName  from classinfo ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                classlist.add(rs.getString("ClassName"));
            }
            selectclass.setItems(classlist);  
            stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }  
    }
    
    public boolean  emptyfieldvalidate()
    {
        if(firstname.getText().isEmpty() || fathername.getText().isEmpty() || bform.getText().isEmpty() || fathercnic.getText().isEmpty() || occupation.getText().isEmpty() || 
                cellno.getText().isEmpty() || address.getText().isEmpty() || city.getText().isEmpty() ||
                 province.getText().isEmpty() || studentid.getText().isEmpty() || reference.getText().isEmpty())
             
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
        cellno.getValidators().add(numValidate);
        telephone.getValidators().add(numValidate);
        
                
        numValidate.setMessage("Only digits");
        cellno.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                     cellno.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
         telephone.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                      telephone.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );  
    }  

    @FXML
    private void StudentIdAction(ActionEvent event) throws ParseException, IOException {
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT *  from studentinfo where studentid like('"+studentid.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                firstname.setText(rs.getString("FirstName"));
                fathername.setText(rs.getString("LastName"));
                bform.setText(rs.getString("Bform"));
                fathercnic.setText(rs.getString("Cnic"));
                occupation.setText(rs.getString("Occupation"));
                cellno.setText(rs.getString("cellno"));
                telephone.setText(rs.getString("Telephone"));
                address.setText(rs.getString("Address"));
                city.setText(rs.getString("City"));
                province.setText(rs.getString("Province"));
                email.setText(rs.getString("Email"));
                selectclass.setValue(rs.getString("Class"));
                section.setValue(rs.getString("Section"));
                reference.setText(rs.getString("Reference"));
                remarks.setText(rs.getString("Status"));
                status.setValue(rs.getString("Status"));
                gender.setValue(rs.getString("Gender"));
                bloodgroup.setValue(rs.getString("BloodGroup"));
                DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-M-d");
                LocalDate DOB= LocalDate.parse(rs.getString("DateofBirth"), formatter);
                dob.setValue(DOB);
                LocalDate DOA= LocalDate.parse(rs.getString("DateofAdmission"), formatter);
                dateofadmission.setValue(DOA);
                byte[] fileBytes=rs.getBytes("Image");
            pane.getChildren().clear();
            String path="E:\\SchoolManagementSystem\\src\\login\\images\\output.jpg";
            ByteArrayInputStream bis = new ByteArrayInputStream(fileBytes);
            BufferedImage bImage2 = ImageIO.read(bis);
             f=new File(path);
            ImageIO.write(bImage2, "jpg", f );
            image.setImage(new Image(f.toURI().toString()));
            image.setFitHeight(130);
            image.setFitWidth(130);
            image.setPreserveRatio(true);
            pane.getChildren().add(image);
            } 
            stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }  
    }

    @FXML
    private void printactionbutton(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\StudentsForms.jrxml");
            String qry ="SELECT\n" +
"     studentinfo.`studentid` AS studentinfo_studentid,\n" +
"     studentinfo.`FirstName` AS studentinfo_FirstName,\n" +
"     studentinfo.`LastName` AS studentinfo_LastName,\n" +
"     studentinfo.`Bform` AS studentinfo_Bform,\n" +
"     studentinfo.`Cnic` AS studentinfo_Cnic,\n" +
"     studentinfo.`Occupation` AS studentinfo_Occupation,\n" +
"     studentinfo.`Email` AS studentinfo_Email,\n" +
"     studentinfo.`cellno` AS studentinfo_cellno,\n" +
"     studentinfo.`Telephone` AS studentinfo_Telephone,\n" +
"     studentinfo.`Gender` AS studentinfo_Gender,\n" +
"     studentinfo.`DateofBirth` AS studentinfo_DateofBirth,\n" +
"     studentinfo.`BloodGroup` AS studentinfo_BloodGroup,\n" +
"     studentinfo.`Address` AS studentinfo_Address,\n" +
"     studentinfo.`City` AS studentinfo_City,\n" +
"     studentinfo.`Province` AS studentinfo_Province,\n" +
"     studentinfo.`DateofAdmission` AS studentinfo_DateofAdmission,\n" +
"     studentinfo.`Class` AS studentinfo_Class,\n" +
"     studentinfo.`Section` AS studentinfo_Section,\n" +
"     studentinfo.`Reference` AS studentinfo_Reference,\n" +
"     studentinfo.`Remarks` AS studentinfo_Remarks,\n" +
"     studentinfo.`Status` AS studentinfo_Status,\n" +
"     studentinfo.`Image` AS studentinfo_Image,\n" +
"     studentinfo.`count` AS studentinfo_count\n" +
"FROM\n" +
"     `studentinfo` studentinfo  where studentinfo.`studentid` like('"+studentid.getText()+"%')";
            JRDesignQuery newQuery=new JRDesignQuery ();
            newQuery.setText(qry);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            HashMap para=new HashMap();
            JasperPrint jp=JasperFillManager.fillReport(jr,para, conn);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            Logger.getLogger(UpdateStudentInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
