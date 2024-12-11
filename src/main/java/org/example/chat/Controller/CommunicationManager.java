package org.example.chat.Controller;

import org.example.chat.Model.Message;
import org.example.chat.Model.Reciver;
import org.example.chat.Model.Sender;

import java.io.*;
import java.net.Socket;

public class CommunicationManager<T> implements Runnable, Sender<T> {

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Reciver reciver;

    public CommunicationManager() {}

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
                if (obj instanceof Message message) {
                    reciver.recive(message, this);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al recibir el mensaje: " + e.getMessage());
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

    public void setReciver(Reciver reciver) {
        this.reciver = reciver;
    }


    @Override
    public void send(T message) {
        try {
            oos.writeObject(message);
            oos.flush();
        } catch (IOException e) {
            System.err.println("Error al enviar el mensaje: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
