import java.util.List;
import edu.princeton.cs.algs4.MinPQ;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;

public class Solver {
    private Board board;
    private Board twinb;
    private int move;
    private MinPQ<Tuple> q ;
    private MinPQ<Tuple> qTwin;
    private boolean isSolutionCalled;
    
    private class Tuple implements  Comparator<Tuple> { 
        public int x; 
        public Board board; 
        public Tuple prev;
        public int moves;
        public Tuple( int x, Board board, Tuple prev, int moves) { 
            this.x = x; 
            board = board;
            prev = prev;
            moves = moves;
  } 
        public int compare(Tuple tup1, Tuple tup2) {
              return Integer.compare((tup1.x), (tup2.x));
    }
} 
    
    public Solver(Board initial)     
    {      
        board = initial;
        twinb = board.twin();
        move = 0;
        isSolutionCalled = false;
         q  = new MinPQ<Tuple>();
         qTwin = new MinPQ<Tuple>(); 
    }
    public boolean isSolvable() {
        if (this.solution() == null) {
            return false;
        }
        else {
            return true;
        }
        
        
       // is the initial board solvable?
    }
    public int moves() {
        if ( isSolutionCalled) {
            return this.move;
        }
        else {
            solution();
            return this.move;
        }
        // min number of moves to solve initial board; -1 if unsolvable
    }
    
    private Iterable<Board> find(Tuple t) {
        List<Board> list = new ArrayList<Board>();
        Board b = t.board;
        while ( b.equals(this.board)) {
            list.add(t.board);
            b = (t.prev).board ;
        }
        return list;        
          
    }
    public Iterable<Board> solution()  {
       // sequence of boards in a shortest solution; null if unsolvable
        int prior = board.hamming();
        isSolutionCalled = true;
        int priorTwin = twinb.hamming();
        Tuple t = new Tuple(prior, board, null, 0);
        Tuple t2 = new Tuple(prior, twinb, null, 0);
        q.insert(t);
        qTwin.insert(t2);
        boolean flag = false;
        Iterable<Board> ans = null;
         // find a solution to the initial board (using the A* algorithm)
        while (true) {
            Tuple currTuple = q.min();
            Tuple currTupleTwin = qTwin.min();
            if ((currTupleTwin.board).isGoal()) {
                 ans = find(currTupleTwin);
                 flag = true;
                this.move = -1;
                break;
            }
            if ((currTuple.board).isGoal()){
                 ans = find(currTuple);
                 flag = false;
                this.move = currTuple.x;
                break;
            }
            for (Board b : (currTuple.board).neighbors()) {
                if (!b.equals((currTuple.prev).board)) {
                    prior = b.hamming() + (currTuple.moves) ;
                    q.insert(new Tuple(prior, b,currTuple, (currTuple.moves) + 1));
                }
            }
            for (Board bTwin : (currTupleTwin.board).neighbors()) {
                if (!bTwin.equals((currTupleTwin.prev).board)) {
                    prior = bTwin.hamming() + (currTupleTwin.moves) ;
                    qTwin.insert(new Tuple(prior, bTwin, currTupleTwin, (currTupleTwin.moves) + 1));
                }
            }
            
        }
        if (flag == true) {
            return null;
        }
        else {
            return ans;
        }
           
    }
    public static void main(String[] args)
    {// solve a slider puzzle (given below)
        
    }
}