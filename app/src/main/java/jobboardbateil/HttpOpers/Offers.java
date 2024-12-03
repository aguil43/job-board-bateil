package jobboardbateil.HttpOpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import jobboardbateil.DbQueries.DbQuery;

// This class do the queries in the db, and return the data in json format
public class Offers {
    // Object to do the queries
    private DbQuery m_offers;
    // Const to save the table name
    private final String m_table = "joboffers";

    // Builder only initialize the object to do queries
    public Offers(){
        m_offers = new DbQuery();
    }

    // Methdo to do the query of the first group or first page
    public String chargeFirstOffers(){
        // Check the quantity of the pages in groups of ten offers each group
        int groups = m_offers.tableLength(m_table)/10;
        // Create the json array of the first group
        JsonArray json = m_offers.groupData(10, "joboffers");
        // Add in the array, the group num
        JsonPrimitive totalGroups = new JsonPrimitive(groups);
        // Add in the array, the actual page  
        JsonPrimitive actualGroup = new JsonPrimitive(1);
        json.add(totalGroups);
        json.add(actualGroup);
        // Return the string in json format
        return json.toString();
    }

    // Method to do queries to agroup other pages
    public String chargeOffers(int page, int groups){
        // Create the json array to save the data
        JsonArray json = m_offers.groupData(10, "joboffers", page * 10);
        // Add in the array, the total pages
        JsonPrimitive totalGroups = new JsonPrimitive(groups);
        // Add in the array, the actual page
        JsonPrimitive actualGroup = new JsonPrimitive(page);
        json.add(totalGroups);
        json.add(actualGroup);
        // Return the string in json format
        return json.toString();
    }
}
