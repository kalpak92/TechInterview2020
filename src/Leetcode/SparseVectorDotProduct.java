package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kalpak
 *
 * Given two sparse vectors, compute their dot product.
 *
 * Implement class SparseVector:
 *
 * SparseVector(nums) Initializes the object with the vector nums
 * dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 * A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.
 *
 * Follow up: What if only one of the vectors is sparse?
 *
 * Example 1:
 *
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * Output: 8
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 * Example 2:
 *
 * Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 * Output: 0
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 * Example 3:
 *
 * Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
 * Output: 6
 *
 *
 * Constraints:
 *
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= 100
 */

public class SparseVectorDotProduct {
    Map<Integer, Integer> map;  // stores the Index and the corresponding element (non-zero)

    SparseVectorDotProduct(int[] nums) {
        map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0)
                map.put(i, nums[i]);
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVectorDotProduct vec) {
        int result = 0;

        for(int key : this.map.keySet()) {
            if(vec.map.containsKey(key)) {
                result += this.map.get(key) * vec.map.get(key);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SparseVectorDotProduct v1 = new SparseVectorDotProduct(new int[]{1,0,0,2,3});
        SparseVectorDotProduct v2 = new SparseVectorDotProduct(new int[]{0,3,0,4,0});

        System.out.println("The Dot Product is : " + v1.dotProduct(v2));

    }
}
