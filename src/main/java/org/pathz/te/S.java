package org.pathz.te;

public class S {
    public static void main(String[] args) {
        s1();
    }

    public static void s1() {
        System.out.println("s1");
        s2();
    }

    public static void s2() {
        System.out.println("s2");
        s3();
        throw new RuntimeException();
    }

    private static void s3() {
        System.out.println("s3");
    }
}
