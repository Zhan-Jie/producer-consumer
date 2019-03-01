package zhanj.producerconsumer;

import java.util.Random;
import static zhanj.producerconsumer.App.log;

public class Consumer implements Runnable{

    private final Buffer buffer;

    private Random r = new Random();

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer) {
                try {
                    while (buffer.isEmpty()) {
                        log("\t\t\tbuffer is empty. waiting...");
                        buffer.wait();
                    }
                    consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                buffer.notifyAll();
            }
        }
    }

    private void consume() throws InterruptedException {
        int period = r.nextInt(5);
        Thread.sleep(period*1000);
        int d = buffer.readInt();
        log("\t\t\tconsume " + d);
    }
}
