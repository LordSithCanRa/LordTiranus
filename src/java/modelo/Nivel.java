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
public class Nivel {
    private int nivel;
    private String recompensa;
    private int experienciaNecesaria;

    public Nivel(int nivel, String recompensa, int experienciaNecesaria) {
        this.nivel = nivel;
        this.recompensa = recompensa;
        this.experienciaNecesaria = experienciaNecesaria;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(String recompensa) {
        this.recompensa = recompensa;
    }

    public int getExperienciaNecesaria() {
        return experienciaNecesaria;
    }

    public void setExperienciaNecesaria(int experienciaNecesaria) {
        this.experienciaNecesaria = experienciaNecesaria;
    }
    
    
}
