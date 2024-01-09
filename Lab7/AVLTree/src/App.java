public class App {
     // Driver method
     /*
      * 
      */
     public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        /* Constructing tree given in the question */
        tree.root = tree.insert(tree.root, 10);        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);        tree.root = tree.insert(tree.root, 25);

        // The AVL Tree after insertion of 10, 20, 30, 40, 50, 25
        System.out.println("Preorder traversal of constructed tree is : ");
        tree.preOrder(tree.root);
    }
}
