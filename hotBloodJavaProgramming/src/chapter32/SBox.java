package chapter32;

import java.io.Serializable;

public class SBox implements Serializable {
    transient String s;
    public SBox(String s) { this.s = s; }
    public String get() {return s;}
}
