
public class Math {

	
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
	
}
