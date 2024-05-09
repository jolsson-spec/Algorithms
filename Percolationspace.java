/*import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationwithSpaces {
    // blocked = 1, free = 0
    // Initialization of variables
    private static int[][] percolator;
    private static int[][] sitestore;
    private static int[][] opensites;
    private static int[] ufstorage;
    //public static int[][] unioncollector;
    private int[] topropen;
    private int[] bottomropen;
    private static int curcount;
    private static int dim;
    private boolean hasOpenRun = false;
    //int id[];
    //int sz[];
    private static int topopens = 0;
    private static int bottomopens = 0;

    // WeightedQuickUnionUF UF = new WeightedQuickUnionUF(dim*dim + 1);
    // Standard functions for union/find
    /*public void QuickUnionUF(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] <  sz[j]) {id[i] = j; sz[j] += sz[i];}
        else {id[j] = i; sz[i] += sz[j];}
    }
    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
    // Percolation functions
    public PercolationwithSpaces(int n) {
        dim = n;
        percolator = new int[dim][dim];
        ufstorage = new int[dim*dim + 2];
        try {
            if (n <= 0) {
                throw new IllegalArgumentException();
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    //System.out.println("Initialization successful at (" + i + "," + j + ")");
                    percolator[i][j] = 1;
                    //System.out.println("Verification:" + (percolator[i][j] == 1));
                }
            }
        }
        catch (IllegalArgumentException w) {
            System.out.println("Error: n must be greater than or equal to 1!");
        }
    }
    public void open(int row, int col) {
        //UNION TAKES INPUTS IN LIST FORM, NOT PERCOLATOR FORM
        WeightedQuickUnionUF UF = new WeightedQuickUnionUF(dim * dim + 2);
        //System.out.print("IMPORTANT TEST HERE:");
        percolator[row][col] = 0;
        if (hasOpenRun) {
            for (int b = 0; b < dim * dim + 2; b++) {
                //System.out.println("top loop at open: I made it inside without throwing!");
                UF.union(ufstorage[b], b);
                //System.out.println(UF.find(b));
            }
        }
        if (row == 0) {
            //System.out.println("Current topopens value = " + topopens);
            topopens++;
        }
        //topopens++;
        if (row == dim - 1) {
            //System.out.println("Current bottomopens value = " + bottomopens);
            bottomopens++;
        }
        //bottomopens++;
        // ufstorage = new int[dim*dim + 2];
        // row++;
        // col++;
        /*try {
            if (isOpen(row - 1, col - 1)) {
                UF.union(dim*row + col, (row - 1)*dim + (col - 1));
                System.out.println("I unioned (" + row + ", " + col + ")" + " with " + "(" + (row-1) + ", " + (col-1) + ")");
                System.out.println("Check: " + (UF.find(dim*row + col)==UF.find((row-1)*dim + (col - 1))));
            }
            else {
                System.out.println("I ran, but did not perform a union!");
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("I threw an exception!");
        }*/
/*
        try {
            if (isOpen(row, col - 1)) {
                UF.union(dim*row + col, dim * row + (col - 1));
                //System.out.println("I unioned (" + row + ", " + col + ")" + " with " + "(" + (row) + ", " + (col-1) + ")");
                //System.out.println("Check: " + (UF.find(dim*row + col)==UF.find((row)*dim + (col - 1))));
            }
            //else {
            //System.out.println("I ran, but did not perform a union!");
            //}
        }
        catch (ArrayIndexOutOfBoundsException e) {
            //System.out.println("I threw an exception!");
        }
        /*try {
            if (isOpen(row + 1, col - 1)) {
                UF.union(dim*row + col, (row + 1) * dim + (col - 1));
                System.out.println("I unioned (" + row + ", " + col + ")" + " with " + "(" + (row+1) + ", " + (col-1) + ")");
                System.out.println("Check: " + (UF.find(dim*row + col)==UF.find((row+1)*dim + (col - 1))));
            }
            else {
                System.out.println("I ran, but did not perform a union!");
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("I threw an exception!");
        }*/
