import java.awt.*;
import java.io.IOException;

class ImageOperations  {

    // what is missing from this class that would generate the ppm file?
    // why can't I view any executable file as of now?
    // what kind of things to check in the testcase? cause these are ppm files, like how do i compare the output
    // What do we need to do with the terminal to generate the ppm file?
   // https://www.cs.rhodes.edu/welshc/CS141/F19/ppmReader.html, what are we supposed to do with this, apart froom viewing the image



  public static Image zeroRed(Image img){

      for (int y = 0; y < img.getHeight(); y++) {

          for (int x = 0; x < img.getWidth(); x++) {

              // get the color of the pixel at position x, y
              Color pixel = new Color(img.getRGB(x, y));

                // set the red value of the pixel to 0

              int newRed = 0;

              // get the green, blue, and alpha values of the pixel
              int green = pixel.getGreen();
              int blue = pixel.getBlue();
              int alpha = pixel.getAlpha();

                // create a new color object with the new red value and the original green, blue, and alpha vals
              int rgb = (alpha << 24) | (newRed << 16) | (green << 8) | blue;

                 // set the color of the pixel at position x, y to the new color
              img.setRGB(x, y, rgb);
          }
      }
      return img;

    }

    public static Image grayscale(Image img){

        for (int y = 0; y < img.getHeight(); y++) { //looping through the height of the image

            for (int x = 0; x < img.getWidth(); x++) { //looping through the width of the image
              //get the color of the pixel at position x, y
                Color pixel = new Color(img.getRGB(x, y));
                //calculate the average of the red, green, and blue values of the pixel
                int gray = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                //create a new color object with the average value
                int rgb = (pixel.getAlpha() << 24) | (gray << 16) | (gray << 8) | gray;
                //set the color of the pixel at position x, y to the new color
                img.setRGB(x, y, rgb);
            }
        }

        return img;

    }

    public static Image invert(Image img){

        for (int y = 0; y < img.getHeight(); y++) { //looping through the height of the image
            for (int x = 0; x < img.getWidth(); x++) { //looping through the width of the image
                Color pixel = new Color(img.getRGB(x, y));
                int red = 255 - pixel.getRed();
                int green = 255 - pixel.getGreen();
                int blue = 255 - pixel.getBlue();
                int alpha = pixel.getAlpha();
                int rgb = (alpha << 24) | (red << 16) | (green << 8) | blue;
                img.setRGB(x, y, rgb);
            }
        }
        return img;

    }



    public static Image crop(Image img, int x1, int y1, int w, int h){

        Image myImage = new Image(w, h) {
            @Override
            public void output(String filename) throws IOException {

            }
        }; //creating  a new image with width w and height h
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Color pixel = new Color(img.getRGB(x1 + x, y1 + y));
              myImage.setRGB(x, y, pixel.getRGB());
            }
        }
        return myImage;

    }

    public static Image mirror(Image img, String mode){

        if (mode.equals("Horizontal")) {
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth() / 2; x++) {
                    Color left = new Color(img.getRGB(x, y));
                    Color right = new Color(img.getRGB(img.getWidth() - x - 1, y));
                    img.setRGB(x, y, right.getRGB());
                    img.setRGB(img.getWidth() - x - 1, y, left.getRGB());
                }
            }
        } else if (mode.equals("Vertical")) {
            for (int y = 0; y < img.getHeight() / 2; y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    Color top = new Color(img.getRGB(x, y));
                    Color bottom = new Color(img.getRGB(x, img.getHeight() - y - 1));
                    img.setRGB(x, y, bottom.getRGB());
                    img.setRGB(x, img.getHeight() - y - 1, top.getRGB());
                }
            }
        }
        return img;

    }







    public static  Image repeat(Image img, int n, String dir){

        if (dir.equals("horizontal")) {
            Image myImage = new Image(img.getWidth() * n, img.getHeight()) {
                @Override
                public void output(String filename) throws IOException {

                }
            };
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    Color pixel = new Color(img.getRGB(x, y));
                    for (int i = 0; i < n; i++) {
                        myImage.setRGB(x + i * img.getWidth(), y, pixel.getRGB());
                    }
                }
            }
            return myImage;
        } else if (dir.equals("vertical")) {

            Image myImage = new Image(img.getWidth() * n, img.getHeight()) {
                @Override
                public void output(String filename) throws IOException {

                }
            };
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    Color pixel = new Color(img.getRGB(x, y));
                    for (int i = 0; i < n; i++) {
                        myImage.setRGB(x, y + i * img.getHeight(), pixel.getRGB());
                    }
                }
            }
            return myImage;
        }
        return null;



    }

    public static void main(String[] args) {


      if (args[0].equals("--zeroRed")) {
            Image img = new Image();
            Image myImage = zeroRed(img);
            //write the new image to a file
        } else if (args[0].equals("--grayscale")) {
            Image img = new Image();
            Image myImage = grayscale(img);
            //write the new image to a file
        } else if (args[0].equals("--invert")) {
            Image img = new Image();
            Image myImage = invert(img);
            //write the new image to a file
        } else if (args[0].equals("--crop")) {
            Image img = new Image();
            int x1 = Integer.parseInt(args[1]);
            int y1 = Integer.parseInt(args[2]);
            int w = Integer.parseInt(args[3]);
            int h = Integer.parseInt(args[4]);
            Image myImage = crop(img, x1, y1, w, h);
            //write the new image to a file
        } else if (args[0].equals("--mirror")) {
            Image img = new Image();
            String mode = args[1];
            Image myImage = mirror(img, mode);
            //write the new image to a file
        } else if (args[0].equals("--repeat")) {
            Image img = new Image();
            int n = Integer.parseInt(args[1]);
            String dir = args[2];
            Image myImage = repeat(img, n, dir);
            //write the new image to a file
        }



    }



}
