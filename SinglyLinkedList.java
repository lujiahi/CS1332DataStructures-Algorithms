import java.util.NoSuchElementException;

/**
 * Your implementation of a Singly-Linked List.
 */
public class SinglyLinkedList<T> {

	/*
	 * Do not add new instance variables or modify existing ones.
	 */
	private SinglyLinkedListNode<T> head;
	private SinglyLinkedListNode<T> tail;
	private int size;

	/*
	 * Do not add a constructor.
	 */

	/**
	 * Adds the element to the front of the list.
	 *
	 * Method should run in O(1) time.
	 *
	 * @param data the data to add to the front of the list
	 * @throws java.lang.IllegalArgumentException if data is null
	 */
	public void addToFront(T data) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		if(data == null) {
			throw new IllegalArgumentException();
		} else {
			SinglyLinkedListNode<T> toAdd = new SinglyLinkedListNode<>(data);
			if (head == null) {
				head = toAdd;
				tail = toAdd;
			} else {
				toAdd.setNext(head);
				head = toAdd;
			}
			size++;
		}
	}

	/**
	 * Adds the element to the back of the list.
	 *
	 * Method should run in O(1) time.
	 *
	 * @param data the data to add to the back of the list
	 * @throws java.lang.IllegalArgumentException if data is null
	 */
	public void addToBack(T data) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		if(data == null) {
			throw new IllegalArgumentException();
		} else {
			SinglyLinkedListNode<T> toAdd = new SinglyLinkedListNode<>(data);
			if (head == null) {
				head = toAdd;
				tail = toAdd;
			} else {
				tail.setNext(toAdd);
				tail = tail.getNext();
			}
			size++;
		}
	}

	/**
	 * Removes and returns the first data of the list.
	 *
	 * Method should run in O(1) time.
	 *
	 * @return the data formerly located at the front of the list
	 * @throws java.util.NoSuchElementException if the list is empty
	 */
	public T removeFromFront() {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		T data;
		if(head == null) {
			throw new NoSuchElementException();
		} else if(size == 1){
			data = head.getData();
			head = null;
			tail = null;
		} else {
			data = head.getData();
			head = head.getNext();
		}
		size--;
		return data;
	}

	/**
	 * Removes and returns the last data of the list.
	 *
	 * Method should run in O(n) time.
	 *
	 * @return the data formerly located at the back of the list
	 * @throws java.util.NoSuchElementException if the list is empty
	 */
	public T removeFromBack() {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		T data;
		if(head == null) {
			throw new NoSuchElementException();
		} else if(size == 1) {
			data = head.getData();
			head = null;
			tail = null;			
		} else {
			data = tail.getData();
			SinglyLinkedListNode<T> current = head;
			while(current.getNext().getNext() != null) {
				current = current.getNext();
			}
			current.setNext(null);
			tail = current;
		}
		size--;
		return data;
	}

	/**
	 * Returns the head node of the list.
	 *
	 * For grading purposes only. You shouldn't need to use this method since
	 * you have direct access to the variable.
	 *
	 * @return the node at the head of the list
	 */
	public SinglyLinkedListNode<T> getHead() {
		// DO NOT MODIFY THIS METHOD!
		return head;
	}

	/**
	 * Returns the tail node of the list.
	 *
	 * For grading purposes only. You shouldn't need to use this method since
	 * you have direct access to the variable.
	 *
	 * @return the node at the tail of the list
	 */
	public SinglyLinkedListNode<T> getTail() {
		// DO NOT MODIFY THIS METHOD!
		return tail;
	}

	/**
	 * Returns the size of the list.
	 *
	 * For grading purposes only. You shouldn't need to use this method since
	 * you have direct access to the variable.
	 *
	 * @return the size of the list
	 */
	public int size() {
		// DO NOT MODIFY THIS METHOD!
		return size;
	}

	@Override
	public String toString() {
		if(head == null) {
			return "";
		} else {
			String sllString = "";
			SinglyLinkedListNode<T> current = head;
			while(current != null) {
				sllString += current.toString() + " ";
				current = current.getNext();
			}
			return sllString;    		
		}
	}

	public static void main(String[] args) {
		SinglyLinkedList<Integer> sll = new SinglyLinkedList<>();
		sll.addToFront(11);
		sll.addToFront(10);
		sll.addToBack(12);
		sll.addToBack(13);
		System.out.println(sll);
		System.out.println(sll.size);
		sll.removeFromFront();
		System.out.println(sll);
		sll.removeFromBack();
		System.out.println(sll);
		
		SinglyLinkedList<Integer> sll2 = new SinglyLinkedList<>();
		sll2.addToBack(100);
		System.out.println(sll2);
		sll2.removeFromBack();
		System.out.println(sll2);
		System.out.println(sll2.size);

	}
}