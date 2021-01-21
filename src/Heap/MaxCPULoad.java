package Heap;

import java.util.*;

/**
 * @author kalpak
 *
 * Given an array of jobs with different time requirements, where each job consists of start time, end time and CPU load.
 * The task is to find the maximum CPU load at any time if all jobs are running on the same machine.
 *
 * Examples:
 *
 * Input: jobs[] = {{1, 4, 3}, {2, 5, 4}, {7, 9, 6}}
 * Output: 7
 * Explanation:
 * In the above-given jobs, there are two jobs which overlaps.
 * That is, Job [1, 4, 3] and [2, 5, 4] overlaps for the time period in [2, 4]
 * Hence, the maximum CPU Load at this instant will be maximum (3 + 4 = 7).
 *
 * Input: jobs[] = {{6, 7, 10}, {2, 4, 11}, {8, 12, 15}}
 * Output: 15
 * Explanation:
 * Since, There are no jobs that overlaps.
 * Maximum CPU Load will be – max(10, 11, 15) = 15
 */

class Job {
    int start;
    int end;
    int cpuLoad;

    public Job(int start, int end, int cpuLoad) {
        this.start = start;
        this.end = end;
        this.cpuLoad = cpuLoad;
    }
};

public class MaxCPULoad {
    public static int findMaxCPULoad(List<Job> jobs) {
        int result = 0;
        int currentCPULoad = 0;
        PriorityQueue<Job> minHeap = new PriorityQueue<>((a, b) -> a.end - b.end);

        Collections.sort(jobs, (a, b) -> a.start - b.start);
        for (Job job : jobs) {
            while(!minHeap.isEmpty() && job.start > minHeap.peek().end) {
                currentCPULoad -= minHeap.peek().cpuLoad;
                minHeap.poll();
            }
            currentCPULoad += job.cpuLoad;
            result = Math.max(result, currentCPULoad);
            minHeap.offer(job);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Job> input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 3), new Job(2, 5, 4), new Job(7, 9, 6)));
        System.out.println(findMaxCPULoad(input));

        input = new ArrayList<Job>(Arrays.asList(new Job(6, 7, 10), new Job(2, 4, 11), new Job(8, 12, 15)));
        System.out.println(findMaxCPULoad(input));

        input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 2), new Job(2, 4, 1), new Job(3, 6, 5)));
        System.out.println(findMaxCPULoad(input));
    }
}
