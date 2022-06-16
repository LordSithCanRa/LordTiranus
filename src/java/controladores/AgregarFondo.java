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
import javax.servlet.http.Part;
import modelo.Fondos;
import modelo.LeerBuffered;

/**
 *
 * @author rcane
 */
@WebServlet(name = "AgregarFondo", urlPatterns = {"/admin/AgregarFondo"})
public class AgregarFondo extends HttpServlet {

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
        //Obtenemos el Fondo del buffered
        BufferedReader bfrFS = new BufferedReader(new InputStreamReader(request.getPart("Fondo").getInputStream()));
        String fondoSub = bfrFS.readLine();

        if(fondoSub!=null){
            //VALIDARCAMPOS
            BufferedReader bfrnPF = new BufferedReader(new InputStreamReader(request.getPart("nombreProducto").getInputStream()));
            String nombreProducto = bfrnPF.readLine();
            BufferedReader bfrpF = new BufferedReader(new InputStreamReader(request.getPart("precio").getInputStream()));
            String precio = bfrpF.readLine();
            String src = null;
            if(nombreProducto == null || nombreProducto.trim().equals("")){
                error = "El nombre de producto no puede estar vacio";
                
            }else{
                nombreProducto = nombreProducto.toUpperCase();//Ponemos el nombre siempre en mayusculas
                try {
                    Integer.parseInt(precio);
                } catch (final NumberFormatException e) {
                    error = "El precio tiene que ser un numero";
                }
            }
            if(error == null){
                 //Subimos el fichero
                try {
                    String nombre = nombreProducto;
                    String extension = "";
                    Part datosSubidos = request.getPart("archivo");
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
                    //Subimos el fichero una vez todo se haya validado
                    if(error == null){
                        nombre = nombre+"."+"PNG";
                        src = nombre;
                        String contexto = request.getServletContext().getRealPath("src/juegoJS/srcjuego/BACKGROUNDS/" + nombre);
                        FileOutputStream fichero = new FileOutputStream(contexto);
                        InputStream contenido = datosSubidos.getInputStream();
                        byte[] bytes = new byte[2048];
                            while (contenido.available()>0) {
                                int longitud = contenido.read(bytes);
                                fichero.write(bytes, 0, longitud);
                            }
                        fichero.close();
                        try{
                            Fondos f = new Fondos(src, nombreProducto, Integer.parseInt(precio));
                            daoSistema.insertarFondo(f);
                        }catch(SQLException e){
                            error = e.getMessage();
                        }
                    }  
                }catch (Exception e) {
                    error = e.getMessage();
                }
            }
               
            
        }
        
        if(error != null){
            String mensaje = URLEncoder.encode(error, "latin1");
            response.sendRedirect(response.encodeRedirectURL("gestionTienda.jsp?error="
                    + mensaje));
        }else{
            String mensaje = URLEncoder.encode("Fondo agregado correctamente", "latin1");
            response.sendRedirect(response.encodeRedirectURL("gestionTienda.jsp?verifica="
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
