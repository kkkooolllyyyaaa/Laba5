package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.InputChecker;
import Tsypk.collection.StudyGroupAsker;

public class RemoveByIdCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private StudyGroupAsker studyGroupAsker;

    public RemoveByIdCommand(CollectionManager collectionManager, StudyGroupAsker studyGroupAsker) {
        super("remove_by_id"," : удалить элемент коллекции по его id");
        this.collectionManager = collectionManager;
        this.studyGroupAsker=studyGroupAsker;
    }

    @Override
    public void execute(String[] args) {
        Long id;
        if(args.length>1 && args[1].length()>0 && InputChecker.checkLong(args[1].trim())){
            id=Long.parseLong(args[1].trim());
        }
        else{
            id= studyGroupAsker.askStudyGroupId();
        }
        if(id!=null && collectionManager.containsId(id))
            collectionManager.removeById(id);
        else{
            CollectionManager.print("Данный id не найден",true);
        }
    }
}
