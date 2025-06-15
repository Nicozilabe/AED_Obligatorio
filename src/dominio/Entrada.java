/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.time.LocalDate;

/**
 *
 * @author nicoz
 */
public class Entrada implements Comparable<Entrada>{
    private Cliente cliente;
    private Evento evento;
    private boolean esValida;
    private LocalDate fecha;

    
    public Entrada(Cliente cliente, Evento evento) {
        this.cliente = cliente;
        this.evento = evento;
        this.esValida = true; 
        this.fecha = LocalDate.now(); 
    }


    public Cliente getCliente() {
        return cliente;
    }


    public void devolverEntrada() {
        this.esValida = false;
    }


    public Evento getEvento() {
        return evento;
    }

    public boolean esValida() {
        return esValida;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public int compareTo(Entrada o) {
        int eventoComparison = this.evento.compareTo(o.evento);
        if (eventoComparison != 0) {
            return eventoComparison;
        }
        return this.cliente.compareTo(o.cliente);
    }


    @Override
    public String toString() {
        return evento.toString() + " - " + cliente.toString();
    }


    @Override
    public boolean equals(Object obj) {
        Entrada other = (Entrada) obj;
        return this.cliente.equals(other.cliente) && this.evento.equals(other.evento);
    }
    //evaluar propiedad boolean EsValida
}
