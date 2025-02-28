public class Main {
    public static void main(String[] args) {

        System.out.println("Hello World!");
        String[] Languages= {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n=(int) (Math.random()*1000000);
        System.out.println(n);
        n*=3;
        System.out.println(n);
        n+=0b10101;
        System.out.println(n);
        n+=0xFF;
        System.out.println(n);
        n*=6;
        System.out.println(n);

        int sum;
        int m;
        while(n>9)
        {
            sum=0; m=n;
            while(m>0)
            {
                sum+=(m%10);
                m/=10;
            }
            System.out.println(sum);
            n=sum;
        }
        System.out.println("Willy-nilly, this semester I will learn "+Languages[n]);
    }

}
