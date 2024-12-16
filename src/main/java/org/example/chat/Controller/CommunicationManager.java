package org.example.chat.Controller;

import javafx.fxml.FXML;
import org.example.chat.Model.Message;
import org.example.chat.Model.Receiver;
import org.example.chat.Model.Sender;

import java.io.*;
import java.net.Socket;
public class CommunicationManager<T> implements Runnable, Sender<T> {

    private Socket socket;
    private Receiver receiver;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    public CommunicationManager(Socket socket, Receiver receiver){
        try {
            this.socket = socket;
            this.receiver = receiver;
            this.salida = new ObjectOutputStream(this.socket.getOutputStream());
            this.entrada = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            System.err.println("Error al inicializar los flujos: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {
            while (true) {
                Object obj = entrada.readObject();
                if (obj instanceof Message message) {
                    receiver.recive(message);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al recibir el mensaje: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (entrada != null) entrada.close();
                if (salida != null) salida.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar los flujos: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void send(T message) {
        try {
            send(message, salida);
        } catch (IOException e) {
            System.err.println("Error al enviar el mensaje: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        if (entrada != null) entrada.close();
        if (salida != null) salida.close();
        if (socket != null && !socket.isClosed()) socket.close();
    }

    public Socket getSocket() {
        return socket;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public ObjectInputStream getEntrada() {
        return entrada;
    }

    public ObjectOutputStream getSalida() {
        return salida;
    }
}
