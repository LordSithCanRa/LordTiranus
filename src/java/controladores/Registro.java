/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.daoSistema;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modelo.Fondos;
import modelo.Skin;
import modelo.Usuario;

/**
 *
 * @author rcane
 */
@WebServlet(name = "registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {

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
        //VARIABLES DEL FORMULARIO 
        String usuario = null;
        BufferedReader bfru = new BufferedReader(new InputStreamReader(request.getPart("usuario").getInputStream()));
        usuario = bfru.readLine();
        String password = null;
        BufferedReader bfrp = new BufferedReader(new InputStreamReader(request.getPart("password").getInputStream()));
        password = bfrp.readLine();
        String passwordr = null;
        BufferedReader bfrpr = new BufferedReader(new InputStreamReader(request.getPart("passwordr").getInputStream()));
        passwordr = bfrpr.readLine();
        String email = null;
        BufferedReader bfre = new BufferedReader(new InputStreamReader(request.getPart("email").getInputStream()));
        email = bfre.readLine();
        String error = null;
        
         /*
         * Validación de los campos
         */
        Usuario u = new Usuario(usuario, password, email);
        Skin skinInicial = null;
        Fondos fondoInicial = null;
        if(!usuario.isEmpty() || usuario != null){
            if(usuario.length() > 15){
                error = "El usuario no puede superar las 15 letras";
            }else{
                //Comprobacion contraseñas
                if(!passwordr.isEmpty() || passwordr != null || !password.isEmpty() || password != null){
                    if(password.equals(passwordr)){
                        if(comprobarEmail(email)){//Si el email da bien
                            
                            try {
                                daoSistema.insertarUsuario(u);//Subimos el usuario a la base de datos
                                u = daoSistema.obtenerUsuario(usuario);
                                skinInicial = daoSistema.obtenerSkin(1);
                                fondoInicial = daoSistema.obtenerFondo(1);
                                //Agregamos los fondos iniciales al inventario del jugador
                                daoSistema.agregarSkinInventario(u, skinInicial);
                                daoSistema.agregarFondoInventario(u, fondoInicial);
                                
                            } catch (SQLException ex) {
                                if (ex.getMessage().contains("Duplicate entry")) {//Si el usuario ya existe
                                    error = "Ya existe el usuario " + usuario;
                                } else {
                                    error = "Error al insertar usuario (" + ex.getMessage() + ")";
                                }
                            }
                        }else{
                            error = "Error en el email";
                        }
                    }else{
                        error = "Las contraseñas no coinciden";
                    }
                }else{
                    error = "Los campos de contraseñas no pueden estar vacíos";
                }
                
            }
        }else{
            error = "El usuario no puede estar vacio";
        }
        
        
        if (error == null) {//Si no hay error
       /*
        * Subimos el fichero
        */
            try {

                String nombre = usuario;
                String extension = "";
                Part datosSubidos = request.getPart("imgUsuario");
                if (datosSubidos == null) { // No se ha subido el fichero
                error = "No se ha recibido la imagen";
                }else{
                    if (datosSubidos.getSize() > 1000*1024) { // Fichero demasiado grande
                        error = "No se permiten ficheros superiores a 1000Kb";
                    }else{
                        if (datosSubidos.getContentType().indexOf("imgUsuario")==1) { // El fichero no es una imagen
                            error = "El fichero recibido no es una imagen válida";
                        }else {
                            String tipoContenido =
                            datosSubidos.getContentType();
                            int posicion = tipoContenido.indexOf("/");
                            extension = tipoContenido.substring(posicion + 1);
                        }
                    }
                }
                if(error == null){
                    nombre = nombre+"."+"PNG";
                    String path = request.getServletContext().getRealPath("/imgUsuarios");
                    String contexto = path + "/" + nombre;
                    InputStream contenido = datosSubidos.getInputStream();
                    FileOutputStream fichero = new FileOutputStream(contexto);
                    byte[] bytes = new byte[12048];
                        while (contenido.available()>0) {
                            int longitud = contenido.read(bytes);
                            fichero.write(bytes, 0, longitud);
                        }


                    fichero.close();
                }  
            }catch (Exception e) {
                error = e.getMessage();
            }
        } 
        
        if(error == null){
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", u);//Guardamos el usuario en la sesion
            //Guardamos en la sesion la skin y el fondo que usaremos inicialmente al jugar
            sesion.setAttribute("skinInicial", skinInicial);
            sesion.setAttribute("fondoInicial", fondoInicial);
            String mensaje = URLEncoder.encode("Bienvenido nuevo padawan, "+usuario, "latin1");
            
            response.sendRedirect(response.encodeRedirectURL("ObtenerInicio?info="
                    + mensaje));
        }else {
            request.setAttribute("usuario", usuario);
            request.setAttribute("password", password);
            request.setAttribute("passwordr", passwordr);
            request.setAttribute("email", email);
            //Enviamos el error
            request.setAttribute("error", error);
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
        
    }
    //Funcion que validará el email
    public boolean comprobarEmail(String email){
    boolean resp = true;
    
    return resp;
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
