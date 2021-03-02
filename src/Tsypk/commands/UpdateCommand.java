package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.InputChecker;
import Tsypk.collection.StudyGroupAsker;

public class UpdateCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private StudyGroupAsker studyGroupAsker;

    public UpdateCommand(CollectionManager collectionManager, StudyGroupAsker studyGroupAsker) {
        super("update"," id {element} обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.studyGroupAsker = studyGroupAsker;
    }

    @Override
    public void execute(String[] args) {
        Long id;
        if(args.length>1 && args[1].length()>0 && InputChecker.checkLong(args[1].trim())){
            id=Long.parseLong(args[1].trim());
        }
        else{
            id=studyGroupAsker.askStudyGroupId();
        }
        if(id!=null && collectionManager.containsId(id))
            collectionManager.update(id,studyGroupAsker.askStudyGroup());
        else{
            CollectionManager.print("Данный id не найден",true);
        }
    }
}
