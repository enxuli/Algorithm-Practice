import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;
import java.util.Random;
import java.util.Collections;

public class ReSpring2018 {
  public static void main(String[] args){

	//1
	System.out.println("1 result:");
	int[] a1 = {3,2,4};
	int n1 = 6;
	//int[] output1 = sol1(a1,n1);
	//System.out.println(output1[0]);
	//System.out.println(output1[1]);
	//3
	System.out.println("3 result:");
	//String a3 = "pwwkew";
	//int output3 = sol3(a3);
	//System.out.println(output3);

	//4
	System.out.println("4 result:");
	int[] a4 = {1,2};
	int[] b4 = {3,4};
	//double output4 = sol4(a4,b4);
	//System.out.println(output4);
	//14 
	System.out.println("14 result:");
	//String [] a14 = {"flower","flow","flight"};
	//String output14 = sol14(a14);
	//System.out.println(output14);
	//21

	//29
	System.out.println("29 result:");
	int a29	= -24123;
	int b29 = 12;
	int output29 = sol29(a29,b29);
	System.out.println(output29);
	}
	//1 two pointer i & j need to be ordered! 
	static int[] sol1(int[]a, int n){
		Map <Integer,Integer> map= new HashMap();
		for (int i = 0; i< a.length; i++){
			map.put(a[i],i);
		}
		Arrays.sort(a);
		int i = 0; 
		int j = a.length -1;
		while (i <= j){
			if(a[i]+a[j]>n) j--;
			else if (a[i]+a[j]<n) i++;
			else break;
		}
		int[] ans= {map.get(a[i]),map.get(a[j])};
		return ans;
	}
    //1 Two-pass Hash Table   ----> One-pass Hash Table
    static int[] OnepassSol1(int[]a, int n){
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < a.length; i++) {
        int complement = n - a[i];
        if (map.containsKey(complement)) {
            return new int[] { map.get(complement), i };
        }
        map.put(a[i], i);
    }
    int[] ans = {-1,-1};
    return ans;
    }


    //2 have to remember that this kind of linkedlist creation !!!! curr.next = new ListNode; curr = curr.next;
    //ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    //    ListNode head = new ListNode(0);
    //    ListNode curr = head;
     //   Boolean flag = false;
     //   while(l1 !=null || l2!=null){
     //       int i = (l1 !=null)? l1.val : 0;
     //       int j = (l2 !=null)? l2.val : 0;
     //       int c = flag ? 1 : 0;
     //       flag = (i + j + c >= 10)? true: false;
     //       curr.next = new ListNode((i + j + c)%10);
     //       curr = curr.next;
    //        if (l1!=null) l1 =l1.next;
     //       if (l2!=null) l2 =l2.next;
     //   }
     //   if (flag) curr.next = new ListNode(1);
     //   return head.next;
   // }

    //3 using hash map to move the leftpointer idx
    static int sol3(String a){
    	if (a.length()==0) return 0;
    	int result = 0;
    	Map <Character,Integer> map = new HashMap();
    	for (int i = 0, j = 0; i < a.length(); i++){
    		if(map.containsKey(a.charAt(i))){
    			j = Math.max(j,map.get(a.charAt(i))+1); // because can be only moving forward
    		}
    		map.put(a.charAt(i),i);
    		result = Math.max(result, i-j-1);
    	}
    	return result;
    }
    //4 using the method in the merge sort!!

    static double sol4(int[] a,int[] b){
    	int mid1=0,mid2= 0;
    	if((a.length+b.length)%2==0) {
    		mid1 = (a.length+b.length)/2 -1;
    		mid2 = mid1 + 1;
   		}else {
   			mid1 = mid2 = (a.length+b.length)/2;
   		}
   		int[] ab = new int[a.length+b.length];
   		int i=0,j=0,count=0;
		while (i<a.length && j<b.length){
			if (a[i]<b[j]){ 
				ab[count++]=a[i++];
			}else{
				ab[count++]=b[j++];
			}
		}
		if (i==a.length){
			while (j<b.length){
				ab[count++]=b[j++];
			}
		}else {
			while (i<a.length){
				ab[count++]=a[i++];
			}
		}
		//for (int n =0; n< ab.length;n++){
		//	System.out.println(ab[n]);
		//}
		double ans = -1;
		if (mid1 == mid2){
			ans = (double) ab[mid1];
			return ans;
		}
		ans = (double) (ab[mid1]+ab[mid2])/2;
		return ans;

    }

    static String sol14(String[] a){
		int length = a[0].length();
        if (a.length ==0) return "";
    	for(int i = 0; i< a.length; i++){
    		length = Math.min(a[i].length(), length);
    	}
        if (length ==0) return "";
    	String ans = "";
    	for (int i = 0; i<length; i++){
    		int j = 0 ;
    		while (j<a.length&&a[j].charAt(i)==a[0].charAt(i)){
    			j++;
    		}
    		if(j==a.length)
    		    ans+=Character.toString(a[0].charAt(i));
            else
                break;
    	}
    	return ans;
    }
//21
      //  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
     //   ListNode head = new ListNode(0);
     //   ListNode curr = head;
     //   while(l1!=null&&l2!=null){
    //        if(l1.val<l2.val){
     //           curr.next = new ListNode(l1.val);
       //         l1=l1.next;
      //      }else{
     //           curr.next = new ListNode(l2.val);
     //           l2=l2.next;
     //       }
     //           curr = curr.next;
     //       }
     //   
     //   if(l1!=null) curr.next = l1;
     //   else curr.next = l2;
      //  return head.next;   
     //   
  //  }

//23 merge K sorted list you can what you can use is that ArrayList .size, .get(), collection.sort()
    //    public ListNode mergeKLists(ListNode[] lists) {
    //    List <Integer> ans = new ArrayList(); 
    //    for (ListNode x : lists){
    //        while (x!=null){
    //            ans.add(x.val);
    //            x = x.next;
    //        }
    //    }
    //    Collections.sort(ans);
    //    ListNode head = new ListNode(0);
    //    ListNode curr = head;
    //    for (int i = 0; i < ans.size(); i ++){
    //        curr.next = new ListNode (ans.get(i));
    //        curr = curr.next;
   //     }
    //    return head.next;    
        
    //}

//29 divide the number without using operator 
        static int sol29(int dividend, int divisor) {
        if (dividend*divisor>0){
            dividend = dividend - divisor;
            if (dividend*divisor<=0)
                return 1;
            else return sol29(dividend,divisor)+1;
        }else if (dividend*divisor<0){
            dividend = dividend + divisor;
            if (dividend*divisor>=0)
                return -1;
            else return sol29(dividend,divisor)-1;
        }else {
        	return 0;
        }

    }


//end	
}