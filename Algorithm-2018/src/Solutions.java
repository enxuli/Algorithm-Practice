import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;
import java.util.Random;
import java.util.Stack;
import java.util.Collections;

public class Solutions {
    public int sol611(int[] nums) {
    	Arrays.sort(nums);
    	int ans = 0;
    	for(int i = 0; i < nums.length - 2; i++)
    		for(int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++)
    			for(int k = j + 1; k<nums.length && nums[i] + nums[j] > nums[k];k++){
    				ans++;//this can be change to while to reduce running time
    			}
    	return ans;
    }
    public String sol592(String expression) {
        List < Character > sign = new ArrayList < > ();
        if (expression.charAt(0) != '-')
            sign.add('+');//add + at beginning 
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-')
                sign.add(expression.charAt(i));
        }
        int prev_num = 0, prev_den = 1, i = 0;
        for (String sub: expression.split("(\\+)|(-)")) {
            if (sub.length() > 0) {
                String[] fraction = sub.split("/");
                int num = (Integer.parseInt(fraction[0]));
                int den = (Integer.parseInt(fraction[1]));
                int g = Math.abs(gcd(den, prev_den));
                if (sign.get(i++) == '+')
                    prev_num = prev_num * den / g + num * prev_den / g;
                else
                    prev_num = prev_num * den / g - num * prev_den / g;
                prev_den = den * prev_den / g;
                g = Math.abs(gcd(prev_den, prev_num));
                prev_num /= g;
                prev_den /= g;
            }
        }
        return prev_num + "/" + prev_den;
    }
    static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
    
    public List<List<Integer>> sol15(int[] a) {
	    List<List<Integer>> sol = new ArrayList <>();
		
		Arrays.sort(a);
		int[] zero = new int[2]; 
		for (int i = 0; i<a.length-1; i++ ) {
			if(a[i] != 0 && a[i+1] == 0) zero[0]=i+1;
			if(a[i] == 0 && a[i+1] != 0) zero[1]=i;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = zero[1]+1; i< a.length; i++) {
			for (int j = 0; j<= zero[0]; j++) {
			int complement = -a[j] - a[i];
			if (map.containsKey(complement)) {
				List<Integer> tmp =  new ArrayList <>();
				tmp.add(a[j]);
				tmp.add(a[i]);
		        tmp.add(a[map.get(complement)]);
		        if(!sol.contains(tmp)) {
		        	sol.add(tmp);
		        }
		    }
		    map.put(a[j], j);
			}
			map.put(a[i], i);
		}
		return sol;
	}
    //15 should convert 3sum to 2sum then find the answer, fix the first num then found the other two
    public List<List<Integer>> sol15p(int[] a) {
        List<List<Integer>> sol = new ArrayList <>();
		Arrays.sort(a);
		int i = 0, j = a.length-1, k = 0, flag = 0;
		while(i<=j){
			int complement = -a[j] - a[i];
		    for(k = i+1; k<j ; k++){
			if (a[k]==complement) {
		        List<Integer> tmp =  new ArrayList <>();
				tmp.add(a[i]);
		        tmp.add(a[k]);
		        tmp.add(a[j]);
		        if(!sol.contains(tmp)) {
		        	sol.add(tmp);
		        }
		        flag = 1;
		    }
		    }
		    if(flag==0) {
		    if(a[i]+a[j]+a[k]<0) i ++;
		    if(a[i]+a[j]+a[i+1]>0) j--;
		    }else {
		    	i++;
		    	j++;
		    }
		}
		return sol;
		}
    
    // may have to use recursion to get things done but you can easily use stack!!
    public boolean sol20(String input20) {
    	int start = 0;
    	System.out.println(input20.length());
    	while(start<input20.length()) {
    		int i =start+1;
        	for(; i < input20.length()+1; i ++) {
        		System.out.println(input20.substring(start, i));
        		if(isSubValid(input20.substring(start, i))) {
        			start = i;
        			break;
        		}
        	}
        	if(start != i) return false;
    	}
    	return true;
    }
    
    public boolean isSubValid(String input20) {
		int i = 0, j = input20.length()-1;
		while(i<j) {
			if(ifClose(input20.charAt(i),input20.charAt(i+1))){
				i+=2;
			}else if(ifClose(input20.charAt(j-1),input20.charAt(j))){
				j-=2;
			}else if(ifClose(input20.charAt(i),input20.charAt(j))){
				i++;
				j--;
			}else return false;
		}
        if(i==j){return false;}
        else return true;
	}
    private boolean ifClose(char a, char b) {
    	switch (a) {
    	case '{' : if (b=='}') {return true;} else return false;
    	case '[' : if (b==']') {return true;} else return false;
    	case '(' : if (b==')') {return true;} else return false;
    	default: return false;
    	}
    }
    public boolean sol20p(String input20) {
    	Stack<Character> stack = new Stack<Character>();
    	for(char c : input20.toCharArray()) {
    		if (c == '(') stack.push(')');
    		else if (c == '[') stack.push(']');
    		else if (c == '{') stack.push('}');
    		else if (stack.isEmpty() || stack.pop()!=c) return false;
    	}
    	return stack.isEmpty();
    }
    
    
}
