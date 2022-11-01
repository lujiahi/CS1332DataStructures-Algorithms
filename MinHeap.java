import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

     /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    	if(data == null) {
    		throw new IllegalArgumentException();
    	} else {
    		if(size >= backingArray.length - 1) {	// resize the backing array
    			T[] temp = (T[]) new Comparable[backingArray.length * 2];
    			for(int i = 1; i < backingArray.length; i++) {
    				temp[i] = backingArray[i];
    			}
    			backingArray = temp;
    		}
    		backingArray[size + 1] = data;	// add new data
    		size++;
    		int currentIndex = size;
    		int parentIndex = currentIndex / 2;
    		while(currentIndex > 1 && backingArray[currentIndex].compareTo(backingArray[parentIndex]) < 0 ) {	// perform upheap until reaches the root or until no swap is needed
    			T currentData = backingArray[currentIndex];
    			backingArray[currentIndex] = backingArray[parentIndex];
    			backingArray[parentIndex] = currentData;
    			currentIndex = parentIndex;
        		parentIndex = currentIndex / 2;
    		}
    	}
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    	if(size == 0) {
    		throw new NoSuchElementException();
    	} else if(size == 1) {
    		T data = backingArray[1];
    		backingArray[1] = null;
    		size--;
    		return data;
    	} else {
    		T data = backingArray[1];
    		backingArray[1] = backingArray[size];
    		backingArray[size] = null;
    		size--;
    		int currentIndex = 1;	// start to downheap from the root
    		int leftChildIndex = currentIndex * 2;
    		int rightChildIndex = currentIndex * 2 + 1;
    		int minChildIndex;
    		if(rightChildIndex > size) {	// check if there is a right child to avoid NPE
    			minChildIndex = leftChildIndex;
    		} else {
    			minChildIndex = (backingArray[leftChildIndex].compareTo(backingArray[rightChildIndex]) < 0)? leftChildIndex : rightChildIndex;	// compare the two children to find the min child
    		}
    		while(minChildIndex <= size && backingArray[currentIndex].compareTo(backingArray[minChildIndex]) > 0) {	// perform downheap until reaches the leaf or until no swap is needed
    			T currentData = backingArray[currentIndex];
    			backingArray[currentIndex] = backingArray[minChildIndex];
    			backingArray[minChildIndex] = currentData;
    			currentIndex = minChildIndex;
        		leftChildIndex = currentIndex * 2;
        		rightChildIndex = currentIndex * 2 + 1;
        		if(rightChildIndex > size) {
        			minChildIndex = leftChildIndex;
        		} else {
        			minChildIndex = (backingArray[leftChildIndex].compareTo(backingArray[rightChildIndex]) < 0)? leftChildIndex : rightChildIndex;
        		}    		
        	}
    		return data;
    	}
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
    
    public static void main(String[] args) {
    	MinHeap<Integer> mh = new MinHeap<>();
    	mh.add(1);
    	mh.add(2);
    	mh.add(3);
    	mh.add(4);
    	mh.add(5);
    	mh.add(6);
    	mh.add(7);
    	mh.add(8);
    	mh.add(9);
    	mh.add(10);
    	mh.add(11);
    	mh.add(12);
    	mh.add(13);	// resize!
    	System.out.println(Arrays.toString(mh.getBackingArray()));
    	mh.add(0);	// upheap until 0 becomes the new root
    	System.out.println(Arrays.toString(mh.getBackingArray()));
    	System.out.println("size: " + mh.size());
    	mh.remove();	// remove 0 from root
    	System.out.println(Arrays.toString(mh.getBackingArray()));
    	System.out.println("size: " + mh.size());
    	mh.remove();	// remove 1 from root
    	System.out.println(Arrays.toString(mh.getBackingArray()));
    	System.out.println("size: " + mh.size());
    }
}