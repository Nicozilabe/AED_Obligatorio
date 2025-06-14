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

    
    public Entrada(Cliente cliente, Evento evento) {
        this.cliente = cliente;
        this.evento = evento;
    }


    public Cliente getCliente() {
        return cliente;
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

    //evaluar propiedad boolean EsValida
}
