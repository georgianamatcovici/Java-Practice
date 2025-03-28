package org.example;

import lombok.Getter;
import lombok.ToString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Locale.filter;

@Getter
@ToString
public class Repository {
    private List<Image> repoImages = new ArrayList<>();
    private String repoName;
    public Repository() {
    }
    public void addImage(Image image) {
        repoImages.add(image);
    }
   public Image findImageByName(String nameToFind) {
       Optional<Image> myImage= repoImages.stream()
                .filter(imageItem->imageItem.imageName().compareTo(nameToFind)==0)
               .findAny();
       return myImage.orElseThrow(null);

   }
//    public void addAll(String folder){
//        try {
//            Files.walk(Paths.get(folder))
//            .filter(myFile -> {Files.isRegularFile(myFile) && myFile.endsWith(".png")})
//                    .
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
}
