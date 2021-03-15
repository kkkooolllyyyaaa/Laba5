package Tsypk.commands;

import Tsypk.utils.LimitedQueue;

public class HistoryCommand extends AbstractCommand {

    public HistoryCommand() {
        super("history", " : вывести последние 5 команд (без их аргументов)");
    }

    @Override
    public void execute(String[] args) {
        LimitedQueue<String> history = CommandReaderInterface.getHistory();
        System.out.println("The list of last 5 commands:");
        for (Object hstr : history) {
            System.out.println(((String) hstr).trim());
        }
    }
}
