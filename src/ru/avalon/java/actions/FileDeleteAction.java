/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.actions;

import java.io.IOException;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Home
 */
public class FileDeleteAction implements Action {

    private Path target;

    public FileDeleteAction(String target) {
        this.target = Paths.get(target);
    }

    private void delete() {
        if (!Files.exists(target)) {
            out.printf("%s does not exist.", target.getFileName().toString());
        } else {
            try {
                Files.delete(target);
                out.printf("%s has been deleted",
                        target.getFileName().toString());

            } catch (IOException ex) {
                ex.getMessage();
            } finally {
                close();
            }
        }
    }

    @Override
    public void close() {
        try {
            service.shutdown();
        } catch (Exception e) {
            out.printf("An error has occured. Error : %n%s", e.getMessage());
        }
    }

    @Override
    public void run() {
        delete();
    }
}
