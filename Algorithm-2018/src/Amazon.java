import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Amazon {

	//819. Most Common Word

    public String mostCommonWord(String paragraph, String[] banned) {
        // remember the regex and the split regex(multiple spaces)!!! 
        String[] words = paragraph.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        // but this is quite time comsumning we could just one pass the string!!
        //String[] words = paragraph.split("[!?',;.\\s]");
        //directly use the split and the regex should be in the []!!!
        HashSet<String> set = new HashSet<>(Arrays.asList(banned));
        HashMap<String, Integer> frequency = new HashMap<>();
        int max = 0;
        String ans = "";
        //burte force O(n) space O(n)
        for(String str : words){
            frequency.put(str,frequency.getOrDefault(str,0)+1);
            if(frequency.get(str)>=max&&!set.contains(str)){max = frequency.get(str); ans = str;}
        }
        return ans;
    }
}
