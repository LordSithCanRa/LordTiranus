/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rcane
 */
public class Trofeo {
    public String nombre;
    public String imgTrofeo;//Proximamente cada trofeo tendrá una imagen personalizada
    
    public Trofeo(String nombre) {
        this.nombre = nombre;
        this.imgTrofeo = "src/img/trofeo.png";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImgTrofeos() {
        return imgTrofeo;
    }

    public void setImgTrofeos(String imgTrofeos) {
        this.imgTrofeo = imgTrofeos;
    }
    
    
    
    //Trofeos personalizados desde AQUI
    public static List<Trofeo> obtenerTrofeos(Usuario u){
        List<Trofeo> trofeos = new LinkedList();
        
            //TROFEOS SEGUN PARTIDAS JUGADAS
        
            if(u.getPartidasJugadas() >=5){//Trofeo por superar las 5 partidas
            trofeos.add(new Trofeo("5 partidas jugadas"));
            }
        
            if(u.getPartidasJugadas() >=10){//Trofeo por jugar 10 partidas
            trofeos.add(new Trofeo("10 partidas jugadas"));
            }
            
            //TROFEOS POR NIVEL
            
            if(u.getNivel() >=5){
            trofeos.add(new Trofeo("Has llegado al nivel 5"));
            }
            
            if(u.getNivel() >=10){
            trofeos.add(new Trofeo("Has llegado al nivel 10"));
            }
            
            if(u.getNivel() >=24){
            trofeos.add(new Trofeo("Conseguiste la skin de TWILEK"));
            }
            
            if(u.getNivel() >=30){
            trofeos.add(new Trofeo("Conseguiste el fondo Batalla-Final"));
            }
            
        return trofeos;
    }
    
    
    
}
