package org.commands;

import lombok.AllArgsConstructor;
import org.example.Image;
import org.example.Repository;
import org.exceptions.CommandException;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class Add implements Command {
    Repository myRepo;
    public void execute(String args[]) throws CommandException {
if(args.length<2)
{
    throw new CommandException("Missing arguments: use add [image name] [path]");

}
System.out.println(args[0]+" "+args[1]);
myRepo.addImage(new Image(args[0], LocalDate.now(), List.of(), args[1]));
myRepo.printImages();
    }
}
