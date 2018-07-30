
public class Math2 {

	//7. Reverse Integer
    public int reverse(int x) {
        int digits = x;
        int result = 0;
        
        while(digits!=0){
            int tail = digits%10;
            result = result*10 + tail;
            if(result%10 != tail) return 0;
            digits/=10;
        }
        return result;
        
    }
    
	
	//264. Ugly Number II brute force
    public int nthUglyNumber(int n) {
        int count = 0;
        int num = 1;
        while (count !=n){
            if(isUgly(num))count ++;
            num++;
        }
        return num-1;
    }
    private boolean isUgly( int num){
        if (num ==1) return true;
        if(num%2==0) return isUgly(num/2);
        if(num%3==0) return isUgly(num/3);
        if(num%5==0) return isUgly(num/5);
        return false;
    }
    //264
    public int nthUglyNumber2(int n) {
        int index2 =0, index3 = 0 , index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;

        int[] ugly = new int[n];
        ugly[0] = 1;
        for(int i = 1 ; i < n; i++){
            int min = Math.min(Math.min(factor2,factor3),factor5);
            ugly[i] = min;
            if(min == factor2) factor2 = 2* ugly[++index2];
            if(min == factor3) factor3 = 3* ugly[++index3];
            if(min == factor5) factor5 = 5* ugly[++index5];
        }
        return ugly[n-1];
    }
	
}
