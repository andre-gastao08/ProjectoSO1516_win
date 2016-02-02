/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imultimodal;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Ac09930639
 */
public class mainIMultimodal {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
            
        ArrayList<Autocarro> listAutocarros = new ArrayList<>();
        listAutocarros = LeitorFicheiros.leitorFicheirosAutocarros("ficheiros/autocarros.txt");
        ArrayList<Comboio> comboios = new ArrayList<>();
        comboios = LeitorFicheiros.leitorFicheiroComboios("ficheiros/comboios.txt");
        ArrayList<Voo> listaVoos = new ArrayList<>();
        listaVoos = LeitorFicheiros.leitorFicheiroVoos("ficheiros/voos.txt");
       
        TerminalMultimodal ter = new TerminalMultimodal();
        Thread threadVoos= new Thread();
        new Thread(ter).start();
        
       
       
        /*----------------------menu principal-----------------------------------------*/
        System.out.println("------------- A sua escolha ao Menu Principal ----------------\n");
        System.out.println("------------- 1 interface Multimodal--------------------------\n");
        System.out.println("------------- 2 Voos a aterrar -------------------------------\n");
        System.out.println("------------- 3 Autocarros -----------------------------------\n");
        System.out.println("------------- 4 Comboios   -----------------------------------\n");
  
        
        /*
        for (Autocarro listAutocarro : listAutocarros) {
            
         System.out.println(listAutocarro.toString());
         } 
         System.out.println("---------------------------lista dos comboios------------------------- \n");
         for (Comboio comboio : comboios) {

         System.out.println(comboio.toString() + "\n");

         }
        System.out.println("---------------------------lista dos voos -------------------------------\n");

        for (Voo voo : listaVoos) {       
            System.out.println(voo.toString() + "\n");
            

        }
        */

    }

}
