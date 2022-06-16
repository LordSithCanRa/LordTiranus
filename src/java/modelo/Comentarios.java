/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Timestamp;
/**
 *
 * @author rcane
 */
public class Comentarios {
    private String usuarioRemitente;
    private String usuarioDestinatario;
    private int idComentario;
    private Timestamp fechaEscrito;
    private int meGusta;
    private String comentario;
    

    public Comentarios(String usuarioRemitente, String usuarioDestinatario, int idComentario, Timestamp fechaEscrito, int meGusta, String comentario) {
        this.usuarioRemitente = usuarioRemitente;
        this.usuarioDestinatario = usuarioDestinatario;
        this.idComentario = idComentario;
        this.fechaEscrito = fechaEscrito;
        this.meGusta = meGusta;
        this.comentario = comentario;
    }

    public String getUsuarioDestinatario() {
        return usuarioDestinatario;
    }

    public void setUsuarioDestinatario(String usuarioDestinatario) {
        this.usuarioDestinatario = usuarioDestinatario;
    }
    
    public String getUsuarioRemitente() {
        return usuarioRemitente;
    }

    public void setUsuarioRemitente(String usuarioRemitente) {
        this.usuarioRemitente = usuarioRemitente;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public Timestamp getFechaEscrito() {
        return fechaEscrito;
    }

    public void setFechaEscrito(Timestamp fechaEscrito) {
        this.fechaEscrito = fechaEscrito;
    }

    public int getMeGusta() {
        return meGusta;
    }

    public void setMeGusta(int meGusta) {
        this.meGusta = meGusta;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
}
