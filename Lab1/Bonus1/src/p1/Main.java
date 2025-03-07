package p1;


import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner myScanner=new Scanner(System.in);
        System.out.println("n:");
        int n=myScanner.nextInt();
        System.out.println("k:");
        int k=myScanner.nextInt();
        Graph myGraph=new Graph(n, k);
        myGraph.printMatrix();
        myGraph.printAnswer(false);
        System.out.println("");
        myGraph.complementGraph();
        myGraph.printAnswer(true);



    }
}