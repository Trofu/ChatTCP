package org.example.chat.Model;

import org.example.chat.Controller.CommunicationManager;

public interface Receiver {
    void recive(Message message);
    void remove(CommunicationManager communicationManager);

}
