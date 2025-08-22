package chapter32;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StringWriter {
    public static void main(String[] args) throws IOException {
        String ks = "공부에 있어서 꼭 돈이 필요한 것은 아니다.";
        String es = "Life is long if you know how to use it.";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("string.txt"))) {
            bw.write(ks);
            bw.newLine();
            bw.write(es);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
