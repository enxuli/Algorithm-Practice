import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.lang.Math;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.Collections;

public class BFSCollection {
	public class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
		  }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean isValidBST(TreeNode root, long minVal, long maxVal){
        if (root == null) return true;
        if (root.val <= minVal || root.val >= maxVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
    
    
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        
        while (cur != null || !stack.empty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
            
        return list;
    }
    
    
    public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left);
        if (k <= count) {
            return kthSmallest(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest(root.right, k-1-count); // 1 is counted as current node
        }
        
        return root.val;
    }
    
    public int countNodes(TreeNode n) {
        if (n == null) return 0;
        
        return 1 + countNodes(n.left) + countNodes(n.right);
    }
    
    public int getMinimumDifference(TreeNode root) {
        int min = Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur!=null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if(pre != null && (cur.val - pre.val)<=min) min = cur.val - pre.val;
            pre = cur;
            cur = cur.right;
        }
        return min;
    }
    int minDiff = Integer.MAX_VALUE;
    public int getMinimumDifference2(TreeNode root) {
        helper(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
        return minDiff;
    }
    private void helper(TreeNode curr, int leftVal, int rightVal){
        if(curr==null) return;
        if(leftVal!=Integer.MIN_VALUE){
            minDiff = Math.min(minDiff,curr.val - leftVal);
        }
        if(rightVal!=Integer.MAX_VALUE){
        minDiff = Math.min(minDiff,rightVal - curr.val);
        }
        helper(curr.left,leftVal,curr.val);
        helper(curr.right,curr.val,rightVal);
    }
    
    
    
    
}
