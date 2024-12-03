package jobboardbateil.HttpOpers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import jobboardbateil.DbQueries.DbQuery;

// This class search the data of a job offer
public class Job {
    // Object to do the query
    private DbQuery m_jobQuery;
    // Object to save the data in json object
    private JsonObject m_json;

    // Builder only initialize the two past objects
    public Job(){
        m_jobQuery = new DbQuery();
        m_json = new JsonObject();
    }

    // This method only charge an specific job, this method require as parameter the id of searched job
    public JsonElement chargeJob(int jobid){
        // Create the string array to save the query
        String[] data = m_jobQuery.get_data_str("" + jobid, "joboffers", "jobofferid");
        // Array of the datum name
        String[] namedata = {"jobid", "compid", "profileid", "name", "location", "date", "journey", "category", "description", "responsabilities", "experiences"};
        // Check if the two past arrays have the same length
        if(data.length == namedata.length){
            // Organaize the key value, json data in the json object
            for(int i = 0; i < data.length; i++){
                m_json.addProperty(namedata[i], data[i]);
            }
            // return the json object
            return m_json;
        }
        // If the length of the arrays not the same return false
        return new JsonPrimitive(false);
    }
}
