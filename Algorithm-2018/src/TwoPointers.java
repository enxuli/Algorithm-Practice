import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoPointers {

	//11. Container With Most Water  too freaking amazing@@
    public int maxArea(int[] height) {
        int left = 0, right = height.length-1;
        int max = Integer.MIN_VALUE;
        while(left<right){
            
            int leftMax = height[left];
            int rightMax = height[right];
            
            max = Math.max(max, (right - left) * (leftMax>rightMax? rightMax: leftMax));
            
            if(leftMax < rightMax){
                while(left<right&&leftMax>=height[++left]);
            }else{
                while(left<right&&rightMax>=height[--right]);
            }
        }
        return max;
    }
    //26. Remove Duplicates from Sorted Array
    public int removeDuplicates(int[] nums) {
        int pos = 1;
        for(int i = 1; i < nums.length ; i++){
            if(nums[i]!=nums[i-1]) nums[pos++]= nums[i];
        }
        return pos;
    }
    //27. Remove Element
    public int removeElement(int[] nums, int val) {
        int pos = 0;
        for(int i = 0; i < nums.length ; i++){
            if(nums[i]!=val) nums[pos++]= nums[i];
        }
        return pos;
    }
    //28. Implement strStr()
    public int strStr(String haystack, String needle) {
        int length = needle.length();
        for(int i = 0 ; i<= haystack.length()-length; i++){
            if(haystack.substring(i,i+length).equals(needle))
                return i;
        }
        return -1;
    }
    
    //42. Trapping Rain Water
    public int trap(int[] height) {
        int left = 0, right = height.length-1;
        int leftMax = 0, rightMax = 0;
        int ans = 0;
        while(left < right){
            leftMax = Math.max(leftMax,height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if(leftMax < rightMax){
                ans+=leftMax-height[left];
                left++;
            }else{
                ans+=rightMax-height[right];
                right--;
            }
                
        }
        return ans;
    }
    
    //88. Merge Sorted Array you can just turn it around to avoid space(m+n)!!!!
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = new int[n+m];
        int i = 0, j = 0 ;
        int idx = 0;
        while(i<m||j<n){
            if(i<m&&j<n){
                if(nums1[i]<nums2[j]) {tmp[idx++]=nums1[i++];}
                else{tmp[idx++]=nums2[j++];}
            }else if(i<m) tmp[idx++]=nums1[i++];
            else if(j<n) tmp[idx++]=nums2[j++];
        }
        for(int k = 0 ; k < m + n; k++ ){
            nums1[k] = tmp[k];
        }
    }
    
    //167. Two Sum II - Input array is sorted
    public int[] twoSum(int[] nums, int target) {
        int i = 0, j = nums.length-1;
        while(i < j){
            if(nums[i]+nums[j]<target) i++;
            else if(nums[i]+nums[j]>target) j--;
            else break;
        }

        return new int[] {i+1,j+1};
    }
    
    //283. Move Zeroes
    // to find the non zero fisrt and swip
    public void moveZeroes(int[] nums) {
        for(int lastNonZero = 0, cur = 0 ; cur < nums.length; cur++){
            if(nums[cur] != 0){
                    int tmp = nums[cur];
                    nums[cur] = nums[lastNonZero];
                    nums[lastNonZero++] = tmp;
            }
        }
    }
    
    //349. Intersection of Two Arrays
    // sort to use two pointers! On(logn)
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> ans = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        while(i<nums1.length&&j<nums2.length){
            if(nums1[i]==nums2[j]){ans.add(nums1[i]);i++;j++;}
            else if(nums1[i]>nums2[j])j++;
            else i++;
        }
        int[] res = new int[ans.size()];
        int idx = 0;
        for(int x : ans) res[idx++] = x;
        return res;
    }
    //using two set can realize O(n)
    
    
}
