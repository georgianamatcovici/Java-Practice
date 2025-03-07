package lab2;

import java.util.Arrays;

public class Solution {
    private Project[] projects;
    private static final int MAX_PROJECTS = 50;
    private int countProjects=0;
    public Solution() {
        projects = new Project[MAX_PROJECTS];
    }
    public Solution(Project[] projects) {
        this.projects = projects;
        countProjects= projects.length;
    }
    public void addProject(Project project) {
        int i;
        boolean ok=true;
        for (i = 0; i < countProjects; i++) {
            if (projects[i].equals(project)) {
                ok=false;
                break;
            }
        }
        if (ok) {
            projects[countProjects] = project;
            countProjects++;
        }
        else {
            System.out.println("Projects can't be added twice!");
        }
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i=0; i<countProjects; i++) {
            str.append(projects[i].toString());
            str.append("\n");
        }
        return str.toString();
    }

}
