
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;
    
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    
    public Deque() { 
        // construct an empty deque
        first = null;
        last = null;
        size = 0;
    }
    public boolean isEmpty() {
        // is the deque empty?
        if (first == null) {
            assert (last == null);
            return true;
        }
        else {
            return false;
        }
        
    }
    public int size()  {
        return size;
    }
    
    // return the number of items on the deque
    private Node createnode(Item item) {
        Node newnode = new Node();
        newnode.item = item;
        newnode.next = null;
        newnode.prev = null;
        return newnode;
    }
    public void addFirst(Item item) {
        if (item == null)  throw new java.util.NoSuchElementException();
        Node newnode = createnode(item);
        if (isEmpty()) {
            first = newnode;
            last = first;
            
        }
        // add the item to the front
        else {
            Node temp = first;
            first = newnode;
            newnode.next = temp;
            temp.prev = newnode;
            
        }
        ++size;
    }
    
    
    public void addLast(Item item) {
        if (item == null) throw new java.util.NoSuchElementException();
        else {
            // add the item to the end
            Node newnode = createnode(item);
            if (isEmpty()) {
                first = newnode;
                last = first;
            }
            else {
                newnode.prev = last;
                last.next = newnode;
                last = newnode;
            }
            ++size;
        }
    }
    
    
    public Item removeFirst() {              
        // remove and return the item from the front
        if (isEmpty()) throw new java.lang.UnsupportedOperationException();
        Node temp = first;
        
        if (first.next != null) {
            
            first = first.next;
            first.prev = null;
        }
        else {
            //temp = first;
            first = null;
            last = null;
        }
        
        --size;
        return temp.item;
    }
    public Item removeLast()  {
        // remove and return the item from the end
        if (isEmpty()) throw new java.lang.UnsupportedOperationException();
        Node temp;
        temp = last;
        if (last.prev == null) {
            //temp = last;  
            last = null;
            first = null;
        }
        else {
            //temp = last;
            last = last.prev;
            last.next = null;
        }
        --size;
        return temp.item;
        
    }
    
    private class DequeIterator implements  Iterator<Item> {
        
        private Node current;
        public DequeIterator() {
            this.current = first;
        }
        
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }
        
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
        
    }
    
    public Iterator<Item> iterator()  {  
        // return an iterator over items in order from front to end
        return new DequeIterator();
        
        
    }
    
    
    public static void main(String[] args) {
        
        Deque<String> que = new Deque<String>();
        while (!StdIn.isEmpty())
        {
            String s = new String(StdIn.readString());
            que.addFirst(s);
            System.out.println("got: \"" + s + "\"");
        }
        while (!que.isEmpty()) {
            String  x = que.removeLast();
        System.out.println(x);
        }
     
        
// unit testing
    }
}
