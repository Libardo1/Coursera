
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
       if (first == null){
           assert(last == null);
     return true;
     }
     else{
       return false;
     }
       
   }
   public int size()  {
     return size;
   }

     // return the number of items on the deque
   private Node create_node(Item item){
      Node new_node = new Node();
      new_node.item = item;
      new_node.next = null;
      new_node.prev = null;
      return new_node;
   }
   public void addFirst(Item item) {
     if (item == null)  throw new java.util.NoSuchElementException();
      Node new_node = create_node(item);
      if (isEmpty() == true){
        first = new_node;
        last = first;
        
      }
      // add the item to the front
      else{
        Node temp = first;
        first = new_node;
        new_node.next = temp;
        temp.prev = new_node;
        
      }
      ++size;
      }
   
     
       public void addLast(Item item){
         if (item == null) throw new java.util.NoSuchElementException();
         else{
         // add the item to the end
         Node new_node = create_node(item);
         if (isEmpty() == true){
           first = new_node;
           last = first;
         }
         else{
         new_node.prev = last;
         last.next = new_node;
         last = new_node;
         }
         ++size;
       }
       }
       
       
       public Item removeFirst() {               // remove and return the item from the front
         if (isEmpty()) throw new java.lang.UnsupportedOperationException();
         Node temp = first;
         
           if (first.next != null){
           
           first = first.next;
           first.prev = null;
           }
           else{
             temp = first;
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
         if (last.prev == null){
           temp = last;  
           last = null;
           first = null;
         }
         else{
         temp = last;
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
           
           Deque<Integer> que = new Deque<Integer>();
           que.addFirst(2);
           que.addFirst(1);
           que.addLast(20);
           int x = que.removeLast();
           System.out.println(x);
           
// unit testing
}
}
