package control;

import dao.DAOUsuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;
import util.PasswordUtil;

import java.io.IOException;

@WebServlet("/SLogin")
public class Servlet_Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String correo = request.getParameter("tfCorreo");
        String pass   = request.getParameter("tfPassword");

        DAOUsuario dao = new DAOUsuario();
        Usuario u = dao.buscarPorCorreo(correo);

        if (u == null || !PasswordUtil.coincide(pass, u.getPassword())) {
            request.setAttribute("error", "Correo o contraseña incorrectos.");
            forward(request, response, "/login.jsp");
            return;
        }

        if (!u.isVerificado()) {
            request.setAttribute("error", "Debes confirmar tu correo antes de iniciar sesión.");
            request.setAttribute("correo", correo);
            forward(request, response, "/verificar.jsp");
            return;
        }

        if (!u.isActivo()) {
            request.setAttribute("error", "Tu cuenta está inactiva. Contacta al administrador.");
            forward(request, response, "/login.jsp");
            return;
        }

        // Login correcto: se crea la sesión
        HttpSession session = request.getSession();
        session.setAttribute("usuario", u);

        response.sendRedirect("alumnos.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forward(request, response, "/login.jsp");
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
