
public class Percolation {
    
    public WeightedQuickUnionUF matrix;
    public int [] states; 
    public int width;
    static int OPEN = 1;
    static int BLOCK = 0;
    int TOP;
    int BOTTOM;
    int UPPER_VIRTUAL;
    int BOTTOM_VIRTUAL;
    
    public Percolation(int N){
        int n = N * N + 2;
        width = N;
        matrix = new WeightedQuickUnionUF(n);
        states = new int[n];
        for(int i = 0; i< N*N; i++){
            states[i] = BLOCK;
        }
        states[N*N] = OPEN;
        states[N*N+1] = OPEN;
        TOP=1;
        BOTTOM=N; 
        UPPER_VIRTUAL = N*N;
        BOTTOM_VIRTUAL = N*N+1;
        
    }// create N-by-N grid, with all sites blocked
    
    public int location(int i, int j){
        return (((i-1)*width) + (j-1) );
    }
    public void open(int i, int j){// open site (row i, column j) if it is not open already
        int cell = location(i,j); 
        if (states[cell]  == OPEN){
            return;
        }
        states[cell] = OPEN;
        
        //not top
        if (i == TOP) {
            matrix.union(UPPER_VIRTUAL,cell);
        }
        
        else if((i != TOP) && isOpen(i-1,j)){
            matrix.union(location(i-1,j), cell);
        }
        //top row
        
        // not last
        if(i==BOTTOM){
            matrix.union(BOTTOM_VIRTUAL,cell);
        }
        else if ((i != BOTTOM)&& isOpen(i+1,j)){
            matrix.union(location(i+1,j), cell);
        }
        
        
        //left corner
        if ((j != 1)&& isOpen(i,j-1)){
            matrix.union(location(i, j-1),cell);
        }
        
        //right corner
        if ((j != width)&& isOpen(i,j+1)){
            matrix.union(location(i, j+1),cell);
        }
        
    }
    public boolean isOpen(int i, int j){
        if (states[location(i,j)] == OPEN){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    public boolean isFull(int i, int j){     // is site (row i, column j) full?
        return matrix.connected(UPPER_VIRTUAL, location(i,j));
    }
    
    
    public boolean percolates(){            // does the system percolate?
        return matrix.connected(UPPER_VIRTUAL, BOTTOM_VIRTUAL);
    }
    
    public static void main(String[] args ){
        
        
    }// test client (optional)
}