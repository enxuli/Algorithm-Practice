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
	
	  class UndirectedGraphNode {
		      int label;
		      List<UndirectedGraphNode> neighbors;
		      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
		  };
	
	class Employee {
	    // It's the unique id of each node;
	    // unique id of this employee
	    public int id;
	    // the importance value of this employee
	    public int importance;
	    // the id of direct subordinates
	    public List<Integer> subordinates;
	};
	  public class TreeLinkNode {
		      int val;
		      TreeLinkNode left, right, next;
		      TreeLinkNode(int x) { val = x; }
		  }
	
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
    
    boolean[][] visited;
    public int maxAreaOfIsland(int[][] grid) {
        visited = new boolean[grid.length][grid[0].length];
        int max = 0;
        for(int i = 0; i< grid.length ;i++){
            for(int j = 0; j< grid[0].length ;j++){
                if(!visited[i][j] && grid[i][j]==1) {
                    max = Math.max(max,areaOfIsland(grid,i,j));
                }
            }
        }
        return max;
    }
    public int areaOfIsland(int[][] grid, int i, int j) {
        if( i >= 0 && i < grid.length && j >= 0 && j < grid[0].length ){
            if(visited[i][j]) return 0;
            visited[i][j] = true;
            if(grid[i][j]== 1)return 1 + areaOfIsland(grid, i+1, j) + areaOfIsland(grid, i-1, j) + areaOfIsland(grid, i, j-1) + areaOfIsland(grid, i, j+1);
        }
        return 0;
    }
	
    
    //733
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int preColor = image[sr][sc];
        if(preColor== newColor) return image;
        return dfs(image,sr,sc,preColor,newColor);
    }
    private int[][] dfs(int[][] image, int sr, int sc,int preColor,int newColor){
        if(0 <= sr&&sr < image.length&&0 <= sc&&sc < image[0].length &&image[sr][sc] == preColor){
            image[sr][sc] = newColor;
            dfs(image,sr-1,sc,preColor,newColor);
            dfs(image,sr+1,sc,preColor,newColor);
            dfs(image,sr,sc-1,preColor,newColor);
            dfs(image,sr,sc+1,preColor,newColor);
        }
        return image;
    }
    
    //257
    List<String> ansbinaryTreePaths = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        dfs(root,"");
        return ansbinaryTreePaths;
    }
    
    private void dfs(TreeNode root, String str){
        if (root == null) return;
        if(root.left==null && root.right==null){
        	ansbinaryTreePaths.add(str+root.val);
        }
        dfs(root.left,str + root.val + "->");
        dfs(root.right,str + root.val + "->");
    }
    
    //124
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }
    
    private int dfs(TreeNode root){
        if (root == null) return 0;
        
        int left = Math.max(0,dfs(root.left));
        int right = Math.max(0,dfs(root.right));
        
        max = Math.max (max, left + right + root.val);
        
        return Math.max(left,right) + root.val;
    }
    
    //129
    public int sumNumbers(TreeNode root) {
        return dfssumNumbers(root,0);
    }
    
    private int dfssumNumbers(TreeNode root, int str){
        if(root == null) return 0;
        if(root.left ==null&& root.right ==null) {
            return str*10 + root.val;
        }else{
            return dfssumNumbers(root.left, str*10 + root.val)+dfssumNumbers(root.right, str*10 + root.val);
        }
    }
    
    //200
    public int numIslands(char[][] grid) {
        int count = 0;
        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j< grid[0].length; j++){
                if(grid[i][j] == '1'){
                    dfs(grid,i,j);
                    count++;
                }
            }
        }
        return count;
    }
    
    private void dfs(char[][] grid, int i, int j){
        
        if(i>=0 && i < grid.length && j>=0 && j<grid[0].length&& grid[i][j] == '1'){
            grid[i][j] = '0';
            dfs(grid, i+1, j);
            dfs(grid, i-1, j);
            dfs(grid, i, j+1);
            dfs(grid, i, j-1);
        }
        
    }
    
    //116
    
    int  count = 0;
    TreeLinkNode previous;
    public void connect(TreeLinkNode root) {
        int height = getHeight(root);
        for(int level = 1; level <= height; level ++){
            dfs(root,level);
            count = 0;
        }
    }
    
    private void dfs(TreeLinkNode root,int level){
        if( level < 1) return;
        dfs(root.left ,level - 1);
        if(level == 1 ){
            if(count == 0){
                previous = root;
                count++;
            }else {
                previous.next = root;
                previous = root;
                count++;
            }
        }
        dfs(root.right,level - 1);
        
    }
    
    private int getHeight(TreeLinkNode root){
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left),getHeight(root.right));
    }
    
    //133
    Map<Integer,UndirectedGraphNode> map = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        
        if( map.containsKey(node.label)) return map.get(node.label);
        
        UndirectedGraphNode tmp = new UndirectedGraphNode(node.label);
        map.put(tmp.label,tmp);
        
        for(UndirectedGraphNode neighbor : node.neighbors){
            tmp.neighbors.add(cloneGraph(neighbor));
        }
        return tmp;
    }
    
    //199 time consuming method
    List<Integer> ansrightSideView = new ArrayList<>();
    int countrightSideView;
    public List<Integer> rightSideView(TreeNode root) {
        int height = getHeight(root);
        
        for(int level = 1; level <= height; level++){
        	countrightSideView = 0;
            postOrderDfs(root, level);
        }
        return ansrightSideView;
        
    }
    
    private void postOrderDfs(TreeNode root, int level){
        if (root == null || level < 1 ||countrightSideView >0) return;
        postOrderDfs(root.right, level-1);
        postOrderDfs(root.left, level-1);
        if(level == 1) {ansrightSideView.add(root.val); countrightSideView ++;}
    }
    
    private int getHeight(TreeNode root){
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left),getHeight(root.right));
    }
    
    
    //199 one traversal method
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        ans =  dfs(root,ans,0);
        return ans;
        
    }
    
    private List<Integer> dfs(TreeNode root, List<Integer> ans, int level){
        if (root == null ) return ans;
        if(level == ans.size()) {ans.add(root.val);}
        dfs(root.right,ans, level+1);
        dfs(root.left,ans,level+1);
        
        return ans;
    }
    
}
