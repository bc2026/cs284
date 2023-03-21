    public void addFirst(E item) {
        head = new Node<>(item, head);
        size++;
    }

    public void add(E item, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add: index out of bounds");
        }
        if (index == 0) {
            addFirst(item);

        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            current.next = new Node<>(item, current.next);
            size++;
        }
    }

    public boolean add(E item) {
        add(item, size);
        return true;
    }

    public E getAt(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("getAt: index out of bounds");
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    public void removeFirst() {
        if (head == null) {
            throw new IllegalStateException("removeFirst: list is empty");
        }
        head = head.next;
        size--;
    }

    public void remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("remove: index out of bounds");
        }
        if (index == 0) {
            removeFirst();
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            current.next = current.next.next;
            size--;
        }
    }
    /**
     * Use previously defined SLL operations
     */
    public SLL<E> clone() {
        SLL<E> result = new SLL<>();

        for (int i = 0; i < size; i++) {
            result.add(getAt(i), i);
        }

        return result;
    }

    public
    SLL<E> clone2() {
        SLL<E> result = new SLL<>();
        Node<E> current = head;

        for (int i = 0; i < size; i++) {
            result.add((E) current.data, i);
            current = current.next;
        }

        return result;
    }

    /**
     * Do NOT use previously defined SLL operations
     */
    public
    SLL<E> clone3() {
        SLL<E> result = new SLL<>();

        Node<E> current = head;
        Node<E> newCurrent = new Node<>(null);
        Node<E> dummy = newCurrent;

        while (current != null) {
            newCurrent.next = (Node<E>)new Node<>(current.data);
            current = current.next;
            newCurrent = newCurrent.next;
        }

        result.head = dummy.next;
        result.size = size;
        return result;
    }

    /**
     * Drops the suffix of the recipient list starting from the node at index
     * count If count is greater than the size of the list, nothing is dropped
     *
     * @param count
     */
    public void take(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("take: negative argument");
        }
        if (count == 0 || head == null) {
            head = null;
            size = 0;
            return;
        }
        // count>0 and the list is not empty

        Node<E> current = head;
        int i = 1;
        while (current.next != null && i < count) {
            current = current.next;
            i++;
        }

        current.next = null;
        size = i;
    }

    /**
     * Drops the prefix of the recipient list starting from the node at index 0
     * and up to and including the node at index count-1. If count is greater than
     * the size of the list, the resulting list is empty
     *
     * @param count
     */
    public void drop(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("drop: negative count");
        }
        if (count == 0 || head == null) {
            return;
        }
        // count>0 and the list is not empty

        int i = 0;
        while (i < count && head != null) {
            removeFirst();
            i++;
        }
    }

    /**
     * Checks to see if item belongs the linked-list that starts with the node
     * local
     *
     * @param item
     * @param local
     * @return
     */
    private Boolean mem(E item, Node<E> local) { return null; }

    public void removeAll(E item) {}

    public
    String toString() {
        StringBuilder s = new StringBuilder();

        Node<E> current = head;
        s.append("[");
        while (current != null) {
            s.append(current.data.toString() + ";");
            current = current.next;
        }
        s.append("]");
        return s.toString();
    }

    SLL<E> append(SLL<E> l2) {
        Node<E> l2Node = l2.head;
        while (l2Node != null) {
            this.add(l2Node.data, size);
            l2Node = l2Node.next;
        }
    }
    // Exercise 2: Returns true if all elements in list are non-null
    public boolean allNonNull() {
        Node<E> curr = head;
        while (curr != null) {
            if (curr.data == null) {
                return false;
            }
            curr = curr.next;
        }
        return true;
    }
    // Exercise 3: Returns true if given element is in the list
    public boolean mem(E el) {
        Node<E> curr = head;
        while (curr != null) {
            if (curr.data.equals(el)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
    // Exercise 4: Returns true if list contains no duplicates
    public boolean nonDuplicates() {
        Node<E> curr = head;
        while (curr != null) {
            Node<E> temp = curr.next;
            while (temp != null) {
                if (curr.data.equals(temp.data)) {
                    return false;
                }
                temp = temp.next;
            }
            curr = curr.next;
        }
        return true;
    }
    // Exercise 7: Reverses the list and returns a new list
    public SLL<E> reverse() {
        SLL<E> newList = new SLL<>();
        Node<E> curr = head;
        while (curr != null) {
            newList.addFirst(curr.data);
            curr = curr.next;
        }
        return newList;
    }
    // Exercise 7: Reverses the list in-place
    public void reverseInPlace() {
        Node<E> prev = null;
        Node<E> curr = head;
        while (curr != null) {
            Node<E> next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }
    // Exercise 8: Returns a list of booleans indicating whether each element is
    // null or not
    public SLL<Boolean> areNull() {
        SLL<Boolean> newList = new SLL<>();
        Node<E> curr = head;
        while (curr != null) {
            newList.add(curr.data == null);
            curr = curr.next;
        }
        return newList;
    }
    public SLL<E> repeatLN(Integer n) {
        SLL<E> newList = new SLL<>();
        for (int i = 0; i < n; i++) {
            Node<E> curr = head;
            while (curr != null) {
                newList.add(curr.data);
                curr = curr.next;
            }
        }
        return newList;
    }
    public SLL<E> stutterNL(int n) {
        SLL<E> result = new SLL<>();
        for (Node<E> node = head; node != null; node = node.next) {
            for (int i = 0; i < n; i++) {
                result.add(node.data);
            }
        }
        return result;
    }
    public void removeAdjacentDuplicates() {
        Node<E> node = head;
        while (node != null && node.next != null) {
            if (node.data.equals(node.next.data)) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
    }

    public void filterNonNull() {
        Node<E> node = head;
        Node<E> prev = null;
        while (node != null) {
            if (node.data == null) {
                if (prev == null) {
                    head = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
            } else {
                prev = node;
            }
            node = node.next;
        }
    }
    public<F> SLL<E> zipL(SLL<F> l2) {
        SLL<Object> result = new SLL<>();
        Node<E> node1 = head;
        Node<F> node2 = l2.head;
        while (node1 != null && node2 != null) {
            result.add(node1.data);
            result.add(node2.data);
            node1 = node1.next;
            node2 = node2.next;
        }
        while (node1 != null) {
            result.add(node1.data);
            node1 = node1.next;
        }
        while (node2 != null) {
            result.add((E)node2.data);
            node2 = node2.next;
        }
        return (SLL<E>)result;
    }
}
