package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author kalpak
 *
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode.
 * Two asteroids moving in the same direction will never meet.
 *
 *
 * Example 1:
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 *
 * Example 2:
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 *
 * Example 3:
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 *
 * Example 4:
 * Input: asteroids = [-2,-1,1,2]
 * Output: [-2,-1,1,2]
 * Explanation: The -2 and -1 are moving left, while the 1 and 2 are moving right. Asteroids moving the same direction never meet, so no asteroids will meet each other.
 *
 *
 * Constraints:
 *
 * 1 <= asteroids <= 104
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 */


public class AsteroidCollision {
    public static int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null)
            return null;
        else if (asteroids.length <= 1)
            return asteroids;

        int[] result;
        Deque<Integer> stack = new ArrayDeque<>();

        for(int asteroid : asteroids) {
            if(asteroid > 0)                // positive asteroid : push to stack
                stack.push(asteroid);
            else {                          // negative asteroid
                // if bigger than positive asteroid present in the stack, pop because it will destroyed
                while(!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -asteroid)
                    stack.pop();

                // if the remaining asteroid are in negative direction now, push it to stack
                if(stack.isEmpty() || stack.peek() < 0)
                    stack.push(asteroid);
                else if (stack.peek() == -asteroid) // same size but opposite direction, pop and no push is needed as both gets destroyed
                    stack.pop();
            }
        }

        result = new int[stack.size()];
        int i = stack.size() - 1;
        for(int item : stack){
            result[i] = item;
            i--;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{5, 10, -5};
        int[] arr2 = new int[]{8, -8};
        int[] arr3 = new int[]{10, 2, -5};
        int[] arr4 = new int[]{-2, -1, 1, 2};

        int[] result1 = asteroidCollision(arr1);
        int[] result2 = asteroidCollision(arr2);
        int[] result3 = asteroidCollision(arr3);
        int[] result4 = asteroidCollision(arr4);

        for (int i : result1)
            System.out.print(i + " ");
        System.out.println();

        for (int i : result2)
            System.out.print(i + " ");
        System.out.println();

        for (int i : result3)
            System.out.print(i + " ");
        System.out.println();

        for (int i : result4)
            System.out.print(i + " ");
        System.out.println();
    }

}
