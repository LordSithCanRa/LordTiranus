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
@WebServlet(name = "ComprarMonedas", urlPatterns = {"/ComprarMonedas"})
public class ComprarMonedas extends HttpServlet {

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
        //ESTE SERVLET ESTA HECHO APARTE DE LA COMPRA DE LOS FONDOS U LAS SKINS
        //YA QUE ESTO SE CONVERTIRÁ EN UNA PASARELA DE PAGO
        int monedasObtener = Integer.parseInt(request.getParameter("monedas"));
        String usuario = request.getParameter("usuario");
        try{
            Usuario u = daoSistema.obtenerUsuario(usuario);
            daoSistema.agregarMonedas(u, monedasObtener);
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        try{
            Usuario usuarioAct = daoSistema.obtenerUsuario(usuario);
            ReinicioSesion.reiniciarUsuario(usuarioAct, request);
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        String mensaje = URLEncoder.encode("Monedas compradas correctamente", "latin1");
            response.sendRedirect(response.encodeRedirectURL("ObtenerTienda?confirm="
                    + mensaje));
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
