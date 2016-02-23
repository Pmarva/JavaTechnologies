/***
 * Image to ASCII, working with colors to, output to file.
 */

package homework.homework3;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.stage.Stage;

import java.io.*;

/**
 * Created by Marva on 18.02.2016.
 */
public class PictureToAc extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    double height = 60.0; // iamge height, automatic aspect ratio.. set 0 for original
    String imageLocation = "http://www.clipartbest.com/cliparts/MiL/r8a/MiLr8a4ia.png"; //image location URL, or file path in pc
    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image(imageLocation, 0, height, true, false);


        FileWriter file = new FileWriter("output.txt");
        PixelReader pr = image.getPixelReader();
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < (int) Math.round(image.getHeight()); y++) {
            for (int x = 0; x < (int) Math.round(image.getWidth()); x++) {
                double r = pr.getColor(x, y).getRed() * 255;
                double g = pr.getColor(x, y).getGreen() * 255;
                double b = pr.getColor(x, y).getBlue() * 255;
                int alpha = (pr.getArgb(x, y) >> 24) & 0xff; // bit shifting for detecting alpha channel, got from google that line

                double l = (0.21 * r) + (0.72 * g) + (0.07 * (b));

                char c = getAciiChar(l);
                if (alpha == 0) {
                    c = ' ';
                }
                sb.append(c);

            }
            sb.append("\n");
        }

        String asciiArt = sb.toString();
        System.out.println(asciiArt);
        file.write(asciiArt); //if you write original size to file and then use notepad++ and zoom all the way out(ctrl + scroll) then its quite cool,
        file.close();
    }

    public char getAciiChar(double g) {
        char str;
        if (g >= 230.0) {
            str = ' ';
        } else if (g >= 200.0) {
            str = '.';
        } else if (g >= 180.0) {
            str = '*';
        } else if (g >= 160.0) {
            str = ':';
        } else if (g >= 130.0) {
            str = 'o';
        } else if (g >= 100.0) {
            str = '&';
        } else if (g >= 70.0) {
            str = '8';
        } else if (g >= 50.0) {
            str = '#';
        } else {
            str = '@';
        }

        return str;
    }
}
