package jobboardbateil.DbQueries;

import java.sql.SQLException;
import java.sql.Statement;

// Class used to change the database (changing the existing data or adding new data)
public class DbChange {
    // Object that manage the connection with the db
    private DbConnection m_db;
    // String that saves the query used in the db
    private String m_query;
    // Object that manage the interactions with the db
    private Statement m_statement;
    // Boolean to alert at the final of the execution if the operation is complete or have an error
    private boolean isEnd = false;

    // Builder only create the object for the db connection
    public DbChange(){
        // Initialize the object to connect with the db
        m_db = new DbConnection();
    }

    // This method is used to add data to the DB, having as 
    public boolean addData(String table, String[] values){
        // This call start the connection with the db
        m_db.startDBConnection();
        // This call gets the object to execute queries
        m_statement = m_db.getStatatement();
        // String that saves the query that be executed in the db
        m_query = "INSERT INTO jobboard." +  table + " VALUES ( 0, '";
        // Make the complete query to execute after in the server
        for(int i = 0; i < values.length; i++){
            // Check if the last element in the string for add end the query,
            // if not is the last element continue making the query
            if(i == values.length - 1){
                m_query = m_query + values[i] + "');";
            }else{
                m_query = m_query + values[i] + "', '";
            }
        }
        System.out.println(m_query);
        // Try to execute the query catchinf if have error
        try{
            m_statement.executeUpdate(m_query);
            isEnd = true;
        }catch(SQLException e){
            // If an error occurred show the message in the server
            System.out.println(e.getErrorCode() + e.getMessage());
        }
        // Return the value for is ended, if don't have errors is true
        // I have arreror is false
        return isEnd;
    }
}
