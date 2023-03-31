package ivisionfx;

import ivisionfx.interaction.KeyPolling;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {

    public static Stage stage;

    public static boolean fullscreen = false;
    public static boolean resizable = false;
    public static int width = 1280;
    public static int height = 720;
    @Override
    public void start(Stage stage) throws IOException {

        setStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("/fxml/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        scene.getStylesheets().add(getClass().getResource("/css/demo.css").toExternalForm());
        scene.setFill(new LinearGradient(
                0, 0, 1, 1, true,                      //sizing
                CycleMethod.NO_CYCLE,                  //cycling
                new Stop(0, Color.web("#81c483")),     //colors
                new Stop(1, Color.web("#fcc200"))));
        KeyPolling.getInstance().pollScene(scene);
        stage.setTitle("iVision FX");
        // stage.initStyle(Stage);
        stage.setFullScreen(fullscreen);
        stage.setFullScreenExitHint("Drücke ESC zum Beenden!");
        stage.setResizable(resizable);
        stage.setScene(scene);
        stage.show();

/*


        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Bitte Eingabe tätigen: ");

            // Eingabe der Variable 'name' zuweisen:
            String name = reader.readLine();

            // Ausgabe der Eingabe:
            System.out.println(name);

            // Wenn Eingabe entspricht 'exit', dann aus der Schleife ausbrechen:
            if(name.equals("exit")) {
                System.out.println("Abfrage beendet!");
                break;
            }
        }

 */

    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage currentStage) {
        stage = currentStage;
    }

    public static void main(String[] args) {
        launch();
    }
}