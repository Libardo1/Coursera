
public class Percolation {
    
    private static boolean  OPEN = true;
    private static boolean BLOCK = false;
    private  WeightedQuickUnionUF matrix;
    private boolean  [] states; 
    private int width;
    
    private WeightedQuickUnionUF matrixCopy;

    private int TOP;
    private int BOTTOM;
    private int UPPER_VIRTUAL;
    private int BOTTOM_VIRTUAL;
    
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        int n = N * N + 2;
        width = N;
        matrix = new WeightedQuickUnionUF(n);
        matrixCopy = new WeightedQuickUnionUF(n);
        states = new boolean[n];
        for (int i = 0; i < N*N; i++) {
            states[i] = BLOCK;
        }
        states[N*N] = OPEN;
        states[N*N+1] = OPEN;
        TOP = 1;
        BOTTOM = N; 
        UPPER_VIRTUAL = N*N;
        BOTTOM_VIRTUAL = N*N+1;
        
    } // create N-by-N grid, with all sites blocked
    
    private  int location(int i, int j) {
        if (i <= 0 || i > width || j <= 0 || j > width) throw 
            new IndexOutOfBoundsException("row index i out of bounds");
        return (((i - 1)*width) + (j-1));
    }
    public void open(int i, int j) { 
        // open site (row i, column j) if it is not open already
        if (i <= 0 || i > width || j <= 0 || j > width) throw
            new IndexOutOfBoundsException("row index i out of bounds");
        int cell = location(i, j); 
        if (states[cell]  == OPEN) {
            return;
        }
        states[cell] = OPEN;
        
        //not top
        if (i == TOP) {
            matrixCopy.union(UPPER_VIRTUAL, cell);
            matrix.union(UPPER_VIRTUAL, cell);
        }
        else if (isOpen(i-1, j)) {
            matrixCopy.union(location(i-1, j), cell);
            matrix.union(location(i-1, j), cell);
        }
        //top row
        
        // not last
        if (i == BOTTOM) {
            matrix.union(BOTTOM_VIRTUAL, cell);
        }
        else if (isOpen(i+1, j)) {
            matrixCopy.union(location(i+1, j), cell);
            matrix.union(location(i+1, j), cell);
        }
                
        //left corner
        if ((j != 1) && isOpen(i, j-1)) {
            matrixCopy.union(location(i, j-1), cell);
            matrix.union(location(i, j-1), cell);
        }
        
        //right corner
        if ((j != width) && isOpen(i, j+1)) {
            matrix.union(location(i, j+1), cell);
             matrixCopy.union(location(i, j+1), cell);
        }
    }

    public boolean isOpen(int i, int j) {
        if (i <= 0 || i > width || j <= 0 || j > width) throw
            new IndexOutOfBoundsException("row index i out of bounds");
        return (states[location(i, j)] == OPEN);
        
    }
    
    public boolean isFull(int i, int j) {     // is site (row i, column j) full?
        if (i <= 0 || i > width || j <= 0 || j > width) throw 
            new IndexOutOfBoundsException("row index i out of bounds");
        return matrixCopy.connected(UPPER_VIRTUAL, location(i, j));
    }
    
    
    public boolean percolates() {            // does the system percolate?
        return matrix.connected(UPPER_VIRTUAL, BOTTOM_VIRTUAL);
    }
    
    public static void main(String[] args) { 
        
        
    } // test client (optional)
}