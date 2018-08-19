import java.util.Stack;

public class StackManipulation {
	//collection for find the intermediate accumulated maximum!
	//42. Trapping Rain Water
	//84. Largest Rectangle in Histogram
	//85. Maximal Rectangle
	
	//42
    public int trap(int[] height) {
        // using a stack to store the decreasing bar's index!
        int index = 0, len = height.length, accumulateDrops = 0;
        Stack<Integer> stack = new Stack<>();
        while(index < len){
            if(stack.isEmpty() || height[index]<=height[stack.peek()]) stack.push(index++);
            else{
                int curLowest = stack.pop();
                accumulateDrops += stack.isEmpty()? 0 : (Math.min(height[index],height[stack.peek()])-height[curLowest])*(index - stack.peek()-1);
                //just be damn aware that the stack is storing the index!!!!!!
            }
        }
        return accumulateDrops;
    }
	
	//84
    public int largestRectangleArea(int[] heights) {
        //this quiz uses a stack to store all the increasing bar
        //and make stack always increasing and compute the local max if there comes a smaller bar!
        //the local max also would be the current largest * length it has being increasing until meet the smaller one!
        // very bar * len has a chance to be Max
        // you have to be aware that stack is storing the index!!
        int index = 0, len = heights.length, maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        while(index <= len){
            if(stack.isEmpty() || (index<len && heights[index]>=heights[stack.peek()])) stack.push(index++);
            else{
                int localHighest = stack.pop();
                maxArea = Math.max(heights[localHighest] * (stack.isEmpty()? index : index - stack.peek() -1), maxArea);
                //stem.out.println(maxArea);
            }
        }
        return maxArea;
    }
    //85
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length==0 ) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;
        int[][] len = new int[m][n];
        for(int j = n-1; j >= 0; j--){
            for(int i = 0 ; i < m; i++){ //using dp to store the length from point to most right!
                if(matrix[i][j] == '0') len[i][j]=0;
                else len[i][j] = j==n-1? 1 : 1 + len[i][j+1];
            }
        }
        
        Stack<Integer> stack = new Stack<>();
        for(int j = 0; j < n; j++){
            for(int i = 0 ; i <= m; i++){
                int length = i == m? -1 : len[i][j];
                if(stack.isEmpty() || length>=len[stack.peek()][j]) stack.push(i);
                else{
                    int curLongestsIndex = stack.pop();
                    maxArea = Math.max(maxArea, len[curLongestsIndex][j] * (stack.isEmpty()? i : i - stack.peek() - 1));
                    i--; // make the index unchange
                }
            }
            stack.clear();// dont forget to clear the stack!
        }
        return maxArea;

    }
	
}
