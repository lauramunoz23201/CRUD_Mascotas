<%@ page import="com.example.proyectomascotasfba.entities.Mascota" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    ArrayList<Mascota> mascotas = new ArrayList();
%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <title>Mascotas</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>

<form role="form" action="SrvlMascota" method="post">
    <fieldset>
        <legend>Información de la mascota</legend>

        <label>Identificación</label>
        <input type="text" name="id" id="id"/><br/><br/>

        <label>Nombre</label>
        <input type="text" name="nombre" id="nombre"/><br/><br/>

        <label>Descripcion</label>
        <input type="text" name="descripcion" id="descripcion"/><br/><br/>

        <label>Raza</label>
        <input type="text" name="raza" id="raza"/><br/><br/>

        <label>Vacunado</label>
        <input type="text" name="vacunado" id="vacunado"/><br/><br/>

        <label>Esterilizado</label>
        <input type="text" name="estirilizado" id="esterilizado"/><br/><br/>

        <label>Edad</label>
        <input type="text" name="edad" id="edad"/><br/><br/>

        <input type="submit" value="Guardar"/>
    </fieldset>
</form>

<form role="form" action="SrvlMascota" method="delete">
    <fieldset>
        <legend>Eliminar mascota</legend>

        <label>Identificación</label>
        <input type="text" name="idM" id="idM"/><br/><br/>

        <input type="submit" value="Borrar"/>
    </fieldset>
</form>

</body>
</html>