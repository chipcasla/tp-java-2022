package utils;

import javax.mail.*;
import javax.mail.internet.*;

import entities.Inscripcion;
import entities.Persona;

import java.util.Properties;

public class Correo {

    private String remitente = "postmaster@sandbox9881c6b04c754e608f08924b80219736.mailgun.org";
    private String servidorSMTP = "smtp.mailgun.org";
    private int puertoSMTP = 587;
    private String usuarioSMTP = "postmaster@sandbox9881c6b04c754e608f08924b80219736.mailgun.org";
    private String contrasenaSMTP = "12343728702e1f40650bc74fa734c2f7-2c441066-30d6a304";

    public Correo() {}

    public void confirmarInscripción(Persona receptor, Inscripcion ins) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", servidorSMTP);
        props.put("mail.smtp.port", puertoSMTP);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuarioSMTP, contrasenaSMTP);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(remitente));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor.getMail()));
        message.setSubject("Inscripción exitosa");
        message.setText("Hola "+receptor.getNombre()+" "+receptor.getApellido()+",\n "
        		+ "Confirmamos la correcta inscripción a "+ins.getCurso().getNombre()+" año "
        				+ ins.getCurso().getModalidad()+ " realizada el "+ins.getFechaInscripcionFormato());

        Transport transport = session.getTransport("smtp");
        transport.connect(servidorSMTP, puertoSMTP, usuarioSMTP, contrasenaSMTP);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}