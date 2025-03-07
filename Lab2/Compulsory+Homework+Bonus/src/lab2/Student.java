package lab2;

import java.time.LocalDate;
import java.util.Objects;

public class Student extends Person {
    private Long regNumber;
    private Project assignedProject;
    private static final int MAX_PROJ=5;
    private int countProjects=0;
    private Project[] prefferedProjects;

    public int getCountProjects() {
        return countProjects;
    }



    public Project[] getPrefferedProjects() {
        return prefferedProjects;
    }

    /**
     * Class Constructor
     * @param name
     * @param birthDate
     * @param regNumber
     */
    public Student(String name, LocalDate birthDate, Long regNumber) {
      super(name, birthDate);
        this.regNumber = regNumber;
        prefferedProjects = new Project[MAX_PROJ];
    }
    public Student(String name)
    {  super(name, null);
        this.regNumber = null;
        prefferedProjects = new Project[MAX_PROJ];
    }
    public Student()
    {
        this(null, null, null);
        prefferedProjects = new Project[MAX_PROJ];
    }

    public Long getRegNumber() {
        return regNumber;
    }


    public void setRegNumber(Long regNumber) {
        this.regNumber = regNumber;
    }

    public Project getAssignedProject() {
        return assignedProject;
    }

    public void setAssignedProject(Project assignedProject) {
        this.assignedProject = assignedProject;
    }
    public void addPrefferedProject(Project prefferedProject) {
        this.prefferedProjects[countProjects] = prefferedProject;
        countProjects++;
    }
public void printPrefferedProjects() {
        for (int i=0; i<countProjects; i++) {
            System.out.println(prefferedProjects[i].getName());
        }
        System.out.println();
}

    /**
     * This method helps us to use System.out.println on an object
     * @return the string conversion of the object
     */
    @Override
    public String toString() {
//        String string="";
//        if(countProjects>0) {
//            for (Project prefferedProject : prefferedProjects)
//                string += prefferedProject.toString() + " ";
//        }
//        else string="No preffered project";

        return "Student [name=" + name + ", birthDate=" + birthDate + ", regNumber="+regNumber+","+" assignedProject="+assignedProject+"], "+countProjects+" preffered projects] ";
    }
    /**
     * Two students are the same person if they have the same id
     * @param o another student
     * @return if two students are the same person
     */
    @Override
    public boolean equals(Object o) {
        Student student = (Student) o;
        if(this.regNumber == ((Student) o).regNumber)
            return true;
        return false;

    }
}
