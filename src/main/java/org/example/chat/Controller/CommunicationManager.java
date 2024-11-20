package org.example.chat.Controller;

import org.example.chat.Model.Message;
import org.example.chat.Model.Sender;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class CommunicationManager<T> implements Runnable, Sender<T> {


    private String name;
    private Socket socket;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {

    }

    @Override
    public void send(T message, ObjectOutputStream oos) {

    }
}
