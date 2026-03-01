
//ProgramacionIII20260228
//CarlosRamos
//0905 23 14141


package umg.edu.gt.data_structure.queue.manual;

public class QueueLinked<T> {

    private Node<T> head; // primero
    private Node<T> tail; // último
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    
    public void enqueue(T item) {
        Node<T> node = new Node<>(item);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }


    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Upss Error: la cola está vacía, no se puede hacer dequeue()");
        }
        T value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return value;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Upss Error: la cola está vacia, no se puede hacer peek()");
        }
        return head.value;
    }
}


