import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] percolator;
    private int[][] opensites;
    private int[] ufstorage;
    private int[] topropen;
    private int dim;
    private boolean hasOpenRun = false;

    public Percolation(int n) {
        dim = n;
        percolator = new int[dim + 1][dim + 1];
        ufstorage = new int[dim * dim + 3];
        for (int a = 0; a < dim * dim + 3; a++) {
            ufstorage[a] = a;
        }
        try {
            if (n == 0) {
                throw new IllegalArgumentException();
            }
            for (int i = 0; i < n + 1; i++) {
                for (int j = 0; j < n + 1; j++) {
                    percolator[i][j] = 1;
                }
            }
        } catch (IllegalArgumentException w) {
        }
    }

    public void open(int row, int col) {
        if ((row > 0) && (col > 0)) {
            row--;
            col--;
        }
        WeightedQuickUnionUF UF = new WeightedQuickUnionUF(dim * dim + 3);
        percolator[row][col] = 0;
        //System.out.println("Test of percolator value: " + percolator[row][col]);
        if (hasOpenRun) {
            for (int b = 0; b < dim * dim + 3; b++) {
                UF.union(ufstorage[b], b);
            }
        }
        //System.out.println("Top of open test");
        //System.out.println("ufstorage length: " + ufstorage.length);
        //StringBuilder linread = new StringBuilder();
        //for (int j : ufstorage) {
        //    linread.append(j);
        //}
        //System.out.println("ufstorage values: " + linread);
        try {
            if (isOpen(row, col - 1)) {
                UF.union(dim * row + col, dim * row + (col - 1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (isOpen(row + 1, col)) {
                UF.union(dim * row + col, (row + 1) * dim + col);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (isOpen(row, col + 1)) {
                UF.union(dim * row + col, dim * row + col + 1);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (isOpen(row - 1, col)) {
                UF.union(dim * row + col, (row - 1) * dim + col);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            for (int i = 1; i < dim * dim + 3; i++) {
                ufstorage[i] = UF.find(i);
            }
        } catch (NullPointerException z) {
        }
        hasOpenRun = true;
    }

    public boolean isOpen(int row, int col) {
        //System.out.println("(row, col) = (" + row + ", " + col + ")");
        if ((row > 0) && (col > 0)) {
            row--;
            col--;
        }
        return percolator[row][col] == 0;
    }

    public boolean isFull(int row, int col) {
        WeightedQuickUnionUF UF = new WeightedQuickUnionUF(dim * dim + 3);
        //System.out.flush();
        //System.out.println("isFull initialized!");
        boolean top = false;
        boolean eval = false;
        boolean topret = true;
        for (int b = 0; b < dim * dim + 3; b++) {
            try {
                UF.union(ufstorage[b], b);
            }
            catch (ArrayIndexOutOfBoundsException v) {
                continue;
            }
            //System.out.print(UF.find(b));
        }
        if ((row > 0) && (col > 0)) {
            //System.out.println("For input (" + row + ", " + col + "): successfully converted");
            row--;
            col--;
        }
        //System.out.println("isOpen(2, 6) :" + isOpen(row, col));
        //System.out.println(isOpen(row, col + 1));
        //System.out.println(isOpen(row, col - 1));
        //System.out.println(isOpen(row - 1, col));
        //System.out.println(isOpen(row + 1, col));

        boolean connected = true;
        if (percolator[row][col] != 0) {
            return false;
        }
        if ((row == 0) && (percolator[row][col] == 0)) {
            //System.out.println("Entered");
            return true;
        }
        try {
            //System.out.println("Entered 2");
            eval = ((percolator[row][col] == 0) && ((percolator[row][col+1] == 0) || (percolator[row][col-1] == 0) || (percolator[row+1][col] == 0) || (percolator[row-1][col] == 0)));
            //System.out.println("For input (" + row + ", " + col + "): eval returned true");
        }
        catch (ArrayIndexOutOfBoundsException v) {
        }
        catch (NullPointerException w) {}
        if (eval) {
            //System.out.println("Entered 3");
            /*for (int f = 0; f < opensites.length + 1; f++) {
                if (UF.find(f) == UF.find(row * dim + col)) {
                    //System.out.println("checked" + f);
                    assert true;
                }
                else connected = false;
            }*/
            numberOfOpenSites();
            percolates();
            for (int i = 0; i < opensites.length - 1; i++) {
                top = (UF.find(opensites[i][3]) == UF.find(row * dim + col));
                if (!top) {
                    topret = false;
                }
            }
            return topret;
        }
        else return false;
        //return percolator[row][col] == 1;
    }

    public int numberOfOpenSites() {
        int[][] sitestore = new int[dim * dim + 1][4];
        for (int n = 0; n < dim * dim + 1; n++) {
            sitestore[n][0] = 1;
        }
        int countOpen = 0;
        try {
            if (!(dim > 0)) {
                throw new IllegalArgumentException();
            }
            for (int i = 1; i < dim + 1; i++) {
                for (int j = 1; j < dim + 1; j++) {
                    try {
                        if (isOpen(i, j)) {
                            //System.out.println("Completed isOpen with i = " + i + ", j = " + j);
                            sitestore[countOpen][0] = percolator[i-1][j-1];
                            sitestore[countOpen][1] = i-1;
                            sitestore[countOpen][2] = j-1;
                            sitestore[countOpen][3] = dim * (i-1) + j-1;
                            countOpen++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("out of bounds");
                    }
                }
            }
            //System.out.println(countOpen);
            opensites = new int[countOpen][4];
            int opensitesind = 0;
            for (int m = 1; m < dim * dim + 1; m++) {
                if (sitestore[m][0] == 0) {
                    opensites[opensitesind][0] = sitestore[m][0];
                    opensites[opensitesind][1] = sitestore[m][1];
                    opensites[opensitesind][2] = sitestore[m][2];
                    opensites[opensitesind][3] = sitestore[m][3];
                    opensitesind++;
                }
            }
        } catch (IllegalArgumentException e) {
        }
        return countOpen;
    }

    public boolean percolates() {
        int[] topropen = new int[dim];
        int topcount = 0;
        int[] bottomropen = new int[dim];
        int bottomcount = 0;
        for (int c = 1; c < dim + 1; c++) {
            if (percolator[0][c] == 0) {
                try {
                    topropen[topcount] = c;
                    topcount++;
                    //System.out.println("Percolator at (0, " + c + ") fed into topropen with count " + topcount);
                } catch (NullPointerException v) {
                } catch (ArrayIndexOutOfBoundsException b) {
                }
            }
            if (percolator[dim - 1][c] == 0) {
                try {
                    bottomropen[bottomcount] = c;
                    bottomcount++;
                    //System.out.println("Percolator at (" + (dim - 1) + ", " + c + ") fed into bottomropen with count " + bottomcount);
                } catch (NullPointerException v) {
                } catch (ArrayIndexOutOfBoundsException b) {
                }
            }
            //.println("topropen: " + topcount);
            //System.out.println("bottomropen: " + bottomcount);
        }
        try {
            WeightedQuickUnionUF UF = new WeightedQuickUnionUF(dim * dim + 3);
            numberOfOpenSites();
            //System.out.println("ufstorage length: " + ufstorage.length);
            //StringBuilder linread = new StringBuilder();
            //for (int i : ufstorage) {
            //    linread.append(i);
            //}
            //System.out.println("ufstorage inputs:" + linread);
            for (int b = 1; b < dim * dim + 3; b++) {
                //System.out.println("Broke ufstorage loop!");
                UF.union(ufstorage[b], b);
                //System.out.println("Union performed between " + ufstorage[b] + " and " + b);
            }
            //System.out.println("Evaluation of UF.union before virtual opens");
            for (int g = 0; g < dim * dim + 3; g++) {
                //System.out.println(UF.find(g));
            }
            for (int d = 0; d < topcount; d++) {
                try {
                    UF.union(dim * dim, opensites[d][3]); //union the north virtual (second to last) and the first open
                    //System.out.println("Union performed between " + dim*dim + " and " + opensites[d][3]);
                } catch (ArrayIndexOutOfBoundsException z) {
                }
            }
            if (opensites.length > 1) {
                //System.out.println("TEST");
                for (int e = 0; e < bottomcount; e++) {
                    UF.union(opensites[opensites.length - (1 + e)][3], dim * dim + 1); //union south virtual (last)
                    //System.out.println("Union performed between " + (dim * dim + 1) + " and " + opensites[opensites.length - (1 + e)][3]);
                }
            }
            for (int h = 0; h < dim * dim + 3; h++) {
                //System.out.println(UF.find(h));
            }
            //System.out.println("Pre-perc test:");
            //System.out.println(UF.find(dim*dim) + " == " + UF.find(dim*dim + 1));
            return UF.find(dim * dim) == UF.find(dim * dim + 1);
        } catch (NullPointerException e) {
            return false;
        }

    }

    public static void main(String[] args) {
        /*int i;
        int j;
        boolean done = false;
        int N = StdIn.readInt();
        StringBuilder linestr;
        Percolation perc = new Percolation(N);
        perc.numberOfOpenSites();
        perc.percolates();
        //System.out.println("Enter pair of open entries:");
        while (!done) {
            try {
                i = StdIn.readInt();
                j = StdIn.readInt();
                perc.open(i, j);
                System.out.println("Next entry?");
            }
            catch (InputMismatchException e) {
                System.out.println("All done");
                done = true;
            }
        }
        i = StdIn.readInt();
        j = StdIn.readInt();
        System.out.println(perc.isFull(i, j));
        //StringBuilder linestr;
        for (i = 0; i < N + 1; i++) {
            linestr = new StringBuilder("");
            for (j = 0; j < N + 1; j++) {
                linestr.append(perc.percolator[i][j]);
                linestr.append(" ");
            }
            System.out.println(linestr);
        }

         */
    }
}
