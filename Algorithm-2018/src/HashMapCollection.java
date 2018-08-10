import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class HashMapCollection {

	  class Point {
	      int x;
	      int y;
	      Point() { x = 0; y = 0; }
	      Point(int a, int b) { x = a; y = b; }
	  }
	//49. Group Anagrams
	  //using sort to rearrage the string to only one order!!!!!!!!!!!!!
	  public List<List<String>> groupAnagrams1(String[] strs) {
	        HashMap<String, Integer> anagram = new HashMap<String, Integer>();
	        List<List<String>> result = new ArrayList<List<String>>();
	        int i = 0;
	        for(String e: strs){
	            char[] temp = e.toCharArray();
	            Arrays.sort(temp);
	            String b = new String(temp);
	            if (!anagram.containsKey(b)){
	                anagram.put(b, i);
	                result.add(new ArrayList<String>() {{add(e);}} );
	                i++;
	            } else {
	                int index = anagram.get(b);
	                result.get(index).add(e);
	            }
	        }
	        
	        return result;
	    }
	  
	  
	  // without sort! using the computeIfAbsent!
	    public List<List<String>> groupAnagrams(String[] strs) {
	        Map<Map<Character, Integer>, List<String>> map = new HashMap<>();
	        for (String str : strs) {
	            Map<Character, Integer> count = new HashMap<>();
	            for (char chr : str.toCharArray()) {
	                count.put(chr, count.getOrDefault(chr, 0)+1);
	            }
	            map.computeIfAbsent(count, k -> new ArrayList<>()).add(str);
	        }
	        return map.values().stream()
	            .collect(java.util.stream.Collectors.toList());
	    }
	//149. Max Points on a Line

    public int maxPoints(Point[] points) {
        if (points.length == 0 || points == null) return 0;
        int max = 0;
        HashMap<Point,Integer> pointmap = new HashMap<>();
        for(Point p : points) {
            int flag = 0;
            for(Point src : pointmap.keySet()){
                if(src.x==p.x&&src.y==p.y) {flag = 1;pointmap.put(src,pointmap.get(src)+1);}
            }
            if(flag==0) pointmap.put(p,1);
        }
        
        for(Point src : pointmap.keySet()){
            System.out.println("src:["+src.x+","+src.y+"]");
            HashMap<String,Integer> nummap = new HashMap<>();
            for(Point des : pointmap.keySet()){
                String slope = "vertical";
                int deltaX = src.x-des.x;
                int deltaY = src.y-des.y;
                int gcd = generateGCD(deltaX,deltaY);
                if(gcd!=0){deltaX/=gcd;deltaY/=gcd;}
                if (deltaX==0&&deltaY==0) continue;
                else if(deltaX!=0) slope = deltaY+","+deltaX;
                nummap.put(slope,nummap.getOrDefault(slope,pointmap.get(src))+pointmap.get(des));
                //System.out.println("des:["+des.x+","+des.y+"]"+"slope:"+slope+"number:"+nummap.get(slope));
            }
            max= Math.max(max,pointmap.get(src));
            //System.out.println("max:"+max+","+pointmap.get(new Point(0,0)));
            for(int x : nummap.values()){
                max= Math.max(max,x);
            }
        }
        return max;
    }
        private int generateGCD(int a,int b){
        	if (b==0) return a;
        	else return generateGCD(b,a%b);
        	
        }
        //170. Two Sum III - Data structure design
        // use a map to record all the data and it's number
        // then value - j = i , j and i both in the map
        // if i == j then number >1, if i != j both contains;
        private HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        public void add(int number) {
            map.put(number, map.containsKey(number) ? map.get(number) + 1 : 1);
        }

        public boolean find(int value) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int i = entry.getKey();
                int j = value - i;
                if ((i == j && entry.getValue() > 1) || (i != j && map.containsKey(j))) {
                    return true;
                }
            }
            return false;
        }
        
        //187. Repeated DNA Sequences
        // if there can not be any overlaping use map
        // if there can be just use map!
        public List<String> findRepeatedDnaSequences(String s) {
            HashMap <String, Integer> map = new HashMap<>();
            HashSet <String> ans = new HashSet<>();
            for(int i = 0; i <= s.length()-10; i++){
                String str = s.substring(i,i+10);
                if(!map.containsKey(str)) map.put(str,i);
                else ans.add(str);
            }
            return new ArrayList<String>(ans);
        }
        
        //205. Isomorphic Strings
        // this is rather straightforward but you can use map to record both 
        //alphabet's postion to check if their occur on the same position last time.
        public boolean isIsomorphic(String s, String t) {
            char[] src = new char[256];
            char[] tar = new char[256];
            char[] sch = s.toCharArray();
            char[] tch = t.toCharArray();
            for(int i = 0 ; i < s.length(); i++){
                if(src[sch[i]]==0) {src[sch[i]]=tch[i];}
                if(tar[tch[i]]==0) {tar[tch[i]]=sch[i];}
                if(src[sch[i]]!=tch[i]||tar[tch[i]]!=sch[i]) return false;
            }
            return true;
        }
        //274. H-Index
        //use built in sorting !
        public int hIndex(int[] citations) {
            Arrays.sort(citations);
            for(int i = 0; i<citations.length; i ++){
                if(citations[i]>=citations.length-i) return citations.length-i;
            }
            return 0;
        }
        //use bucket sorting!
        //some how amazingly O(n)
        public int hIndex1(int[] citations) {
            int n = citations.length;
            int[] buckets = new int[n+1];
            for(int c : citations) {
                if(c >= n) {
                    buckets[n]++;
                } else {
                    buckets[c]++;
                }
            }
            int count = 0;
            for(int i = n; i >= 0; i--) {
                count += buckets[i];
                if(count >= i) {
                    return i;
                }
            }
            return 0;
        }

	//290. Word Pattern！！  using string[] str.split('')!!!
	 public boolean wordPattern(String pattern, String str) {
	        String[] strs = str.split(" ");
	        if (strs.length != pattern.length()) return false;
	        HashMap<Character,String> map = new HashMap<Character,String>();
	        for (int i = 0; i < strs.length; i++){
	            char c = pattern.charAt(i);
	            if (!map.containsKey(c)){
	                if (map.containsValue(strs[i])) return false;
	                map.put(c, strs[i]);
	            }else{
	                if (!map.get(c).equals(strs[i])) return false;
	            }
	        }
	            return true;
	    }
	 
	 //299. Bulls and Cows
	 // learn to use accumulation when doing something seen or unseen!!!!
	    public String getHint(String secret, String guess) {
	        int[] map = new int[10];
	        int bull = 0, cow =0;
	        char[]s = secret.toCharArray();
	        char[]g = guess.toCharArray();
	        for(int i = 0 ; i < s.length ; i++) {
	            if(s[i]==g[i]) bull ++;
	            else{
	                if(map[s[i]-'0']++ < 0 ) cow ++;
	                if(map[g[i]-'0']-- > 0 ) cow ++;
	            }
	        }

	        return bull+"A"+cow+"B";
	    }
	 
	 //347. Top K Frequent Elements
	 	// using map to store the frquency 
	 //then using entry to put key into a list array and value to its index!!!
	 //very smart!
	 //this is called bucket Sort !!!! first time to hear
	 //because we already know that the range would exceed the nums.length so we take the advantage!!
	    public List<Integer> topKFrequent(int[] nums, int k) {
	        HashMap<Integer, Integer> frequecy = new HashMap<>();
	        for(int num : nums){
	            frequecy.put(num, frequecy.getOrDefault(num, 0) + 1);
	        }
	        ArrayList<Integer>[] bucket = new ArrayList[nums.length + 1];
	        for(Map.Entry<Integer, Integer> entry : frequecy.entrySet()){
	            if(bucket[entry.getValue()] == null){
	                bucket[entry.getValue()] = new ArrayList<>();
	            }
	            bucket[entry.getValue()].add(entry.getKey());
	        }
	        List<Integer> topk = new ArrayList<>();
	        for(int i = nums.length ; i >= 0 && topk.size()< k; i--){
	            if(bucket[i] != null){
	                topk.addAll(bucket[i]) ;
	            }
	        }
	        return topk;
	    }
	    
	    // or you can just create a new comparator, but the sort is still NlogN
	    //
	    public List<Integer> topKFrequent2(int[] nums, int k) {
	        HashMap <Integer,Integer> map = new HashMap<>();
	        List<Integer> ans = new ArrayList<>();
	        List<HashMap.Entry<Integer,Integer>> list = new ArrayList<>();
	        for(int x : nums)map.put(x,map.getOrDefault(x,0)+1);
	        
	        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
	             list.add(entry);
	        }

	        list.sort(new Comparator<Map.Entry<Integer, Integer>>(){
	               public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
	                    return b.getValue()-a.getValue();} 
	        }); 
	        
	        for(int i = 0; i < k ; i++){
	              ans.add(list.get(i).getKey());
	        }
	        return ans;
	    }
	    
	    //389. Find the Difference
	    // do not forget the bit manipulation !!!! only when there is only one difference
	    //we can simply use XOR!!! to every thing!!!  a^=i!!!!!
	    // finally there will only be the added difference!
	    public char findTheDifference(String s, String t) {
	        int[] map = new int[26];
	        int ans = 0;
	        char[] sch = s.toCharArray();
	        char[] tch = t.toCharArray();
	        for(int i = 0 ; i < tch.length; i++){
	            if(i<sch.length) map[sch[i]-'a']++;
	            map[tch[i]-'a']--;
	        }
	        for(int i = 0; i < 26; i++ ){
	            if(map[i]==-1) ans = i;
	        }
	        return (char) ('a'+ans);
	    }
	    //
	    public char findTheDifference2(String s, String t) {
	    	int n = t.length();
	    	char c = t.charAt(n - 1);
	    	for (int i = 0; i < n - 1; ++i) {
	    		c ^= s.charAt(i);
	    		c ^= t.charAt(i);
	    	}
	    	return c;
	    }
	    
	 //409. Longest Palindrome

	    public int longestPalindrome(String s) {
	        int[] hash = new int[256];
	        char[] str = s.toCharArray();
	        int result = 0;
	        int odd = 0;
	        for(int i = 0; i < s.length();i++){
	            hash[str[i]]++;
	        }
	        for(int i : hash){
	            if(i%2==0)result+=i;
	            else {odd = 1;result+=i-1;}
	        }
	        return result+odd;
	    }
	    
	    //438. Find All Anagrams in a String

	    
	    
	    //500. Keyboard Row
	    char[][] ch = {{'Q','W','E','R','T','Y','U','I','O','P'},{'A','S','D','F','G','H','J','K','L'},{'Z','X','C','V','B','N','M'}};
	    public String[] findWords(String[] words) {
	        HashMap<Character,Integer> map = new HashMap<>();
	        for(int i = 0; i < ch.length;i++){
	            for(int j = 0; j< ch[i].length;j++){
	                map.put(ch[i][j],i);
	            }
	        }
	        List<String> ans = new ArrayList<>();
	        for(String str : words){
	            char[] tmp = str.toCharArray();
	            int row = map.get(Character.toUpperCase(tmp[0]));
	            boolean inSameRow = true;
	            for(char inner : tmp){
	                if(map.get(Character.toUpperCase(inner))!=row){inSameRow=false;break;}
	            }
	            if(!inSameRow)continue;
	            ans.add(str);
	        }
	        return ans.toArray(new String[ans.size()]);
	    }
	    //535. Encode and Decode TinyURL
	    // need to learn how to use hashCode!!!! Integer!
	    Map<Integer, String> map = new HashMap<>();
	    public String encode(String longUrl) {
	        map.put(longUrl.hashCode(),longUrl);
	        return "http://tinyurl.com/"+longUrl.hashCode();
	    }
	    public String decode(String shortUrl) {
	        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
	    }  
	 
	    //560. Subarray Sum Equals K
	    // brute force n^2 too simple
	    public int subarraySum(int[] nums, int k) {
	        int ans = 0;
	        for(int i = 0 ; i < nums.length; i++){
	            int sum = 0;
	            for(int j = i ; j < nums.length; j++){
	                sum += nums[j];
	                if (sum == k ) ans++;
	            }
	        }
	        return ans;
	    }
	    // use a preSum to sum up the accumulate sum
	    // sum[i,j] would be sum[0,j]- sum[0,i-1] = k !!!!
	    // so we just need to see if the sum[0,i-1] is in the map!!!
	    public int subarraySum2(int[] nums, int k) {
	        int ans = 0, sum = 0;
	        HashMap<Integer,Integer> preSum = new HashMap<>();
	        preSum.put(0,1);
	        for(int i = 0 ; i < nums.length; i++){
	            sum+=nums[i];
	            if(preSum.containsKey(sum - k)){
	                ans += preSum.get(sum - k);
	            }
	            preSum.put(sum, preSum.getOrDefault(sum,0)+1);
	        }
	        return ans;
	    }
	    //575. Distribute Candies

	    public int distributeCandies(int[] candies) {
	        HashSet<Integer> set = new HashSet<>();
	        int unique = 0;
	        for(int i : candies) if(!set.contains(i)){set.add(i);unique++;}
	        return unique>=(candies.length/2)? (candies.length/2):unique;
	    }
	    
	//599. Minimum Index Sum of Two Lists
    public String[] findRestaurant(String[] list1, String[] list2) {
        int min = Integer.MAX_VALUE;
        List<String> ans = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        
        for(int i = 0 ; i < list1.length; i ++){
            map.put(list1[i], i);
        }
        for(int j = 0 ; j < list2.length; j++){
            if(map.containsKey(list2[j])){
                if(j+map.get(list2[j])==min){
                    ans.add(list2[j]);
                }
                else if(j+map.get(list2[j])<min){
                    min = j+map.get(list2[j]);
                    ans.clear();
                    ans.add(list2[j]);
                }
            }
        }
        return ans.toArray(new String[ans.size()]);
    }
    //739. Daily Temperatures
    // nice implement of how to take the advantage of the range of the data!!!
    // some sort of bucket sort thinking!!
    public int[] dailyTemperatures(int[] temp) {
        //brute force
        return opt(temp);
    }
    public int[] bruteforce(int[] temp){
       //brute force
        int[] ans = new int[temp.length];
        for(int i = 0 ; i < temp.length; i ++){
            for(int j = i + 1 ; j < temp.length; j++){
                if(temp[j]>temp[i]){ans[i]=j-i; break;}
            }
        }
        return ans;
    }
    public int[] opt(int[] temp){
        int[] map = new int [101];
        int[] ans = new int[temp.length];
        Arrays.fill(ans,Integer.MAX_VALUE);
        ans[temp.length-1] = 0; 
        for(int i = temp.length-1; i >=0; i--){
            map[temp[i]]=i;
            for(int j = temp[i]+1;j<=100;j++){

                if(map[j]!=0) {ans[i] = Math.min(ans[i],map[j]-i);}
                
            }
            ans[i] = ans[i]==Integer.MAX_VALUE?0:ans[i];
        }
        return ans;
    }
    
    //760. Find Anagram Mappings // using maps and sets!
    public int[] anagramMappings(int[] A, int[] B) {
        HashMap<Integer,List<Integer>> order = new HashMap<>();
        HashSet<Integer> usedIndex = new HashSet<>();
        int[] ans = new int[A.length];
        for(int i= 0; i < B.length;i++){
            if(!order.containsKey(B[i])){
                List<Integer> list = new ArrayList<>();
                list.add(i);
                order.put(B[i],list);
            }else{
                order.get(B[i]).add(i);
            }
        }
        for(int j=0;j< A.length;j++){
            int i = 0;
            while(usedIndex.contains(order.get(A[j]).get(i))){
                i++;
            }
            usedIndex.add(order.get(A[j]).get(i));
            ans[j]=order.get(A[j]).get(i);
        }
        return ans;
    }
    // using stack to avoid sets!!! nice idea!
    public int[] anagramMappings2(int[] A, int[] B) {
        HashMap<Integer,Stack<Integer>> order = new HashMap<>();
        int[] ans = new int[A.length];
        for(int i= 0; i < B.length;i++){
            if(!order.containsKey(B[i])){
                Stack<Integer> stack = new Stack<>();
                stack.push(i);
                order.put(B[i],stack);
            }else{
                order.get(B[i]).push(i);
            }
        }
        for(int j=0;j< A.length;j++){
            ans[j]=order.get(A[j]).pop();
        }
        return ans;
    }
    
    
    //811. Subdomain Visit Count

    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String,Integer> map = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for(int i = 0 ; i < cpdomains.length; i ++){
            String[] str= cpdomains[i].split(" ");
            int number = Integer.valueOf(str[0]);
            map.put(str[1],map.getOrDefault(str[1],0)+number);
            for(int j = 0; j<str[1].length(); j ++){
                if(str[1].charAt(j)=='.') {
                    map.put(str[1].substring(j+1,str[1].length()),map.getOrDefault(str[1].substring(j+1,str[1].length()),0)+number);
                }
            }
        }
        for(HashMap.Entry<String,Integer> entry : map.entrySet()){
            ans.add(entry.getValue()+" "+entry.getKey());
        }
        return ans;
        
    }
}
