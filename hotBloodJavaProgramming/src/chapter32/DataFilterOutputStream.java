package chapter32;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataFilterOutputStream {
    public static void main(String[] args) throws IOException {
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream("data.dat"))) {
            out.writeInt(3); // int type data write
            out.writeDouble(3.14); // double type data write

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
