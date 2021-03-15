package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.StudyGroupCreater;

public class RemoveGreaterCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final StudyGroupCreater studyGroupCreater;

    public RemoveGreaterCommand(CollectionManager collectionManager, StudyGroupCreater studyGroupCreater) {
        super("remove_greater", " : {element} удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.studyGroupCreater = studyGroupCreater;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.removeGreater(studyGroupCreater.askStudyGroup());
    }
}
