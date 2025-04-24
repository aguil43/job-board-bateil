package jobboardbateil.HttpOpers;

import jobboardbateil.DbQueries.DbChange;

public class DeleteJob {
    private DbChange m_db;
    private String m_query;

    public DeleteJob(){
        m_db = new DbChange();
    }

    public boolean deleteJob(int id){
        m_query = "DELETE FROM jobboard.joboffers WHERE jobofferid = " + id + ";";
        return m_db.addQuery(m_query);
    }
}
