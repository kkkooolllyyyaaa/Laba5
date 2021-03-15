package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.Semester;
import Tsypk.collection.StudyGroupCreater;
import Tsypk.exceptions.InvalidFieldException;

public class CountLessThanSemesterEnumCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final StudyGroupCreater studyGroupCreater;

    public CountLessThanSemesterEnumCommand(CollectionManager collectionManager, StudyGroupCreater studyGroupCreater) {
        super("count_less_than_semester_enum", " semesterEnum : вывести количество элементов, значение поля semesterEnum которых меньше заданного");
        this.collectionManager = collectionManager;
        this.studyGroupCreater = studyGroupCreater;
    }

    @Override
    public void execute(String[] args) {
        try {
            collectionManager.countLessThanSemesterEnum((Semester) studyGroupCreater.checkStudyGroupEnum(args[1].trim()));
        } catch (InvalidFieldException e) {
            System.out.println("There is no enum named " + args[1].trim());
        }
    }
}
