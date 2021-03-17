package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.StudyGroupCreater;
import Tsypk.collection.StudyGroupCreaterInterface;
import Tsypk.utils.InputChecker;

public class UpdateCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final StudyGroupCreaterInterface studyGroupCreater;

    public UpdateCommand(CollectionManager collectionManager, StudyGroupCreaterInterface studyGroupCreater) {
        super("update", " id {element} обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.studyGroupCreater = studyGroupCreater;
    }

    @Override
    public void execute(String[] args) {
        Long id;
        if (args.length > 1 && args[1].length() > 0 && InputChecker.checkLong(args[1].trim())) {
            id = Long.parseLong(args[1].trim());
        } else {
            id = studyGroupCreater.askStudyGroupId();
        }
        if (id != null && collectionManager.containsId(id))
            collectionManager.update(id, studyGroupCreater.askStudyGroup());
        else {
            System.out.println("Данный id не найден");
        }
    }
}
