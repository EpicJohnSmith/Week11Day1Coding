package coding;

public class DoubleLinkley
{

    static class Node
    {
        int data;
        Node next;
        Node prev;

        Node(int data)
        {
            this.data = data;
        }
    }

    static class DoublyLinkedList
    {
        private Node head;
        private Node tail;

        // Append to end
        public void append(int data)
        {
            Node newNode = new Node(data); // Thanks for the help here, AI
            if (head == null)
            {
                head = tail = newNode;
            }
            else
            {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        }

        // Prepend to beginning
        public void prepend(int data)
        {
            Node newNode = new Node(data);
            if (head == null)
            {
                head = tail = newNode;
            }
            else
            {
                newNode.next = head; // And here too, AI helps understanding this
                head.prev = newNode;
                head = newNode;
            }
        }

        // Insert after a given node value
        public void insertAfter(int target, int data)
        {
            Node current = head;
            while (current != null && current.data != target)
            {
                current = current.next;
            }
            if (current == null) return;

            Node newNode = new Node(data);
            newNode.next = current.next;
            newNode.prev = current;
            if (current.next != null) // Confused here, AI to the rescue
            {
                current.next.prev = newNode;
            }
            else
            {
                tail = newNode;
            }
            current.next = newNode;
        }

        // Insert before a given node value
        public void insertBefore(int target, int data)
        {
            Node current = head;
            while (current != null && current.data != target)
            {
                current = current.next;
            }
            if (current == null) return;

            Node newNode = new Node(data);
            newNode.next = current;
            newNode.prev = current.prev;
            if (current.prev != null)
            {
                current.prev.next = newNode;
            }
            else
            {
                head = newNode;
            }
            current.prev = newNode;
        }

        // Remove after a given value
        public void removeAfter(int target)
        {
            Node current = head;
            while (current != null && current.data != target) // Needed help here
            {
                current = current.next;
            }
            if (current == null || current.next == null) return;

            Node toRemove = current.next;
            current.next = toRemove.next;
            if (toRemove.next != null)
            {
                toRemove.next.prev = current;
            }
            else
            {
                tail = current;
            }
        }

        // Remove before a given value
        public void removeBefore(int target)
        {
            Node current = head;
            while (current != null && current.data != target)
            {
                current = current.next;
            }
            if (current == null || current.prev == null) return;

            Node toRemove = current.prev;
            if (toRemove.prev != null)
            {
                toRemove.prev.next = current;
                current.prev = toRemove.prev;
            }
            else
            {
                head = current;
                current.prev = null;
            }
        }

        // Search for a value
        public boolean search(int value)
        {
            Node current = head;
            while (current != null)
            {
                if (current.data == value) return true;
                current = current.next;
            }
            return false;
        }

        // Sort the list using insertion sort
        public void insertionSort() // Used the insertion sort here, instead of the bubble sort
        {
            if (head == null || head.next == null) return;

            Node current = head.next;
            while (current != null)
            {
                int key = current.data;
                Node prev = current.prev;
                while (prev != null && prev.data > key)
                {
                    prev.next.data = prev.data;
                    prev = prev.prev;
                }
                if (prev == null)
                {
                    head.data = key;
                }
                else
                {
                    prev.next.data = key;
                }
                current = current.next;
            }
        }

        // Print list forward
        public void printForward() // Using AI to make this part
        {
            Node current = head;
            System.out.print("List (head -> tail): ");
            while (current != null)
            {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }

        // Print list backward
        public void printBackward()
        {
            Node current = tail;
            System.out.print("List (tail -> head): ");
            while (current != null)
            {
                System.out.print(current.data + " ");
                current = current.prev;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) // Printing this part out through AI documentation
    {
        DoublyLinkedList list = new DoublyLinkedList();

        list.append(5);
        list.append(10);
        list.append(15);
        list.prepend(1);
        list.printForward();

        list.insertAfter(10, 12);
        list.insertBefore(5, 3);
        list.printForward();

        list.removeAfter(10);
        list.printForward();

        list.removeBefore(15);
        list.printForward();

        System.out.println("Searching for 12: " + list.search(12));
        System.out.println("Searching for 15: " + list.search(15));

        list.append(8);
        list.append(2);
        list.append(20);
        System.out.println("Before sorting:");
        list.printForward();

        list.insertionSort();
        System.out.println("After sorting:");
        list.printForward();

        list.printBackward();
    }
}