package ru.avalon.java.actions;

import com.oracle.tools.packager.IOUtils;
import static java.lang.System.out;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Абстрактное представление о действии, как функциональном элементе приложения.
 * <p>
 * Действие является потоковым объектом, что позволяет исполнять несколько
 * действий параллельно и не блокировать основной поток исполнения.
 */
public interface Action extends Runnable, AutoCloseable {

    /**
     * Запускает потоковый объект на исполнение в отдельном потоке исполнения.
     */
    ExecutorService service = Executors.newWorkStealingPool();

    default void start() {
        /*
         * TODO №1 Реализуйте метод start интерфейса Action.
         */
        service.execute(this);
    }

    default void close() {
        try {
            service.shutdown();
        } catch (Exception e) {
            out.printf("An error has occured. Error : %n%s", e.getMessage());
            service.shutdownNow();
        }

    }
}
