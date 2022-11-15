import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
 */
public class AVL<T extends Comparable<? super T>> {

	/*
	 * Do not add new instance variables or modify existing ones.
	 */
	private AVLNode<T> root;
	private int size;

	/*
	 * Do not add a constructor.
	 */

	/**
	 * Adds the element to the tree.
	 *
	 * Start by adding it as a leaf like in a regular BST and then rotate the
	 * tree as necessary.
	 *
	 * If the data is already in the tree, then nothing should be done (the
	 * duplicate shouldn't get added, and size should not be incremented).
	 *
	 * Remember to recalculate heights and balance factors while going back
	 * up the tree after adding the element, making sure to rebalance if
	 * necessary. This is as simple as calling the balance() method on the
	 * current node, before returning it (assuming that your balance method
	 * is written correctly from part 1 of this assignment).
	 *
	 * @param data The data to add.
	 * @throws java.lang.IllegalArgumentException If data is null.
	 */
	public void add(T data) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		if(data == null) {
			throw new IllegalArgumentException("Data cannot be null.");
		} else {
			root = addH(data, root);
		}
	}

	private AVLNode<T> addH(T data, AVLNode<T> current){
		if(current == null) {
			current = new AVLNode<>(data);
			current = balance(current);	// create new node and update it to height = 0 & balance factor = 0
			size++;
			return current;
		} else if(data.compareTo(current.getData()) < 0) {
			current.setLeft(addH(data, current.getLeft()));
		} else if(data.compareTo(current.getData()) > 0) {
			current.setRight(addH(data, current.getRight()));
		}
		current = balance(current);	// update all the nodes along the recursive call for height & balance factor changes
		return current;
	}

	/**
	 * Removes and returns the element from the tree matching the given
	 * parameter.
	 *
	 * There are 3 cases to consider:
	 * 1: The node containing the data is a leaf (no children). In this case,
	 *    simply remove it.
	 * 2: The node containing the data has one child. In this case, simply
	 *    replace it with its child.
	 * 3: The node containing the data has 2 children. Use the successor to
	 *    replace the data, NOT predecessor. As a reminder, rotations can occur
	 *    after removing the successor node.
	 *
	 * Remember to recalculate heights and balance factors while going back
	 * up the tree after removing the element, making sure to rebalance if
	 * necessary. This is as simple as calling the balance() method on the
	 * current node, before returning it (assuming that your balance method
	 * is written correctly from part 1 of this assignment).
	 *
	 * Do NOT return the same data that was passed in. Return the data that
	 * was stored in the tree.
	 *
	 * Hint: Should you use value equality or reference equality?
	 *
	 * @param data The data to remove.
	 * @return The data that was removed.
	 * @throws java.lang.IllegalArgumentException If the data is null.
	 * @throws java.util.NoSuchElementException   If the data is not found.
	 */
	public T remove(T data) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		if(data == null) {
			throw new IllegalArgumentException("Data cannot be null.");
		} else {
			AVLNode<T> dummy = new AVLNode<>(null);
			root = removeH(data, root, dummy);
			return dummy.getData();
		}
	}

	private AVLNode<T> removeH(T data, AVLNode<T> current, AVLNode<T> dummy) {
		if(current == null) {
			throw new NoSuchElementException("Node is not found in the tree.");
		} else if(current.getData().equals(data)) {	// node is found
			dummy.setData(current.getData());
			size--;
			if(current.getLeft() == null && current.getRight() == null) {
				return null;	// no need to balance a null node
			} else if(current.getLeft() == null) {	// node has only a right child
				current = current.getRight();	// set current to its right child, effectively skipping the current node
				current = balance(current);	// balance the current node
				return current;
			} else if(current.getRight() == null) {	// node has only a left child
				current = current.getLeft();
				current = balance(current);
				return current;
			} else {	// node has two children
				AVLNode<T> temp = new AVLNode<>(null);
				current.setRight(successor(current.getRight(), temp));	// find the successor
				current.setData(temp.getData());	// replace current with successor
				current = balance(current);	// balance the current node
				return current;
			}
		} else if(data.compareTo(current.getData()) < 0) {	// search to the left
			current.setLeft(removeH(data, current.getLeft(), dummy));
		} else if(data.compareTo(current.getData()) > 0) {	// search to the right
			current.setRight(removeH(data, current.getRight(), dummy));
		}
		current = balance(current);	// update all the nodes along the recursive call for height & balance factor changes
		return current;
	}

	private AVLNode<T> successor(AVLNode<T> current, AVLNode<T> temp) {
		if(current.getLeft() == null) {	// successor is found
			temp.setData(current.getData());
			if(current.getRight() != null) {
				current = current.getRight();
				current = balance(current);
				return current;
			} else {
				return null;
			}
		} else {
			current.setLeft(successor(current.getLeft(), temp));
			current = balance(current);
			return current;
		}
	}

	/**
	 * Updates the height and balance factor of a node using its
	 * setter methods.
	 *
	 * Recall that a null node has a height of -1. If a node is not
	 * null, then the height of that node will be its height instance
	 * data. The height of a node is the max of its left child's height
	 * and right child's height, plus one.
	 *
	 * The balance factor of a node is the height of its left child
	 * minus the height of its right child.
	 *
	 * This method should run in O(1).
	 * You may assume that the passed in node is not null.
	 *
	 * This method should only be called in rotateLeft(), rotateRight(),
	 * and balance().
	 *
	 * @param currentNode The node to update the height and balance factor of.
	 */
	private void updateHeightAndBF(AVLNode<T> currentNode) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		if(currentNode.getLeft() == null && currentNode.getRight() == null) {	// a leaf node is found: height and BF should both be 0
			currentNode.setHeight(0);
			currentNode.setBalanceFactor(0);
		} else if(currentNode.getLeft() != null && currentNode.getRight() != null) {	// if a node has two children, height = max(child height) + 1, balance factor = left height - right height
			int maxChildHeight = currentNode.getLeft().getHeight() > currentNode.getRight().getHeight() ? currentNode.getLeft().getHeight() : currentNode.getRight().getHeight();
			currentNode.setHeight(maxChildHeight + 1);
			currentNode.setBalanceFactor(currentNode.getLeft().getHeight() - currentNode.getRight().getHeight());
		} else if(currentNode.getLeft() == null) {
			currentNode.setHeight(currentNode.getRight().getHeight() + 1);
			currentNode.setBalanceFactor(-1 - currentNode.getRight().getHeight());	// height of a null node is -1
		} else if(currentNode.getRight() == null) {
			currentNode.setHeight(currentNode.getLeft().getHeight() + 1);
			currentNode.setBalanceFactor(currentNode.getLeft().getHeight() + 1);
		} 
	}

	/**
	 * Method that rotates a current node to the left. After saving the
	 * current's right node to a variable, the right node's left subtree will
	 * become the current node's right subtree. The current node will become
	 * the right node's left subtree.
	 *
	 * Don't forget to recalculate the height and balance factor of all
	 * affected nodes, using updateHeightAndBF().
	 *
	 * This method should run in O(1).
	 *
	 * You may assume that the passed in node is not null and that the subtree
	 * starting at that node is right heavy. Therefore, you do not need to
	 * perform any preliminary checks, rather, you can immediately perform a
	 * left rotation on the passed in node and return the new root of the subtree.
	 *
	 * This method should only be called in balance().
	 *
	 * @param currentNode The current node under inspection that will rotate.
	 * @return The parent of the node passed in (after the rotation).
	 */
	private AVLNode<T> rotateLeft(AVLNode<T> currentNode) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		AVLNode<T> newRoot = currentNode.getRight();	// save the right child
		currentNode.setRight(newRoot.getLeft());
		newRoot.setLeft(currentNode);
		updateHeightAndBF(currentNode);	// update height and BF from the bottom up
		updateHeightAndBF(newRoot);
		return newRoot;	// this becomes the new root
	}

	/**
	 * Method that rotates a current node to the right. After saving the
	 * current's left node to a variable, the left node's right subtree will
	 * become the current node's left subtree. The current node will become
	 * the left node's right subtree.
	 *
	 * Don't forget to recalculate the height and balance factor of all
	 * affected nodes, using updateHeightAndBF().
	 *
	 * This method should run in O(1).
	 *
	 * You may assume that the passed in node is not null and that the subtree
	 * starting at that node is left heavy. Therefore, you do not need to perform
	 * any preliminary checks, rather, you can immediately perform a right
	 * rotation on the passed in node and return the new root of the subtree.
	 *
	 * This method should only be called in balance().
	 *
	 * @param currentNode The current node under inspection that will rotate.
	 * @return The parent of the node passed in (after the rotation).
	 */
	private AVLNode<T> rotateRight(AVLNode<T> currentNode) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		AVLNode<T> newRoot = currentNode.getLeft();
		currentNode.setLeft(newRoot.getRight());
		newRoot.setRight(currentNode);
		updateHeightAndBF(currentNode);
		updateHeightAndBF(newRoot);
		return newRoot;
	}

	/**
	 * This is the overarching method that is used to balance a subtree
	 * starting at the passed in node. This method will utilize
	 * updateHeightAndBF(), rotateLeft(), and rotateRight() to balance
	 * the subtree. In part 2 of this assignment, this balance() method
	 * will be used in your add() and remove() methods.
	 *
	 * The height and balance factor of the current node is first recalculated.
	 * Based on the balance factor, a no rotation, a single rotation, or a
	 * double rotation takes place. The current node is returned.
	 *
	 * You may assume that the passed in node is not null. Therefore, you do
	 * not need to perform any preliminary checks, rather, you can immediately
	 * check to see if any rotations need to be performed.
	 *
	 * This method should run in O(1).
	 *
	 * @param cur The current node under inspection.
	 * @return The AVLNode that the caller should return.
	 */
	private AVLNode<T> balance(AVLNode<T> currentNode) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

		/* First, we update the height and balance factor of the current node. */
		updateHeightAndBF(currentNode);

		if ( currentNode.getBalanceFactor() <= -2 ) {	// current node is right heavy
			if ( currentNode.getRight().getBalanceFactor() > 0 ) {	// right child is left heavy
				currentNode.setRight(rotateRight(currentNode.getRight()));	// rotate the right child to the right first
			}
			currentNode = rotateLeft(currentNode);	// rotate to the left
		} else if ( currentNode.getBalanceFactor() >= 2 ) {	// current node is left heavy
			if ( currentNode.getLeft().getBalanceFactor() < 0 ) {	// left child is right heavy
				currentNode.setLeft(rotateLeft(currentNode.getLeft()));	// rotate the left child to the left first
			}
			currentNode = rotateRight(currentNode);	// rotate to the right
		}

		return currentNode;
	}

	/**
	 * Returns the root of the tree.
	 *
	 * For grading purposes only. You shouldn't need to use this method since
	 * you have direct access to the variable.
	 *
	 * @return The root of the tree.
	 */
	public AVLNode<T> getRoot() {
		// DO NOT MODIFY THIS METHOD!
		return root;
	}

	/**
	 * Returns the size of the tree.
	 *
	 * For grading purposes only. You shouldn't need to use this method since
	 * you have direct access to the variable.
	 *
	 * @return The size of the tree.
	 */
	public int size() {
		// DO NOT MODIFY THIS METHOD!
		return size;
	}

	/**
	 * Returns the preOrder traversal of the AVL
	 */
	@Override
	public String toString() {
		String returnString = "";
		returnString = preOrderTraversal(root, returnString);
		return returnString;
	}

	/**
	 * Private helper method used for traversal
	 * 
	 * @param The current node
	 * @param The String to be returned in toString
	 * @return
	 */
	private String preOrderTraversal(AVLNode<T> current, String returnString){
		if(current != null) {
			returnString += current.getData() + " ";
			if(current.getLeft() != null) {
				returnString = preOrderTraversal(current.getLeft(), returnString);
			}
			if(current.getRight() != null) {
				returnString = preOrderTraversal(current.getRight(), returnString);
			}
		}
		return returnString;
	}

	public static void main(String[] args) {
		AVL<Integer> avl = new AVL<>();
		avl.add(7);
		avl.add(4);
		avl.add(10);
		avl.add(2);
		avl.add(6);
		avl.add(8);
		avl.add(11);
		avl.add(0);
		avl.add(3);
		avl.add(5);
		avl.add(9);
		avl.add(1);
		System.out.println(avl);	//should be 7 4 2 0 1 3 6 5 10 8 9 11 
		System.out.println(avl.size());	//should be 12
		avl.remove(4);
		System.out.println(avl);	//should be 7 2 0 1 5 3 6 10 8 9 11 after a right rotation on 5
		System.out.println(avl.size());	// should be 11
	}
}
