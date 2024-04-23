import java.awt.*;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


class PpmImage extends Image {

        public PpmImage(int width, int height) {
            //Constructor to create a PpmImage with specified width and height
            PpmImage(int width, int height) {
                //Call superclass constructor to initialize width, height, and colors array
                super(width, height);
            }

            //Constructor to create a PpmImage by reading data from a provided PPM file
            PpmImage(String filename) throws FileNotFoundException {
                readImage(filename);
            }

            void readImage(String filename) throws FileNotFoundException {
                //Create a Scanner to read from the file
                Scanner sc = new Scanner(new File(filename));
                sc.nextLine();

                //Initialize the colors array with the correct dimensions
                int width = sc.nextInt();
                int height = sc.nextInt();
                this.setWidth(width);
                this.setHeight(height);
                this.setColors(new Color[height][width]);


                //Iterate over each pixel in the image
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        int red = sc.nextInt();
                        int green = sc.nextInt();
                        int blue = sc.nextInt();

                        //Create a Color object and store it in the colors array
                        this.getColors()[i][j] = new Color(red, green, blue);
                    }
                }
                sc.close();
            }

    @Override
    public void output(String filename) throws IOException {
    }
}


    public void setPixel(int x, int y, Color color) {
        getColors()[y][x] = color;
    }

    public Color getPixel(int x, int y) {
        return getColors()[y][x];
    }

    public void toGrayscale() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                Color color = getPixel(x, y);
                int average = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                Color gray = new Color(average, average, average);
                setPixel(x, y, gray);
            }
        }
    }


}
