package com.ljheee.other;

public class Solution {
	
    public int[] twoSum(int[] nums, int target) {
        int sum[]=new int[2];
        
        for(int i = 0 ; i<nums.length ; i++){
        	int temp  = target - nums[i];
        	if(isExist(nums , temp)){
        		sum[0] = nums[i];
        		sum[1] = temp;
        	}
        }
      return sum;
    }
    
    
    public boolean isExist(int[] nums, int key){
    	boolean result = false;
    	
    	for (int i = 0; i < nums.length; i++) {
			if(key == nums[i]){
				result = true;
				break;
			}
		}
		return result;
    }
    
    public static void main(String[] args) {
		
    	Solution solution = new Solution();
    	int[] two = solution.twoSum(new int[]{2, 7, 11, 15}, 9);
    	System.out.println(two[0]);
    	System.out.println(two[1]);
	}
}
