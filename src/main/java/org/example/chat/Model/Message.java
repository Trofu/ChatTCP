package org.example.chat.Model;

public class Message {
    private String user;
    private String message;
    private double red;
    private double blue;
    private double green;

    public Message(String user, String message, double red, double blue, double green) {
        this.user = user;
        this.message = message;
        this.red = red;
        this.blue = blue;
        this.green = green;
    }
}
