package com.example.proyectomascotasfba.servlets;

import com.example.proyectomascotasfba.entities.Mascota;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonParser.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@WebServlet(name = "SrvlMascota", value = "/SrvlMascota")
public class SrvlMascota extends HttpServlet {

    public static ArrayList<Mascota> MASCOTAS = new ArrayList<>(Arrays.asList(
            new Mascota("1", "Pepito","Jugueton", "Criollo",true,false,2),
            new Mascota("2", "Lucas","Tierno", "Criollo",false,false,4)
    ));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");

        if(request.getParameter("id")==null){
            for (int i=0; i<this.MASCOTAS.size();i++){
                out.print(MASCOTAS.get(i).getId()+" ");
                out.print(MASCOTAS.get(i).getNombre()+" ");
                out.print(MASCOTAS.get(i).getDescripcion()+" ");
                out.print(MASCOTAS.get(i).getRaza()+" ");
                out.print(MASCOTAS.get(i).getVacunado()+" ");
                out.print(MASCOTAS.get(i).getEsterilizado()+" ");
                out.print(MASCOTAS.get(i).getEdad()+" ");
                out.println();
            }
        }else{
            Mascota mascota =this.searchMascota(request.getParameter("id"));
            out.print(mascota.getId()+" ");
            out.print(mascota.getNombre()+" ");
            out.print(mascota.getDescripcion()+" ");
            out.print(mascota.getRaza()+" ");
            out.print(mascota.getVacunado()+" ");
            out.print(mascota.getEsterilizado()+" ");
            out.print(mascota.getEdad()+" ");
        }
        */
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");
        GsonBuilder gsonBuilder=new GsonBuilder();

        Gson gson=gsonBuilder.create();

        if(request.getParameter("mascotaId")==null){
            out.print(gson.toJson(this.MASCOTAS));
        }else {
            Mascota mas=this.searchMascota(request.getParameter("mascotaId"));
            out.print(gson.toJson(mas));
        }
        out.flush();
    }

    private Mascota searchMascota(String mascotaId) {
        /*
        for(int i=0; i<this.MASCOTAS.size();i++){
            if(this.MASCOTAS.get(i).getId().equals(id)){
                return this.MASCOTAS.get(i);
            }
        }
        return null;

        */
        for(int i=0;i<MASCOTAS.size();++i){
            if(MASCOTAS.get(i).getId().equals(mascotaId)){
                return (this.MASCOTAS).get(i);
            }
        }
        return null;
    }

    private String getParamsFromPost(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();
        String params = sb.toString();
        return params;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        Mascota mascota = new Mascota(
                request.getParameter("id"),
                request.getParameter("nombre"),
                request.getParameter("descripcion"),
                request.getParameter("raza"),
                request.getParameter("vacunado"),
                request.getParameter("esterilizado"),
                request.getParameter("edad")
        );

        this.MASCOTAS.add(mascota);

        request.setAttribute("mascotas", this.MASCOTAS);

        request.getRequestDispatcher("views/mascotas/list.jsp").forward(request,response);
        */

        ServletOutputStream out=response.getOutputStream();
        GsonBuilder gsonBuilder=new GsonBuilder();
        Gson gson=gsonBuilder.create();
        JsonObject body= JsonParser.parseString(this.getParamsFromPost(request)).getAsJsonObject();
        int min=0, max=10000;
        Random rd=new Random();

        Mascota mascota = new Mascota(
                String.valueOf(rd.nextInt(max-min)+min),
                body.get("nombre").getAsString(),
                body.get("descripcion").getAsString(),
                body.get("raza").getAsString(),
                body.get("vacunado").getAsBoolean(),
                body.get("esterilizado").getAsBoolean(),
                body.get("edad").getAsInt()
        );

        this.MASCOTAS.add(mascota);
        out.print(gson.toJson(mascota));
        out.flush();

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");

        if(request.getParameter("idM")==null){
            out.print("La mascota no fue encontrada en la lista.");
        }else {
            Mascota mascota=this.searchMascotaDelete(request.getParameter("idM"));
            out.print("La mascota con el id " + mascota.getId() + " fue eliminada de la lista.");
            this.MASCOTAS.remove(mascota);
        }
        */

        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");
        GsonBuilder gsonBuilder=new GsonBuilder();

        Gson gson=gsonBuilder.create();

        if(request.getParameter("mascotaId")==null){
            out.print(gson.toJson(this.MASCOTAS));
        }else {
            Mascota mascota=this.searchMascota(request.getParameter("mascotaId"));
            this.MASCOTAS.remove(mascota);
            out.print(gson.toJson(mascota));
        }
        out.flush();

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");
        GsonBuilder gsonBuilder=new GsonBuilder();

        Gson gson=gsonBuilder.create();

        JsonObject body= JsonParser.parseString(this.getParamsFromPost(request)).getAsJsonObject();

        if(request.getParameter("mascotaId")==null){
            out.print(gson.toJson(this.MASCOTAS));
        }else {
            Mascota mascota=this.searchMascota(request.getParameter("mascotaId"));
            mascota.setId(body.getAsString());
            mascota.setNombre(body.getAsString());
            mascota.setDescripcion(body.getAsString());
            mascota.setRaza(body.getAsString());
            mascota.setVacunado(body.getAsBoolean());
            mascota.setEsterilizado(body.getAsBoolean());
            mascota.setEdad(body.getAsInt());
            out.println(gson.toJson("Mascota actualizada"));
        }
        out.flush();

    }

}