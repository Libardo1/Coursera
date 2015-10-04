import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.In;

public class Board {
    private int[][] board;
    private int N;
    
    
    public Board(int[][] blocks) {
       
          N = blocks.length; 
          board = new int[N][N];
          for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              this.board[i][j] = blocks[i][j];
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
        int sumt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              if (board[i][j] == 0) {
                    continue;
                }
              int val = (i * N) + j + 1;
             
              if (board[i][j] != val) {
                 int r = (board[i][j] -1) / N;
                 int c = (board[i][j] -1) % (N);
//                 System.out.println("row :" + r + "  col :" +c);
//                 System.out.println("board row :" + i + "  board col :" +j);
                 int temp = (Math.abs(i - r) + Math.abs(j - c));
                 //System.out.println("adding : " + temp);
                 sumt +=  temp;
              }
            }
            }
        return sumt;
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
              if (board[i][j] != val) {
                  
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
                
                    newboard[i][j] = board[i][j];
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
        if (y == null) {
            return false;
        }
      
     if (y == this) return true;
     //if (y == null) return false;
     if (y.getClass() != (this.getClass())) return false;
     
     if (((Board) y).N != this.N) return false;
     
     for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              
              if (board[i][j] != ((Board) y).board[i][j]) {
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
          
          int[] rowvals = {1, -1, 0, 0};
          int[] colvals = {0, 0, -1, +1};
          
          for (int i = 0; i < 4; i++) {
              int rowval = row + rowvals[i];
              int colval = colvals[i] + col;
//              System.out.println("" + row_val+col_val);
              if ((rowval >= 0 && rowval < N) && (colval >= 0 && colval < N)) {
                  
                  int[][] newboard = new int[N][N];
                  for (int k = 0; k < N; k++) {
                      for (int j = 0; j < N; j++) {
                          
                              newboard[k][j] = board[k][j];
                          }
                      }
                  int temp = newboard[row][col];
                  newboard[row][col] = newboard[rowval][colval];
                  newboard[rowval][colval] = temp;
                  Board bb = new Board(newboard);
//                  System.out.println(bb.toString());
//                  System.out.println("ere");
                  list.add(bb);
              }
              
          }
          return list;
    }
     public String toString() {
      StringBuilder s = new StringBuilder();

      s.append(N).append("\n");
       for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              s.append(board[i][j]);
              s.append(" ");
              }
              s.append("\n");
            }
       return s.toString();
            }

    
    
    public static void main(String[] args) 
        
    {
        In in = new In(args[0]);
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            blocks[i][j] = in.readInt();
    Board bod = new Board(blocks);
        System.out.println(bod.toString());
        System.out.println(bod.manhattan());
        for (Board bee : bod.neighbors()) {
            System.out.println(bee.toString());
        }
        
        
    }
}