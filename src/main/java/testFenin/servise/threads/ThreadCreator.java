package testFenin.servise.threads;

import java.util.ArrayList;
import java.util.List;

public class ThreadCreator {

    public ThreadCreator() {
    }

    public static ThreadCreator get() {
        return new ThreadCreator();
    }

    public void start() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            threads.add(new Thread(new ParserThread((long) i)));
        }

        for (Thread thread : threads) {
            thread.setDaemon(true);
            thread.start();
        }

        for (Thread thread:threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
