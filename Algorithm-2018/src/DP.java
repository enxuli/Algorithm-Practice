
public class DP {
	
	//53. Maximum Subarray
	
	
	//72. Edit Distance

    public int minDistance(String word1, String word2) {
        //using dp we could find that their sould be four states which could be the next states
        //assume we are looking at the ith char in word1 and jth char in word2
        // and dp(i,j) represents the cost we need to make two words equal at those char i and j,
        // now looking from most right to most left
        // insert a char should be dp(i,j-1), i remain the same but jump over j
        //delete a char should be dp(i-1,j),  j remain the same but jump over i
        //replace a char should be (char1!=char2) dp(i-1,j-1) 
        //so thinking bottom up the next states should convert to the preivous states
        // d(i,j) = min(1+min(dp(i,j-1),dp(i-1,j)),char1==char2? dp(i-1,j-1):1+dp(i-1,j-1))
        // then let's do this bottom up!
        char[] src = word1.toCharArray();
        char[] tar = word2.toCharArray();
        int m = src.length;
        int n = tar.length;
        int[][] dp = new int[m+1][n+1];
        for(int i = 0; i <= m ; i ++) dp[i][0]=i;
        for(int j = 0; j <= n ; j ++) dp[0][j]=j;
        // always remember to set the boundery otherwise the bottom up wont work!\
        for(int i = 1; i <= m ; i ++){
            for(int j = 1 ; j <= n ; j ++){
                int delorins = 1 + Math.min(dp[i][j-1],dp[i-1][j]);
                int reporskip = (src[i-1] == tar[j-1])? dp[i-1][j-1] : 1 + dp[i-1][j-1];
                dp[i][j] = Math.min(delorins,reporskip);
            }
        }
        return dp[m][n];
    }
	
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
    //673. Number of Longest Increasing Subsequence

    public int findNumberOfLIS(int[] nums) {
        // the main idea is to store the current length from 0 to i, and its count
        // every time meet a same smaller length , count + number of that length with that end.
        int n = nums.length;
        if(n == 0) return n;
        int[] len = new int[n];
        int[] count = new int[n];
        int maxLen = Integer.MIN_VALUE, res = 0;
        for(int i = 0; i < n; i ++){
            len[i] = count[i] = 1;
            for(int j = 0; j < i; j ++){
                if(nums[j] < nums[i]){
                    if(len[i] == len[j] + 1) count[i] += count[j];//meet this length for after the fisrt time!
                    if(len[i] < len[j] + 1){//meet this length for the first time!
                        len[i] = len[j] + 1;
                        count[i] = count[j];
                    }
                }
            }
            if(len[i] == maxLen) res+=count[i];
            else if(len[i] > maxLen){
                maxLen = len[i];
                res = count[i];
            }
        }
        return res;
    }
    
}
