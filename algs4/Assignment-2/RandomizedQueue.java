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
       if (n == 1) {
           first = null;
           
       }
           
       Node temp = first;
       while(random_n > 1){
           temp = temp.next;
           --random_n
       }
       if (temp.next != null) && ((temp.next).next != null){
       temp.next = (temp.next).next;
       }
       else if ((temp.next).next) == null){
           temp.next = null;
       }
       
       ---n;
       
   }
   public Item sample()           {
       random_n = StdRandom.uniform(size)+1;
       temp = first;
       while(random_n > 0){
           temp = temp.next;
           --random_n
       }

       return temp.item;// return (but do not remove) a random item
   }
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   {
       
   }
   public static void main(String[] args)   // unit testing
}