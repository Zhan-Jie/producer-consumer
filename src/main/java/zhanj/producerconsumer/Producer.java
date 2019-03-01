package zhanj.producerconsumer;

import java.util.Random;
import static zhanj.producerconsumer.App.log;

public class Producer implements Runnable{

    private final Buffer buffer;

    private Random r = new Random();

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer) {
                try {
                    while (buffer.isFull()) {
                        log("buffer is full. waiting...");
                        buffer.wait();
                    }
                    produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                buffer.notifyAll();
            }
        }
    }

    private void produce() throws InterruptedException {
        int period = r.nextInt(3);
        Thread.sleep(period*1000);
        int d = r.nextInt(10000);
        buffer.writeInt(d);
        log("produce " + d);
    }

}
