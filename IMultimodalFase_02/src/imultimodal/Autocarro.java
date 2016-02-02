/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imultimodal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GastonJr
 */
public class Autocarro extends Thread {
    private ArrayList<Voo> listaAvioes;
    private ArrayList<Passageiro> listaPassageiros = new ArrayList<>();
    private Passageiro passageiro;
    private TerminalMultimodal terminal;
    private TipoTransporte tipo;
    private Timer tempoDesembarquePassageiroRecolha;
    private int numeroCarreira;
    private String destino;
    private Relogio horaPartida;
    private Relogio horaChegada;
    private int numeroLinhaEmbarque;
    private int numeroLugarVagos;

    public Autocarro(int numeroCarreira, String destino, String horaPartida, String horaChegada, int numeroLinhaEmbarque, int numeroLugarVagos
    ) throws IOException {
        this.numeroCarreira = numeroCarreira;
        this.destino = destino;
        StringTokenizer st = new StringTokenizer(horaPartida, ":");
        this.horaPartida = new Relogio(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(horaChegada, ":");
        this.horaChegada = new Relogio(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        this.numeroLinhaEmbarque = (numeroLinhaEmbarque > 3 || numeroLinhaEmbarque <= 0) ? 1 : numeroLinhaEmbarque;
        this.numeroLugarVagos = numeroLugarVagos;
        tempoDesembarquePassageiroRecolha = new Timer();
        listaAvioes = LeitorFicheiros.leitorFicheiroVoos("ficheiros/voos.txt");

    }

    Autocarro() throws IOException {
        Voo voo = new Voo(listaPassageiros);
        carregarPassageiroEmAutocarros(voo);
        listaPassageiros.add(passageiro);

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

    public ArrayList<Voo> getPassageiros() {
        ArrayList<Autocarro> listaAutocarros = new ArrayList<>();

        for (Voo  passageiro: listaAvioes) {

            if (passageiro.equals(getPassageiros())) {
               
                        
            }
        }

        return listaAvioes;
    }

    public TipoTransporte getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        String st = "\n";
        
          st = " \n numero da Carreira: " + numeroCarreira
                    + " \ndestino: " + destino
                    + " \nhora de Partida " + horaPartida.toString()
                    + " \nhora de chegada " + horaChegada.toString()
                    + " \nnumero da Linha de Embarque:" + numeroLinhaEmbarque
                    + " \nnumero de Lugar vagas: " + numeroLugarVagos;
          
        return st;
    }

    protected synchronized ArrayList<Autocarro> carregarPassageiroEmAutocarros(Voo passageiro) throws IOException {
        ArrayList<Autocarro> listaAutocarros = new ArrayList<>();
        listaAutocarros = LeitorFicheiros.leitorFicheirosAutocarros("ficheiros/autocarros.txt");
        for (Autocarro autocarro: listaAutocarros) {
            System.out.println();
            System.out.println(""+autocarro.toString());

            
        }
        return listaAutocarros;
    }

    protected synchronized void verificarDescarregarPassageiros(Voo passageiro) throws IOException, InterruptedException {
        ArrayList<Autocarro> listaAutocarros = new ArrayList<>();
       listaAutocarros = LeitorFicheiros.leitorFicheirosAutocarros("ficheiros/autocarros.txt");
        while (passageiro.contadorPassageirChegado()>0) {
            System.out.println();
            System.out.println("fim de carregar passageiros no autocarro.");
            sleep(500);

            for (Voo p : listaAvioes) {
               p.getPriority();
                //sleep(MIN_PRIORITY);
              
               tempoDesembarquePassageiroRecolha.schedule(new tolerancaPartidaAutocarro(), 20*10000);
                carregarPassageiroEmAutocarros(p);
                
            }
            for (int i=0; i<listaAutocarros.size();i++) {
                for (int j = 0; j <listaAvioes.size(); j++) {
                    
                    listaPassageiros=listaAvioes.get(j).getPassageiros();
                   listaAutocarros.get(i).resume();
                    //sleep(MAX_PRIORITY);

                    //System.out.println(listaAutocarros.toString());
                
                System.out.println();
                System.out.println("voo numero: " + listaAvioes.get(j).getNumeroVoo()
                        + " descarregou  grupo de numero de passageiro: " +listaPassageiros.get(j).getNumeroPassageiro()
                        + " ,\n"+tipo.AUTOCARRO+ " numero : " + listaAutocarros.get(i).getNumeroCarreira()
                        +"  ,linha numero: "+listaAutocarros.get(i).getNumeroLinhaEmbarque()
                        + " , \ncarregou grupo de numero de passageiro:" +listaPassageiros.get(j).getNumeroPassageiro()
                        + " destino: " +listaAutocarros.get(i).getDestino());
            }}

        }sleep(500);
        this.notifyAll();
    }

    protected class tolerancaPartidaAutocarro extends TimerTask {

        private ArrayList<Autocarro> listaAutocarros = new ArrayList<>();
        private Passageiro passag;

        @Override
        public void run() {

            tempoDesembarquePassageiroRecolha.cancel();
            System.out.println("fim de descarrega passageiro no autocarro");
            while (terminal.DescarregaListaPassageirosVoo(passag) != null) {
                for (Voo lista : listaAvioes) {
                    tempoDesembarquePassageiroRecolha.schedule(new tolerancaPartidaAutocarro(), 20 * 10000);
                    System.out.println(" carregar os passageiros no autocarro...\n");
                    for (Voo voo : listaAvioes) {
                        voo.notify();
                        try {
                            verificarDescarregarPassageiros(voo);
                        } catch (IOException ex) {
                            Logger.getLogger(Autocarro.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Autocarro.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println();

                    }
                }

            }

        }

    }
}
