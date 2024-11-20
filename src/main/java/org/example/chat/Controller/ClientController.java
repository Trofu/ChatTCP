package org.example.chat.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.chat.Model.Message;
import org.example.chat.Model.Reciver;

public class ClientController implements Reciver {

    private CommunicationManager communicationManager;
    private String name;

    @FXML
    private TextField username;
    @FXML
    private TextField ip;
    @FXML
    private TextField puerto;
    @FXML
    private TextField mensaje;
    @FXML
    private TextArea texto;
    @FXML
    private Button conection;
    @FXML
    private Button enviar;

    public ClientController() {
        this.communicationManager = new CommunicationManager<>();
    }

    @Override
    public void recive(Message message, CommunicationManager communicationManager) {

    }

    @Override
    public void remove(CommunicationManager communicationManager) {

    }

    @FXML
    protected void onSendButtonClick(){

    }

    @FXML
    protected void onConnectButtonClick(){
        name = username.getText();
        communicationManager.setName(name);

    }

}
