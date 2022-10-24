import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

	/*
	 * Do not add new instance variables or modify existing ones.
	 */
	private BSTNode<T> root;
	private int size;

	/*
	 * Do not add a constructor.
	 */

	/**
	 * Adds the data to the tree.
	 *
	 * This must be done recursively.
	 *
	 * The new data should become a leaf in the tree.
	 *
	 * Traverse the tree to find the appropriate location. If the data is
	 * already in the tree, then nothing should be done (the duplicate
	 * shouldn't get added, and size should not be incremented).
	 *
	 * Should be O(log n) for best and average cases and O(n) for worst case.
	 *
	 * @param data The data to add to the tree.
	 * @throws java.lang.IllegalArgumentException If data is null.
	 */
	public void add(T data) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		if(data == null) {
			throw new IllegalArgumentException();
		} else {
			root = addH(data, root);	// call private helper method and pass in the root
		}
	}

	private BSTNode<T> addH(T data, BSTNode<T> current) {	// a private recursive helper method
		if(current == null) {								// if current is null, add data here
			size++;
			return new BSTNode<T>(data);
		} else if(data.compareTo(current.getData()) < 0) {	// search on the left
			current.setLeft(addH(data, current.getLeft()));
		} else if(data.compareTo(current.getData()) > 0) {	// search on the right
			current.setRight(addH(data, current.getRight()));
		}
		return current;
	}

	/**
	 * Removes and returns the data from the tree matching the given parameter.
	 *
	 * This must be done recursively.
	 *
	 * There are 3 cases to consider:
	 * 1: The node containing the data is a leaf (no children). In this case,
	 * simply remove it.
	 * 2: The node containing the data has one child. In this case, simply
	 * replace it with its child.
	 * 3: The node containing the data has 2 children. Use the SUCCESSOR to
	 * replace the data. You should use recursion to find and remove the
	 * successor (you will likely need an additional helper method to
	 * handle this case efficiently).
	 *
	 * Do NOT return the same data that was passed in. Return the data that
	 * was stored in the tree.
	 *
	 * Hint: Should you use value equality or reference equality?
	 *
	 * Must be O(log n) for best and average cases and O(n) for worst case.
	 *
	 * @param data The data to remove.
	 * @return The data that was removed.
	 * @throws java.lang.IllegalArgumentException If data is null.
	 * @throws java.util.NoSuchElementException   If the data is not in the tree.
	 */
	public T remove(T data) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		if(data == null) {
			throw new IllegalArgumentException();
		} else {
			BSTNode<T> dummy = new BSTNode<T>(null); 							// create a dummy node as a container to return the data
			root = removeH(data, root, dummy);
			return dummy.getData();
		}
	}

	private BSTNode<T> removeH(T data, BSTNode<T> current, BSTNode<T> dummy) {
		if(current == null) {													// if null node is reached, means we have searched through the tree and data is not found
			throw new NoSuchElementException();
		} else if(data.compareTo(current.getData()) < 0) {						// search on the left
			current.setLeft(removeH(data, current.getLeft(), dummy));
		} else if(data.compareTo(current.getData()) > 0) {						// search on the right
			current.setRight(removeH(data, current.getRight(), dummy));
		} else {																// base case: data is found
			dummy.setData(current.getData());									// set dummy data
			size--;
			if(current.getLeft() == null && current.getRight() == null) {		// if the node is a leaf, return null to cut off the node
				return null;
			} else if(current.getLeft() == null) {								// if the node only has one right child, return the right child
				return current.getRight();
			} else if(current.getRight() == null) {								// if the node only has one left child, return the left child
				return current.getLeft();
			} else {															// if the node has two children, find the successor
				BSTNode<T> temp = new BSTNode<T>(null);
				current.setRight(successor(current.getRight(), temp));
				current.setData(temp.getData());
			}
		}
		return current;
	}
	
	private BSTNode<T> successor(BSTNode<T> current, BSTNode<T> temp) {
		if(current.getLeft() == null) {											// base case: reached the left-most node with no left child
			temp.setData(current.getData());									// set temp data
			if(current.getRight() == null) {									// if the node has no children, return
				return null;
			} else {
				return current.getRight();										// return the right child
			}
		} else {
			current.setLeft(successor(current.getLeft(), temp));				// recursively search for the successor
			return current;
		}
	}

	/**
	 * Returns the root of the tree.
	 *
	 * For grading purposes only. You shouldn't need to use this method since
	 * you have direct access to the variable.
	 *
	 * @return The root of the tree
	 */
	public BSTNode<T> getRoot() {
		// DO NOT MODIFY THIS METHOD!
		return root;
	}

	/**
	 * Returns the size of the tree.
	 *
	 * For grading purposes only. You shouldn't need to use this method since
	 * you have direct access to the variable.
	 *
	 * @return The size of the tree
	 */
	public int size() {
		// DO NOT MODIFY THIS METHOD!
		return size;
	}
}