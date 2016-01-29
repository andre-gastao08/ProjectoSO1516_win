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
    public TerminalMultimodal() throws IOException {

        //this.listAutocarros = LeitorFicheiros.leitorFicheirosAutocarros("ficheiros/autocarros.txt");
        //this.listaCombois = LeitorFicheiros.leitorFicheiroComboios("ficheiros/comboios.txt");
        this.listAvioes = LeitorFicheiros.leitorFicheiroVoos("ficheiros/voos.txt");

    }

    @Override
    public void run() {

        int sum = 0;

        listAvioes.stream().map((passageiro) -> {
            try // dorme de 0 a 3 segundos, então coloca valor em Buffer
            {
                sleep(passageiro.getTempoDesembarque()); // thread sleep
                // configura valor no buffer

                DescarregarPassageiroRecolhaBagagem(passageiro);
            } // fim do try
            // se a thread adormecida é interrompida, imprime rastreamento de pilha 
            catch (InterruptedException exception) {

            }
            return passageiro;
        }).forEach((_item) -> {
            System.out.printf("\n", "Producer done producing.",
                    "Terminating Producer.");
        }); // fim do método run

    }

    public synchronized ArrayList<Voo> carregarPassageirosTipoTransporte(Passageiro bufferPassageiro) {
        try {

            System.out.println("\nlista de passageiros a carregar, apos a teragem de voos  ");
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

    private synchronized void DescarregarPassageiroRecolhaBagagem(Voo passageiro) throws InterruptedException {
        while (!verificarCapacidadePassageiroAceita(passageiro)) {
            tempAdicionalDesambarquePassageiroVoos(passageiro);

        }
        sleep(passageiro.getTempoRecolhaBagagem());
        for (Passageiro lisPassageiro : passageiro.getPassageiros()) {

            carregarPassageirosTipoTransporte(lisPassageiro);

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

    public synchronized void tempAdicionalDesambarquePassageiroVoos(Voo passageiro) throws InterruptedException {

        if (passageiro.getNumeroPortaEmbarque() >= 1 || passageiro.getNumeroPortaEmbarque() <= 3) {
            int tempoAdicional = rand.nextInt(30)+passageiro.getTempoDesembarque();
            for (Voo voos : listAvioes) {
                this.wait(tempoAdicional);
                System.out.println("os aviões podem esperar ate ao minuto "+voos.getTempoDesembarque()+
                        "para desembarcarem");
            }
        } else {

            this.notifyAll();
        }

    }

}
