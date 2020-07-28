/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Helper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Jahanzaib Tayyab
 */
public class HelperViewsResources {

    public HelperViewsResources() {
    }
    public static Parent loadFXML(String resoures)
    {
        Parent root=null;
        try {
            root =new FXMLLoader().load(new HelperViewsResources().getClass().getResource("/login/view/"+resoures+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HelperViewsResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return root;    
    }
    
}
