import java.util.Iterator;
import java.util.NoSuchElementException;

//Nodes of the doubly linked list.
class Node <T> {
    int data;
    Node<T> next;
    Node<T> previous;
}

public class SimpleLinkedDeque<T> implements SimpleDeque<T> {

    //Memmber Variables
    private int capacity; //deque's limited capacity
    private Node <T> head;
    private Node <T> tail;


    class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;
        public Node(T data, Node next, Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }

        public Node(T e) {
            this.data = e;
        }
    }


    /**
     * Constructs a new linked list based deque with unlimited capacity.
     */
    public SimpleLinkedDeque() {
        this.capacity = Integer.MAX_VALUE;
        this.head = null;
        this.tail = null;
    }

    /**
     * Constructs a new linked list based deque with limited capacity.
     *
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


    @Override
    public boolean isEmpty() {
        return (this.head == null);
    }

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

    @Override
    public T peekLeft() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.head.data;
    }

    @Override
    public T peekRight() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.tail.data;
    }

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
