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
    
    Map<Integer, Integer> map = new HashMap<>();
    public int[] findMode1(TreeNode root) {
        dfs(root);
        if (root == null) return new int[] {};
        int max = Collections.max(map.values());
        List<Integer> tmp = new ArrayList<>();
        for (int x : map.keySet()){
            if (map.get(x)==max) {
                tmp.add(x);
            }
        }
        int[] ans = new int[tmp.size()];
        for(int i = 0; i < tmp.size(); i++){
            ans[i] = tmp.get(i);
        }
        return ans;
        
    }
    public void dfs(TreeNode root){
        if (root == null) return;
        dfs(root.left);
        map.put(root.val, map.getOrDefault(root.val,0)+1);
        dfs(root.right);
    }
    
    public TreeNode convertBST1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        
        while (cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.right;
            }
            cur = stack.pop();
            if (pre != null) cur.val += pre.val;
            pre = cur;
            cur = cur.left;
        }
        return root;
    }
    
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        dfsconvertBST(root);
        return root;
    }
    
    private void dfsconvertBST(TreeNode root) {
        if (root == null) return;
        dfsconvertBST(root.right);
        root.val += sum;
        sum = root.val;
        dfsconvertBST(root.left);
    }
    
    List<Integer> ans = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        dfspreorderTraversal(root);
        return ans;
    }
    
    private void dfspreorderTraversal(TreeNode root){
        if(root == null) return;
        ans.add(root.val);
        
        dfspreorderTraversal(root.left);
        dfspreorderTraversal(root.right);
    }
    
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        
        while(cur != null || !s.isEmpty()){
            while(cur != null){
                s.push(cur);
                ans.add(cur.val);
                cur = cur.left;
            }
            cur = s.pop();
            cur = cur.right;
        }
        return ans;
    }
    
    LinkedList<Integer> anspostorderTraversal = new LinkedList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        dfspostorderTraversal( root );
        return ans;
    }
    public void dfspostorderTraversal(TreeNode root){
        if( root == null ) return ;
        dfspostorderTraversal(root.left);
        dfspostorderTraversal(root.right);
        ans.add(root.val);
    }
    
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode cur = root;
        TreeNode pre = null;
        TreeNode tmp = null;
        while(cur != null){
            if (cur.val > key){
                pre = cur;
                cur = cur.left;
            }else if (cur.val < key){
                pre = cur;
                cur = cur.right;
            }else{
                tmp = cur;
                break;
            }
        }
        if(tmp == null ) return root;
        else if (tmp == root){
            
             if(tmp.left != null){
                 root = tmp.left;
                 cur = tmp.left;
                 while(cur.right!=null){
                     cur = cur.right;
                 }
                 cur.right = tmp.right;
             }else {
                 root = tmp.right;
             }
            
        }else {
            if(tmp.left != null){
                if(pre.left == tmp)pre.left = tmp.left;
                if(pre.right == tmp)pre.right = tmp.left;
                cur = tmp.left;
                while(cur.right!=null){
                    cur = cur.right;
                }
                cur.right = tmp.right;
            }else{
                if(pre.left == tmp)pre.left = tmp.right;
                if(pre.right == tmp)pre.right = tmp.right;
            }
        }
        return root;
    }
    
    public boolean findTarget653(TreeNode root, int k) {
        return dfs(root, root,  k);
    }
    
    public boolean dfs(TreeNode root,  TreeNode cur, int k){
        if(cur == null)return false;
        return search(root, cur, k - cur.val) || dfs(root, cur.left, k) || dfs(root, cur.right, k);
    }
    
    public boolean search(TreeNode root, TreeNode cur, int value){
        if(root == null)return false;
        return (root.val == value) && (root != cur) 
            || (root.val < value) && search(root.right, cur, value) 
                || (root.val > value) && search(root.left, cur, value);
    }
    
    
    
}
