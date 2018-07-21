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
	
	
}
