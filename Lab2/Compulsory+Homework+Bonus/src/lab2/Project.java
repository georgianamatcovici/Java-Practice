package lab2;

public class Project {
    private int id;
    private String name;
    private ProjectType type;
    private Student student;
    private Teacher teacher;

    /**
     * Class Constructor
     * @param name
     * @param type
     * @param id
     */
    public Project(String name, ProjectType type, int id) {
        this.name = name;
        this.type = type;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectType getType() {
        return type;
    }

    public void setType(ProjectType type) {
        this.type = type;
    }
    public void addProject(Project project) {

    }
    /**
     * This method helps us to use System.out.println on an object
     * @return the string conversion of the object
     */
    @Override
    public String toString() {
        if(student!=null)
            return "Project [name=" + name + ", type=" + type + ","+"id="+id+", student="+student.getName()+"]";
        else
            return "Project [name=" + name + ", type=" + type + ","+"id="+id+", no student enrolled]";
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    /**
     * Two projects are the same project if they have the same name
     * @param o another project
     * @return if two projects are the same one
     */
    @Override
    public boolean equals(Object o) {
        if(this.name.equals(((Project)o).getName())) {
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }
}
