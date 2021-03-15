package Tsypk.collection;

import Tsypk.utils.ScriptReader;

import java.util.*;

/**
 * Класс который хранит коллекцию и совершает действия с ней
 */
public class CollectionManager {
    private final LinkedList<StudyGroup> studyGroups;
    private final Date creationDate;
    private final HashSet<Long> idList;

    public CollectionManager() {
        studyGroups = new LinkedList<>();
        idList = new HashSet<>();
        creationDate = new Date();
    }

    /**
     * Метод отвечающий за полную очистку коллекции
     */
    public void clear() {
        studyGroups.clear();
        print("The collection successfully cleared", true);
    }

    /**
     * Метод, отвечающий за подсчет количества всех студентов
     */
    public int sumOfStudentsCount() {
        int sum = 0;
        for (StudyGroup it : studyGroups) {
            sum += it.getStudentsCount();
        }
        print("Sum of students count is: "+ sum, true);
        return sum;
    }

    /**
     * Метод, отвевающий за удаление первого элемента коллекции
     */
    public void removeFirstElement() {
        if (!studyGroups.isEmpty()) {
            studyGroups.removeFirst();
            print("The first element is successfully removed from collection", true);
        } else
            print("There is no elements in the collection.\nElement is not removed from collection", true);
    }

    /**
     * Метод, отвечающий за удаление элементов, превышающий заданный (по id)
     */
    public void removeGreater(StudyGroup studyGroup) {
        int count = 0;
        boolean flag = true;
        while (flag) {
            for (StudyGroup st : studyGroups) {
                if (st.getId() > studyGroup.getId()) {
                    studyGroups.remove(st);
                    count++;
                    flag = true;
                    break;
                }
                flag = false;
            }
        }
        print(count + " elements removed", true);
    }

    /**
     * Метод, отвечающий за удаление элемента коллекции по id
     *
     * @param id
     */
    public void removeById(long id) {
        if (studyGroups.removeIf(x -> x.getId() == id))
            print("Element is successfully removed from collection", true);
        else
            print("Element is not removed from collection", true);
    }

    /**
     * Считает, сколько сколько групп учится в семестрах, ниже заданного
     *
     * @param semester
     */
    public void countLessThanSemesterEnum(Semester semester) {
        int count = 0;
        for (StudyGroup it : studyGroups) {
            if (it.getSemesterEnum().getValue() < semester.getValue()) {
                count++;
            }
        }
        print("Count of elements is: " + count, true);
    }

    /**
     * Метод, отвечающий за добавление элемента в коллекцию
     *
     * @param studyGroup
     * @return был ли добавлен элемент
     */
    public boolean addElement(StudyGroup studyGroup) {
        if (studyGroups.contains(studyGroup)) {
            print("Element is already exist in collection!", true);
            return false;
        } else {
            studyGroups.add(studyGroup);
            print("Element is added successfully!", true);
            return true;
        }
    }

    /**
     * @param id         айди группы, по которому произойдет обноваление
     * @param studyGroup обновленный экземпляр
     * @return Произошло ли обновление
     */
    public boolean update(long id, StudyGroup studyGroup) {
        for (StudyGroup sg : studyGroups) {
            if (sg.getId() == id) {
                removeById(id);
                studyGroup.setId(id);
                studyGroups.add(studyGroup);
                print("Element is updated!", true);
                return true;
            }
        }
        print("Input id is not found!", true);
        return false;
    }

    /**
     * Метод выводит кратку информацию о классе
     */
    public void info() {
        String info = "Время инциализации коллекции: " + getInitializationTime() + "\n" +
                "Количество элементов в коллекции: " + studyGroups.size() + "\n" +
                "Тип коллекции: " + studyGroups.getClass().getSimpleName();
        print(info, true);
    }

    /**
     * Метод, обеспечивающий сортировку коллекции
     */
    public void sort() {
        Collections.sort(studyGroups);
    }

    /**
     * Метод возвращающий дату создания обьекта.
     *
     * @return дата создания обькта.
     */
    public Date getInitializationTime() {
        return creationDate;
    }

    /**
     * Проверяет, есть ли элемент с данным id
     *
     * @param id
     * @return true - если элемент с данным id существует
     */
    public boolean containsId(long id) {
        for (StudyGroup st : studyGroups) {
            if (st.getId() == id)
                return true;
        }
        return false;
    }

    /**
     * @return idList
     */
    public HashSet<Long> getIdList() {
        return idList;
    }

    /**
     * @return studyGroups
     */
    public LinkedList<StudyGroup> getStudyGroups() {
        return studyGroups;
    }

    /**
     * Получить Comparator сравнения по id
     *
     * @return
     */
    public Comparator<StudyGroup> getComparatorById() {
        return (StudyGroup a, StudyGroup b) -> (int) (a.getId() - b.getId());
    }

    /**
     * Метод, обеспечивающий вывод строки
     *
     * @param str      выведится введенная строка
     * @param printMod булевая переменная, отвечаающая за наличие переноса строки
     */
    public void print(String str, boolean printMod) {
        if (!printMod)
            System.out.print(str);
        else
            System.out.println(str);
    }
}