import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
    
    //75. Sort Colors
    public void sortColors(int[] nums) {
        int i = 0, j = nums.length-1;
        while(i<j){
            if(nums[i]>nums[j]){
                int tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;
            }
            if(nums[i]==0) i++;
            if(nums[j]==2) j--;
            if(nums[i]==nums[j]&&nums[i]==1){
                int cur = i;
                while(cur<nums.length-1&&nums[cur]==1){
                    cur++;
                }
                //System.out.println(cur);
                if(cur >= j) break;
                int tmp = nums[cur];
                nums[cur] = nums[i];
                nums[i] = tmp;
            }
        }
        
    }
    public void sortColors1(int[] nums) {
        int j = nums.length;
        int n1 = -1,n2 = -1,n3 = -1;
        for (int i = 0; i < j; i++){
            if(nums[i]==0){
                nums[++n3]=2;
                nums[++n2]=1;
                nums[++n1]=0;
            }else if(nums[i]==1){
                nums[++n3]=2;
                nums[++n2]=1;
            }else if(nums[i]==2){
                nums[++n3]=2;
            }
        }
        
    }
    
    
    //76. Minimum Window Substring
    // very important! this is for those substring hash + two pointers!!!
    public String minWindow(String s, String target) {
        HashMap<Character,Integer> map = new HashMap<>();
        char[] charstring = s.toCharArray();
        for(char ch : target.toCharArray()) map.put(ch,map.getOrDefault(ch,0)+1);
        int count = target.length(), end = 0, start = 0, head = 0, min = Integer.MAX_VALUE;
        while(end<s.length()){
            if(map.containsKey(charstring[end])){
                if(map.get(charstring[end])>0) count --;
                map.put(charstring[end],map.get(charstring[end])-1);
            }
            end++;
            while(count==0){
                if(end-start<min) {head = start; min=end-head;}
                if(map.containsKey(charstring[start])){
                    if(map.get(charstring[start])==0)count++;
                    map.put(charstring[start],map.get(charstring[start])+1);
                }
                start++;
            }
        }
        return min==Integer.MAX_VALUE? "":s.substring(head,head+min);
        
    }
    //https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
    //when it's only need to count the number of the element "alphabic" we should int[] to replace the hashMaP
    public String minWindow1(String s, String target) {
        int[] hash = new int[256];
        char[] charstring = s.toCharArray();
        for(char ch : target.toCharArray()) hash[ch]++;
        int count = target.length(), end = 0, start = 0, head = 0, min = Integer.MAX_VALUE;
        
        while(end<s.length()){
            if(hash[charstring[end++]]-->0) count--;
            while(count==0){
                if(end-start<min)  min=end-(head=start);
                if(hash[charstring[start++]]++==0) count++;
            }
        }
        return min==Integer.MAX_VALUE? "":s.substring(head,head+min);
        
    }
    
    //80. Remove Duplicates from Sorted Array II
    public int removeDuplicates1(int[] nums) {
        int pos = 0;
        for(int n : nums){
            if(pos<2||n>nums[pos-2])
                nums[pos++]=n;
        }
        return pos;
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
    
    //159. Longest Substring with At Most Two Distinct Characters
    // this is just for the at most type of tricks but not for at least!!!
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        char[] str = s.toCharArray();
        int[] hash = new int[256];
        int max=0;
            int unique = 0,start = 0, end = 0, alpha=2;
            Arrays.fill(hash,0);
            while(end<s.length()){
                if(unique<=alpha){
                    if(hash[str[end++]]++==0)unique++;
                }
                while(unique>alpha){
                    if(hash[str[start++]]--==1)unique--;
                }
                max = Math.max(max, end - start);
            }
        return max;
    }
    
    
    //209. Minimum Size Subarray Sum
    
    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length==0|| nums==null) return 0;
        int i = 0 , j = 0;
        int sum=0;
        int min = Integer.MAX_VALUE;
        while(j<nums.length){
            if(sum<s) {sum+=nums[j++];}
            while(sum>=s) {
                min = Math.min(min,j-i);
                sum-=nums[i++];
            }
        }
        return min==Integer.MAX_VALUE? 0:min;
    }
    
    //NlogN always would remind you of binary search!!!
    // make the sum accumulated!!!!
    //too freaking awesome!
    private int solveNLogN1(int s, int[] nums) {
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) sums[i] = sums[i - 1] + nums[i - 1];
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < sums.length; i++) {
            int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);
            if (end == sums.length) break;
            if (end - i < minLen) minLen = end - i;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    
    private int binarySearch(int lo, int hi, int key, int[] sums) {
        while (lo <= hi) {
           int mid = (lo + hi) / 2;
           if (sums[mid] >= key){
               hi = mid - 1;
           } else {
               lo = mid + 1;
           }
        }
        return lo;
    }
    
    //340. Longest Substring with At Most K Distinct Characters
    // this is to easy when you have the trick!!!
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        char[] str = s.toCharArray();
        int[] hash = new int[256];
        int max=0;
            int unique = 0,start = 0, end = 0;
            while(end<s.length()){
                if(unique<=k){
                    if(hash[str[end++]]++==0)unique++;
                }
                while(unique>k){
                    if(hash[str[start++]]--==1)unique--;
                }
                max = Math.max(max, end - start);
            }
        return max;
    }
    
    //395. Longest Substring with At Least K Repeating Characters
    // force to use hash and the two pointer! too freaking genius
    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        int[] hash = new int[256];
        int max=0;
        
        for (int alpha = 1; alpha<=26; alpha++){
            int unique = 0,start = 0, end = 0,valid = 0;
            Arrays.fill(hash,0);
            while(end<s.length()){
                if(unique<=alpha){
                    if(hash[str[end]]++==0)unique++;
                    if(hash[str[end++]]==k) valid++;
                }else{
                    if(hash[str[start]]--==k)valid--;
                    if(hash[str[start++]]==0)unique--;
                }
                if(unique == alpha && valid == unique){
                    max = Math.max(max, end - start);
                }
            }
        }
        return max;
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
    
    //763. Partition Labels
    public List<Integer> partitionLabels(String S) {
        List<Integer> ans = new ArrayList<>();
        int[] hash = new int[26];
        char[] str = S.toCharArray();
        for(int i = 0; i < S.length(); i++) hash[str[i]-'a'] = i;
        
        int start = 0, end = 0;
        for(int i = 0; i < S.length(); i++){
            end = Math.max(end,hash[str[i]-'a']);
            if(i == end){
                ans.add(end - start + 1);
                start = end +1;
            }
        }
        return ans;
    }
    
    
}
