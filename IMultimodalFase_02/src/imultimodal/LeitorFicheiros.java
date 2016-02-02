/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imultimodal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author GastonJr
 */
public class LeitorFicheiros extends Thread implements Runnable {

    private TerminalMultimodal tMultimodal;
    private static ArrayList<Autocarro> listaAutocarros;
    private static ArrayList<Comboio> listaComboios;
    private static ArrayList<Voo> listaVoos;
    private TipoTransporte tipo;

    public LeitorFicheiros() {

    }

    public static ArrayList<Autocarro> leitorFicheirosAutocarros(String ficheiroAutocarro) throws IOException {
        listaAutocarros = new ArrayList<>();
        BufferedReader bufftexto;
        bufftexto = new BufferedReader(new FileReader(ficheiroAutocarro));

        do {

            int NumeroCarreira = Integer.parseInt(bufftexto.readLine());
            String destino = bufftexto.readLine();
            String horaPardida = bufftexto.readLine();
            String horaChegada = bufftexto.readLine();
            int numerLinhaEmbarque = Integer.parseInt(bufftexto.readLine());
            int numVaga = Integer.parseInt(bufftexto.readLine());
            
            listaAutocarros.add(new Autocarro(NumeroCarreira, destino, horaPardida, horaChegada, numerLinhaEmbarque, numVaga));
        } while (bufftexto.readLine() != null);
        return listaAutocarros;
    }

    public static ArrayList<Comboio> leitorFicheiroComboios(String ficheiroComboio) throws IOException {
        listaComboios = new ArrayList<>();
        BufferedReader bufftexto = null;
        bufftexto = new BufferedReader(new FileReader(ficheiroComboio));

        do {
            int numeroComboio = Integer.parseInt(bufftexto.readLine());
            String destino = bufftexto.readLine();
            String horaPartida = bufftexto.readLine();
            String horaChagada = bufftexto.readLine();
            int numeroPlataforma = Integer.parseInt(bufftexto.readLine());
            int numeroLugarVaga = Integer.parseInt(bufftexto.readLine());

            listaComboios.add(new Comboio(numeroComboio, destino, horaPartida, horaChagada, numeroPlataforma, numeroLugarVaga));
        } while (bufftexto.readLine() != null);

        return listaComboios;

    }

    public static ArrayList<Voo> leitorFicheiroVoos(String ficheiroVoo) throws IOException {
        listaVoos = new ArrayList<>();

        try {
            BufferedReader bufftexto = null;
            bufftexto = new BufferedReader(new FileReader(ficheiroVoo));
            //String texto = bufftexto.readLine();
            do {
                int numeroVoo = Integer.parseInt(bufftexto.readLine());
                String origem = bufftexto.readLine();
                String horaChagada = bufftexto.readLine();
                int numerPortaEmbarque = Integer.parseInt(bufftexto.readLine());
                String linha = bufftexto.readLine();
                ArrayList<Passageiro> passageiros = new ArrayList<>();
                do {

                    StringTokenizer st = new StringTokenizer(linha, ";");
                    int numeroPassGrupo = Integer.parseInt(st.nextToken());
                    TipoTransporte tipo = null;

                    switch (st.nextToken()) {
                        default:;
                        case "Autocarro":
                        case "autocarro":
                        case "AUTOCARRO":
                            tipo = TipoTransporte.AUTOCARRO;
                            break;
                        case "Comboio":
                        case "comboio":
                        case "COMBOIO":
                            tipo = TipoTransporte.COMBOIO;
                            break;
                    }

                    String destino = st.nextToken();
                    passageiros.add(new Passageiro(numeroPassGrupo, tipo, destino));
                    linha = bufftexto.readLine();

                    if (linha == null) {
                        break;
                    }
                } while (linha.compareTo("") != 0);
                listaVoos.add(new Voo(numeroVoo, origem, horaChagada, numerPortaEmbarque, passageiros));
            } while (bufftexto.ready());
            bufftexto.close();
        } catch (IOException e) {
            System.out.println("Erro de Entrada / Saída de dados: " + e);

        }
        return listaVoos;
    }
}
