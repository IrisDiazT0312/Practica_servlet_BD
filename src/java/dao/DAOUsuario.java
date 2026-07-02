package dao;

import modelo.Usuario;
import conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUsuario {

    /** Inserta un usuario nuevo, no verificado, con su código de verificación. */
    public boolean registrar(Usuario u) {
        String sql = "INSERT INTO usuarios (nombre, correo, password, codigo, verificado, activo) "
                   + "VALUES (?, ?, ?, ?, FALSE, TRUE)";
        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getCodigo());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false; // ej. correo duplicado (UNIQUE)
        }
    }

    public Usuario buscarPorCorreo(String correo) {
        String sql = "SELECT * FROM usuarios WHERE correo = ?";
        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) { }
        return null;
    }

    /** Marca el correo como verificado y limpia el código. */
    public boolean confirmarCodigo(String correo, String codigoIngresado) {
        String sql = "UPDATE usuarios SET verificado = TRUE, codigo = NULL "
                   + "WHERE correo = ? AND codigo = ?";
        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, codigoIngresado);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            return false;
        }
    }

    /** Genera y guarda un nuevo código (para reenviar). */
    public boolean actualizarCodigo(String correo, String nuevoCodigo) {
        String sql = "UPDATE usuarios SET codigo = ? WHERE correo = ?";
        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoCodigo);
            ps.setString(2, correo);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            return false;
        }
    }

    /** Activa o desactiva una cuenta (para un panel de administración, si se requiere). */
    public boolean cambiarEstatus(String correo, boolean activo) {
        String sql = "UPDATE usuarios SET activo = ? WHERE correo = ?";
        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, activo);
            ps.setString(2, correo);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            return false;
        }
    }

    private Usuario mapear(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setNombre(rs.getString("nombre"));
        u.setCorreo(rs.getString("correo"));
        u.setPassword(rs.getString("password"));
        u.setCodigo(rs.getString("codigo"));
        u.setVerificado(rs.getBoolean("verificado"));
        u.setActivo(rs.getBoolean("activo"));
        return u;
    }
}
