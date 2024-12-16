package org.example.chat.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.chat.Model.Message;
import org.example.chat.Model.Receiver;

import java.net.Socket;


public class ClientController implements Receiver {

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

    public ClientController() {}

    @Override
    public void recive(Message message) {
        try{
            if (message != null) {
                System.out.println(message);
                texto.appendText(message+"\r\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void remove(CommunicationManager communicationManager) {

    }

    @FXML
    protected void onSendButtonClick(){
        if (name!=null && communicationManager != null) {
            if (!mensaje.getText().equals("")) {
                Message message = new Message(name,mensaje.getText());
                mensaje.setText("");
                System.out.println(message);
                communicationManager.send(message);
            }
        }else {
            texto.appendText("/*Conectate al servidor.*/");
        }
    }

    @FXML
    protected void onConnectButtonClick() {
        username.setText("Juan");
        ip.setText("127.0.0.1");
        puerto.setText("50000");
        name = username.getText();
        String ip = this.ip.getText();
        String puerto = this.puerto.getText();
        if (puerto == null || puerto.isEmpty() || ip == null || ip.isEmpty()) {
            puerto = null;
            ip = null;
        }
        if (name != null && ip != null && puerto != null) {
            try {
                int puertoInt = Integer.parseInt(puerto);
                communicationManager = new CommunicationManager<>(new Socket(ip, puertoInt),this);
                System.out.println("Conectado");
                conection.setDisable(true);
                new Thread(communicationManager).start();
            } catch (Exception e) {
                System.out.println("Connection Error");
                conection.setDisable(false);
            }
        }
    }

}
