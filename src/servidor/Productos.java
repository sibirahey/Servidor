/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author lalo
 */
public class Productos implements ToFile {

    private String nombre;
    private int precio;

    public Productos(String nombre, String contraseña) {
        this.nombre = nombre;
        this.precio = Integer.parseInt(contraseña);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format("%s\t\t\t$%s", nombre, precio);
    }

    @Override
    public String toFile() {
        return String.format("%s%s%s", nombre, Constantes.REGEX_USUARIOS, precio);
    }

}
