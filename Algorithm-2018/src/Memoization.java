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
    
    //297. Serialize and Deserialize Binary Tree
    // I could just using dfs because there is null for me to read instead of using preorder+inorder
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(root == null) return sb.toString();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if(cur == null) sb.append("n,");
            else {
                sb.append(cur.val + ",");
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        return sb.toString();
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("")) return null;
        String[] node = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(node[0]));
        queue.add(root);
        int index = 0;
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if(cur !=null){
                // just just be carefull for string equal!!!!
                cur.left = node[++index].equals( "n")? null : new TreeNode(Integer.valueOf(node[index]));
                cur.right = node[++index].equals("n")? null : new TreeNode(Integer.valueOf(node[index]));
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        return root;
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
    //543. Diameter of Binary Tree

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        int[] ans = dfs(root);
        return ans[0];
    }
    private int[] dfs(TreeNode root){
        int[] ans = new int[2];
        if(root == null ) return ans;
        
        int[] left = dfs(root.left);
        int[] right = dfs(root. right);
        
        ans[1] = 1 + Math.max(left[1], right[1]);
        ans[0] = Math.max(left[0],Math.max(right[0],left[1]+ right[1]));
        
        return ans;
    }
    
}
