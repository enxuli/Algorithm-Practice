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
	
	
	//
}
