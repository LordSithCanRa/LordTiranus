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
import modelo.Email;
import modelo.Usuario;

/**
 *
 * @author rcane
 */
@WebServlet(name = "EnviarEmail", urlPatterns = {"/admin/EnviarEmail"})
public class EnviarEmail extends HttpServlet {

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
        String usuarioEm = request.getParameter("usuarioEm");
        String error = null;
        if(usuarioEm!=null){//Obtenemos el usuario y redireccionamos al formulario
            Usuario uEm = null;
            try{
                uEm = daoSistema.obtenerUsuario(usuarioEm);
            }catch(SQLException e){
                error = e.getMessage();
            }
            request.setAttribute("uEm", uEm);
            request.setAttribute("media", error);
            request.getRequestDispatcher("enviarEmail.jsp").forward(request, response);
        }else{//Enviamos email
            String usuario = request.getParameter("usuario");
            String emailR = request.getParameter("emailAdm");
            Usuario u = null;
            String mensaje = null;
            try{
                u = daoSistema.obtenerUsuario(usuario);//Obtenemos al usuario al que le vamos a enviar el email
            }catch(SQLException e){
                error = e.getMessage();
            }
                Email email = new Email();
                email.setFrom(emailR);
                email.setSubject(request.getParameter("subject"));
                email.setTo(u.getEmail());
                email.setText(request.getParameter("text"));

            try{
                email.setEnviarEmail(email, request.getParameter("passwordAdm"));//Llamamos al método para enviar el email
            }catch(Exception e){
                error = e.getMessage();
            }
            
            if(error!=null){
                error = URLEncoder.encode(error , "latin1");
                response.sendRedirect("enviarEmail.jsp?error=" + error);
            }else{
                mensaje = URLEncoder.encode("Se ha enviado el mensaje a "+u.getUsuario() , "latin1");
                response.sendRedirect("enviarEmail.jsp?mensaje=" + mensaje);
            }
                

            
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
