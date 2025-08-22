package chapter33;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataFilterInputStreamNewVer {
    public static void main(String[] args) throws IOException {
        Path fp = Paths.get("data.dat");

        try(DataInputStream in =
                new DataInputStream(Files.newInputStream(fp))) {
            int num1 = in.readInt();
            double num2 = in.readDouble();

            System.out.println(num1);
            System.out.println(num2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
