package Tsypk.collection;

import Tsypk.commands.CommandReader;
import Tsypk.utils.LimitedQueue;
import Tsypk.utils.CSVWriter;
import Tsypk.utils.ScriptReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Класс который хранит коллекцию и совершает действия с ней
 */
public class CollectionManager {
    private static LinkedList<StudyGroup> studyGroups= new LinkedList<>();
    private static HashSet<Long> idList=new HashSet<>();
    private final Date creationDate;
    public CollectionManager(){
        studyGroups = new LinkedList<>();
        idList = new HashSet<>();
        creationDate = new Date();
    }

    /**
     * Генератор id для StudyGroup, подбирается рандомно
     * @return рандомно сгенерированный id
     */
    public static long generateId(){
        long id=(long)(Math.random()*666666);
        while(idList.contains(id)){
            id=(long)(Math.random()*666666);
        }
        idList.add(id);
        return id;
    }

    /**
     * Метод, обеспечивающий вывод строки
     * @param str выведится введенная строка
     * @param printMod булевая переменная, отвечаающая за наличие переноса строки
     */
    public static void print(String str,boolean printMod){
        if(!printMod)
            System.out.print(str);
        else
            System.out.println(str);
    }

    /**
     * Вспомогательный метод сравнивающий 2 строки, без учета регистра
     * @param str1
     * @param str2
     * @return true - эквивалентны, false - не эквивалентны
     */
    public static boolean isEquivalent(String str1, String str2){
        return str1.equalsIgnoreCase(str2);
    }

    /**
     * Метод, выводящий справку по всем командам
     */
    public void help(){
        String filePath="/Users/tsypk/IdeaProjects/Laba5/src/Tsypk/files/CommandList.txt";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            while((line=bufferedReader.readLine())!=null){
                print(line,true);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            print("File is not find",true);
            return;
        } catch (IOException ioException){
            print("File can't be readed",true);
        }
    }

    /**
     * Метод отвечающий за полную очистку коллекции
     */
    public void clear(){
        studyGroups.clear();
        CollectionManager.print("The collection successfully cleared",true);
    }

    /**
     * Метод, отвечающий за подсчет количества всех студентов
     */
    public void sumOfStudentsCount(){
        int sum=0;
        for(StudyGroup it : studyGroups){
            sum+=it.getStudentsCount();
        }
        CollectionManager.print(Integer.toString(sum),true);
    }

    /**
     * Метод, отвевающий за удаление первого элемента коллекции
     */
    public void removeFirstElement() {
        if (!studyGroups.isEmpty()) {
            studyGroups.removeFirst();
            CollectionManager.print("The first element is successfully removed from collection", true);
        } else
            CollectionManager.print("There is no elements in the collection.\nElement is not removed from collection", true);
    }

    /**
     * Метод, отвечающий за удаление элементов, превышающий заданный (по id)
     */
    public void removeGreater(StudyGroup studyGroup) {
        int count=0;
        for(StudyGroup st : studyGroups){
            if(st.getId()>studyGroup.getId()){
                studyGroups.remove(st);
                count++;
            }
        }
        print(count+" elements removed",true);
    }

    /**
     * Метод, отвечающий за остановку интерактивного режима
     * @return
     */
    public void exit() {
        CommandReader.quit();
    }

    /**
     * Метод, отвечающий за удаление элемента коллекции по id
     * @param id
     */
    public void removeById(long id){
        if(studyGroups.removeIf(x->x.getId()==id))
            CollectionManager.print("Element is successfully removed from collection",true);
        else
            CollectionManager.print("Element is not removed from collection",true);
    }

    /**
     * Считает, сколько сколько групп учится в семестрах, ниже заданного
     * @param semester
     */
    public void countLessThanSemesterEnum(Semester semester){
        int count=0;
        for(StudyGroup it : studyGroups){
            if(it.getSemesterEnum().getValue()<semester.getValue()){
                count++;
            }
        }
        CollectionManager.print("Count of elements is: "+count,true);
    }

    /**
     * Метод, отвечающий за добавление элемента в коллекцию
     * @param studyGroup
     * @return
     */
    public boolean addElement(StudyGroup studyGroup){
        if(studyGroups.contains(studyGroup)){
            CollectionManager.print("Element is already exist in collection!",true);
            return false;
        }
        else {
            studyGroups.add(studyGroup);
            CollectionManager.print("Element is added successfully!",true);
            return true;
        }
    }

    /**
     *
     * @param id айди группы, по которому произойдет обноваление
     * @param studyGroup
     * @return Произошло ли обновление
     */
    public boolean update(long id, StudyGroup studyGroup){
        for(StudyGroup sg : studyGroups) {
            if (sg.getId() == id) {
                removeById(id);
                studyGroup.setId(id);
                studyGroups.add(studyGroup);
                CollectionManager.print("Element is updated!",true);
                return true;
            }
        }
        CollectionManager.print("Input id is not found!",true);
        return false;
    }

    /**
     * Метод сохраняет текущую коллекцию в csv файл
     */
    public void save(){
        CSVWriter.saveToFile(CSVWriter.parseToString(studyGroups));
        CollectionManager.print("The collection is saved successfully",true);
    }

    /**
     * Метод, обеспечиывающий вывод истории последних 5-ти команд
     * @param history
     */
    public void history(LimitedQueue history){
        CollectionManager.print("The list of last 5 commands:",true);
        for(Object hstr : history){
            CollectionManager.print(((String)hstr).trim(),true);
        }
    }

    /**
     * Метод выводит кратку информацию о классе
     */
    public void info(){
        StringBuilder sb = new StringBuilder();
        sb.append("Время инциализации коллекции: ").append(getInitializationTime()).append("\n");
        sb.append("Количество элементов в коллекции: ").append(studyGroups.size()).append("\n");
        sb.append("Тип коллекции: ").append(studyGroups.getClass().getSimpleName());
        CollectionManager.print(sb.toString(),true);
    }

    /**
     * Метод выводит все элементы коллекции в строчном представлении
     */
    public void show() {
        StringBuilder sb = new StringBuilder("Элементов в коллекции: " + studyGroups.size()).append("\n");
        for (StudyGroup sg : studyGroups) {
            sb.append(SGStringShower.toStrView(sg));
        }
        CollectionManager.print(sb.toString(), false);
    }

    /**
     * Метод, обеспечивающий сортировку коллекции
     */
    public static void sort() {
        Collections.sort(studyGroups);
    }

    /**
     * Метод, обеспечивающий выполнения файлового скрипта
     */
    public void executeScript(ScriptReader scriptReader) {
        scriptReader.readCommand();
        ScriptReader.clearPaths();
    }

    /**
     * Метод, выводящий коллекцию в порядке возрастания
     */
    public void printAscending(){
        sort();
        show();
    }

    /**
     * Метод возвращает ссылку на коллекцию
     * @return studyGroups
     */
    public static LinkedList<StudyGroup> getStudyGroups() {
        return studyGroups;
    }

    /**
     * Метод возвращающий дату создания обьекта.
     * @return дата создания обькта.
     */
    public Date getInitializationTime(){
        return creationDate;
    }

    /**
     * Проверяет, есть ли элемент с данным id
     * @param id
     * @return true - если элемент с данным id существует
     */
    public boolean containsId(long id){
        for(StudyGroup st : studyGroups){
            if(st.getId()==id)
                return true;
        }
        return false;
    }
}