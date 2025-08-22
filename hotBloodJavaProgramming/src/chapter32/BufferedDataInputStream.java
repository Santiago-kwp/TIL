package chapter32;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedDataInputStream {
    public static void main(String[] args) throws IOException {
        try(DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("data.dat")
                )))
        {
            int num1 = in.readInt();
            double num2 = in.readDouble();

            System.out.println(num1);
            System.out.println(num2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
