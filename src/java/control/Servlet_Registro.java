package control;

import dao.DAOUsuario;
import jakarta.mail.MessagingException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import util.EmailUtil;
import util.PasswordUtil;

import java.io.IOException;

@WebServlet("/SRegistro")
public class Servlet_Registro extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String nombre = request.getParameter("tfNombre");
        String correo = request.getParameter("tfCorreo");
        String pass1  = request.getParameter("tfPassword");
        String pass2  = request.getParameter("tfPassword2");

        DAOUsuario dao = new DAOUsuario();

        if (pass1 == null || !pass1.equals(pass2)) {
            request.setAttribute("error", "Las contraseñas no coinciden.");
            forward(request, response, "/registro.jsp");
            return;
        }

        if (dao.buscarPorCorreo(correo) != null) {
            request.setAttribute("error", "Ese correo ya está registrado.");
            forward(request, response, "/registro.jsp");
            return;
        }

        String codigo = EmailUtil.generarCodigo();

        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setPassword(PasswordUtil.hash(pass1));
        u.setCodigo(codigo);

        boolean guardado = dao.registrar(u);

        if (!guardado) {
            request.setAttribute("error", "No se pudo registrar el usuario. Intenta de nuevo.");
            forward(request, response, "/registro.jsp");
            return;
        }

        try {
            EmailUtil.enviarCodigoVerificacion(correo, nombre, codigo);
        } catch (MessagingException e) {
            // El usuario ya quedó guardado; puede pedir "Reenviar código" desde verificar.jsp
            request.setAttribute("error", "Usuario creado, pero no se pudo enviar el correo. "
                    + "Usa la opción de reenviar código en la pantalla de verificación.");
        }

        request.setAttribute("correo", correo);
        forward(request, response, "/verificar.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forward(request, response, "/registro.jsp");
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp, String path)
            throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
        rd.forward(req, resp);
    }
}
