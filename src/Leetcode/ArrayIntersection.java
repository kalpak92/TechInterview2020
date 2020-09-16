package Leetcode;

import java.util.*;

/**
 * @author kalpak
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 *
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 *
 * Note:
 *
 *     Each element in the result must be unique.
 *     The result can be in any order.
 */

/**
 * INTERSECTION II
 *
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 *
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 *
 * Note:
 *     Each element in the result should appear as many times as it shows in both arrays.
 *     The result can be in any order.
 *
 * Follow up:
 *
 *     What if the given array is already sorted? How would you optimize your algorithm?
 *     What if nums1's size is small compared to nums2's size? Which algorithm is better?
 *     What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 *
 */

public class ArrayIntersection {
    public static int[] intersection1(int[] nums1, int[] nums2) {
        Set<Integer> uniqueElements = new HashSet<>();
        List<Integer> common = new ArrayList<>();

        for(int i : nums1) {
            uniqueElements.add(i);
        }

        for(int i : nums2) {
            if(uniqueElements.contains(i)) {
                if(!common.contains(i))
                    common.add(i);
            }
        }

        int[] result = new int[common.size()];
        int idx = 0;
        for(int i : common) {
            result[idx++] = i;
        }
        return result;
    }

    public static int[] intersection2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();

        // create a hashamp with the frequency of each number
        // use getOrDefault for brevity
        for (int num : nums1)
            map.put(num, map.getOrDefault(num, 0) + 1);

        // find the intersection from the other array by comparing it with the frequency of the number in the hashmap.
        // if a match is found, decrease the count in the hashmap
        // if the count becomes 0, remove that element.

        ArrayList<Integer> common = new ArrayList<>();

        for (int num : nums2)
        {
            if(map.containsKey(num))
            {
                common.add(num); // add the element to the result since it is present in the other set
                map.put(num, map.get(num) - 1); // decrement the frequency of the occuring number in the hashmap

                // remove(Object key, Object value)
                // Removes the entry for the specified key only if it is currently mapped to the specified value.
                map.remove(num, 0);
            }
        }

        // convert the result to the asked datatype
        int[] result = new int[common.size()];
        int idx = 0;
        for(int i : common)
            result[idx++] = i;

        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,2,1};
        int[] arr2 = {2,2};

        int[] result = intersection1(arr1, arr2);
        for(int i : result)
            System.out.print(i + " ");
        System.out.println();

        result = intersection2(arr1, arr2);
        for(int i : result)
            System.out.print(i + " ");
        System.out.println();
    }
}
