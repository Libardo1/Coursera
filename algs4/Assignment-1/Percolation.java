
public class Percolation {
    
    public WeightedQuickUnionUF matrix;
    public int [] states; 
    public int width;
    static int OPEN = 1;
    static int BLOCK = 0;
    
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
        if (i ==1) {
            matrix.union(width*width,cell);
        }
        
        else if((i != 1) && isOpen(i-1,j)){
            matrix.union(location(i-1,j), cell);
        }
        //top row
        
        // not last
        if(i==width){
            matrix.union(width*width+1,cell);
        }
        else if ((i != width)&& isOpen(i+1,j)){
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
        return matrix.connected(width*width, location(i,j));
    }
    
    
    public boolean percolates(){            // does the system percolate?
        return matrix.connected(width*width, width*width+1);
    }
    
    public static void main(String[] args ){
        
        
    }// test client (optional)
}