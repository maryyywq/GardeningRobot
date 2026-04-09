public interface ICommand {
    void execute(Robot robot);
    boolean canBeHandledBy(Robot robot);
}
