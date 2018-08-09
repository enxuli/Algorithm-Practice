import java.util.ArrayList;
import java.util.Arrays;
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
    
}
