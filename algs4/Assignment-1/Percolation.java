public class Percolation {
    
    public WeightedQuickUnionUF matrix;
    
    public Percolation(int N){
        int n = N * N + 2;
        this.matrix = WeightedQuickUnionUF(n)
    }// create N-by-N grid, with all sites blocked
   public void open(int i, int j)          // open site (row i, column j) if it is not open already
   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
   public boolean isFull(int i, int j)     // is site (row i, column j) full?
   public boolean percolates()             // does the system percolate?

   public static void main(String[] args ){
       
    
        }// test client (optional)
}