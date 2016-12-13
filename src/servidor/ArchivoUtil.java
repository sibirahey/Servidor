/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.DefaultListModel;
import utils.Compra;
import utils.Productos;
import utils.ToFile;

/**
 *
 * @author lalo
 */
public class ArchivoUtil {

    public static DefaultListModel leerArchivoUsuarios(String fileUsuarios,String regex,Class<?> aClass) {
        try {
            DefaultListModel listModel = new DefaultListModel();
            Stream<String> lines = Files.lines(Paths.get(fileUsuarios));
            for (String line : (Iterable<String>) lines::iterator) {
                Object object = agregarObjeto(line, regex, aClass);
                if (object != null) {
                    listModel.addElement(object);
                }
            }
            return listModel;
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void guardarArchivo(String fileUsuarios,DefaultListModel defaultListModel) {
        ArrayList<?> listModel = Collections.list(defaultListModel.elements());
        List<String> lines = new ArrayList<>();
        for (Object object : listModel) {
            lines.add(((ToFile)object).toFile());
        }
        try {
            Files.write(Paths.get(fileUsuarios), lines);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean guardarCompra(String fileCompra ,Compra compra) {
        
        try {
            
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileCompra),StandardOpenOption.APPEND);
            LocalDateTime ahora = LocalDateTime.now(); 
            writer.write(String.format("Usuario:%s\tTotal:%s\tFecha:%s\n", compra.getUsuario(),compra.getTotal(),ahora));
            for (Productos productos : compra.getProductos()) {
                writer.write(String.format("\t%s\t%s\n", productos.getNombre(),productos.getPrecio()));
            }
            writer.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Object agregarObjeto(String line,String regex,Class<?> aClass) {
        Object object = null;
        String[] split = line.split(regex, 2);
        if (split.length == 2 && !split[0].isEmpty() && !split[1].isEmpty()) {
            try {
                Constructor<?> constructor = aClass.getConstructor(String.class,String.class);
                object = constructor.newInstance(split[0], split[1]);
            } catch (Exception  ex) {
                Logger.getLogger(ArchivoUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return object;
    }

}
