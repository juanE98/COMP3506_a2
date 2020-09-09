import java.util.Iterator;
import java.util.NoSuchElementException;

public class ReversibleDeque<T> implements SimpleDeque<T> {

    private SimpleDeque<T> reverseible;

    /**
     * Constructs a new reversible deque, using the given data deque to store
     * elements.
     * The data deque must not be used externally once this ReversibleDeque
     * is created.
     * @param data a deque to store elements in.
     */
    public ReversibleDeque(SimpleDeque<T> data) {
        this.reverseible = data;
    }

    public void reverse() {
        SimpleDeque temp = null;
        Iterator<T> reverseIter = reverseible.reverseIterator();
       while (reverseIter.hasNext()){
           temp.pushRight(reverseible.popRight());
       }
       this.reverseible = temp;
    }

    @Override
    public int size() {
        return reverseible.size();
    }

    @Override
    public boolean isEmpty() {
        return (reverseible != null);
    }

    @Override
    public boolean isFull() {
        return reverseible.isFull();
    }

    @Override
    public void pushLeft(T e) throws RuntimeException {
        reverseible.pushLeft(e);
    }

    @Override
    public void pushRight(T e) throws RuntimeException {
        reverseible.pushRight(e);
    }

    @Override
    public T peekLeft() throws NoSuchElementException {
        return reverseible.peekLeft();
    }

    @Override
    public T peekRight() throws NoSuchElementException {
        return reverseible.peekRight();
    }

    @Override
    public T popLeft() throws NoSuchElementException {
        return reverseible.popLeft();
    }

    @Override
    public T popRight() throws NoSuchElementException {
        return reverseible.popRight();
    }

    @Override
    public Iterator<T> iterator() {
        return reverseible.iterator();
    }

    @Override
    public Iterator<T> reverseIterator() {
        return reverseible.reverseIterator();
    }
}
