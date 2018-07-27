
public class BitManipulation {
//https://leetcode.com/problems/sum-of-two-integers/discuss/84278/A-summary:-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently

	
	
	
	//191. Number of 1 Bits
    public int hammingWeight(int n) {
        int bitCount = 0;
        for(int j = 0 ; j < 32 ; j ++){
            bitCount += (n >> j) & 1;
        }
        return bitCount;
    }
	
// 477 brute force!! n^2
	
    public int totalHammingDistance(int[] nums) {
        int total = 0;
        for(int i = 0; i < nums.length-1; i++){
            for (int j = i + 1 ; j < nums.length; j++)
                total += hammingDistance(nums[i], nums[j]);
        }
        return total;
    }
    
    private int hammingDistance( int x, int y){
        return Integer.bitCount(x ^ y);
    }
    
    //477 using bit set
    public int totalHammingDistance2(int[] nums) {
        int result = 0;
        int total = nums.length;
        for(int j = 0 ; j < 32; j++){
            int bitCount = 0;
            for(int i = 0; i < total; i++){
                bitCount += (nums[i] >> j) & 1;
            }
            result += bitCount * (total - bitCount);
        }
        return result;
    }
	
}
