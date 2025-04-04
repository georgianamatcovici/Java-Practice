package org.commands;

import org.example.Repository;
import org.exceptions.CommandException;
import org.exceptions.CommandNotFoundException;
import org.exceptions.ImageNotFoundException;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Shell {
    private Map<String, Command> findCommand= new HashMap<>();
    private Repository myRepo;
    public Shell(Repository mrRepo) {
        this.myRepo = mrRepo;
        findCommand.put("add", new Add(myRepo));
        findCommand.put("remove", new Remove(myRepo));
        findCommand.put("update", new Update(myRepo));
        findCommand.put("save", new Save(myRepo));
        findCommand.put("load", new Load(myRepo));
        findCommand.put("report", new Report(myRepo));
        findCommand.put("addAll", new AddAll(myRepo));
        findCommand.put("tags", new Tags(myRepo));
    }
    public void runShell() throws CommandNotFoundException, CommandException, ImageNotFoundException {
        System.out.println("Enter command: ");
        boolean runningShell=true;
        while (runningShell) {
            Scanner myScanner = new Scanner(System.in);
            String commandInput = myScanner.nextLine();
            if(commandInput.equals("exit")) {runningShell=false;}
            else if(commandInput.equals("report")) { Command commandToExecute = new Report(myRepo);
                String[] args=new String[]{"no args"};
                commandToExecute.execute(args);}
            else {

                String[] commandParts = commandInput.split(" ", 2);
                String commandName = commandParts[0];
                String[] commandArgs = commandParts[1].split("\\s+");
                Command commandToExecute = findCommand.get(commandName);
                if (commandToExecute == null) throw new CommandNotFoundException();
                commandToExecute.execute(commandArgs);
            }

        }


    }
}
