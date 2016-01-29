/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imultimodal;

/**
 *
 * @author utilizador
 */
public class Relogio {
    
  private int horas ;
  private int minutos ;
  private int segundos ;
  
  
  public Relogio(){
      
  }

    public Relogio(int horas, int minutos, int segundos) {
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }
    public Relogio(int horas, int minutos){
        this.horas=horas;
        this.minutos=minutos;
        this.segundos=0;
    }
    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    @Override
    public String toString() {
        return  horas + ":" + minutos + ":" + segundos ;
    }
    
    
    
    
    
    
}
