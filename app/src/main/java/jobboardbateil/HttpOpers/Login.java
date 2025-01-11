package jobboardbateil.HttpOpers;

import io.javalin.http.Context;
import jobboardbateil.DbQueries.*;

// This class is used to auth an user in the system
public class Login {
    // Declare the vars used to auth the users
    // Save the users data, inputs and db values
    private String m_inputEmail;
    private String m_inputPassword;
    //private String m_dbEmail;
    //private String m_dbPassword;
    // Save the status of the user, logged or not logged
    private String[] m_isAuth;
    // Save the object that save the connection with the db
    private DbQuery conn;
    
    public Login(){
        conn = new DbQuery();
        m_isAuth = new String[4];
    }

    // This method check if the strings saves the same text, and return the status of the user, and the data of the user
    public String[] startLogin(String inputEmail, String inputPassword, Context ctx){
        // Save the inputs of the user in the private vars of this class
        this.m_inputEmail = inputEmail;
        this.m_inputPassword = inputPassword;
        // Declare the users status as false
        m_isAuth[0] = "false";
        // Search the user data in the db, and save the password to check if this is correct
        String[] creds = conn.get_data_str(m_inputEmail, "users", "useremail"); // Add here the password saved in the db, using a sql query
        // Check if the strings save the same text, and check if the email is different that null
        System.out.println(creds[0] != null);
        if(creds[0] != null){
            System.out.println(creds[3].equals(m_inputPassword));
            if(creds[3].equals(m_inputPassword)){
                // if the conditions are true change the user status to true
                m_isAuth[0] = "true";
                m_isAuth[1] = creds[1];
                m_isAuth[2] = creds[2];
                m_isAuth[3] = creds[0];
            }
        }
        // Return the status of the user
        return m_isAuth;
    }
}
