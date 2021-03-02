package Tsypk.commands;

import Tsypk.collection.CollectionManager;

public class SumOfStudentsCountCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public SumOfStudentsCountCommand(CollectionManager collectionManager) {
        super("sum_of_students_count"," : вывести сумму значений поля studentsCount для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.sumOfStudentsCount();
    }
}
