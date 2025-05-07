/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author nicoz
 */
public class ListaN<T extends Comparable<T>> implements IListaN<T> {

    private Nodo<T> inicio;
    private Integer cantElementos = 0;

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

    public ListaN() {
        inicio = null;
    }

    @Override
    public void vaciar() {
        this.inicio = null;
        cantElementos = 0;
    }

    @Override
    public void eliminarInicio() {
        if (!esVacia()) {
            Nodo borrar = inicio;
            inicio = inicio.getSiguiente();
            borrar.setSiguiente(null);
            cantElementos--;
        }
    }

    @Override
    public void eliminarFinal() {
        if (!esVacia()) {
            if (inicio.getSiguiente() == null) {
                this.vaciar();
            } else {

                Nodo actual = inicio;

                while (actual.getSiguiente().getSiguiente() != null) {
                    actual = actual.getSiguiente();

                }
                actual.setSiguiente(null);
            }
            cantElementos--;
        }
    }

    @Override
    public int cantidadElementos() {
        // Nodo actual = inicio;
        // int cant = 0;

        // while (actual != null) {
        //     cant++;
        //     actual = actual.getSiguiente();
        // }

        return cantElementos;
    }

    @Override
    public void agregarFinal(T x) {
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
    public void agregarInicio(T n) {
        Nodo nuevo = new Nodo(n);
        nuevo.setSiguiente(inicio);
        inicio = nuevo;
        cantElementos++;

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

    public T tomar_n(int n) {
        return tomar_nRec(n, inicio).getDato();
    }

    private Nodo<T> tomar_nRec(int n, Nodo<T> nodo) {
        if (n == 1) {
            return nodo;
        }
        return tomar_nRec(n - 1, nodo.getSiguiente());
    }

    @Override
    public T obtenerInicio() {
        return (T) getInicio().getDato();
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

    

    //Busca por valor, si no existe no elimina (evita recorrer doble).
    public boolean eliminarConfirmado(T x){
        if (inicio != null) {

            if (inicio.getDato().equals(x)) {
                inicio = inicio.getSiguiente();
                return true;
            } else {
                Nodo actual = inicio;
                while (actual.getSiguiente() != null && !actual.getSiguiente().getDato().equals(x)) {
                    actual = actual.getSiguiente();
                }
                if (actual.getSiguiente() != null) {
                    Nodo aBorrar = actual.getSiguiente();
                    actual.setSiguiente(aBorrar.getSiguiente());
                    aBorrar.setSiguiente(null);
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    public boolean existeElemento(T x) {
        Nodo actual = getInicio();
        boolean existe = false;

        while (actual != null && !existe) {
            if (actual.getDato().equals(x)) {
                existe = true;
            }
            actual = actual.getSiguiente();
        }

        return existe;
    }

    @Override
    public Nodo getNodoInicio() {
        return inicio;
    }

    @Override
    public void eliminarElemento(T x) {
        if (inicio != null) {

            if (inicio.getDato() == x) {
                inicio = inicio.getSiguiente();
            } else {
                Nodo actual = inicio;
                while (actual.getSiguiente() != null && !actual.getSiguiente().getDato().equals(x)) {
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

}
