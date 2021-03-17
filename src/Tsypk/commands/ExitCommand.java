package Tsypk.commands;

public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", " : завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute(String[] args) {

    }
}
