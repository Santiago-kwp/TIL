package chapter33;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {
    public static void main(String[] args) {
        Path pt1 = Paths.get("/JavaStudy/PathDemo.java"); // this is for mac
        Path pt2 = pt1.getRoot(); // 루트 디렉토리 반환
        Path pt3 = pt1.getParent(); // 부모 디렉토리 반환
        Path pt4 = pt1.getFileName(); // 파일 이름 반환

        System.out.println("Absolute: " + pt1);
        System.out.println("Root: " + pt2);
        System.out.println("Parent: " + pt3);
        System.out.println("File: " + pt4);


    }
}
