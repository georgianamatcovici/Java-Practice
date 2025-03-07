package p1;

public class Graph {
    private int n, k;
    private int[][] A;
    private int[] Subset;
    private int [] Visited;
    private boolean hasClique=false;

    public Graph(int n, int k) {
        this.n=n;
        this.k=k;
        A=new int[n][n];
        Subset=new int[k];
        Visited=new int[n];
        for(int i=0; i<k; i++)
        {
            Visited[i]=0;
        }

        int i, j;
        for(i=0; i<n-1; i++) {
            for(j=i+1; j<n; j++) {
                A[i][j]=(int)(Math.random()*2);
                A[j][i]=A[i][j];
            }
        }
    }
    public void printSubset()
    {
        for(int i=0; i<k; i++)
            System.out.print(Subset[i]+" ");
        System.out.println();


    }
    public void printMatrix()
    {int i, j;
        for(i=0; i<n; i++) {
            for (j = 0; j < n; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }

    }
    public boolean checkClique()
    {int i, j;
        for(i=0; i<k-1; i++)
        {//System.out.print(Subset[i]+" ");
            for(j=i+1; j<k; j++) {
              //  System.out.println(Subset[i]+" "+Subset[j]);

                if (A[Subset[i]][Subset[j]] == 0)
                    return false;
            }
           // System.out.println();
        }
        return true;
    }

    public void backTracking(int p) {
        if (p == k) { //printSubset();
            if(checkClique()) {hasClique=true;
                printSubset(); return;}
           // System.out.println();

        } else {
            for (int i = 0; i < n; i++) {
                if (Visited[i] == 0 && (p==0 || Subset[p-1]<i)) {
                    Visited[i] = 1;
                    Subset[p] = i;
                    backTracking(p + 1);
                    Visited[i] = 0;


                }
            }
        }
    }
    public void printAnswer(boolean flag)
    { backTracking(0);
        if (hasClique) {
               if(!flag) System.out.println("The graph has a clique of size " + k);
               else System.out.println("The graph has a stable set of size " + k);
//            for (int i = 0; i < k; i++) {
//                System.out.println(Subset[i] + " ");
//            }
            } else {
           if(!flag) System.out.println("The graph has no clique of size " + k);
           else System.out.println("The graph has no stable set of size " + k);
        }
    }
    public void complementGraph()
    {int i, j;
        for(i=0; i<n; i++) {
            for(j=0; j<n; j++) {
                A[i][j]=1-A[i][j];
            }
        }

    }

}

