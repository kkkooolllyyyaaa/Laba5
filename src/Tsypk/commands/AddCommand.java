package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.StudyGroupCreaterInterface;

public class AddCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final StudyGroupCreaterInterface studyGroupCreater;

    public AddCommand(CollectionManager collectionManager, StudyGroupCreaterInterface studyGroupCreater) {
        super("add", " {element} : добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.studyGroupCreater = studyGroupCreater;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.addElement(studyGroupCreater.askStudyGroup());
    }
}
