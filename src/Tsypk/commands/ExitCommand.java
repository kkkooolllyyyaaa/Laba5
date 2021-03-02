package Tsypk.commands;

import Tsypk.collection.CollectionManager;

public class ExitCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public ExitCommand(CollectionManager collectionManager) {
        super("exit"," : завершить программу (без сохранения в файл)");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.exit();
    }

}
