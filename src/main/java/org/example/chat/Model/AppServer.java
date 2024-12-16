package org.example.chat.Model;

import org.example.chat.Controller.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AppServer implements Runnable{

    private List<Socket> sockets;

    public AppServer() {
        sockets = new ArrayList<>();
    }

    @Override
    public void run() {
        try (ServerSocket socketServidor = new ServerSocket(50000)) {
            System.out.println("Servidor establecido");
            ChanelManager chanelManager = new ChanelManager();
            while (true) {
                Socket socketComunicacion = socketServidor.accept();
                sockets.add(socketComunicacion);
                System.out.printf("Cliente conectado desde %s:%d.\n", socketComunicacion.getInetAddress().getHostAddress(), socketComunicacion.getPort());
                chanelManager.add(socketComunicacion);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
