/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imultimodal;

import java.util.StringTokenizer;

/**
 *
 * @author Ac09930639
 */
public class Comboio {

    private int numeroComboio;
    private String destino;
    private Relogio horaPartida;
    private Relogio horaChegada;
    private int plataFormaDeEmbarque;
    private int numeroLugarVago;

    public Comboio() {

    }

    public Comboio(int numeroComboio, String destino, String horaPartida, String horaChegada, int plataFormaDeEmbarque, int numeroLugarVagos) {
        this.numeroComboio = numeroComboio;
        this.destino = (destino == null) ? this.destino : destino;
        StringTokenizer st=new StringTokenizer(horaPartida, ":");
        this.horaPartida = new Relogio(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st=new StringTokenizer(horaChegada, ":");
        this.horaChegada = new Relogio(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        this.plataFormaDeEmbarque = (plataFormaDeEmbarque > 0 || plataFormaDeEmbarque <= 2) ? 1 : plataFormaDeEmbarque;
        this.numeroLugarVago = (numeroLugarVagos <= 0) ? this.numeroLugarVago : numeroLugarVagos;
    }

    public int getNumeroComboio() {
        return numeroComboio;
    }

    public void setNumeroComboio(int numeroComboio) {
        this.numeroComboio = (numeroComboio > 0) ? this.numeroComboio : numeroComboio;
    }

    public int getPlataFormaDeEmbarque() {
        return plataFormaDeEmbarque;
    }

    public void setPlataFormaDeEmbarque(int plataFormaDeEmbarque) {
        this.plataFormaDeEmbarque = (plataFormaDeEmbarque > 0) ? this.plataFormaDeEmbarque : plataFormaDeEmbarque;
    }

    public int getNumeroLugarVago() {

        return numeroLugarVago;
    }

    public void setNumeroLugarVago(int numeroLugarVago) {

        this.numeroLugarVago = (numeroLugarVago > 0) ? this.plataFormaDeEmbarque : numeroLugarVago;

    }

    @Override
    public String toString() {
        return "\nnumero de Comboio: " + numeroComboio
                + "\ndestino: " + destino
                + " \nhora de Partida: " + horaPartida
                + " \nhora de Chegada: " + horaChegada
                + " \nplataForma de Embarque: " + plataFormaDeEmbarque
                + " \nnumeroLugarVagos: " + numeroLugarVago;
    }

}
