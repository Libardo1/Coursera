import java.util.List;
import edu.princeton.cs.algs4.MinPQ;
import java.util.Comparator;
import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Collections;

public class Solver {
    private Board board;
    private Board twinb;
    private int move;
    private boolean isSolutionCalled;
    private Iterable<Board> boardSolutions;
    
    private class Tuple { 
        private int x; 
        private Board board; 
        private Tuple prev;
        private int moves;
        public Tuple(int x, Board board, Tuple prev, int moves) { 
            this.x = x; 
            this.board = board;
            this.prev = prev;
            this.moves = moves;
            
  }
        public int getx() {
            return this.x;
        }
        public Board getboard() { return this.board; }
        public Tuple getprev() { return this.prev; }
        public int getmoves() { return this.moves; }
     
} 
    
    public Solver(Board initial)     
    {      
        this.board = initial;
        this.twinb = board.twin();
        this.move = 0;
        this.isSolutionCalled = false;
        boardSolutions = null;
      
    }
 
    public boolean isSolvable() {
        boolean sol = true;
        if (!isSolutionCalled) {
            if (this.solution() == null) {
                return false;
            }
            return sol;
            
        }
        if (this.move == -1) {
                return false;
            }
            return sol;
        }
       // is the initial board solvable?
    
    public int moves() {
        if (isSolutionCalled) {
            return this.move;
        }
        else {
            solution();
            return this.move;
        }
        // min number of moves to solve initial board; -1 if unsolvable
    }
    
    private Iterable<Board> find(Tuple tt) {
        Tuple t = tt;
        List<Board> list = new ArrayList<Board>();
       
        while (t.getprev() != null) {
            list.add(t.getboard());
            t = t.getprev();
        }
        list.add(this.board);
        Collections.reverse(list);
        return list;        
          
    }
    public Iterable<Board> solution()  {
       // sequence of boards in a shortest solution; null if unsolvable
        if (isSolutionCalled) {
            return boardSolutions;
        }
        
        isSolutionCalled = true;
        
        MinPQ<Tuple> q = new MinPQ<Tuple>(new Comparator<Tuple>() {
        public int compare(Tuple tup1, Tuple tup2) {
              return Integer.compare((tup1.x), (tup2.x));
       }
    });
        MinPQ<Tuple> qTwin =  new MinPQ<Tuple>(new Comparator<Tuple>() {
        public int compare(Tuple tup1, Tuple tup2) {
              return Integer.compare((tup1.x), (tup2.x));
       }
    });
        
        Tuple t = new Tuple(board.manhattan(), board, null, 0);
        Tuple t2 = new Tuple(twinb.manhattan(), twinb, null, 0);
        q.insert(t);
        qTwin.insert(t2);
        boolean flag = false;
        
        while (true) {
            Tuple currTuple = q.delMin();
            Tuple currTupleTwin = qTwin.delMin(); 
//            System.out.println(currTuple.board.toString());
//            System.out.println(currTupleTwin.board.toString());
            if ((currTupleTwin.board).isGoal()) {
                 flag = true;
                
                this.move = -1;
                break;
            }
            if ((currTuple.board).isGoal()) {
                 boardSolutions =  find(currTuple);
                 flag = false;
                this.move = currTuple.getmoves();
                break;
            }
            
            for (Board b : (currTuple.getboard()).neighbors()) {
                boolean shouldadd = true;
                if (currTuple.getprev() != null) {
                    if (b.equals((currTuple.getprev()).getboard())) {
                        shouldadd = false;
                    }
                }
                if (shouldadd) {
                    int prior = b.manhattan() + (currTuple.getmoves());
                    q.insert(new Tuple(prior, b, currTuple,
                                       currTuple.getmoves() + 1));
                }
            }
            for (Board bTwin : (currTupleTwin.getboard()).neighbors()) {
                boolean shouldaddTwin = true;
                if (currTupleTwin.getprev() != null) {
                    if (bTwin.equals((currTupleTwin.getprev()).getboard())) {
                        shouldaddTwin = false;
                    }
                }
                if (shouldaddTwin) {
                    int prior = bTwin.manhattan() + (currTupleTwin.getmoves());
                    qTwin.insert(new Tuple(prior, bTwin, currTupleTwin, 
                                           currTupleTwin.getmoves() + 1));
                }
            }   
        }
        if (flag) {
            return null;
        }
        else {
            return boardSolutions;
        }
    }
 
    public static void main(String[] args)
    {
    In in = new In(args[0]);
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.boardSolutions)
            StdOut.println(board);
    }
    }
}