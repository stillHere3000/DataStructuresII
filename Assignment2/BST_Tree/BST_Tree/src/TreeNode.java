/*
 * COSC 2007 Section O & LAB O 
 * Author: Trevor Maliro 
 * Student ID: 239498690
 * Date: 2023/11/30 
 * 
 * Assignment 2: Question B
 * Pseudocode/Algorithm: 
 *      -
 * Code: 
 * No input necessary
 * Output: 
 * 
* A+(B*C) -> ABC*+
*Conclusion: 
* The algorithm works almost as expected.
 */
public class TreeNode<T> {
	T item;
	TreeNode<T> leftChild;
	TreeNode<T> rightChild;
	
	public TreeNode(T newItem) {
	// Initializes tree node with item and no children.
		item = newItem;
		leftChild = null;
		rightChild = null;
	} // end constructor
	
	public TreeNode(T newItem, TreeNode<T> left, TreeNode<T> right) {
	// Initializes tree node with item and
	// the left and right children references.
		item = newItem;
		leftChild = left;
		rightChild = right;
	} // end constructor
	
	public T getItem() {
		return item;
	}
	
	public void setItem(T item) {
		this.item = item;
	}
	
	public TreeNode<T> getLeftChild() {
		return leftChild;
	}
	
	public void setLeftChild(TreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}
	
	public TreeNode<T> getRightChild() {
		return rightChild;
	}
	
	public void setRightChild(TreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}
} // end TreeNode