/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import javax.swing.DefaultListModel;

/**
 *
 * @author lalo
 */
public class Modelo {

    private DefaultListModel listUsuarios;
    private DefaultListModel listProductos;

    public Modelo() {
        listUsuarios = ArchivoUtil.leerArchivoUsuarios(Constantes.ARCHIVO_USUARIOS, Constantes.REGEX_USUARIOS, Usuario.class);
        listProductos = ArchivoUtil.leerArchivoUsuarios(Constantes.ARCHIVO_PRODUCTOS, Constantes.REGEX_PRODUCTOS, Productos.class);
    }

    public DefaultListModel getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(DefaultListModel listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public DefaultListModel getListProductos() {
        return listProductos;
    }

    public void setListProductos(DefaultListModel listProductos) {
        this.listProductos = listProductos;
    }

}
