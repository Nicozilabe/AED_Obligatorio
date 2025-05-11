/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import tads.ListaN;

/**
 *
 * @author nicoz
 */
public class Sala implements Comparable<Sala> {

    private String Nombre;
    private Integer Capacidad;
    private ListaN<Evento> Eventos = new ListaN<Evento>();

    public Sala(String n, Integer c){
        Nombre=n;
        Capacidad=c;
    }

   public ListaN<Evento> getEventos(){
    return Eventos;
   }
   public Integer getCapacidad(){
    return Capacidad;
   }


   //se verifica antes de invocar éste método 
   public void addEvento(Evento e){
        Eventos.agregarFinal(e);
   }

public String getNombre(){
    return this.Nombre;
}
   
@Override
 public boolean equals(Object obj){
    Sala otra = (Sala) obj;
    return (otra.Nombre == this.Nombre);
 }

@Override
public int compareTo(Sala o) {
    return this.Nombre.compareTo(o.Nombre);
}

public String toString(){
    return Nombre + "-" + Capacidad;
}

}
