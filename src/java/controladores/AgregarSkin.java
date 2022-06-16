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
import modelo.Skin;


/**
 *
 * @author rcane
 */
@WebServlet(name = "AgregarSkin", urlPatterns = {"/admin/AgregarSkin"})
public class AgregarSkin extends HttpServlet {

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
        
        BufferedReader bfrSS = new BufferedReader(new InputStreamReader(request.getPart("Skin").getInputStream()));
        String skinSub = bfrSS.readLine();

        if(skinSub!=null){
            //TODO: VALIDARCAMPOS
            BufferedReader bfrnPS = new BufferedReader(new InputStreamReader(request.getPart("nombreProducto").getInputStream()));
            String nombreProducto = bfrnPS.readLine();
            BufferedReader bfrpS = new BufferedReader(new InputStreamReader(request.getPart("precio").getInputStream()));
            String precio = bfrpS.readLine();
            BufferedReader bfrWS = new BufferedReader(new InputStreamReader(request.getPart("width").getInputStream()));
            String width =bfrWS.readLine();
            BufferedReader bfrHS = new BufferedReader(new InputStreamReader(request.getPart("height").getInputStream()));
            String height = bfrHS.readLine();
            
            if(error == null){
                //Subimos el fichero
                try {
                    String nombre = nombreProducto;
                    String extension = "";
                    Part datosSubidos = request.getPart("sprite");
                    Part datosSubidosV = request.getPart("visual");
                    if (datosSubidos == null || datosSubidosV == null) { // No se ha subido el fichero
                    error = "No se ha recibido la imagen";
                    }else{
                        if (datosSubidos.getSize() > 1000*1024 || datosSubidosV.getSize() > 1000*1024) { // Fichero demasiado grande
                            error = "No se permiten ficheros superiores a 1000Kb";
                        }else{
                            if (datosSubidos.getContentType().indexOf("imgUsuario")==1) {//BORRAR?
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
                        String src = nombre+"."+"PNG";;
                        String contexto = request.getServletContext().getRealPath("src/juegoJS/srcjuego/PLAYERS/" + nombre +".png");
                        String contextoV = request.getServletContext().getRealPath("src/juegoJS/srcjuego/SHOWPLAYERS/" + src);
                        FileOutputStream fichero = new FileOutputStream(contexto);
                        FileOutputStream ficheroV = new FileOutputStream(contextoV);
                        InputStream contenido = datosSubidos.getInputStream();
                        InputStream contenidoV = datosSubidosV.getInputStream();
                        byte[] bytes = new byte[2048];
                            while (contenido.available()>0) {
                                int longitud = contenido.read(bytes);
                                fichero.write(bytes, 0, longitud);
                            }
                        fichero.close();
                        byte[] bytesV = new byte[2048];
                            while (contenidoV.available()>0) {
                                int longitud = contenidoV.read(bytes);
                                ficheroV.write(bytes, 0, longitud);
                            }
                        ficheroV.close();
                        //Agregamos a la base de datos
                        try{
                            Skin s = new Skin(src, nombreProducto, Double.parseDouble(width), Double.parseDouble(height), Integer.parseInt(precio));
                            daoSistema.insertarSkin(s);
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
            String mensaje = URLEncoder.encode("Skin agregada correctamente", "latin1");
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
