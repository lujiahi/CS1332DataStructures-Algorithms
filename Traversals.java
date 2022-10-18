import java.util.List;
import java.util.LinkedList;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

	/**
	 * DO NOT ADD ANY GLOBAL VARIABLES!
	 */

	/**
	 * Given the root of a binary search tree, generate a
	 * pre-order traversal of the tree. The original tree
	 * should not be modified in any way.
	 *
	 * This must be done recursively.
	 *
	 * Must be O(n).
	 *
	 * @param <T> Generic type.
	 * @param root The root of a BST.
	 * @return List containing the pre-order traversal of the tree.
	 */
	public List<T> preorder(TreeNode<T> root) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		return preorderTraversal(root);
	}

	private List<T> preorderTraversal(TreeNode<T> node) {
		List<T> list = new LinkedList<>();
		if(node == null) {
			return list;
		} else {
			list.add(node.getData());
			list.addAll(preorderTraversal(node.getLeft()));
			list.addAll(preorderTraversal(node.getRight()));
			return list;
		}
	}

	/**
	 * Given the root of a binary search tree, generate an
	 * in-order traversal of the tree. The original tree
	 * should not be modified in any way.
	 *
	 * This must be done recursively.
	 *
	 * Must be O(n).
	 *
	 * @param <T> Generic type.
	 * @param root The root of a BST.
	 * @return List containing the in-order traversal of the tree.
	 */
	public List<T> inorder(TreeNode<T> root) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		return inorderTraversal(root);
	}

	private List<T> inorderTraversal(TreeNode<T> node) {
		List<T> list = new LinkedList<>();
		if(node == null) {
			return list;
		} else {
			list.addAll(inorderTraversal(node.getLeft()));
			list.add(node.getData());
			list.addAll(inorderTraversal(node.getRight()));
			return list;
		}
	}

	/**
	 * Given the root of a binary search tree, generate a
	 * post-order traversal of the tree. The original tree
	 * should not be modified in any way.
	 *
	 * This must be done recursively.
	 *
	 * Must be O(n).
	 *
	 * @param <T> Generic type.
	 * @param root The root of a BST.
	 * @return List containing the post-order traversal of the tree.
	 */
	public List<T> postorder(TreeNode<T> root) {
		// WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
		return postorderTraversal(root);
	}

	private List<T> postorderTraversal(TreeNode<T> node) {
		List<T> list = new LinkedList<>();
		if(node == null) {
			return list;
		} else {
			list.addAll(postorderTraversal(node.getLeft()));
			list.addAll(postorderTraversal(node.getRight()));
			list.add(node.getData());
			return list;
		}
	}

}
