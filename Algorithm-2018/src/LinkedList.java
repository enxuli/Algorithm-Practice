
public class LinkedList {

	public class ListNode {
		     int val;
		     ListNode next;
		     ListNode(int x) { val = x; }
		 }
	
    public boolean isPalindrome(ListNode head) {
        if(head == null ||head.next == null) return true;
        int count = 0;
        ListNode tmp = head;
        
        while(tmp!= null) {count ++; tmp = tmp.next;}
        System.out.println(count);
        return helper (head,count);

    }
    
    private boolean helper(ListNode head, int count){
        if (count == 0 ||count ==1) return true;
        int i = count;
        ListNode tmp = head;
        while (i >1) { tmp = tmp.next; i--;}
        if(tmp.val == head.val) return helper (head.next, count -2);
        else return false;
    }
    
}
