package util;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.security.SecureRandom;
import java.util.Properties;

/**
 * Envío de correo por SMTP (pensado para Gmail) y generación del
 * código de verificación de 6 dígitos.
 *
 * IMPORTANTE - configura estos 2 valores con tu cuenta:
 *   CORREO_REMITENTE : el correo desde el que se envían los códigos
 *   CLAVE_APP         : una "contraseña de aplicación" de Gmail
 *                        (Cuenta Google > Seguridad > Verificación en 2 pasos
 *                         > Contraseñas de aplicaciones). NO tu contraseña normal.
 *
 * Requiere en el classpath el jar de Jakarta Mail (ver README_V2.md).
 */
public class EmailUtil {

    private static final String CORREO_REMITENTE = "torresiris183@gmail.com";
    private static final String CLAVE_APP         = "lfmv hzdu uwxq bxeq";

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    /** Genera un código numérico de 6 dígitos. */
    public static String generarCodigo() {
        SecureRandom random = new SecureRandom();
        int numero = 100000 + random.nextInt(900000); // 100000 - 999999
        return String.valueOf(numero);
    }

    public static void enviarCodigoVerificacion(String correoDestino, String nombre, String codigo)
            throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(CORREO_REMITENTE, CLAVE_APP);
            }
        });

        Message mensaje = new MimeMessage(session);
        mensaje.setFrom(new InternetAddress(CORREO_REMITENTE));
        mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino));
        mensaje.setSubject("Código de verificación - Registro de Alumnos");
        mensaje.setText(
            "Hola " + nombre + ",\n\n" +
            "Tu código de verificación es: " + codigo + "\n\n" +
            "Ingresa este código en la página de verificación para activar tu cuenta.\n\n" +
            "Si tú no solicitaste este registro, ignora este correo."
        );

        Transport.send(mensaje);
    }
}
