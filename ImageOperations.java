import java.awt.*;

class ImageOperations {

    public static void main(String[] args) {

        //  a new PpmImage object with width 100 and height 100.
        PpmImage image = new PpmImage(100, 100);

        // Print the width and height of the image.
        System.out.println("Width: " + image.getWidth());
        System.out.println("Height: " + image.getHeight());

        // Set the pixel at position (10, 10) to the color (255, 0, 0).
        image.setPixel(10, 10, new Color(255, 0, 0));

    }

}
