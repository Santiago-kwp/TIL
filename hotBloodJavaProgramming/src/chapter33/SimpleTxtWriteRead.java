package chapter33;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class SimpleTxtWriteRead {
    public static void main(String[] args) throws IOException {
        String cdir = Paths.get("").toAbsolutePath().toString();
        Path fp = Paths.get(cdir+"/simple.txt");
        String st1 = "One Simple String";
        String st2 = "Two Simple String";
        List<String> lst1 = Arrays.asList(st1, st2);

        Files.write(fp, lst1); // save String to File
        List<String> lst2 = Files.readAllLines(fp); // read String from File
        System.out.println(lst2); // [One Simple String, Two Simple String]
    }
}
