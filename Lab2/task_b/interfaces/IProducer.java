package task_b.interfaces;

import task_b.Producer;

public interface IProducer<T extends IMessage> {
    void produce(T product) throws InterruptedException;
}