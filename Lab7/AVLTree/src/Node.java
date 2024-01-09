/**
 * Represents a node in an AVL tree.
 */
class Node {
    int key, height;
    Node left, right;

    /**
     * Constructs a new Node with the specified key.
     * 
     * @param key the key value of the node
     */
    Node(int key) {
        this.key = key;
        height = 1;
    }
}

