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
import modelo.Fondos;
import modelo.Skin;
import modelo.Usuario;

/**
 *
 * @author rcane
 */
@WebServlet(name = "LogIn", urlPatterns = {"/LogIn"})
public class LogIn extends HttpServlet {

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
        String usuario = request.getParameter("usuarioLog").trim();
        String password = request.getParameter("passwordLog").trim();
        String error = null;
        if(usuario != null && !usuario.isEmpty() && password != null && !password.isEmpty()){
            Usuario u = null;
            Skin skinInicial = null;
            Fondos fondoInicial = null;
            try{
                u = daoSistema.obtenerUsuario(usuario);
                skinInicial = daoSistema.obtenerSkin(1);
                fondoInicial = daoSistema.obtenerFondo(1);
            }catch(SQLException ex){
                error = ex.getClass().getName() + ":" + ex.getMessage();
            }
            if(u!=null){
                if (u.getClave().equals(password)) {
                    HttpSession sesion = request.getSession();
                    sesion.setAttribute("usuario", u);//Guardamos el usuario en la sesion
                    //Guardamos en la sesion la skin y el fondo que usaremos inicialmente al jugar
                    sesion.setAttribute("skinInicial", skinInicial);
                    sesion.setAttribute("fondoInicial", fondoInicial);
                } else {
                    error = "Clave incorrecta ";
                }
            }else{
                error = "Usuario y/o clave incorrectos";
            }
            
        }else{
            error = "Tienes que rellenar ambos campos";
        }
        if(error != null){//Si hay algun error
            String mensaje = URLEncoder.encode(error, "latin1");
            response.sendRedirect(response.encodeRedirectURL("index.jsp?media="
                    + mensaje));
            
        }else{//Si el log ha sido correcto
            String mensaje = URLEncoder.encode("Bienvenido de nuevo, "+usuario, "latin1");
            
            response.sendRedirect(response.encodeRedirectURL("ObtenerInicio?info="
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
