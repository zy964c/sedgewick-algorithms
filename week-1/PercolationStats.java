public class PercolationStats {
  private double[] trialResults;
  private int siteCount;
  public PercolationStats(int N, int T) {
    siteCount = N * N;
    trialResults = new double[T];

    for (int i = 0; i < T; i++) {
      Percolation perc = new Percolation(N);
      int sitesOpen = 0;
      while(!perc.percolates()) {
        int x = random(N);
        int y = random(N);
        if (!perc.isOpen(x, y)) {
          sitesOpen++;
          perc.open(x, y);
        }
      }
      trialResults[i] = (double) sitesOpen / (double) siteCount;
    }

  }
  public double mean() {
    return StdStats.mean(trialResults);
  }
  public double stddev() {
    return StdStats.stddev(trialResults);
  }
  public double confidenceLo() {
    return mean() - (1.96 * stddev())/Math.sqrt(siteCount);
  }
  public double confidenceHi() {
    return mean() + (1.96 * stddev())/Math.sqrt(siteCount);
  }

  private int random(int N) {
    return (int)Math.floor((StdRandom.uniform() * N) + 1);
  }

  public static void main(String[] args)  {
    int N = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);

    PercolationStats stats = new PercolationStats(N, T);
    StdOut.println(stats.mean());
    StdOut.println(stats.stddev());
    StdOut.println(stats.confidenceLo());
    StdOut.println(stats.confidenceHi());
  }

}
