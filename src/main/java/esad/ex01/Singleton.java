package esad.ex01;

/**
 * @author ashan on 2020-08-17
 */
public class Singleton {
    private Singleton() {
        System.out.println("Singleton instance created");
    }

    public static Singleton getInstance() {
        return InnerSingleton.singleton;
    }

    private static final class InnerSingleton {
        private static Singleton singleton = new Singleton();
    }
}

class Sample implements Runnable {

    public static void main(String[] args) {
        new Thread(new Sample()).start();
        for (int i = 0; i < 10; i++) {
            System.out.println("Main thread instance : " + (i + 1));
            Singleton.getInstance();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Partial thread instance : " + (i + 1));
            Singleton.getInstance();
        }
    }
}