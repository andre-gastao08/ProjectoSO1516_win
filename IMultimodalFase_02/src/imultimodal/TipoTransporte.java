/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imultimodal;

/**
 *
 *  @author GastonJr
 */
public enum TipoTransporte {

    AUTOCARRO, COMBOIO, AVIAO;
    
    
    TipoTransporte( ){  
    }

    public String TipoVeiculoTransportador() {

        String tipo;

        switch (this) {

            case AUTOCARRO:
                return tipo = "AutoCarro";

            case COMBOIO:
                return tipo = "Comboio ";
            default:
                return tipo = "transporte escolhido não existe na lista";

        }

    }
     

}
