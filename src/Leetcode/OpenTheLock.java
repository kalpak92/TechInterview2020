package Leetcode;

import java.util.*;
/**
 * @author kalpak
 *
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
 * The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
 *
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 *
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
 * the wheels of the lock will stop turning and you will be unable to open it.
 *
 * Given a target representing the value of the wheels that will unlock the lock,
 * return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 *
 * Example 1:
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 *
 * Example 2:
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation:
 * We can turn the last wheel in reverse to move from "0000" -> "0009".
 *
 * Example 3:
 * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * Output: -1
 * Explanation:
 * We can't reach the target without getting stuck.
 *
 * Example 4:
 * Input: deadends = ["0000"], target = "8888"
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= deadends.length <= 500
 * deadends[i].length == 4
 * target.length == 4
 * target will not be in the list deadends.
 * target and deadends[i] consist of digits only.
 */

public class OpenTheLock {
    public static int openLock(String[] deadends, String target) {
        Set<String> deadEnds = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer("0000");
        visited.add("0000");

        int level = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();

            while(size-- > 0) {
                String currentLockPosition = queue.poll();

                // check if destination has been reached
                if(currentLockPosition.equals(target))
                    return level;

                if(deadEnds.contains(currentLockPosition))      // screwed. Move on
                    continue;

                // Need to make our moves
                StringBuilder sb = new StringBuilder(currentLockPosition);

                for(int i = 0; i < 4; i++) {
                    char currentPosition = sb.charAt(i);
                    // incremented string
                    String s1 = sb.substring(0, i) + (currentPosition == '9' ? 0 :  currentPosition - '0' + 1) + sb.substring(i+1);
                    // decremented string
                    String s2 = sb.substring(0, i) + (currentPosition == '0' ? 9 :  currentPosition - '0' - 1) + sb.substring(i+1);

                    // check if the moves have been visited or not
                    if(!visited.contains(s1) && !deadEnds.contains(s1)) {
                        visited.add(s1);
                        queue.offer(s1);
                    }

                    if(!visited.contains(s2) && !deadEnds.contains(s2)) {
                        visited.add(s2);
                        queue.offer(s2);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] deadends = new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"};
        System.out.println("The number of moves to open the lock is : " + openLock(deadends,  "8888"));

        deadends = new String[]{"0201","0101","0102","1212","2002"};
        System.out.println("The number of moves to open the lock is : " + openLock(deadends,  "0202"));
    }
}
