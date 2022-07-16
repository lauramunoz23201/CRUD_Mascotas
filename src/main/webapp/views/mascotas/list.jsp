<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.proyectomascotasfba.entities.Mascota" %>
<%@ page import="java.util.ArrayList" %>

<%
    ArrayList<Mascota> listMascotas = (ArrayList<Mascota>)request.getAttribute("mascotas");
%>

<html>
<head>
    <title>Lista de mascotas</title>
</head>
<body>
<table id="tbl_mascotas">
    <thead>
    <tr>
        <th>Identificación</th>
        <th>Nombre</th>
        <th>Descripción</th>
        <th>Raza</th>
        <th>Vacunado</th>
        <th>Esterilizado</th>
        <th>Edad</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Mascota x: listMascotas){
    %>
    <tr>
        <td><%= x.getId() %></td>
        <td><%= x.getNombre() %></td>
        <td><%= x.getDescripcion() %></td>
        <td><%= x.getRaza() %></td>
        <td><%= x.getVacunado() %></td>
        <td><%= x.getEsterilizado() %></td>
        <td><%= x.getEdad() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
