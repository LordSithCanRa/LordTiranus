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
import modelo.Fondos;
import modelo.ReinicioSesion;
import modelo.Skin;
import modelo.Usuario;

/**
 *
 * @author rcane
 */
@WebServlet(name = "Comprar", urlPatterns = {"/Comprar"})
public class Comprar extends HttpServlet {

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
        String usuario = request.getParameter("usuario");
        if(request.getParameter("Fondo")!=null){//Compramos fondo
            int idFondo = Integer.parseInt(request.getParameter("idFondo"));
            try{
                Fondos f = daoSistema.obtenerFondo(idFondo);
                Usuario u = daoSistema.obtenerUsuario(usuario);
                boolean monedasInsf = daoSistema.quitarMonedas(u, f.getPrecio());
                if(!monedasInsf){//Si no hay monedas suficientes
                    error = "No tienes monedas suficientes";
                }else{
                    daoSistema.agregarFondoInventario(u, f);
                }
            }catch(SQLException e){
                if (e.getMessage().contains("Duplicate entry")) {//Si el usuario ya existe
                    error = "Ya tienes este fondo";
                } else {
                    error = "Error al obtener el fondo (" + e.getMessage() + ") COD-"+e.getErrorCode();
                }
            }
        }else if(request.getParameter("Skin")!=null){//Compramos skin
            int idSkin = Integer.parseInt(request.getParameter("idSkin"));
           try{
               Skin s = daoSistema.obtenerSkin(idSkin);
               Usuario u = daoSistema.obtenerUsuario(usuario);
               boolean monedasInsf = daoSistema.quitarMonedas(u, s.getPrecio());
               if(!monedasInsf){
                   error = "No tienes monedas suficientes";
               }else{
                   daoSistema.agregarSkinInventario(u, s);
               }
           }catch(SQLException es){
               if(es.getMessage().contains("Duplicate entry")){//Si el usuario ya existe
                    error = "Ya tienes esta skin";
                }else{
                    error = "Error al comprar el fondo (" + es.getMessage() + ") COD-"+es.getErrorCode();
                }
           }
        }
        try{
            Usuario usuarioAct = daoSistema.obtenerUsuario(usuario);
            ReinicioSesion.reiniciarUsuario(usuarioAct, request);
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        if(error != null){//Si hay algun error
            String mensaje = URLEncoder.encode(error, "latin1");
            response.sendRedirect(response.encodeRedirectURL("ObtenerTienda?media="
                    + mensaje));
            
        }else{
            String mensaje = URLEncoder.encode("Producto enviado a su inventario", "latin1");
            response.sendRedirect(response.encodeRedirectURL("ObtenerTienda?confirm="
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
