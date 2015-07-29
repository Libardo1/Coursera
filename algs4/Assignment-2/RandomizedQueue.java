public class RandomizedQueue<Item> implements Iterable<Item> {
       private Item arr[];
       private int size;
       
   public boolean isEmpty()                
   {
       return (size == 0);
// is the queue empty?
   }
       public int size()   {return size;}
   // return the number of items on the queue
   public void enqueue(Item item)           // add the item
   {
       if (item == null) throw new java.lang.NullPointerException();
       if (size == arr.length()){
         resize(size*2);
       }
       arr[++size] = item;
   }
   
   public Item dequeue() {
       if (size == 0) throw new java.util.NoSuchElementException();
       StdRandom.shuffle(arr, 0, size- 1);
       Item item = arr[size -1];
       arr[size -1] = null;
       size--;
       if (size > 0 && size ==arr.length/4) {
           resize(arr.length/2);
       }
       
       
   public Item sample()           { 
      // return (but do not remove) a random item
      if (isEmpty()) {
            throw new NoSuchElementException();
        } 
       int random_n = StdRandom.uniform(size);
       return arr[random_n];
   }
   
   private class RandomizedQueueIterator<Item> implements Iterator<Item>{
     
        private int cursor;

        public RandomizedQueueIterator() {
            this.cursor = Range.this.start;
        }

        public boolean hasNext() {
            return this.cursor < Range.this.end;
        }

        public Integer next() {
            if(this.hasNext()) {
                int current = cursor;
                cursor ++;
                return current;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

     
     
   }
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   {
     new RandomizedQueueIterator();
       
   }
   
   
   public static void main(String[] args)   // unit testing
}