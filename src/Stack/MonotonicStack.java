package Stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MonotonicStack {
    private int[] nums;
    private int[] firstSmallerToLeft;
    private int[] firstSmallerToRight;
    private int[] firstLargerToLeft;
    private int[] firstLargerToRight;

    public MonotonicStack(int nums[]) {
        this.nums = nums;
        this.firstSmallerToLeft = new int[nums.length];
        this.firstSmallerToRight = new int[nums.length];
        this.firstLargerToLeft = new int[nums.length];
        this.firstLargerToRight = new int[nums.length];

        Arrays.fill(firstSmallerToLeft, -1);
        Arrays.fill(firstSmallerToRight, -1);
        Arrays.fill(firstLargerToLeft, -1);
        Arrays.fill(firstLargerToRight, -1);

        buildIncreasingStack();
        buildDecreasingStack();
    }

    private void buildIncreasingStack() {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && (nums[stack.peek()] >= nums[i])) {
                firstSmallerToRight[stack.poll()] = nums[i];
            }
            if(!stack.isEmpty())
                firstSmallerToLeft[i] = nums[stack.peek()];

            stack.push(i);
        }
    }

    private void buildDecreasingStack() {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && (nums[stack.peek()] <= nums[i])) {
                firstLargerToRight[stack.poll()] = nums[i];
            }
            if(!stack.isEmpty())
                firstLargerToLeft[i] = nums[stack.peek()];

            stack.push(i);
        }
    }

    public int[] getFirstSmallerToLeft() {
        return this.firstSmallerToLeft;
    }

    public int[] getFirstSmallerToRight() {
        return this.firstSmallerToRight;
    }

    public int[] getFirstLargerToLeft() {
        return this.firstLargerToLeft;
    }

    public int[] getFirstLargerToRight() {
        return this.firstLargerToRight;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{8, 6, 4, 5, 7};
        MonotonicStack obj = new MonotonicStack(nums);

        System.out.println("First Smaller to Left : " + Arrays.toString(obj.getFirstSmallerToLeft()));
        System.out.println("First Smaller to Right : " + Arrays.toString(obj.getFirstSmallerToRight()));
        System.out.println("First Larger to Left : " + Arrays.toString(obj.getFirstLargerToLeft()));
        System.out.println("First Larger to Right : " + Arrays.toString(obj.getFirstLargerToRight()));
    }
}
