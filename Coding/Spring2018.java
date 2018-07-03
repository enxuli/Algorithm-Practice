import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;
import java.util.Random;
import java.util.Collections;

public class Spring2018 {
  public static void main(String[] args){

	//697
	System.out.println("697 result:");
	int[] nums={1,2,2,3,1,4,2};
	//int output=arrayDegree(nums);
	//System.out.println(output);
	//int output2=findShortestSubArray(nums);
	//System.out.println(output2);
	//611
	int[] nums611={2,2,3,4};
	System.out.println("611 result:");
    //int output611 =sol611(nums611);
    //System.out.println(output611);
    //592
    System.out.println("592 result:");
    String nums592 = "-1/2+1/2";
    //String output592 =sol592(nums592);
    //System.out.println(output592);
    //454
    System.out.println("454 result:");
    int[] a454 ={1,-1,-2,0};
    int[] b454 ={2,-1,0,1};
    int[] c454 ={2,-2,-1,0};
    int[] d454 ={-1,3,0,2};
    //int output454 = sol454(a454,b454,c454,d454);
    //System.out.println(output454);

    //412
    System.out.println("412 result:");
    //String[] output412 = sol412(15);
    //for(int i = 0; i <output412.length;i++)
    //System.out.println(output412[i]);

    //398
    System.out.println("398 result:");
    //395
    System.out.println("395 result:");
    String a395 = "aaabbb";
    int n395 = 3;
    //int output395 = sol395(a395,n395);
    //System.out.println(output395);
    //387
    System.out.println("387 result:");
    //String a387 = "leetcode";
    //int output387 = sol387(a387);
    //System.out.println(output387);
    //382
    System.out.println("382 result:");
    //Reservoir Sampling !!!! important for the infinite long data set!!!
    //The n-1 node possibility is (1/n-1)*(n-1/n)=1/n ,equal to the n node's possibility 1/n,So you can conclude that every node's possibility is 1/n.
    int output382 = sol382();
    System.out.println(output382);
  }

//brute force would have bug when same degree but not the smallest O(n^2)
static int arrayDegree(int[] nums){
	int[] table = new int[50000];
    Arrays.fill(table,-1);
    int max = 0;
    int[] maxIdx = new int[50000];
	for (int i=0;i<nums.length;i++){
		if(table[nums[i]] != -1){
			table[nums[i]]++;
		}else{
			table[nums[i]]=1;
		}
		if(table[nums[i]]>=max){
				max = table[nums[i]];
		}
	}
	int j=0;
	for (int i=0;i<table.length;i++){
		if(table[i]==max){
			maxIdx[j++]=i;
		}
	}
	int min=50000;
    for (int k=0;k<j;k++){
	    int left=0;
		int right=0;
		for(int i=0;i<nums.length;i++){
			if(nums[i]==maxIdx[k]) {left = i;break;}
		}
		for(int n=nums.length-1;n>0;n--){
			if(nums[n]==maxIdx[k]) {right = n;break;}
		}
		if(right-left+1<min) min = right-left+1;
	}
	return min;
}
//all store into hashmap count and the left and right, 
// by using .get(), .put(), .containsKey(), .keySet(), values(),.getOrDefault(x, 0)
// remember: Map<key,value>!!!
    static int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> left = new HashMap(),
            right = new HashMap(), count = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (left.get(x) == null) left.put(x, i);
            right.put(x, i);
            count.put(x, count.getOrDefault(x, 0) + 1);
        }

        int ans = nums.length;
        int degree = Collections.max(count.values());
        for (int x: count.keySet()) {
            if (count.get(x) == degree) {
                ans = Math.min(ans, right.get(x) - left.get(x) + 1);
            }
        }
        return ans;
    }
//linear search , make j and k less
    static int sol611(int[] nums) {
    	Arrays.sort(nums);
    	int ans = 0;
    	for(int i = 0; i < nums.length - 2; i++)
    		for(int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++)
    			for(int k = j + 1; k<nums.length && nums[i] + nums[j] > nums[k];k++){
    				ans++;//this can be change to while to reduce running time
    				System.out.println(nums[i] +"+" +nums[j] +">"+ nums[k]);
    			}
    	return ans;
    }

