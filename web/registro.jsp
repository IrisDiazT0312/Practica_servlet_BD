<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registro de Usuario</title>

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

.tarjeta{
    width:460px;
    background:#fff;
    border-radius:18px;
    overflow:hidden;
    box-shadow:0 15px 35px rgba(0,0,0,.12);
}

h2{
    background:linear-gradient(160deg,#f3a8c6,#ec8fb8);
    color:white;
    text-align:center;
    padding:28px;
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
    box-shadow:18px 0 #ffbd2e,
               36px 0 #28c840;
}

.error{
    margin:20px;
    padding:12px;
    background:#fdecec;
    color:#c0392b;
    border-radius:8px;
    text-align:center;
}

form{
    padding:30px;
}

input[type=text],
input[type=email],
input[type=password]{
    width:100%;
    padding:14px;
    margin-bottom:16px;
    border:1px solid #f3d4e1;
    border-radius:8px;
    background:#fafafa;
    transition:.3s;
}

input:focus{
    outline:none;
    border-color:#ec8fb8;
    box-shadow:0 0 0 3px rgba(236,143,184,.25);
}

input[type=submit]{
    width:100%;
    padding:14px;
    background:#ec8fb8;
    color:white;
    border:none;
    border-radius:8px;
    font-size:15px;
    font-weight:600;
    cursor:pointer;
    transition:.3s;
}

input[type=submit]:hover{
    background:#df6fa4;
}

.footer{
    text-align:center;
    padding:20px;
    border-top:1px solid #f3d4e1;
}

.footer a{
    color:#df6fa4;
    font-weight:600;
    text-decoration:none;
}

.footer a:hover{
    text-decoration:underline;
}

br{
    display:none;
}

</style>

</head>

<body>

<div class="tarjeta">

<h2>Crear cuenta</h2>

<% if (request.getAttribute("error") != null) { %>
    <div class="error">
        <%= request.getAttribute("error") %>
    </div>
<% } %>

<form method="post" action="SRegistro">

    <input type="text" name="tfNombre" placeholder="Nombre completo" required>

    <input type="email" name="tfCorreo" placeholder="Correo electrónico" required>

    <input type="password" name="tfPassword" placeholder="Contraseña" required>

    <input type="password" name="tfPassword2" placeholder="Confirmar contraseña" required>

    <input type="submit" value="Registrarme">

</form>

<div class="footer">
    ¿Ya tienes cuenta?
    <a href="login.jsp">Inicia sesión</a>
</div>

</div>

</body>
</html>