/*
        try {
            if (isOpen(row + 1, col)) {
                UF.union(dim*row + col, (row + 1) * dim + col);
                //System.out.println("I unioned (" + row + ", " + col + ")" + " with " + "(" + (row+1) + ", " + (col) + ")");
                //System.out.println("Check: " + (UF.find(dim*row + col)==UF.find((row+1)*dim + (col))));
            }
            //else {
            //System.out.println("I ran, but did not perform a union!");
            //}
        }
        catch (ArrayIndexOutOfBoundsException e) {
            //System.out.println("I threw an exception!");
        }
        /*try {
            if (isOpen(row+1, col+1)) {
                UF.union(dim * row + col, (row + 1) * dim + col + 1);
                System.out.println("I unioned (" + row + ", " + col + ")" + " with " + "(" + (row + 1) + ", " + (col + 1) + ")");
                System.out.println("Check: " + (UF.find(dim * row + col) == UF.find((row + 1) * dim + (col + 1))));
            }
            else {
                System.out.println("I ran, but did not perform a union!");
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("I threw an exception!");
        }*/
                /*
        try {
            if (isOpen(row, col + 1)) {
                UF.union(dim*row + col, dim*row + col + 1);
                //System.out.println("I unioned (" + row + ", " + col + ")" + " with " + "(" + (row) + ", " + (col+1) + ")");
                //System.out.println("Check: " + (UF.find(dim*row + col)==UF.find((row)*dim + (col+1))));
            }
            //else {
            //System.out.println("I ran, but did not perform a union!");
            //}
        }
        catch (ArrayIndexOutOfBoundsException e) {
            //System.out.println("I threw an exception!");
        }
        /*try {
            if (isOpen(row - 1, col + 1)) {
                UF.union(dim*row + col, (row - 1) * dim + col + 1);
                System.out.println("I unioned (" + row + ", " + col + ")" + " with " + "(" + (row-1) + ", " + (col+1) + ")");
                System.out.println("Check: " + (UF.find(dim*row + col)==UF.find((row-1)*dim + (col+1))));
            }
            else {
                System.out.println("I ran, but did not perform a union!");
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("I threw an exception!");
        }*/
                /*
        try {
            if (isOpen(row - 1, col)) {
                UF.union(dim*row + col, (row - 1) * dim + col);
                //System.out.println("I unioned (" + row + ", " + col + ")" + " with " + "(" + (row-1) + ", " + (col) + ")");
                //System.out.println("Check: " + (UF.find(dim*row + col)==UF.find((row-1)*dim + (col))));
            }
            //else {
            //System.out.println("I ran, but did not perform a union!");
            //}
        }
        catch (ArrayIndexOutOfBoundsException e) {
            //System.out.println("I threw an exception!");
        }
        //for (int m = 0; m < dim*dim + 2; m++) System.out.println(UF.find(m));
        try {
            for (int i = 0; i < dim * dim + 2; i++) {
                ufstorage[i] = UF.find(i);
            }
        }
        catch (NullPointerException z) {
            System.out.println("There's a problem with ufstorage!");
        }
        hasOpenRun = true;
    }
    public boolean isOpen(int row, int col) {
        // TAKES INPUTS IN PERCOLATOR FORMAT
        return percolator[row][col] == 0;

    }
    public boolean isFull(int row, int col) {
        return percolator[row][col] == 1;
    }
    public int numberOfOpenSites() {
        sitestore = new int[dim*dim][4];
        for (int n = 0; n < dim*dim; n++) {
            sitestore[n][0] = 1;
        }
        int countOpen = 0;
        try {
            if (!(dim > 0)) {
                throw new IllegalArgumentException();
            }
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    if (percolator[i][j] == 0) {
                        //System.out.println("Percolator zero, adding to opensites!");
                        sitestore[countOpen][0] = percolator[i][j];
                        sitestore[countOpen][1] = i;
                        sitestore[countOpen][2] = j;
                        sitestore[countOpen][3] = dim*i + j;
                        countOpen++;
                        //System.out.println("countOpen post increment = " + countOpen);
                    }
                    //else {
                    //System.out.println("Percolator is non-zero!");
                    //}
                }
            }
            //System.out.println("Warning: opensites now initialized with row dim = " + countOpen);
            opensites = new int[countOpen][4];
            //System.out.println("Opensites dim: " + opensites.length);
            int opensitesind = 0;
            for (int m = 0; m < dim*dim; m++) {
                if (sitestore[m][0] == 0) {
                    opensites[opensitesind][0] = sitestore[m][0];
                    //System.out.println("0 Opensites at opensitesind = " + opensitesind + " is " + opensites[opensitesind][0]);
                    //System.out.println("0 Sitestore at m = " + m + " is " + sitestore[m][0]);
                    opensites[opensitesind][1] = sitestore[m][1];
                    //System.out.println("1 Opensites at opensitesind = " + opensitesind + " is " + opensites[opensitesind][1]);
                    //System.out.println("1 Sitestore at m = " + m + " is " + sitestore[m][1]);
                    opensites[opensitesind][2] = sitestore[m][2];
                    //System.out.println("2 Opensites at opensitesind = " + opensitesind + " is " + opensites[opensitesind][2]);
                    //System.out.println("2 Sitestore at m = " + m + " is " + sitestore[m][2]);
                    opensites[opensitesind][3] = sitestore[m][3];
                    //System.out.println("3 Opensites at opensitesind = " + opensitesind + " is " + opensites[opensitesind][3]);
                    //System.out.println("3 Sitestore at m = " + m + " is " + sitestore[m][3]);
                    opensitesind++;
                }
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: bad dimension!");
        }
        /*for (int i = 0; i < open.length - 1; i++) {
            union(percolator[open[i][1]][open[i][2]], percolator[open[i+1][1]][open[i+1][2]]);
        }*/
                /* */
        // below: real, efficient method using virtual sites
                /*
        for (int c = 0; c < dim; c++) {
            if (percolator[0][c] == 0) {
                //System.out.println("percolator[0][c] == 0 is true with index " + c + ", but...");
                try {
                    topropen[c] = percolator[0][c];
                }
                catch (NullPointerException v) {
                    //System.out.println("Topropen did not respond with index " + c);
                }
                catch (ArrayIndexOutOfBoundsException b) {
                    //System.out.println("Topropen was out of bounds at index " + c);
                }
            }
            if (percolator[dim - 1][c] == 0) {
                //System.out.println("percolator[dim-1][c] == 0 is true with index " + c + ", but...");
                try {
                    bottomropen[c] = percolator[dim - 1][c];
                }
                catch (NullPointerException v) {
                    //System.out.println("Bottomropen did not respond with index " + c);
                }
                catch (ArrayIndexOutOfBoundsException b) {
                    //System.out.println("Topropen was out of bounds at index " + c);
                }
            }
        }
        try {
            //System.out.println("First");
            WeightedQuickUnionUF UF = new WeightedQuickUnionUF(dim*dim + 2);
            //for (int m = 0; m < dim*dim + 2; m++) System.out.println(UF.find(m));
            //System.out.println("Second");
            numberOfOpenSites();
            //System.out.println("Third");
            //System.out.println("Possibly final test:");
            for (int b = 0; b < dim * dim + 2; b++) {
                //System.out.println("bottom loop at open: I made it inside without throwing!");
                UF.union(ufstorage[b], b);
                //System.out.println(UF.find(b));
            }
            for (int d = 0; d < topropen.length; d++) {
                //System.out.println("Attention: opensites may have an ArrayIndex error! Possibly use a try/except?");
                //System.out.println("d value: " + d);
                //System.out.println("Length of topropen: " + topropen.length);
                //for (int e = 0; e < topropen.length; e++) {
                //    linestr2 = linestr2 + topropen[e] + " ";
                //}
                //System.out.println("Vals of topropen: " + linestr2);
                //for (int i = 0; i < dim; i++){
                //    linestr = "";
                //    for (int j = 0; j < dim; j++){
                //    linestr = linestr + percolator[i][j] + " ";
                //    }
                //    System.out.println(linestr);
                //}
                try {
                    //System.out.println("Test of opensites value at index d = " + d + ": " + opensites[d][3]);
                    UF.union(dim * dim, opensites[d][3]); //union the north virtual (second to last) and the first open
                }
                catch (ArrayIndexOutOfBoundsException z) {
                }
            }
            //System.out.println("Important test: " + (UF.find(dim*dim) == UF.find(opensites[0][3])));
            //System.out.println("Fourth");
            //System.out.println(opensites.length);
            //for (int o = 0; o < opensites.length; o++) {
            //    System.out.println("Opensites in matrix form:");
            //    System.out.println(opensites[o][0] + " " + opensites[o][1] + " " + opensites[o][2] + " " + opensites[o][3]);
            //}
            if (opensites.length > 1) {
                for (int e = 0; e < topropen.length; e++) {
                    UF.union(opensites[opensites.length - 1][3], dim * dim + 1); //union south virtual (last)
                }
                //if (UF.find(opensites[opensites.length - 1][3]) == UF.find(dim*dim + 1)) {
                //System.out.println("South virtual site successfully completed union!");
                //}
            }
            //else {
            //System.out.println("South virtual site did not union!");
            //}
            //System.out.println("Fifth");
            //union the south virtual and the last open
            // System.out.println(connected(0, opensites[0][2]));
            //System.out.println("Sixth");
            // System.out.println(UF.find(0));
            //System.out.println("Seventh");
            // System.out.println(UF.find(dim*dim + 1));
            //System.out.println("Eighth");
            // System.out.println("Open union tests now running...");
            // (int m = 0; m < dim*dim + 2; m++) System.out.println(UF.find(m));
            if (UF.find(dim*dim) == UF.find(dim*dim + 1)) {
                System.out.println("I percolated!");
                return true;
            }
            else {
                System.out.println("I failed to percolate!");
                return false;
            }
        }
        catch (NullPointerException e) {
            //System.out.println("I caught an exception!");
            return false;
        }

    }
    public static void main(String[] args) {
        //Initialize WeightedQuickUnionUF to have N^2 + 2 elements. The first
        //element is the north virtual site, while the last is the south
        //virtual site. The middle elements of the list, excluding the edges,
        //correspond to reading the percolator matrix's rows from left to right.
        //Ensure that each newly opened site is joined with its neighbors. When
        //the process is finished, evaluate whether the north and south virtual
        //sites are connected. This is the boolean value that percolates() must return.
        // WeightedQuickUnionUF UF = new WeightedQuickUnionUF(dim*dim + 2);
        // String namestr;
        Percolation perc = new Percolation(8);
        /*for (int a = 0; a < dim; a++) {
            String name = String.valueOf(a);
            name = new int[dim];
        }*/
        /*perc.open(0, 2);
        perc.open(0, 3);
        perc.open(0, 5);
        perc.open(1, 0);
        perc.open(1, 1);
        perc.open(1, 2);
        perc.open(1, 3);
        perc.open(1, 4);
        perc.open(2, 0);
        perc.open(2, 1);
        perc.open(2, 4);
        perc.open(2, 5);
        perc.open(3, 2);
        perc.open(3, 3);
        perc.open(3, 4);
        perc.open(3, 5);
        perc.open(3, 6);
        perc.open(4, 0);
        perc.open(4, 6);
        perc.open(4, 7);
        perc.open(5, 1);
        perc.open(5, 3);
        perc.open(5, 4);
        perc.open(5, 5);
        perc.open(6, 1);
        perc.open(6, 2);
        perc.open(6, 4);
        perc.open(6, 5);
        perc.open(6, 7);
        perc.open(7, 0);
        perc.open(7, 2);
        perc.open(7, 6);*/
        /*perc.open(0, 0);
        perc.open(1, 0);
        perc.open(2, 0);
        perc.open(3, 0);
        perc.open(4, 0);
        perc.open(5, 0);
        perc.open(6, 0);
        perc.open(7, 0);
        for (int i = 0; i < dim; i++) {
            //System.out.println("[");
            System.out.println(percolator[i][0] + " " + percolator[i][1] + " " + percolator[i][2] + " " + percolator[i][3] + " " + percolator[i][4] + " " + percolator[i][5] + " " + percolator[i][6] + " " + percolator[i][7]);
            //System.out.println("]");
        }
        perc.percolates();
        */
                /*
    }
}
*/
