package com.java.sample.Algo;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * ClassName: TestTwoSum2
 * Package: com.java.sample.Algo
 * Description:
 *
 * @Author: Howard
 * @Create: 2025/3/11
 */
public class TestTwoSum2 {

    public int[] twoSum(int[] nums,int target)  {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i =0; i < nums.length; i++){
           int complement=  target - nums[i];
           if (map.containsKey(complement)){
               return new int[]{map.get(complement),i};
           } else {
               map.put(nums[i],i);
           }
        }

        throw new IllegalArgumentException("no solution");

    }


    @Test
    void testTwoSumWithMultiplePairs() {
        TestTwoSum2 testTwoSUM2 = new TestTwoSum2();

        int[] nums = {15, 11, 7, 2};
        int target = 9;
        int[] expected = {2, 3}; // Pair is nums[2]=4 and nums[4]=6.

        int[] result = testTwoSUM2.twoSum(nums, target);

        assertArrayEquals(expected, result, "Should return indices [0, 1]");
    }

    /*
        intput:int[11,15,7,2] , target =9
        output:int[2,3]
     */




}
