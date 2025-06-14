/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tads;

/**
 *
 * @author nicoz
 */
public interface ILista<T extends Comparable<T>> {
    
    public boolean existeElemento (T x);

    public void eliminarElemento (T x);
    
    public int cantidadElementos ();
     
    public boolean esVacia();
    
    public String mostrar(); 
    
    public void vaciar();
    

    public void eliminarInicio();
    

    public void eliminarFinal();
    
    public T obtenerElemento(T x);
    

    public T obtenerInicio(); 
    public Nodo<T> getNodoInicio();

    public String mostrarN(int n);
}
