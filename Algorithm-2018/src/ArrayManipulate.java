import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ArrayManipulate {
	
	//15. 3Sum you wanna learn how to use a HashSet as a list and a array as a list!!!!!
	//combine with two point method!
    public List<List<Integer>> threeSum(int[] nums) {
        HashSet<List<Integer>> ans = new HashSet<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-2; i++){
                int j = i+1; 
                int k = nums.length -1;
                while(j<k){
                    int sum = nums[i]+nums[j]+nums[k];
                    if(sum<0) j++;
                    else if(sum>0) k--;
                    else ans.add(Arrays.asList(nums[i],nums[j++],nums[k--]));
                }
            }
        return new ArrayList<>(ans);
    }
    //16. 3Sum Closest //always trying to implement two pointers!!
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = nums[0]+nums[1]+nums[2];
        int result = sum;
        for(int i = 0; i < nums.length-2; i++){
            int left = i+1;
            int right = nums.length-1;
            while(left<right){
                sum =nums[i]+nums[left]+nums[right];
                if(Math.abs(result-target) > Math.abs(sum-target)) {result = sum;}
                if(sum>target) right--;
                else if(sum<target) left++;
                else break;
                }
        }
        return result;
    }
    
    
    
	// 18. four sum
    public List<List<Integer>> fourSum(int[] nums, int target) {
        HashSet<List<Integer>> ans = new HashSet<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-3; i++){
            for(int j = i+1; j<nums.length-2; j++ ){
                int k = j+1; 
                int l = nums.length -1;
                while(k<l){
                    int sum = nums[i]+nums[j]+nums[k]+nums[l];
                    if(sum<target) k++;
                    else if(sum>target) l--;
                    else ans.add(Arrays.asList(nums[i],nums[j],nums[k++],nums[l--]));
                }
            }
        }
        return new ArrayList<>(ans);
    }
    //41. First Missing Positive

    public int firstMissingPositive(int[] A) {
        int i = 0;
        // swaping every positive to its idx position
        // then travel and get the first idx != value;
        while(i < A.length){
            if(A[i] == i+1 || A[i] <= 0 || A[i] > A.length) i++;
            else if(A[A[i]-1] != A[i]) swap(A, i, A[i]-1);
            else i++;
        }
        i = 0;
        while(i < A.length && A[i] == i+1 ) i++;
        return i+1;
    }
    
    private void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
	//66. Plus One  
	//Remember !! never convert back to int if it's not orinally int!!!
    public int[] plusOne(int[] digits) {
        int c = 0, tmp = 0;
        int length = digits.length-1;
        
        for(int i = digits.length-1 ; i >=0; i--){
            tmp = digits[i];
            digits[i] = (tmp + c + (i==length? 1 : 0)) % 10;
            c = (tmp + c + (i==length? 1 : 0))/10;
        }
        if( c == 1) {
            int[] ans = new int[digits.length+1];
            ans[0] = 1;
            System.arraycopy(digits, 0, ans, 1, digits.length);
            return ans;
                
        }
        else return digits;
    }
    
    //189. Rotate Array
    
    
    
    
    //259 3Sum smaller sum!!! always transfor to the two pointer or hashtable search!!(O(n))
    
    public int threeSumSmaller(int[] nums, int target) {
        int ans = 0;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length -2; i++ ){
            int j = i + 1;
            int k = nums.length-1;
            while(j < k){
                int sum = nums[i]+ nums[j] + nums[k];
                if(sum>=target) k--;
                else {
                    ans+= k - j;
                    j++;
                }
            }
        }
        return ans;
    }
    //334. Increasing Triplet Subsequence
    public boolean increasingTriplet(int[] nums) {
        int low = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
        for(int x : nums){
            if(x<=low){low = x;}
            else if(x<=mid){mid = x;}
            else return true;
        }
        return false;
    }
    
    //350. Intersection of Two Arrays II
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for(int i= 0; i < nums1.length ; i ++){
            map.put(nums1[i],map.getOrDefault(nums1[i],0)+1);
        }
        for(int j= 0; j < nums2.length ; j ++){
            if(map.containsKey(nums2[j])&&(map.get(nums2[j])>=1)) {
                list.add(nums2[j]);
                map.put(nums2[j],map.get(nums2[j])-1);
            }
        }
        int[] arr = new int[list.size()];
        for(int k = 0; k < list.size();k++) arr[k] = list.get(k);
        return arr;
    }
    
    //421. Maximum XOR of Two Numbers in an Array
    public int findMaximumXOR(int[] nums) {
        //brute force O(n^2)
        int max = 0;
        for(int i = 0; i < nums.length-1; i ++){
            for(int j = i+1; j< nums.length; j ++ ){
                max = Math.max(max,nums[i]^nums[j]);
            }
        }
        return max;
    }
    
    //448. Find All Numbers Disappeared in an Array
    //just like the 41, we have to put all the integer to its postion.
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int index = 0;
        while(index <nums.length){
            if(nums[nums[index]-1]!=nums[index]) swap(nums, index, nums[index]-1);
            else index++;
        }
        //System.out.println(Arrays.toString(nums));
        for(int i = 0 ; i < nums.length; i++){
            if(nums[i]!=i+1) ans.add(i+1);
        }
        return ans;
        
    }
    //476. Number Complement

    public int findComplement(int num) {
        int count =0, i= num, ans = 0;
        while(i>0){
            if(((num>>count)&1)!=1) ans+=Math.pow(2,count);
            count++;
            i/=2;
        }
        return ans;
    }
    
    //611. Valid Triangle Number
    // using a three pointers method to do compare search!!!
    public int triangleNumber(int[] nums) {
    	Arrays.sort(nums);
    	int ans = 0;
    	for(int i = nums.length - 1; i >=2; i--){
            int small = 0;
            int large = i-1;
            while(small < large){
                if(nums[small] + nums[large] <= nums[i]) small++;
                else {
                    ans+=large-small;
                    large--;
                }
            }
        }
        return ans;
        
    }
    

}
