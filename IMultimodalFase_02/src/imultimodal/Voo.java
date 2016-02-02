/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imultimodal;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

/**
 *
 * @author Ac09930639
 */
public class Voo extends Thread {

    public Random rand = new Random();
    private int numeroVoo;
    private String origem;
    //private Relogio horaPartida;
    private Relogio horaChegada;
    private int numeroPortaEmbarque;
    private ArrayList<Passageiro> listaPassageiros;
    private LeitorFicheiros leitorFicheiro;
    private int tempoDesembarquePassageiro;
    private int tempoRecolhaBagagem;
    private TerminalMultimodal terminal;

    public Voo() {
    }

    public Voo(ArrayList<Passageiro> listaPassageiros) {
        this.listaPassageiros = listaPassageiros;
    }
    
    
    

    public Voo(int numeroVoo, String origem, String horaChegada, int numeroPortaEmbarque,
            ArrayList<Passageiro> passageiros) {
        this.numeroVoo = numeroVoo;
        this.origem = origem;
        this.listaPassageiros = passageiros;
        StringTokenizer st = new StringTokenizer(horaChegada, ":");
        this.horaChegada = new Relogio(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        this.numeroPortaEmbarque = (numeroPortaEmbarque <= 0 || numeroPortaEmbarque > 3) ? 1 : numeroPortaEmbarque;
        tempoRecolhaBagagem = rand.nextInt(21) + 20;
        int temp = rand.nextInt(41) + tempoRecolhaBagagem;
        tempoDesembarquePassageiro = (temp) < 60 ? temp : 60;
    }

    public String getOrigem() {
        return origem;
    }

    public int getNumeroVoo() {
        return numeroVoo;
    }

    public int getNumeroPortaEmbarque() {
        return numeroPortaEmbarque;
    }

    public ArrayList<Passageiro> getPassageiros() {
        return listaPassageiros;
    }

    public int getTempoDesembarquePassageiro() {
        return tempoDesembarquePassageiro;
    }

    public int getTempoRecolhaBagagem() {
        return tempoRecolhaBagagem;
    }

    public int contadorPassageirChegado() {

        int capacidade = 0;
        capacidade = listaPassageiros.stream().map((listPassageiro) -> listPassageiro.getNumeroPassageiro()).reduce(capacidade, Integer::sum);
        return capacidade;
    }

    @Override
    public String toString() {
        String retvalue = "\nnumero de Voo:" + numeroVoo
                + "\n origem: " + origem
                + " \n hora de chegada " + horaChegada.toString()
                + " \n numero da porta de embarque: " + numeroPortaEmbarque
                + "\n Tempo de Recolha de bagagem: " + tempoRecolhaBagagem+" min "
                + "\n Tempo de Desembarque: " + tempoDesembarquePassageiro +" min ";
        retvalue = listaPassageiros.stream().map((passageiro) -> passageiro.toString()).reduce(retvalue, String::concat);
        return retvalue;
    }


}
