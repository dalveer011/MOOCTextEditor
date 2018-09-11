package linkedlist;

public class LinkedListRun {

	public static void main(String[] args) {
		
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		
		ListNode<Integer> a = new ListNode<Integer>(1);
		ListNode<Integer> b = new ListNode<Integer>(2);
		ListNode<Integer> c = new ListNode<Integer>(3);
		
		list.printLinkedListData();
		
		list.add(a);
		list.add(b);
		list.add(c);
		
		list.printLinkedListData();
		
		list.remove(c);
		
		list.printLinkedListData();
		
		list.addInMiddle(c,a);
		
		list.printLinkedListData();

	}

}
