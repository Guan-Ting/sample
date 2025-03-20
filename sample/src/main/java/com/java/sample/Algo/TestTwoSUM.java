package com.java.sample.Algo;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


/**
 * ClassName: TestTwoSUM
 * Package: com.java.sample.Algo
 * Description:
 *
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 *
 *
 */
public class TestTwoSUM {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(); // 用來存儲數字及其索引
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // 計算理想配對值
            if (map.containsKey(complement)) { // 如果配對值已在 HashMap 中
                return new int[] {map.get(complement), i}; // 返回配對索引
            }
            map.put(nums[i], i); // 將當前數字與索引存入 HashMap
        }
        throw new IllegalArgumentException("No two sum solution");

    }

    @Test
    void testTwoSumWithMultiplePairs() {
        TestTwoSUM testTwoSUM = new TestTwoSUM();

        int[] nums = {15, 11, 7, 2};
        int target = 9;
        int[] expected = {2, 3}; // Pair is nums[2]=4 and nums[4]=6.

        int[] result = testTwoSUM.twoSum(nums, target);

        assertArrayEquals(expected, result, "Should return indices [0, 1]");
    }


}
