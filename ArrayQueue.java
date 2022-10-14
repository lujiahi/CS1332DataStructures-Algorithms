import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayQueue.
 */
public class ArrayQueue<T> {

	/*
	 * The initial capacity of the ArrayQueue.
	 *
	 * DO NOT MODIFY THIS VARIABLE.
	 */
	public static final int INITIAL_CAPACITY = 9;

	/*
	 * Do not add new instance variables or modify existing ones.
	 */
	private T[] backingArray;
	private int front;
	private int size;

	/**
	 * This is the constructor that constructs a new ArrayQueue.
	 * 
	 * Recall that Java does not allow for regular generic array creation,
	 * so instead we cast an Object[] to a T[] to get the generic typing.
	 */
	public ArrayQueue() {
		// DO NOT MODIFY THIS METHOD!
		backingArray = (T[]) new Object[INITIAL_CAPACITY];
	}

	/**
	 * Adds the data to the back of the queue.
	 *
	 * If sufficient space is not available in the backing array, resize it to
	 * double the current length. When resizing, copy elements to the
	 * beginning of the new array and reset front to 0.
	 *
	 * Method should run in amortized O(1) time.
	 *
	 * @param data the data to add to the back of the queue
	 * @throws java.lang.IllegalArgumentException if data is null
	 */
	public void enqueue(T data) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		if(data == null) {
			throw new IllegalArgumentException();
		} else {
			if(size == backingArray.length) {	// resize if array reaches capacity
				T[] tempArray = (T[]) new Object[backingArray.length * 2];
				for(int i = front; i < backingArray.length; i++) {	// copy while shifting array to realign front to 0
					tempArray[i - front] = backingArray[i];
				}
				for(int i = 0; i < front; i++) {	// copy the part that was wrapped around to the back
					tempArray[backingArray.length + i - front] = backingArray[i];
				}
				backingArray = tempArray;
				front = 0;	// reset front index to 0 after resizing
			}
			backingArray[(front + size) % backingArray.length] = data;	// add data
			size++;
		}
	}

	/**
	 * Removes and returns the data from the front of the queue.
	 *
	 * Do not shrink the backing array.
	 *
	 * Replace any spots that you dequeue from with null.
	 *
	 * If the queue becomes empty as a result of this call, do not reset
	 * front to 0.
	 *
	 * Method should run in O(1) time.
	 *
	 * @return the data formerly located at the front of the queue
	 * @throws java.util.NoSuchElementException if the queue is empty
	 */
	public T dequeue() {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		if(size == 0) {
			throw new NoSuchElementException();
		} else {
			T data = backingArray[front];
			backingArray[front] = null;	// set front to null
			front++;	// move front forward
			if(front == backingArray.length) {	// wrap around if front has reached the end of the array
				front = 0;
			}
			size--;
			return data;
		}
	}

	/**
	 * Returns the backing array of the queue.
	 *
	 * For grading purposes only. You shouldn't need to use this method since
	 * you have direct access to the variable.
	 *
	 * @return the backing array of the queue
	 */
	public T[] getBackingArray() {
		// DO NOT MODIFY THIS METHOD!
		return backingArray;
	}

	/**
	 * Returns the size of the queue.
	 *
	 * For grading purposes only. You shouldn't need to use this method since
	 * you have direct access to the variable.
	 *
	 * @return the size of the queue
	 */
	public int size() {
		// DO NOT MODIFY THIS METHOD!
		return size;
	}

	public String toString() {
		String queueString = "";
		for(int i = 0; i < backingArray.length; i++) {
			queueString += backingArray[i] + " ";
		}
		return queueString;
	}

	public static void main(String[] args) {
		ArrayQueue<Integer> queue = new ArrayQueue<>();
		queue.enqueue(0);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		queue.enqueue(6);
		queue.enqueue(7);
		queue.enqueue(8); // full capacity
		System.out.println(queue);
		queue.enqueue(9); // resize!
		System.out.println(queue);
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();		
		queue.dequeue();		
		queue.dequeue();		
		System.out.println(queue);
		System.out.println("front index: " + queue.front);
		queue.enqueue(10);
		queue.enqueue(11);
		queue.enqueue(12);
		queue.enqueue(13);
		queue.enqueue(14);
		queue.enqueue(15);
		queue.enqueue(16);
		queue.enqueue(17);
		queue.enqueue(18);	// wrap around!
		System.out.println(queue);
		System.out.println("front index: " + queue.front);
	}
}