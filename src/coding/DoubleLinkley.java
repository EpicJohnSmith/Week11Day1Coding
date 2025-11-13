package coding;

public class DoublyLinkedList<T extends Comparable<T>> {

    /**
     * Inner Node class representing a single element of the list.
     */
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
    private int size;

    // Constructor
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // ------------------------------------------------------------
    //  BASIC OPERATIONS
    // ------------------------------------------------------------

    /** Add a new node to the end of the list. */
    public void append(T data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /** Add a new node to the start of the list. */
    public void prepend(T data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /** Check if the list is empty. */
    public boolean isEmpty() {
        return head == null;
    }

    /** Return the number of elements in the list. */
    public int size() {
        return size;
    }

    // ------------------------------------------------------------
    //  SEARCH & INSERT OPERATIONS
    // ------------------------------------------------------------

    /** Find and return the node containing the specified value. */
    private Node search(T value) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(value)) return current;
            current = current.next;
        }
        return null;
    }

    /** Insert a new node after the first node containing target. */
    public void insertAfter(T target, T data) {
        Node current = search(target);
        if (current == null) return; // not found

        Node newNode = new Node(data);
        newNode.next = current.next;
        newNode.prev = current;

        if (current.next != null) {
            current.next.prev = newNode;
        } else {
            tail = newNode; // inserting at end
        }
        current.next = newNode;
        size++;
    }

    /** Insert a new node before the first node containing target. */
    public void insertBefore(T target, T data) {
        Node current = search(target);
        if (current == null) return; // not found

        Node newNode = new Node(data);
        newNode.next = current;
        newNode.prev = current.prev;

        if (current.prev != null) {
            current.prev.next = newNode;
        } else {
            head = newNode; // inserting at start
        }
        current.prev = newNode;
        size++;
    }

    // ------------------------------------------------------------
    //  REMOVE OPERATIONS
    // ------------------------------------------------------------

    /** Remove the node that comes after the node containing target. */
    public void removeAfter(T target) {
        Node current = search(target);
        if (current == null || current.next == null) return;

        Node nodeToRemove = current.next;
        current.next = nodeToRemove.next;

        if (nodeToRemove.next != null) {
            nodeToRemove.next.prev = current;
        } else {
            tail = current;
        }

        size--;
    }

    /** Remove the node that comes before the node containing target. */
    public void removeBefore(T target) {
        Node current = search(target);
        if (current == null || current.prev == null) return;

        Node nodeToRemove = current.prev;
        current.prev = nodeToRemove.prev;

        if (nodeToRemove.prev != null) {
            nodeToRemove.prev.next = current;
        } else {
            head = current;
        }

        size--;
    }

    /** Remove the first node with the given value. */
    public void remove(T value) {
        Node current = search(value);
        if (current == null) return;

        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            head = current.next;
        }

        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }

        size--;
    }

    /** Clear the entire list. */
    public void clear() {
        head = tail = null;
        size = 0;
    }

    // ------------------------------------------------------------
    //  SORTING (INSERTION SORT)
    // ------------------------------------------------------------

    /**
     * Sort the list in ascending order using Insertion Sort.
     * Rearranges node links instead of just copying data.
     */
    public void sort() {
        if (head == null || head.next == null) return;

        Node current = head.next;
        while (current != null) {
            Node next = current.next;
            Node search = current.prev;

            // Detach current temporarily
            if (current.prev != null) current.prev.next = current.next;
            if (current.next != null) current.next.prev = current.prev;

            // Move backwards until correct position found
            while (search != null && search.data.compareTo(current.data) > 0) {
                search = search.prev;
            }

            // Reinsert current
            if (search == null) {
                // Insert at beginning
                current.next = head;
                head.prev = current;
                head = current;
                current.prev = null;
            } else {
                // Insert after search
                current.next = search.next;
                if (search.next != null) search.next.prev = current;
                search.next = current;
                current.prev = search;
            }

            current = next;
        }

        // Update tail reference
        Node temp = head;
        while (temp.next != null) temp = temp.next;
        tail = temp;
    }

    // ------------------------------------------------------------
    //  DISPLAY OPERATIONS
    // ------------------------------------------------------------

    /** Print the list from head to tail. */
    public void displayForward() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.data).append(" <-> ");
            current = current.next;
        }
        sb.append("null");
        System.out.println(sb);
    }

    /** Print the list from tail to head. */
    public void displayBackward() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        Node current = tail;
        while (current != null) {
            sb.append(current.data).append(" <-> ");
            current = current.prev;
        }
        sb.append("null");
        System.out.println(sb);
    }
}