package ivisionfx.controller;

import TUIO.TuioClient;
import TUIO.TuioListener;
import com.almasb.fxgl.app.GameController;
import com.almasb.fxgl.profile.DataFile;
import ivisionfx.GameApplication;
import ivisionfx.interaction.ButtonConfig;
import ivisionfx.interaction.GameLoopTimer;
import ivisionfx.interaction.KeyPolling;
import ivisionfx.interaction.gamepad.GamepadListener;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ResourceBundle;

import static ivisionfx.GameApplication.stage;

public class MainController implements Initializable, GameController {
    @FXML
    private Button toggleFullscreenText;
    @FXML
    private ImageView symbol;

    private TuioListener gamepadListener;

    private final TuioClient client = new TuioClient();

    private final KeyPolling key = KeyPolling.getInstance();

    private GameLoopTimer gameLoop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Verarbeitung der Benutzereingabe über Reactable-Marker.
        gamepadListener = new GamepadListener();

        // UDP-Client, der Datenpakete von Port 3333 abfängt und weiterleitet.
        client.addTuioListener(gamepadListener);
        // Verbindung herstellen und Abfrage starten.
        client.connect();

        // Nur für Entwicklungszwecke
        if(GameApplication.fullscreen) {
            toggleFullscreenText.setText("Fenstermodus");
        } else {
            toggleFullscreenText.setText("Vollbild");
        }
        // ENDE

        // START: Game Loop

        gameLoop  = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {

                getUserInput();

            }
        };

        // ENDE: Game Loop

        gameLoop.start();

    }

    public void getUserInput () {

        // Periodische Tastenabfragen
        if (key.isDown(ButtonConfig.actionPrimary))             System.out.println(key);

        // einmalige Tastenabfragen (innerhalb Anschlagverzögerung)
        if (key.isPressed(ButtonConfig.toggleFullscreen))       toggleFullscreen();
        if(key.isPressed(ButtonConfig.gameMenu))                exit();

    }

    @FXML
    protected void toggleFullscreen() {

        if(stage.isFullScreen()) {
            stage.setFullScreen(false);
            this.toggleFullscreenText.setText("Vollbild");
        } else {
            stage.setFullScreen(true);
            this.toggleFullscreenText.setText("Fenstermodus");
        }
    }



    @Override
    public void exit() {
        gameLoop.stop();
    }

    @Override
    public void gotoGameMenu() {
        pauseEngine();
    }

    @Override
    public void gotoIntro() {
        gameLoop.stop();
    }

    @Override
    public void gotoLoading(@NotNull Runnable runnable) {

    }

    @Override
    public void gotoLoading(@NotNull Task<?> task) {

    }

    @Override
    public void gotoMainMenu() {
        gameLoop.stop();
    }

    @Override
    public void gotoPlay() {
        gameLoop.start();
    }

    @Override
    public void loadGame(@NotNull DataFile dataFile) {

    }

    @Override
    public void pauseEngine() {
        gameLoop.pause();
    }

    @Override
    public void resumeEngine() {
        gameLoop.play();
    }

    @Override
    public void saveGame(@NotNull DataFile dataFile) {

    }

    @Override
    public void startNewGame() {
        Circle circle = new Circle(640, 340, 100, Color.TRANSPARENT);
        circle.setStroke(Color.GREEN);
        circle.setStrokeWidth(0);
        circle.setFill(Color.GREEN);



        FillTransition tlt = new FillTransition(Duration.millis(2000),circle);
        FadeTransition ft = new FadeTransition(Duration.millis(500), circle);
        ScaleTransition st = new ScaleTransition(Duration.millis(500),circle);

        ft.setFromValue(0.6);
        ft.setToValue(1);
        ft.setCycleCount(-1);
        ft.setAutoReverse(true);

        tlt.setFromValue(Color.BLUEVIOLET);
        tlt.setToValue(Color.AQUAMARINE);
        tlt.setCycleCount(-1);
        tlt.setAutoReverse(true);

        st.setFromX(1.1);
        st.setFromY(1.1);
        st.setToX(1.0);
        st.setToY(1.0);
        st.setAutoReverse(true);
        st.setCycleCount(-1);


        if(circle.intersects(circle.getLayoutBounds())) {
            System.out.println("Kreis berührt sich selbst!");
        }

        Pane pane = new Pane(circle);
        Scene scene = new Scene(pane,1280,720);
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
        ft.play();
        tlt.play();
        st.play();
    }

    public void shutDownComputer () {
        try {

            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("shutdown -s -t 10");

        } catch (Exception e6) {
            System.err.println("Not Valid!!!");
        }
    }
}