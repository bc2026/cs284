import java.util.ArrayList;
// Bhagawat Chapagain; I pledge my honor that I have abided by the Stevens Honor System.
// 02 23 2023
public class IDLList<E> {

    // Nodes head and tail mark the beginning and end of the list respectively
    // indices is an ArrayList used to keep track of the nodes by index
    private Node<E> head;
    private Node<E> tail;
    private ArrayList<Node<E>> indices;
    public int size;

    private class Node<E> {

        // Nodes have a data field, which stores the data of the node
        // Nodes have a next field, which stores a reference to the next node in the list
        // Nodes have a prev field, which stores a reference to the previous node in the list
        private E data;
        private Node<E> next;
        private Node<E> prev;

        // Node constructor that takes in an element of type E
        public Node(E elem) {
            this.data = elem;
            this.next = null;
            this.prev = null;
        }

        // Node constructor that takes in an element of type E, and the previous and next nodes
        public Node(E elem, Node<E> prev, Node<E> next) {
            this.data = elem;
            this.prev = prev;
            this.next = next;
        }

        // Returns a string representation of the node's data field
        @Override
        public String toString() {
            return String.valueOf(this.data);
        }
    }

    // Constructor that initializes an empty list
    public IDLList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
        this.indices = new ArrayList<Node<E>>();
    }

    // Adds a new node with the given element to the specified index
    public boolean add(int index, E elem) {
        if(index < 0 || index > this.size)
        {
            System.out.println("error");
        }

        Node<E> newNode = new Node<E>(elem);
        Node<E> curNode;

        // If list is empty, set the new node as head and tail
        if(head == null) {
            this.head = newNode;
            this.tail = newNode;
        }
        // If index is 0, set the new node as head
        else if (index == 0) {
            newNode.next = this.head;
            head.prev = newNode;
            this.head = newNode;
        }
        // Otherwise, add the new node at the specified index
        else {
            curNode = this.head;
            for (int i = 0; i < index - 1; i++) {
                curNode = curNode.next;
            }
            newNode.next = curNode.next;
            newNode.prev = curNode;
            if (curNode.next != null) {
                curNode.next.prev = newNode;
            } else {
                this.tail = newNode;
            }
            curNode.next = newNode;
        }

        this.size++;
        this.indices.add(index, newNode);
        return true;
    }

    // Adds a new node with the given element at the head of the list
    public boolean add(E elem) {
        Node<E> newNode = new Node<>(elem);

        // If list is empty, set the new node as head and tail
        if (this.size == 0) {
            head = newNode;
            tail = newNode;
        }
        // Otherwise, set the new node as the head and update the next and prev pointers
        else {
            head.prev = newNode;
            newNode.next = head;
            this.head = newNode;
        }

        this.size++;
        this.indices.add(0, newNode);
        return true;
    }

    public boolean append (E elem)
    {
        // If list is empty, add new element as head
        if (this.size == 0) {
            this.add(elem);
        } else {
            // Otherwise, create new node and append it to tail
            Node<E> currNode = this.tail;
            Node<E> newNode = new Node<E>(elem, currNode, currNode.next);
            currNode.next = newNode;
            this.tail = newNode;
            this.size++;
            this.indices.add(newNode); // Add new node to indices list
        }

        return true;
    }

    public E get (int index)
    {
        if (index < 0 || index > this.size-1) {
            throw new IllegalArgumentException();
        }
        return (E) this.indices.get(index).data; // Return element at given index from indices list
    }

    public E getHead()
    {
        return (E) this.head.data; // Return head element
    }

    public E getLast()
    {
        if (this.head == null && this.tail == null) {
            throw new IllegalArgumentException();
        }

        return (E) this.tail.data; // Return tail element
    }

    public E remove()
    {
        // If head is null, throw an exception
        if(head == null)
        {
            throw new IllegalStateException();
        }

        if (this.head == this.tail) {
            // If only one element, remove it and reset head and tail to null
            E dummyVal = this.head.data;
            this.head = null;
            this.tail = null;
            size--;
            this.indices.remove(0);
            return dummyVal;
        }

        // Otherwise, remove head element and set the next element as the new head
        Node<E> curNode = this.head;
        this.head = this.head.next;
        this.head.prev = null;


        size--;
        this.indices.remove(0); // Remove head node from indices list


        return curNode.data;
    }

    public E removeLast()
    {
        // If tail is null, throw an exception
        if (this.tail == null)
        {
            throw new IllegalStateException();
        }
        if (this.head == this.tail) {
            // If only one element, call remove()
            return remove();
        }

        // Otherwise, remove tail element and set the previous element as the new tail
        Node<E> curNode = this.tail;
        Node<E> currNodePrev = curNode.prev;

        currNodePrev.next = null;
        this.tail = currNodePrev;


        size--;

        indices.remove(size); // Remove tail node from indices list

        return curNode.data;
    }

    public E removeAt (int index) {
        if (index < 0 || index > this.size-1) {
            throw new IllegalArgumentException();
        }

        if (this.head == null && this.tail == null) {
            throw new IllegalArgumentException();
        }

        if (this.head == this.tail) {
            return remove();
        }

        if (index == 0) {
            return remove();
        }

        if (index == size-1) {
            return removeLast();
        }



        Node<E> currNode = this.indices.get(index);
        System.out.println(currNode);
        Node<E> currPrev = currNode.prev;
        Node<E> currNext = currNode.next;
        currPrev.next = currNode.next;
        currNext.prev = currNode.prev;

        size--;
        this.indices.remove(index);
        return currNode.data;
    }

    public boolean remove(E elem)
    {

        boolean isInIndices = false;
        int index = 0;

// Search for the element in the indices array to get the index of the node to be removed

        for (int i = 0; i < size; i++) {
            if(this.indices.get(i).data == elem)
            {
                isInIndices = true;
                index = i;
                break;
            }
        }

// If the element is found, remove it and update the size and indices array
        if(isInIndices)
        {
            this.removeAt(index);
        }
        else {
            // If the element is not found, return false
            return false;
        }

// If the element is found and removed, return true
        return true;
    }

    @Override
    public String toString() {
        System.out.println(this.indices);
        if (!(indices.isEmpty())) {

            String rArrow = "->";
            String lArrow = "<-";
            // If there are items in indices, return them ordered by index sequentially in a string surrounded by curlies
            String returnString = "null";

            Node<E> currNode = this.head;

            while (currNode != this.tail.next) {
                returnString += (lArrow);
                returnString +=  currNode.data ;
                returnString += rArrow;
                currNode = currNode.next;
            }

            returnString += "null";

            return returnString;
        }

// If there are no items in indices, return an empty set of curlies
        return "{}";

    }
}
