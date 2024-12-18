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

    @Override
    public void recive(Message message) {
        try{
            if (message != null) {
                System.out.println("Mensaje recibido: " + message);
                texto.appendText(message+"\r\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void remove(CommunicationManager communicationManager) {
        if (this.communicationManager.equals(communicationManager)) {
            this.communicationManager = null;
        }
    }

    @FXML
    protected void onSendButtonClick(){
        if (name!=null && communicationManager != null) {
            if (!mensaje.getText().equals("")) {
                Message message = new Message(name,mensaje.getText());
                mensaje.setText("");
                System.out.println("Mensaje enviado: " + message.getMessage());
                communicationManager.send(message);
            }
        }else {
            texto.appendText("Conectate al servidor.");
        }
    }

    @FXML
    protected void onConnectButtonClick() {
        if (username.getText().isEmpty()) {
            username.setText("Juan");
        }
        if (ip.getText().isEmpty()) {
            ip.setText("127.0.0.1");
        }
        if (puerto.getText().isEmpty()) {
            puerto.setText("50000");
        }
        name = username.getText();
        String ip = this.ip.getText();
        String puerto = this.puerto.getText();
        if (puerto == null || puerto.isEmpty() || ip == null || ip.isEmpty() || name == null || name.isEmpty()) {
            return;
        }
        System.out.println("Conectando al servidor.");
        try {
            texto.setText("");
            int puertoInt = Integer.parseInt(puerto);
            communicationManager = new CommunicationManager<>(new Socket(ip, puertoInt),this);
            System.out.println("Conectado");
            conection.setDisable(true);
            enviar.setDisable(false);
            mensaje.setEditable(true);
            username.setEditable(false);
            this.ip.setEditable(false);
            this.puerto.setEditable(false);
            new Thread(communicationManager).start();
        } catch (Exception e) {
            System.out.println("Connection Error");
            conection.setDisable(false);
        }
    }

    @Override
    public String toString() {
        conection.setDisable(false);
        enviar.setDisable(true);
        mensaje.setEditable(false);
        username.setEditable(true);
        this.ip.setEditable(true);
        this.puerto.setEditable(true);
        texto.appendText("/*  Se ha desconectado el servidor  */.\n");
        return "Se ha desconectado el servidor.";
    }
}
