package org.example;

import lombok.Getter;
import lombok.ToString;
import org.exceptions.ImageNotFoundException;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Locale.filter;

@Getter
@ToString
public class Repository implements Serializable {
    private List<Image> repoImages = new ArrayList<>();
    private String repoName;
    public Repository(String repoName) {
        this.repoName = repoName;
    }
    public Repository() {
    }
    public void addImage(Image image) {
        repoImages.add(image);
    }
    public void removeImage(Image image) {
        repoImages.remove(image);
    }

    public void updateImage(Image image, String newName) {
        Image newImage=new Image(newName, image.addingDate(), image.tags(), image.imageLocation());
        repoImages.set(repoImages.indexOf(image), newImage);

    }
    public void addImageTags(Image image,  List<String> tags) {
        Image newImage=new Image(image.imageName(), image.addingDate(), tags, image.imageLocation());
        repoImages.set(repoImages.indexOf(image), newImage);

    }
   public Image findImageByName(String nameToFind) throws ImageNotFoundException {
       Optional<Image> myImage= repoImages.stream()
                .filter(imageItem->imageItem.imageName().compareTo(nameToFind)==0)
               .findAny();
       return myImage.orElseThrow(ImageNotFoundException::new);

   }

   public void printImages() {
        System.out.println(repoName+": ");
        repoImages.stream()
                .forEach(System.out::println);
   }
    public void addAll(String folder){
        Path folderPath= Paths.get(folder);
        try(Stream<Path> folderStream= Files.walk(folderPath))
        {folderStream.filter(f->f.getFileName().toString().endsWith(".jpg")|| f.getFileName().toString().endsWith(".png"))
                .forEach(f->this.addImage(new Image(f.getFileName().toString(), LocalDate.now(), new ArrayList<>(), f.toAbsolutePath().toString())));
               // .forEach(f->System.out.println(f.toAbsolutePath().toString()));
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }


    }
}
