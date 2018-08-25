import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bucket {
	//347. Top K Frequent Elements
	public List<Integer> topKFrequent(int[] nums, int k) {

		List<Integer>[] bucket = new List[nums.length + 1];
		Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

		for (int n : nums) {
			frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
		}

		for (int key : frequencyMap.keySet()) {
			int frequency = frequencyMap.get(key);
			if (bucket[frequency] == null) {
				bucket[frequency] = new ArrayList<>();
			}
			bucket[frequency].add(key);
		}

		List<Integer> res = new ArrayList<>();

		for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
			if (bucket[pos] != null) {
				res.addAll(bucket[pos]);
			}
		}
		return res;
	}
	
	//692. Top K Frequent Words
    public List<String> topKFrequent(String[] words, int k) {
        //Intuition nlog(n)
        int n = words.length;
        Arrays.sort(words);
        //System.out.println(Arrays.toString(words));
        LinkedList<String>[] bucket = new LinkedList[n];
        List<String> ans = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int start = i;
            while(i < n-1 && words[i+1].equals( words[i])) i++;
            if(bucket[i - start + 1] == null) bucket[i - start + 1] = new LinkedList<String>(Arrays.asList(words[i]));
            else bucket[i - start + 1].add(words[i]);
        }
        for(int i = n-1; i >=1 ; i--){
            while(k > 0&&( bucket[i]!=null && !bucket[i].isEmpty())){
                //System.out.println(i+":"+bucket[i]);
                ans.add(bucket[i].remove());
                k--;
            }
        }
        return ans;
    }
}
