import java.util.TreeSet;

public class BinarySearch {
	
	//35. Search Insert Position
    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length-1;
        while(low <= high){
            int mid = (low + high) / 2 ;
            if(nums[mid] > target) high = mid - 1;
            else if(nums[mid] < target) low = mid + 1;
            else return mid;
        }
        return low;
    }
	
	//50. Pow(x, n)
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        
        if(n<0){
            x = 1/x;
        }
        return (n%2 == 0) ? myPow(x*x, (n<0? -1:1)* (n/2)) : x*myPow(x*x, (n<0? -1:1)* (n/2));
    }
    // sqrt(x) 
    // brute force O(sqrt(n))
    public int mySqrt(int x) {
        int i = 0;
        for(; ((long)i*i)<=x; i++){}
        
        return i-1;
    }
    //binary search approach
    //O(log n)
    public int mySqrt2(int x) {
        if(x == 1) return 1;
        long low = 0, high = x;
        while(low < high-1){
            long mid = (low + high) / 2;
            if(mid * mid < x) low = mid;
            else if(mid * mid > x) high = mid;
            else return (int)mid;
        }
        if(high * high == x) return (int) high;
        return (int)low;
    }
    //153. Find Minimum in Rotated Sorted Array

    public int findMin(int[] nums) {
        //intuitively using binray search
        int low = 0, high = nums.length-1;
        while(low<high){
            int mid = (low + high)/2;
            if(nums[mid]>nums[high]) {low = mid+1;}
            else{high = mid;}
        }
        return nums[low];
    }
    //154 II including duplicates
    public int findMin2(int[] nums) {
        //intuitively using binray search
        int low = 0, high = nums.length-1;
        while(low<high){
            int mid = (low + high)/2;
            if(nums[mid]>nums[high]) {low = mid+1;}
            else if(nums[mid]<nums[high]){high = mid;}
            else { //actually here can be just high--;
            		// same effect and time Complexity like the isLeft!
                if(isLeft(nums,low,high)) low = mid+1;
                else high = mid;
            }
        }
        return nums[low];
    }
    private boolean isLeft(int[] nums, int low, int high){
        for(int i = low ; i < (low+high)/2; i++){
            if(nums[i+1]!=nums[i]) return false;
        }
        return true;
    }
    
    
	
	//Binary search in 1D array Pattern!!!!
	//Max Sum of Subarray No Larger Than K
	public int maxSumSubArray(int[] a , int k){
		int max = Integer.MIN_VALUE; 
		int sumj = 0; 
		TreeSet<Integer> ts = new TreeSet(); 
		ts.add(0); 
		for(int i=0;i<a.length;i++){
			sumj += a[i]; 
			/*E ceiling(E e): This method returns the least element 
			 * in this set greater than or equal to the given element,
			 *  or null if there is no such element.*/
			Integer gap = ts.ceiling(sumj - k);
			if(gap != null) max = Math.max(max, sumj - gap);
			ts.add(sumj); 
		} 
		return max;
	}
	
	//240. Search a 2D Matrix II
	// optimized binary search worst case log(log(n!)
    public boolean searchMatrix(int[][] matrix, int target) {
        //binary search both matrix[0][] and matrix[][0], find the boundry
        //binary search every col or row (depending on which is longer)
        if(matrix.length == 0|| matrix[0].length==0 || matrix == null) return false;
        int m = seachArray(matrix, false, 0, target,0,matrix.length-1); // row number
        int n = seachArray(matrix, true, 0, target,0,matrix[0].length-1); // col number
        //System.out.println("["+m+","+n+"]");
        if(matrix[0][n]==target || matrix[m][0] == target) return true;
        for(int i = 1 ; i <= (m<n?m:n); i++){
            int colfind = seachArray(matrix, true, i, target,i, n);
            int rowfind = seachArray(matrix, false, i, target,i, m);
            if(matrix[i][colfind]==target||matrix[rowfind][i]==target) return true;
        }
        return false;
    }
    private int seachArray(int[][] matrix, boolean isRow, int index, int target, int low ,int high){
        while(low < high){
            int mid = (low + high) / 2;
            //System.out.println(isRow?matrix[index][mid]:matrix[mid][index]);
            if((isRow?matrix[index][mid]:matrix[mid][index]) > target) high = mid;
            else if ((isRow?matrix[index][mid]:matrix[mid][index]) < target) low = mid + 1;
            else {low = mid; break;} 
        }
        return low;
    }
    // more awesome space deduct! O(m+n) which is DFS!!!
    public boolean searchMatrix2(int[][] matrix, int target) {
        //reshaping the search range every time!
        int col = 0, row = matrix.length-1;
        while(row >=0&&col<=matrix[0].length-1 ){
            if(matrix[row][col]>target) row--;
            else if (matrix[row][col]<target) col ++;
            else return true;
        }
        return false;
    }
	
	
	//363  combine all the selected col to 1D array and then use the binary search in 1D array!!. 
	//363. Max Sum of Rectangle No Larger Than K
    public int maxSumSubmatrix(int[][] matrix, int target) {
        int row = matrix[0].length;
        int col = matrix.length;
        if(col == 0) return 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < row; i++){
            int[] array = new int[col];
            for(int j = i; j < row; j++ ){
                TreeSet<Integer> preSum = new TreeSet<>();
                preSum.add(0);
                int sum = 0;
                for(int k = 0; k < col; k++){
                    array[k] = array[k]+matrix[k][j];
                    sum += array[k];
                    Integer lastElement = preSum.ceiling(sum - target);
                    if(lastElement!=null) max = Math.max(max, sum - lastElement);
                    preSum.add(sum);
                }
            }
        }
        return max;
    }

	


}
