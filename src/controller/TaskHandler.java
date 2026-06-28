package controller;

import core.ICommand;
import core.IMapSegmentVisitor;
import core.ITaskHandler;

public abstract class TaskHandler implements ITaskHandler {
    protected TaskHandler next;

    public void setNext(TaskHandler next) {
        this.next = next;
    }

    protected abstract boolean handle(ICommand command, IMapSegmentVisitor visitor);
    protected abstract boolean canHandle(ICommand command);
    protected abstract boolean doHandle(ICommand command);
}