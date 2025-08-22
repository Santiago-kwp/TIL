package chapter33;

import java.io.IOException;
import java.nio.file.*;

public class CopyFileFromFiles {
    public static void main(String[] args) throws IOException {
        String cdir = Paths.get("").toAbsolutePath().toString()+"/src/chapter33";
        Path src = Paths.get(cdir+"/CopyFileFromFiles.java");
        Path dst = Paths.get(cdir+"/CopyFileFromFiles2.java");

        // src가 지시하는 파일을 dst가 자시하는 위치와 이름으로 복사
        Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);

    }
}
