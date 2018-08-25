import java.util.ArrayList;
import java.util.List;

public class DivideAndConquer {

	
	//95. Unique Binary Search Trees II
	public List<DFS.TreeNode> generateTrees(int n) {
        // intuitive simply use divide and conquer
        if(n==0) return new ArrayList<>();
        return helper(1,n);
    }
    
    private List<DFS.TreeNode> helper(int start, int end){
            
        List<DFS.TreeNode> list = new ArrayList<>(); 
        if(start > end){
            list.add(null);
            return list;
        }
        if(start == end){
            list.add(new DFS.TreeNode(start));
            return list;
        }
        List<DFS.TreeNode> left,right;
        for(int i = start; i <= end; i++){
            left = helper( start, i - 1);
            right = helper( i + 1, end);
            for(DFS.TreeNode lnode : left){
                for(DFS.TreeNode rnode : right){
                	DFS.TreeNode root = new DFS.TreeNode(i);
                    root.left = lnode;
                    root.right = rnode;
                    list.add(root);
                }
            }
        }
        return list;
    }
    //437. Path Sum III
    // using a map to store the prefix path sum and then if we meet the preSum we need update the results and go to left and right
    // clear the currSum for only this node in the end! like backtracking because we are using map
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        return helper(root, 0, sum, preSum);
    }
    
    public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }
        
        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);
        
        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1); // a little bit like backtracking, we remove the data after reach the end!
        return res;
    }
}
