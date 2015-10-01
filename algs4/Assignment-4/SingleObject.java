public class SingleObject {

   //create an object of SingleObject
   private static SingleObject instance = new SingleObject();

   //make the constructor private so that this class cannot be
   //instantiated
   private SingleObject(){
       System.out.println("geet");}

   //Get the only object available
   public static SingleObject getInstance(){
      return instance;
   }

   public void showMessage(){
      System.out.println("Hello World!");
   }
  
  public static void main(String args[]){
    SingleObject obj = new SingleObject();
    //SingleObject obj1 = new SingleObject();
  }
}