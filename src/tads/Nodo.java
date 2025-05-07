/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author nicoz
 */
public class Nodo<T extends Comparable<T>> implements Comparable<T> {

    private T dato;
    private Nodo<T> siguiente;
    
    public Nodo(T dato){
        this.setDato(dato);
        this.setSiguiente(null);
    }

  
    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

   
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
    @Override
    public int compareTo(T o) {
        Nodo otro = (Nodo) o;
        return (otro.getDato().compareTo(this.dato));
    }

}