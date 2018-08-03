
public class HashMapCollection {

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
