/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import utils.Compra;
import utils.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lalo
 */
class ThreadServer extends Thread {

    Socket socket;
    Modelo modelo;

    public ThreadServer(Socket incoming,Modelo modelo) {
        socket = incoming;
        this.modelo = modelo;
    }

    @Override
    public void run() {
        try {
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine,outputLine;
            //while ((inputLine = in.readLine()) != null) {
            if ((inputLine = in.readLine()) != null) {
                outputLine = processInput(inputLine);
            }
            in.close();
            socket.close();

        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String processInput(String inputLine) {
        String respuesta = null;
        switch(inputLine){
            case "autenticar":
                procesarAutenticar();
                break;
            case "productos":
                procesarProductos();
                break;
            case "compra":
                procesarCompra();
                break;
            default:
                respuesta = "salir";
        }
        return respuesta;
    }

    private void procesarAutenticar() {
        try {
            //mandar ok, recibir objeto, mandar respuesta, cerrar
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("OK");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Usuario usuario = (Usuario)ois.readObject();
            out.println(validarUsuario(usuario)?"true":"false");
            out.close();
            ois.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void procesarProductos() {
        //mandar productos, cerrar
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(modelo.getListProductos());
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void procesarCompra() {
        //mandar ok, recibirCompra, mandar confirmacion, cerrar
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("OK");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Compra compra = (Compra)ois.readObject();
            out.println(ArchivoUtil.guardarCompra("compra.txt", compra)?"true":"false");
            out.close();
            ois.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean validarUsuario(Usuario usuario) {
        return modelo.getListUsuarios().contains(usuario);
    }


}
