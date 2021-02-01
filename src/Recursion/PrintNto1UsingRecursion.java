package Recursion;

/**
 * @author kalpak
 *
 * Given an integer N, print N to 1 using recursion.
 *
 */

public class PrintNto1UsingRecursion {
    public static void printNaturalNumbers(int N) {
        if(N == 1) {
            System.out.print(N + " ");
            return;
        }

        System.out.print(N + " ");
        printNaturalNumbers(N-1);
    }

    public static void main(String[] args) {
        printNaturalNumbers(10);
    }
}
