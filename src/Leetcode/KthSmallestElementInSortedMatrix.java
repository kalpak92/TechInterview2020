package Leetcode;

import java.util.PriorityQueue;

/**
 * @author kalpak
 *
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * Example:
 *
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 *
 * return 13.
 *
 */

class Node {
    int row;
    int col;
    int val;
    Node(int row, int col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }
}

public class KthSmallestElementInSortedMatrix {
    public static int kthSmallest(int[][] matrix, int k) {
        // Base Condition
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return -1;

        int result = 0;
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the elements of the first row
        for(int i = 0; i < matrix[0].length; i++)
            minHeap.offer(new Node(0, i, matrix[0][i]));

        // Now pop elements from the heap and add the next element from its same column and next row
        for(int i = 0; i < k; i++) {
            Node temp = minHeap.poll();
            result = temp.val;

            if(temp.row == matrix.length - 1)
                continue;

            minHeap.offer(new Node(temp.row + 1, temp.col, matrix[temp.row + 1][temp.col]));
        }
        return result;
    }

    public static int kthSmallestUsingBinarySearch(int[][] matrix, int k) {
        // corner case
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return -1;

        // Here Binary Search will be on the elements itself, contrary to the indexes.
        int left = matrix[0][0];
        int right = matrix[matrix.length - 1][matrix[0].length - 1];



        while(left <= right) {
            int mid = left + (right - left)/2;

            // Count number of elements <= mid
            int count = 0;
            int row = 0;
            int col = matrix[0].length - 1;
            int result = left; // lowest possible value

            while(row < matrix.length) {
                while(col >= 0 && matrix[row][col] > mid)
                    col--;

                if(col >= 0) {
                    count += (col + 1);
                    result = Math.max(result, matrix[row][col]);
                }
                row++;
            }

            if(count == k)
                return result;
            else if (count < k)
                left = mid + 1;
            else
                right = mid - 1;
        }

        // Why we return left at the end?
        // Here left = right + 1.
        // For right, we found < k elems, for left, we found >=k elem, left must have duplicates in matrix, return left
        return left;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        System.out.println(kthSmallest(matrix, 8));
        System.out.println(kthSmallestUsingBinarySearch(matrix, 8));
    }

}
