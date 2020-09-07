import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class SimpleArrayDeque<T> implements SimpleDeque<T> {
    //Array properties:
    private int capacity; //current capacity of array
    private int head; //index of the head element
    private int tail; //index of the tail element

    //The array deque
    private T[] arrayDeque;

    /**
     * Constructs a new array based deque with limited capacity.
     * 
     * @param capacity the capacity
     * @throws IllegalArgumentException if capacity <= 0
     */
    public SimpleArrayDeque(int capacity) throws IllegalArgumentException {
        if(capacity > 0 && capacity == (int) capacity){
            this.arrayDeque = (T[]) new Comparable[capacity];
            this.capacity = capacity;
            this.head = 0;
            this.tail = 0;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * Constructs a new array based deque with limited capacity, and initially populates the deque
     * with the elements of another SimpleDeque.
     *
     * @param otherDeque the other deque to copy elements from. otherDeque should be left intact.
     * @param capacity the capacity
     * @throws IllegalArgumentException if capacity <= 0 or size of otherDeque is > capacity
     */
    public SimpleArrayDeque(int capacity, SimpleDeque<? extends T> otherDeque) 
            throws IllegalArgumentException {
        if(capacity <= 0 || otherDeque.size() > capacity ){
            throw new IllegalArgumentException();
        }
        this.arrayDeque = (T[]) Arrays.copyOf(new SimpleDeque[]{otherDeque},
                capacity);
        this.capacity = capacity;
        this.head = 0;
        this.tail = otherDeque.size() - 1;

    }


    @Override
    public boolean isEmpty() {
        if (this.head == this.tail) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isFull() {
        if (this.head == (this.tail + 1) % capacity) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        if (this.tail > this.head){
            return this.tail - this.head;
        }
        return this.capacity - (this.head - this.tail);
    }

    @Override
    public void pushLeft(T e) throws RuntimeException {
        if (isFull() == true){
            throw new RuntimeException();
        }
        //Go back in the array
        this.arrayDeque[(this.head + capacity - 1) % capacity] = e;
        this.head = ((this.head + capacity - 1) % capacity);

    }

    @Override
    public void pushRight(T e) throws RuntimeException {
        if (isFull() == true){
            throw new RuntimeException();
        }
        //Go forward in the array
        this.arrayDeque[(this.tail + 1) % capacity] = e;
        this.tail = (this.tail + 1) % capacity;

    }

    @Override
    public T peekLeft() throws NoSuchElementException {
        if (arrayDeque[((this.head + capacity - 1) % capacity)] == null){
            throw new NoSuchElementException();
        }
        return this.arrayDeque[this.head];
    }

    @Override
    public T peekRight() throws NoSuchElementException {
        if (arrayDeque[(this.tail + 1) % capacity] == null){
            throw new NoSuchElementException();
        }
        return this.arrayDeque[this.tail];
    }

    @Override
    public T popLeft() throws NoSuchElementException {
        if (this.isEmpty()){
            throw new NoSuchElementException();
        }
        //Get head element
        T element = this.peekLeft();
        //remove element
        this.arrayDeque[this.head] = null;
        //Head moves forward 1
        this.head = (this.head + 1) % this.capacity;
        return element;
    }

    @Override
    public T popRight() throws NoSuchElementException {
        if (this.isEmpty()){
            throw new NoSuchElementException();
        }
        //Get tail element
        T element = this.peekRight();
        this.arrayDeque[this.tail] = null;
        //Tail moves backward 1
        this.tail = (this.tail + this.capacity - 1) % this.capacity;
        return element;
    }


    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new Iterator<T>() {
            private int currentPos = head;

            @Override
            public boolean hasNext() {
                return arrayDeque[(currentPos + 1) % capacity] != null;
            }

            @Override
            public T next() {
                T nextElem = (T) arrayDeque[(currentPos + 1) % capacity];
                currentPos = (currentPos + 1) % capacity;
                return nextElem;
            }
        };
        return iterator;
    }


    @Override
    public Iterator<T> reverseIterator() {
        Iterator<T> iterator = new Iterator<T>() {
            private int currentPos = tail;

            @Override
            public boolean hasNext() {
                return arrayDeque[(currentPos + capacity - 1) % capacity] != null;
            }

            @Override
            public T next() {
                T nextElem = (T) arrayDeque[(currentPos + capacity - 1) % capacity];
                currentPos = (currentPos + capacity - 1) % capacity;
                return nextElem;
            }
        };
        return iterator;
    }
}
