package org.commands;

import lombok.AllArgsConstructor;
import org.exceptions.ImageNotFoundException;
import org.example.Repository;
import org.exceptions.CommandException;

@AllArgsConstructor
public class Remove implements Command{
    private Repository myRepo;
    @Override
    public void execute(String[] args) throws CommandException {
        if(args.length<1)
        {
            throw new CommandException("Missing arguments: use remove [image name]");

        }
        try {
            myRepo.removeImage(myRepo.findImageByName(args[0]));
        }
        catch (ImageNotFoundException e) {
            throw new CommandException(e.getMessage());
        }
        myRepo.printImages();
    }
}
