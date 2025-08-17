package chapter33;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MakeFileAndDir {
    public static void main(String[] args) throws IOException {
        Path cdir = Paths.get("");
        Path fp = Paths.get(cdir.toAbsolutePath().toString()+"/empty.txt");
        fp = Files.createFile(fp); // file generates

        Path dp1 = Paths.get(cdir.toAbsolutePath().toString()+"/Empty");
        dp1 = Files.createDirectory(dp1); // create directory

        Path dp2 = Paths.get(cdir.toAbsolutePath().toString()+"/Empty2/Empty2");
        dp2 = Files.createDirectories(dp2);  // 없으면 경로의 모든 디렉토리를 만들어가면서 다 만듦


    }
}
