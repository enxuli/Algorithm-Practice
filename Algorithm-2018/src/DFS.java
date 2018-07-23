import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.lang.Math;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import java.util.Collections;


public class DFS {

	public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	  }

	public class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) { val = x; }
		  }
	
	class Node {
	    public int val;
	    public List<Node> children;
	
	    public Node() {}
	
	    public Node(int _val,List<Node> _children) {
	        val = _val;
	        children = _children;
	    }
	};
	
	//105
	public TreeNode buildTree(int[] preorder, int[] inorder) {
	    return build(preorder,inorder,0,0,preorder.length-1);
	}
	
	private TreeNode build(int[] preorder, int[] inorder, int rootIndex, int start, int end){
	    if(start > end || rootIndex > preorder.length-1) return null;
	    TreeNode root = new TreeNode(preorder[rootIndex]);
	    int idx = 0;
	    for(int i = start ; i <= end; i ++){
	        if(inorder[i]==preorder[rootIndex]) idx = i;
	    }
	    
	    root.left = build(preorder,inorder,rootIndex+1,start,idx-1);
	    root.right = build(preorder,inorder,rootIndex+idx-start+1,idx+1,end);
	    return root;
	    }
	
	
	
	
	//574
	
	public int findCircleNum(int[][] M) {
	    boolean[] visited = new boolean[M.length];
	    int count = 0;
	    for(int i = 0; i < M.length; i++) {
	        if (!visited[i]) {
	            count++;
	            dfs(M, i, visited);
	        }
	    }
	    return count;
	}
	
	void dfs(int[][] M, int node, boolean[] visited) {
	    visited[node] = true;
	    for (int next = 0; next < M.length; next ++) {
	        if (M[node][next] == 1 && !visited[next]) {
	            dfs(M, next, visited);
	        }
	    }
	}
	
	
	//112 misunderstand the problem with path root! to! leaf!
    boolean ans = false;
    public boolean hasPathSum(TreeNode root, int sum) {
        dfs(root,sum);
        return ans;
    }
    
    private void dfs(TreeNode root, int sum){
        if (root == null) return;
        if(ans = subHasPathSum( root, sum, root.val)) return;
        dfs(root.left, sum);
        dfs(root.right, sum);
    }
    
    private boolean subHasPathSum(TreeNode root, int target, int sum){
        if (sum == target) return true;
        else if(sum > target || root == null) return false;
        return subHasPathSum(root.left,target,sum+ root.val)||subHasPathSum(root.right,target,sum+root.val);
    }
    
  //112 misunderstand the problem with path root! to! leaf! leaf is a node with no child!!!! 
    public boolean hasPathSum2(TreeNode root, int sum) {
        if(root == null ) return false;
        
        if(sum - root.val == 0 && (root.left==null && root.right==null)) return true;
        
        return hasPathSum( root.left , sum-root.val)||hasPathSum( root.right , sum-root.val);
    }
    
    //113
    List<List<Integer>> ans113 = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, sum, new ArrayList<>());
        return ans113;
    }
    private void dfs(TreeNode root, int sum, List<Integer> tmp){
        if(root == null ) return;
        tmp.add(root.val);
        if(sum - root.val == 0 && (root.left==null && root.right==null)) ans113.add(new ArrayList<>(tmp));

        dfs( root.left , sum-root.val, tmp);
        dfs( root.right , sum-root.val, tmp);
        tmp.remove(tmp.size()-1);
    }
    //114
    private TreeNode previous;

    public void flatten(TreeNode root) {
            
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        
        root.right = previous;
        root.left = null;
        
        previous = root;
        

    }
    
    //559
    public int maxDepth(Node root) {
        if(root == null) return 0;
        int max = 0;
        
        for (Node child : root.children){
            max = Math.max(max, maxDepth(child));
        }
        return max + 1;
    }
    //690
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map=new HashMap<>();
        for(Employee employee: employees){
            map.put(employee.id,employee);
        }
        return dfs(map, id);
        
    }
    private int dfs(Map<Integer, Employee> map, int id){
        Employee tmp = map.get(id);
        int totalImp = tmp.importance;

        for(int i : tmp.subordinates){
            totalImp+=dfs(map, i);
        }
        return totalImp;
    }
    
    //695
    
    
	
}
