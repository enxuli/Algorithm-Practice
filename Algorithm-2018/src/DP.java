import java.util.ArrayList;
import java.util.List;

public class DP {
	//5. Longest Palindromic Substring
    public String longestPalindrome(String s) {
        // the idea is to see whether a substring [i,j] is palindromic!
        // then going from rigth to left if dp[i][j] is and i-1,j+1 are same char then dp[i-1][j+1] is too
        char[] str = s.toCharArray();
        int n = str.length;
        String ans = "";
        boolean[][] dp = new boolean[n][n];
        for(int i = n - 1 ; i >= 0; i--){
            for(int j = i ; j < n ; j++){
                dp[i][j] = (str[i] == str[j]) && (j - i + 1<=3 || dp[i+1][j-1]);
                if(dp[i][j]&& j-i+1 >= ans.length()){
                    ans = s.substring(i,j + 1);
                }
            }
        }
        return ans;
        
    }
	
	//32. Longest Valid Parentheses
    public int longestValidParentheses(String s) {
        // we can assume that dp[i] is the valid length end with index i 
        // what we need to solve is that we need to know whether there is also a consecutive previous length
        // also use a max to record the max dp.
        char[] str = s.toCharArray();
        int[] dp = new int[str.length];
        int max = 0, countleft = 0;
        for(int i = 0; i < str.length; i++){
            if(str[i] == '('){
                countleft++;
            }else{
                if(--countleft>=0){
                    dp[i] = dp[i-1]+2;
                    dp[i] += i-dp[i]>=0? dp[i-dp[i]]:0;
                    max = Math.max(dp[i],max);
                }
                else countleft=0;
            }
        }
        return max;
    }
	
	//53. Maximum Subarray
	
	
	//62. Unique Paths
    public int uniquePaths(int m, int n) {
        //dp with time O(mn) space O(mn)
        int[][] dp = new int[m][n];
        for(int i = 0 ; i < m ; i ++) dp[i][0] = 1;
        for(int j = 0 ; j < n ; j ++) dp[0][j] = 1;
        for(int i = 1; i < m ; i ++){
            for(int j = 1; j < n ; j++){
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
    //64. Minimum Path Sum
    
	
	//72. Edit Distance
    public int minDistance(String word1, String word2) {
        //using dp we could find that their should be four states which could be the next states
        //assume we are looking at the i+1th char in word1 and j+1th char in word2
        // and dp(i,j) represents the cost we need to make two words equal at remain length i+1 and j+1,
        // now looking from most right to most left
        // insert a char should be dp(i,j-1), i remain the same but jump over j
        //delete a char should be dp(i-1,j),  j remain the same but jump over i
        //replace a char should be (char1!=char2) dp(i-1,j-1) 
        //so thinking bottom up the next states should convert to the previous states
        // d(i,j) = min(1+min(dp(i,j-1),dp(i-1,j)),char1==char2? dp(i-1,j-1):1+dp(i-1,j-1))
        // then let's do this bottom up!
        char[] src = word1.toCharArray();
        char[] tar = word2.toCharArray();
        int m = src.length;
        int n = tar.length;
        int[][] dp = new int[m+1][n+1];//here is quite tricky! index on dp represents the length!!!!!!!! 
        for(int i = 0; i <= m ; i ++) dp[i][0]=i;//while any one's length reduce to 0, the remain operation is the other's length!!
        for(int j = 0; j <= n ; j ++) dp[0][j]=j;
        // always remember to set the boundary otherwise the bottom up wont work!\
        for(int i = 1; i <= m ; i ++){
            for(int j = 1 ; j <= n ; j ++){
                int delorins = 1 + Math.min(dp[i][j-1],dp[i-1][j]);
                int reporskip = (src[i-1] == tar[j-1])? dp[i-1][j-1] : 1 + dp[i-1][j-1]; // that's why here is (src[i-1] == tar[j-1])
                dp[i][j] = Math.min(delorins,reporskip);
            }
        }
        return dp[m][n];
    }
    
    //91. Decode Ways

    public int numDecodings(String s) {
        //intuition: the answer only require the number of the decode method
        //we need to find all the partitions of the String
        // assume dp[i] is the total number partitions when length is i;
        // then dp[i] is previous number + substring(i-1,i)<= 26 ? 1:0;
        // but here is the tricky part , we can not distinguish if it's not valid 0 from left to right
        // so we have to do it from right to left
        int n = s.length();
        int[] dp = new int[s.length()+1];
        dp[n] = 1;
        dp[n-1] = s.charAt(n-1)=='0'?0:1;
        for(int i = n-2; i >=0; i--){
            if(s.charAt(i)=='0')continue;
            dp[i] = (Integer.valueOf(s.substring(i,i+2))<=26)? dp[i+1]+dp[i+2] : dp[i+1];
        }
        return dp[0];   
    }
	
    //96. Unique Binary Search Trees
    public int numTrees(int n) {
        //using dp[] to represent the number of node you select to build the BST
        //NOTE: always firstly come up with the total number and total length to be the index of the dp[]
        //but for the index of the orginal data, take it as count or non count!
        //then we find that dp[n] = f(1,n)+f(2,n)+...+f(i,n) where i is the root number and n is total number of treenodes
        //for singel f(i,n) which can be divide into two parts : dp[0=<total number < i]*dp[n>=total number >i]
        // so that dp[n] = dp[1-0]*dp[n-1]+ dp[2-0]*dp[n-2]+...+dp[n-0]*dp[n-n]
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int node = 2; node<= n; node ++){
            for(int root = 1; root <= node; root ++){
                dp[node] += dp[root-1]*dp[node - root];
            }
        }
        return dp[n];
    }
    //115. Distinct Subsequences
    public int numDistinct(String s, String t) {
        char[] src = s.toCharArray();
        char[] tar = t.toCharArray();
        int m = tar.length;
        int n = src.length;
        int[][] dp = new int[m+1][n+1];
        
        for(int j = 0; j <= n; j++) dp[0][j] = 1;
        
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                dp[i][j] = src[j-1]==tar[i-1]? (dp[i][j-1]+dp[i-1][j-1]):dp[i][j-1];
            }
            
        }
        return dp[m][n];
    }
    
