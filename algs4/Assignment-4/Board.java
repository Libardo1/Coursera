import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private int[][] board;
    private int N;
    
    
    public Board(int[][] blocks){
        System.out.println("here, creating a board");
      // construct a board from an N-by-N array of blocks
      // (where blocks[i][j] = block in row i, column j)
          N = blocks.length; 
          board = new int[N][N];
          for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              this.board[i][j] = blocks[i][j];
            }
          }
          System.out.println("board created");
          
          
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
                if (board[i][j] == 0) {
                    continue;
                }
              int val = (i * N) + j + 1;
              if (board[i][j] != val) {
                 count++;
              }
            }
            }
       return count;
    }
    public int manhattan() {
        int sum_t = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              if (board[i][j] == 0) {
                    continue;
                }
              int val = (i * N) + j + 1;
              if (board[i][j] != val){
                 int r = val/N + 1;
                 int c = val - r*N;
                 sum_t +=  (Math.abs(i - r) + Math.abs(j - c));
              }
            }
            }
        return sum_t;
   // sum of Manhattan distances between blocks and goal
        
    
    }
    public boolean isGoal()            {
      // is this board the goal board?
      for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N-1 && j == N-1 && board[i][j] == 0) {
                    continue;
                }
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
        int[][] newboard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                {
                    newboard[i][j] = board[i][j];
                }
            }
        }
              
           int row = 0;
           if (newboard[row][0] == 0 || newboard[row][1] == 0) {
               row = 1;
           }
        int temp = newboard[row][0];
        newboard[row][0] = newboard[row][1];
        newboard[row][1] = temp;
        
        return new Board(newboard);
      
    }
    public boolean equals(Object y) {
        if ( y== null) {
            return false;
        }
      
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
    
      
    public Iterable<Board> neighbors()  
    { // all neighboring boards
        int row = 0;
        int col = 0;
        
         for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    break;
                }
            }
         }
         
          List<Board> list = new ArrayList<Board>();
          
          int[] row_vals = {1,-1,0,0};
          int[] col_vals = {0,0,-1,+1};
          for (int i = 0;i < 4; i++) {
              int row_val = row + row_vals[i];
              int col_val = col_vals[i] + col;
              
              if ((row_val > 0 && row_val < N) && (col_val > 0 && col_val < N)){
                  int[][] newboard = new int[N][N];
                  for (int k = 0; i < N; i++) {
                      for (int j = 0; j < N; j++) {
                          {
                              newboard[k][j] = board[k][j];
                          }
                      }
                  }
                 
                  newboard[row][col] = newboard[row_val][col_val];
                  newboard[row_val][col_val] = 0;
                  list.add(new Board(newboard));
              }
              
          }
          return list;
    }
     public String toString(){
      String output = "";
      
      // string representation of this board (in the output format specified below)
       for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              output += board[i][j];
              output += " ";
              }
              output += "\n";
            }
       return output;
            }

    
    
    public static void main(String[] args) 
        
    {
        int [][] b = {{1 , 2,  3 },{4 , 5,  6},  {7,  8,  0}};
        Board bod = new Board(b);
        Board bod1 = new Board(b);
        System.out.println(bod.manhattan());
        
    }
}