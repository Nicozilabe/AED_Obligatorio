/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author nicoz
 */
public class Pila<T extends Comparable<T>> implements IPila<T> {

    private Nodo<T> tope;
    private Nodo<T> bot;
    private int cantidadElementos;
    
    public Pila(){
        this.tope = null;
        this.bot = null;
        cantidadElementos = 0;
    }
    
    
    @Override
    public boolean estaVacia() {
        return (this.tope == null);
    }

    @Override
    public void apilar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        nuevoNodo.setSiguiente(tope);
        tope = nuevoNodo;
        if (bot == null) {
            bot = nuevoNodo;
        }
        cantidadElementos++;

    }

    @Override
    public T top() {
        if(estaVacia()){
            throw new IllegalStateException("La pila esta vacia");
        } 
        
        return (T) tope.getDato();
    }

    @Override
    public Nodo<T> getNodoTope() {
        if(estaVacia()){
            throw new IllegalStateException("La pila esta vacia");
        } 
        
        return tope;
    }

    //Solo se usa para mostrar
    // @Override
    // public Nodo<T> getNodoBot() {
    //     if(estaVacia()){
    //         throw new IllegalStateException("La pila esta vacia");
    //     } 
        
    //     return bot;
    // }

    @Override
    public T desapilar() {
        if(estaVacia()){
            throw new IllegalStateException("La pila esta vacia");
        }

        Nodo nodoTope = tope;
        
        tope = tope.getSiguiente();
        nodoTope.setSiguiente(null);
        
        cantidadElementos--;
        
        return (T) nodoTope.getDato();
        
    }

    @Override
    public void vaciar() {
        this.tope = null;
        this.cantidadElementos = 0;
    }

    @Override
    public void mostrar() {
        Nodo<T> aux = tope;
        while (aux != null){
            System.out.println(aux.getDato());
            aux = aux.getSiguiente();
        }
    }

    @Override
    public int cantidadElementos() {
        return cantidadElementos;
    }
    
    


    public Pila copiarPila() {
        Pila<T> copia = new Pila<>();
        Pila<T> invertida = new Pila<>();

        while (!this.estaVacia()) {
            invertida.apilar(this.desapilar());
        }

        while (!(invertida.estaVacia())) {
            T dato = invertida.desapilar();
            copia.apilar(dato);
            this.apilar(dato);
        }

        return copia;
    }
    
    
}
