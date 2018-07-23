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

public class graph {
    class GraphNode{
        int val;
        List<GraphNode> neighbors;
        GraphNode(int x){
            val = x;
            neighbors = new ArrayList<>();
        }
    }
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
    
    GraphNode graphtarget;
    List<Integer> ans = new ArrayList<>();
    List<GraphNode> visited = new ArrayList<>();
    
    public GraphNode buildGraphMarkTarget(GraphNode parent,TreeNode tnode, TreeNode target){
        if(tnode == null) return null;
        GraphNode gnode = new GraphNode(tnode.val);
        if(tnode == target) graphtarget = gnode;
        if(parent != null) gnode.neighbors.add(parent);
        if(tnode.left != null)gnode.neighbors.add(buildGraphMarkTarget(gnode,tnode.left,target));
        if(tnode.right != null)gnode.neighbors.add(buildGraphMarkTarget(gnode,tnode.right,target));
        return gnode;
    }
    
    public void dfs(GraphNode gnode, int k){
        if(visited.contains(gnode)) return;
        else visited.add(gnode);
        if(k == 0){
            ans.add(gnode.val);
            return;
        }
        for(GraphNode neighbor: gnode.neighbors){
            dfs(neighbor,k-1);
        }
    }
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        buildGraphMarkTarget(null,root,target);
        dfs(graphtarget,K);
        return ans;
    }
	
	
	
}
