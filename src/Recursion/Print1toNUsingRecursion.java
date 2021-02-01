package Recursion;

/**
 * @author kalpak
 *
 * Given an integer N, print 1 to N using recursion.
 *
 */

public class Print1toNUsingRecursion {
    public static void printNaturalNumbers(int N) {
        if(N == 1) {
            System.out.print(N + " ");
            return;
        }

        printNaturalNumbers(N-1);
        System.out.print(N + " ");
    }

    public static void main(String[] args) {
        printNaturalNumbers(10);
    }
}
