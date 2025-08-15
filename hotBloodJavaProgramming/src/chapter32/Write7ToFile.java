package chapter32;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Write7ToFile {
    public static void main(String[] args) throws IOException {
        OutputStream out = new FileOutputStream("data.dat");
        out.write(7);
        out.close();


    }
}
