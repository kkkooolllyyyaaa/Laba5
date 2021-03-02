package Tsypk.commands;

import Tsypk.collection.CollectionManager;

public class HelpCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public HelpCommand(CollectionManager collectionManager) {
        super("help"," : вывести справку по доступным командам");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.help();
    }
}
