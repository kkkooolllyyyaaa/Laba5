package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.StudyGroupCreater;

public class AddCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final StudyGroupCreater studyGroupCreater;

    public AddCommand(CollectionManager collectionManager, StudyGroupCreater studyGroupCreater) {
        super("add", " {element} : добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.studyGroupCreater = studyGroupCreater;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.addElement(studyGroupCreater.askStudyGroup());
    }
}
