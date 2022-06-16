/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import dao.daoSistema;

/**
 *
 * @author rcane
 */
public class Skin {
    private int id;
    private String src;
    private String nombre;
    private double width;
    private double height;
    private int precio;
    
    public Skin(String src, String nombre, double width, double height, int precio) {
        this.src = src;
        this.nombre = nombre;
        this.width = width;
        this.height = height;
        this.precio = precio;
    }
    
    public Skin(int id, String src, String nombre, double width, double height, int precio) {
        this.id = id;
        this.src = src;
        this.nombre = nombre;
        this.width = width;
        this.height = height;
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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
