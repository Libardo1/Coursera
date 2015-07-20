public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first;
       private int size;
        private class Node {
        private Item item;
        private Node next;
        
        public Node(Item item){
            item = item;
            next = null;
        }
    }
        public RandomizedQueue()  {   
           // construct an empty randomized queue
         first = null;
         size = 0;
        }
  
        
   public boolean isEmpty()                
   {
       return (fist == null);
// is the queue empty?
   }
       public int size()   {return size;}
   // return the number of items on the queue
   public void enqueue(Item item)           // add the item
   {
       if (item == null) throw new java.lang.NullPointerException();
       Node new_node = Node(item);
       if (first == null){
           first = new_node;
       }
       else{
           new_node.next = first;
           first = new_node;
       }
       ++size;
   }
   
   public Item dequeue()                    // remove and return a random item
   {
       if (size == 0) throw new java.util.NoSuchElementException();
       random_n = StdRandom.uniform(size)+1;
       Node temp = first;
       while(random_n > 1){
           temp = temp.next;
       }
       
       
       
   }
   public Item sample()           {
       // return (but do not remove) a random item
   }
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   {
       
   }
   public static void main(String[] args)   // unit testing
}