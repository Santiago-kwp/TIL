package chapter32;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Write7ToFileAdvanced {
    public static void main(String[] args) throws IOException {
        try(OutputStream out = new FileOutputStream("data.dat")) {
            out.write(7);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
