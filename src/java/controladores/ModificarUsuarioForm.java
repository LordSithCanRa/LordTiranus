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
import modelo.Usuario;

/**
 *
 * @author rcane
 */
@WebServlet(name = "ModificarUsuarioForm", urlPatterns = {"/admin/ModificarUsuarioForm"})
public class ModificarUsuarioForm extends HttpServlet {

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
        String usuario = request.getParameter("usuarioMod");
        String email = request.getParameter("email");
        String monedas = request.getParameter("monedas");
        String nivel = request.getParameter("nivel");
        String recordPuntos = request.getParameter("recordPuntos");
        String clave = request.getParameter("clave");
        boolean administrador = false;
        if(request.getParameter("administrador")!=null){
         administrador = true;
        }
        String error = null;
        if(email == null || email.isEmpty() || monedas == null || monedas.isEmpty()
                || nivel == null || nivel.isEmpty() || recordPuntos == null || recordPuntos.isEmpty()
                || clave == null || clave.isEmpty()){
            error = "Los campos no pueden estar vacios";
        }else{
            
            try{
                Usuario uMod = daoSistema.obtenerUsuario(usuario);
                uMod.setClave(clave);
                uMod.setAdmin(administrador);
                uMod.setEmail(email);
                uMod.setMonedas(Integer.parseInt(monedas));
                uMod.setNivel(Integer.parseInt(nivel));
                uMod.setRecordPuntos(Integer.parseInt(recordPuntos));
                daoSistema.modificarUsuario(uMod);
            }catch(SQLException e){
                error = "Error en la base de datos: " + e.getMessage();
            }
        }
        
        if(error != null){//Si hay algun error
            String mensaje = URLEncoder.encode(error, "latin1");
            response.sendRedirect(response.encodeRedirectURL("ModificarUsuario?media="
                    + mensaje+"&usuarioMod="+usuario));
            
        }else{
            response.sendRedirect("ModificarUsuario?usuarioMod="+usuario);
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
