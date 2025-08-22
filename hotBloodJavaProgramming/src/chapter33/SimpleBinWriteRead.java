package chapter33;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SimpleBinWriteRead {
    public static void main(String[] args) throws IOException {
        String cdir = Paths.get("").toAbsolutePath().toString();
        Path fp = Paths.get(cdir+"/Simple.bin");

        // create a file, if the file exists raise Exception
        fp = Files.createFile(fp);

        byte buf1[] = {0x13, 0x14, 0x15}; // data which will be written in the file
        for (byte b : buf1)
            System.out.print(b + "\t");
        System.out.println();

        // write the data to File
        Files.write(fp, buf1, StandardOpenOption.APPEND);

        // Read the data from File
        byte buf2[] = Files.readAllBytes(fp);

        for(byte b : buf2)
            System.out.print(b + "\t");
        System.out.println();

    }
}
