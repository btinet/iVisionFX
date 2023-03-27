module ivisionfx.ivisionfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires annotations;

    opens fxml to javafx.fxml;
    exports ivisionfx.controller;
    opens ivisionfx.controller to javafx.fxml;
    exports ivisionfx;
    opens ivisionfx to javafx.fxml;
}