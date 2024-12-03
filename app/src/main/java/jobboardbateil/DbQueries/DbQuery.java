package jobboardbateil.DbQueries;

import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

// This class do the queries into the db
public class DbQuery {
    // This vars are used to do queries to the db
    // Save the connection with the db
    private DbConnection m_db;
    // Save the statement created from the db
    private Statement m_statement;
    // Save the result after the query
    private ResultSet m_result;
    // Save additional data of the query
    private ResultSetMetaData m_meta;
    // Save the query, wants to do to the db
    private String m_query;
    // Save the data in plain text
    private String m_datum_str;
    private String[] m_data_str;
    private JsonArray m_json;

    // Builder only create the object to after create the connection with the db
    public DbQuery(){
        m_db = new DbConnection();
    }

    // This method return a specific value read from the db
    public String get_datum_str(String datumString,String columnName, String table, int column){
        // Create the connection to the db, ang gets the statement object
        m_db.startDBConnection();
        m_statement = m_db.getStatatement();
        // Make the sql sentence, to do the query
        m_query = "SELECT * FROM jobboard." + table + " WHERE " + columnName + "='" + datumString + "';";
        try{
            // Execute the query to gets the data
            m_result = m_statement.executeQuery(m_query);
            // Save the additional data from our query
            m_meta = m_result.getMetaData();
            // Move the cursor in the result, to the first element
            m_result.next();
            // Create the array to save all the collumns of the specific query
            m_data_str = new String[m_meta.getColumnCount()];
            // Save each datum in a space of the array
            for(int i = 1; i <= m_meta.getColumnCount(); i++){
                m_data_str[i-1] = m_result.getString(i);
            }
        }catch(SQLException e){
            // If the query fails, show the error code in terminal, and declare the result query to null
            System.out.println(e.getErrorCode());
            m_result = null;
        }finally{
            // For good practice stop the db connection
            m_db.stopDBConnection();
        }
        // Save and return the specific datum of the created array
        m_datum_str = m_data_str[column];
        return m_datum_str;
    }

    // This method return an all data array from the query
    public String[] get_data_str(String dataString, String table, String columnName){
        // Initialize the connection with the db, and gets the statement's object
        m_db.startDBConnection();
        m_statement = m_db.getStatatement();
        // Make the sentence to do the query
        m_query = "SELECT * FROM jobboard." + table + " WHERE " + columnName + "='" + dataString + "';";
        try{
            // Execute the query in the db, and save the result in a var of type resultset
            m_result = m_statement.executeQuery(m_query);
            // Save the metadata of the query
            m_meta = m_result.getMetaData();
            // Move the cursor in the result, to the first element
            m_result.next();
            // Declare the lenght of the array to start the fill of this
            m_data_str = new String[m_meta.getColumnCount()];
            // Iterate for each column in the result, to extract all the data of the query
            // and save this in the array
            for(int i = 0; i < m_meta.getColumnCount(); i++){
                m_data_str[i] = m_result.getString(i+1);
            }
        }catch(SQLException e){
            // If the query fails, show the message error in terminal, and declare the object result as null
            System.out.println(e.getErrorCode() + ": " + e.getMessage());
            m_result = null;
        }finally{
            // Finally for good practice, stop the connection with the server 
            m_db.stopDBConnection();
        }
        // Return the array with the data 
        return m_data_str; 
    }

    public int tableLength(String table){
        m_db.startDBConnection();
        m_statement = m_db.getStatatement();
        m_query = "SELECT COUNT(*) as totalitems FROM jobboard." + table + ";";
        int len = 0;
        try{
            m_result = m_statement.executeQuery(m_query);
            m_result.next();
            len = m_result.getInt(1);
        }catch(SQLException e){
            System.out.println(e.getErrorCode() + ": " + e.getMessage());
        }finally{
            m_db.stopDBConnection();
        }
        return len;
    }

    public JsonArray groupData(int len, String table, int startAt){
        m_json = new JsonArray();
        m_db.startDBConnection();
        m_statement = m_db.getStatatement();
        m_query = "SELECT * FROM jobboard." + table + " LIMIT " + len + " OFFSET " + startAt + ";";
        try{
            m_result = m_statement.executeQuery(m_query);
            m_meta = m_result.getMetaData();
            m_result.next();
            m_data_str = new String[m_meta.getColumnCount()];
            while(m_result.next()){
                JsonObject data = new JsonObject();
                data.addProperty("offerid", m_result.getInt(1));
                data.addProperty("companyid", m_result.getInt(2));
                data.addProperty("profileid", m_result.getInt(3));
                data.addProperty("job", m_result.getString(4));
                data.addProperty("location", m_result.getString(5));
                data.addProperty("date", m_result.getString(6));
                data.addProperty("journey", m_result.getString(7));
                data.addProperty("category", m_result.getString(8));
                data.addProperty("description", m_result.getString(9));
                data.addProperty("responsability", m_result.getString(10));
                data.addProperty("experience", m_result.getString(11));
                m_json.add(data);
            }
        }catch(SQLException e){
            System.out.println(e.getErrorCode() + ": " + e.getMessage());
        }finally{
            m_db.stopDBConnection();
        }
        return m_json;
    }

    public JsonArray groupData(int len, String table){
        m_json = new JsonArray();
        m_db.startDBConnection();
        m_statement = m_db.getStatatement();
        m_query = "SELECT * FROM jobboard." + table + " LIMIT " + len + ";";
        try{
            m_result = m_statement.executeQuery(m_query);
            while(m_result.next()){
                JsonObject data = new JsonObject();
                data.addProperty("offerid", m_result.getInt(1));
                data.addProperty("companyid", m_result.getInt(2));
                data.addProperty("profileid", m_result.getInt(3));
                data.addProperty("job", m_result.getString(4));
                data.addProperty("location", m_result.getString(5));
                data.addProperty("date", m_result.getString(6));
                data.addProperty("journey", m_result.getString(7));
                data.addProperty("category", m_result.getString(8));
                data.addProperty("description", m_result.getString(9));
                data.addProperty("responsability", m_result.getString(10));
                data.addProperty("experience", m_result.getString(11));
                m_json.add(data);
            }
        }catch(SQLException e){
            System.out.println(e.getErrorCode() + ": " + e.getMessage());
        }finally{
            m_db.stopDBConnection();
        }
        return m_json;
    }

// Para escribir la fecha en la tabla usar la funcion curdate()
}
