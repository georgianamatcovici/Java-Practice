package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.exceptions.InvalidCatalogException;
import org.exceptions.InvalidRepositoryException;

import java.awt.*;
import java.io.*;

public class RepositoryService {
        public void saveJson(Repository repo, String path)
                throws IOException {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.writeValue(
                    new File(path),
                    repo);
        }

        public void saveBinary(Repository repo, String path) throws IOException {
            try (var oos = new ObjectOutputStream(
                    new FileOutputStream(path))) {
                if (!(repo instanceof Serializable)) {
                    throw new IOException("Repository not serializable.");
                }
                oos.writeObject(repo);
            }
        }

        public Repository loadJson(String path) throws IOException {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            Repository repo = objectMapper.readValue(
                    new File(path),
                    Repository.class);
            return repo;
        }

    public Repository loadBinary(String path)
            throws InvalidRepositoryException, FileNotFoundException {
        Repository myLoadedRepo=null;
        try(FileInputStream fis = new FileInputStream(path)) {
            ObjectInputStream input=new ObjectInputStream(fis);
            myLoadedRepo=(Repository) input.readObject();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        if (myLoadedRepo == null) {
            throw new InvalidRepositoryException("Couldn't Deserialize.");
        }
        return myLoadedRepo;
    }
    
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
