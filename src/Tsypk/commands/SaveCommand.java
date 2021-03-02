package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.utils.CSVWriter;

public class SaveCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        super("save"," : сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        CSVWriter.inputFilePath();
        collectionManager.save();
    }
}
