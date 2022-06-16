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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.ReinicioSesion;
import modelo.Usuario;

/**
 *
 * @author rcane
 */
@WebServlet(name = "ObtenerExperiencia", urlPatterns = {"/ObtenerExperiencia"})
public class ObtenerExperiencia extends HttpServlet {

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
        String usuarioExp = request.getParameter("usuarioExp");
        int exp = Integer.parseInt(request.getParameter("experiencia"));
        //Strings para mensajes
        String error = null;
        String pro = null;
        String confirm = null;
        try{
            Usuario u = daoSistema.obtenerUsuario(usuarioExp);
            if(u.getNivel() >= (daoSistema.obtenerUltimoNivel()-1)){//Menos uno porque el método obtiene el último nivel para agregar
                pro = "Has llegado al maximo de nivel";
            }else{
                confirm = daoSistema.comprobarSubidaNivel(u, exp);
                u = daoSistema.obtenerUsuario(usuarioExp);//Actualizamos los datos del cusuario
                ReinicioSesion.reiniciarUsuario(u, request);//Reiniciamos la sesion para que se muestren los datos
            }
        }catch(SQLException e){
            error = e.getMessage();
        }
        
        
        if(error != null){
            String mensaje = URLEncoder.encode(error, "latin1");
            response.sendRedirect(response.encodeRedirectURL("ObtenerInicio?error="
                    + mensaje));
        }else if(pro != null){
            String mensaje = URLEncoder.encode(pro, "latin1");
            response.sendRedirect(response.encodeRedirectURL("ObtenerInicio?pro="
                    + mensaje));
        }else{
            String mensaje = URLEncoder.encode(confirm, "latin1");
            response.sendRedirect(response.encodeRedirectURL("ObtenerInicio?confirm="
                    + mensaje));
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
