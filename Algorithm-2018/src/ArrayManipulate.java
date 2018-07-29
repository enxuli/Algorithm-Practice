
public class ArrayManipulate {
	
	//66. Plus One  
	//Remember !! never convert back to int if it's not orinally int!!!
    public int[] plusOne(int[] digits) {
        int c = 0, tmp = 0;
        int length = digits.length-1;
        
        for(int i = digits.length-1 ; i >=0; i--){
            tmp = digits[i];
            digits[i] = (tmp + c + (i==length? 1 : 0)) % 10;
            c = (tmp + c + (i==length? 1 : 0))/10;
        }
        if( c == 1) {
            int[] ans = new int[digits.length+1];
            ans[0] = 1;
            System.arraycopy(digits, 0, ans, 1, digits.length);
            return ans;
                
        }
        else return digits;
    }

}
