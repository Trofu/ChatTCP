module org.example.chat {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.chat to javafx.fxml;
    exports org.example.chat;
    exports org.example.chat.Controller;
    opens org.example.chat.Controller to javafx.fxml;
}