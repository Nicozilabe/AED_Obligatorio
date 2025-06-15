/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author nicoz
 */
public class Entrada implements Comparable<Entrada>{
    private Cliente cliente;
    private Evento evento;
    private boolean esValida;

    
    public Entrada(Cliente cliente, Evento evento) {
        this.cliente = cliente;
        this.evento = evento;
        this.esValida = true; 
    }


    public Cliente getCliente() {
        return cliente;
    }


    public void devolverEntrada() {
        this.esValida = false;
    }




    @Override
    public int compareTo(Entrada o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
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
