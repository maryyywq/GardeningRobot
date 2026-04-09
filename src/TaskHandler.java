public abstract class TaskHandler implements ITaskHandler {
    protected TaskHandler next;

    public void setNext(TaskHandler next) {
        this.next = next;
    }

    @Override
    public boolean handle(ICommand command) {
        if (canHandle(command)) {
            return doHandle(command);
        } else if (next != null) {
            return next.handle(command);
        } else {
            return false;
        }
    }

    protected abstract boolean canHandle(ICommand command);
    protected abstract boolean doHandle(ICommand command);
}