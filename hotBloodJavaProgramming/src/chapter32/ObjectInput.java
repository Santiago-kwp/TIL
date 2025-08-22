package chapter32;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInput {
    public static void main(String[] args) throws IOException {

        try(ObjectInputStream oi = new ObjectInputStream(
                new FileInputStream("Object.bin"))) {
            SBox box1 = (SBox) oi.readObject();
            SBox box2 = (SBox) oi.readObject();

            System.out.println(box1.get()); // Robot
            System.out.println(box2.get()); // Strawberry
        }
        catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
