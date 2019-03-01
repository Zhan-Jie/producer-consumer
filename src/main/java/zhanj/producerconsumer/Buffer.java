package zhanj.producerconsumer;

public class Buffer {
    private Node<Integer> head;

    private Node<Integer> readIndex;

    private Node<Integer> writeIndex;

    private int size;

    private final int CAPACITY;

    public Buffer(int capacity) {
        CAPACITY = capacity;
        for (int i = 0; i < capacity; ++i) {
            addNode();
        }
        size = 0;
        readIndex = head;
        writeIndex = head;
    }

    public int readInt() {
        if (size < 1) {
            throw new IndexOutOfBoundsException();
        }
        int d = readIndex.data;
        readIndex.data = null;
        readIndex = readIndex.next;
        --size;
        return d;
    }

    public void writeInt(int value) {
        if (size >= CAPACITY) {
            throw new IndexOutOfBoundsException();
        }
        writeIndex.data = value;
        writeIndex = writeIndex.next;
        ++size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == CAPACITY;
    }

    private void addNode() {
        Node<Integer> newNode = new Node<>();
        if (head == null) {
            head = newNode;
            head.last = head;
            head.next = head;
            return;
        }
        Node<Integer> tail = head.last;
        tail.next = newNode;
        newNode.last = tail;
        head.last = newNode;
        newNode.next = head;
    }

    private class Node<T> {
        Node<T> next;
        Node<T> last;
        T data;
    }
}
