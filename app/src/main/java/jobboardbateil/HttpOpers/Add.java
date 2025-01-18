package jobboardbateil.HttpOpers;

import jobboardbateil.DbQueries.DbChange;

// Class Add is a class to add job-offers to the db
public class Add {
    // Object to save the connection with the db
    private DbChange m_db; 
    
    // Builders start the connection with the db
    public Add(){
        m_db = new DbChange();
    }

    // Method addJob give the data of the form to make the sql query to insert the new data
    public boolean addJob(String table, String[] data){
        String query = "INSERT INTO jobboard." + table + " VALUES (0, 1, null, ";
        // Loop for make the sql query
        for(int i = 0; i <= data.length; i++){
            // check the position to add the date of adding offer
            if(i == 2){
                query = query + "curdate(), ";
            }else{
                // Check what is the correct structure for add or end the current data
                if(i == data.length){
                    query = query + "'" + data[i - 1] + "');";
                }else if(i > 2){
                    query = query + "'" + data[i - 1] + "', ";
                }else{
                    query = query + "'" + data[i] + "', ";
                }
            }
        }
        // Save the status of the interaction with the db
        boolean status = m_db.addQuery(query);
        // return the status
        return status;
    }
}
