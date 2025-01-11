package jobboardbateil.HttpOpers;

import jobboardbateil.DbQueries.DbChange;

// The register class is used to register new users in the db
public class Register {
    // This object saves the connection with the db
    private DbChange m_conn;
    // This bool saves the status of the register, for return this at the end
    private boolean isReg = false;

    // Builders only create the object to connect with the db
    public Register(){
        m_conn = new DbChange();
    }

    // This method send the indication to add the new user at the db
    public boolean reg(String name, String email, String password, String speciality){
        // This array saves the userd data to save this in the db
        String[] data = {name, email, password};
        // Check the returning status before try to add the user at the db
        if(m_conn.addData("users", data)){
            // Change the bool of status for the operation
            isReg = true;
        }
        // Return the status
        return isReg;
    }
}
