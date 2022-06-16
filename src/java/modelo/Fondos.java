/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author rcane
 */
public class Fondos {
    private int id;
    private String src;
    private String nombre;
    private int precio;
    
    public Fondos(String src, String nombre, int precio) {
        this.src = src;
        this.nombre = nombre;
        this.precio = precio;
    }
    
    public Fondos(int id, String src, String nombre, int precio) {
        this.id = id;
        this.src = src;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    
}
