import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Memoization {

	
	//329
	
	
	//139. Word Break

    public boolean wordBreak1(String s, List<String> wordDict) {
        return dfs(new Boolean[s.length()],s,wordDict,0);
    }
        private boolean dfs(Boolean[] cache,String s, List<String> wordDict,int start){
        if(start == s.length()) return true;
        if(cache[start]!= null) return cache[start];
        boolean res = false;
        for(int i = start + 1; i <=s.length(); i++ ){
            if(wordDict.contains(s.substring(start,i))&&dfs(cache,s,wordDict,i)){
                return cache[start] = true;
            }
        }
        return cache[start] = false;
    }
	
	
	//140. Word Break II
    //using memoization to store the case already exists
    HashMap<Integer,List<String>> map = new HashMap<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s,wordDict,0);
    }
    private List<String> dfs(String s, List<String> wordDict,int start){
        //System.out.println(word);
        if(map.containsKey(start))return map.get(start);
        
        List<String> res = new ArrayList<>();
        
        if(start == s.length()) {res.add("");}
        
        List<String> tmp = new ArrayList<>();
        
        for(int i = start+1; i <=s.length(); i++ ){
            if(wordDict.contains(s.substring(start,i))) {
                List<String> rest = dfs(s,wordDict,i);
                for(String str : rest){
                    res.add(s.substring(start,i) + (i==s.length()? "":" ") + str);
                }
            }
        }
        map.put(start,res);
        return res;
    }
    
    //140 derectly start from the Diction!!!
    public List<String> wordBreak2(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, ArrayList<String>>());
    }       

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, List<String> wordDict, HashMap<String, ArrayList<String>>map) {
        if (map.containsKey(s)) 
            return map.get(s);
            
        List<String> res = new ArrayList<String>();     
        if (s.length() == 0) {
            res.add("");
            return res;
        }               
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist) 
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);               
            }
        }       
        map.put(s, res);
        return res;
    }
    
    //312. Burst Balloons
    public int maxCoins(int[] nums) {
        int[] nums2 = new int[nums.length + 2];
        nums2[0] = 1;
        int i = 1;
        for (int n: nums) {
            if (n > 0) nums2[i++] = n;
        }
        nums2[i] = 1;
        int[][] memo = new int[i + 1][i + 1];
        
        return maxCoins(nums2, 0, i, memo);
    }
    
    private int maxCoins(int[] nums, int left, int right, int[][] memo) {
        if (left == right - 1) return 0;
        if (memo[left][right] > 0) return memo[left][right];
        
        int max = 0;
        for (int i = left + 1; i < right; i++) {
            int resultForI = 
                nums[left] * nums[i] * nums[right] +
                maxCoins(nums, left, i, memo) +
                maxCoins(nums, i, right, memo);
            max = Math.max(max, resultForI);
        }
        memo[left][right] = max;
        return max;
    }

    
}
