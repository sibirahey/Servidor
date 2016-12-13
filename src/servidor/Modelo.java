/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import utils.Constantes;
import utils.Productos;
import utils.Usuario;
import javax.swing.DefaultListModel;

/**
 *
 * @author lalo
 */
public class Modelo {

    private DefaultListModel<Usuario> listUsuarios;
    private DefaultListModel<Productos> listProductos;

    public Modelo() {
        listUsuarios = ArchivoUtil.leerArchivoUsuarios(Constantes.ARCHIVO_USUARIOS, Constantes.REGEX_USUARIOS, Usuario.class);
        listProductos = ArchivoUtil.leerArchivoUsuarios(Constantes.ARCHIVO_PRODUCTOS, Constantes.REGEX_USUARIOS, Productos.class);
    }

    public DefaultListModel<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(DefaultListModel<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public DefaultListModel<Productos> getListProductos() {
        return listProductos;
    }

    public void setListProductos(DefaultListModel<Productos> listProductos) {
        this.listProductos = listProductos;
    }

}
