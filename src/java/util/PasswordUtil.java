package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hashea contraseñas con SHA-256 para no guardarlas en texto plano.
 * (Para un proyecto en producción real se recomienda BCrypt, pero
 * SHA-256 ya evita el problema principal de guardar la clave "tal cual".)
 */
public class PasswordUtil {

    public static String hash(String textoPlano) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(textoPlano.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException("No se pudo hashear la contraseña", e);
        }
    }

    public static boolean coincide(String textoPlano, String hashGuardado) {
        return hash(textoPlano).equals(hashGuardado);
    }
}