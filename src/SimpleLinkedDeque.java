import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *  Space Complexity: O(1) for member variables
 * @param <T>
 */
public class SimpleLinkedDeque<T> implements SimpleDeque<T> {

    //Member Variables
    private int capacity; //deque's limited capacity
    private Node <T> head; //head node
    private Node <T> tail; //tail node

    /**
     * inner class for a node
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     */
    class Node<T> {
        //member variables for class Node:
        T data; //data node stores
        Node<T> next; //pointer to next node
        Node<T> previous; //pointer to previous node

        /**
         * constructor for a node
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * @param e data stored in node
         */
        public Node(T e) {
            this.data = e;
        }
    }


    /**
     * Constructs a new linked list based deque with unlimited capacity.
     * Time Complexity: O(1)
     * Space Complexity: O(1) assuming capacity assigned with MAX_VALUE takes
     * constant time.
     */
    public SimpleLinkedDeque() {
        this.capacity = Integer.MAX_VALUE;
        this.head = null;
        this.tail = null;
    }

    /**
     * Constructs a new linked list based deque with limited capacity.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * @param capacity the capacity
     * @throws IllegalArgumentException if capacity <= 0
     */
    public SimpleLinkedDeque(int capacity) throws IllegalArgumentException {
        if (capacity <= 0){
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        this.head = null;
        this.tail = null;

    }

    /**
     * Constructs a new linked list based deque with unlimited capacity, and initially 
     * populates the deque with the elements of another SimpleDeque.
     *
     * Time Complexity: O(n) will depend on the size of the otherDeque.
     * Space Complexity: O(n) creating number of Nodes based on the nodes on
     * the OtherDeque.
     * @param otherDeque the other deque to copy elements from. otherDeque should be left intact.
     * @requires otherDeque != null
     */
    public SimpleLinkedDeque(SimpleDeque<? extends T> otherDeque) {
        this.capacity = Integer.MAX_VALUE;
        //iterate over otherDeque and add element to current deque.
        for (Iterator i = otherDeque.iterator(); i.hasNext();){
            if (this.head == null){
                this.pushRight((T) i.next());
            }
                this.pushRight((T) i.next());
        }
    }
    
    /**
     * Constructs a new linked list based deque with limited capacity, and initially 
     * populates the deque with the elements of another SimpleDeque.
     *
     * Time Complexity: O(n) will depend on the size of the otherDeque.
     * Space Complexity: O(n) creating number of Nodes based on the nodes on
     * the OtherDeque.
     *
     * @param otherDeque the other deque to copy elements from. otherDeque should be left intact.
     * @param capacity the capacity
     * @throws IllegalArgumentException if capacity <= 0 or size of otherDeque is > capacity
     */
    public SimpleLinkedDeque(int capacity, SimpleDeque<? extends T> otherDeque) 
            throws IllegalArgumentException {
        if (capacity <= 0 || otherDeque.size() > capacity) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        for (Iterator i = otherDeque.iterator(); i.hasNext();){
            if (this.head == null){
                this.pushRight((T) i.next());
            }
            this.pushRight((T) i.next());
        }
    }


    /**
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public boolean isEmpty() {
        return (this.head == null);
    }

    /**
     * Time Complexity: O(n) traversing will loop over the number of nodes.
     * Space Complexity: O(1) no new nodes are created that depend on the
     * size of the deque.
     */
    @Override
    public boolean isFull() {
        int counter = 0;
        Node <T> trav = head;
        //Traverse forwards from head.
        while(trav != null) {
            counter++;
            trav = trav.next;
        }
        //check if limit has been reached.
        if (counter >= capacity){
            return true;
        }
        return false;
    }

    /**
     * Time Complexity: O(n) traversing will loop over the number of nodes.
     * Space Complexity: O(1) no new nodes are created.
     */
    @Override
    public int size() {
        int counter = 0;
        Node trav = this.head;
        //Traverse forward from head.
        while(trav != null){
            counter++;
            trav = trav.next;
        }
        return counter;
    }

    /**
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public void pushLeft(T e) throws RuntimeException {
        if (isFull()){
            throw new RuntimeException();
        }
        Node newNode = new Node(e);
        if (isEmpty()) {
            this.head = newNode;
        }
        else {
            head.previous = newNode;
            newNode.next = this.head;
        }
        this.head = newNode;
    }

    /**
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public void pushRight(T e) throws RuntimeException {
        if (isFull()){
            throw new RuntimeException();
        }
        Node newNode = new Node(e);
        if (isEmpty()) {
            this.head = newNode;
        }
        else {
            tail.next = newNode;
            newNode.previous = this.tail;
        }
        this.tail = newNode;
    }

    /**
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public T peekLeft() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.head.data;
    }

    /**
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public T peekRight() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.tail.data;
    }

    /**
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public T popLeft() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        //Get head element
        T element = this.peekLeft();
        head.next = this.head;
        return element;
    }

    /**
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public T popRight() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        //Get tail element
        T element = this.peekRight();
        tail.previous = this.tail;
        return element;
    }

    /**
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new Iterator<T>() {
            //instance variables:
            private Node currentPos = head;

            @Override
            public boolean hasNext() {
                return currentPos != null;
            }

            @Override
            public T next() {
                T nextElem = (T) currentPos.data;
                currentPos = currentPos.next;
                return nextElem;
            }
        };
        return iterator;
    }

    /**
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public Iterator<T> reverseIterator() {
        Iterator<T> iterator = new Iterator<T>() {
            //instance variables:
            private Node currentPos = tail;

            @Override
            public boolean hasNext() {
                return currentPos != null;
            }

            @Override
            public T next() {
                T previousElem = (T) currentPos.data;
                currentPos = currentPos.previous;
                return previousElem;
            }
        };
        return iterator;
    }
}
