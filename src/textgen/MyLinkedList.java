package textgen;

import java.util.AbstractList;

import linkedlist.ListNode;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		
		head.setNext(tail);
		tail.setPrev(head);
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		
		LLNode<E> node = new LLNode<E>(element);
		
		node.setNext(tail);
		node.setPrev(tail.getPrev());
		
		tail.getPrev().setNext(node);
		tail.setPrev(node);
		this.size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(index > this.size-1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		int i = 0;
		LLNode<E> start = this.head;
		
		while(i <= index) {
			start = start.getNext();
			i++;
		}
		return start.getData();
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		int i = 0;
		LLNode<E> toBeAdded = new LLNode<E>(element);
		LLNode<E> start = this.head;
		
		while(i <= index) {
			start = start.getNext();
			toBeAdded.setNext(start);
			toBeAdded.setPrev(start.getPrev());
			
			start.getPrev().setNext(toBeAdded);
			start.setPrev(toBeAdded);
			i++;
			
		}
		
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		int i = 0;
		LLNode<E> start = this.head;
		
		while(i <= index) {
			start = start.getNext();
			start.getNext().setPrev(start.getPrev());
			start.getPrev().setNext(start.getNext());
			i++;
			
		}
		return start.getData();
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		return null;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	

	public LLNode<E> getPrev() {
		return prev;
	}



	public void setPrev(LLNode<E> prev) {
		this.prev = prev;
	}



	public LLNode<E> getNext() {
		return next;
	}



	public void setNext(LLNode<E> next) {
		this.next = next;
	}



	public E getData() {
		return data;
	}



	public void setData(E data) {
		this.data = data;
	}



	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
