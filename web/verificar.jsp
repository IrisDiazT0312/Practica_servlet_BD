<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verificar Correo</title>
<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
}

body{
    font-family:'Segoe UI',Arial,sans-serif;
    background:linear-gradient(135deg,#fbe3ec,#f7d0df);
    display:flex;
    justify-content:center;
    align-items:center;
    min-height:100vh;
    color:#2c3e50;
}

body::before{
    content:"";
    position:fixed;
    top:-150px;
    left:-150px;
    width:520px;
    height:520px;
    background:linear-gradient(135deg,#f3a8c6,#ec8fb8);
    border-radius:50%;
    opacity:.22;
    z-index:-1;
}

body::after{
    content:"";
    position:fixed;
    bottom:-170px;
    right:-170px;
    width:520px;
    height:520px;
    background:linear-gradient(135deg,#ec8fb8,#f3a8c6);
    border-radius:50%;
    opacity:.18;
    z-index:-1;
}

h2{
    width:460px;
    background:linear-gradient(160deg,#f3a8c6,#ec8fb8);
    color:white;
    padding:28px;
    text-align:center;
    border-radius:18px 18px 0 0;
    box-shadow:0 10px 25px rgba(219,112,147,.25);
    position:relative;
}

h2::before{
    content:"";
    position:absolute;
    top:16px;
    left:20px;
    width:11px;
    height:11px;
    border-radius:50%;
    background:#ff5f57;
    box-shadow:
        18px 0 #ffbd2e,
        36px 0 #28c840;
}

p{
    width:460px;
    background:white;
    text-align:center;
    padding:12px 35px;
    box-shadow:0 15px 35px rgba(0,0,0,.10);
    color:#555;
    line-height:1.5;
}

form{
    width:460px;
    background:white;
    padding:0 35px 20px;
    box-shadow:0 15px 35px rgba(0,0,0,.10);
}

form:last-of-type{
    padding-top:0;
    padding-bottom:35px;
    border-radius:0 0 18px 18px;
}

input[type=email],
input[type=text]{
    width:100%;
    padding:14px 16px;
    margin-bottom:18px;
    border:1px solid #f3d4e1;
    border-radius:8px;
    background:#fafafa;
    transition:.3s;
    font-size:14px;
}

input:focus{
    outline:none;
    background:white;
    border-color:#ec8fb8;
    box-shadow:0 0 0 3px rgba(236,143,184,.25);
}

input[type=submit]{
    width:100%;
    padding:14px;
    border:none;
    border-radius:8px;
    background:#ec8fb8;
    color:white;
    font-size:15px;
    font-weight:600;
    cursor:pointer;
    transition:.3s;
}

input[type=submit]:hover{
    background:#df6fa4;
    transform:translateY(-2px);
}

form:last-of-type input[type=submit]{
    background:#ffffff;
    color:#df6fa4;
    border:2px solid #df6fa4;
}

form:last-of-type input[type=submit]:hover{
    background:#df6fa4;
    color:white;
}

p[style*="red"]{
    background:#fdecec;
    color:#c0392b !important;
    border-left:5px solid #e74c3c;
    margin-bottom:10px;
}

p[style*="green"]{
    background:#ecfdf3;
    color:#27ae60 !important;
    border-left:5px solid #27ae60;
    margin-bottom:10px;
}

br{
    display:none;
}

</style>
    </head>
    <body>
        <h2>Verifica tu correo</h2>
        <p>Enviamos un código de 6 dígitos a tu correo. Ingrésalo abajo para activar tu cuenta.</p>

        <% if (request.getAttribute("error") != null) { %>
            <p style="color:red;"><%= request.getAttribute("error") %></p>
        <% } %>
        <% if (request.getAttribute("mensaje") != null) { %>
            <p style="color:green;"><%= request.getAttribute("mensaje") %></p>
        <% } %>

        <%
            String correo = (request.getAttribute("correo") != null)
                    ? (String) request.getAttribute("correo") : "";
        %>

        <form method="post" action="SVerificar">
            <input type="hidden" name="accion" value="Confirmar" />
            <input type="email" name="tfCorreo" value="<%= correo %>" placeholder="Correo electrónico" required /><br/><br/>
            <input type="text" name="tfCodigo" placeholder="Código de 6 dígitos" maxlength="6" required /><br/><br/>
            <input type="submit" value="Confirmar" />
        </form>

        <form method="post" action="SVerificar" style="margin-top:10px;">
            <input type="hidden" name="accion" value="Reenviar" />
            <input type="hidden" name="tfCorreo" value="<%= correo %>" />
            <input type="submit" value="Reenviar código" />
        </form>
    </body>
</html>