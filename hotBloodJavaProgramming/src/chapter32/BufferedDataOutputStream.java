package chapter32;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedDataOutputStream {
    public static void main(String[] args) throws IOException {
        try(DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("data.dat")
                )))
        {
         out.writeInt(379);
         out.writeDouble(3.141592);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
