module org.apollo.template {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jdk.compiler;
    requires java.desktop;

    opens org.apollo.template to javafx.fxml;
    exports org.apollo.template;
    exports org.apollo.template.Controller;
    opens org.apollo.template.Controller to javafx.fxml;
    exports org.apollo.template.Controller.Rental;
    opens org.apollo.template.Controller.Rental to javafx.fxml;
}