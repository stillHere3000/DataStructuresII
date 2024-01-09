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
import java.util.*;
import java.util.Iterator;


public class BinarySearchTree<T extends Comparable<T>> {
	private TreeNode<T> root;
	
	public BinarySearchTree() {
		root = null;
	} // end default constructor
	
	public BinarySearchTree(T rootItem) {
		root = new TreeNode<T>(rootItem, null, null);
	} // end constructor
	
	public void insert(T newItem) {
		//root = insertItem(root, newItem);
		TreeNode<T> newNode;		
		if (root == null)
			root= insertItem(null, newItem);
		else
			newNode = insertItem(root, newItem);
	} // end insert
	
	public T retrieve(T searchKey) {
		return retrieveItem(root, searchKey);
	} // end retrieve
	
	public void delete(T searchKey) {
		root = deleteItem(root, searchKey, new TreeNode<T>(null));
	} // end delete
	
	private TreeNode<T> insertItem(TreeNode<T> tNode, T newItem) {
		T result = null; TreeNode<T> newNode=null;
		if (tNode == null){ // Tree is empty
			newNode= new TreeNode<T>(newItem);
			return newNode;
		}
		else{
			result = retrieveItem(root, newItem);
			if(result == null){
				int comparison = newItem.compareTo(tNode.getItem());
				if (comparison == 0){
					result = tNode.getItem();
					tNode.setItem(newItem);
				}
				else if (comparison < 0){
					if (tNode.getLeftChild() != null)
						newNode = insertItem(tNode.getLeftChild(), newItem);
					else
						tNode.setLeftChild(new TreeNode<T>(newItem));
				}
				else{
					if (tNode.getRightChild() != null)
						newNode = insertItem(tNode.getRightChild(), newItem);
					else
						tNode.setRightChild(new TreeNode<T>(newItem));
				} // end i
			}
			else{
				if (tNode.getRightChild() != null)
					newNode = insertItem(tNode.getRightChild(), newItem);
				else
					tNode.setRightChild(new TreeNode<T>(newItem));
			}			
		}
		return newNode;
	} // end insertItem
	
	private T retrieveItem(TreeNode<T> tNode, T searchKey) {
		T result = null;
		if (tNode != null){
			T rootEntry = tNode.getItem();
			if (searchKey.equals(rootEntry))
				result = rootEntry;
			else if (searchKey.compareTo(rootEntry) < 0)
				result = retrieveItem(tNode.getLeftChild(), searchKey);
			else
				result = retrieveItem(tNode.getRightChild(), searchKey);
		} // end if
		return result;
	} // end retrieveItem
	
	private TreeNode<T> deleteItem(TreeNode<T> tNode, T searchKey, TreeNode<T> oldNode) {
		T result = null; //TreeNode<T> oldNode;
		//return tNode;
		// Implement deleteItem here
		// The error will go away once you implement and returns a node
		if (tNode != null){
			T rootData = tNode.getItem();
			int comparison = searchKey.compareTo(rootData);
			if (comparison == 0){ // anEntry == root entry			
				oldNode.setItem(tNode.getItem());
				tNode = removeFromRoot(tNode);
			}
			else if (comparison < 0) // searchKey < root entry
			{
				TreeNode<T> leftChild = tNode.getLeftChild();
				TreeNode<T> subtreeRoot = deleteItem(leftChild, searchKey, oldNode);
				tNode.setLeftChild(subtreeRoot);
			}
			else{ // SearchKey > root entry
			
				TreeNode<T> rightChild = tNode.getRightChild();
				// A different way of coding than for left child:
				tNode.setRightChild(deleteItem(rightChild, searchKey, oldNode));
			} // end if
		} // end if
		return tNode;

	} // end deleteItem

