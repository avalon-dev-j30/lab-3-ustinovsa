/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.actions;

import static java.lang.System.out;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import static java.nio.file.Files.isDirectory;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Home
 */
public class FileScanAction implements Action {

    private final Path path;
    private BufferedOutputStream bos;

    public FileScanAction(String path, OutputStream out) {
        this.path = Paths.get(path);
        this.bos = new java.io.BufferedOutputStream(out);
    }

    @Override
    public void run() {
        fileScan();
    }

    public void fileScan() {
        // Проверяем существует ли указанный путь
        if (!Files.exists(path)) {
            System.out.printf("%s does not exist.", path.getFileName());
        }

        // Если путь существует, выводим его и ниже список вхождений 
        out.println(path.toString());

        try ( DirectoryStream<Path> ds = Files.newDirectoryStream(path)) {
            synchronized (path) {
                for (Path p : ds) {
                    byte[] buffer = p.getFileName().toString().getBytes();
                    bos.write(buffer, 0, buffer.length);
                    if (isDirectory(p)) {
                        out.printf("%n(dir) %s", p.getFileName());
                    } else {
                        out.printf("%n(file) %s", p.getFileName());
                    }
                }
                out.printf("%n>");
            }
        } catch (IOException ex) {
            out.printf("An error has occured. Error : %n%s", ex.getMessage());
            close();
        } finally {
            close();
        }
        
    }

}
