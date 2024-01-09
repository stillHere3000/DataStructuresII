public class DriverBST {

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(15);
		
		bst.insert(5);
		bst.insert(16);
		
		//.........
		//.........
		//.........
		// Call preorder, inorder, and postorder traversals
		
		bst.delete(13);
		// Call preorder, inorder, and postorder traversals
		
		bst.delete(16);
		// Call preorder, inorder, and postorder traversals
		
		bst.delete(5);
		// Call preorder, inorder, and postorder traversals
		
	} // end main
}