package org.example.chat.Model;

import org.example.chat.Controller.CommunicationManager;

public interface Reciver {
    void recive(Message message, CommunicationManager communicationManager);
    void remove(CommunicationManager communicationManager);

}
