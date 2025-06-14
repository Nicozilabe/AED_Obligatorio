/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author nicoz
 */
public class Cola<T extends Comparable<T>> implements ICola<T> {

    private Nodo<T> inicio;
    private Integer cantElementos = 0;

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public void encolar(T x) {
        Nodo nuevo = new Nodo(x);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo actual = inicio;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
                cantElementos++;
            }

            actual.setSiguiente(nuevo);

        }
    }

    @Override
    public T desencolar() {

        if (esVacia()) {
              return null; 
        }
       if(inicio.getSiguiente() != null) {
            T dato = inicio.getDato();
           inicio = inicio.getSiguiente();
           cantElementos--;
           return dato;
       } else {
           T dato = inicio.getDato();
           inicio = null; // La cola queda vacía
           cantElementos--;
           return dato;
       }
       
    }

    @Override
    public T frente() {
        if (esVacia()) {
            return null; 
        }
        return inicio.getDato(); 
    }

    @Override
    public void vaciar() {
        inicio = null; 
        cantElementos = 0; 
    }

    @Override
    public int cantidadElementos() {
        return cantElementos;
    }

    @Override
    public void mostrar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
