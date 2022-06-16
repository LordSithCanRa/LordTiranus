/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.daoSistema;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Fondos;
import modelo.ReinicioSesion;
import modelo.Skin;

/**
 *
 * @author rcane
 */
@WebServlet(name = "Equipar", urlPatterns = {"/Equipar"})
public class Equipar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("Fondo")!=null){
            int idFondo = Integer.parseInt(request.getParameter("idFondo"));
            Fondos fondo = null;
            try{
                fondo = daoSistema.obtenerFondo(idFondo);
            }catch(SQLException e){
                System.err.println(e.getMessage());
            }
            ReinicioSesion.reiniciarFondoInicial(fondo, request);
        }else if(request.getParameter("Skin")!=null){
            int idSkin = Integer.parseInt(request.getParameter("idSkin"));
            Skin skin = null;
            try{
                skin = daoSistema.obtenerSkin(idSkin);
            }catch(SQLException e){
                System.err.println(e.getMessage());
            }
            ReinicioSesion.reiniciarSkinInicial(skin, request);
        }
        response.sendRedirect("ObtenerInicio");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
