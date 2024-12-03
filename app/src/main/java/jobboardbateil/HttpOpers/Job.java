package jobboardbateil.HttpOpers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import jobboardbateil.DbQueries.DbQuery;

public class Job {
    private DbQuery m_jobQuery;
    private JsonObject m_json;

    public Job(){
        m_jobQuery = new DbQuery();
        m_json = new JsonObject();
    }

    public JsonElement chargeJob(int jobid){
        String[] data = m_jobQuery.get_data_str("" + jobid, "joboffers", "jobofferid");
        String[] namedata = {"jobid", "compid", "profileid", "name", "location", "date", "journey", "category", "description", "responsabilities", "experiences"};
        if(data.length == namedata.length){
            for(int i = 0; i < data.length; i++){
                m_json.addProperty(namedata[i], data[i]);
            }
            return m_json;
        }
        return new JsonPrimitive(false);
    }
}
