package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.utils.LimitedQueue;

public class HistoryCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private LimitedQueue limitedQueue;

    public HistoryCommand(CollectionManager collectionManager) {
        super("history"," : вывести последние 5 команд (без их аргументов)");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        setLimitedQueue(CommandReader.getHistory());
        collectionManager.history(limitedQueue);
    }

    public void setLimitedQueue(LimitedQueue limitedQueue) {
        this.limitedQueue = limitedQueue;
    }
}
