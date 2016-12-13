/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author lalo
 */
public class Controlador {

    private PantallaServidor pantalla;
    private Modelo modelo;

    public Controlador(PantallaServidor pantalla, Modelo modelo) {
        this.pantalla = pantalla;
        this.modelo = modelo;
        bindingModelo();
        setUpEventosUsuario();
        setUpEventosProductos();
    }

    void mostrarPantalla() {
        pantalla.setLocationRelativeTo(null);
        pantalla.setVisible(true);
    }

    private void setUpEventosUsuario() {
        pantalla.getAgregar().setAction(new AbstractAction("Agregar") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                modelo.getListUsuarios().addElement(obtenerUsuario());
                limpiarCamposUsuario();
            }
        });
        pantalla.getLimpiar().setAction(new AbstractAction("Limpiar") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                limpiarCamposUsuario();
            }
        });
        pantalla.getEliminar().setAction(new AbstractAction("Eliminar") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int index = pantalla.getListaUsuarios().getSelectedIndex();
                modelo.getListUsuarios().remove(index);
            }
        });
        pantalla.getGuardar().setAction(new AbstractAction("Guardar") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ArchivoUtil.guardarArchivo(Constantes.ARCHIVO_USUARIOS, modelo.getListUsuarios());
            }
        });
    }

    private void limpiarCamposUsuario() {
        pantalla.getNombreText().setText("");
        pantalla.getContrasenaText().setText("");
    }

    private Usuario obtenerUsuario() {
        String nombre = pantalla.getNombreText().getText();
        String password = pantalla.getContrasenaText().getText();
        return new Usuario(nombre, password);
    }

    private void bindingModelo() {
        pantalla.getListaUsuarios().setModel(modelo.getListUsuarios());
        pantalla.getListaProductos().setModel(modelo.getListProductos());
    }

    private void setUpEventosProductos() {
        pantalla.getAgregarProductos().setAction(new AbstractAction("Agregar") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                modelo.getListProductos().addElement(obtenerProducto());
                limpiarCamposProducto();
            }
        });
        pantalla.getLimpiarProductos().setAction(new AbstractAction("Limpiar") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                limpiarCamposProducto();
            }
        });
        pantalla.getEliminarProductos().setAction(new AbstractAction("Eliminar") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int index = pantalla.getListaProductos().getSelectedIndex();
                modelo.getListProductos().remove(index);
            }
        });
        pantalla.getGuardarProductos().setAction(new AbstractAction("Guardar") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ArchivoUtil.guardarArchivo(Constantes.ARCHIVO_PRODUCTOS, modelo.getListProductos());
            }
        });
    }
    
    private void limpiarCamposProducto() {
        pantalla.getProductoText().setText("");
        pantalla.getPrecioText().setText("");
    }

    private Productos obtenerProducto() {
        String nombre = pantalla.getProductoText().getText();
        String precio = pantalla.getPrecioText().getText();
        return new Productos(nombre, precio);
    }

}
