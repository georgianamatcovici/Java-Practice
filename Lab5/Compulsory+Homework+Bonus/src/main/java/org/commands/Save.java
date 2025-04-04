package org.commands;

import lombok.AllArgsConstructor;
import org.example.Repository;
import org.example.RepositoryService;
import org.exceptions.CommandException;

import javax.swing.*;
import java.io.IOException;
@AllArgsConstructor
public class Save implements Command {
  private Repository myRepo;
    @Override
    public void execute(String[] args) throws CommandException {
        if(args.length<2)
        {
            throw new CommandException("Missing arguments: use save [path] [format]");

        }
        try {
            if(args[1].equals("json")) {
                new RepositoryService().saveJson(myRepo, args[0]);
            }
            else if(args[1].equals("binary")) {
                new RepositoryService().saveBinary(myRepo, args[0]);
            }
        }
        catch(IOException e)
        {
            throw new CommandException(e.getMessage());
        }
    }
}
