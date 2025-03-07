package lab2;

import java.time.LocalDate;
import java.util.Arrays;

public class Teacher extends Person {
private Project[] projects;

    /**
     * Constructor
     * @param name
     * @param birthDate
     */
    public Teacher(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
    public void setProjects(Project[] projects) {
        this.projects = projects;
        for(Project p: projects)
        {
            p.setTeacher(this);
        }
    }
    public Project[] getProjects() {
        return projects;
    }
    /**
     * This method helps us to use System.out.println on an object
     * @return the string conversion of the object
     */
    @Override
    public String toString() {
        return "Teacher [name=" + name + ", birthDate=" + birthDate + ", projects=" + Arrays.toString(projects) + "]";
    }

    /**
     * Two teachers are the same person if they have the same name
     * @param o another teacher
     * @return if two teachers are the same person
     */
    @Override
    public boolean equals(Object o) {
if(this.name.equals(((Teacher) o).name))
    return true;
return false;
    }

}
