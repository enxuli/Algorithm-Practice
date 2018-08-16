
public class DP {
	
	//53. Maximum Subarray
	//152. Maximum Product Subarray
    public int maxProduct(int[] nums) {
        //brute force O(n^2)
        if(nums.length ==1 ) return nums[0];
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length ; i ++){
            int tmp = nums[i];
            for(int j = i; j < nums.length ; j ++ ){
                tmp= j==i?nums[j]:tmp*nums[j];
                max = Math.max(max,tmp);
            }
        }
        return max;
    }
    public int maxProduct2(int[] nums) {
        // actually what we need to consider is whether for now the nums[i] is biger then 0!
        // they are all integers so that the only way to affect the maximum is the negative or positive!
        // the max and min would be swaping if current number is nagetive!
        
        int result = nums[0];
        for(int i = 1, localMax = nums[0], localMin = nums[0]; i < nums.length; i ++){
            if(nums[i]<0){
                int tmp = localMax;
                localMax = localMin;
                localMin = tmp;
            }
            localMax = Math.max(nums[i],localMax*nums[i]);
            localMin = Math.min(nums[i],localMin*nums[i]);
            
            result = Math.max(result,localMax);
        }
        return result;
    }
    
}
