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


public class Tree {

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



	//654 bad version
	public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (Arrays.equals(new int[]{}, nums)) return null;
        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0 ; i < nums.length; i++){
            if(nums[i] > max) {
                index = i;
                max = nums[i];
            }
        }
        TreeNode root = new TreeNode( nums[index]);
          
        if(index >=1){
            int[] left = Arrays.copyOfRange(nums, 0, index);
            root.left = constructMaximumBinaryTree(left);
        }
        if(index <=nums.length-1){
            int[] right = Arrays.copyOfRange(nums, index+1, nums.length);
            root.right = constructMaximumBinaryTree(right);
        }
        return root;
    }
    
	
	//654 using start and end instead of put through array!!!
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        if (nums == null) return null;
        return build(nums, 0, nums.length - 1);
    }
    
    private TreeNode build(int[] nums, int start, int end) {
        if (start > end) return null;
        
        int idxMax = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > nums[idxMax]) {
                idxMax = i;
            }
        }
        
        TreeNode root = new TreeNode(nums[idxMax]);
        
        root.left = build(nums, start, idxMax - 1);
        root.right = build(nums, idxMax + 1, end);
        
        return root;
    }
    
    
    //104
    class Solution {
        public int maxDepth(TreeNode root) {
                    if(root == null)
                return 0; 
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right)); 
        }
    }
    
    //110 awesome height count!!!
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        if (Math.abs(countHeight(root.left)-countHeight(root.right))>1) return false;
        return isBalanced(root.left)&&isBalanced(root.right);
    }

    int countHeight(TreeNode root){
        if(root == null)
            return 0; 
        return 1 + Math.max(countHeight(root.left), countHeight(root.right)); 
        
    }
    
    //111 
    public int minDepth(TreeNode root) {
        if(root == null) return 0; 
        if(root.left == null || root.right == null)
            return 1 + minDepth(root.right) + minDepth(root.left) ; 
        return 1 + Math.min(minDepth(root.left), minDepth(root.right)); 
    }
    
    //101
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isMirror(root.left, root.right);
    }
    
    private boolean isMirror(TreeNode left,TreeNode right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        return left.val == right.val && isMirror(left.left,right.right) && isMirror(left.right,right.left);
    }
    
    

    
    //590 N-ary Tree Postorder Traversal
    
    List<Integer> list = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        postorderDFS(root);
        return list;
    }
    private void postorderDFS(Node root){
        if(root == null) return;
        for(Node child : root.children){
            postorderDFS(child);
        }
        list.add(root.val);
    }
    //589. N-ary Tree Preorder Traversal
    List<Integer> list2 = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        preorderDFS(root);
        return list2;
    }
    private void preorderDFS(Node root){
        if(root == null) return;
        list.add(root.val);
        for(Node child : root.children){
            preorderDFS(child);
        }
    }
    
    //589 iterative version
    public List<Integer> preorder2(Node root) {
        List<Integer> list = new ArrayList<>();
        Node cur = root;
        if (cur == null) return list;
        
        Stack<Node> s = new Stack<>();
        s.push(cur);
        
        while(!s.isEmpty()){
            cur = s.pop();
            list.add(cur.val); ///preorder do here 
            for(int i = cur.children.size()-1; i>=0; i--){ // with backforward child!!!
                s.push(cur.children.get(i));
            }
        }
        return list;
    }
    
    //590 iterative
    
    public List<Integer> postorder2(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        
        while(!stack.isEmpty()) {
            root = stack.pop();
            list.add(root.val); ///postorder do here 
            for(Node node: root.children) // with forward child!!!
                stack.add(node);
        }
        Collections.reverse(list); // reverse in the end
        return list;
    }
    //652. Find Duplicate Subtrees

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        // like i thought, we can take the advantage of the serialization of the tree node
        // that is how we can put them into the map and check if there already exsit the same serials
        List<TreeNode> ans = new ArrayList<>();
        dfspostorder(root,new HashMap<String,Integer>(),ans);
        return new ArrayList<TreeNode>(ans);
    }
    // remember here we can not put the duplicate answer into the list!!!
    private String dfspostorder(TreeNode root, HashMap<String,Integer> set, List<TreeNode> ans){
        if(root == null) return "";
        String serial = root.val + "," + dfspostorder(root.left, set, ans)+ "," + dfspostorder(root.right,set,ans);
        if(set.getOrDefault(serial,0)==1) ans.add(root);
        set.put(serial,set.getOrDefault(serial,0)+1);
        return serial;
    }
    
    //687. Longest Univalue Path
    int len = 0; // global variable
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        len = 0;
        getLen(root, root.val);
        return len;
    }

    private int getLen(TreeNode node, int val) {
        if (node == null) return 0;
        int left = getLen(node.left, node.val);
        int right = getLen(node.right, node.val);
        len = Math.max(len, left + right);
        if (val == node.val)  return Math.max(left, right) + 1;
        return 0;
    }
    
    
	
}
