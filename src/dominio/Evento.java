/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.time.LocalDate;

import tads.Cola;
import tads.ListaN;
import tads.ListaO;
import tads.Nodo;

/**
 *
 * @author nicoz
 */
public class Evento implements Comparable<Evento> {

    private String Codigo;
    private String Descripcion;
    private Integer AforoNecesario;
    private LocalDate Fecha;
    private Sala Sala;
    private Integer EntradasDisponibles;
    public ListaN<Entrada> EntradasVendidas;
    public Cola<Entrada> ColaEspera;
    public ListaO<Cliente> ListaClientes;
    public Double Puntaje;
    public ListaN<Calificacion> Calificaciones;

    // agregar comentarios

    public Evento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {
        Codigo = codigo;
        Descripcion = descripcion;
        AforoNecesario = aforoNecesario;
        Fecha = fecha;
        EntradasVendidas = new ListaN<>();
        ColaEspera = new Cola<>();
        ListaClientes = new ListaO<>();
        EntradasDisponibles = aforoNecesario;
        Puntaje = null;
        Calificaciones = new ListaN<>();
    }

    public LocalDate getFecha() {
        return Fecha;
    }

    public void setEntradasDisponibles(Integer n) {
        EntradasDisponibles = n;

    }

    public void setSala(Sala s) {
        Sala = s;
    }

    public Integer getAforo() {
        return AforoNecesario;
    }

    @Override
    public int compareTo(Evento o) {
        return this.Codigo.compareTo(o.Codigo);
    }

    public String toString() {
        return Codigo + "-" + Descripcion + "-" + Sala.getNombre() + "-"
                + (Sala.getCapacidad() - EntradasVendidas.cantidadElementos()) + "-"
                + EntradasVendidas.cantidadElementos();
    }

    public void comprarEntrada(Entrada e) {
        if (EntradasDisponibles > 0) {
            EntradasVendidas.agregarInicio(e);
            ListaClientes.agregarDato(e.getCliente());
            EntradasDisponibles--;
        } else {
            ColaEspera.encolar(e);
        }
    }

    public Integer getEntradasCantVendidas() {
        return EntradasVendidas.cantidadElementos();
    }

    public Sala getSala() {
        return Sala;
    }

    public String mostrarNUltclientes(int n) {
        Nodo<Entrada> mostrar = EntradasVendidas.getNodoInicio();
        String res = "";
        while (mostrar != null && n > 0) {
            Entrada entrada = mostrar.getDato();
            res += entrada.getCliente() + "#";
            mostrar = mostrar.getSiguiente();
            n--;
        }

        return res;
    }

    public void devolverEntrada(Entrada e) {
        EntradasVendidas.eliminarElemento(e);
        e.getCliente().devolverEntrada(e);
        ListaClientes.eliminarElemento(e.getCliente());
        EntradasDisponibles++;
        if (!ColaEspera.esVacia()) {
            Entrada entradaEspera = ColaEspera.desencolar();
            comprarEntrada(entradaEspera);
        }

    }

    public String mostrarCola() {

        return ListaClientes.mostrar();

    }

    public boolean calificarEvento(Calificacion c) {
        if (Calificaciones.existeElemento(c)) {
            return false;
        }
        
        if (Puntaje == null) {
            Puntaje = c.getPuntaje().doubleValue();
        }else{
            Puntaje = (Puntaje * Calificaciones.cantidadElementos() + c.getPuntaje()) / (Calificaciones.cantidadElementos()+1);
        }
        Calificaciones.agregarFinal(c);
        return true;
    }
    public Double getPuntaje() {
        return Puntaje;
    }
    @Override
    public boolean equals(Object obj) {
        Evento otro = (Evento) obj;
        return (otro.Codigo == this.Codigo);
    }

}
