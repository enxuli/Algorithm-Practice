import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.lang.Math;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.Collections;

public class BackTrackingCollection {
	
	//
	//
	// this is for the problems using backtracking method to solve
	//
	//
	
	 public List<List<Integer>> sol78(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        backTrack78(ans,new ArrayList<>(),nums,0);
        return ans;
    }

	private void backTrack78(List<List<Integer>> ans,List<Integer> tmp,int[] nums, int start) {
		ans.add(new ArrayList<>(tmp));//because the tmp is erased when it switched to another branch
		//so we need to create a new list to added to the ans
		for(int i = start; i < nums.length;i++) {
				tmp.add(nums[i]);
				backTrack78(ans,tmp,nums,i+1);
				tmp.remove(tmp.size() - 1);
		}
	}
	
    public List<List<Integer>> sol90(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
         Arrays.sort(nums);
         backTrack90(ans,new ArrayList<>(),nums,0);
         return ans;
     }

 	private void backTrack90(List<List<Integer>> ans,List<Integer> tmp,int[] nums, int start) {
 		ans.add(new ArrayList<>(tmp));
 		for(int i = start; i < nums.length;i++) {
                if(i > start && nums[i] == nums[i-1]) continue; //in the tree, we dont
                //delete the single end branch but the dup branch
                //every new branch is start with a "start" and i++
                //dup branch is the increasing i among that new start
                // so that only when i > start, we can delete that branch
 				tmp.add(nums[i]);
 				backTrack90(ans,tmp,nums,i+1);
 				tmp.remove(tmp.size() - 1);
 		}
 	}
 	
    public List<List<Integer>> sol46(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
         Arrays.sort(nums);
         backTrack46(ans,new ArrayList<>(),nums);
         return ans;
     }

 	private void backTrack46(List<List<Integer>> ans,List<Integer> tmp,int[] nums) {
 		if(tmp.size()== nums.length) {
 		ans.add(new ArrayList<>(tmp));
 		}else {
 			for(int i = 0; i < nums.length;i++) {
 			if(tmp.contains(nums[i])) continue;
 				tmp.add(nums[i]);
 				backTrack46(ans,tmp,nums);
 				tmp.remove(tmp.size() - 1);
 			}
 		}
 	}
 	
    public List<List<Integer>> sol47(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        backTrack47(ans,new ArrayList<>(),nums);
        return ans;
    }

	private void backTrack47(List<List<Integer>> ans,List<Integer> tmp,int[] nums) {
       List<Integer> flag = new ArrayList<>();
		if(tmp.size()== nums.length) {
		ans.add(new ArrayList<>(tmp));
		}else {
			for(int i = 0; i < nums.length;i++) {
			if(flag.contains(nums[i])) continue;
               flag.add(nums[i]);
				tmp.add(nums[i]);
				backTrack47(ans,tmp,nums);
				tmp.remove(tmp.size() - 1);
			}
		}
	}
	
    public List<List<Integer>> sol39(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        backTrack39(ans,new ArrayList<>(),candidates,target,0,0);
        return ans;
    }

	private void backTrack39 (List<List<Integer>> ans,List<Integer> tmp,int[] nums,int target,int start,int sum) {
		if(sum==target) {
            ans.add(new ArrayList<>(tmp));
		}else if(sum<target){
			for(int i = start; i < nums.length;i++) {
				tmp.add(nums[i]);
				backTrack39(ans,tmp,nums,target,i,sum+nums[i]);
				tmp.remove(tmp.size() - 1);
			}
		}else return;
	}
	
    public List<List<Integer>> sol40(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        backTrack40(ans,new ArrayList<>(),candidates,target,0,0);
        return ans;
    }

	private void backTrack40 (List<List<Integer>> ans,List<Integer> tmp,int[] nums,int target,int start,int sum) {
		if(sum==target) {
            ans.add(new ArrayList<>(tmp));
		}else if(sum<target){
			for(int i = start; i < nums.length;i++) {
				if(i>start && nums[i]==nums[i-1]) continue;
				tmp.add(nums[i]);
				backTrack40(ans,tmp,nums,target,i+1,sum+nums[i]);
				tmp.remove(tmp.size() - 1);
			}
		}else return;
	}
	
    public List<List<String>> sol131(String target) {
        List<List<String>> ans = new ArrayList<>();
        backTrack131(ans,new ArrayList<>(),target,"",0);
        return ans;
    }

	private void backTrack131 (List<List<String>> ans,List<String> tmp,String target,String add,int start) {
		if(add==target) {
            ans.add(new ArrayList<>(tmp));
		}else {
			for(int i = start+1; i < target.length();i++) {
				if(isPalindrome(target.substring(start, i))) {
				tmp.add(target.substring(start, i));
				backTrack131(ans,tmp,target,add + target.substring(start, i),i+1);
				tmp.remove(tmp.size() - 1);
				}
			}
		}
	}

	private boolean isPalindrome(String substring) {
		int i = 0, j = substring.length()-1;
		while(i<=j) {
			if (substring.charAt(i)==substring.charAt(j)) {
				i++;
				j--;
			}else return false;
		}
		return true;
	}
	
	
}
