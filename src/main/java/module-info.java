module ivisionfx.ivisionfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens ivisionfx.ivisionfx to javafx.fxml;
    exports ivisionfx.ivisionfx;
}