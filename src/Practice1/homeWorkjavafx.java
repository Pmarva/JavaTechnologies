package Practice1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.stage.Stage;

/**
 * Created by Marva on 17.02.2016.
 */
public class homeWorkjavafx extends  Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Canvas canvas = new Canvas(200,18);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Arial",20));
        gc.setFontSmoothingType(FontSmoothingType.GRAY);

        gc.fillText("marvin".toUpperCase(),10,15);
        WritableImage i = canvas.snapshot(null,null);
        PixelReader pr = i.getPixelReader();

        for (int y = 0; y < (int)Math.round(canvas.getHeight()); y++) {
            for (int x = 0; x < (int)Math.round(canvas.getWidth()); x++) {
                Color c = pr.getColor(x,y);

               if(c.equals(Color.BLACK)){
                   System.out.print("+");
               } else {
                   System.out.print("|");
               }
            }
            System.out.println();
        }
        root.getChildren().addAll(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
