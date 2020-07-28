/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import login.Helper.MysqlConnect;

/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class ClassChargesController implements Initializable {

    @FXML
    private AnchorPane FeeVoucherDetails;
    @FXML
    private JFXTextField classfee;
    @FXML
    private JFXTextField generatorfeeS;
    @FXML
    private JFXButton Add;
    @FXML
    private JFXButton Clear;
    @FXML
    private JFXComboBox<String> slectclass;
    @FXML
    private JFXTextField functionfee;
    @FXML
    private JFXTextField partyfee;
    @FXML
    private JFXTextField othercharges;
    @FXML
    private JFXTextField sportfee;
    @FXML
    private JFXTextField tripfee;
    @FXML
    private JFXTextField examsfee;
    @FXML
    private JFXButton Clear1;
    @FXML
    private JFXComboBox<String> section;
    @FXML
    private JFXTextField stationaryfee;
    @FXML
    private JFXTextField bookfee;

    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    ObservableList<String> classlist =FXCollections.observableArrayList();
    ObservableList<String> sectionlist =FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        textfieldvalidator();
        comboboxvalues();
        // TODO
    }    

    @FXML
    private void InsertButtonHndling(MouseEvent event) {
        if(true)
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Add Fee");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                String classfeeinfo ="INSERT INTO classfee "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
                pst=conn.prepareStatement(classfeeinfo);
                pst.setString(1,slectclass.getValue());
                pst.setString(2,section.getValue());
                pst.setInt(3,Integer.parseInt(classfee.getText()));
                if(generatorfeeS.getText().isEmpty())
                    pst.setInt(4,0);
                else   
                    pst.setInt(4,Integer.parseInt(generatorfeeS.getText()));
                if(functionfee.getText().isEmpty())
                    pst.setInt(5,0);
                else
                    pst.setInt(5,Integer.parseInt(functionfee.getText()));
                if(examsfee.getText().isEmpty())
                    pst.setInt(6,0);
                else
                    pst.setInt(6,Integer.parseInt(examsfee.getText()));
                if(partyfee.getText().isEmpty())
                    pst.setInt(7,0);
                else
                    pst.setInt(7,Integer.parseInt(partyfee.getText()));
                if(sportfee.getText().isEmpty())
                    pst.setInt(8,0);
                else
                    pst.setInt(8,Integer.parseInt(sportfee.getText()));
                if(tripfee.getText().isEmpty())
                    pst.setInt(9,0);
                else
                    pst.setInt(9,Integer.parseInt(tripfee.getText()));
                if(othercharges.getText().isEmpty())
                    pst.setInt(10,0);
                else
                    pst.setInt(10,Integer.parseInt(othercharges.getText()));
                if(bookfee.getText().isEmpty())
                    pst.setInt(11,0);
                else
                    pst.setInt(11,Integer.parseInt(bookfee.getText()));
                if(stationaryfee.getText().isEmpty())
                    pst.setInt(12,0);
                else
                    pst.setInt(12,Integer.parseInt(stationaryfee.getText()));
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
    private void UpdateButtonHandling(MouseEvent event) {
        if(true)
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Update Fee");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                String classfeeinfo ="Update  classfee set ClassName=?,Section=?,ClassFee=?,GeneratorFee=?,FunctionFee=?,ExamsFee=?,"
                        + "PartyFee=?,SportsFee=?,TripFee=?,OtherCharges=?,BooksFee=?,StationaryFee=? where ClassName like('"+slectclass.getValue()+"%') and Section like('"+section.getValue()+"%')";
        try{
                pst=conn.prepareStatement(classfeeinfo);
                pst.setString(1,slectclass.getValue());
                pst.setString(2,section.getValue());
                pst.setInt(3,Integer.parseInt(classfee.getText()));
                if(generatorfeeS.getText().isEmpty())
                    pst.setInt(4,0);
                else   
                    pst.setInt(4,Integer.parseInt(generatorfeeS.getText()));
                if(functionfee.getText().isEmpty())
                    pst.setInt(5,0);
                else
                    pst.setInt(5,Integer.parseInt(functionfee.getText()));
                if(examsfee.getText().isEmpty())
                    pst.setInt(6,0);
                else
                    pst.setInt(6,Integer.parseInt(examsfee.getText()));
                if(partyfee.getText().isEmpty())
                    pst.setInt(7,0);
                else
                    pst.setInt(7,Integer.parseInt(partyfee.getText()));
                if(sportfee.getText().isEmpty())
                    pst.setInt(8,0);
                else
                    pst.setInt(8,Integer.parseInt(sportfee.getText()));
                if(tripfee.getText().isEmpty())
                    pst.setInt(9,0);
                else
                    pst.setInt(9,Integer.parseInt(tripfee.getText()));
                if(othercharges.getText().isEmpty())
                    pst.setInt(10,0);
                else
                    pst.setInt(10,Integer.parseInt(othercharges.getText()));
                if(bookfee.getText().isEmpty())
                    pst.setInt(11,0);
                else
                    pst.setInt(11,Integer.parseInt(bookfee.getText()));
                if(stationaryfee.getText().isEmpty())
                    pst.setInt(12,0);
                else
                    pst.setInt(12,Integer.parseInt(stationaryfee.getText()));
              int row=pst.executeUpdate();
                if(row==1)
                {
                     Alert alert1 =new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information");
                        alert1.setHeaderText("Data Successfully Updated");
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
    private void SelectClassCombo(ActionEvent event) {
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT Section  from classinfo where ClassName like('"+slectclass.getValue()+"%')";
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
    @FXML
    private void SectionComboAction(ActionEvent event) {
    }
    @FXML
    private void resetButtonHandling(MouseEvent event) {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Conformation");
                        alert.setHeaderText("Do you want Clear the data");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                classfee.clear();
                generatorfeeS.clear();
                functionfee.clear();
                examsfee.clear();
                partyfee.clear();
                sportfee.clear();
                tripfee.clear();
                othercharges.clear();
                bookfee.clear();
                stationaryfee.clear();
            }
    }
     public void comboboxvalues()
    {
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT ClassName  from classinfo ";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                classlist.add(rs.getString("ClassName"));
            }
            slectclass.setItems(classlist);  
            stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }  
    }
    public void textfieldvalidator()
    {
        RequiredFieldValidator validator=new RequiredFieldValidator();
        NumberValidator numValidate =new NumberValidator();
       classfee.getValidators().add(numValidate);
        generatorfeeS.getValidators().add(numValidate);  
        functionfee.getValidators().add(numValidate);
        partyfee.getValidators().add(numValidate);
        othercharges.getValidators().add(numValidate);
        sportfee.getValidators().add(numValidate);
        tripfee.getValidators().add(numValidate);
        generatorfeeS.getValidators().add(numValidate);
        numValidate.setMessage("Only digits");
        classfee.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                    classfee.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
         generatorfeeS.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                      generatorfeeS.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
          functionfee.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                       functionfee.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
         partyfee.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                      partyfee.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
         othercharges.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                      othercharges.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
         sportfee.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                      sportfee.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
         tripfee.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                      tripfee.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
         examsfee.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                 if(!newValue)
                 {
                      examsfee.validate();
                 }
                //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
    }

    

    

    
    
}
