package org.example;

import org.exceptions.CommandException;
import org.exceptions.CommandNotFoundException;
import org.commands.Shell;
import org.exceptions.ImageNotFoundException;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class Main {
    Repository myRepo;
    public static void main(String[] args) {
        Main app=new Main();
       app.testRepo();
        app.printImages();
       app.executeCommands();
    }
    private void testRepo() {
        myRepo = new Repository("Repo1"); //Model
        myRepo.addImage(new Image("Sunset", LocalDate.of(2025, 3, 22), List.of("sunset", "nature", "flowers"),"D:\\AdvancedProgrammingLabs\\MyRepo\\photo.jpg"));
        myRepo.addImage(new Image("Image", LocalDate.now(), List.of("nature", "forest"), "D:\\AdvancedProgrammingLabs\\MyRepo\\photo2.jpg"));
        System.out.println(myRepo);
         var myService = new RepositoryService(); //Logic
        try {
            myService.view(myRepo.findImageByName("Train"));
        }
        catch (ImageNotFoundException e) {
            System.out.println("Image not found.");
        }

    }
    private void executeCommands()
    {
        Shell myShell=new Shell(myRepo);
        try {
            myShell.runShell();
        }
        catch (CommandNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch(CommandException e)
        {
            System.out.println(e.getMessage());
        } catch (ImageNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    private void printImages() {
        myRepo.printImages();
    }
}

