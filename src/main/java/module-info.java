module com.example.hblpsl {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires javafx.media;
    requires java.desktop;

    opens com.example.hblpsl to javafx.fxml;
    exports com.example.hblpsl;
}