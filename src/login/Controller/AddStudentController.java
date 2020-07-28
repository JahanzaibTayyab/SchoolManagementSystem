/*                                                                                                                                                           m,,,,,,,,,,,,,,,,,,,,,,,,,,,,,.km
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.swing.JOptionPane;
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
public class AddStudentController implements Initializable {

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
    private ComboBox<String> gender;
    @FXML
    private JFXTextField studentid;
    @FXML
    private ComboBox<String> classes;
    @FXML
    private JFXTextField reference;
    @FXML
    private JFXTextField remarks;
    @FXML
    private JFXComboBox<String> section;
    @FXML
    private JFXDatePicker dateofadmission;
    @FXML
    private ComboBox<String> status;
    @FXML
    private Button save;
    @FXML
    private Button print;
    @FXML
    private Button clear;
    private File file;
    @FXML
    private ImageView image;
    @FXML
    private Button upload;
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
    private Pane pane1;
    @FXML
    private AnchorPane mainpane;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> bloodgrouplist =FXCollections.observableArrayList("A+","B+","O+","AB+","AB-","B-","O-");
    ObservableList<String> genderlist =FXCollections.observableArrayList("Male","Female");
    ObservableList<String> statuslist =FXCollections.observableArrayList("Passout","Clear","Student");
    ObservableList<String> classlist =FXCollections.observableArrayList();
    ObservableList<String> sectionlist =FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        lastfile=new ArrayList<>();
        lastfile.add("*.jpg");
        lastfile.add("*.png");
        textfieldvalidator();
        addValuesincombobox();
        // TODO
    }    

    @FXML
    private void UploadpictureButtinACtion(ActionEvent event) {
         fc=new FileChooser();
         fc.getExtensionFilters().add(new ExtensionFilter("Select Picture",lastfile));
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
    private void AddStudentButtonHandling(MouseEvent event) throws FileNotFoundException{
        
        if(emptyfieldvalidate())
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Add Student");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                fis=new FileInputStream(f);
                String studentinfo ="Insert into studentinfo (studentid,FirstName,LastName,Bform,Cnic,Occupation,Email,cellno"
                        + ",Telephone,Gender,DateofBirth,BloodGroup,Address,City,Province,DateofAdmission,Class,Section"
                        + ",Reference,Remarks,Status,Image)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
                pst.setString(17,classes.getValue());
                pst.setString(18,section.getValue());
                pst.setString(19,reference.getText());
                pst.setString(20,remarks.getText());
                pst.setString(21,status.getValue());
                pst.setBinaryStream(22,(InputStream)fis,(int)f.length());
          int check=pst.executeUpdate();
          pst.close();
                    if(check==1)
                        {
                            insertintofee();
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
    private void ClassComboBoxAction(ActionEvent event) {
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Section  from classinfo where ClassName like('"+classes.getValue()+"%')";
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
    public void addValuesincombobox()
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
            classes.setItems(classlist);  
            stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }    
    }
    public void insertintofee()
    {
        int classfee=0,generatorfee=0,functionfee=0,examsfee=0,bookfee=0,partyfee=0,sportsfee=0,tripfee=0,othersfee=0,stationaryfee=0,totaldues=0,month,year;
        String monthname=" ",yearname;
        try (    
                
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from classfee where ClassName like('"+classes.getValue()+"%') and Section like ('"+section.getValue()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                    classfee=Integer.parseInt(rs.getString("ClassFee"));
                    generatorfee=Integer.parseInt(rs.getString("GeneratorFee"));
                    functionfee=Integer.parseInt(rs.getString("FunctionFee"));
                    examsfee=Integer.parseInt(rs.getString("ExamsFee"));
                    bookfee=Integer.parseInt(rs.getString("BooksFee"));
                    partyfee=Integer.parseInt(rs.getString("PartyFee"));
                    sportsfee=Integer.parseInt(rs.getString("SportsFee"));
                    tripfee=Integer.parseInt(rs.getString("TripFee"));
                   othersfee=Integer.parseInt(rs.getString("OtherCharges"));
                   stationaryfee=Integer.parseInt(rs.getString("StationaryFee"));
            }
            stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        totaldues=classfee+generatorfee+functionfee+examsfee+bookfee+partyfee+sportsfee+tripfee+othersfee+stationaryfee;
        Calendar now = Calendar.getInstance();
        month=now.get(Calendar.MONTH);
        year=now.get(Calendar.YEAR);
        yearname=Integer.toString(year); 
        switch(month)
        {
            case 0:
                monthname="Jan";
                break;
            case 1:
                monthname="Feb";
                break;
            case 2:
                monthname="Mar";
                break;
            case 3:
                monthname="Apr";
                break;
            case 4:
                monthname="May";
                break;
            case 5:
                monthname="June";
                break;
            case 6:
                monthname="July";
                break;
            case 7:
                monthname="Aug";
                break;
            case 8:
                monthname="Sep";
                break;
            case 9:
                monthname="Oct";
                break;
            case 10:
                monthname="Nov";
                break;
            case 11:
                monthname="Dec";
                break;        
        }
        Random rn=new Random();
        int ad=rn.nextInt(999999999);
        String feeinfo ="Insert into feedetails (feecode,StudentID,StudentName,Month,Year,Classfee,GeneratorFee,FunctionFee,ExamsFee"
                + ",BooksFee,PartyFee,SportsFee,TripFee,StationaryFee,Others,TotalDues,Balance,ClassName,Section,Paid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                try{
                pst=conn.prepareStatement(feeinfo);
                pst.setInt(1,ad);
                pst.setString(2,studentid.getText());
                pst.setString(3,firstname.getText());
                pst.setString(4,monthname);
                pst.setString(5,yearname);
                pst.setInt(6,classfee);
                pst.setInt(7,generatorfee);
                pst.setInt(8,functionfee);
                pst.setInt(9,examsfee);
                pst.setInt(10,bookfee);
                pst.setInt(11,partyfee);
                pst.setInt(12,sportsfee);
                pst.setInt(13,tripfee);
                pst.setInt(14,stationaryfee);
                pst.setInt(15,othersfee);
                pst.setInt(16,totaldues);
                pst.setInt(17,totaldues);
                pst.setString(18,classes.getValue());
                 pst.setString(19,section.getValue());
                 pst.setInt(20,0);
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
    public boolean  emptyfieldvalidate()
    {
        if(firstname.getText().isEmpty() || fathername.getText().isEmpty() || bform.getText().isEmpty() || fathercnic.getText().isEmpty() || occupation.getText().isEmpty() || 
                cellno.getText().isEmpty()  || address.getText().isEmpty() || city.getText().isEmpty() ||
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
    private void ClearButtonHandling(MouseEvent event) {
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
    private void printbuttonhandling(MouseEvent event) {
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
