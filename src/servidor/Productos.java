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
    private int contraseña;

    public Productos(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = Integer.parseInt(contraseña);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getContraseña() {
        return contraseña;
    }

    public void setContraseña(int contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public String toFile() {
        return String.format("%s%s%s", nombre,Constantes.REGEX_PRODUCTOS, contraseña);
    }

}
