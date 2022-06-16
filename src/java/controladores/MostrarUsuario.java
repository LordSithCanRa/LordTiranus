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
import javax.servlet.http.HttpSession;
import modelo.Comentarios;
import modelo.Trofeo;
import modelo.Usuario;

/**
 *
 * @author rcane
 */
@WebServlet(name = "MostrarUsuario", urlPatterns = {"/MostrarUsuario"})
public class MostrarUsuario extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        String usuarioV = request.getParameter("usuarioV");
        String error = request.getParameter("error");
        //Usuario que vamos a mostrar
        Usuario uMostrado = null;
        try{
            uMostrado = daoSistema.obtenerUsuario(usuarioV);
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        
        //Obtenemos los trofeos
        List<Trofeo> trofeos = null;
        trofeos = Trofeo.obtenerTrofeos(uMostrado);
        
        //Obtenemos los comentarios del usuario
        List<Comentarios> comentarios = null;
        try{
            comentarios = daoSistema.obtenerComentariosUsuario(uMostrado);
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        
        request.setAttribute("uMostrado", uMostrado);
        request.setAttribute("trofeos", trofeos);
        request.setAttribute("comentarios", comentarios);
        request.setAttribute("error", error);
        request.getRequestDispatcher("verUsuario.jsp").forward(request, response);
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
