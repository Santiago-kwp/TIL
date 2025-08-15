package chapter32;

import java.io.*;
import java.util.Scanner;

public class BufferedFileCopier {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("대상 파일: ");
        String src = sc.nextLine();

        System.out.println("사본 이름: ");
        String dst = sc.nextLine();

        try(InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dst)) {
            byte buf[] = new byte[1024];
            int len;
            while(true) {
                len = in.read(buf); // read from file by buffered byte[]
                if(len == -1) // no more data exists
                    break;
                out.write(buf, 0, len); // write file by len byte
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