    //120. Triangle
    // intuitive using dfs and memoiztion dp! but with O(n^2)
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() == 0) return 0;
        return dfs(triangle,new List[triangle.size()],0,0);

    }
    private int dfs(List<List<Integer>> triangle, List<Integer>[] dp, int level, int idx){
        if(level== triangle.size() || idx == triangle.get(level).size()) return 0;
        if(dp[level] != null && dp[level].size() > idx) return dp[level].get(idx);
        int result = triangle.get(level).get(idx); 
        
        int left = dfs(triangle,dp,level+1,idx);
        int right = dfs(triangle,dp,level+1,idx+1);
        
        if(idx == 0) dp[level] = new ArrayList<>();
        dp[level].add( result + Math.min(left,right));
        
        return result + Math.min(left,right);
    }
    // now we can find that in every layer, the result only  depend on the nodes in the same layer
    // and the next layer so that we can just rewrite the it every time we go bottom to up
    // remember as always! the DP is bottom up thinking! reverse it !
    public int minimumTotal2(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()+1];
        for(int i=triangle.size()-1;i>=0;i--){
            for(int j=0;j<triangle.get(i).size();j++){
                dp[j] = Math.min(dp[j],dp[j+1])+triangle.get(i).get(j); // when we are at bottom. all dp is 0
            }
        }
        return dp[0];
    }
    //123. Best Time to Buy and Sell Stock III
    public int maxProfit(int[] prices) {
        // in this quiz we come into a situation as (312) about how much is the range we need to compute!
        // here we can assume dp[k][i] is the profile we get if we have k th transaction or not at day i
        // remember we have to reverse the thought!
        // that is dp[k][i] = max(dp[k][i-1] (if dont, same with i-1), prices[i] - prices[j](buy at j sell at i) + dp[k-1][j-1](previous profile)); where j can be from 0 to i
        //note that the tricky part here is that dp[k-1][j-1] could also be same with dp[k-1][j-2]!!!
        // we only need to find the min(prices[j]-dp[k-1][j-1]) from 0 to i is just the current minimum 
        // if we use a min to record and compare it,min = min(min,prices[i]-dp[k-1][i-1]) would do the same thing!
        
        int m = 2;
        int n = prices.length;
        if(n == 0) return 0;
        int[][] dp = new int[m+1][n];
        int max = 0;
        for(int k = 1; k <= m ; k++){
            int min = prices[0]; 
            for(int i = 1; i < n ; i++){
                min = Math.min(prices[i] - dp[k-1][i-1],min);
                dp[k][i] = Math.max(dp[k][i-1], prices[i]-min);
                // it's not nessarily transact k times, we just want to find among those k which i makes it a max
                // so we would find that in this situation i wont affect the solution anymore!!!
                // only k would! so that we can decrease the space and overwrite k again and again just like 120. Triangle

            }
        }
        return dp[m][n-1];
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
    
    //279. Perfect Squares

    public int numSquares(int n) {
        
        //inoptimized dp n^2
        //vs optimized dp worst n^(3/2)
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for(int i = 1 ; i <= n ; i ++){
            dp[i] = Integer.MAX_VALUE;
            for(int divide = 1; divide*divide<= i; divide ++){
                dp[i] = Math.min(dp[i - divide*divide]+1,dp[i]);
            }
        }
        return dp[n];
    }
    
    //312. Burst Balloons

    public int maxCoins(int[] nums) {
        //intuition: assume that we may burst n balloon and get money dp[n]
        int[] num = new int[nums.length + 2];
        int n = 1;
        for (int x : nums) if (x > 0) num[n++] = x; //clear all the 0 balloons!
        num[0] = num[n] = 1; //set the boundary
        
        /*Then another interesting idea come up. Which is quite often seen in dp problem analysis. 
        That is reverse thinking. Like I said the coins you get for a balloon does not depend on the balloons already burst. 
        Therefore instead of divide the problem by the first balloon to burst, 
        we divide the problem by the last balloon to burst. In many cases, we just think of the last to count or not count!!!*/
        //Still, to divide the balloons into two parts we need all the start and end index of balloons to store in the dp[start][end]
        //then we try to compete all the combination of them by whether divided by the last balloon
        // dp[start][end] = Math.max(dp[start][end],num[start]*num[i]*num[end]+dp[start][i]+dp[i][end])
        // the holy tricky part is here 
        // because we are selecting the last the balloon to burst, so it always needs to multiple the current boundary
        // thats why num[start]*num[i]*num[end]
        
        int[][] dp = new int[n+1][n+1];
        for(int k = 2 ; k <= n ; k++){
            for(int start = 0; start <= n - k; start ++){
                int end = start + k;
                for(int i = start + 1; i < end ; i ++){
                    dp[start][end] = Math.max(dp[start][end],num[start]*num[i]*num[end] + dp[start][i] + dp[i][end]);
                }
            }
        }
        return dp[0][n];
        
    }
    //647. Palindromic Substrings 
    // same idea with 5
    public int countSubstrings(String s) {
        int n = s.length();
        int res = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                if(dp[i][j]) ++res;
            }
        }
        return res;
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
