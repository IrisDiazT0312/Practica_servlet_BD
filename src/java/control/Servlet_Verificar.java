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

import java.io.IOException;

@WebServlet("/SVerificar")
public class Servlet_Verificar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        String correo = request.getParameter("tfCorreo");

        DAOUsuario dao = new DAOUsuario();

        if ("Reenviar".equals(accion)) {
            Usuario u = dao.buscarPorCorreo(correo);
            if (u != null) {
                String nuevoCodigo = EmailUtil.generarCodigo();
                dao.actualizarCodigo(correo, nuevoCodigo);
                try {
                    EmailUtil.enviarCodigoVerificacion(correo, u.getNombre(), nuevoCodigo);
                    request.setAttribute("mensaje", "Se envió un nuevo código a tu correo.");
                } catch (MessagingException e) {
                    request.setAttribute("error", "No se pudo reenviar el código.");
                }
            }
            request.setAttribute("correo", correo);
            forward(request, response, "/verificar.jsp");
            return;
        }

        // accion = "Confirmar"
        String codigo = request.getParameter("tfCodigo");
        boolean ok = dao.confirmarCodigo(correo, codigo);

        if (ok) {
            request.setAttribute("mensaje", "¡Correo verificado! Ya puedes iniciar sesión.");
            forward(request, response, "/login.jsp");
        } else {
            request.setAttribute("error", "Código incorrecto. Verifica e intenta de nuevo.");
            request.setAttribute("correo", correo);
            forward(request, response, "/verificar.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forward(request, response, "/verificar.jsp");
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp, String path)
            throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
        rd.forward(req, resp);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
