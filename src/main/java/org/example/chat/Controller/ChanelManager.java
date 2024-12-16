package org.example.chat.Controller;

import org.example.chat.Model.Message;
import org.example.chat.Model.Receiver;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
        for (CommunicationManager communicationManager : this.communicationManagers) {
            communicationManager.send(message);
        }
    }

    @Override
    public void remove(CommunicationManager communicationManager) {
        communicationManagers.remove(communicationManager);
    }

}
