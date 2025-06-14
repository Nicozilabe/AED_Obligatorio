/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tads;

/**
 *
 * @author nicoz
 */
public interface ICola<T extends Comparable<T>> {
    
    public boolean estaVacia();
    public void encolar(T x);
    public T desencolar(); //devuelve el valor y lo quita
    public T frente(); //devuelve el valor pero no lo quita
    public void vaciar();
    public int cantidadElementos();
    public void mostrar();


}
