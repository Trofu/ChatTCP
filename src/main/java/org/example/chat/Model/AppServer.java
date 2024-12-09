package org.example.chat.Model;

import org.example.chat.Controller.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServer implements Runnable{

    private final static String COD_TEXTO = "UTF-8";

    @Override
    public void run() {
        try (ServerSocket socketServidor = new ServerSocket(50000)) {
            System.out.println("Servidor establecido");
            while (true) {
                // Acepta una conexión de cliente tras otra
                Socket socketComunicacion = socketServidor.accept();
                System.out.printf("Cliente conectado desde %s:%d.\n", socketComunicacion.getInetAddress().getHostAddress(), socketComunicacion.getPort());
                Thread thread = new Thread(new ChanelManager(socketComunicacion));
                thread.start();
            }
        } catch (IOException ex) {
            System.out.println("Excepción de E/S");
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
