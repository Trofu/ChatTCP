package org.example.chat.View;



import org.example.chat.Model.AppServer;

public class Servidor {

    public static void main(String[] args) {

        AppServer appServer = new AppServer();
        Thread thread = new Thread(appServer);
        thread.start();
    }
}
