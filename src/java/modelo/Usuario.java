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
public class Usuario {
    private String usuario;
    private String clave;
    private String email;
    private boolean admin;
    private int experienciaAct;
    private int monedas;
    private int nivel;
    private int recordPuntos;
    private int partidasJugadas;
    //Constructor para usuarioRegistrado
    public Usuario(String usuario, String clave, String email){
        this.usuario = usuario;
        this.clave = clave;
        this.email = email;
        this.admin = false;
        this.experienciaAct = 0;
        this.monedas = 0;
        this.nivel = 0;
        this.recordPuntos = 0;
        this.partidasJugadas = 0;
    }
    //Constructor
    public Usuario(String usuario, String clave, String email, boolean admin, int experienciaAct, int monedas, int nivel, int recordPuntos, int partidasJugadas) {
        this.usuario = usuario;
        this.clave = clave;
        this.email = email;
        this.admin = admin;
        this.experienciaAct = experienciaAct;
        this.monedas = monedas;
        this.nivel = nivel;
        this.recordPuntos = recordPuntos;
        this.partidasJugadas = partidasJugadas;
    }
    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getExperienciaAct() {
        return experienciaAct;
    }

    public void setExperienciaAct(int experienciaAct) {
        this.experienciaAct = experienciaAct;
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getRecordPuntos() {
        return recordPuntos;
    }

    public void setRecordPuntos(int recordPuntos) {
        this.recordPuntos = recordPuntos;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }
    
    //Tofeos
//    public List<Trofeo>
}
