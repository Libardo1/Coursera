public class PercolationStats {
    private double [] percolationThreshhold;
    
    public PercolationStats(int N, int T)
    // perform T independent experiments on an N-by-N grid
    {
        if ((N <= 0) || (T <= 0)) throw new IllegalArgumentException();
        percolationThreshhold =  new double[T];
        for (int i = 0; i < T; i++) {
            int round = 0;
            Percolation perc = new Percolation(N);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, N+1);
                int column = StdRandom.uniform(1, N+1);
                
                if (!perc.isOpen(row, column)) {
                    perc.open(row, column);
                    round++;
                }      
            }
            percolationThreshhold[i]  = ((double) round)/(N*N); 
        }
    }
    public double mean()// sample mean of percolation threshold
    {
        return StdStats.mean(percolationThreshhold);
    }
    
    public double stddev() {                 
        // sample standard deviation of percolation threshold
        return StdStats.stddev(percolationThreshhold);
    }
    public double confidenceLo()// low  endpoint of 95% confidence interval
    {
        return mean() - ((1.96*stddev())/Math.sqrt(percolationThreshhold.length));
    }
    public double confidenceHi()     
    {
        return mean() + ((1.96*stddev())/Math.sqrt(percolationThreshhold.length));
    } // high endpoint of 95% confidence interval
    
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(200, 100);
        
        StdOut.print("mean = "+ps.mean()+"\n");
        StdOut.print("std dev = "+ps.stddev()+"\n");
        StdOut.print("95% confidence interval = "
                         +ps.confidenceLo()+", "+ps.confidenceHi());
    }
}