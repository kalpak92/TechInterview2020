package Leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author kalpak
 *
 * Design a Leaderboard class, which has 3 functions:
 *
 * addScore(playerId, score): Update the leaderboard by adding score to the given player's score.
 *                            If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
 * top(K): Return the score sum of the top K players.
 * reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard).
 *                  It is guaranteed that the player was added to the leaderboard before calling this function.
 *
 * Initially, the leaderboard is empty.
 */

public class LeaderBoard {
    Map<Integer, Integer> leaderBoard;
    public LeaderBoard() {
        leaderBoard = new HashMap<>();
    }

    public void addScore(int playerId, int score) {
        leaderBoard.put(playerId, leaderBoard.getOrDefault(playerId, 0) + score);
    }

    public int top(int K) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int scoreSum = 0;

        for(int score : leaderBoard.values()) {
            minHeap.offer(score);

            if(minHeap.size() > K)
                minHeap.poll();
        }

        while(!minHeap.isEmpty())
            scoreSum += minHeap.poll();

        return scoreSum;
    }

    public void reset(int playerId) {
        leaderBoard.remove(playerId);
    }
}
