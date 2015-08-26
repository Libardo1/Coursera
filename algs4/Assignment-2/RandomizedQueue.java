import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item [ ] arr;
    private int size;
    
    public RandomizedQueue() {
        arr = (Item []) new Object[2];
        size = 0;
    }
    
    public boolean isEmpty()                
    {
        return (size == 0);
// is the queue empty?
    }
    public int size()   { return size; }
    
    
    private void resize(int len) {
        assert (len > size);
        Item [] temp = (Item []) new Object[len];
        for (int i = 0; i < size; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }
    // return the number of items on the queue
    public void enqueue(Item item)           // add the item
    {
        if (item == null) throw new java.lang.NullPointerException();
        if (size == arr.length) {
            resize(size*2);
        }
        arr[size] = item;
        ++size;
    }
    
    private void swap(int i, int j) {
        Item temp  = (Item) arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public Item dequeue() {
        if (size == 0) throw new java.util.NoSuchElementException();
        //StdRandom.shuffle(arr, 0, size- 1);
        int randomN = StdRandom.uniform(size);
        assert (size > 0);
        Item item = arr[randomN];
        swap(randomN, size-1);
        arr[size -1] = null;
        size--;
        if (size > 0 && size == arr.length/4) {
            resize((arr.length)/2);
        }
        return item;
    }
    
    
    public Item sample()           { 
        // return (but do not remove) a random item
        if (isEmpty()) {
            throw new NoSuchElementException();
        } 
        int randomnumber = StdRandom.uniform(size);
        return arr[randomnumber];
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] itemTemp;
        private int count;
        
        public RandomizedQueueIterator() {
            itemTemp = (Item []) new Object[size];
            for (int i = 0; i < size; i++) {
                itemTemp[i] = arr[i];
            }
            if (size > 0) {
               StdRandom.shuffle(itemTemp, 0, size - 1);
            }
            count = size;
            
        }
        
        public boolean hasNext() {
            return count > 0;
        }
        
        public Item next() {
            if (this.hasNext()) {
                return itemTemp[--count];
            }
            throw new NoSuchElementException();
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    public Iterator<Item> iterator()         
// return an independent iterator over items in random order
    {
        return new RandomizedQueueIterator();
    }
    
    public static void main(String[] args)  {
        // unit testing
        /*RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        Iterator i = rq.iterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }*/
        
    }
}
