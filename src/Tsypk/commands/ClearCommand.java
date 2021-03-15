package Tsypk.commands;

import Tsypk.collection.CollectionManager;

public class ClearCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear", " : очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.clear();
    }
}