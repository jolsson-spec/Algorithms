/*import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStatswithSpaces {
    //boolean isPercolated = false;
    private static double[] statsites;

    public PercolationStatswithSpaces(int n, int trials) {
        //String linestr = "";
        //System.out.println("PercolationStats is starting!");
        statsites = new double[trials];
        //System.out.println("Statsites successfully initialized!");
        int opencount;
        //System.out.println("Opencount successfully initialized!");
        //String matstring = "";
        //System.out.println("Matstring successfully initialized!");
        Percolation perc4stat = new Percolation(n);
        //System.out.println("Percolation constructor success!");
        //System.out.println("First test: printing of percolator matrix");
        /*for (int i = 0; i < Percolation.dim; i++){
            linestr = "";
            for (int j = 0; j < Percolation.dim; j++){
                linestr = linestr + Percolation.percolator[i][j] + " ";
            }
            System.out.println(linestr);
        }*/
        //perc4stat.dim = n;
        //System.out.println("dim set to n!");
        //Percolation.percolator = new int[Percolation.dim][Percolation.dim];
        //System.out.println("Percolator initialized!");
        //Percolation.ufstorage = new int[Percolation.dim * Percolation.dim + 2];
        //System.out.println("Ufstorage initialized!");
        //System.out.println(perc4stat.percolates());
        //while (!isPercolated) {
        //System.out.println("Within for loop!");
/*
        for (int a = 0; a < trials; a++) {
            try {
                if (n <= 0) {
                    throw new IllegalArgumentException();
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        //System.out.println("Initialization successful at (" + i + "," + j + ")");
                        perc4stat.percolator[i][j] = 1;
                        //System.out.println("Verification:" + (perc4stat.percolator[i][j] == 1));
                    }
                }
            }
            catch (IllegalArgumentException w) {
                System.out.println("Error: n must be greater than or equal to 1!");
            }
            perc4stat.numberOfOpenSites();
            System.out.println("Running iteration #" + (a+1));
            opencount = 0;
            //System.out.println("Opencount test: " + opencount);
            //System.out.println("Within percolation for loop!");
            try {
                if (n <= 0) {
                    throw new IllegalArgumentException();
                }
            }
            catch (IllegalArgumentException w) {
                //System.out.println("Error: n and trials must be greater than or equal to 1!");
            }
            //System.out.println("Starting Monte Carlo simulation!");
            for (int f = 0; f < n*n; f++) {
                int i = StdRandom.uniformInt(0, n);
                //System.out.println("Row generated as " + i);
                int j = StdRandom.uniformInt(0, n);
                //System.out.println("Column generated as " + j);
                if ((!StdRandom.bernoulli()) && (!perc4stat.isOpen(i, j))) {
                    //System.out.println("Bernoulli has evaluated, open performed!");
                    perc4stat.open(i, j);
                    //System.out.println("Successfully passed open function!");
                    opencount++;
                    //System.out.println("Opencount value: " + opencount);
                }
                //else {
                //System.out.println("Bernoulli has evaluated, no open performed!");
                //}
                //isPercolated = perc4stat.percolates();
            }
                /*for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        //try {
                        //Percolation.percolator[i][j] = 1;
                        System.out.println("Pre-initialization: percolator[i][j] = " + Percolation.percolator[i][j]);
                        //System.out.println("MC loops initialized!");
                        isPercolated = perc4stat.percolates();
                        //System.out.println("Percolates has been evaluated with i = " + i + " ,j = " + j + "!");
                        //System.out.println("Evaluation: " + isPercolated);
                        //}
                        /*catch (ArrayIndexOutOfBoundsException r) {
                           System.out.println("Sorry, array out of bounds");
                        }
                        if (StdRandom.bernoulli()) {
                            System.out.println("Bernoulli has evaluated, no open performed!");
                        }
                        else {
                            System.out.println("Bernoulli has evaluated, open performed!");
                            //System.out.println("Made it past open confirmation!");
                            perc4stat.open(i, j);
                            //System.out.println("Made it past open command!");
                            opencount++;
                            //System.out.println("Made it past opencount increment!");
                            System.out.println("Opencount value: " + opencount);
                        }
                    }
                }*/

            //System.out.println("Test of statsites construction:");
            //System.out.println("Opencount final check: " + opencount);
            //System.out.println("Entry dim^2 check: " + (Percolation.dim*Percolation.dim));
            //System.out.println("Statsites agreement check: " + ((opencount)/(Percolation.dim*perc4stat.dim)));
/*
            double num = opencount;
            double denom = n*n;
            //System.out.println("Revised statsites agreement check: " + (num/denom));
            statsites[a] = 1 - (num/denom);
            System.out.println("Statsites entry addition: " + statsites[a]);
        }
    }

    private static double findSqrt(double n) {
        int iteration = 1;
        double y = n;
        System.out.println("Initial tests:");
        System.out.println(y);
        double z = ((y + (n / y)) / 2);
        System.out.println(z);
        while (y - z >= 0.00001) {
            System.out.println("Iteration #" + iteration);
            y = z;
            z = ((y + (n / y)) / 2);
            iteration++;
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
        StringBuilder linestr;
        PercolationStats percystat = new PercolationStats(N, T);
        System.out.println("I made it past the constructor!");
        double mean = percystat.mean();
        double stddev = percystat.stddev();
        double conLo = percystat.confidenceLo();
        double conHi = percystat.confidenceHi();
        System.out.println("First test: printing of percolator matrix");
        for (int i = 0; i < N; i++){
            linestr = new StringBuilder();
            for (int j = 0; j < N; j++){
                linestr.append(Percolation.percolator[i][j]).append(" ");
            }
            System.out.println(linestr);
        }
        System.out.println("Second test: ");
        System.out.println("mean                    = " + mean);
        System.out.println("stddev                  = " + stddev);
        System.out.println("95% confidence interval = [" + conLo + ", " + conHi + "]");
        linestr = new StringBuilder();
        for (double statsite : statsites) {
            linestr.append(statsite).append(" ");
        }
        //System.out.println("Statsites entries: " + linestr);
        //System.out.println(findSqrt(16.0));
    }
}
*/

