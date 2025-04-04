package org.example;

import java.io.Serializable;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public record Image(String imageName, LocalDate addingDate, List<String> tags, String imageLocation) implements Serializable {
}
