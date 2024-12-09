package org.example.chat.Controller;

import org.example.chat.Model.Message;
import org.example.chat.Model.Reciver;

import java.io.*;
import java.net.Socket;

public class CommunicationManager<T> implements Runnable {

    private String name;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Reciver reciver;

    public CommunicationManager() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setSocket(String ip, String puerto) throws IOException {
        socket = new Socket(ip, Integer.parseInt(puerto));
        oos = new ObjectOutputStream(socket.getOutputStream()); // ObjectOutputStream para enviar
        ois = new ObjectInputStream(socket.getInputStream()); // ObjectInputStream para recibir
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object obj = ois.readObject(); // Lee el objeto enviado por el servidor
                if (obj instanceof Message) {
                    Message message = (Message) obj;
                    // Notificamos al Reciver (en este caso al ClientController) que recibió el mensaje
                    if (reciver != null) {
                        reciver.recive(message, this);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al recibir el mensaje: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para enviar el mensaje
    public void send(Message message) {
        try {
            oos.writeObject(message);
            oos.flush();
        } catch (IOException e) {
            System.err.println("Error al enviar el mensaje: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar el socket: " + e.getMessage());
        }
    }


    // Método para establecer el Reciver (ClientController)
    public void setReciver(Reciver reciver) {
        this.reciver = reciver;
    }


}
