/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author rcane
 */
public class LeerBuffered {
    public static String leerStringBFR(String nombreCampo, HttpServletRequest request) throws IOException, ServletException{
        String valorCampo = null;
        BufferedReader bfr = new BufferedReader(new InputStreamReader(request.getPart(nombreCampo+"").getInputStream()));
        valorCampo = bfr.readLine();
        return valorCampo;
    }
}
