package testFenin;


import testFenin.servise.DAOImp.PostService;
import testFenin.servise.threads.ThreadCreator;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ThreadCreator.get().start();
    }
}
