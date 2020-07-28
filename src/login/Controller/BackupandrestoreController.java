/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author Jahan
 */
public class BackupandrestoreController implements Initializable {

    @FXML
    private TextField backuppath;
    @FXML
    private Button backupbrowser;
    @FXML
    private Button backup;
    @FXML
    private TextField restorepath;
    @FXML
    private Button restoreBrowser;
    @FXML
    private Button restore;

    /**
     * Initializes the controller class.
     */
    String path=null;
    String filename;
    List<String> lastfile;
    FileChooser fc ;
     private FileInputStream fis;
    File f;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BackupBrowserButtonHandling(MouseEvent event) {
        JFileChooser fc= new JFileChooser();
        fc.showOpenDialog(null);
        String date =new SimpleDateFormat("yyyy-mm-dd").format(new Date());
        File f=fc.getSelectedFile();
        path=f.getAbsolutePath();
        path=path.replace('\\','/');
        path=path+"_"+date+".sql";
        backuppath.setText(path);
    }

    @FXML
    private void backupButtonHandling(MouseEvent event) throws IOException, InterruptedException {
        Process p=null;
        Runtime runtime=Runtime.getRuntime();
        p=runtime.exec("C:/Program Files/MySQL/MySQL Server 8.0/bin/mysqldump.exe -uroot -proot --add-drop-database -B schoolmanagementsystem -r"+path);
       int processComplete = p.waitFor();
            if (processComplete == 0)
            {
                      Alert alert =new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText("Backup Successfully Saved");
                        alert.showAndWait();
        }
           
    }

    @FXML
    private void RestoreBrowserButtonHandling(MouseEvent event) {
        lastfile=new ArrayList<>();
        lastfile.add("*.sql");
       fc=new FileChooser();
         fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Select File",lastfile));
          f=fc.showOpenDialog(null);  
          restorepath.setText(f.getAbsolutePath());
    }

    @FXML
    private void restoreButtonHandling(MouseEvent event) {
        
         if(f!=null)
         {
            try {
                String[] restoreCMd=new String[]{"C:/Program Files/MySQL/MySQL Server 8.0/bin/mysql.exe","--user"+"root","--password=root","-e","source"+f.getAbsolutePath()};
                Process p;
                p=Runtime.getRuntime().exec(restoreCMd);
                int processComplete = p.waitFor();
            if (processComplete == 0)
            {
             Alert alert1 =new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information");
                        alert1.setHeaderText("Restore Successfully ");
                        alert1.showAndWait();
                }
            } catch (IOException ex) {
                Logger.getLogger(BackupandrestoreController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(BackupandrestoreController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
