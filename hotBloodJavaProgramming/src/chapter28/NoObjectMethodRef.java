package chapter28;

import java.util.function.ToIntBiFunction;

class IBox {
    private int n;
    public IBox(int i) { n = i; }
    public int larger(IBox b) {
        if (n > b.n)
            return n;
        else return b.n;
    }

}

public class NoObjectMethodRef {
    public static void main(String[] args) {
        IBox ib1 = new IBox(5);
        IBox ib2 = new IBox(7);

        // 두 상자에 저장된 값 비교하여 더 큰 값 반환
//        ToIntBiFunction<IBox, IBox> bf = (b1, b2) -> b1.larger(b2); // 람다식 활용
        ToIntBiFunction<IBox, IBox> bf = IBox::larger; // 메소드 참조 이용

        int bigNum = bf.applyAsInt(ib1, ib2);
        System.out.println(bigNum);

    }
}
