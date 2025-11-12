package coding;

public class DoubleLinkley 
{
	public class DoublyLinkedList<T extends Comparable<T>> {

	    // Node class
	    private class Node {
	        T data;
	        Node prev;
	        Node next;

	        Node(T data) {
	            this.data = data;
	            this.prev = null;
	            this.next = null;
	        }
	    }

	    // Head and tail pointers
	    private Node head;
	    private Node tail;

	    // Constructor
	    public DoublyLinkedList() {
	        this.head = null;
	        this.tail = null;
	    }

	    // Append: Add node to end
	    public void append(T data) {
	        Node newNode = new Node(data);
	        if (head == null) {
	            head = tail = newNode;
	            return;
	        }
	        tail.next = newNode;
	        newNode.prev = tail;
	        tail = newNode;
	    }

	    // Prepend: Add node to beginning
	    public void prepend(T data) {
	        Node newNode = new Node(data);
	        if (head == null) {
	            head = tail = newNode;
	            return;
	        }
	        newNode.next = head;
	        head.prev = newNode;
	        head = newNode;
	    }

	    // Search by value
	    public Node search(T value) {
	        Node current = head;
	        while (current != null) {
	            if (current.data.equals(value)) {
	                return current;
	            }
	            current = current.next;
	        }
	        return null;
	    }

	    // Insert After a given value
	    public void insertAfter(T target, T data) {
	        Node current = search(target);
	        if (current == null) return;

	        Node newNode = new Node(data);
	        newNode.next = current.next;
	        newNode.prev = current;
	        if (current.next != null)
	            current.next.prev = newNode;
	        else
	            tail = newNode; // update tail if added at end
	        current.next = newNode;
	    }

	    // Insert Before a given value
	    public void insertBefore(T target, T data) {
	        Node current = search(target);
	        if (current == null) return;

	        Node newNode = new Node(data);
	        newNode.next = current;
	        newNode.prev = current.prev;
	        if (current.prev != null)
	            current.prev.next = newNode;
	        else
	            head = newNode; // update head if added at start
	        current.prev = newNode;
	    }

	    // Remove After a given value
	    public void removeAfter(T target) {
	        Node current = search(target);
	        if (current == null || current.next == null) return;

	        Node nodeToRemove = current.next;
	        current.next = nodeToRemove.next;
	        if (nodeToRemove.next != null)
	            nodeToRemove.next.prev = current;
	        else
	            tail = current; // update tail if removed last
	    }

	    // Remove Before a given value
	    public void removeBefore(T target) {
	        Node current = search(target);
	        if (current == null || current.prev == null) return;

	        Node nodeToRemove = current.prev;
	        current.prev = nodeToRemove.prev;
	        if (nodeToRemove.prev != null)
	            nodeToRemove.prev.next = current;
	        else
	            head = current; // update head if removed first
	    }

	    // Sort (using Insertion Sort)
	    public void sort() {
	        if (head == null || head.next == null) return;

	        Node current = head.next;
	        while (current != null) {
	            T key = current.data;
	            Node prevNode = current.prev;

	            while (prevNode != null && prevNode.data.compareTo(key) > 0) {
	                prevNode.next.data = prevNode.data;
	                prevNode = prevNode.prev;
	            }

	            if (prevNode == null)
	                head.data = key;
	            else
	                prevNode.next.data = key;

	            current = current.next;
	        }
	    }

	    // Display list forward
	    public void displayForward() {
	        Node current = head;
	        while (current != null) {
	            System.out.print(current.data + " <-> ");
	            current = current.next;
	        }
	        System.out.println("null");
	    }

	    // Display list backward
	    public void displayBackward() {
	        Node current = tail;
	        while (current != null) {
	            System.out.print(current.data + " <-> ");
	            current = current.prev;
	        }
	        System.out.println("null");
	    }
	}

}
