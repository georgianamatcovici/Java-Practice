package org.example;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class RepositoryService {
    public void save(Repository repo, String path)
            throws IOException {
        try (var oos = new ObjectOutputStream(
                new FileOutputStream(path))) {
            oos.writeObject(repo);
        }
    }

    public Repository load(String path)
            throws InvalidRepositoryException
    {
        return new Repository();
    }
        //use ObjectInputStream
    
public void view(Image imageToView) {

    File imageFile = new File(imageToView.imageLocation().toString());
    if(imageFile.exists()) {
        Desktop myDesktop = Desktop.getDesktop();
        try {
            myDesktop.open(imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
  else System.out.println("Not found");
}



}
