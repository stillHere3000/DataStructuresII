/*
 * Author: Trevor Maliro
 * Student ID: 239498690
 * COSC2006LABI23F Lab 6 Abstract Data Type LinkedList
 * Any and all work in this file is my own.
 * 
 */

/**
 * This class contains the SingleNode. 
 * It is a single linked list implementation.
 * @author 
 * @version 
 */
public class SingleNode {
   
   
   protected int item; // The item in the node
   protected SingleNode left; // The reference to the next node
    protected SingleNode right; // The reference to the next node

   // Constructor with only the item
   public SingleNode(int item) {
       this.item = item;
       this.left = this.right = null; // Next is initially set to null
   }

   // Constructor with both the item and the next node
   public SingleNode(int item, SingleNode Left, SingleNode Right) {
       this.item = item;
       this.left = Left;
       this.right = Right;
   }
}
