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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import login.Helper.MysqlConnect;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class UpdateEmployeeDetailsController implements Initializable {

    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField fathername;
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
    private JFXComboBox<String> bloodgroup;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField city;
    @FXML
    private JFXTextField province;
    @FXML
    private JFXComboBox<String> gender;
    @FXML
    private JFXTextField mrollnumber;
    @FXML
    private JFXTextField mgrade;
    @FXML
    private JFXTextField minstitute;
    @FXML
    private JFXDatePicker myearofpassing;
    @FXML
    private JFXTextField irollnumber;
    @FXML
    private JFXTextField igrade;
    @FXML
    private JFXTextField iinstitute;
    @FXML
    private JFXDatePicker iyearofpassing;
    @FXML
    private JFXTextField mmajorsubject;
    @FXML
    private JFXTextField imajorsubject;
    @FXML
    private JFXTextField grollnumber;
    @FXML
    private JFXTextField ggrade;
    @FXML
    private JFXTextField ginstitute;
    @FXML
    private JFXDatePicker gyearofpassing;
    @FXML
    private JFXTextField gmajorsubject;
    @FXML
    private JFXTextField orollnumber;
    @FXML
    private JFXTextField ograde;
    @FXML
    private JFXTextField oinstitute;
    @FXML
    private JFXDatePicker oyearofpassing;
    @FXML
    private JFXTextField omajorsubject;
    @FXML
    private JFXTextField r1name;
    @FXML
    private JFXTextField r1relationship;
    @FXML
    private JFXTextField r1occupation;
    @FXML
    private JFXTextField r2name;
    @FXML
    private JFXTextField r2relationship;
    @FXML
    private JFXTextField r2phonenumber;
    @FXML
    private JFXTextField r2occupation;
    @FXML
    private JFXTextField post;
    @FXML
    private JFXDatePicker joingdate;
    @FXML
    private JFXDatePicker leavingdate;
    @FXML
    private Button clear;
    @FXML
    private TextField remarks;
    @FXML
    private JFXTextField scnic;
    @FXML
    private JFXTextField r1phoneno;
    @FXML
    private JFXTextField R1CNIC;
    @FXML
    private JFXTextField R2CNIC;
    @FXML
    private JFXComboBox<String> staffstatus;
    @FXML
    private Button save;
    @FXML
    private Pane pane;
    @FXML
    private ImageView image;
    @FXML
    private Button uploadpicture;
     DateTimeFormatter formatter=DateTimeFormatter.ofPattern("M/d/yyyy");
     LocalDate date= LocalDate.parse("1/1/1900", formatter);
    /**
     * Initializes the controller class.
     */
    ObservableList<String> bloodgrouplist =FXCollections.observableArrayList("A+","B+","O+","AB+","AB-","B-","O-");
    ObservableList<String> genderlist =FXCollections.observableArrayList("Male","Female");
    ObservableList<String> statuslist =FXCollections.observableArrayList("Married","Single");
    ObservableList<String> Employeestatuslist =FXCollections.observableArrayList("Unactive","Active","Clear");
     Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    List<String> lastfile;
    FileChooser fc ;
    private Image imageslect;
    @FXML
    private JFXTextField EmployeeId;
    @FXML
    private JFXComboBox<String> personalstatus;
    private FileInputStream fis;
    File f;
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
    private void ActiononEmployeeId(ActionEvent event) throws IOException {
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT *  from employeeinfo where employeeid like('"+EmployeeId.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                firstname.setText(rs.getString("FirstName"));
                fathername.setText(rs.getString("LastName"));
                scnic.setText(rs.getString("CNIC"));
                occupation.setText(rs.getString("FatherOccupation"));
                cellno.setText(rs.getString("cellno"));
                telephone.setText(rs.getString("Telephone"));
                address.setText(rs.getString("Address"));
                city.setText(rs.getString("City"));
                province.setText(rs.getString("Province"));
                email.setText(rs.getString("Email"));
               personalstatus.setValue(rs.getString("MarriedStatus"));
                remarks.setText(rs.getString("Remarks"));
                gender.setValue(rs.getString("Gender"));
                bloodgroup.setValue(rs.getString("BloodGroup"));
                post.setText(rs.getString("Post"));
                staffstatus.setValue(rs.getString("EmployeeStatus"));
                DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-M-d");
                LocalDate DOB= LocalDate.parse(rs.getString("DateofBirth"), formatter);
                dob.setValue(DOB);
                LocalDate DOJ= LocalDate.parse(rs.getString("JoiningDate"), formatter);
                joingdate.setValue(DOJ);
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
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT *  from employeereferences where employeeid like('"+EmployeeId.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                r1name.setText(rs.getString("RoName"));
                r1relationship.setText(rs.getString("RoRelationship"));
                r1phoneno.setText(rs.getString("RoPhoneNo"));
                r1occupation.setText(rs.getString("RoOccupation"));
                R1CNIC.setText(rs.getString("RoCnic"));
                r2name.setText(rs.getString("RtName"));
                r2relationship.setText(rs.getString("RtRelationship"));
                r2phonenumber.setText(rs.getString("RtPhoneNo"));
                r2occupation.setText(rs.getString("RtOccupation"));
                R2CNIC.setText(rs.getString("RtCnic"));
            } 
            stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT *  from employeequalification where employeeid like('"+EmployeeId.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                mrollnumber.setText(rs.getString("MatricRollNo"));
                mgrade.setText(rs.getString("MatricGrade"));
                mmajorsubject.setText(rs.getString("MatricSubject"));
                minstitute.setText(rs.getString("MatricIns"));
                irollnumber.setText(rs.getString("InterRollNo"));
                igrade.setText(rs.getString("InterGrade"));
                imajorsubject.setText(rs.getString("InterSubject"));
                iinstitute.setText(rs.getString("InterIns"));
                grollnumber.setText(rs.getString("GraduationRollNo"));
                ggrade.setText(rs.getString("GraduationGrade"));
                gmajorsubject.setText(rs.getString("GraduationSubject"));
                ginstitute.setText(rs.getString("GraduationIns"));
                orollnumber.setText(rs.getString("OtherRollNo"));
                ograde.setText(rs.getString("OtherGrade"));
                omajorsubject.setText(rs.getString("OtherSubject"));
                oinstitute.setText(rs.getString("OtherIns"));
                DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-M-d");
                LocalDate MYear= LocalDate.parse(rs.getString("Matricyear"), formatter);
                myearofpassing.setValue(MYear);
                LocalDate IYear= LocalDate.parse(rs.getString("Interyear"), formatter);
                iyearofpassing.setValue(IYear);
                LocalDate GYear= LocalDate.parse(rs.getString("Graduationyear"), formatter);
                gyearofpassing.setValue(GYear);
                LocalDate OYear= LocalDate.parse(rs.getString("Otheryear"), formatter);
                oyearofpassing.setValue(OYear);
            } 
            stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    @FXML
    private void SaveButtonHandling(MouseEvent event) throws FileNotFoundException {
        if(emptyfieldvalidate())
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Add Student");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                fis=new FileInputStream(f);
                String employeeinfo ="Update employeeinfo set employeeid=?,FirstName=?,LastName=?,CNIC=?,FatherOccupation=?,Email=?,CellNo=?,Telephone=?,DateofBirth=?,BloodGroup=?,gender=?,Address=?,City=?,Province=?,MarriedStatus=?,Post=?,EmployeeStatus=?,"
                + "JoiningDate=?,Remarks=?,DataStatus=? , LeavingDate=? ,Image=? where employeeid like('"+EmployeeId.getText()+"%')";
        String employeequalifiction ="Update employeequalification set employeeid=?,MatricRollNo=?,MatricGrade=?,MatricSubject=?,MatricIns=?,Matricyear=?,InterRollNo=?,InterGrade=?,InterSubject=?,InterIns=?,Interyear=?,GraduationRollNo=?,GraduationGrade=?,GraduationSubject=?,GraduationIns=?,Graduationyear=?,OtherRollNo=?,"
                + "OtherGrade=?,OtherSubject=?,OtherIns=?,Otheryear=? where employeeid like('"+EmployeeId.getText()+"%')";
        String employeereference ="Update employeereferences set RoName=?,RoRelationship=?,RoPhoneNo=?,RoOccupation=?,RoCnic=?,RtName=?,RtRelationship=?,RtPhoneNo=?,RtOccupation=?,RtCnic=?,employeeid=? where employeeid like('"+EmployeeId.getText()+"%')";
        try{
                pst=conn.prepareStatement(employeeinfo);
                pst.setString(1,EmployeeId.getText());
                pst.setString(2,firstname.getText());
                pst.setString(3,fathername.getText());
                pst.setString(4,scnic.getText());
                pst.setString(5,occupation.getText());
                pst.setString(6,email.getText());
                pst.setString(7,cellno.getText());
               if(telephone.getText().isEmpty())
                    pst.setString(8,"null");
                else
                    pst.setString(8,telephone.getText());
                pst.setDate(9,java.sql.Date.valueOf(dob.getValue()));
                pst.setString(10,bloodgroup.getValue());
                pst.setString(11,gender.getValue());
                pst.setString(12,address.getText());
                pst.setString(13,city.getText());
                pst.setString(14,province.getText());
                pst.setString(15,personalstatus.getValue());
                pst.setString(16,post.getText());
                pst.setString(17,staffstatus.getValue());
                pst.setDate(18,java.sql.Date.valueOf(joingdate.getValue()));
                pst.setString(19,remarks.getText());
                pst.setBinaryStream(22,(InputStream)fis,(int)f.length());
                pst.setString(20,"Employee");
                pst.setDate(21,java.sql.Date.valueOf(date));
          pst.executeUpdate();
          pst.close();
               pst=conn.prepareStatement(employeequalifiction);
                    pst.setString(1,EmployeeId.getText());
                if(mrollnumber.getText().isEmpty())
                    pst.setString(2,"null");
                else
                    pst.setString(2,mrollnumber.getText());
                if(mgrade.getText().isEmpty())
                    pst.setString(3,"null");
                else
                    pst.setString(3,mgrade.getText());
                if(mmajorsubject.getText().isEmpty())
                    pst.setString(4,"null");
                else
                    pst.setString(4,mmajorsubject.getText());
                if(minstitute.getText().isEmpty())
                    pst.setString(5,"null");
                else
                     pst.setString(5,minstitute.getText());
                pst.setDate(6,java.sql.Date.valueOf(date));
                if(irollnumber.getText().isEmpty())
                    pst.setString(7,"null");
                else
                    pst.setString(7,irollnumber.getText());
                if(igrade.getText().isEmpty())
                    pst.setString(8,"null");
                else
                    pst.setString(8,igrade.getText());
                if(imajorsubject.getText().isEmpty())
                    pst.setString(9,"null");
                else
                    pst.setString(9,imajorsubject.getText());
                if(iinstitute.getText().isEmpty())
                    pst.setString(10,"null");
                else
                     pst.setString(10,iinstitute.getText());
                pst.setDate(11,java.sql.Date.valueOf(date));
                if(grollnumber.getText().isEmpty())
                    pst.setString(12,"null");
                else
                    pst.setString(12,grollnumber.getText());
                if(ggrade.getText().isEmpty())
                    pst.setString(13,"null");
                else
                    pst.setString(13,ggrade.getText());
                if(gmajorsubject.getText().isEmpty())
                    pst.setString(14,"null");
                else
                    pst.setString(14,gmajorsubject.getText());
                if(ginstitute.getText().isEmpty())
                    pst.setString(15,"null");
                else
                     pst.setString(15,ginstitute.getText());
                pst.setDate(16,java.sql.Date.valueOf(date));
                if(orollnumber.getText().isEmpty())
                    pst.setString(17,"null");
                else
                    pst.setString(17,orollnumber.getText());
                if(ograde.getText().isEmpty())
                    pst.setString(18,"null");
                else
                    pst.setString(18,ograde.getText());
                if(omajorsubject.getText().isEmpty())
                    pst.setString(19,"null");
                else
                    pst.setString(19,omajorsubject.getText());
                if(oinstitute.getText().isEmpty())
                    pst.setString(20,"null");
                else
                     pst.setString(20,oinstitute.getText());
                pst.setDate(21,java.sql.Date.valueOf(date));
            pst.executeUpdate();
            pst.close();
                   pst=conn.prepareStatement(employeereference);
                   if(r1name.getText().isEmpty())
                       pst.setString(1,"null");
                   else
                       pst.setString(1,r1name.getText());
                   if(r1relationship.getText().isEmpty())
                       pst.setString(2,"null");
                   else
                       pst.setString(2,r1relationship.getText());
                   if(r1phoneno.getText().isEmpty())
                       pst.setString(3,"null");
                   else
                       pst.setString(3,r1phoneno.getText());
                   if(r1occupation.getText().isEmpty())
                       pst.setString(4,"null");
                   else
                       pst.setString(4,r1occupation.getText());
                   if(R1CNIC.getText().isEmpty())
                       pst.setString(5,"null");
                   else
                       pst.setString(5,R1CNIC.getText());
                   if(r2name.getText().isEmpty())
                       pst.setString(6,"null");
                   else
                       pst.setString(6,r2name.getText());
                   if(r2relationship.getText().isEmpty())
                       pst.setString(7,"null");
                   else
                       pst.setString(7,r2relationship.getText());
                   if(r2phonenumber.getText().isEmpty())
                       pst.setString(8,"null");
                   else
                       pst.setString(8,r2phonenumber.getText());
                   if(r2occupation.getText().isEmpty())
                       pst.setString(9,"null");
                   else
                       pst.setString(9,r2occupation.getText());
                   if(R2CNIC.getText().isEmpty())
                       pst.setString(10,"null");
                   else
                       pst.setString(10,R2CNIC.getText());
                   pst.setString(11,EmployeeId.getText());
      int row=pst.executeUpdate();
                if(row==1)
                {
                    System.out.println("come");
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
    private void ClearButtonHandling(KeyEvent event) {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Clear the data");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                firstname.clear();
                fathername.clear();
                scnic.clear();
                occupation.clear();
                cellno.clear();
                telephone.clear();
                address.clear();
                city.clear();
                province.clear();
                mrollnumber.clear();
                mgrade.clear();
                mmajorsubject.clear();
                minstitute.clear();
                irollnumber.clear();
                igrade.clear();
                imajorsubject.clear();
                iinstitute.clear();
                grollnumber.clear();
                ggrade.clear();
                gmajorsubject.clear();
                ginstitute.clear();
                orollnumber.clear();
                ograde.clear();
                omajorsubject.clear();
                oinstitute.clear();
                r1name.clear();
                r1relationship.clear();
                r1phoneno.clear();
                r1occupation.clear();
                R1CNIC.clear();
                r2name.clear();
                r2relationship.clear();
                r2phonenumber.clear();
                r2occupation.clear();
                R2CNIC.clear();
                EmployeeId.clear();
                remarks.clear();
                post.clear();
            }
    }
    public void addValuesincombobox()
    {
         bloodgroup.setItems(bloodgrouplist);
        gender.setItems(genderlist);
        personalstatus.setItems(statuslist);
        staffstatus.setItems(Employeestatuslist);   
        
    }
    public boolean  emptyfieldvalidate()
    {
        if(firstname.getText().isEmpty() || fathername.getText().isEmpty() || scnic.getText().isEmpty() || occupation.getText().isEmpty() || 
                cellno.getText().isEmpty()  || address.getText().isEmpty() || city.getText().isEmpty() ||
                 province.getText().isEmpty()
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
    private void Matricyear(MouseEvent event) {
        date=myearofpassing.getValue();
    }

    @FXML
    private void interyear(MouseEvent event) {
        date=iyearofpassing.getValue();
    }

    @FXML
    private void graduationyear(MouseEvent event) {
        date=gyearofpassing.getValue();
    }

    @FXML
    private void otheryear(MouseEvent event) {
        date=oyearofpassing.getValue();
    }

    @FXML
    private void leavingyear(MouseEvent event) {
        date=leavingdate.getValue();
    }
    
}
