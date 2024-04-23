import java.io.IOException;

interface Writable {
    void output(String filename) throws IOException;
}