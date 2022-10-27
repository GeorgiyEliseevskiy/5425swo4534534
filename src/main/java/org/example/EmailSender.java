package org.example;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class EmailSender {

    public static void main(String args[]) {
        sendEmail();
    }


    public static void sendEmail() {
        String user = "swoosh.carwash@yandex.ru";
        String password = "Swooshmail1@1342asd";

        String message = "123";
        String email = "geliseevskiy@list.ru";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(user));
            InternetAddress[] addresses = {new InternetAddress(email)};
            msg.setRecipients(Message.RecipientType.TO, addresses);
            msg.setSubject("Swoosh confirm");
            msg.setSentDate(new Date());
            msg.setText("Код подтверждения: " + message);
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

