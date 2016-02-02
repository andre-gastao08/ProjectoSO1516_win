/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imultimodal;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;
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
    private static Timer tempoDemora, tempoDesembarquePassageiroRecolha, tempoRecolhaBagagem;

    public TerminalMultimodal() throws IOException {

        this.listAutocarros = LeitorFicheiros.leitorFicheirosAutocarros("ficheiros/autocarros.txt");
        //this.listaCombois = LeitorFicheiros.leitorFicheiroComboios("ficheiros/comboios.txt");
        this.listAvioes = LeitorFicheiros.leitorFicheiroVoos("ficheiros/voos.txt");
        tempoDemora = new Timer();
        tempoDesembarquePassageiroRecolha = new Timer();
        tempoRecolhaBagagem = new Timer();

    }

    @Override
    public void run() {
        for (Voo voos : listAvioes) {
            try {

                Thread.sleep(10);               
                controlarPassageiroRecolhaBagagem(voos);
                
                

            } catch (InterruptedException ex) {
                Logger.getLogger(TerminalMultimodal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TerminalMultimodal.class.getName()).log(Level.SEVERE, null, ex);
            }

            tempoDemora.schedule(new TempoDemoraAviaoPortaEmbarque(), 30* 1000);

        }
    }

    public synchronized ArrayList<Voo> DescarregaListaPassageirosVoo(Passageiro bufferPassageiro) {
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

    /**
     *
     * @param passageiro é variavel do tipo voo, e recebe lista dos voos
     * exixtentes no ficheiro.
     * @throws InterruptedException uma excepção para lançar , sempre que tempo
     * da recolha de bagagem termina
     */
    public synchronized void controlarPassageiroRecolhaBagagem(Voo passageiro) throws InterruptedException, IOException {
        //Autocarro aut= new Autocarro();
        
        do{
           for (Passageiro lisPassageiro : passageiro.getPassageiros()) {
            DescarregaListaPassageirosVoo(lisPassageiro);
            System.out.println("\nfim de descarrega de passageiro de voo");
            this.notifyAll();
            for (Autocarro passag : listAutocarros) {
                passag.verificarDescarregarPassageiros(passageiro);
            }
            this.wait(passageiro.getTempoRecolhaBagagem());
            //notificar Autocarros ou comboios  disponiveis 
        } 
        }while (!verificarCapacidadePassageiroAceita(passageiro));
            
        }
        
    

    /**
     *
     * @param passageiro é do voo, que verifica a capacidade de dos passageiros
     * existentes com as que foram adicionados.
     * @return
     */
    public boolean verificarCapacidadePassageiroAceita(Voo passageiro) {

        return passageiro.contadorPassageirChegado() + contadorPassageiroRecolha() < 200;
    }

    /**
     *
     * @return
     */
    public int contadorPassageiroRecolha() {
        int capacidade = 0;
        for (Voo listPassageiro : listAvioes) {
            capacidade += listPassageiro.getPassageiros().indexOf(listaRecolhaPassageiro);
        }
        return capacidade;
    }

    /**
     *
     */
    protected class TempoDemoraAviaoPortaEmbarque extends TimerTask {

        @Override
        public void run() {
            tempoDemora.cancel();
            for (Voo voo : listAvioes) {
                voo.suspend();
            }
            System.out.println("fim de descarrega dos passageiros vindo de voo...");
            System.out.println("aviões podem ficarem 30 minutes para limpez ou reaabasticimento");
            System.out.println();
            for (int i = 0; i < listAvioes.size(); i++) {
                if (listAvioes.get(i).getNumeroPortaEmbarque() == 1 || (listAvioes.get(i).getNumeroPortaEmbarque() == 2
                        || (listAvioes.get(i).getNumeroPortaEmbarque() == 3))) {
                    listAvioes.get(i).resume();
                    //duvida se o voo , tem horas diferente pois não ha como a porta esteja ocupada..
                    System.out.println("porta ocupada numero: " + listAvioes.get(i).getNumeroPortaEmbarque()
                            + " \t o voo em espera para reaabastecimento com numero: " + listAvioes.get(i).getNumeroVoo());
                }
            }
            System.out.println("fim da descarrega de passageiro vindo de voo");
            System.out.println("incio de carregar os passageiros no autocarro");

        }
    }

}
