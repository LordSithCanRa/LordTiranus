/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.daoSistema;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Clasificacion;
import modelo.Comentarios;
import modelo.Fondos;
import modelo.Usuario;

/**
 *
 * @author rcane
 */
@WebServlet(name = "ObtenerInicio", urlPatterns = {"/ObtenerInicio"})
public class ObtenerInicio extends HttpServlet {

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
        Usuario u = (Usuario) sesion.getAttribute("usuario");
        String error = request.getParameter("error");
        String confirm = request.getParameter("confirm");
        //Obtenemos el top 10
        List<Clasificacion> clasif = null;
        try{
            clasif = daoSistema.obtenerTop10();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        
       
        //ObtenerComentarios redactados por el usuario
        List<Comentarios> comentariosEscritos = null;
        try{
            comentariosEscritos = daoSistema.obtenerComentariosUsuario2(u);
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        
        request.setAttribute("top10", clasif);
        request.setAttribute("comentariosEscritos", comentariosEscritos);
        //CONDICIONALES PARA LOS MENSAJES
        if(confirm!=null){
            request.setAttribute("confirm", confirm);
        }
        if(error!=null){
            request.setAttribute("error", error);
        }
        request.getRequestDispatcher("inicio.jsp").forward(request, response);
        
        
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
