import java.util.*;


public class BinarySearchTree<T extends Comparable<T>> {
	private TreeNode<T> root;
	
	public BinarySearchTree() {
		root = null;
	} // end default constructor
	
	public BinarySearchTree(T rootItem) {
		root = new TreeNode<T>(rootItem, null, null);
	} // end constructor
	
	public void insert(T newItem) {
		root = insertItem(root, newItem);
	} // end insert
	
	public T retrieve(T searchKey) {
		return retrieveItem(root, searchKey);
	} // end retrieve
	
	public void delete(T searchKey) {
		root = deleteItem(root, searchKey);
	} // end delete
	
	private TreeNode<T> insertItem(TreeNode<T> tNode, T newItem) {
		return tNode;
		// Implement insertItem here. 
		// The error will go away once you implement and returns an node
	} // end insertItem
	
	private T retrieveItem(TreeNode<T> tNode, T searchKey) {
		return tNode.item;
		// Implement retrieveItem here
		// The error will go away once you implement and returns an item
	} // end retrieveItem
	
	private TreeNode<T> deleteItem(TreeNode<T> tNode, T searchKey) {
		return tNode;
		// Implement deleteItem here
		// The error will go away once you implement and returns a node
	} // end deleteItem
	
	public void setPreorder() {
		preorder(root);
	} // end setPreOrder
	
	public void setInorder() {
		inorder(root);
	} // end setInorder
	
	public void setPostorder() {
		postorder(root);
	} // end setPostorder
	
	private void preorder(TreeNode<T> treeNode) {
		// implement preorder here
	} // end preorder
	
	private void inorder(TreeNode<T> treeNode) {
		// implement inorder here
	} // end inorder
	
	private void postorder(TreeNode<T> treeNode) {
		// implement postorder here
	} // end postorder
	
} // end BinarySearchTree