/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tads;

/**
 *
 * @author nicoz
 */

//Lista no ordenada
public interface IListaN<T extends Comparable<T>> extends ILista<T>{
   
    public void agregarFinal (T x); 
     
    public void agregarInicio (T x);

}