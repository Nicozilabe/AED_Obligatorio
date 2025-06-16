/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author nicoz
 */
public class EstadisticaDia implements Comparable<EstadisticaDia> {
    
   private int Dia;
   private int Cant;
   
   public EstadisticaDia(int dia, int cant) {
       this.Dia = dia;
       this.Cant = cant;
   }
   public int getDia() {
       return Dia;
   }
   public int getCant() {
       return Cant;
   }
   public void setCantidad(int cant) {
       this.Cant = cant;
   }

    @Override
    public String toString() {
        return  Dia + "-" + Cant;
    }

   @Override
    public boolean equals(Object obj) {
        EstadisticaDia otro = (EstadisticaDia) obj;
        return (otro.Dia == this.Dia);
    }


   @Override
   public int compareTo(EstadisticaDia o) {
      return Integer.compare(this.Dia, o.Dia);
   }

   
    
}
