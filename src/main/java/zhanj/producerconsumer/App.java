package zhanj.producerconsumer;

public class App {

    public static void main(String[] args) {
        Buffer buffer = new Buffer(10);
        new Thread(new Consumer(buffer), "consumer-0").start();
        new Thread(new Consumer(buffer), "consumer-1").start();
        new Thread(new Producer(buffer), "producer-0").start();
        new Thread(new Producer(buffer), "producer-1").start();
    }

    public static void log(String message) {
        System.out.println(Thread.currentThread().getName() + ": " + message);
    }
}
