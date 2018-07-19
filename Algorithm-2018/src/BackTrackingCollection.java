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

import com.sun.corba.se.spi.orbutil.threadpool.Work;

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
		if(add.equals(target)) {
            ans.add(new ArrayList<>(tmp));
		}else {
			for(int i = start+1; i <= target.length();i++) {
				if(isPalindrome(target.substring(start, i))) {
				tmp.add(target.substring(start, i));
				backTrack131(ans,tmp,target,add+target.substring(start, i),i);
				tmp.remove(tmp.size() - 1);
				}
			}
		}
	}

	public boolean isPalindrome(String substring) {
		int i = 0, j = substring.length()-1;
		while(i<=j) {
			if (substring.charAt(i)==substring.charAt(j)) {
				i++;
				j--;
			}else return false;
		}
		return true;
	}
	
    public List<List<Integer>> sol77(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        int[] nums = new int[n]; 
        for(int i = 0 ; i < n ; i++) { nums[i]=i+1; }
        backTrack77(ans,new ArrayList<>(),nums, k, 0);
        return ans;
    }

	private void backTrack77(List<List<Integer>> ans,List<Integer> tmp,int[] nums, int k, int start) {
		if(tmp.size()== k) {
		ans.add(new ArrayList<>(tmp));
		}else {
			for(int i = start; i < nums.length;i++) {
				tmp.add(nums[i]);
				backTrack77(ans,tmp,nums,k,i+1);
				tmp.remove(tmp.size() - 1);
			}
		}
	}
	
        boolean[][] visitedPath;
        public boolean sol79(char[][] board, String word) {
            visitedPath = new boolean[board.length][board[0].length];
        	for (int i = 0; i<board.length; i++) {
        		for(int j = 0 ; j < board[i].length;j++) {
        			if(backTrack77(board,"",word, i, j, 0))return true;
        		}
        	}

            return false;
        }

    	private boolean backTrack77(char[][] board,String tmp,String word, int i, int j, int index) {
    		boolean result1=false,result2=false,result3=false,result4 = false;
            if(tmp.equals(word)) return true;
            if(i<0||j<0||i>=board.length||j>=board[0].length) return false;
            if(word.charAt(index) != board[i][j])return false;
            if(visitedPath[i][j])return false;
    		
            visitedPath[i][j]=true;
    			result1= backTrack77(board,tmp + board[i][j],word,i+1,j,index+1);
    			result2=backTrack77(board,tmp + board[i][j],word,i-1,j,index+1);
    			result3=backTrack77(board,tmp + board[i][j],word,i,j+1,index+1);
    			result4=backTrack77(board,tmp + board[i][j],word,i,j-1,index+1);
    		
    		boolean result = result1||result2||result3||result4;
    		if (result) return result;
            visitedPath[i][j]=false;
    		return false;
    	}
    	
        public List<List<Integer>> sol216(int k, int n) {
            List<List<Integer>> list = new ArrayList<>();
            backtrack216(list, new ArrayList<>(), k, n, 1, 0);
            return list;
        }

        private void backtrack216(List<List<Integer>> list, List<Integer> tempList,int k,int target, int start,int length){
            if(target == 0&& length == k){
                list.add(new ArrayList<>(tempList));
            } else if (target > 0){
                for(int i = start; i < 10; i++){
                    tempList.add(i);
                    backtrack216(list, tempList, k, target-i, i+1,length+1);
                    tempList.remove(tempList.size() - 1);
                    
                }
            }
        }
        
        public List<String> sol93(String s) {
            List<String> list = new ArrayList<>();
            backtrack93(list, "", s, 0, 0);
            return list;
        }

        private void backtrack93(List<String> list, String tempList,String s, int start,int count){
            if(count == 3&&s.length()-start>1&&s.substring(start,start+1).equals("0")) return;
            if(count == 3 &&s.length()-start<=3 && s.length()>start && Integer.valueOf(s.substring(start,s.length()))<=255 ){
                tempList+= s.substring(start,s.length());
                list.add(tempList);
            } else if (count < 3){
                for(int i = start+1; i <= s.length(); i++){
                    if(i-start<=3&&Integer.valueOf(s.substring(start,i))<=255){
                        if(s.substring(start,start+1).equals("0")) {
                        	backtrack93(list, tempList+s.substring(start,i)+".", s, i,count+1);
                        	break;
                        }
                        backtrack93(list, tempList+s.substring(start,i)+".", s, i,count+1);
                    }
                }
            }
        }
        
        public int sol357(int n) {
        	if(n == 0) return 1;
        	if(n == 1) return 10;
            return factorial(10,n)+((n-2>0)?(n-2)*factorial(9,n-2):0)+sol357( n - 2 );
        }

        private int factorial(int i, int n) {
			if (n == 1) return i;
			return i*factorial(i - 1, n - 1);
		}


}
