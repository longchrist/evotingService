/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Long
 */
public class DB_Connection {
    private Connection conn = null;
    public String sDbDriver;
    public String sDbUrl;
    public String sDbUser;
    public String sDbPass;

    public DB_Connection() throws FileNotFoundException, IOException {
        //BufferedReader br = new BufferedReader(new FileReader("http://localhost:8084/evotingService/Components/WebEngineSettings.json"));
//        String json = "{"database_settings":{"confirm_security":"admin","db_password":"","security\":\"admin\",\"db_link\":\"localhost:3306\",\"target_database\":\"db_evoting\",\"db_username\":\"root\"}}";
//        try {
//            StringBuilder sb = new StringBuilder();
//            String line = br.readLine();
//
//            while (line != null) {
//                sb.append(line);
//                sb.append(System.lineSeparator());
//                line = br.readLine();
//            }
//            json = sb.toString();
//        } finally {
//            br.close();
//        }
        
        //JSONObject obj = new JSONObject(json);
        
        String databaseLink = "localhost:3306";//obj.getJSONObject("database_settings").getString("db_link");
        String databaseUsername = "root"; //obj.getJSONObject("database_settings").getString("db_username");
        String databasePassword = ""; //obj.getJSONObject("database_settings").getString("db_password");
        String databaseTarget = "db_evoting"; //obj.getJSONObject("database_settings").getString("target_database");
        String databaseSecurity = ""; //obj.getJSONObject("database_settings").getString("security");
        String databaseConfirmSecurity = ""; //obj.getJSONObject("database_settings").getString("confirm_security");
        
        sDbDriver = "com.mysql.jdbc.Driver";
        sDbUrl = "jdbc:mysql://"+databaseLink+"/"+databaseTarget+"";
        sDbUser = databaseUsername;
        sDbPass = databasePassword;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(sDbUrl, sDbUser, sDbPass);
        } catch (SQLException ex) {
            Logger.getLogger(DB_Connection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    public Connection getConnection() {
        return this.conn;
    }
    
    public void closeConnection() throws SQLException{
        conn.close();
    }
    
    public Connection checkConnections(String varHost, String varPort, String varUsername, String varPassword) {
        sDbDriver = "com.mysql.jdbc.Driver";
        
        String var_host = varHost;
        String var_port = varPort;
        String var_username = varUsername;
        String var_password = varPassword;
        Connection retrieveConn;
        
        sDbUrl = ""+var_host+":"+var_port+"";
        sDbUser = var_username;
        sDbPass = var_password;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+sDbUrl,sDbUser,sDbPass);
            retrieveConn = conn;
            System.out.println("Coonection : "+retrieveConn);
            return retrieveConn;
        } catch (SQLException ex) {
            //Logger.getLogger(Database_Connections.class.getName()).log(Level.SEVERE, null, ex);
            retrieveConn = conn;
            return retrieveConn;
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(Database_Connections.class.getName()).log(Level.SEVERE, null, ex);
            retrieveConn = conn;
            return retrieveConn;
        }
    }
    
    public static void main(String[] args) throws IOException {
        DB_Connection DBC = new DB_Connection();
        DBC.checkConnections("localhost", "3306", "root", "");
    }
}
