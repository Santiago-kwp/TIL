package chapter32;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class TextReader {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("읽을 파일 : ");
        String src = sc.nextLine();

        try(Reader in = new FileReader(src)) { // String inputStream generate
            int ch;

            while(true) {
                ch = in.read(); // read character
                if(ch==-1)
                    break;
                System.out.print((char)ch); // print character
            }


        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
