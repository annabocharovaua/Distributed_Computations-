package task_b.interfaces;

public interface IConsumer<T extends IMessage>  {
    T consume() throws InterruptedException;
}