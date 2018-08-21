import java.util.ArrayList;
import java.util.Arrays;
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
    //10. Regular Expression Matching
    public boolean isMatch(String t, String p) {
        // intuition: we have dp[i][j], i for length i in s, j for length j in p, dp to store the if they match with i and j length
        // 1 if s[i] == p[j] dp[i][j] = dp[i-1][j-1];
        // 2 if p[j] == '.' we need to pass the j so that dp[i][j] = dp[i-1][j-1]
        // 3 if p[*] == '*' we have to see the previous char in p and s
        // if p[j-1] != s[i]  dp[i][j] = dp[i][j-2]
        // if p[j-1] == s[i] || p[j-1] == '.' dp[i][j] = dp[i][j-2](for zero)||dp[i][j-1](for one)||dp[i-1][j](for multiple)
        // because there can be a lot of previous i that can match j if it's multiple, so we make it the last i.
        char[] string = t.toCharArray();
        char[] pattern = p.toCharArray();
        int m = string.length;
        int n = pattern.length;
        boolean[][] dp = new boolean[m+1][n+1];
        
        //here comes the most tricky part!!!! because there can be a* become empty so that we need to make sure all the 
        //boundery with * to be same with the previous empty, as they can be start with true or stick to the non empty boundary
        dp[0][0] = true;
        for(int j = 1 ; j <= n; j++) if(pattern[j-1] =='*') dp[0][j] = dp[0][j-2];
        
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(string[i-1] == pattern[j-1]) dp[i][j] = dp[i-1][j-1];
                else if(pattern[j-1] == '.') dp[i][j] = dp[i-1][j-1];
                else if(pattern[j-1] == '*'){
                    if(j == 1) continue;
                    if(pattern[j-2] != string[i-1]&&pattern[j-2] != '.') dp[i][j] = dp[i][j-2];
                    else{
                        dp[i][j] = dp[i][j-2]||dp[i][j-1]||dp[i-1][j];
                    }
                }
            }
        }
        return dp[m][n];
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
	
    
    //44. Wildcard Matching

    public boolean isMatch2(String s, String p) {
        //simplified 10 match pattern
        // say we dp[i][j] to see if length i for s matches length j for p
        // 1. if s[i-1] == p[j-1] dp[i][j] = dp[i-1][j-1];
        // 2. if p[j-1] == '*' dp[i][j] = dp[i][j-1] (for zero) || dp[i-1][j-1](for one) || dp[i-1][j](for multiple);
        // 3. if p[j-1] == '?' dp[i][j] = dp[i-1][j-1];
        
        char[] string = s.toCharArray();
        char[] pattern = p.toCharArray();
        int n = string.length;
        int m = pattern.length;
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0]= true;
        // dont forget the trikcy part ! the first element witch is * can be empty!!!
        
        for(int j = 1; j<= m; j ++) if(pattern[j-1] == '*') dp[0][j] = dp[0][j-1];
        for(int i = 1; i <= n ; i ++){
            for(int j = 1 ; j <= m ; j ++){
                if(string[i-1] == pattern[j-1] || pattern[j-1] == '?') dp[i][j] = dp[i-1][j-1];
                else if(pattern[j-1] =='*') dp[i][j] = dp[i][j-1] || dp[i-1][j-1] || dp[i-1][j];
            }
        }
        return dp[n][m];
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
    //97. Interleaving String
    public boolean isInterleave(String s1, String s2, String s3) {
        // this is quite similar to the minimum operators problem!!!!!
        // when we come across two strings! we always use 2D array of DP!
        // one demension for length of s1 one demension for length of s2
        // say dp[i][j] is for length i in s1 and length j in s2
        // the interleaving pattern could be unidentical so there can be 
        
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] src = s3.toCharArray();
        int m = str1.length;
        int n = str2.length;
        int srcLength = src.length;
        if(srcLength != m+n) return false;
        boolean[][] dp = new boolean[m+1][n+1];
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <=n; j++){
                if(i == 0 && j == 0)dp[i][j] = true;
                else if(i == 0) dp[0][j] = dp[0][j-1] && (str2[j-1]==src[j-1]);
                else if(j == 0) dp[i][0] = dp[i-1][0] && (str1[i-1]==src[i-1]);
                else dp[i][j] = (dp[i][j-1] && (str2[j-1]==src[i+j-1]))||(dp[i-1][j] && (str1[i-1]==src[i+j-1]));
            }
        }
        return dp[m][n];
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
    // intuitive usng dfs and memoiztion dp! but with O(n^2)
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
    
    //132. Palindrome Partitioning II
    
    //I realize the order we are going through is actually matter!!!
    // int this case , we are going right to left
    // which doest not matter too much but it affect the cut[]!!!!!
    public int minCut(String s) {
        // intuition: use dp[i][j] to store wheher i to j is a palindromic subarray!
        // dp[i][j] = s[i] == s[j] && ( j-i+1<=3 || dp[i+1][j-1])
        // then go over dp[i][j] from top left to bottom right make last encountered true j as a next i = j +1 also count ++
        // this will not work because the count itself need some comparison and select(count need dp too!!!)
        // so we have to decide whether cut[i] = min(cut[j+1]+1)(i=<j<n); 
        // in the end return the count
        char[] str = s.toCharArray();
        int n = str.length;
        boolean[][] dp = new boolean[n][n];
        int[] cut = new int[n];
        int min = 0;
        for(int i = n-1; i >=0; i--){
            min = n-i;
            for(int j = i; j < n; j++){
                if(str[i] == str[j] && ( j-i+1<=3 || dp[i+1][j-1])){
                    dp[i][j] = true;
                    min = Math.min(min,j!=n-1?cut[j+1]+1:0);
                }  
            }
            cut[i] = min;
        }

        return cut[0];
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
    
    //221. Maximal Square

    public int maximalSquare(char[][] matrix) {
        //say we have dp[i][j] to recard the area of square one corner at the matrix[i][j]!!
        //
        // but actually we dont have to record the area we just need to recorde be side lenth!
        //
        // and use max to record the maximum dp[i][j] during the interation
        //1. if matrix[i][j] == '0', then dp[i][j] = 0
        //2. if matrix[i][j] == '1', dp[i][j] = (1 + Math.sqrt(Math.min(dp[i-1,j-1],dp[i-1,j],dp[i][j-1])))
        int m = matrix.length;
        if(m == 0 ) return 0;
        int n = matrix[0].length;
        int[][] dp = new int[m+1][n+1];
        int areaMax = 0;
        for(int i = 1 ; i <= m; i++){
            for(int j = 1; j <= n ; j++){
                if(matrix[i-1][j-1] == '0') dp[i][j] = 0;
                else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j],dp[i][j-1]));
                    areaMax = Math.max(dp[i][j], areaMax);
                }
            }
        }
        return areaMax*areaMax;
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
    //304. Range Sum Query 2D - Immutable
    class NumMatrix {
        // we can easily get that the the region is actually (0,0) to (r2,c2) cut by two parts (r1,c2)and (r2,c1) add one intersect part(r1,c1)! 
        // intuition we can firstly construct a 2D array to store the sum from (0,0) to (i,j)
        // then every time we call sum region we only do the math within constant time!
        
        int[][] sum;
        
        public NumMatrix(int[][] matrix) {
            int n = matrix.length;
            if(n == 0) return;
            int m = matrix[0].length;
            sum = new int[n][m];
            for(int i = 0; i < n ; i++){
                for(int j = 0; j < m ; j++){
                    if(i == 0 && j == 0) sum[i][j] = matrix[i][j];
                    else sum[i][j] = (i-1>=0?sum[i-1][j]:0) + (j-1>=0?sum[i][j-1]:0) + matrix[i][j] - (i-1>=0&&j-1>=0?sum[i-1][j-1]:0);
                }
            }
        }
        
        public int sumRegion(int row1, int col1, int row2, int col2) {
            if(sum.length == 0||sum[0].length == 0) return 0;
            if(row1 == 0 && col1 == 0 ) return sum[row2][col2];
            else if(row1 == 0 ) return sum[row2][col2] - sum[row2][col1-1];
            else if(col1 == 0 ) return sum[row2][col2] - sum[row1-1][col2];
            else return sum[row2][col2] - sum[row1-1][col2] - sum[row2][col1-1] + sum[row1-1][col1-1];
        }
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
    
    //322. Coin Change
    public int coinChange(int[] coins, int amount) {
        // intuition greedy: O(mn) but greedy has problem!!! it some times cannot foresee if it can be solved!
        // this problem should be solved by dp
        // say dp[i] is the least amount of coins that we need to add up to i!
        // so dp[i] sould pick up the smallest previous amount from the one can be solved! mount dp[i] = min(dp[i-coin[j]]+1) j = 0,1,..., n
        // the tricky part is because there can be the amount can not be solved, we init the dp with amount + 1!!
        // only dp[0] = 0;
        // now this is bottom up! we have reverse the thoughts!
        
        int n = coins.length;
        int[] dp = new int[amount + 1 ];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for(int i = 1 ; i <= amount ; i++){
            for(int j = 0; j < n; j++){
                if(coins[j]<=i){
                    dp[i] = Math.min(dp[i], dp[i- coins[j]]+1);
                }
            }
        }
        return dp[amount] > amount? -1 : dp[amount];
        
    }
    //343. Integer Break
    public int integerBreak(int n) {
        //intuition: use dp[i] to store the max product of i
        // and dp[i] = max(( i - j ) dp[i - j])
        // time would be O(n^2) space would be O(n)
        int[] dp = new int[n+1];
        dp[2] = 1;
        for(int i = 3; i <= n ; i ++){
            for(int j = i - 1; j>=1; j --){
                dp[i] = Math.max(dp[i],(i-j)*(dp[j]<j? j:dp[j]));
            }
        }
        //System.out.println(Arrays.toString(dp));
        return dp[n];
    }
    
    //413. Arithmetic Slices
    public int numberOfArithmeticSlices(int[] A) {
        // this is quite similar with the delete char distance ans some question that ask number of consecutive length
        // intuition using dp[i] to store the number of slice end with i
        // then how do we determine dp[i]?
        // like for 1 2 3 4 dp[3] = 1, dp[4] = if(A[4-1]-A[4-2] == A[4-2]-A[4-3]) 1 + dp[4-1] : 0
        // as for the boudery dp[0] = dp[1] = 0; plus dp[3] can not be greater than 1
        int n = A.length;
        if(n<3) return 0;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;
        int total = 0;
        for(int i = 3 ; i <= n ;i++){
            dp[i] = A[i-1]-A[i-2] == A[i-2]-A[i-3]? 1 + dp[i-1] : 0;
            total+=dp[i];
        }
        return total;
    }
    //516. Longest Palindromic Subsequence
    public int longestPalindromeSubseq(String s) {
        //intuition: very similar to the longest palindromic substring
        // but this is more loose because the this is for subsquence which can be unconsecutive!
        // so let's say there is at most a length of dp[i+1][j-1] palindromic subsquence in the index from i+1 to j-1
        // then dp[i][j] can be dp[i+1][j-1] + 2 if s[i] == s[j]
        // or dp[i][j] is just the biger one in dp[i+1][j] or dp[i][j-1]
        char[] str = s.toCharArray();
        int m = str.length;
        int[][] dp = new int[m][m];
        // you have to reverse the thought here btw ! because the dp table itself is going from left bottom to right top
        // same with the 10 longest palindromic substring!
        for(int i = m-1; i >=0  ; i --){
            dp[i][i] = 1 ; // at least have a single char sequence!
            for(int j = i+1 ; j <m ; j++){
                if(str[i] == str[j]) dp[i][j] = dp[i+1][j-1] + 2;
                else dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }
        return dp[0][m-1];
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
    
    //746. Min Cost Climbing Stairs

    public int minCostClimbingStairs(int[] cost) {
        //intuition: reverse the thought and using dp[i] to record the cost from that stair to the destination 
        //say we know on the destination dp[n] = 0; 
        //also dp[n-1] = n-1 which can not be avoided if you step on last stair
        //so we can easily get dp[i] = cost[i-1] + Min(dp[i+1],dp[i+2])
        int n = cost.length;
        int[] dp = new int[ n + 1 ];
        dp[n] = 0;
        dp[n-1] = cost[n-1];
        for( int i = n - 2; i >= 0; i --){
            dp[i] = cost[ i ] + Math.min(dp[i+1],dp[i+2]);
        }
        return Math.min(dp[0],dp[1]);
    }
    
}
