package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.Semester;
import Tsypk.collection.StudyGroupAsker;
import Tsypk.commands.Command;

public class CountLessThanSemesterEnumCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private StudyGroupAsker studyGroupAsker;

    public CountLessThanSemesterEnumCommand(CollectionManager collectionManager, StudyGroupAsker studyGroupAsker) {
        super("count_less_than_semester_enum"," semesterEnum : вывести количество элементов, значение поля semesterEnum которых меньше заданного");
        this.collectionManager = collectionManager;
        this.studyGroupAsker=studyGroupAsker;
    }

//    @Override
//    public void execute() {
//        collectionManager.countLessThanSemesterEnum(semester);
//    }

    @Override
    public void execute(String[] args) {
        collectionManager.countLessThanSemesterEnum((Semester)studyGroupAsker.askStudyGroupEnum(args[1]));
    }
}
