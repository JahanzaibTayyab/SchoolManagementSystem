/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class EmployeePaymentController implements Initializable {

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
    private JFXTextField ssalary;
    @FXML
    private Button ssave;
    @FXML
    private Button sclear;
    @FXML
    private JFXComboBox<String> smonth;
    @FXML
    private JFXComboBox<String> syear;
    @FXML
    private Button generateslip;
    @FXML
    private JFXComboBox<String> sstatus;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> month =FXCollections.observableArrayList("Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec");
    ObservableList<String> year =FXCollections.observableArrayList("2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030");
    ObservableList<String> staus =FXCollections.observableArrayList("Pay","Advanced");
    @FXML
    private JFXTextField remaining;
    @FXML
    private JFXTextField totalallownace;
    @FXML
    private JFXTextField totaldeducation;
    @FXML
    private JFXTextField balance;
    @FXML
    private JFXTextField payable;
    @FXML
    private JFXTextField paidamount;
    @FXML
    private Button ssave1;
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    String post;
    int totaldeducationamount,allownace,balace,totalsalary,remain;
    String csalary;
    @FXML
    private Pane pane;
    @FXML
    private ImageView image;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        // TODO
        addValuesincombobox();
        generateslip.setDisable(true);
    }    

    @FXML
    private void StaffSaveButtonHandling(MouseEvent event) {
        if(true)
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Add Payment Information");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                generateslip.setDisable(false);
                String studentinfo ="Insert into employeepayment (employeeid,EmployeeName,Post,Salary,PaidSalry,Status,month,"
                        + "year,balance,TotalSalary)values (?,?,?,?,?,?,?,?,?,?)";
                try{
                pst=conn.prepareStatement(studentinfo);
                pst.setString(1,staffid.getText());
                pst.setString(2,staffname.getText());
                pst.setString(3,post);
                pst.setInt(4,Integer.parseInt(csalary));
                if(paidamount.getText().isEmpty())
                    pst.setInt(5,0);
                else
                    pst.setInt(5,Integer.parseInt(paidamount.getText()));
                pst.setString(6,sstatus.getValue());
                pst.setString(7,smonth.getValue());
                pst.setString(8,syear.getValue());
                pst.setInt(9,remain);
                pst.setInt(10,totalsalary);
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
    private void StaffclearButton(MouseEvent event) {
    }

    @FXML
    private void staffgenerateButton(MouseEvent event) {
        try {
            JasperDesign jd=JRXmlLoader.load("E:\\SchoolManagementSystem\\src\\login\\Reports\\EmployeeSalarySlip.jrxml");
            String qry ="SELECT\n" +
"     employeesalary.`employeeid` AS employeesalary_employeeid,\n" +
"     employeesalary.`EmployeeName` AS employeesalary_EmployeeName,\n" +
"     employeesalary.`WorkingHours` AS employeesalary_WorkingHours,\n" +
"     employeesalary.`salary` AS employeesalary_salary,\n" +
"     employeesalary.`Date` AS employeesalary_Date,\n" +
"     employeesalary.`post` AS employeesalary_post,\n" +
"     employeesalary.`Dateofjoing` AS employeesalary_Dateofjoing,\n" +
"     employededucation.`employeeid` AS employededucation_employeeid,\n" +
"     employededucation.`EmployeeName` AS employededucation_EmployeeName,\n" +
"     employededucation.`Post` AS employededucation_Post,\n" +
"     employededucation.`DeductionAmount` AS employededucation_DeductionAmount,\n" +
"     employededucation.`SalaryAfterDeducation` AS employededucation_SalaryAfterDeducation,\n" +
"     employededucation.`Reason` AS employededucation_Reason,\n" +
"     employededucation.`Month` AS employededucation_Month,\n" +
"     employededucation.`year` AS employededucation_year,\n" +
"     employeeallowance.`employeeid` AS employeeallowance_employeeid,\n" +
"     employeeallowance.`EmployeeName` AS employeeallowance_EmployeeName,\n" +
"     employeeallowance.`Post` AS employeeallowance_Post,\n" +
"     employeeallowance.`OverTime` AS employeeallowance_OverTime,\n" +
"     employeeallowance.`Medical` AS employeeallowance_Medical,\n" +
"     employeeallowance.`Bounce` AS employeeallowance_Bounce,\n" +
"     employeeallowance.`HomeAllowance` AS employeeallowance_HomeAllowance,\n" +
"     employeeallowance.`TransportAllowance` AS employeeallowance_TransportAllowance,\n" +
"     employeeallowance.`Others` AS employeeallowance_Others,\n" +
"     employeeallowance.`TotalAllownace` AS employeeallowance_TotalAllownace,\n" +
"     employeeallowance.`Month` AS employeeallowance_Month,\n" +
"     employeeallowance.`Year` AS employeeallowance_Year,\n" +
"     employeeallowance.`TotalSalary` AS employeeallowance_TotalSalary,\n" +
"     employeepayment.`employeeid` AS employeepayment_employeeid,\n" +
"     employeepayment.`EmployeeName` AS employeepayment_EmployeeName,\n" +
"     employeepayment.`Post` AS employeepayment_Post,\n" +
"     employeepayment.`Salary` AS employeepayment_Salary,\n" +
"     employeepayment.`PaidSalry` AS employeepayment_PaidSalry,\n" +
"     employeepayment.`Status` AS employeepayment_Status,\n" +
"     employeepayment.`month` AS employeepayment_month,\n" +
"     employeepayment.`year` AS employeepayment_year,\n" +
"     employeepayment.`balance` AS employeepayment_balance,\n" +
"     employeepayment.`TotalSalary` AS employeepayment_TotalSalary\n" +
"FROM\n" +
"     `employeesalary` employeesalary,\n" +
"     `employededucation` employededucation,\n" +
"     `employeeallowance` employeeallowance,\n" +
"     `employeepayment` employeepayment\n" +
"where employeesalary.`employeeid` like('"+staffid.getText()+"%')\n" +
"and   employededucation.`employeeid` like('"+staffid.getText()+"%')\n" +
"and   employededucation.`Month` like('"+smonth.getValue()+"%')\n" +
"and   employededucation.`year` like('"+syear.getValue()+"%')\n" +
"and   employeeallowance.`employeeid` like('"+staffid.getText()+"%')\n" +
"and   employeeallowance.`Month` like('"+smonth.getValue()+"%')\n" +
"and   employeeallowance.`Year` like('"+syear.getValue()+"%')\n" +
"and   employeepayment.`employeeid` like('"+staffid.getText()+"%')\n" +
"and   employeepayment.`month` like('"+smonth.getValue()+"%')\n" +
"and   employeepayment.`year` like('"+syear.getValue()+"%')" ;
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

    public void addValuesincombobox()
    {
        smonth.setItems(month);
        syear.setItems(year);
        sstatus.setItems(staus);  
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
    private void ViewButtonHandling(MouseEvent event) {
         Parent root =new HelperViewsResources().loadFXML("Viewpayrollpayment");
               Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.setTitle("Employee Paument");
               stage.show();
    }

    @FXML
    private void CalculateRadioButton(MouseEvent event) {
        try (
               
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT salary from employeesalary where employeeid like('"+staffid.getText()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                            csalary=(rs.getString("salary"));
                            ssalary.setText(csalary);
                            ssalary.setEditable(false);
            }   
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try (
               
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employededucation where employeeid like('"+staffid.getText()+"%') and Month like('"+smonth.getValue()+"%') and Year like('"+syear.getValue()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                            totaldeducationamount=Integer.parseInt(rs.getString("DeductionAmount"));
                            totaldeducation.setText(rs.getString("DeductionAmount"));
                            totaldeducation.setEditable(false);
            }   
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try (           
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT * from employeeallowance where employeeid like('"+staffid.getText()+"%') and Month like('"+smonth.getValue()+"%') and Year like('"+syear.getValue()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                            allownace=Integer.parseInt(rs.getString("TotalAllownace"));
                            totalallownace.setText(rs.getString("TotalAllownace"));
                            totalallownace.setEditable(false);
            }   
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        String monthname="",yearname="";
        String cmonth=smonth.getValue();
            int cyear=Integer.parseInt(syear.getValue());
        switch(cmonth)
        {
            case "Jan":
                monthname="Dec";
                cyear=cyear-1;
                yearname=Integer.toString(cyear); 
                break;
            case "Feb":
                monthname="Jan";
                yearname=Integer.toString(cyear);
                break;
            case "Mar":
                monthname="Feb";
                yearname=Integer.toString(cyear);
                break;
            case "Apr":
                monthname="Mar";
                yearname=Integer.toString(cyear);
                break;
            case "May":
                monthname="Apr";
                yearname=Integer.toString(cyear);
                break;
            case "June":
                monthname="May";
                yearname=Integer.toString(cyear);
                break;
            case "July":
                monthname="June";
                yearname=Integer.toString(cyear);
                break;
            case "Aug":
                monthname="July";
                yearname=Integer.toString(cyear);
                break;
            case "Sep":
                monthname="Aug";
                yearname=Integer.toString(cyear);
                break;
            case "Oct":
                monthname="Sep";
                yearname=Integer.toString(cyear);
                break;
            case "Nov":
                monthname="Oct";
                yearname=Integer.toString(cyear);
                break;
            case "Dec":
                monthname="Nov";
                yearname=Integer.toString(cyear);
                break;        
        }
         try (            
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT balance from employeepayment where employeeid like('"+staffid.getText()+"%') and month like ('"+monthname+"%') and year like ('"+yearname+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                    balace=Integer.parseInt(rs.getString("Balance"));
                    balance.setText(rs.getString("Balance"));
            }
            stmt.close();
            rs.close();
        }
           catch (SQLException e) {
            e.printStackTrace();
        }
         totalsalary=balace+allownace-totaldeducationamount+Integer.parseInt(csalary);
         payable.setText(Integer.toString(totalsalary));
         payable.setEditable(false);
    }

    @FXML
    private void PaidActionButtonHandling(ActionEvent event) {
        remain=totalsalary-Integer.parseInt(paidamount.getText());
        remaining.setText(Integer.toString(remain));
        remaining.setEditable(false);
    }

    @FXML
    private void UpdateButtonHandling(MouseEvent event) {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Add Payment Information");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                generateslip.setDisable(false);
                String studentinfo ="Update  employeepayment set employeeid=?,EmployeeName=?,Post=?,Salary=?,PaidSalry=?,Status=?,month=?,"
                        + "year=?,balance=?,TotalSalary=? where employeeid like('"+staffid.getText()+"%') and month like('"+smonth.getValue()+"%') and year like('"+syear.getValue()+"%')";
                try{
                pst=conn.prepareStatement(studentinfo);
                pst.setString(1,staffid.getText());
                pst.setString(2,staffname.getText());
                pst.setString(3,post);
                pst.setInt(4,Integer.parseInt(csalary));
                if(paidamount.getText().isEmpty())
                    pst.setInt(5,0);
                else
                    pst.setInt(5,Integer.parseInt(paidamount.getText()));
                pst.setString(6,sstatus.getValue());
                pst.setString(7,smonth.getValue());
                pst.setString(8,syear.getValue());
                pst.setInt(9,remain);
                pst.setInt(10,totalsalary);
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
