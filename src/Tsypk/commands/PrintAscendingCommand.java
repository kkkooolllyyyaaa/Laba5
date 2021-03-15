package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.SGStringShower;
import Tsypk.collection.StudyGroup;

public class PrintAscendingCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public PrintAscendingCommand(CollectionManager collectionManager) {
        super("print_ascending", " : вывести элементы коллекции в порядке возрастания");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.sort();
        StringBuilder sb = new StringBuilder("Элементов в коллекции: " + collectionManager.getStudyGroups().size()).append("\n");
        for (StudyGroup sg : collectionManager.getStudyGroups()) {
            sb.append(SGStringShower.toStrView(sg));
        }
        System.out.println(sb.toString());
    }
}
