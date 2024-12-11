package org.example.chat.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
        try{
            if (communicationManager.equals(this.communicationManager)) {
                if (message != null) {
                    texto.appendText(message+"\r\n");
                }
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
        if (name!=null) {
            if (!mensaje.getText().equals("")) {
                Message message = new Message(name,mensaje.getText());
                mensaje.setText("");
                System.out.println(message);
                communicationManager.send(message);
            }
        }
    }

    @FXML
    protected void onConnectButtonClick() {
        name = username.getText();
        String ip = this.ip.getText();
        String puerto = this.puerto.getText();
        if (puerto == null || puerto.isEmpty() || ip == null || ip.isEmpty()) {
            puerto = null;
            ip = null;
        }
        if (name != null && ip != null && puerto != null) {
            try {
                communicationManager.setSocket(ip, puerto);
                communicationManager.setReciver(this);
                new Thread(communicationManager).start();
                System.out.println("Conectado");
                conection.setDisable(true);
            } catch (Exception e) {
                System.out.println("Connection Error");
                conection.setDisable(false);
            }
        }
    }

}
