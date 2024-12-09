package org.example.chat.Model;

import java.io.Serializable;

public class Message implements Serializable {
    private String user;
    private String message;
    private double red;
    private double blue;
    private double green;

    public Message(String user, String message) {
        this.user = user;
        this.message = message;
    }

    @Override
    public String toString() {
        return user + ": " + message;
    }
}
