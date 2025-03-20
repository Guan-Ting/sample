package com.java.sample.Algo;

/**
 *  ClassName: testRemoveElement
 *  Package: com.java.sample.Algo
 *  Description:
 *  
*/
public class TestRemoveElement {
    public int removeElement(int[] nums, int val) {
        // 快慢指针
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;
    }

    public static void main(String[] args) {
        TestRemoveElement testRemoveElement = new TestRemoveElement();

        int[] nums = {1,3,2,7,8,3,4,5};
        int n = testRemoveElement.removeElement(nums,3);
        System.out.println("n:"+n);
    }
}
