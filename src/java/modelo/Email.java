/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author rcane
 */
public class Email {
    private String from = "";
    private String to = "";
    private String subject = "";
    private String text = "";

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public void setEnviarEmail(Email email, String password) throws Exception {
        Properties p = new Properties();
        // Servidor smtp de correo
        p.setProperty("mail.smtp.host", "smtp.gmail.com");
        // Usar TLS
        p.setProperty("mail.smtp.starttls.enable", "true");
        // puerto del servidor smtp
        p.setProperty("mail.smtp.port", "587");
        // Usuario smtp
        p.setProperty("mail.smtp.user", email.getFrom());
        // Autenticación requerida
        p.setProperty("mail.smtp.auth", "true");
        // Obtenemos la sesión
        Session sesion = Session.getDefaultInstance(p);
        sesion.setDebug(false);
        // Creamos el mensaje
        MimeMessage mensaje = new MimeMessage(sesion);
        // Y establecemos sus propiedades

        mensaje.setFrom(new InternetAddress(email.getFrom()));
        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));
        mensaje.setSubject(email.getSubject());
        mensaje.setText(email.getText());
        // Enviamos el mensaje
        Transport t = sesion.getTransport("smtp");
        // Para conectarnos usamos usuario y password
        t.connect(email.getFrom(), password);
        t.sendMessage(mensaje, mensaje.getAllRecipients());

    }
}