package chapter32;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StringReader {
    public static void main(String[] args) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("string.txt"))) {
            String str;
            while(true) {
                str = br.readLine();
                if (str == null)
                    break;
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
