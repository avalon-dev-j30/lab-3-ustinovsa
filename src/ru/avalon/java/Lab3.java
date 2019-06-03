package ru.avalon.java;

import java.io.*;
import ru.avalon.java.actions.FileCopyAction;
import ru.avalon.java.actions.FileCreateAction;
import ru.avalon.java.actions.FileDeleteAction;
import ru.avalon.java.actions.FileMoveAction;
import ru.avalon.java.actions.FileScanAction;
import ru.avalon.java.console.ConsoleUI;

/**
 * Лабораторная работа №3
 * <p>
 * Курс: "Программирование на платформе Java. Разработка многоуровневых
 * приложений"
 * <p>
 * Тема: "Потоки исполнения (Threads) и многозадачность"
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class Lab3 extends ConsoleUI<Commands> {

    /**
     * Точка входа в приложение.
     *
     * @param args
     */
    private BufferedReader in
            = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        System.out.printf("Type help to see list of commands.%n>");
        new Lab3().run();
    }

    /**
     * Конструктор класса.
     * <p>
     * Инициализирует экземпляр базового типа с использоавнием перечисления
     * {@link Commands}.
     */
    Lab3() {
        super(Commands.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCommand(Commands command) throws IOException {
        String target;
        String to;
        char type;
        
        switch (command) {
            case help:
                System.out.printf("show - show all entries in directory from path"
                        + "%ncopy - copy target file or directory to target path"
                        + "%nmove - move target file or directory to target path"
                        + "%ndelete - delete target file or empty directory"
                        + "%ncreate - create file or directory at target path"
                        + "%nexit - exit programm"
                        + "%nEnter command:%n>");
                break;
            case scan:
                System.out.println("Enter path:");
                target = in.readLine();
                new FileScanAction(target, System.out).start();
                break;
            case copy:
                /*
                 * TODO №6 Обработайте команду copy
                 */
                System.out.print("from: ");
                target = in.readLine();
                System.out.print("to: ");
                to = in.readLine();
                new FileCopyAction(target, to).start();
                break;
            case move:
                /*
                 * TODO №7 Обработайте команду move
                 */
                System.out.print("from: ");
                target = in.readLine();
                System.out.print("to: ");
                to = in.readLine();
                new FileMoveAction(target, to).start();
                break;
            case delete:
                /*
                 *c TODO №7 Обработайте команду move
                 */
                System.out.print("Path to deleting file or directory: ");
                target = in.readLine();
                new FileDeleteAction(target).start();
                break;
            case create:
                /*
                 * TODO №7 Обработайте команду move
                 */
                System.out.print("Enter d or f to creating directory or file  : ");
                type = in.readLine().charAt(0);
                System.out.print("Enter name of creating object : ");
                target = in.readLine();
                new FileCreateAction(type, target).start();
                break;
            case exit:
                close();
                break;
            /*
                 * TODO №9 Обработайте необработанные команды
             */
        }
    }

}
