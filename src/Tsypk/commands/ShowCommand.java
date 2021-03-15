package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.SGStringShower;
import Tsypk.collection.StudyGroup;

public class ShowCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super("show", " : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        StringBuilder sb = new StringBuilder("Элементов в коллекции: " + collectionManager.getStudyGroups().size()).append("\n");
        for (StudyGroup sg : collectionManager.getStudyGroups()) {
            sb.append(SGStringShower.toStrView(sg));
        }
        System.out.println(sb.toString());
    }
}
