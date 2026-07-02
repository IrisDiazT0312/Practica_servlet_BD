<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Alumno"%>
<%@page import="dao.DAOAlumno"%>
<%!
    DAOAlumno lista = new DAOAlumno();
    Alumno edit = null;
%>

<%
    edit = null;
    if (request.getAttribute("edit") != null)
    {
        edit = (Alumno) request.getAttribute("edit");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BD con Servlets</title>
    </head>
    <style>
      *
      {
        box-sizing: border-box;
      }

    body 
    {
        font-family: 'Segoe UI', Arial, sans-serif;
        background-color: #fbe3ec;
        margin: 0;
        padding: 40px 20px;
        color: #2c3e50;
    }

    .contenedor
    {
        max-width: 850px;
        margin: 0 auto;
        display: flex;
        flex-direction: column;
        gap: 30px;
    }

    .tarjeta 
    {
        background-color: #ffffff;
        border-radius: 16px;
        box-shadow: 0 10px 30px rgba(219, 112, 147, 0.2);
        overflow: hidden;
    }

    #form_registro 
    {
        position: relative;
        padding: 55px 40px 40px;
        background: linear-gradient(160deg, #f3a8c6, #ec8fb8);
    }

    #form_registro::before 
    {
        content: "";
        position: absolute;
        top: 20px;
        left: 24px;
        width: 12px;
        height: 12px;
        border-radius: 50%;
        background-color: #ff5f57;
        box-shadow: 20px 0 0 #ffbd2e, 40px 0 0 #28c840;
    }

    #form_registro h2 
    {
        margin-top: 0;
        color: #ffffff;
        font-size: 20px;
    }

    #form_registro form input[type="text"] 
    {
        display: block;
        width: 100%;
        padding: 14px 16px;
        margin-bottom: 16px;
        border: none;
        border-radius: 8px;
        font-size: 14px;
        background-color: #ffffff;
    }

    #form_registro form input[type="text"]:focus 
    {
        outline: none;
        box-shadow: 0 0 0 3px rgba(255, 255, 255, 0.6);
    }

    .btn,input[type="submit"] 
    {
        background-color: #ffffff;
        color: #e0629b;
        border: none;
        padding: 12px 26px;
        border-radius: 8px;
        font-size: 14px;
        font-weight: 600;
        cursor: pointer;
    }

    .btn:hover,input[type="submit"]:hover 
    {
        background-color: #fdeef4;
    }


    .tarjeta:not(#form_registro) 
    {
        padding: 25px 30px;
    }

    table 
    {
        width: 100%;
        border-collapse: collapse;
        margin-top: 10px;
    }

    caption
    {
        text-align: left;
        font-weight: bold;
        font-size: 16px;
        margin-bottom: 10px;
        color: #1f2d3d;
    }

    thead th 
    {
        background-color: #ec8fb8;
        color: #ffffff;
        text-align: left;
        padding: 10px;
    }

    tbody td 
    {
        padding: 10px;
        border-bottom: 1px solid #f3d9e6;
    }

    tbody tr:nth-child(even) 
    {
        background-color: #fdf3f8;
    }

    tbody tr:hover 
    {
        background-color: #fbe3ec;
    }

    table form 
    {
        display: inline;
        margin: 0;
    }

    table input[type="submit"] 
    {
        padding: 6px 12px;
        font-size: 13px;
        color: #ffffff;
        background-color: #4a90e2;
    }

    table td:nth-last-child(2) input[type="submit"] 
    {
        background-color: #f0ad4e;
    }

    table td:last-child input[type="submit"] 
    {
        background-color: #e05252;
    }
    </style>
    
    <body>
        <div id="form_registro">
            <h2><%= (edit != null) ? "Modificar calificaciones" : "Registro de calificaciones" %></h2>

            <form method="post" action="SAlumno">
                <input type="hidden" name="accion" id="accion" value="<%= (edit != null) ? "Modificar" : "Agregar" %>" />
                <input type="hidden" name="tfNLOld" id="tfNLOld" value="<%= (edit != null) ? edit.getNL() : "" %>" />

                <input type="text" name="tfNL" id="tfNL" value="<%= (edit != null) ? edit.getNL() : "" %>" placeholder="NL" required />
                <input type="text" name="tfNombre" id="tfNombre" value="<%= (edit != null) ? edit.getNombre() : "" %>" placeholder="Nombre" required />
                <input type="text" name="tfPaterno" id="tfPaterno" value="<%= (edit != null) ? edit.getPaterno() : "" %>" placeholder="Paterno" required />
                <input type="text" name="tfMaterno" id="tfMaterno" value="<%= (edit != null) ? edit.getMaterno() : "" %>" placeholder="Materno" required />

                <input type="submit" value="<%= (edit != null) ? "Modificar" : "Agregar" %>" />
            </form>
        </div>

        <div>
            <%= lista.mostrar() %>
        </div>
    </body>
</html>
