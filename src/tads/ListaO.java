/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author nicoz
 */
public class ListaO<T extends Comparable<T>> implements IListaO<T>{
    
        
    private Nodo<T> inicio;

    /**
     * @return the inicio
     */
    private Nodo getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    private void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public ListaO() {
        inicio = null;
    }

    @Override
    public boolean existeElemento(T x) {
        Nodo actual = getInicio();
        boolean existe = false;
        if (inicio == null){
            return existe;
        }
        while (actual != null && !existe) {
            if (actual.getDato().equals(x)) {
                existe = true;
            }
            actual = actual.getSiguiente();
        }

        return existe;
    }

    @Override
    public void eliminarElemento(T x) {
        //Evaluo el primero 

        if (inicio != null) {

            if (inicio.getDato() == x) {
                inicio = inicio.getSiguiente();
            } else {
                Nodo actual = inicio;
                while (actual.getSiguiente() != null && actual.getSiguiente().getDato().equals(x)) {
                    actual = actual.getSiguiente();
                }
                if (actual.getSiguiente() != null) {
                    Nodo aBorrar = actual.getSiguiente();
                    actual.setSiguiente(aBorrar.getSiguiente());
                    aBorrar.setSiguiente(null);

                }
            }
        }
    }

    @Override
    public void vaciar() {
        this.inicio = null;
    }

    @Override
    public void eliminarInicio() {
        if (!esVacia()) {
            Nodo borrar = inicio;
            inicio = inicio.getSiguiente();
            borrar.setSiguiente(null);
        }
    }

    @Override
    public void eliminarFinal() {
        if (!esVacia()) {
            if (inicio.getSiguiente() == null) {
                this.vaciar();
            } else {
                
                Nodo actual = inicio;
                
                while (actual.getSiguiente().getSiguiente()!=null) {
                    actual = actual.getSiguiente();
                    
                }
                actual.setSiguiente(null);
            }
        }
    }

    @Override
    public int cantidadElementos() {
        Nodo actual = inicio;
        int cant = 0;

        while (actual != null) {
            cant++;
            actual = actual.getSiguiente();
        }

        return cant;
    }


    @Override
    public String mostrar() {

        Nodo mostrar = inicio;
        String res = "";
        while (mostrar != null) {
            res += mostrar.getDato() + "#";
            mostrar = mostrar.getSiguiente();
        }
        
        return res;
    }

    
    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    
    public T tomar_n(int n){
        return tomar_nRec(n, inicio).getDato();
    }
    private Nodo<T> tomar_nRec(int n, Nodo<T> nodo){
        if (n == 1){
            return nodo;
        }
        return tomar_nRec(n-1, nodo.getSiguiente());
    }
    

    @Override
    public T obtenerElemento(T x) {
        Nodo actual = getInicio();
        T retorno = null;
        boolean existe = false;

        while (actual != null && !existe) {
            if (actual.getDato().equals(x)) {
                existe = true;
                retorno = (T) actual.getDato();
            }
            actual = actual.getSiguiente();
        }

        return retorno;
    }

    @Override
    public T obtenerInicio() {
        return (T) getInicio().getDato();
    }

    @Override
    public void agregarDato(T x) {
        Nodo<T> nuevo = new Nodo<>(x);
        if (inicio == null || x.compareTo(inicio.getDato()) < 0) {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        } else {
            Nodo<T> actual = inicio;
            while (actual.getSiguiente() != null && x.compareTo((T) actual.getSiguiente().getDato()) >= 0) {
                actual = actual.getSiguiente();
            }
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
        }
    }

    // eviro recorrer doble
    public boolean agregarDatoConfirmado(T x) {
        Nodo<T> nuevo = new Nodo<>(x);
        if (inicio == null || x.compareTo(inicio.getDato()) < 0) {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }else if(inicio.getDato().equals(x)){
            return false;
        } else {
            Nodo<T> actual = inicio;
            while (actual.getSiguiente() != null && x.compareTo((T) actual.getSiguiente().getDato()) > 0) {
                actual = actual.getSiguiente();
            }
            if (actual.getSiguiente() != null &&actual.getSiguiente().getDato().equals(x)) {
                return false;
            }
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);

        }
        return true;
    }


    @Override
    public Nodo<T> getNodoInicio() {
       return inicio;
    }
    
    
}
