package org.commands;

import org.exceptions.CommandException;
import org.exceptions.ImageNotFoundException;

public interface Command {
    void execute(String args[]) throws CommandException, ImageNotFoundException;
}
