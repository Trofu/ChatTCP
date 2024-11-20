package org.example.chat.Model;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AppServer implements Runnable {

    private Socket socketComunicacion;

    public AppServer(Socket socketComunicacion) {
        this.socketComunicacion = socketComunicacion;
    }

    @Override
    public void run(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socketComunicacion.getInputStream()));
            PrintWriter pw = new PrintWriter(new BufferedOutputStream(socketComunicacion.getOutputStream()),true);

            String linea,respuesta="";
            while ((linea=br.readLine())!=null){
                switch (Integer.parseInt(linea.split(":")[0])){
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                }

                pw.println(respuesta);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
