import java.util.List;

public class Solver {
    private Board board;
    private Board twinb;
    private int move;
    
    private class Tuple { 
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
} 
    
    public Solver(Board initial)     
    {      
        board = initial;
        twinb = board.twin();
        move = 0;
        
        MinPQ<Tuple> q = new MinPQ<Tuple>( new Comparator<Tuple>() {
              public int compare(Tuple tup1, Tuple tup2) {
              return compare((tup1.x), (tup2.x));
    }
    });
        MinPQ<Tuple> qTwin = new MinPQ<Tuple>( new Comparator<Tuple>() {
              public int compare(Tuple tup1, Tuple tup2) {
              return compare((tup1.x), (tup2.x));
    }
    });
       
       
        
    
     
        
    }
    public boolean isSolvable() {
        if (this.solution == null) {
            return false;
        }
        else {
            return true;
        }
        
        
       // is the initial board solvable?
    }
    public int moves() {
        if (isSolvable()) {
            return this.move;
        }
        else{
            return -1;
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
        Tuple t = new Tuple(prior, board, null, 0);
        q.insert(t);
        qTwin.insert(t);
        flag = false;
         // find a solution to the initial board (using the A* algorithm)
        while ((!q.isEmpty() )  || (!qTwin.isEmpty())) {
            Tuple currTuple = q.min();
            Tuple currTupleTwin = qTwin.min();
            if ((currTupleTwin.board).isGoal()) {
                Iterable<Board> ans = find(currTupleTwin);
                flag = true;
                //this.move = currentTupleTwin.prior;
                break;
            }
            if ((currTuple.board).isGoal()){
                Iterable<Board> ans = find(currTupleTwin);
                this.move = currentTuple.prior;
                break;
            }
            for (Board b : (currTuple.board).neighbors()) {
                if (!b.equals((currTuple.prev).board)) {
                    prior = b.hamming() + ((currTuple.board).moves) ;
                    q.insert(new Tuple(prior, b,currTuple, ((currTuple.board).moves) + 1));
                }
            }
            for (Board bTwin : (currTupleTwin.board).neighbors()) {
                if (!bTwin.equals((currTupleTwin.prev).board)) {
                    prior = bTwin.hamming() + ((currTupleTwin.board).moves) ;
                    qTwin.insert(new Tuple(prior, bTwin, currTupleTwin, ((currTupleTwin.board).moves) + 1));
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