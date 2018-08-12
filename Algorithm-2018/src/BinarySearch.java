import java.util.TreeSet;

public class BinarySearch {
	
	//50. Pow(x, n)
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        
        if(n<0){
            x = 1/x;
        }
        return (n%2 == 0) ? myPow(x*x, (n<0? -1:1)* (n/2)) : x*myPow(x*x, (n<0? -1:1)* (n/2));
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
