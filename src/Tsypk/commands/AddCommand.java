package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.StudyGroup;
import Tsypk.collection.StudyGroupAsker;

public class AddCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final StudyGroupAsker studyGroupAsker;

    public AddCommand(CollectionManager collectionManager, StudyGroupAsker studyGroupAsker) {
        super("add"," {element} : добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.studyGroupAsker=studyGroupAsker;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.addElement(studyGroupAsker.askStudyGroup());
    }
}
