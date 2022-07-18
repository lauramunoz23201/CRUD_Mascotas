package com.example.proyectomascotasfba.servlets;

import com.example.proyectomascotasfba.entities.Mascota;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "SrvlMascota", value = "/SrvlMascota")
public class SrvlMascota extends HttpServlet {

    public static ArrayList<Mascota> MASCOTAS = new ArrayList<>(Arrays.asList(
            new Mascota(1, "Pepito","Cariñoso", "Criollo",true,true,2),
            new Mascota(2, "Lucas","Tierno", "Criollo",false,true,4)
    ));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");

        out.println("Identificacion de la mascota: " + request.getParameter("id"));
        out.println("Nombre de la mascota: " + request.getParameter("nombre"));
        int id = Integer.parseInt(request.getParameter("id"));
        int nombre = Integer.parseInt(request.getParameter("nombre"));
        out.println("El id es: " + (id));

        out.println("El token es: " + request.getHeader("Token"));
    }

}
