package lab2;

import java.util.Arrays;

public class Problem {
    private Student[] students;
    private Teacher[] teachers;
    private int countStudents = 0;
    private int countTeachers = 0;
    private static final int MAX_STUD = 300;
    private static final int MAX_TEACH = 100;

    /**
     * Class constructor
     */
    public Problem() {
        students = new Student[MAX_STUD];
        teachers = new Teacher[MAX_TEACH];
    }

    ;

    /**
     * Class constructor with:
     * @param students
     * @param teachers
     */
    public Problem(Student[] students, Teacher[] teachers) {
        this.students = students;
        this.teachers = teachers;
        countStudents = students.length;
        countTeachers = teachers.length;
    }

    /**
     * Adds a student in the Problem class
     * Checks if the student was already added (if it can find a student with the same id)
     * @param student
     */
    public void addStudent(Student student) {
        int i;
        boolean ok = true;
        for (i = 0; i < countStudents; i++) {
            if (students[i].equals(student)) {
                ok = false;
                break;
            }
        }
        if (ok) {
            students[countStudents] = student;
            countStudents++;
        } else {
            System.out.println("Students can't be added twice!");
        }
    }

    /**
     *   * Adds a student in the Problem class
     *   Checks if the teacher was already added.
     * @param teacher
     */

    public void addTeacher(Teacher teacher) {
        int i;
        boolean ok = true;
        for (i = 0; i < countTeachers; i++) {
            if (teachers[i].equals(teacher)) {
                ok = false;
                break;
            }
        }
        if (ok) {
            teachers[countTeachers] = teacher;
            countTeachers++;
        } else {
            System.out.println("Teachers can't be added twice!");
        }
    }

    /**
     *
     * @return Returns an array of all the persons involved, students or teachers.
     */
    public Person[] getAllPersons() {
        Person[] persons = new Person[students.length + teachers.length];
        int k = 0;
        for (int i=0; i<countStudents; i++) {
            persons[k++] = students[i];
        }

        for (int j=0; j<countTeachers; j++) {
            persons[k++] = teachers[j];

        }
        return persons;
    }

    /**
     * Implements the solution using a simple greedy algorithm: for each student,
     * we assign to our current student the first project available
     * in his prefferd projects list
     * @return An object of class Solution, which contains a list of projects and every project has a variable Student which was established by this method
     */
    public Solution getSolution() {
        Solution solution = new Solution();
        for (int i=0; i<countStudents; i++) {
            int count = students[i].getCountProjects();
          //  System.out.println("Student "+students[i].getName()+" has "+count+" projects ");
            if(count!=0) {
                Project[] projects = students[i].getPrefferedProjects();
                boolean assigned = false;
                int j=0;
                while (!assigned && j<count) {
                        if (projects[j].getStudent() == null) {
                            //System.out.println("Student is null!");
                            assigned = true;
                            projects[j].setStudent(students[i]);
                            solution.addProject(projects[j]);
                            System.out.println("Added project "+projects[j].getName()+" to student "+students[i].getName());
                            break;

                    }
                        j++;
                }
            }


        }
        return solution;
    }

    /**
     * Implements a brute force algorithm to check if the problem has a solution (we can assign a project to each student)
     * based on Hall's Theorem
     * Creates a bipartite graph object, we have two subsets of vertices, S containing the students and T containing the projects
     */
    public void getSolutionHall() {
        Graph myGraph=new Graph(countStudents, 5*countStudents);
        for (int i=0; i<countStudents; i++) {
            int count = students[i].getCountProjects();
            //  System.out.println("Student "+students[i].getName()+" has "+count+" projects ");
            if(count!=0) {
                Project[] projects = students[i].getPrefferedProjects();
                    for (int j = 0; j < count; j++) {
                        myGraph.setEdge(i, projects[j].getId()-1);
                      //  System.out.println(students[i].getName()+ " "+ i+" "+projects[j].getId()+" "+(projects[j].getId()-1));

                    }
                    System.out.println();
                }



        }
        myGraph.printMatrix();
        myGraph.backTracking(0);
        if(myGraph.getHasSolution())
        {
            System.out.println("The problem has solution.");
        }
        else System.out.println("The problem has no solution.");
    }
}


