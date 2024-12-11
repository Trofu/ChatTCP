package org.example.chat.Controller;

import org.example.chat.Model.Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChanelManager implements Runnable {

    private List<Socket> socketComunicacion;

    public ChanelManager(List<Socket> socketComunicacion) {
        this.socketComunicacion = socketComunicacion;
    }

    public void add(Socket socketComunicacion) {
        this.socketComunicacion.add(socketComunicacion);
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (Socket socketComunicacion : this.socketComunicacion) {
                    ObjectInputStream entrada = new ObjectInputStream(socketComunicacion.getInputStream());
                    ObjectOutputStream salida = new ObjectOutputStream(socketComunicacion.getOutputStream());
                    try{
                        while (true) {
                            // Leer el objeto Message recibido desde el socket
                            Object obj = entrada.readObject();
                            // Comprobamos si el objeto es una instancia de Message
                            if (obj instanceof Message) {
                                Message message = (Message) obj;
                                System.out.println("Mensaje recibido: " + message);
                                salida.writeObject(message);
                            }
                        }
                    }catch (IOException e) {
                        System.out.println("Se ha cerrado la conexion con: " + socketComunicacion.getRemoteSocketAddress());
                    }
                    finally {
                        socketComunicacion.close();
                    }
                }
            }
            // Se crea un ObjectInputStream para leer objetos del socket
        } catch (IOException e) {
            System.err.println("Error al configurar el canal de comunicación");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Error al deserializar el mensaje");
            e.printStackTrace();
        }
    }


}
