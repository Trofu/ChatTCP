package org.example.chat.Model;

import org.example.chat.Controller.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AppServer implements Runnable{

    private final static String COD_TEXTO = "UTF-8";

    @Override
    public void run() {
        List<Socket> sockets = new ArrayList<>();
        try (ServerSocket socketServidor = new ServerSocket(50000)) {
            System.out.println("Servidor establecido");
            while (true) {
                // Acepta una conexión de cliente tras otra
                Socket socketComunicacion = socketServidor.accept();
                sockets.add(socketComunicacion);
                System.out.printf("Cliente conectado desde %s:%d.\n", socketComunicacion.getInetAddress().getHostAddress(), socketComunicacion.getPort());
                ChanelManager chanelManager= new ChanelManager(sockets);
                Thread thread = new Thread(chanelManager);
                thread.start();
            }
        } catch (IOException ex) {
            System.out.println("Excepción de E/S");
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
