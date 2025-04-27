package jobboardbateil.HttpOpers;

import javax.mail.*;
import javax.mail.internet.*;

import jobboardbateil.ReadEnv;

import java.util.Properties;

public class Sender {

    private String username;
    private String password;
    private ReadEnv env;
    private String[] dataMessage = new String[3];
    private boolean status = false;

    public Sender(String userName, String jobName, String userContanct){
        env = new ReadEnv();
        dataMessage[0] = userName;
        dataMessage[1] = jobName;
        dataMessage[2] = userContanct;
        username = env.getStrVar("MAIL_DIRECTION");
        password = env.getStrVar("MAIL_APP_PASSWORD");
        sendMessage(startSession());
    }

    public Session startSession(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            }
        });

        return session;
    }

    public void sendMessage(Session session){
        String subjectString = "Solicitud del puesto " + dataMessage[1];
        String bodyString = "El usuario " + dataMessage[0] + "\ndesea postularse al puesto " + dataMessage[1] + "\nsu correo electronico es " + dataMessage[2];
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("pedro.alejandro.garza@bateil.edu.mx"));
            message.setSubject(subjectString);
            message.setText(bodyString);

            Transport.send(message);
            System.out.println("Correo enviado");
            status = true;
        }catch(Exception e){
            System.err.println(e.getLocalizedMessage());
        }
    }

    public boolean getStatus(){
        return status;
    }
}
