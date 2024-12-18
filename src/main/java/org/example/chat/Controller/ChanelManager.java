package org.example.chat.Controller;

import org.example.chat.Model.Message;
import org.example.chat.Model.Receiver;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChanelManager implements Receiver {

    private List<CommunicationManager> communicationManagers;

    public ChanelManager() {
        communicationManagers = new ArrayList<>();
    }

    public void add(Socket socket){
        CommunicationManager communicationManager = new CommunicationManager(socket, this);
        communicationManagers.add(communicationManager);
        new Thread(communicationManager).start();
    }

    @Override
    public void recive(Message message) {
        synchronized (communicationManagers) {
            System.out.println(message);
            for (CommunicationManager communicationManager : new ArrayList<>(communicationManagers)) {
                communicationManager.send(message);
            }
        }
    }

    @Override
    public void remove(CommunicationManager communicationManager) {
        synchronized (communicationManagers) {
            if (communicationManagers.contains(communicationManager)) {
                communicationManagers.remove(communicationManager);
                try {
                    communicationManager.close();
                } catch (IOException e) {
                    System.err.println("Error cerrando recursos: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Se ha desconectado un cliente";
    }

}
