/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imultimodal;
/**
 *
 * @author GastonJr
 */
public class Passageiro {

    private int numeroPassageiro;
    private TipoTransporte tipo;
    private String destino;
   

    public Passageiro(int numeroPassageiro, TipoTransporte tipoMeio, String destino) {

        this.numeroPassageiro = (this.numeroPassageiro > 0) ? this.numeroPassageiro : numeroPassageiro;
        this.tipo = tipoMeio;
        this.destino = destino;
       

    }

    Passageiro() throws Exception {

    }

    public int getNumeroPassageiro() {
        return numeroPassageiro;
    }

    public void setNumeroPassageiro(int numeroPassageiro) {
        if (numeroPassageiro <=0 || numeroPassageiro >200) {
            this.numeroPassageiro = numeroPassageiro;
        }
    }

    public TipoTransporte getTipo() {
        return tipo;
    }

    public String getDestino() {
        return destino;
    }

    @Override
    public String toString() {
        return " \nnumero de Passageiros no grupo: " + numeroPassageiro
                + "; tipo de meio de transporte: " + tipo
                + "; destino: " + destino;
    }

}
