public class Graph {
    private int n, k;
    private int[][] A;
    private int m;
    public Graph(int n, int k)
    {this.n=n;
        this.k=k;
        A=new int[n+1][n+1];
    }
    public boolean CreateRandomGraph()
    {
        if(n<2*k)
        {
            System.out.println("The graph cannot have those properties.");
            return false;
        }
        else
        {
            int i, j;
            for(i=1; i<=k; i++)
            {for(j=1; j<=k; j++)
            {A[i][j]=1;
            }
            }
            for(i=n-k+1; i<=n; i++)
                for(j=n-k+1; j<=n; j++)
                {
                    A[i][j]=0;
                }
            for(i=k+1; i<=n-k; i++)
                for(j=k+1; j<=n-k; j++)
                    A[i][j]=(int)(Math.random()*2);}
        return true;


    }

    public void PrintMatrix()
    {
        int i, j;
        for(i=1; i<=n; i++)
        {for(j=1; j<=n; j++)
            System.out.print(A[i][j]+" ");
            System.out.println();
        }

    }
    public void DisplayMatrix()
    {
        StringBuilder myBuilder=new StringBuilder();
        int i, j;
        for(i=1; i<=n; i++)
        {
            for (j = 1; j <= n; j++) {
               if(A[i][j]==1) myBuilder.append("\u2BC0");
               else myBuilder.append("\u2B1A");
                myBuilder.append(" ");

            }
            myBuilder.append("\n");
        }

        //myBuilder.append("some text");
        System.out.println(myBuilder.toString());
    }
    public void PrintNumberOfEdges()
    {
        int i, j;
        int sum=0;
        for(i=1; i<=n; i++) {
            for (j = 1; j <= n; j++) {
            sum+=A[i][j];
            }
        }
        m=sum/2;
        System.out.println(sum/2);
    }
    public void PrintMaxDegree()
    {
        int i, j;
        int max=0, degree=0;
        for(i=1; i<=n; i++) {
            degree=0;
            for (j = 1; j <= n; j++) {
                degree+=A[i][j];

            }
            if(degree>max) max=degree;

        }
        System.out.println("\u0394"+"(G)="+max);
    }
    public void PrintMinDegree()
    {
        int i, j;
        int min=0, degree=0;
        for(i=1; i<=n; i++) {
            degree=0;
            for (j = 1; j <= n; j++) {
                degree+=A[i][j];

            }
            if(degree<min) min=degree;
        }
        System.out.println("\u03B4"+"(G)="+min);
    }
    public boolean CheckSum()
    {int i, j;
        int sum=0;
        for(i=1; i<=n; i++)
        {
            for (j = 1; j <= n; j++)
                sum+=A[i][j];
        }
        return sum==2*m;


    }




}