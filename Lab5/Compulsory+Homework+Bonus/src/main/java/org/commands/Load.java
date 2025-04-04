package org.commands;

import lombok.AllArgsConstructor;
import org.example.Repository;
import org.example.RepositoryService;
import org.exceptions.CommandException;
import org.exceptions.InvalidCatalogException;
import org.exceptions.InvalidRepositoryException;

import java.io.IOException;

@AllArgsConstructor
public class Load implements Command{
    private Repository myRepo;
    @Override
    public void execute(String[] args) throws CommandException {
        if(args.length<2)
        {
            throw new CommandException("Missing arguments: use load [path] [format]");

        }
        try {
            if(args[1].equals("json"))
            {Repository myDeserializedRepo=new RepositoryService().loadJson(args[0]);
            System.out.println(myDeserializedRepo);}
            else if(args[1].equals("binary"))
            { try {
                Repository myDeserializedRepo = new RepositoryService().loadBinary(args[0]);
                System.out.println(myDeserializedRepo);
            } catch (InvalidRepositoryException e) {
                System.out.println(e.getMessage());
            }
            }
        }
        catch(IOException e)
        {
            throw new CommandException(e.getMessage());
        }


    }
}
