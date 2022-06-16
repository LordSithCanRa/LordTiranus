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
import modelo.Usuario;

/**
 *
 * @author rcane
 */
@WebServlet(name = "ModificarEmail", urlPatterns = {"/ModificarEmail"})
public class ModificarEmail extends HttpServlet {

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
        String error = null;//Envio de errores
        String nuevoEmail = request.getParameter("email");
        
        if(nuevoEmail == null || nuevoEmail.isEmpty()){//Si el cambpo esta vacio
            error = "El email no puede estar vacio";
        }else{
            try{
                HttpSession sesion = request.getSession();
                Usuario u = (Usuario)sesion.getAttribute("usuario");
                daoSistema.modificarEmail(nuevoEmail, u);//Cambiamos el email del usuario
            }catch(SQLException e){
                error = "Error al modificar el email ERROR-"+e.getErrorCode()+"["+e.getMessage()+"]";
            }
        }
        //Redirección y mensajes
        if(error != null){
            String mensaje = URLEncoder.encode(error, "latin1");
            response.sendRedirect(response.encodeRedirectURL("ObtenerInicio?error="
                    + mensaje));
        }else{
            String mensaje = URLEncoder.encode("Email modificado correctamente", "latin1");
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
