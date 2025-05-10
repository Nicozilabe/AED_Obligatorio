/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.time.LocalDate;
import tads.ListaN;

/**
 *
 * @author nicoz
 */
public class Evento implements Comparable <Evento> {
    
    private String Codigo;
    private String Descripcion;
    private Integer AforoNecesario;
    private LocalDate Fecha;
    private Sala Sala;
    private Integer EntradasDisponibles;
    public ListaN<Entrada> EntradasVendidas;

    //agregar comentarios
    
    public Evento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha){
        Codigo = codigo;
        Descripcion =descripcion;
        AforoNecesario = aforoNecesario;
        Fecha = fecha;
    }
    public LocalDate getFecha(){
        return Fecha;
    }

    public void setEntradasDisponibles(Integer n){
        EntradasDisponibles = n;
    }

    public void setSala(Sala s){
        Sala = s;
    }
    public Integer getAforo(){
        return AforoNecesario;
    }
    @Override
    public int compareTo(Evento o) {
        return this.Codigo.compareTo(o.Codigo);
    }

    public String toString(){
        return Codigo + "-" + Descripcion + "-" + Sala + "-" +
               EntradasDisponibles + "-" + EntradasVendidas;
    }

    @Override
    public boolean equals(Object obj){
        Evento otro = (Evento) obj;
        return (otro.Codigo == this.Codigo);
    }


}
