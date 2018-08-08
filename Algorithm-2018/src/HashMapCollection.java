import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HashMapCollection {

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
    
    
}
