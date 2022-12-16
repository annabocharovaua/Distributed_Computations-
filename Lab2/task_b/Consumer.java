package task_b;

import task_b.interfaces.IConsumer;
import task_b.interfaces.IMessage;
import task_b.interfaces.IProcessor;

import java.util.concurrent.BlockingQueue;

public class Consumer<T extends IMessage> extends Thread implements IConsumer<T> {
    private final BlockingQueue<T> queue;
    private final IProcessor<T> processor;

    public Consumer(BlockingQueue<T> queue, IProcessor<T> processor) {
        this.queue = queue;
        this.processor = processor;

    }

    @Override
    public T consume() throws InterruptedException {
        T consumedProduct = queue.take();
        System.out.println("Consumer received " + consumedProduct.toString());
        return consumedProduct;
    }

    @Override
    public void run() {
        while(true) {
            try {
                T consumedProduct = consume();
                processor.process(consumedProduct);

                if(consumedProduct.isPoisonPill()) {
                    break;
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer interrupted");
            }
        }
    }
}