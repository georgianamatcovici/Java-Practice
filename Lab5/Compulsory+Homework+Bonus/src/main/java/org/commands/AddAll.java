package org.commands;

import lombok.AllArgsConstructor;
import org.example.Repository;
import org.exceptions.CommandException;

@AllArgsConstructor
public class AddAll implements Command{
    Repository myRepo;
    @Override
    public void execute(String[] args) throws CommandException {
        if(args.length == 0)
        {
            throw new CommandException("Missing args: use addAll [path]");
        }
        myRepo.addAll(args[0]);
        myRepo.printImages();
    }
}
