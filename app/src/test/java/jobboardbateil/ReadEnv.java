package jobboardbateil;

import io.github.cdimascio.dotenv.Dotenv;

public class ReadEnv {
    private Dotenv m_env;
    private String m_stringEnvVar;
    private int m_intEnvVar;

    public ReadEnv(){
        m_env = Dotenv.load();
    }

    public int getIntVar(String nameVar){
        try{
            m_stringEnvVar = m_env.get(nameVar);
            m_intEnvVar = Integer.parseInt(m_stringEnvVar);
        }catch(Exception e){
            System.out.println(e.getMessage());
            m_intEnvVar = 0;
        }
        return m_intEnvVar;
    }

    public String getStrVar(String nameVar){
        try{
            m_stringEnvVar = m_env.get(nameVar);
        }catch(Exception e){
            System.out.println(e.getMessage());
            m_stringEnvVar = "";
        }
        return m_stringEnvVar;
    }
}
