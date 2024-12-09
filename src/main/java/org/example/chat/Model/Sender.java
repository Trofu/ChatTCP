package org.example.chat.Model;

import java.io.ObjectOutputStream;
import java.io.Serializable;

public interface Sender<T> extends Serializable {
    void send(T message);
}
