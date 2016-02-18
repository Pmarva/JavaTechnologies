package Practice1.exercises;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String... args) {
        ConsoleDrawer.draw("tere, mina olen");
     // ConsoleDrawer.draw("Juttu blablablasfkosghiwe");
    }
}

class ConsoleDrawer {

    public static void draw(String str) {
        BufferedImage inputImage = generateImageFrom(str.toUpperCase());
        //writeImage(inputImage, "out.png");
        int[][] rgb_values = getRgbFromImage(inputImage);

        for (int[] row : rgb_values) {
            for (int rgb : row) {
                Color c = new Color(rgb);

                if (c.equals(Color.BLUE)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    private static int[][] getRgbFromImage(BufferedImage image) {

        int width = image.getWidth();
        int height = image.getHeight();
        int[][] output = new int[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                output[row][col] = image.getRGB(col, row);
            }
        }
        return output;
    }

    private static BufferedImage generateImageFrom(String text) {
        Graphics2D g2 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();
        FontRenderContext frc = g2.getFontRenderContext();
        GlyphVector gv = g2.getFont().createGlyphVector(frc, text);
        Rectangle r = gv.getPixelBounds(null, 0, 0);

        Integer w = (int) Math.round(r.getWidth()); // rounding to nearest int
        Integer h = (int) Math.round(r.getHeight()); // rounding to nearest int

        final int PADDING = 1; // BufferedImage
        BufferedImage image = new BufferedImage(w + PADDING, h + PADDING, BufferedImage.TYPE_INT_ARGB);

        Graphics g = image.getGraphics();
        g.setColor(Color.BLUE);
        g.drawString(text, 0, h); //position from bottom left

        return image;
    }

    private static void writeImage(BufferedImage image, String fname) {
        try {
            ImageIO.write(image, "png", new File(fname));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}