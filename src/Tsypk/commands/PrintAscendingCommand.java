package Tsypk.commands;

import Tsypk.collection.CollectionManager;

public class PrintAscendingCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public PrintAscendingCommand(CollectionManager collectionManager) {
        super("print_ascending"," : вывести элементы коллекции в порядке возрастания");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.printAscending();
    }
}
