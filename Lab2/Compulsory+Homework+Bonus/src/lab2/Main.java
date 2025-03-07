package lab2;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();

        Student myFirstStudent = new Student("S1", LocalDate.of(2004, 11, 1), 123L);
        Student mySecondStudent = new Student("S2", LocalDate.of(2005, 11, 1), 124L);
        Student myThirdStudent = new Student("S3", LocalDate.of(2003, 3, 6), 125L);
        Student myFourthStudent = new Student("S4", LocalDate.of(2002, 7, 15), 126L);
        Student myFifthStudent = new Student("S5", LocalDate.of(2001, 3, 1), 126L);

        System.out.println(myFirstStudent);

        Teacher myTeacher=new Teacher("T1", LocalDate.of(1985, 1, 1));
        Teacher mySecondTeacher=new Teacher("T2", LocalDate.of(1986, 1, 1));

        Project myFirstProject = new Project("P1", ProjectType.THEORETICAL, 1);
        Project mySecondProject = new Project("P2", ProjectType.PRACTICAL, 2);
        Project myThirdProject = new Project("P3", ProjectType.PRACTICAL, 3);
        Project myFourthProject = new Project("P4", ProjectType.THEORETICAL, 4);

        myTeacher.setProjects(new Project[]{myFirstProject, myThirdProject});
        mySecondTeacher.setProjects(new Project[]{myFirstProject, myFourthProject});

        myFirstStudent.addPrefferedProject(mySecondProject);
        myFirstStudent.addPrefferedProject(myFirstProject);
        mySecondStudent.addPrefferedProject(myFirstProject);
        mySecondStudent.addPrefferedProject(myThirdProject);
        myThirdStudent.addPrefferedProject(myThirdProject);
        myThirdStudent.addPrefferedProject(myFourthProject);
        myFourthStudent.addPrefferedProject(myFourthProject);
        myFourthStudent.addPrefferedProject(myFirstProject);

       // Student otherStudent = new Student("S5", LocalDate.of(2001, 3, 1), 127L);
        //otherStudent.addPrefferedProject(myThirdProject);
       // otherStudent.addPrefferedProject(myFourthProject);

        Problem p1=new Problem();
        p1.addStudent(myFirstStudent);
        p1.addStudent(mySecondStudent);
        p1.addStudent(myThirdStudent);
        p1.addStudent(myFourthStudent);
        p1.addStudent(myFifthStudent);
        p1.addTeacher(myTeacher);
        p1.addTeacher(mySecondTeacher);
       // p1.addStudent(otherStudent);

       Person[] persons= p1.getAllPersons();
       for(int i=0; persons[i]!=null; i++){
           System.out.println(persons[i]);
       }

       myFourthStudent.printPrefferedProjects();
        
     Solution s1=p1.getSolution();
     System.out.println(s1);
       p1.getSolutionHall();
        long endTime = System.nanoTime();
        long runningTime = endTime - startTime;
        System.out.println("Running time: " + runningTime + " ns");
        System.out.println("Used memory: " + totalMemory / (1024 * 1024) + " MB");

    }
}