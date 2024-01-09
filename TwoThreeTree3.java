public class TwoThreeTree<T extends Comparable<T>> {

	private TreeNode<T> root;
	private boolean inserted;

	public TwoThreeTree() {
		//no-arg constructor
		root = null;
		inserted = false;
	}

	public TwoThreeTree(T item) {
		//constructor with an item
		root = new TreeNode<T>(item);
		inserted = false;
	}

	public void insert(T insertKey) {
		root = insertItem(root, insertKey);
		inserted = false;
	} // end insert

	public void retrieve(T searchKey) {
		TreeNode<T> treeNode = retrieveItem(root, searchKey);
		if (treeNode == null) {
			if (retrieveItem2(root, searchKey) == null) {
				System.out.println(searchKey + " is not present in 2-3 tree.");
			}
			else {
				System.out.println(searchKey + " is present in 2-3 tree.");
			}
			
		} else {
			System.out.println(searchKey + " is present in 2-3 tree.");
		}
	} // end retrieve

	private TreeNode<T> findRightInsertionNode(TreeNode<T> treeNode, T insertKey) {
		if (treeNode.isLeaf()) {
			return treeNode;
		}
	
		if (treeNode.isTwoNode()) {
			if (insertKey.compareTo(treeNode.getSmallItem()) < 0) {
				return findRightInsertionNode(treeNode.getLeftChild(), insertKey);
			} else {
				return findRightInsertionNode(treeNode.getRightChild(), insertKey);
			}
		} else {
			if (insertKey.compareTo(treeNode.getSmallItem()) < 0) {
				return findRightInsertionNode(treeNode.getLeftChild(), insertKey);
			} else if (insertKey.compareTo(treeNode.getLargeItem()) < 0) {
				return findRightInsertionNode(treeNode.getMidChild(), insertKey);
			} else {
				return findRightInsertionNode(treeNode.getRightChild(), insertKey);
			}
		}
	}

	private TreeNode<T> insertItem(TreeNode<T> treeNode, T insertKey) {
		if (treeNode == null) {
			TreeNode<T> temp= new TreeNode<T>(insertKey);
			root = temp;
			return temp;
		}
	
		// Split if current is a 3-node
		if (treeNode.isThreeNode()) {
			TreeNode oldNode = treeNode;
			
			split(treeNode, insertKey); // Needs to be implemented
			// After split, treeNode may not be the same, find the correct node to continue
			TreeNode newNode = retrieveItem(treeNode, insertKey);
			// Insert in left or right child
			
		}else{
	
			if(treeNode.isTwoNode()) {
				// Insert in current node
				if (insertKey.compareTo(treeNode.getSmallItem()) < 0) {
					treeNode.setLargeItem(treeNode.getSmallItem());
					treeNode.setSmallItem(insertKey);
				} else {
					treeNode.setLargeItem(insertKey);
				}
			} else {
				// Insert in left or right child
				if (insertKey.compareTo(treeNode.getSmallItem()) < 0) {
					treeNode.setLeftChild(insertItem(treeNode.getLeftChild(), insertKey));
				} else if (insertKey.compareTo(treeNode.getLargeItem()) < 0) {
					treeNode.setMidChild(insertItem(treeNode.getMidChild(), insertKey));
				} else {
					treeNode.setRightChild(insertItem(treeNode.getRightChild(), insertKey));
				}
			}
		}
	
		return treeNode;
	
	}

	
	
	private TreeNode<T> retrieveItem(TreeNode<T> treeNode, T searchKey) {
		if (treeNode == null) {
			return null;
		}
	
		int cmp = searchKey.compareTo(treeNode.getSmallItem());
		if (cmp == 0) {
			return treeNode;
		} else if (cmp < 0) {
			return retrieveItem(treeNode.getLeftChild(), searchKey);
		} else {
			if (treeNode.isThreeNode() && searchKey.compareTo(treeNode.getLargeItem()) == 0) {
				return treeNode;
			}
			return retrieveItem((cmp < 0) ? treeNode.getMidChild() : treeNode.getRightChild(), searchKey);
		}
	}

	private TreeNode<T> retrieveItem2(TreeNode<T> treeNode, T searchKey) {
		if (treeNode == null) {
			return null;
		}
	
		int cmp = searchKey.compareTo(treeNode.getLargeItem());
		if (cmp == 0) {
			return treeNode;
		} else if (cmp < 0) {
			return retrieveItem(treeNode.getLeftChild(), searchKey);
		} else {
			if (treeNode.isThreeNode() && searchKey.compareTo(treeNode.getLargeItem()) == 0) {
				return treeNode;
			}
			return retrieveItem((cmp > 0) ? treeNode.getMidChild() : treeNode.getRightChild(), searchKey);
		}
	}

	

	/**
	 * Splits a TreeNode in a 2-3 Tree based on the given item.
	 * 
	 * @param treeNode the TreeNode to be split
	 * @param item the item to be inserted into the tree
	 */
	private void split(TreeNode<T> treeNode, T item) {

		// if there is not parent of treeNode
		if (treeNode.getParentNode() == null) {

			// if the item less than the treeNode(smallItem) item
			if (treeNode.getSmallItem().compareTo(item) > 0) {

				// create new treeNode left
				TreeNode<T> left = new TreeNode<T>();

				// set treeNode as left's parent
				left.setParentNode(treeNode);

				// set small item of left
				left.setSmallItem(item);

				// if treeNode has already a left child, we attach the left Child of treeNode to
				// the new left Node
				if (treeNode.getLeftChild() != null) {
					treeNode.getLeftChild().setParentNode(left);
				}

				// attaching the left child of TreeNode<T> as a left child of our left node
				left.setLeftChild(treeNode.getLeftChild());

				// if treeNode has already a mid child, we attach the mid Child of treeNode to
				// the new left Node
				if (treeNode.getMidChild() != null) {
					treeNode.getMidChild().setParentNode(left);
				}

				// attach middle child
				left.setMidChild(treeNode.getTempChild());

				// set the left node to the left of treeNode
				treeNode.setLeftChild(left);

				// we do same with right node as we just did with the left node
				TreeNode<T> right = new TreeNode<T>();

				// set right's parent
				right.setParentNode(treeNode);

				// shift the largeItem of treeNode to the smallItem of right
				right.setSmallItem(treeNode.getLargeItem());

				// if treeNode has already a right child, we attach the right Child of treeNode
				// to the new right Node
				if (treeNode.getRightChild() != null) {
					treeNode.getRightChild().setParentNode(right);
				}

				// attaching the midChild of TreeNode<T> as a left child of our right node
				right.setLeftChild(treeNode.getMidChild());

				// if the tempChild is not null, we attach the temp Child of treeNode to the new
				// right Node
				if (treeNode.getTempChild() != null) {
					treeNode.getTempChild().setParentNode(right);
				}

				// set the mid child of right
				right.setMidChild(treeNode.getRightChild());

				// set the child of treeNode
				treeNode.setTempChild(null);
				treeNode.setMidChild(right);
				treeNode.setLargeItem(null);
				treeNode.setRightChild(null);

			}

			// if item is greater than smallItem and smaller than large Item
			else if (treeNode.getSmallItem().compareTo(item) < 0 && treeNode.getLargeItem().compareTo(item) > 0) {

				// do the same while maintaining the properties of 2-3 Tree
				TreeNode<T> left = new TreeNode<T>();
				left.setParentNode(treeNode);
				left.setSmallItem(treeNode.getSmallItem());
				if (treeNode.getLeftChild() != null) {
					treeNode.getLeftChild().setParentNode(left);
				}
				left.setLeftChild(treeNode.getLeftChild());
				if (treeNode.getMidChild() != null) {
					treeNode.getMidChild().setParentNode(left);
				}
				left.setMidChild(treeNode.getMidChild());
				treeNode.setLeftChild(left);

				treeNode.setSmallItem(item);

				TreeNode<T> right = new TreeNode<T>();
				right.setParentNode(treeNode);
				right.setSmallItem(treeNode.getLargeItem());
				if (treeNode.getRightChild() != null) {
					treeNode.getRightChild().setParentNode(right);
				}
				right.setLeftChild(treeNode.getRightChild());
				if (treeNode.getTempChild() != null) {
					treeNode.getTempChild().setParentNode(right);
				}
				right.setMidChild(treeNode.getTempChild());

				treeNode.setTempChild(null);
				treeNode.setMidChild(right);

				treeNode.setLargeItem(null);
				treeNode.setRightChild(null);

			}

			// if item is greater than the large item again do the same process
			else if (treeNode.getLargeItem().compareTo(item) < 0) {
				TreeNode<T> left = new TreeNode<T>();
				left.setParentNode(treeNode);
				left.setSmallItem(treeNode.getSmallItem());
				if (treeNode.getLeftChild() != null) {
					treeNode.getLeftChild().setParentNode(left);
				}
				left.setLeftChild(treeNode.getLeftChild());
				if (treeNode.getMidChild() != null) {
					treeNode.getMidChild().setParentNode(left);
				}
				left.setMidChild(treeNode.getMidChild());
				treeNode.setLeftChild(left);
				treeNode.setSmallItem(treeNode.getLargeItem());
				treeNode.setLargeItem(null);

				TreeNode<T> right = new TreeNode<T>();
				right.setParentNode(treeNode);
				right.setSmallItem(item);
				if (treeNode.getRightChild() != null) {
					treeNode.getRightChild().setParentNode(right);
				}
				right.setLeftChild(treeNode.getRightChild());
				if (treeNode.getTempChild() != null) {
					treeNode.getTempChild().setParentNode(right);
				}
				right.setMidChild(treeNode.getTempChild());

				treeNode.setTempChild(null);
				treeNode.setMidChild(right);

				treeNode.setRightChild(null);
			}
		}
		// if the root has 2 children but it is the root this time
		else if (treeNode.getParentNode().getLargeItem() == null) {

			// if the item less than the treeNode(smallItem) item
			if (treeNode.getSmallItem().compareTo(item) > 0) {
				TreeNode<T> largest = new TreeNode<T>();
				largest.setParentNode(treeNode.getParentNode());
				largest.setSmallItem(treeNode.getLargeItem());
				largest.setLeftChild(treeNode.getRightChild());
				treeNode.setRightChild(null);
				largest.setMidChild(treeNode.getTempChild());
				treeNode.setTempChild(null);
				treeNode.getParentNode().setRightChild(largest);
				treeNode.getParentNode().setMidChild(treeNode);
				treeNode.setLargeItem(null);
				treeNode.getParentNode().setLargeItem(treeNode.getSmallItem());
				treeNode.setSmallItem(item);

			}
			// if the item greater than the treeNode(smallItem) item but less than treeNode
			// (LargeItem)
			else if (treeNode.getSmallItem().compareTo(item) < 0 && treeNode.getLargeItem().compareTo(item) > 0) {
				TreeNode<T> largest = new TreeNode<T>();
				largest.setParentNode(treeNode.getParentNode());
				largest.setSmallItem(treeNode.getLargeItem());
				largest.setLeftChild(treeNode.getTempChild());

				largest.setMidChild(treeNode.getRightChild());
				treeNode.setRightChild(null);
				treeNode.setTempChild(null);
				treeNode.getParentNode().setRightChild(treeNode.getParentNode().getMidChild());
				treeNode.getParentNode().setMidChild(largest);

				treeNode.setLargeItem(null);
				treeNode.getParentNode().setLargeItem(treeNode.getParentNode().getSmallItem());
				treeNode.getParentNode().setSmallItem(item);
			}
			// if the item greater than the treeNode(largeItem)
			else if (treeNode.getLargeItem().compareTo(item) < 0) {
				TreeNode<T> largest = new TreeNode<T>();
				largest.setParentNode(treeNode.getParentNode());
				largest.setSmallItem(item);
				largest.setLeftChild(treeNode.getRightChild());
				treeNode.setRightChild(null);
				largest.setMidChild(treeNode.getTempChild());
				treeNode.setTempChild(null);
				treeNode.getParentNode().setRightChild(treeNode.getParentNode().getMidChild());
				treeNode.getParentNode().setMidChild(largest);

				treeNode.getParentNode().setLargeItem(treeNode.getParentNode().getSmallItem());
				treeNode.getParentNode().setSmallItem(treeNode.getLargeItem());
				treeNode.setLargeItem(null);
			}
		}

		// finally the parent of the node that is being split is full
		else {
			// a new item for recursive call
			T newKey = null;
			if (treeNode.getSmallItem().compareTo(item) > 0) {
				// create a temporary node
				TreeNode<T> tempNode = new TreeNode<T>();

				// set parent of temporary Node
				tempNode.setParentNode(treeNode.getParentNode());

				// set the small item of tempNode
				tempNode.setSmallItem(treeNode.getLargeItem());

				// set the tempNode leftChild
				tempNode.setLeftChild(treeNode.getRightChild());

				// set right child of treeNode to null
				treeNode.setRightChild(null);

				// set treeNode tempChild
				tempNode.setMidChild(treeNode.getTempChild());

				// set the treeNode's tempChild to null
				treeNode.setTempChild(null);
				treeNode.getParentNode().setTempChild(tempNode);

				// for recursive call
				newKey = treeNode.getSmallItem();

				// set treeNode smallItem
				treeNode.setSmallItem(item);

				// set largeItem of treeNode to null
				treeNode.setLargeItem(null);

			}

			// if the item greater than the treeNode(smallItem) item but less than treeNode
			// (LargeItem)
			else if (treeNode.getSmallItem().compareTo(item) < 0 && treeNode.getLargeItem().compareTo(item) > 0) {
				TreeNode<T> tempNode = new TreeNode<T>();
				tempNode.setParentNode(treeNode.getParentNode());
				tempNode.setSmallItem(treeNode.getLargeItem());
				tempNode.setLeftChild(treeNode.getTempChild());
				treeNode.setTempChild(null);
				tempNode.setMidChild(treeNode.getRightChild());
				treeNode.setRightChild(null);
				treeNode.getParentNode().setTempChild(tempNode);

				treeNode.setLargeItem(null);
				newKey = item;
			}

			// if the item greater than the treeNode(largeItem)
			else if (treeNode.getLargeItem().compareTo(item) < 0) {
				// do the same
				// create new temporary node
				TreeNode<T> tempNode = new TreeNode<T>();

				// set tempNode parent
				tempNode.setParentNode(treeNode.getParentNode());

				// set small item of tempNode
				tempNode.setSmallItem(item);

				// set the leftChild of tempNode
				tempNode.setLeftChild(treeNode.getRightChild());

				treeNode.setRightChild(null);
				tempNode.setMidChild(treeNode.getTempChild());
				treeNode.setTempChild(null);
				treeNode.getParentNode().setTempChild(tempNode);

				newKey = treeNode.getLargeItem();
				treeNode.setLargeItem(null);
			}
			split(treeNode.getParentNode(), newKey); // recursively split
		}
	}
	
	public void setPreorder() {
		preorder(root);
	} // setPreOrder
	
	public void setInorder() {
		inorder(root);
	} // end setInorder
	
	public void setPostorder() {
		postorder(root);
	} // end setPostorder

	private void preorder(TreeNode<T> treeNode) {
		if (treeNode != null) {
			System.out.print(treeNode.getSmallItem() + " ");
			if (treeNode.isThreeNode()) {
				System.out.print(treeNode.getLargeItem() + " ");
			}
			preorder(treeNode.getLeftChild());
			preorder(treeNode.getMidChild());
			preorder(treeNode.getRightChild());
		}
	}
	
	private void inorder(TreeNode<T> treeNode) {
		if (treeNode != null) {
			inorder(treeNode.getLeftChild());
			System.out.print(treeNode.getSmallItem() + " ");
			inorder(treeNode.getMidChild());
			if (treeNode.isThreeNode()) {
				System.out.print(treeNode.getLargeItem() + " ");
				inorder(treeNode.getRightChild());
			}
		}
	}
	
	private void postorder(TreeNode<T> treeNode) {
		if (treeNode != null) {
			postorder(treeNode.getLeftChild());
			postorder(treeNode.getMidChild());
			if (treeNode.isThreeNode()) {
				postorder(treeNode.getRightChild());
			}
			System.out.print(treeNode.getSmallItem() + " ");
			if (treeNode.isThreeNode()) {
				System.out.print(treeNode.getLargeItem() + " ");
			}
		}
	}
	
}