package chapter33;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.ByteBuffer;
import java.nio.file.StandardOpenOption;

public class FileRandomAccess {
    public static void main(String[] args) {
        Path fp = Paths.get("data.dat");

        // create buffer
        ByteBuffer wb = ByteBuffer.allocate(1024);

        // save data to buffer
        wb.putInt(120);
        wb.putInt(240);
        wb.putDouble(0.94);
        wb.putDouble(0.75);

        // create one channel
        try(FileChannel fc = FileChannel.open(fp, StandardOpenOption.CREATE,
                StandardOpenOption.READ, StandardOpenOption.WRITE)) {

            // write to file
            wb.flip();
            fc.write(wb);

            // read from file
            ByteBuffer rb = ByteBuffer.allocate(1024); // create buffer
            fc.position(0); // move to head of channel's position
            fc.read(rb);

            // read data from buffer
            rb.flip();
            System.out.println(rb.getInt()); // 정수형을 출력하고, 4바이트만큼 이동 => 120
            rb.position(Integer.BYTES * 2); // 버퍼의 포지션 이동
            System.out.println(rb.getDouble()); // double형 출력 하고 8바이트만큼 이동 => 0.94
            System.out.println(rb.getDouble()); // double형 출력 하고 8바이트만큼 이동 => 0.75
            rb.position(Integer.BYTES); // 버퍼의 포지션을 4바이트로 이동 => 240
            System.out.println(rb.getInt());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
