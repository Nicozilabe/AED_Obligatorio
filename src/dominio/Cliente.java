/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author nicoz
 */
public class Cliente implements Comparable<Cliente> {
    private String Cedula;
    private String Nombre;

    public Cliente(String cedula, String nombre){
        Cedula = cedula;
        Nombre = nombre;
    }

    @Override
    public int compareTo(Cliente o) {
        return this.Cedula.compareTo(o.Cedula);
    }

    public String toString(){
        return Cedula + "-" + Nombre + "#";
    }

    @Override
    public boolean equals(Object obj){
        Cliente otra = (Cliente) obj;
        return (otra.Cedula == this.Cedula);
    }

}
