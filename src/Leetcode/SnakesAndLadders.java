package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author kalpak
 *
 *
 */
public class SnakesAndLadders {
    public static int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0 )
            return -1;

        int len = board.length;
        boolean[] isVisited = new boolean[len * len + 1];
        Queue<Integer> queue = new LinkedList<>();
        int moves = 0;
        int result = len*len;
        // Start at 1
        queue.offer(1);

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int currentPosition = queue.poll();

                if(currentPosition == len*len)          // reached the end
                    result = Math.min(result, moves);

                for(int j = 1; j <= 6; j++) {
                    int possibleMove = currentPosition + j;
                    if(possibleMove > len*len)
                        break;

                    if(!isVisited[possibleMove]) {
                        isVisited[possibleMove] = true;

                        int row = len - (possibleMove - 1) / len - 1;
                        int col = (len - row) % 2 == 0 ? len - (possibleMove - 1) % len - 1 : (possibleMove - 1) % len;

                        if(board[row][col] == currentPosition)
                            continue;

                        if(board[row][col] == -1)
                            queue.offer(possibleMove);
                        else
                            queue.offer(board[row][col]);
                    }
                }
            }
            moves++;
        }
        return result == len*len ? -1 : result;
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},
                {-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};

        System.out.println("The minimum number of moves needs to reach the end : " + snakesAndLadders(board));
    }
}
