/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author nicoz
 */
public class Calificacion implements Comparable<Calificacion> {
    
    private Integer puntaje;
    private String comentario;
    private Evento evento;
    private Cliente cliente;

    public Calificacion(Integer puntaje, String comentario, Evento evento, Cliente cliente) {
        this.puntaje = puntaje;
        this.comentario = comentario;
        this.evento = evento;
        this.cliente = cliente;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public String getComentario() {
        return comentario;
    }

    public Evento getEvento() {
        return evento;
    }
    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "Calificacion{" + "puntaje=" + puntaje + ", comentario='" + comentario + '\'' + ", evento=" + evento + '}';
    }
    @Override
    public boolean equals(Object obj) {
        Calificacion otro = (Calificacion) obj;
        return (otro.cliente == this.cliente && otro.evento == this.evento);
    }
    @Override
    public int compareTo(Calificacion o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

}
