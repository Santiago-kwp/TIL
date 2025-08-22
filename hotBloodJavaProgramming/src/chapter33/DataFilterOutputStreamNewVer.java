package chapter33;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataFilterOutputStreamNewVer {
    public static void main(String[] args) throws IOException {
        Path fp = Paths.get("data.dat");

        try(DataOutputStream out =
                new DataOutputStream(Files.newOutputStream(fp))) {
            out.writeInt(370);
            out.writeDouble(3.1415921);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
