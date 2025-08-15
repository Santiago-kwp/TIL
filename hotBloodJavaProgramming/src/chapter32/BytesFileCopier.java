package chapter32;

import java.io.*;
import java.util.Scanner;

public class BytesFileCopier {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("대상 파일: ");
        String src = sc.nextLine();

        System.out.println("사본 이름: ");
        String dst = sc.nextLine();

        try(InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dst)) {
            int data;
            while(true) {
                data = in.read(); // read from file by 1byte
                if(data == -1) // no more data exists
                    break;
                out.write(data); // write file by 1 byte
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
