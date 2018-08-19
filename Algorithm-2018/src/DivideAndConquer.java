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
}
