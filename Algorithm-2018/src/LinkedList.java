import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LinkedList {

	public class ListNode {
		     int val;
		     ListNode next;
		     ListNode(int x) { val = x; }
		 }
	// 19. Remove Nth Node From End of List Space O(n)version!
	public ListNode removeNthFromEnd(ListNode head, int n) {
    List<ListNode> list = new ArrayList<>();
    ListNode dummyHead = new ListNode(0);
    dummyHead.next = head;
    ListNode cur = dummyHead;
    int count = 0;
    while(cur!=null){
        count ++;
        list.add(cur);
        cur = cur.next;
    }
    list.get(count-n-1).next = list.get(count-n-1).next.next;
    return dummyHead.next;  
	}
	// space(1)  time O(n) version:
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummyhead = new ListNode(0);
        dummyhead.next = head;
        ListNode fast = dummyhead;
        ListNode slow = dummyhead;
        for(int i = 0; i < n + 1; i ++){
            fast = fast.next;
        }
        while(fast != null){
            fast= fast.next;
            slow= slow.next;
        }
        slow.next = slow.next.next;
        return dummyhead.next;
    }
    
    //61. Rotate List brute force of the rotate, realize every swift!
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next==null) return head;
        ListNode newhead = head;
        ListNode pretail = head;

        for(int i = 0; i < k; i ++){
            newhead = head;
            while(newhead.next!=null){
                pretail = newhead;
                newhead = newhead.next;
            }
            newhead.next = head;
            pretail.next =null;
            head = newhead;
        }
        
        return head;
    }
    
	
	
	//92. Reverse Linked List II  actually using a two pointers to make it a insertion
	 public ListNode reverseBetween(ListNode head, int m, int n) {
		 if(head == null) return null;
		    ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
		    dummy.next = head;
		    ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
		    for(int i = 0; i<m-1; i++) pre = pre.next;
		    
		    ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
		    ListNode then = start.next; // a pointer to a node that will be reversed
		    
		    // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
		    // dummy-> 1 -> 2 -> 3 -> 4 -> 5
		    
		    for(int i=0; i<n-m; i++)
		    {
		        start.next = then.next;
		        then.next = pre.next;
		        pre.next = then;
		        then = start.next;
		    }
		    
		    // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
		    // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)
		    
		    return dummy.next;
		    }
	
	
	//141. LinkedList cycle
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        
        ListNode fast = head;
        ListNode slow = head;
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast!=null &&fast.val == slow.val) return true;
        }
        return false;
    }
    //142 LinkedList cycle II return the cycle beginning
    Set<ListNode> loop = new HashSet();
    public ListNode detectCycle(ListNode head) {
        if(head == null) return null;
        if(!loop.contains(head)) {
            loop.add(head);
            return detectCycle(head.next);
        }else return head;
        
    }
    // Using a fast to chase the slow, then using other slow to meet the slow at the beginning
    // this pattern is used a lot of scenario!!!  FAST AND SLOW CHASING!!
    public ListNode detectCycle2(ListNode head) {
        if(head == null || head.next == null)return null;
        // ListNode dummy = new ListNode(0);
        // dummy.next = head;
        ListNode slow = head,fast = head;
        
        
        while(fast!=null && fast.next !=null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow)
                break;
        }
        if(fast!=slow)return null;
        slow = head;
        while(slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    
    //206 reverse LinkedList
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode tmp = null;
        ListNode next = null;
        while(head.next!=null){
            next = head.next;
            head.next = tmp;
            tmp = head;
            head = next;
        }
        head.next = tmp;
        return head;
    }
    //206 recursion
    public ListNode reverseList2(ListNode head) {

        return reverseListInt(head, null);
    }

    private ListNode reverseListInt(ListNode cur, ListNode pre) {
        if (cur == null)
            return pre;
        ListNode next = cur.next;
        cur.next = pre;
        return reverseListInt(next, cur);
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
    
    //369. Plus One Linked List
    public ListNode plusOne(ListNode head) {
        if (dfs(head) == 1) {
            ListNode ans = new ListNode(1); 
            ans.next = head;
            return ans;
        }else return head;
    }
    private int dfs(ListNode head){
            int tmp = head.val; 
            int c = (head.next==null)? 0:dfs(head.next);
            head.val = (tmp + c + (head.next==null? 1 : 0))%10; 
            return (tmp + c + (head.next==null? 1 : 0))/10; 
        
    }
    
    //
    
    
    
    
    
}
