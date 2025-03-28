package org.example;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main app=new Main();
        app.testRepo();
        //app.testLoadView();
    }
    private void testRepo() {
        var myRepo = new Repository(); //Model
        myRepo.addImage(new Image("Sunset", LocalDate.of(2025, 3, 22), List.of("sunset", "nature", "flowers"), Paths.get("C:\\Users\\user2\\Desktop\\OneDrive\\Documente\\photo.jpg")));
        System.out.println(myRepo);
         var myService = new RepositoryService(); //Logic
//        var repo = service.load("c:/repo.txt"); //or .json, .xml, .ser, ...
        try {
            myService.view(myRepo.findImageByName("Sunset"));
        }
        catch (Exception e) {
        }
//        repo.add(...);
//        service.save(repo);
    }
}

