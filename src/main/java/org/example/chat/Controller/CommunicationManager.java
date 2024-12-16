package org.example.chat.Controller;

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
            // Inicializar primero el ObjectInputStream
            this.entrada = new ObjectInputStream(this.socket.getInputStream());
            // Luego el ObjectOutputStream
            this.salida = new ObjectOutputStream(this.socket.getOutputStream());
            // Iniciar el hilo para recibir mensajes
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
                // Asegurarse de cerrar los flujos y el socket al terminar
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
            // Aquí se podría agregar lógica de sincronización si fuera necesario
            send(message, salida);  // Asegúrate de usar el flujo correcto
        } catch (IOException e) {
            System.err.println("Error al enviar el mensaje: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
