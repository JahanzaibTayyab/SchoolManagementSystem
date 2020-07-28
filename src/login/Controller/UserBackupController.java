/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author Jahan
 */
public class UserBackupController implements Initializable {

    @FXML
    private TextField path;
    @FXML
    private Button browser;
    @FXML
    private Button backup;

    /**
     * Initializes the controller class.
     */
    String path1=null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BrowserButtonHandling(MouseEvent event) {
        JFileChooser fc= new JFileChooser();
        fc.showOpenDialog(null);
        String date =new SimpleDateFormat("yyyy-mm-dd").format(new Date());
        File f=fc.getSelectedFile();
        path1=f.getAbsolutePath();
        path1=path1.replace('\\','/');
        path1=path1+"_"+date+".sql";
        path.setText(path1);
    }

    @FXML
    private void BackupButtonHandling(MouseEvent event) throws IOException, InterruptedException {
        Process p=null;
        Runtime runtime=Runtime.getRuntime();
        p=runtime.exec("C:/Program Files/MySQL/MySQL Server 8.0/bin/mysqldump.exe -uroot -proot --add-drop-database - B schoolmanagementsystem -r"+path1);
        int processComplete=p.waitFor();
        if(processComplete == 0)
        {
             Alert alert1 =new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information");
                        alert1.setHeaderText("Backup Successfully Saved");
                        alert1.showAndWait();
        }
          
    }
    
}
