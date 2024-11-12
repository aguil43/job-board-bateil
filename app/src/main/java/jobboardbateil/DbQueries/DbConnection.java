package jobboardbateil.DbQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import jobboardbateil.ReadEnv;

// This class create the connection with the db, to after do queries to the db
public class DbConnection {
    // This are objects vars
    // This var save the object to read the env file, that contains the db credentials
    private ReadEnv m_dotenv;
    // This var save te object that contains the db connection
    private Connection m_db;
    // This vars save the credential, that was read of the env file
    private String m_db_username;
    private String m_db_url;
    private String m_db_password;

    // Builder do the assignation of the values for the used vars
    public DbConnection(){
        // Create a object to read the env file
        m_dotenv = new ReadEnv();
        // Read the needed vars of the env file
        m_db_username = m_dotenv.getStrVar("DB_SERVER_USER");
        m_db_password = m_dotenv.getStrVar("DB_SERVER_PASSWORD");
        m_db_url = m_dotenv.getStrVar("DB_SERVER_URL");
    }

    // This method start the connection with te database, with the assigned creds
    public void startDBConnection(){
        try{
            // Initialize the connection, using the creds saved in the env file
            m_db = DriverManager.getConnection(m_db_url, m_db_username, m_db_password);
            // Show a message in terminal, to see the correct connection with the db
            System.out.println("Succesful start connection");
        }catch(SQLException e){
            // If the connections fails show the error code in terminal
            System.out.println(e.getErrorCode());
        }
    }

    // This method return an object of connection type, to do the necessary queries in the db
    public Connection getConnection(){
        // return the connection object
        return m_db;
    }

    // This method create and return a object of type statement
    public Statement getStatatement(){
        try{
            // Create the object statement using the db object, and return this new object
            return m_db.createStatement();
        }catch(SQLException e){
            // If the statement creation throw an error, show the message error an return null value
            System.out.println(e.getErrorCode());
            return null;
        }
    }

    // This method close the db connection, this practice is only for good practice
    public void stopDBConnection(){
        try{
            // Close the connection, using the db object
            m_db.close();
            // Show a message in terminal, if the connection us closed succesful
            System.out.println("Succesful close connection");
        }catch(SQLException e){
            // If the close fails, show the error code in terminal
            System.out.println(e.getErrorCode());
        }
    }
}
