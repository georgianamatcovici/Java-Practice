package org.commands;

import lombok.AllArgsConstructor;
import org.exceptions.ImageNotFoundException;
import org.example.Repository;
import org.exceptions.CommandException;

@AllArgsConstructor
public class Update implements Command{
   Repository myRepo;
    @Override
    public void execute(String[] args) throws CommandException {
        if(args.length<2)
        {
            throw new CommandException("Missing arguments: use update [image old_name] [image new_name]");

        }
        try {
            myRepo.updateImage(myRepo.findImageByName(args[0]), args[1]);
        } catch (ImageNotFoundException e) {
            System.out.println(e.getMessage());
        }
        myRepo.printImages();
    }
}
