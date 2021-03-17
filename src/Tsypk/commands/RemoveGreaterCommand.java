package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.StudyGroupCreater;
import Tsypk.collection.StudyGroupCreaterInterface;

public class RemoveGreaterCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final StudyGroupCreaterInterface studyGroupCreater;

    public RemoveGreaterCommand(CollectionManager collectionManager, StudyGroupCreaterInterface studyGroupCreater) {
        super("remove_greater", " : {element} удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.studyGroupCreater = studyGroupCreater;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.removeGreater(studyGroupCreater.askStudyGroup());
    }
}
