package linkedlist;

public class MyLinkedList<T> {
	private ListNode<T> head;
	private ListNode<T> tail;
	private int size;
	
	public MyLinkedList() {
		size = 0;
		head = new ListNode<T>(null);
		tail = new ListNode<T>(null);
		
		head.setNext(tail);
		tail.setPrev(head);
	}
	
	public ListNode<T> getHead() {
		return head;
	}
	
	public ListNode<T> getTail() {
		return tail;
	}
	
	
	public  void add(ListNode<T> node) {
		
		if(node == null) {
			throw new NullPointerException("Can not add node set as null");
		}
		
		node.setNext(head.getNext());
		node.setPrev(head);
		
		head.setNext(node);
		
		node.getNext().setPrev(node);
		
		this.size++;
		
	}
	
	public  void addInMiddle(ListNode<T> node,ListNode<T> before) {
		
		if(node == null) {
			throw new NullPointerException("Can not add node set as null");
		}
		
		node.setNext(before);
		node.setPrev(before.getPrev());
		
		before.getPrev().setNext(node);	
		
		before.setPrev(node);
			
		
		this.size++;
		
	}
	
	
	public void remove(ListNode<T> node) {
		
		if(node == null) {
			throw new NullPointerException("Can not add node set as null");
		}
		
		node.getPrev().setNext(node.getNext());
		node.getNext().setPrev(node.getPrev());
		
		this.size--;
	}
	
	public int size() {
		return this.size;
	}
	
	public void printLinkedListData() {
		ListNode<T> start = this.head;
		while(start.getNext().getData() != null) {
			System.out.print(start.getNext().getData());
			start = start.getNext();
			System.out.print((start.getNext().getData() != null ? "->":""));
		}
		
		System.out.println(" Size of Linked List is "+this.size());
		System.out.println("\n");
	}
	
}