//592
// String : .charAt(), for(String x : str.split("+")) or String[] x = str.split("+"),
    //List : .add()
    static String sol592(String expression) {
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
//454 
// fully used the great of  data = map.getOrDefault(x,0)+1 x= key, 0 = data
	static int sol454(int[]a,int[]b,int[]c,int[]d){
		Map <Integer,Integer> sum1 = new HashMap(),sum2 = new HashMap();
        int ans = 0;
		for(int i =0; i < a.length ;i++)
			for(int j =0; j < b.length ;j++){
            	sum1.put(a[i]+b[j],sum1.getOrDefault(a[i]+b[j],0)+1);
			}
		
		for(int n =0; n < c.length ;n++)
			for(int  m=0; m < d.length ;m++){
            	ans += sum1.getOrDefault(-c[n]-d[m],0);
			}

		return ans;
	}
	
//412 too freaking simple
	static String[] sol412(int nums){
		String[] ans = new String[nums];
		for (int i = 0; i < nums ; i++){
			if ((i+1)%15 == 0){
				ans[i]="FizzBuzz";
			}else if ((i+1)%5 == 0){
				ans[i]="Buzz";
			}else if ((i+1)%3 == 0){
				ans[i]="Fizz";
			}else{
				ans[i]=Integer.toString(i+1);
			}
		}
		return ans;
	}
//412 list version  the point is that initialize the list need to use ArrayList<String>()
//; LinkedList<String>();Vector<String>(); is also common, next we can use LinkedList to do things!
	static List<String> sol412List(int nums){
		List<String> ans = new ArrayList<String>();
		for (int i = 0; i < nums ; i++){
			if ((i+1)%15 == 0){
				ans.add("FizzBuzz");
			}else if ((i+1)%5 == 0){
				ans.add("Buzz");
			}else if ((i+1)%3 == 0){
				ans.add("Fizz");
			}else{
				ans.add(Integer.toString(i+1));
			}
		}
		return ans;
	}
//398 use map to build table    using Math.random()
    //public int[] a;
    //public Solution(int[] nums) {
    //this.a=nums;
    //}
    //public int pick(int target) {
    //    double d = Math.random();
    //    int step = (int)(d*a.length);
    //    while(a[step]!=target){
    //        double k = Math.random();
    //        step = (int)(k*a.length);
    //    }
    //    return step;
    //}

//395 
	//this is convenient but would it exceeds the time limit!!!!
	public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = new char[26];
        // record the frequency of each character
        for (int i = 0; i < s.length(); i ++) chars[s.charAt(i) - 'a'] +=1;
        boolean flag = true;
    	int result = 0;
        for (int i = 0; i < s.length(); i ++) {
            if (chars[s.charAt(i) - 'a'] < k && chars[s.charAt(i) - 'a'] > 0) {
            	flag = false;
            	String[] b = s.split(Character.toString(s.charAt(i)));
            for (int j = 0; j < b.length; j ++) {
            result = Math.max(result, longestSubstring(b[j], k));
        }
        }
        }
        // return the length of string if this string is a valid string
        if (flag == true) return s.length();
		return result;
    }
//395 two pointer!!
    public int longestSubstring2(String s, int k) {
        char[] str = s.toCharArray();
        int[] counts = new int[26];
        int h, i, j, idx, max = 0, unique, noLessThanK;
        
        for (h = 1; h <= 26; h++) {
            Arrays.fill(counts, 0);
            i = 0; 
            j = 0;
            unique = 0;
            noLessThanK = 0;
            while (j < str.length) {
                if (unique <= h) {
                    idx = str[j] - 'a';
                    if (counts[idx] == 0)
                        unique++;
                    counts[idx]++;
                    if (counts[idx] == k)
                        noLessThanK++;
                    j++;
                }
                else {
                    idx = str[i] - 'a';
                    if (counts[idx] == k)
                        noLessThanK--;
                    counts[idx]--;
                    if (counts[idx] == 0)
                        unique--;
                    i++;
                }
                if (unique == h && unique == noLessThanK)
                    max = Math.max(j - i, max);
            }
        }
        
        return max;
    }


//395 brilliant way!!! Straight forward!!!
	static int sol395(String a, int n){
        char[] chars = new char[26];
        String toSplit = "";
        for (int i = 0; i < a.length(); i ++) chars[a.charAt(i) - 'a'] +=1;
    	int result = 0;
        for (int i = 0; i < a.length(); i ++) {
            if (chars[a.charAt(i) - 'a'] < n && chars[a.charAt(i) - 'a'] > 0) {
            	toSplit += (Character.toString(a.charAt(i)) +"|");
        	}
        }
        if(toSplit!=""){
        toSplit = toSplit.substring(0,toSplit.length()-1);
        String[] b = a.split(toSplit);
        int i = 0;
        while(i < b.length) {
        	result = Math.max(result, sol395(b[i++],n));
        }
		return result;
		}else{
			return a.length();
		}
	}

//387 quick think: how can it be faster? the space is much higher than the last one.
	static int sol387(String s){
		Map <Character,Integer> map = new HashMap();
		for (int i = 0; i < s.length();i++)
			map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
		for (int i = 0; i < s.length();i++){
			if (map.get(s.charAt(i))==1)
				return i;  	
		}
		return -1;
	}
//387 better: actually you dont have to use hash map： just use "freq [s.charAt(i) - 'a'] ++"
	 public int firstUniqChar(String s) {
        int freq [] = new int[26];
        for(int i = 0; i < s.length(); i ++)
            freq [s.charAt(i) - 'a'] ++;
        for(int i = 0; i < s.length(); i ++)
            if(freq [s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }
//382
    //reservoir Sampling :  if (rand.nextInt(i+1)=i) action; that means P(n-1) = 1/n-1 *(n-1)/n 
    static int sol382() {
        Random rand = new Random();
            int r = rand.nextInt(10);
        return r;
    }
//end    
}

