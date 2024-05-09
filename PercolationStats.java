import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] statsites;
    public PercolationStats(int n, int trials) {
        statsites = new double[trials];
        int opencount;
            for (int a = 0; a < trials; a++) {
                Percolation perc4stat = new Percolation(n);
                perc4stat.numberOfOpenSites();
                opencount = 0;
                try {
                    if (n <= 0) {
                        throw new IllegalArgumentException();
                    }
                }
                catch (IllegalArgumentException w) {
                }
                for (int f = 0; f < n*n; f++) {
                    int i = StdRandom.uniformInt(0, n);
                    int j = StdRandom.uniformInt(0, n);
                    if ((!StdRandom.bernoulli()) && (!perc4stat.isOpen(i, j))) {
                        perc4stat.open(i, j);
                        opencount++;
                    }
                }
                double num = opencount;
                double denom = n*n;
                statsites[a] = 1 - (num/denom);
            }
        }

    private static double findSqrt(double n) {
        double y = n;
        double z = ((y + (n / y)) / 2);
        while (y - z >= 0.00001) {
            y = z;
            z = ((y + (n / y)) / 2);
        }
        return z;
    }

    public double mean() {
        return StdStats.mean(statsites);
    }

    public double stddev() {
        return StdStats.stddev(statsites);
    }

    public double confidenceLo() {
        return (mean() - (1.96 * stddev()) / (findSqrt(statsites.length)));
    }

    public double confidenceHi() {
        return (mean() + (1.96 * stddev()) / (findSqrt(statsites.length)));
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats percystat = new PercolationStats(N, T);
        double mean = percystat.mean();
        double stddev = percystat.stddev();
        double conLo = percystat.confidenceLo();
        double conHi = percystat.confidenceHi();
        System.out.println("mean                    = " + mean);
        System.out.println("stddev                  = " + stddev);
        System.out.println("95% confidence interval = [" + conLo + ", " + conHi + "]");
    }
}

