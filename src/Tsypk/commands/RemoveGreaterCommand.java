package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.StudyGroup;
import Tsypk.collection.StudyGroupAsker;

public class RemoveGreaterCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private StudyGroupAsker studyGroupAsker;
    public RemoveGreaterCommand(CollectionManager collectionManager, StudyGroupAsker studyGroupAsker) {
        super("remove_greater", " : {element} удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.studyGroupAsker=studyGroupAsker;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.removeGreater(studyGroupAsker.askStudyGroup());
    }
}
