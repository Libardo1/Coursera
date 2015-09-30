public class Board {
    private int[][] board;
    private int N;
    private int[][] goalBoard;
    
    public Board(int[][] blocks){
      // construct a board from an N-by-N array of blocks
      // (where blocks[i][j] = block in row i, column j)
          N = blocks.length; 
         int[][] board = new int[N][N];
          for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
              board[i][i] = blocks[i][j];
            }
          }
          int start = 1;
            for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              goalBoard[i][j] = start;
              start++;
              if (start == N - 1) {
                break;
              }
            }
          }
          
    }
    
    public int dimension() {
      // board dimension N
       return N;
    }
    public int hamming() {
       int count = 0;
      // number of blocks out of place
       for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              int val = (i * N) + j + 1;
              if (board[i][j] != val){
                 count++;
              }
            }
            }
       return count;
    }
    public int manhattan() {
   // sum of Manhattan distances between blocks and goal
    }
    public boolean isGoal()            {
      // is this board the goal board?
      for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              int val = (i * N) + j + 1;
              if (board[i][j] != val){
                 return false;
              }
            }
            }
       return true;
    }
    
      
    
    
    public Board twin()     {
  // a board that is obtained by exchanging any pair of blocks
      
    }
    public boolean equals(Object y) {
      
     if(y==this) return true;
     if(y==null) return false;
     if(!(y instanceof Board)) return false;
     if(((Board)y).N!=this.N) return false;
     
    
    
      for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              
              if (board[i][j] != ((Board)y).board[i][j]){
                 return false;
              }
            }
            }
       return true;
    }
    
      
    public Iterable<Board> neighbors()     // all neighboring boards
      
     public String toString(){
      string output = ""
      // string representation of this board (in the output format specified below)
       for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              output += board[i][j];
              output += " ";
              }
              output += "\n";
            }
            }
       return true;
    } 
    
    public static void main(String[] args) // unit tests (not graded)
}