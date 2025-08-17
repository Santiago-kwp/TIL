package chapter33;

import java.nio.ByteBuffer;

public class BufferPositionCheck {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(1024);
        System.out.println("Position: " + bb.position());
        bb.putInt(120);
        System.out.println("Position: " + bb.position());
        bb.putDouble(0.24);
        System.out.println("Position: " + bb.position());
        bb.flip(); // 읽기 모드로 전환
        System.out.println("Position: " + bb.position());
        int n = bb.getInt();
        System.out.println(n);
        System.out.println("Position: " + bb.position());
        double d = bb.getDouble();
        System.out.println(d);
        System.out.println("Position: " + bb.position());


    }
}
/* 실행 결과
Position: 0
Position: 4
Position: 12
Position: 0
120
Position: 4
0.24
Position: 12

