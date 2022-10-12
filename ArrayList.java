import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 */
public class ArrayList<T> {

	/*
	 * The initial capacity of the ArrayList.
	 *
	 * DO NOT MODIFY THIS VARIABLE!
	 */
	public static final int INITIAL_CAPACITY = 9;

	/*
	 * Do not add new instance variables or modify existing ones.
	 */
	private T[] backingArray;
	private int size;

	/**
	 * This is the constructor that constructs a new ArrayList.
	 * 
	 * Recall that Java does not allow for regular generic array creation,
	 * so instead we cast an Object[] to a T[] to get the generic typing.
	 */
	public ArrayList() {
		//DO NOT MODIFY THIS METHOD!
		backingArray = (T[]) new Object[INITIAL_CAPACITY];
	}

	/**
	 * Adds the data to the front of the list.
	 *
	 * This add may require elements to be shifted.
	 *
	 * Method should run in O(n) time.
	 *
	 * @param data the data to add to the front of the list
	 * @throws java.lang.IllegalArgumentException if data is null
	 */
	public void addToFront(T data) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		if (data == null) {
			throw new IllegalArgumentException();   		
		} else if (size > 0) {
			if (size == backingArray.length) {	// check if the array needs resizing
				T[] tempArray = (T[]) new Object[backingArray.length * 2];	// create a temp array with double the capacity
				for(int i = 0; i < backingArray.length; i++) {
					tempArray[i + 1] = backingArray[i];	// copy the backing array over to temp, shifting one space to the right
				}
				backingArray = tempArray;
			} else {
				for(int i = size - 1; i >= 0; i--) {
					backingArray[i + 1] = backingArray[i];
				}
			}
		} 
		backingArray[0] = data;	// add data to position 0
		size++;
	}

	/**
	 * Adds the data to the back of the list.
	 *
	 * Method should run in amortized O(1) time.
	 *
	 * @param data the data to add to the back of the list
	 * @throws java.lang.IllegalArgumentException if data is null
	 */
	public void addToBack(T data) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		if (data == null) {
			throw new IllegalArgumentException();   		
		} else if (size == backingArray.length) {	// check if the array needs resizing
			T[] tempArray = (T[]) new Object[backingArray.length * 2];
			for(int i = 0; i < backingArray.length; i++) {
				tempArray[i] = backingArray[i];	// copy over without shifting
			}
			backingArray = tempArray;
		}
		backingArray[size] = data;
		size++;
	}

	/**
	 * Removes and returns the first data of the list.
	 *
	 * Do not shrink the backing array.
	 *
	 * This remove may require elements to be shifted.
	 *
	 * Method should run in O(n) time.
	 *
	 * @return the data formerly located at the front of the list
	 * @throws java.util.NoSuchElementException if the list is empty
	 */
	public T removeFromFront() {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		T data;
		if(size == 0) {
			throw new NoSuchElementException();
		} else {
			data = backingArray[0];	// save the data to be returned
			backingArray[0] = null;
			for(int i = 1; i < size; i++) {
				backingArray[i - 1] = backingArray[i];	// shift the array one space to the left
			}
			backingArray[size - 1] = null;	// after the shift, set the right most space to null
		}
		size--;
		return data;
	}

	/**
	 * Removes and returns the last data of the list.
	 *
	 * Do not shrink the backing array.
	 *
	 * Method should run in O(1) time.
	 *
	 * @return the data formerly located at the back of the list
	 * @throws java.util.NoSuchElementException if the list is empty
	 */
	public T removeFromBack() {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		T data;
		if(size == 0) {
			throw new NoSuchElementException();
		} else {
			data = backingArray[size-1];	// save the data to be returned
			backingArray[size-1] = null;	// set the right most space to null
		}
		size--;
		return data;
	}

	/**
	 * Returns the backing array of the list.
	 *
	 * For grading purposes only. You shouldn't need to use this method since
	 * you have direct access to the variable.
	 *
	 * @return the backing array of the list
	 */
	public T[] getBackingArray() {
		// DO NOT MODIFY THIS METHOD!
		return backingArray;
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
	
	public String toString() {
		String arrayString = "";
		for(int i = 0; i < backingArray.length; i++) {
			arrayString += backingArray[i];
			arrayString += ", ";
		}
		return arrayString;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		System.out.println(list);	// empty
		list.addToFront(0);	// 1
		list.addToBack(1);	// 2
		list.addToFront(2);	// 3
		list.addToFront(2);	// 4
		list.addToFront(2);	// 5
		list.addToFront(2);	// 6
		list.addToFront(2);	// 7
		list.addToFront(3);	// 8
		list.addToBack(100);	// 9
		list.addToFront(2);	// resize!
		list.addToFront(2);	//	11
		System.out.println(list);
		list.removeFromFront();
		System.out.println(list);
		list.removeFromBack();
		System.out.println(list);
	}
}