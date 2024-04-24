import java.awt.*;
import java.awt.Color;
import java.io.*;
import java.util.Scanner;


class PpmImage extends Image {

    public PpmImage(int width, int height) {
        //Constructor to create a PpmImage with specified width and height

        //Call superclass constructor to initialize width, height, and colors array
        super(width, height);
        initializeBlack();
    }

    private void initializeBlack(){
        Color[][] col = new Color[this.getWidth()][this.getHeight()];
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                col[i][j] = new Color(0,0,0);
            }
        }
        // loop each height pixel
            // loop width
                // access new color pixel at positions set equal Color.BLACK

        setColors(col);
    }
    //Constructor to create a PpmImage by reading data from a provided PPM file
    PpmImage(String filename) throws FileNotFoundException {
        super();
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
        sc.nextLine();
        sc.nextLine();


        //Iterate over each pixel in the image
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int red = sc.nextInt();
                int green = sc.nextInt();
                int blue = sc.nextInt();

                //Create a Color object and store it in the colors array
                this.getColors()[j][i] = new Color(red, green, blue);
            }
        }
        sc.close();
    }



    @Override
    public void output(String filename) throws IOException {
        FileWriter writ = new FileWriter(filename);
        writ.write("P3\n");
        writ.write(this.getWidth() + " " + this.getHeight() + "\n");
        writ.write("255\n");

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Color pix = this.getColors()[j][i];
                writ.write(pix.getRed() + " " + pix.getGreen() + " " + pix.getBlue() + " ");
                // create a variable to capture the pixel
                // write to file the red, green, blue



            }
            writ.write("\n");

        }
        writ.close();
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

