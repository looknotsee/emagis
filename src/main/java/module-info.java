module finals {
    requires javafx.controls;
    requires javafx.fxml;
    // allow JavaFX to reflectively construct the Application class
    opens project.core to javafx.graphics, javafx.fxml;

    // allow FXMLLoader to access controller classes in the 'project.controller' package
    opens project.controller to javafx.fxml;
}
