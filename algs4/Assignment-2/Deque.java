public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;
    
   private class Node {
        private Item item;
        private Node next;
    }
    
   public Deque() { 
   // construct an empty deque
       first = null;
       last = null;
       n = 0;
   }
   public boolean isEmpty() {
       // is the deque empty?
     if ((first == null) && (last == null)){
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
   public void addFirst(Item item) {
      if (item == null) throw new java.util.NoSuchElementException();
      if (start == null){
        first = item;
        item.next = null;
        last = first;
      }
     // add the item to the front
      else{
        Item temp = first;
        first = item;
        item.next = temp;
        
      }
   }
     
       public void addLast(Item item){
         if (item == null) throw new java.util.NoSuchElementException();
         // add the item to the end
         if ((last == null) && (first == null)){
           first = item;
           item.next = null;
           last = first;
         }
         else{
         last.next = item;
         item.next = null;
         last = item;
         }
       }
       public Item removeFirst() {               // remove and return the item from the front
         if (isEmpty()) throw new java.lang.UnsupportedOperationException();
         
       
       }
   public Item removeLast()                 // remove and return the item from the end
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   public static void main(String[] args)   // unit testing
}