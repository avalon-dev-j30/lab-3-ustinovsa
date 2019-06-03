package ru.avalon.java.actions;

import java.io.File;
import java.io.IOException;
import static java.lang.System.out;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Действие, которое перемещает файлы в пределах дискового пространства.
 */
public class FileMoveAction implements Action {

    private Path movingObject;
    private Path targetPath;

    public FileMoveAction(String from, String to) {
        this.movingObject = Paths.get(from);
        this.targetPath = Paths.get(to);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        /*
         * TODO №4 Реализуйте метод run класса FileMoveAction
         */
        move();
    }

    private synchronized void move() {
        if (!Files.exists(movingObject)) {
            out.printf("%s does not exist.", movingObject.getFileName().toString());
        } else if (!Files.exists(targetPath)) {
            try {
                Files.createDirectory(targetPath);
            } catch (IOException ex) {
                out.printf("Target path does not exist and can't be created. Because of error: %n%s%n",
                        ex.getMessage());
            }
        } else {
            try {
                Files.move(movingObject, targetPath, StandardCopyOption.REPLACE_EXISTING);
                //.resolve(movingObject.getFileName())
                out.printf("%s moved to %s",
                        movingObject.getFileName().toString(),
                        targetPath.getFileName().toString());
            } catch (IOException ex) {
                Logger.getLogger(FileMoveAction.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                close();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        /*
         * TODO №5 Реализуйте метод close класса FileMoveAction
         */
        try {
            service.shutdown();
        } catch (Exception e) {
            out.printf("An error has occured. Error : %n%s", e.getMessage());
            service.shutdownNow();
        }
    }

}
