import java.util.Scanner;

public class Main {
public static void main(String[] args)
{   long startTime = System.nanoTime();
    Scanner sc = new Scanner(System.in);
    int n, k;
    System.out.println("Enter the number of vertices: ");
    n = sc.nextInt();
    System.out.println("Enter k: ");
    k = sc.nextInt();
    Graph g = new Graph(n, k);
    if(g.CreateRandomGraph())
    {
       // g.PrintMatrix();
        g.DisplayMatrix();
        g.PrintNumberOfEdges();
        g.PrintMinDegree();
        g.PrintMaxDegree();
    }
    long endTime = System.nanoTime();
    long runningTime = endTime - startTime;

    System.out.println("Running time: " + runningTime + " ns");

}
}