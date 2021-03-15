package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.StudyGroupCreater;
import Tsypk.utils.InputChecker;

public class RemoveByIdCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final StudyGroupCreater studyGroupCreater;

    public RemoveByIdCommand(CollectionManager collectionManager, StudyGroupCreater studyGroupCreater) {
        super("remove_by_id", " : удалить элемент коллекции по его id");
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
            collectionManager.removeById(id);
        else {
            System.out.println("Данный id не найден");
        }
    }
}
