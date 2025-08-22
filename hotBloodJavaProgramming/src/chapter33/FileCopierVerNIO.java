package chapter33;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class FileCopierVerNIO {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("대상 파일: ");
        Path src = Paths.get(sc.nextLine());

        System.out.println("사본 이름: " );
        Path dst = Paths.get(sc.nextLine());

        // create one buffer
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // create two channels
        try(FileChannel ifc = FileChannel.open(src, StandardOpenOption.READ);
        FileChannel ofc = FileChannel.open(dst, StandardOpenOption.WRITE,
                StandardOpenOption.CREATE)) {

            int num;
            while(true) {
                num = ifc.read(buf);  // read data through ifc channel to buffer
                if (num == -1)
                    break;

                buf.flip(); // mode change
                ofc.write(buf); // data transfer through buffer to channel ofc
                buf.clear(); // flush buffer
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
}
