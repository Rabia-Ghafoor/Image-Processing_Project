import java.awt.*;

abstract class Image implements Writable{
    //abstract Image class, which stores an image width and height as instance variables.
    private int width;
    private int height;
    //Non-final arrays
    private Color[][] colors;
    Image(int width, int height) {
        //Image will receive width and height as parameter
        this.setWidth(width);
        this.setHeight(height);
        //Instantiate the color array with width and height
        this.colors = new Color[height][width];
    }
    Image() {
        //Assign w/h to zero. DO NOT INSTANTIATE THE COLOR ARRAY!
        this.width = 0;
        this.height = 0;
    }

    //6 accessor methods, 3 getters and 3 setters.

    //Side note: 90% sure the color had to be one of them but if I'm wrong let me know.
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public Color[][] getColors() {
        return colors;
    }
    public void setColors(Color[][] colors) {
        this.colors = colors;
    }


    public void setRGB(int i, int y, int rgb) {
        this.colors[i][y] = new Color(rgb);

    }

    public int getRGB(int x, int y) {
        return colors[x][y].getRGB();

    }
}
