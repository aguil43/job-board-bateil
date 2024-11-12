package jobboardbateil.HttpOpers;

import jobboardbateil.DbQueries.*;

// This class is used to auth an user in the system
public class Login {
    // Declare the vars used to auth the users
    // Save the users data, inputs and db values
    private String m_inputEmail;
    private String m_inputPassword;
    private String m_dbPassword;
    // Save the status of the user, logged or not logged
    private boolean m_isAuth;
    // Save the object that save the connection with the db
    private DbConnection conn;
    
    // Builder do nothing jajaja
    public Login(){}

    // This method check if the strings saves the same text, and return the status of the user
    public boolean startLogin(String inputEmail, String inputPassword){
        // Save the inputs of the user in the private vars of this class
        this.m_inputEmail = inputEmail;
        this.m_inputPassword = inputPassword;
        // Declare the users status as false
        m_isAuth = false;
        // Search the user data in the db, and save the password to check if this is correct
        m_dbPassword = ""; // Add here the password saved in the db, using a sql query
        // Check if the strings save the same text, and check if the email is different that null
        if(m_dbPassword.equals(m_inputPassword) && m_inputPassword != null){
            // if the conditions are true change the user status to true
            m_isAuth = true;
        }
        // Return the status of the user
        return m_isAuth;
    }
}
