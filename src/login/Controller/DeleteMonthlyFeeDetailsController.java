/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import login.Helper.HelperViewsResources;
import login.Helper.MysqlConnect;


/**
 * FXML Controller class
 *
 * @author Jahanzaib Tayyab
 */
public class DeleteMonthlyFeeDetailsController implements Initializable {

    @FXML
    private JFXComboBox<String> month;
    @FXML
    private JFXComboBox<String> year;

    /**
     * Initializes the controller class.
     */
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    ObservableList<String> monthlist =FXCollections.observableArrayList("Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec");
    ObservableList<String> yearlist =FXCollections.observableArrayList("2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        month.setItems(monthlist);
        year.setItems(yearlist);
        // TODO
    }    
    @FXML
    private void DeleteAction(MouseEvent event) {
        Alert alert =new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warring");
                        alert.setHeaderText("Do you want Delete Fee");
                        Optional<ButtonType> action=alert.showAndWait();
            if(action.get()==ButtonType.OK)
            {
                String sql ="Delete From feedetails where Month=? and Year=?";
        try{
            
            pst=conn.prepareStatement(sql);
            pst.setString(1,month.getValue());
            pst.setString(2,year.getValue());
               boolean numRows=pst.execute();
               if(numRows==false)
               {
                    Alert alert1 =new Alert(Alert.AlertType.CONFIRMATION);
                        alert1.setTitle("Conformation");
                        alert1.setHeaderText("Deleted");
                        alert1.showAndWait();
               }
            }
            catch (SQLException e) {
            e.printStackTrace();
        }
            }
            }
            
    }
    

