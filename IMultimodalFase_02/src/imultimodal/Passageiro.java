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

        if (numeroPassageiro <= 200 && numeroPassageiro > 0) {
            this.numeroPassageiro = numeroPassageiro;
        } else {
            System.out.println("numero: " + numeroPassageiro + " e maior que a capacidade maxima");
        }
        this.tipo = tipoMeio;
        this.destino = destino;

    }

    Passageiro() throws Exception {

    }

    public int getNumeroPassageiro() {
        return numeroPassageiro;
    }

    public void setNumeroPassageiro(int numeroPassageiro) {
        this.numeroPassageiro = numeroPassageiro;
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
