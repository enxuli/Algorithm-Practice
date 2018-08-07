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
    //125. Valid Palindrome char tech to use : Character.isLetterOrDigit!!!!! Character.toLowerCase

    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while(i <= j) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++; continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--; continue;
            }
            char a = Character.toLowerCase(s.charAt(i));
            char b = Character.toLowerCase(s.charAt(j));
            if (a != b) return false;
            i++; j--;
        }
        return true;
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
    
    //287. Find the Duplicate Number
    //exactly like in the linkedlist using fast and slow! to find the start of cycle!!!
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int slow = n;
        int fast = n;
        do{
            slow = nums[slow-1];
            fast = nums[nums[fast-1]-1];
        }while(slow != fast);
        slow = n;
        while(slow!=fast){
            slow = nums[slow-1];
            fast = nums[fast-1];
        }
        return slow;
    }
    
    //345. Reverse Vowels of a String
    Set<Character> vowel = new HashSet<>(Arrays.asList('a','e','o','i','u','A','E','O','I','U'));
    public String reverseVowels(String s) {
        char[] ans = s.toCharArray();
        int i = 0, j = s.length()-1;
        while(i < j){
            if(vowel.contains(ans[i])&&vowel.contains(ans[j])){
                char tmp = ans[i];
                ans[i++] = ans[j];
                ans[j--] = tmp;
            }else if(vowel.contains(ans[i]))j--;
            else i++;
        }
        return String.valueOf(ans);
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
    //or you use two set can realize O(n)
    
    
    
    //two pointer vs . two set!
    public int findPairs(int[] nums, int k) {
 	
        if(nums.length < 2){
            return 0;
        }
        Arrays.sort(nums);
        int i = 0;
        int j = 0;
        int result = 0;
        while(j < nums.length){
            j = Math.max(i+1,j);
            while(j < nums.length && nums[j] - nums[i] < k){
                j++;
            }
            if(j < nums.length && nums[j] - nums[i] == k){
                result++;
            }
            i++;
            while(i< nums.length && nums[i] == nums[i-1]){
                i++;
            }
        }
        return result;
    }
    public int findPairs2(int[] nums, int k) {
        if(k<0) return 0;
        HashSet<Integer> set1=new HashSet();
        HashSet<Integer> set2=new HashSet();
        for(int i=0;i<nums.length;i++) {
            if(set1.contains(nums[i]-k)) set2.add(nums[i]-k);
            if(set1.contains(nums[i]+k)) set2.add(nums[i]);
            set1.add(nums[i]);
        }
        
        return set2.size();
        
    }
    
    
    //680. Valid Palindrome II

    public boolean validPalindrome(String s) {
        int i = 0, j = s.length()-1;
        while(i<=j){
            if(s.charAt(i)!=s.charAt(j)){
                return validSub(s,i+1,j)||validSub(s,i,j-1);
            }else{
                i++;j--;
            }
        }
        return true;
    }
    private boolean validSub(String s,int i, int j){
        while(i<=j){
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }else{
                i++;j--;
            }
        }
        return true;
    }
    
    
    
}
