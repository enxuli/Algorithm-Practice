import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BitManipulation {
//https://leetcode.com/problems/sum-of-two-integers/discuss/84278/A-summary:-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently

	
	//89. Gray Code
	public List<Integer> grayCode(int n) {
	    List<Integer> rs=new ArrayList<Integer>();
	    rs.add(0);
	    for(int i=0;i<n;i++){
	        int size=rs.size();
	        for(int k=size-1;k>=0;k--)
	            rs.add(rs.get(k) | 1<<i);
	    }
	    return rs;
	}
	//137. Single Number II
	/*
    This is a case of a finite state machine.
    States of machine- Total three (number appeared once, number appeared twice, number appeared thrice)
    Action - Incoming bit of one
    We will need two bits to keep track of the state. So lets take those states as 00, 01 and 10.
    The states will transition like 00 -> 01 -> 10 with every incoming bit.
    Now lets look at the individual bits.
    First bit - 0 -> 0 -> 1 -> back to 0
    Second bit - 0 -> 1 -> 0 -> back to 0
    Note that these bits are transitioning with every state change. Now we need to find a pattern of this change.

    For first bit it is sufficient to say that with every incoming 1 bit, its next state is its XOR with it with an exception-
    If second bit is set, the first bit becomes zero. So we come up with =>
    ones = ones ^ A[i];
    if (twos == 1) then ones = 0
    It condenses to (ones ^ A[i]) & ~twos;

    For second bit, it is sufficient to say that with every incoming 1 bit, its next state is its XOR with it with an exception-
    If the one's bit after the change above is set, then it will become zero too. So we come up with =>
    twos = twos ^ A[i];
    if (ones* == 1) then twos = 0
    It condenses to (twos ^ A[i]) & ~ones;
     */
    public static int singleNumber5(int[] A) {
        int ones = 0, twos = 0;
        for(int i = 0; i < A.length; i++){

            // Accumulate the incoming number in ones provided twos is zero.
            // Twos will hold the number that has appeared twice.
            // If two becomes zero, it means the number has appeared the third time- Ones will hold that number now
            ones = (ones ^ A[i]) & ~twos;

            // Wait for ones bits to be zero before you increment twos.
            // Ones will be zero when the number is received twice.
            // So when the number will be received twice, we will store that in twos.
            twos = (twos ^ A[i]) & ~ones;
        }

        return ones;
    }
	
    //190. Reverse Bits
    public int reverseBits(int n) {
        int ans = 0;
        //brute force O(n)
        for(int i = 0; i < 32; i ++){
            ans |= ((n>>i)&1)<<(31-i);
        }
        return ans;
        
    }
    // more genius using O(1)!!
    //Switching left and right for 5 times which 2^5 = 32.
    public int reverseBits2(int n) {
        n = (n >> 16) | (n << 16);
        n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
        return n;
        
    }
    
    
	
	//191. Number of 1 Bits
    public int hammingWeight(int n) {
        int bitCount = 0;
        for(int j = 0 ; j < 32 ; j ++){
            bitCount += (n >> j) & 1;
        }
        return bitCount;
    }
    
    //201. Bitwise AND of Numbers Range

    public int rangeBitwiseAnd(int m, int n) {
        int and = m;
        //brute force O(n)
        for(int i = m ; i!=-2147483648 &&i <= n; i++){
            and &= i;
            //System.out.println(and+"i:"+i);
            if(and ==0) return 0;
        }
        return and;
    }
    // if two number are different one is odd one is even then 
    //there must be a 0 in their last AND bit
    // so the zero would accumulate at the different 
    // bit is binary so very two^n number between them would create a zero.
    public int rangeBitwiseAnd2(int m, int n) {
        if(m == 0){
            return 0;
        }
        int moveFactor = 0;
        while(m != n){
            m >>= 1;
            n >>= 1;
            moveFactor ++;
        }
        return m <<= moveFactor;
    }
    
    //231. Power of Two
    public boolean isPowerOfTwo(int n) {
        int bitCount = 0;
        for (int bit = 0 ; bit < 32; bit++ ) bitCount += (n>>bit) & 1;
        return  (bitCount == 1 && n > 0) ;
    }
    
    //260. Single Number III

    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for(int num : nums){ diff ^= num; }
        diff = Integer.highestOneBit(diff);
        int[] ans = new int[]{0,0};
        //use the highest different bit between the two single number to divide
        // the group into two part, each part has only one single number!!!
        //then use XOR to do single number I
        for(int num : nums){
            if ((diff & num) ==0){
                ans[0]^= num;
            }else{
                ans[1]^= num;
            }
        }
        return ans;
    }
    //268. Missing Number
    // to freaking easy just make everything doubled than find the miss which is single!!
    
    public int missingNumber(int[] nums) {
        int xor = 0, i = 0;
        for(; i < nums.length; i++){
            xor ^= i^nums[i];
        }
        return xor^i;
    }
    
    public boolean isPowerOfTwo2(int num) {
        int bitCount = 0;
        return  ((num&(num-1)) == 0 && num > 0) ;
    }
    
    
    //318. Maximum Product of Word Lengths
    public int maxProduct(String[] words) {
        //O(n^2)
        //usting bit to recard every words' charactor distribute
        //then AND those bit to get if two words have comman charactor!!
        int[] distribute = new int[words.length];
        for(int i = 0 ; i < words.length; i++){
            for(char ch : words[i].toCharArray()){
                distribute[i]|=1<<(ch-'a');
            }
        }
        int maxLen = 0;
        for(int i = 0; i < words.length-1; i ++){
            for(int j = i+1; j < words.length; j++)
                if((distribute[i]&distribute[j])==0){
                    maxLen=Math.max(maxLen,words[i].length()*words[j].length());
                }
        }
        return maxLen;
    }
    
    //320. Generalized Abbreviation
    public List<String> generateAbbreviations(String word) {
        // intuitive backtracking
        List<String> ans = new ArrayList<>();
        backtracking(ans,word,"",0,0);
        return ans;
    }
    private void backtracking(List<String> ans, String word, String tmp, int index, int count){
        if(index == word.length()){
            if(count>0)tmp+=count;
            ans.add(tmp);
        }else{
            backtracking(ans,word,tmp,index+1,count+1);
            backtracking(ans,word, tmp + (count>0? count:"")+ word.charAt(index),index+1,0);
        }
    }
    // bit manipulate
    public List<String> generateAbbreviations2(String word) {
        List<String> ret = new ArrayList<>();
        int n = word.length();
        // the position of the number is actually the bit position of the mask
        // moving the mask from 0 to 2^n, which mean the bit could cover all in the 2^n
        // every time we move mask, check the succeed mask bit and count them
        // every time we meet 0, append count (then clear count) and append char.
        for(int mask = 0;mask < (1 << n);mask++) {
            int count = 0;
            StringBuffer sb = new StringBuffer();
            for(int i = 0;i <= n;i++) {
                if(((1 << i) & mask) > 0) {
                    count++;
                } else {
                    if(count != 0) {
                        sb.append(count);
                        count = 0;
                    }
                    if(i < n) sb.append(word.charAt(i));
                }
            }
            ret.add(sb.toString());
        }
        return ret;
    }
    
    
	//342 Power of Four
    public boolean isPowerOfFour1(int n) {
        int bitCount = 0;
        int checker = 0x55555554;
        for (int bit = 0 ; bit < 32; bit++ ) bitCount += (n>>bit) & 1;
        return  (bitCount == 1 && n > 0 &&  (n & checker) == n || n ==1) ;
    } // check there is noly 1 bit using num&(num-1) ==0!!!!!
    //using compare check !!!!

        public boolean isPowerOfFour(int num) {
              return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) != 0;
        }
        
        //371. Sum of Two Integers toooooo freaking genius
     // Recursive
        public int getSum(int a, int b) {
        	return (b == 0) ? a : getSum(a ^ b, (a & b) << 1);
        }

        // Recursive
        public int getSubtract(int a, int b) {
        	return (b == 0) ? a : getSubtract(a ^ b, (~a & b) << 1);
        }

        // Get negative number
        public int negate(int x) {
        	return ~x + 1;
        }
        public int getSum2(int a, int b) {
        	if (a == 0) return b;
        	if (b == 0) return a;

        	while (b != 0) {
        		int carry = a & b;
        		a = a ^ b;
        		b = carry << 1;
        	}
        	
        	return a;
        }

        // Iterative
        public int getSubtract2(int a, int b) {
        	while (b != 0) {
        		int borrow = (~a) & b;
        		a = a ^ b;
        		b = borrow << 1;
        	}
        	
        	return a;
        }
        
        
        
        //405. Convert a Number to Hexadecimal
        // we need to know that the >> arithmetical shift and >>> logical shift
        public String toHex(int num) {
            if (num == 0) return "0";
            
            char[] hash=new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuilder sb = new StringBuilder(); 
            while(num != 0){
                char c = hash[num & 15];
                sb.append(c);
                num >>>= 4;
            }
            return sb.reverse().toString();
        }
        
        //421. Maximum XOR of Two Numbers in an Array
        //firstly every time you come acrossed a bit problem
        //you have take the advantage of the 32 bits length
        //to make it a less time complexity
        //this one gradually finding the max one bit by one bit
        // but you have to use a mask to make sure that 
        // the max wont miss any "sub max" which it contains
        public int findMaximumXOR(int[] nums) {
            int ans = 0, mask = 0;
            for (int x = 31; x >= 0; x--) {
                mask |= (1 << x);
                HashSet set = new HashSet();
                int temp = ans | (1 << x);
                for (int i = 0; i < nums.length; i++) {
                    int num = nums[i] & mask;
                    if (set.contains(temp ^ num)) {
                        ans = temp;
                        break;
                    }
                    set.add(num);
                }
            }
            
            return ans;
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
    
    //477. Total Hamming Distance
 
    //using bit set
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
    
    //762. Prime Number of Set Bits in Binary Representation

    public int countPrimeSetBits(int L, int R) {
        Set<Integer> primes = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19 , 23, 29));
        int ans = 0;
        for(int i = L; i <= R; i++){
            int bit = 0;
            for(int n=i; n>0; n>>=1){
                bit+=n & 1;
            }
            if(primes.contains(bit))ans++;
        }
        return ans;
    }
	
}
