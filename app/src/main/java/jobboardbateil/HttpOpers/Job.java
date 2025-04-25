package jobboardbateil.HttpOpers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import jobboardbateil.DbQueries.DbChange;
import jobboardbateil.DbQueries.DbQuery;

// This class search the data of a job offer
public class Job {
    // Object to do the query
    private DbQuery m_jobQuery;
    // Object to save the data in json object
    private JsonObject m_json;
    private DbChange m_jobChange;
    // Array with the name of the column on the db
    private final String[] k_namedata = {"jobid", "compid", "profileid", "name", "location", "date", "journey", "category", "description", "responsabilities", "experiences", "isAvailable"};
    private final String[] k_columnsName = {"jobofferid", "companyid", "profileid", "joboffer", "joblocation", "jobdate", "jobjourney", "jobcategory", "jobdescription", "jobresp", "jobexp", "isAvailable"};


    // Builder only initialize the two past objects
    public Job(){
        m_jobQuery = new DbQuery();
        m_json = new JsonObject();
        m_jobChange = new DbChange();
    }

    // This method only charge an specific job, this method require as parameter the id of searched job
    public JsonElement chargeJob(int jobid){
        // Create the string array to save the query
        String[] data = m_jobQuery.get_data_str("" + jobid, "joboffers", "jobofferid");
        // Check if the two past arrays have the same length
        if(data.length == k_namedata.length){
            // Organaize the key value, json data in the json object
            for(int i = 0; i < data.length; i++){
                m_json.addProperty(k_namedata[i], data[i]);
            }
            // return the json object
            return m_json;
        }
        // If the length of the arrays not the same return false
        return new JsonPrimitive(false);
    }

    public boolean editJob(int id, String[] newData){
        int[] editableIndexes = {3, 4, 5, 6, 7, 8, 9, 10};
        String modify = "";
        for(int i = 0; editableIndexes.length > i; i++){
            modify = modify + k_columnsName[editableIndexes[i]] + "='" + newData[i] + "', ";
            if(i == editableIndexes.length-1){
                modify = modify + k_columnsName[editableIndexes[i]] + "='" + newData[i] + "' ";
            }
        }
        String query = "UPDATE jobboard.joboffers SET " + modify + "WHERE jobofferid = " + id + ";";
        System.out.println(query);
        if(m_jobChange.addQuery(query)){
            return true;
        }
        return false;
    }
}
