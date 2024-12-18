package org.example.chat.Model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public interface Sender<T> extends Serializable {
    default void send(T message, ObjectOutputStream out) throws IOException {
        new Thread(() ->
        {
            try {
                out.writeObject(message);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
