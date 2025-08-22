package chapter33;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MoveFileFromFiles {
    public static void main(String[] args) throws IOException {
        String cdir = Paths.get("").toAbsolutePath().toString()+"/src/chapter33";
        Path src = Paths.get(cdir+"/Dir1");
        Path dst = Paths.get(cdir+"/Dir2");

        // src가 지시하는 디렉토리를 dst가 자시하는 위치와 이름으로 디렉토리로 이동
        Files.move(src, dst, StandardCopyOption.REPLACE_EXISTING);

    }
}
