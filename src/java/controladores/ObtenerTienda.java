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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Fondos;
import modelo.Skin;

/**
 *
 * @author rcane
 */
@WebServlet(name = "ObtenerTienda", urlPatterns = {"/ObtenerTienda"})
public class ObtenerTienda extends HttpServlet {

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
        String mensajeError = request.getParameter("media");
        String confirm = request.getParameter("confirm");
        //Obtenemos la lista de Fondos y Skins y la enviamos por separado
        List<Fondos> fondos = null;
        List<Skin> skins = null;
        try{
            skins = daoSistema.obtenerSkins();
            fondos = daoSistema.obtenerFondos();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        //Guardamos las listas de productos
        request.setAttribute("fondos", fondos);
        request.setAttribute("skins", skins);
        
        //ERRORES/CONFIRMACIONES DE COMPRA
        if(mensajeError!=null){
            request.setAttribute("error", mensajeError);
        }
        if(confirm!=null){
            request.setAttribute("confirm", confirm);
        }
        
        
        request.getRequestDispatcher("tienda.jsp").forward(request, response);
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
