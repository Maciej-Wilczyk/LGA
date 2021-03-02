package gui;
import funcitons.Function1;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.net.URL;
import java.util.ResourceBundle;



public class MainWindow implements Initializable {
    @FXML
    public Canvas canvas;

    GraphicsContext gc;
    private String imagePath = "assets/paint.png";
    private Image image;
    private Function1 function1;

    public void beginningData() {
        gc = canvas.getGraphicsContext2D();
        image = new Image(imagePath);
        function1 = new Function1();
        image = function1.setRandomGasCell(image);
        function1.prepare(image);
        gc.drawImage(image, 0, 0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        beginningData();
    }

    public void start() {

        Thread thread = new Thread(() -> {
            while (true) {
                image = function1.makeAll(image);
                gc.drawImage(image, 0, 0);
                image = new Image(imagePath);
            }
        });
        thread.start();
    }


}
