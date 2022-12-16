package task_b.interfaces;

@FunctionalInterface
public interface IProcessor<T extends IMessage> {
    void process(IMessage message);
}