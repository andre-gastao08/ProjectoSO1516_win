/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imultimodal;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;
import static java.lang.Thread.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ac09930639
 */
public class TerminalMultimodal implements Runnable {

    private static Random rand = new Random();
    private ArrayList<Autocarro> listAutocarros;
    private ArrayList<Comboio> listaCombois;
    private ArrayList<Voo> listAvioes;
    private ArrayList<Passageiro> listaRecolhaPassageiro;
    private Timer tempoDemora, tempoDesembarquePassageiro, tempoRecolhaBagagem;
    public TerminalMultimodal() throws IOException {

        //this.listAutocarros = LeitorFicheiros.leitorFicheirosAutocarros("ficheiros/autocarros.txt");
        //this.listaCombois = LeitorFicheiros.leitorFicheiroComboios("ficheiros/comboios.txt");
        listAvioes = LeitorFicheiros.leitorFicheiroVoos("ficheiros/voos.txt");
        tempoDemora = new Timer();
        tempoDesembarquePassageiro = new Timer();
        tempoRecolhaBagagem = new Timer();

    }

    @Override
    public void run() {

        int sum = 0;
        for (Voo voos : listAvioes) {
            try {
                sleep(voos.getTempoRecolhaBagagem());
                ControlarPassageiroRecolhaBagagem(voos);
                System.out.println(voos);
            } catch (InterruptedException ex) {
                Logger.getLogger(TerminalMultimodal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //tempoDemora.schedule(new TempoDemoraAviaoPortaEmbarque(), 30*1000);
    }

    public synchronized ArrayList<Voo> DescarregaListaPassageirosVoo(Voo bufferPassageiro) {
        try {

            System.out.println("\nlista de  Voos com passageiros proveniente de diferentes cidades... ");
            listAvioes = LeitorFicheiros.leitorFicheiroVoos("ficheiros/voos.txt");
            listAvioes.stream().forEach((passageiro) -> {
                System.out.println(passageiro.toString());
            });
        } catch (IOException ex) {

            System.out.println("Impossível abrir o ficheiro txt");
            System.out.println("fim do programa...");

        }

        return listAvioes;
    }

    public synchronized void ControlarPassageiroRecolhaBagagem(Voo passageiro) throws InterruptedException {
        while (!verificarCapacidadePassageiroAceita(passageiro)) {
            sleep(passageiro.getTempoRecolhaBagagem());
        }
        for (Voo lisPassageiro : listAvioes) {
            DescarregaListaPassageirosVoo(lisPassageiro);
           

        }

        this.notify();
        //notificar Autocarros ou comboios  disponiveis 
    }

    public boolean verificarCapacidadePassageiroAceita(Voo passageiro) {

        return passageiro.contadorPassageirChegado() + contadorPassageiroRecolha() < 200;
    }

    /**
     *
     * @return
     */
    public synchronized int contadorPassageiroRecolha() {
        int capacidade = 0;
        for (Voo listPassageiro : listAvioes) {
            capacidade += listPassageiro.getPassageiros().indexOf(listaRecolhaPassageiro);
        }
        return capacidade;
    }

   /* public class TempoDemoraAviaoPortaEmbarque extends TimerTask {

        @Override
        public void run() {
            tempoDemora.cancel();
            for (int i = 0; i < listAvioes.size(); i++) {
                listAvioes.get(i).stop();
                System.out.println(listAvioes);
            }
            System.out.println("\nfim de descarrega dos passageiros...");
            System.out.println("\naviões podem ficarem 30 minutes para limpez ou reaabasticimento");

            for (int i = 0; i < listAvioes.size(); i++) {
                if (listAvioes.get(i).getNumeroPortaEmbarque() == 1 || (listAvioes.get(i).getNumeroPortaEmbarque() == 2
                        || (listAvioes.get(i).getNumeroPortaEmbarque() == 3))) {
                    listAvioes.get(i).resume();
                    //duvida se o voo , tem horas diferente pois não ha como a porta esteja ocupada..
                    System.out.println("porta ocupada numero: " + listAvioes.get(i).getNumeroPortaEmbarque()
                            + " \t o voo em espera para reaabastecimento com numero: " + listAvioes.get(i).getNumeroVoo());

                }
            }

        }
    }*/

}
