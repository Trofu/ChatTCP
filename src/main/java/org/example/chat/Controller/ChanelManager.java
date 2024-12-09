package org.example.chat.Controller;

import org.example.chat.Model.Message;

import java.io.*;
import java.net.Socket;

public class ChanelManager implements Runnable {

    private Socket socketComunicacion;

    public ChanelManager(Socket socketComunicacion) {
        this.socketComunicacion = socketComunicacion;
    }

    @Override
    public void run() {
        try {
            // Se crea un ObjectInputStream para leer objetos del socket
            ObjectInputStream entrada = new ObjectInputStream(socketComunicacion.getInputStream());
            PrintWriter salida = new PrintWriter(socketComunicacion.getOutputStream(), true);
            ObjectOutputStream salida2 = new ObjectOutputStream(socketComunicacion.getOutputStream());
            while (true) {
                try {
                    // Leer el objeto Message recibido desde el socket
                    Object obj = entrada.readObject();
                    // Comprobamos si el objeto es una instancia de Message
                    if (obj instanceof Message) {
                        Message message = (Message) obj;
                        System.out.println("Mensaje recibido: " + message);
                        // Aquí puedes hacer cualquier otra acción con el mensaje recibido
                    }
                } catch (ClassNotFoundException e) {
                    System.err.println("Error al deserializar el mensaje: " + e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    System.err.println("Error de E/S en el socket: " + e.getMessage());
                    e.printStackTrace();
                    break;
                }finally {
                    socketComunicacion.close();
                }
            }
        } catch (IOException e) {
            System.err.println("Error al configurar el canal de comunicación: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
