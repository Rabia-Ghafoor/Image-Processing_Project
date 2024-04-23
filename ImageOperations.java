import java.awt.*;

class ImageOperations {

  public static Image zeroRed(Image img){

      for (int y = 0; y < img.getHeight(); y++) {
          for (int x = 0; x < img.getWidth(); x++) {
              Color pixel = new Color(img.getRGB(x, y));
              int newRed = 0;
              int green = pixel.getGreen();
              int blue = pixel.getBlue();
              int alpha = pixel.getAlpha();
              int rgb = (alpha << 24) | (newRed << 16) | (green << 8) | blue;
              img.setRGB(x, y, rgb);
          }
      }
      return img;



    }


    public static Image grayscale(Image img){
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color pixel = new Color(img.getRGB(x, y));
                int gray = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                int rgb = (pixel.getAlpha() << 24) | (gray << 16) | (gray << 8) | gray;
                img.setRGB(x, y, rgb);
            }
        }
        return img;

    }



    public static Image invert(Image img){

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
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

    public static Image crop(Image, int x1, int y1, int w, int h){

      return img.getSubimage(x1, y1, w, h);


    }

    public static Image mirror(Image img, String mode){

        if (mode.equals("horizontal")) {
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth() / 2; x++) {
                    Color left = new Color(img.getRGB(x, y));
                    Color right = new Color(img.getRGB(img.getWidth() - x - 1, y));
                    img.setRGB(x, y, right.getRGB());
                    img.setRGB(img.getWidth() - x - 1, y, left.getRGB());
                }
            }
        } else if (mode.equals("vertical")) {
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
            Image newImage = new Image(img.getWidth() * n, img.getHeight());
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    Color pixel = new Color(img.getRGB(x, y));
                    for (int i = 0; i < n; i++) {
                        newImage.setRGB(x + i * img.getWidth(), y, pixel.getRGB());
                    }
                }
            }
            return newImage;
        } else if (dir.equals("vertical")) {
            Image newImage = new Image(img.getWidth(), img.getHeight() * n);
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    Color pixel = new Color(img.getRGB(x, y));
                    for (int i = 0; i < n; i++) {
                        newImage.setRGB(x, y + i * img.getHeight(), pixel.getRGB());
                    }
                }
            }
            return newImage;
        }
        return null;



    }

    public static void main(String[] args) {

        //Create a new PpmImage object with width 10 and height 10
        PpmImage image = new PpmImage(10, 10);
        //Create a new Color object with RGB values 255, 255, 255
        Color color = new Color(255, 255, 255);
        //Set the color of the pixel at position 0, 0 to the color object
        image.setPixel(0, 0, color);
        //Get the color of the pixel at position 0, 0
        Color pixelColor = image.getPixel(0, 0);
        //Print the RGB values of the pixel color
        System.out.println(pixelColor.getRed() + " " + pixelColor.getGreen() + " " + pixelColor.getBlue());

    }



}
