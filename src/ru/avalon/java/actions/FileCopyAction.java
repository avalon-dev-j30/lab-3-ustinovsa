package ru.avalon.java.actions;

import java.io.IOException;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Действие, которое копирует файлы в пределах дискового пространства.
 */
public class FileCopyAction implements Action {

    /**
     * {@inheritDoc}
     */
    private Path from;
    private Path to;

    public FileCopyAction(String from, String to) {
        this.from = Paths.get(from);
        this.to = Paths.get(to);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        /*
         * TODO №4 Реализуйте метод run класса FileMoveAction
         */
        copy();
    }

    private synchronized void copy() {
        if (!Files.exists(from)) {
            out.printf("%s does not exist.", from.getFileName().toString());
        } else if (!Files.exists(to)) {
            try {
                Files.createDirectory(to);
                Files.copy(from, to, REPLACE_EXISTING);
                out.printf("%s copied to %s&n>",
                        from.getFileName().toString(),
                        to.getFileName().toString());
            } catch (IOException ex) {
                out.printf("Target path does not exist and can't be created. Because of error: %n%s%n>",
                        ex.getMessage());
            } finally {
                close();
            }
        }
    }
}

