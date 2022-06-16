/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rcane
 */
public class ReinicioSesion {
    public static void reiniciarUsuario(Usuario usuario, HttpServletRequest request){
        HttpSession sesion = request.getSession();
        sesion.removeAttribute("usuario");
        sesion.setAttribute("usuario", usuario);
        
    }
    public static void reiniciarFondoInicial(Fondos fondo, HttpServletRequest request){
        HttpSession sesion = request.getSession();
        sesion.removeAttribute("fondoInicial");
        sesion.setAttribute("fondoInicial", fondo);
    }
    public static void reiniciarSkinInicial(Skin skin, HttpServletRequest request){
        HttpSession sesion = request.getSession();
        sesion.removeAttribute("skinInicial");
        sesion.setAttribute("skinInicial", skin);
    }
}
