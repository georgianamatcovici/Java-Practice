package org.commands;

import lombok.AllArgsConstructor;
import org.example.Repository;
import org.exceptions.CommandException;
import org.exceptions.ImageNotFoundException;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
public class Tags implements Command{
private Repository myRepo;
public static final List<String> IMAGE_TAGS = List.of("summer", "nature", "landscape", "vintage", "forest", "beach", "bridge", "urban", "historic");
    @Override
    public void execute(String[] args) throws CommandException, ImageNotFoundException {
        if(args.length == 0){
            throw new CommandException("Missing args: use tags [image name]");
        }
        if(args[0]==null)
            throw new ImageNotFoundException();
        int nTags=(int) (Math.random() * (IMAGE_TAGS.size()-1))+1;
        List<String> tags=new ArrayList<>();
        for(int i=0; i<nTags; i++){
            int tagNumber = (int) (Math.random() * (IMAGE_TAGS.size()));
            String tagName = IMAGE_TAGS.get(tagNumber);
            tags.add(tagName);

        }
        System.out.println(tags);
        myRepo.addImageTags(myRepo.findImageByName(args[0]), tags);
        myRepo.printImages();

    }
}
