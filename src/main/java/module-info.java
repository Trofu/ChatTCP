module org.example.chat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    exports org.example.chat.Model;
    opens org.example.chat.Model to javafx.fxml;
    exports org.example.chat.Controller;
    opens org.example.chat.Controller to javafx.fxml;
    exports org.example.chat.View;
    opens org.example.chat.View to javafx.fxml;
}