package jobboardbateil;

import io.github.cdimascio.dotenv.Dotenv;
// This class is used to read and converto to a specific type values in .env file
public class ReadEnv {
    // This object is used to read the env vars
    private Dotenv m_env;

    // This vars saves the env vars in string or int values
    private String m_stringEnvVar;
    private int m_intEnvVar;

    // The builder, only charge the env file in the dotenv object
    public ReadEnv(){
        m_env = Dotenv.load();
    }

    // This method return an int read from var of the env file
    public int getIntVar(String nameVar){
        // Sentence try-catch to prevents error, at the moment of read a var
        // of the env file
        try{
            // Read the string of vars value in the env file
            m_stringEnvVar = m_env.get(nameVar);
            // Convert the string to int
            m_intEnvVar = Integer.parseInt(m_stringEnvVar);
        }catch(Exception e){
            // Show error message, if can't read env file
            System.out.println(e.getMessage());
            m_intEnvVar = 0;
        }
        // Return the int read value
        return m_intEnvVar;
    }

    // This method return a string read from var of the env file
    public String getStrVar(String nameVar){
        // Sentence try-catch to prevents error, at the moment of read a var
        try{
            // Read the string of vars value in the env file
            m_stringEnvVar = m_env.get(nameVar);
        }catch(Exception e){
            // Shoe message error, if can't read env file
            System.out.println(e.getMessage());
            m_stringEnvVar = "";
        }
        // Return the string read value
        return m_stringEnvVar;
    }
}
