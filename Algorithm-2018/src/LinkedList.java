import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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
    //24. Swap Nodes in Pairs iterative version
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while (head!=null && head.next!=null){
            ListNode tmp = head.next;
            pre.next = pre.next.next;
            head.next = head.next.next;
            tmp.next = head;
            pre = head;
            head = head.next;
        }
        return dummyHead.next;
        
    }
    // recursive version 
    public ListNode swapPairs2(ListNode head) {
        if ((head == null)||(head.next == null))
            return head;
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }
    
    //24 merge k sort!
    
    
    //25. Reverse Nodes in k-Group
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k==1||head==null) return head;
        int len = getLen(head);
        int numGroup = len / k;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < numGroup; i++){
            pre.next=reverse(pre.next,k);
            for (int j = 0; j < k; j++) pre = pre.next;
        }
        return dummy.next;
    }
    private int getLen(ListNode head){
        if(head == null) return 0;
        return 1+getLen(head.next);
    }
    private ListNode reverse(ListNode head,int k){
        ListNode cur = head.next;
        ListNode tmp = head;
        for(int i = 0 ; i < k - 1 ; i++){
            head.next = head.next.next;
            cur.next = tmp;
            tmp = cur;
            cur = head.next;
        }
        return tmp;
        
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
    
    //82. Remove Duplicates from Sorted List II
    public ListNode deleteDuplicates1(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while(head!=null&&head.next!=null){
            if(head.val != head.next.val) {
                head = head.next;
                pre = pre.next;
            }else{
            while(head.next!=null && head.val == head.next.val){
                head = head.next;
            }
            pre.next = head.next;
                head=head.next;
            }
        }
        return dummy.next;
    }
    
	//83. Remove Duplicates from Sorted List
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        if(head.val == deleteDuplicates(head.next).val){ head.next = head.next.next;}
        return head;
        
    }
    
    //86. Partition List
    public ListNode partition(ListNode head, int x) {
        ListNode dummyLess = new ListNode(0);
        ListNode less = dummyLess;
        
        ListNode dummyEqMore = new ListNode(0);
        ListNode eqMore = dummyEqMore;
        
        ListNode cur = head;

        while(cur!=null){
            if(cur.val < x){
                less.next = cur;
                less = cur;
            }else{
                eqMore.next = cur;
                eqMore =cur;
            }
            cur = cur.next;
        }
        
        eqMore.next = null;
        less.next = dummyEqMore.next;
        return dummyLess.next;
        
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
    
    //143 reorder list brute force!!!  O(n^2)
    public void reorderList(ListNode head) {
        if(head == null||head.next==null) return;
        ListNode cur = head.next;
        ListNode precur = head;
        while(cur!=null&&cur.next!=null){
            ListNode prelast = cur;
            ListNode last = cur;
            while(last.next!=null){
                prelast = last;
                last = last.next;
            }
            prelast.next = null;
            precur.next = last;
            last.next = cur;
            precur = cur;
            cur = cur.next;
        }
        
    }
    // O(n)
    public void reorderList(ListNode head) {
        if(head==null||head.next==null) return;
        
        //Find the middle of the list
        ListNode p1=head;
        ListNode p2=head;
        while(p2.next!=null&&p2.next.next!=null){ 
            p1=p1.next;
            p2=p2.next.next;
        }
        
        //Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
        ListNode preMiddle=p1;
        ListNode preCurrent=p1.next;
        while(preCurrent.next!=null){
            ListNode current=preCurrent.next;
            preCurrent.next=current.next;
            current.next=preMiddle.next;
            preMiddle.next=current;
        }
        
        //Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
        p1=head;
        p2=preMiddle.next;
        while(p1!=preMiddle){
            preMiddle.next=p2.next;
            p2.next=p1.next;
            p1.next=p2;
            p1=p2.next;
            p2=preMiddle.next;
        }
    }
    //147 insertion sorting 
    public ListNode insertionSortList(ListNode head) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head.next;
        ListNode precur = head;
        while(cur!=null){
            ListNode ordered = dummy.next;
            ListNode preorder = dummy;
            
            while(ordered!=cur){
                if(ordered.val > cur.val){
                    preorder.next = new ListNode(cur.val);
                    preorder.next.next = ordered;
                    break;
                }
                preorder = ordered;
                ordered = ordered.next;
            }
            if (ordered != cur){
                precur.next = precur.next.next;
            }else precur = cur;
            cur = cur.next;
            
        }
        cur = null;
        return dummy.next;
    }
    
    
    //148. Sort List using the merge sort to achieve all O(n log n)!!!!!!
    public ListNode sortList(ListNode head) {
        return partition(head);
    }
    private ListNode partition(ListNode head){
        if(head ==null || head.next ==null) return head;
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        while (fast!=null && fast.next!=null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        
        ListNode left = partition(head);
        ListNode right = partition(slow);
        
        return merge(left,right);    
    }
    private ListNode merge(ListNode a, ListNode b){
        ListNode head = new ListNode(0), cur = head;
        while(a!=null && b!=null){
            if(a.val<b.val){
                cur.next = a; 
                cur = cur.next;
                a = a.next;
            }else {
                cur.next = b; 
                cur = cur.next;
                b = b.next;
            }
        }
        
        if(a == null) cur.next = b;
        if(b == null) cur.next = a;
        
        return head.next;
    }
    
    //160. Intersection of Two Linked Lists there is problem here!!!!!!!!!!!!!
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        //boundary check
        if(headA == null || headB == null) return null;
        ListNode ans = null;
        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while( a != null||b!=null){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            if(a!=null&&b!=null&&a.val==b.val) {
                if(isIntersect(a,b)) {
                    ans = a;
                    return ans;
                }
            }
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;    
        }
    
        return ans;
    }
    private static boolean isIntersect(ListNode a, ListNode b){
        if(a == null && b == null) return true;
        if(a.val != b.val) return false;
        return isIntersect(a.next, b.next);
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
    
    //328 328. Odd Even Linked List
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return head;
        ListNode dummyEven = new ListNode(0);
        dummyEven.next = head.next;
        ListNode odd = head;
        ListNode even = head.next;
        int count = 0;
        while(even!=null&&even.next!=null){
            odd.next = odd.next.next;
            odd = odd.next;
            even.next = even.next.next;
            even =even.next;
        }
        odd.next = dummyEven.next;
        //System.out.println(ans.next.val);
        return head;
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
    
    //445. Add Two Numbers II
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int c = 0;
        ListNode head = new ListNode(0);
        
        Stack<Integer> a = pushInStack(l1);
        Stack<Integer> b = pushInStack(l2);
        
        while(!a.isEmpty()||!b.isEmpty()){
            int tmp = (a.isEmpty()? 0:a.pop()) +(b.isEmpty()?0:b.pop())+c;
            int sum = tmp%10;
            c = tmp/10;
            ListNode insert = new ListNode(sum);
            insert.next = head.next;
            head.next = insert;
        }
        if(c == 1){            
            ListNode insert = new ListNode(1);
            insert.next = head.next;
            head.next = insert;
        }
        
        return head.next;
        
    }
    private static Stack<Integer> pushInStack(ListNode head){
        Stack<Integer> res = new Stack<>();
        while(head!=null){
            res.push(head.val);
            head = head.next;
        }
        return res;
    }
    
    
    
    
    
}
