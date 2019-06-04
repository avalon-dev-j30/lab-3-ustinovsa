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
public class FileCreateAction implements Action {

    private Path target;
    private char type;

    public FileCreateAction(char type, String target) {
        this.type = type;
        this.target = Paths.get(target);

    }

    private synchronized void create() {
        if (Files.exists(target)) {
            out.printf("%s cannot be created because of same object exist.", target.getFileName().toString());
        } else {
            try {
                if (type != 'd') {
                    Files.createFile(target);
                    out.printf("%s has been created",
                            target.getFileName().toString());
                } else {
                    Files.createDirectories(target);
                    out.printf("%s has been created%n>",
                            target.getFileName().toString());
                }
            } catch (IOException ex) {
                ex.getMessage();
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
        create();
    }
}
