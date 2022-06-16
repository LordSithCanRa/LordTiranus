/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.daoSistema;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Comentarios;

/**
 *
 * @author rcane
 */
@WebServlet(name = "InsertarComentario", urlPatterns = {"/InsertarComentario"})
public class InsertarComentario extends HttpServlet {

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
        String error = null;
        String comentario = request.getParameter("comentario");
        String usuarioDestinatario = request.getParameter("usuarioDestinatario");
        String usuarioRemitente = request.getParameter("usuarioRemitente");
        if(comentario == null || comentario.length()==0 || comentario.trim().equals("")){
            error = "Debes escribir un comentario para poder insertarlo.";
        }
        
        if(error == null){
            Timestamp fechaEscrito;
            Long datetime = System.currentTimeMillis();
            fechaEscrito = new Timestamp(datetime);
            try{
                Comentarios c = new Comentarios(usuarioRemitente, usuarioDestinatario, 0, fechaEscrito, 0, comentario);
                daoSistema.insertarComentario(c);
            }catch(SQLException e){
                error = e.getMessage();

            }
        }
            
        if(error != null){
            String mensaje = URLEncoder.encode(error, "latin1");
            response.sendRedirect(response.encodeRedirectURL("ObtenerInicio?error="
                    + mensaje));
        }else{
            response.sendRedirect("MostrarUsuario?usuarioV="+usuarioDestinatario);
        }
        
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
