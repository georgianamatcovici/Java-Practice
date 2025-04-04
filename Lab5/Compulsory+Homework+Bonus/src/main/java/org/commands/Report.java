package org.commands;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.example.Repository;
import org.exceptions.CommandException;


import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Report implements Command{
    private Repository myRepo;
    @Override
    public void execute(String[] args) throws CommandException {
        Configuration myConfig = new Configuration(Configuration.VERSION_2_3_31);
        try {
            System.out.println("Here "+myRepo);
            myConfig.setDirectoryForTemplateLoading(new File("."));
            myConfig.setDefaultEncoding("UTF-8");

            Template myTemplate = myConfig.getTemplate("Template.ftl");
            Map<String, Object> repoData = new HashMap<>();
            repoData.put("repository", myRepo.getRepoName());
            repoData.put("images", myRepo.getRepoImages());
            File reportFile = new File("Repository.html");
            try (Writer writer = new FileWriter(reportFile)) {
                myTemplate.process(repoData, writer);
            } catch (TemplateException e) {
                throw new RuntimeException(e);
            }

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(reportFile);
            }

        }
        catch(IOException e)
        {
            throw new CommandException(e.getMessage());
        }


    }
}
