/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imultimodal;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author Ac09930639
 */
public class Autocarro  extends Thread{

    private int numeroCarreira;
    private String destino;
    private Relogio horaPartida;
    private Relogio horaChegada;
    private int numeroLinhaEmbarque;
    private int numeroLugarVagos;

    public Autocarro(int numeroCarreira, String destino, String horaPartida, String horaChegada, int numeroLinhaEmbarque, int numeroLugarVagos) throws IOException {
        this.numeroCarreira = numeroCarreira;
        this.destino = destino;
        StringTokenizer st = new StringTokenizer(horaPartida, ":");
        this.horaPartida = new Relogio(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(horaChegada, ":");
        this.horaChegada = new Relogio(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        this.numeroLinhaEmbarque = (numeroLinhaEmbarque > 3 || numeroLinhaEmbarque <= 0) ? 1 : numeroLinhaEmbarque;
        this.numeroLugarVagos = numeroLugarVagos;
        LeitorFicheiros.leitorFicheirosAutocarros("ficheiro/autocarros.txt");
    }

    public int getNumeroCarreira() {

        return numeroCarreira;

    }

    /**
     *
     * @param numeroCarreira
     */
    public void setNumeroCarreira(int numeroCarreira) {
        this.numeroCarreira = (numeroCarreira >= 0) ? this.numeroCarreira : numeroCarreira;
    }

    public int getNumeroLinhaEmbarque() {

        return numeroLinhaEmbarque;

    }

    public void setNumeroLinhaEmebarque(int numeroLinhaEmbarque) {

        this.numeroLinhaEmbarque = (numeroLinhaEmbarque > 0) ? this.numeroLinhaEmbarque : numeroLinhaEmbarque;

    }

    public String getDestino() {

        return destino;

    }

    public void setDestino(String destino) {

        this.destino = destino;
    }

    public int getNumeroLugarVagos() {

        return numeroLugarVagos;
    }

    public void setNumeroLugarVagos(int numeroLugarVagos) {

        this.numeroLugarVagos = numeroLugarVagos;

    }

    @Override
    public String toString() {
        return "\nnumero da Carreira: " + numeroCarreira
                + " \ndestino: " + destino
                + " \nhora de Partida " + horaPartida.toString()
                + " \nhora de chegada " + horaChegada.toString()
                + " \nnumero da Linha de Embarque:" + numeroLinhaEmbarque
                + " \nnumero de Lugar vagas: " + numeroLugarVagos;
    }
    
    public synchronized void tempoTolerancaAutocarros(){
        
    }
    
  
}
