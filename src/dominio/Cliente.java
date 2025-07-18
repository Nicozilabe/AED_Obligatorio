/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import tads.ListaN;
import tads.Nodo;

/**
 *
 * @author nicoz
 */
public class Cliente implements Comparable<Cliente> {
    private String Cedula;
    private String Nombre;
    private ListaN<Entrada> EntradasCompradas;

    public Cliente(String cedula, String nombre){
        Cedula = cedula;
        Nombre = nombre;
        EntradasCompradas = new ListaN<>();
    }

    public void comprarEntrada(Entrada e){
        EntradasCompradas.agregarFinal(e);
    }


    @Override
    public int compareTo(Cliente o) {
        return this.Cedula.compareTo(o.Cedula);
    }

    public String MostrarEntradasCompradas(){
        String ret = "";
        Nodo<Entrada> actual = EntradasCompradas.getNodoInicio();
        while (actual != null) {
            Entrada entrada = actual.getDato();
            
            if(actual.getSiguiente() == null){
                ret += entrada.getEvento().getCodigo() + "-" + (entrada.esValida() ? "N" : "D");
            } else {
                ret += entrada.getEvento().getCodigo() + "-" + (entrada.esValida() ? "N" : "D") + "#";
            }
            actual = actual.getSiguiente();
        }
        return ret;
    }

    public String getCedula() {
        return Cedula;
    }

    public String toString(){
        return Cedula + "-" + Nombre;
    }

    public void devolverEntrada(Entrada e){
        Entrada puntero = EntradasCompradas.obtenerElemento(e);
        if (puntero != null) {
            puntero.devolverEntrada();
        }
    }


    @Override
    public boolean equals(Object obj){
        Cliente otra = (Cliente) obj;
        return (otra.Cedula == this.Cedula);
    }

}
