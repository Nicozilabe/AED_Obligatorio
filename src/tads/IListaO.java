/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tads;

/**
 *
 * @author nicoz
 */
//Lista ordenada
public interface IListaO<T extends Comparable<T>> extends ILista<T>{
   
    public void agregarDato (T x); 
     
    public boolean agregarDatoConfirmado(T x);
}