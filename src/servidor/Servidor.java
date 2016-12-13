/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Admin
 */
public class Servidor {

    private static PantallaServidor pantalla;
    private static Modelo modelo;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        pantalla = new PantallaServidor();
        modelo = new Modelo();
        Controlador controlador = new Controlador(pantalla, modelo);
        controlador.mostrarPantalla();
        iniciarSocket();
    }

    private static void iniciarSocket() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6000);
        while (true) {
            Socket incoming = serverSocket.accept();
            Thread t = new ThreadServer(incoming,modelo);
            t.start();
        }
    }

}
