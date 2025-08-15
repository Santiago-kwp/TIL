package chapter32;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class Read7toFile {
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("data.dat");
        int dat = in.read();
        System.out.println(dat);

    }
}