	// Removes the entry in a given root node of a subtree.
	// returns 
	private TreeNode<T> removeFromRoot(TreeNode<T> rootNode){
		// Case 1: rootNode has two children
		if (rootNode.leftChild != null && rootNode.rightChild != null){
			// Find node with largest entry in left subtree
			System.out.println(rootNode.getItem());
			if(rootNode.getRightChild() != null){
				TreeNode<T> rightSubtreeRoot = rootNode.getRightChild();
				TreeNode<T> leftSubtreeRoot2 = rootNode.getLeftChild();

				TreeNode<T> largestNode = findLargest2(rightSubtreeRoot);
				System.out.println(rootNode.getItem());
				System.out.println(rightSubtreeRoot.getItem());
				System.out.println(largestNode.getItem());

				rootNode.setItem(largestNode.getItem());
				//largestNode.setRightChild(largestNode);
				//rootNode = largestNode;
				//rootNode.setRightChild(rightSubtreeRoot);
				// Remove node with largest entry in left subtree
				largestNode.setItem(null);;
				
				
			}else{
				TreeNode<T> leftSubtreeRoot = rootNode.getLeftChild();
				TreeNode<T> largestNode = findLargest(rootNode);
				rootNode.setItem(largestNode.getItem());
				// Remove node with largest entry in left subtree
				rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
				// Replace entry in root
				System.out.println(leftSubtreeRoot.getItem());
				System.out.println(largestNode.getItem());
			}
		} // end if
		// Case 2: rootNode has at most one child
		else if (rootNode.rightChild != null)
				rootNode = rootNode.getRightChild();
			else
				rootNode = rootNode.getLeftChild();
		// Assertion: If rootNode was a leaf, it is now null
		return rootNode;
	} // end removeEntry

	// Finds the node containing the largest entry in a given tree.
	// rootNode is the root node of the tree.
	// Returns the node containing the largest entry in the tree.
	private TreeNode<T> findLargest(TreeNode<T> rootNode){
		if (rootNode.rightChild != null)
			rootNode = findLargest(rootNode.getRightChild());
		return rootNode;
	} // end findLargest

	// Finds the node containing the largest entry in a given tree.
	// rootNode is the root node of the tree.
	// Returns the node containing the largest entry in the tree.
	private TreeNode<T> findLargest2(TreeNode<T> rootNode){
		// if (rootNode.rightChild == null && rootNode.leftChild == null)
		// 	return rootNode;
		do{
			 if (rootNode.leftChild != null)
				rootNode= rootNode.getLeftChild();
		}
		while ( rootNode.leftChild != null);

		return rootNode;
	} 

	// Removes the node containing the largest entry in a given tree.
	// rootNode is the root node of the tree.
	// Returns the root node of the revised tree.
	private TreeNode<T> removeLargest(TreeNode<T> rootNode){
		if (rootNode.rightChild != null){
			TreeNode<T> rightChild = rootNode.getRightChild();
			//rightChild = removeLargest(rightChild);
			rootNode = rightChild;
			if(rightChild != null && rightChild.rightChild != null)
				rightChild.rightChild=  null;

			if(rightChild != null && rightChild.leftChild != null)
				rightChild.leftChild = null;


			//rootNode.leftChild = null;

		}
		else
			rootNode = rootNode.getLeftChild();
		return rootNode;
	} // end removeLargest

	
	public void setPreorder() {
		System.out.print("Pre Order travesal of the Tree:");
		preorder(root);
	} // end setPreOrder
	
	public void setInorder() {
		System.out.print("In Order travesal of the Tree:");
		inorder(root);
	} // end setInorder
	
	public void setPostorder() {
		System.out.print("Post Order travesal of the Tree:");
		postorder(root);
	} // end setPostorder
	
	private void preorder(TreeNode<T> treeNode) {
		if (treeNode == null)
            return;

        // Visit the node
        System.out.print(treeNode.getItem() + " ");

        if(treeNode.getLeftChild() != null)
            preorder(treeNode.getLeftChild());

        if(treeNode.getRightChild() != null)
            preorder(treeNode.getRightChild());
	} // end preorder
	
	private void inorder(TreeNode<T> treeNode) {
		
		if (root != null) { // CHECK bst NOT EMPTY
			if(treeNode.getLeftChild() != null)
            	inorder(treeNode.getLeftChild());
			if(treeNode.getItem() != null)
            	System.out.print(treeNode.getItem() + " ");
			if(treeNode.getRightChild() != null)
            	inorder(treeNode.getRightChild());
        }
	} // end inorder
	
	private void postorder(TreeNode<T> treeNode) {
		if (treeNode == null)
            return;

        // Traverse the left subtree
		if (treeNode.getLeftChild() != null)
        	postorder(treeNode.getLeftChild());

        // Traverse the right subtree
		if (treeNode.getRightChild() != null)
        	postorder(treeNode.getRightChild());

        // Visit the node
        System.out.print(treeNode.getItem() + " ");
    
	} // end postorder
	
} // end BinarySearchTree