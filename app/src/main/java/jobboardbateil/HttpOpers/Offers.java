package jobboardbateil.HttpOpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import jobboardbateil.DbQueries.DbQuery;

public class Offers {
    private DbQuery m_offers;
    private final String m_table = "joboffers";

    public Offers(){
        m_offers = new DbQuery();
    }

    public String chargeFirstOffers(){
        int groups = m_offers.tableLength(m_table)/10;
        JsonArray json = m_offers.groupData(10, "joboffers");
        JsonPrimitive totalGroups = new JsonPrimitive(groups);
        JsonPrimitive actualGroup = new JsonPrimitive(1);
        json.add(totalGroups);
        json.add(actualGroup);
        return json.toString();
    }

    public String chargeOffers(int page, int groups){
        JsonArray json = m_offers.groupData(10, "joboffers", page * 10);
        JsonPrimitive totalGroups = new JsonPrimitive(groups);
        JsonPrimitive actualGroup = new JsonPrimitive(page);
        json.add(totalGroups);
        json.add(actualGroup);
        return json.toString();
    }
}
