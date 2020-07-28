/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Helper;

/**
 *
 * @author Jahanzaib Tayyab
 */
import java.sql.*;
import javax.swing.*;
public class MysqlConnect {
    Connection conn=null;
    public static Connection ConnectDB()
    {
        try
        {
           // Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagementsystem","root",""); 
            return con;
        }
        catch(Exception e)
                {
                   JOptionPane.showMessageDialog(null,e); 
                   return null;
                }
    } 
}
