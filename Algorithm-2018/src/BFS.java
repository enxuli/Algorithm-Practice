import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BFS {
	  public class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
		  }
	
	//107. Binary Tree Level Order Traversal II
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        if(root ==null) return ans;
        Queue<TreeNode> q = new java.util.LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            int n = q.size();
            for(int i = 0 ; i < n;i ++){
                TreeNode cur = q.poll();
                System.out.println(cur.val);
                if(cur.left!=null)q.add(cur.left);
                if(cur.right!=null)q.add(cur.right);
                tmp.add(cur.val);

            }
            ans.add(0,new ArrayList<>(tmp));
            tmp.clear();
        }
        return ans;
    }
	
	//542 BFS version based on DFS trick
	
    private int m, n;
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int[][] updateMatrix(int[][] matrix) {
        m = matrix.length; n = matrix[0].length;
        
        for(int i = 0; i < m; ++i){
            for (int j = 0; j < n; ++j){
                if (!hasZeroNeighbor(matrix,i,j)&& matrix[i][j] ==1) matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        Queue<int[]> q = new java.util.LinkedList<>();
        for(int i = 0; i < m; ++i){
            for (int j = 0; j < n; ++j){
               if(matrix[i][j] ==1) { // margin
                   q.add(new int[]{i,j});
                   while(!q.isEmpty()){
                      int[] coor = q.poll();
                      int a = coor[0];
                      int b = coor[1];
                       
                      for(int[] dir : dirs){
                          int x = a + dir[0]; int y = b + dir[1];
                          if(x >= 0 && x < m && y >= 0 && y < n && matrix[x][y]>matrix[a][b]){
                              q.add(new int[]{x,y});
                              matrix[x][y] = matrix[a][b]+1;
                          }
                      }
                   }
                }
            }
        }
        return matrix;
    }
    
    private boolean hasZeroNeighbor(int[][] matrix, int x, int y){

        if(x>0&&matrix[x-1][y]==0) return true;
        if(x<matrix.length-1&&matrix[x+1][y]==0) return true;
        if(y>0&&matrix[x][y-1]==0) return true;
        if(y<matrix[0].length-1&&matrix[x][y+1]==0) return true;
        return false;
    }
}
