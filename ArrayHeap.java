import java.util.ArrayList;

/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value.
 */
public class ArrayHeap<T> {
	private ArrayList<Node> contents = new ArrayList<Node>();

	/**
	 * Inserts an item with the given priority value. This is enqueue, or offer.
	 */
	public void insert(T item, double priority) {
		if (contents.size() == 0) {
			contents.add(0, null);
			//setNode(0, null);
			Node newNode = new Node(item, priority);
			//setNode(1, newNode);
			contents.add(1, newNode);

		} else {
			int last = contents.size();
			Node newNode = new Node(item, priority);
			contents.add(last, newNode);
			//setNode(last, newNode);
			//contents.add(last, newNode);
			bubbleUp(last);
		}
	}

	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
		// TODO Complete this method!

		return getNode(1);
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		// TODO Complete this method!

		Node output = peek();
		int lastNodeIndex = contents.size() - 1;
		swap(1, lastNodeIndex);
		//setNode(lastNodeIndex, null);
		contents.remove(lastNodeIndex);
		bubbleDown(1);

		return output;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		// TODO Complete this method!

		int size = contents.size();
		if (size == 0) {

		} else {
			for (int i = 1; i <= size - 1; i++) {
				Node target = getNode(i);
				if (target.myItem.equals(item)) {
					target.myPriority = priority;
					break;
				}
			}
		}
	}

	/**
	 * Prints out the heap sideways.
	 */
	@Override
	public String toString() {
		return toStringHelper(1, "");
	}

	/* Recursive helper method for toString. */
	private String toStringHelper(int index, String soFar) {
		if (getNode(index) == null) {
			return "";
		} else {
			String toReturn = "";
			int rightChild = getRightOf(index);
			toReturn += toStringHelper(rightChild, "        " + soFar);
			if (getNode(rightChild) != null) {
				toReturn += soFar + "    /";
			}
			toReturn += "\n" + soFar + getNode(index) + "\n";
			int leftChild = getLeftOf(index);
			if (getNode(leftChild) != null) {
				toReturn += soFar + "    \\";
			}
			toReturn += toStringHelper(leftChild, "        " + soFar);
			return toReturn;
		}
	}

	private Node getNode(int index) {
		if (index >= contents.size()) {
			return null;
		} else {
			return contents.get(index);
		}
	}

	private void setNode(int index, Node n) {
		// In the case that the ArrayList is not big enough
		// add null elements until it is the right size
		while (index + 1 >= contents.size()) {
			contents.add(null);
		}
		contents.set(index, n);
	}

	/**
	 * Swap the nodes at the two indices.
	 */
	private void swap(int index1, int index2) {
		Node node1 = getNode(index1);
		Node node2 = getNode(index2);
		this.contents.set(index1, node2);
		this.contents.set(index2, node1);
	}

	/**
	 * Returns the index of the node to the left of the node at i.
	 */
	private int getLeftOf(int i) {
		// TODO Complete this method!

		return 2 * i;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		// TODO Complete this method!

		return 2 * i + 1;
	}

	/**
	 * Returns the index of the node that is the parent of the node at i.
	 */
	private int getParentOf(int i) {
		// TODO Complete this method!

		// if it is root, return index 0
		if (i == 1) {
			return 0;
		}
		if (i % 2 == 0) {
			return i / 2;
		} else {
			return (i - 1) / 2;
		}
	}

	/**
	 * Adds the given node as a left child of the node at the given index.
	 */
	private void setLeft(int index, Node n) {
		// TODO Complete this method!

		contents.set(2 * index, n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
		// TODO Complete this method!

		contents.set(2 * index + 1, n);
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		// TODO Complete this method!

		int parent = getParentOf(index);
		if (parent > 0) {
			int smaller = min(index, parent);
			if (smaller == index) {
				swap(index, parent);
				bubbleUp(parent);
			}
		}
	}
/*
		int parent = getParentOf(index);
		if (parent <= 0) {

		} else {
			double leftPri;
			double rightPri;
			double parentPri;
			if (index % 2 == 0) {
				leftPri = getNode(index).priority();
				//rightPri = getNode(index + 1).priority();
				Node rightNode = getNode(index + 1);
				parentPri = getNode(index / 2).priority();

				if (rightNode == null) {
					if (leftPri < parentPri) {
						swap(index, parent);
						bubbleUp(parent);
					}
				} else {
					rightPri = rightNode.priority();
					if (leftPri < rightPri && leftPri < parentPri) {
						swap(index, parent);
						bubbleUp(parent);
					} else if (leftPri < parentPri && leftPri > rightPri && rightPri < parentPri) {
						swap(index + 1, parent);
						bubbleUp(parent);
					}
				}

			} else if (index % 2 != 0) {
				rightPri = getNode(index).priority();
				//leftPri = getNode(index - 1).priority();
				Node leftNode = getNode(index - 1);
				parentPri = getNode((index - 1) / 2).priority();

				if (leftNode == null) {
					if (rightPri < parentPri) {
						swap(index, parent);
						bubbleUp(parent);
					}
				} else {
					leftPri = leftNode.priority();
					if (rightPri < leftPri && rightPri < parentPri) {
						swap(index, parent);
						bubbleUp(parent);
					} else if (rightPri > leftPri && rightPri < parentPri && leftPri < parentPri) {
						swap(index - 1, parent);
						bubbleUp(parent);
					}
				}
			}
		}
	}
*/

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		// TODO Complete this method!

		if (index == 0) {

		} else if (getNode(index) == null) {

		} else {
			int left = getLeftOf(index);
			int right = getRightOf(index);
			Node leftNode = getNode(left);
			Node rightNode = getNode(right);
			double curPri = getNode(index).priority();

			if (leftNode == null && rightNode == null) {

			} else if (leftNode != null && rightNode == null) {
				if (curPri > leftNode.priority()) {
					swap(index, left);
					bubbleDown(left);
				} else {

				}
			} else if (leftNode == null && rightNode != null) {
				if (curPri > rightNode.priority()) {
					swap(index, right);
					bubbleDown(right);
				} else {

				}
			} else {
				double leftPri = leftNode.priority();
				double rightPri = rightNode.priority();

				if (curPri > leftPri && curPri > rightPri && leftPri <= rightPri) {
					swap(index, left);
					bubbleDown(left);
				} else if (curPri > leftPri && curPri > rightPri && rightPri < leftPri) {
					swap(index, right);
					bubbleDown(right);
				} else if (curPri <= leftPri && curPri > rightPri) {
					swap(index, right);
					bubbleDown(right);
				} else if (curPri > leftPri && curPri <= rightPri) {
					swap(index, left);
					bubbleDown(left);
				} else {

				}
			}
		}

	}

	/**
	 * Returns the index of the node with smaller priority. Precondition: Not
	 * both of the nodes are null.
	 */
	private int min(int index1, int index2) {
		Node node1 = getNode(index1);
		Node node2 = getNode(index2);
		if (node1 == null) {
			return index2;
		} else if (node2 == null) {
			return index1;
		} else if (node1.myPriority < node2.myPriority) {
			return index1;
		} else {
			return index2;
		}
	}

	public class Node {
		private T myItem;
		private double myPriority;

		private Node(T item, double priority) {
			myItem = item;
			myPriority = priority;
		}

		public T item() {
			return myItem;
		}

		public double priority() {
			return myPriority;
		}

		@Override
		public String toString() {
			return item().toString() + ", " + priority();
		}
	}

	public static void main(String[] args) {
		ArrayHeap<String> heap = new ArrayHeap<String>();
		heap.insert("c", 3);
		heap.insert("i", 9);
		heap.insert("g", 7);
		heap.insert("d", 4);
		heap.insert("a", 1);
		heap.insert("h", 8);
		heap.insert("e", 5);
		heap.insert("b", 2);
		heap.insert("c", 3);
		heap.insert("d", 4);
		System.out.println(heap);
	}

}
