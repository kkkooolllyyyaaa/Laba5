package Tsypk.utils;

import Tsypk.exceptions.FileInputException;
import Tsypk.collection.CollectionManager;
import Tsypk.collection.*;

import java.io.*;
import java.util.LinkedList;

/**
 * Класс, который обеспечивает считывание из csv файла
 * файл имеет вид a;b;c;d,...,z или a,b,c,d,...,z
 * Наследник CSVFileWorker
 */
public class CSVReader extends CSVFileWorker{
    private static BufferedReader bufferedReader;
    private static StudyGroupAsker studyGroupAsker;
    /**
     * Читает построчно файл csv и добавляет элементы в коллекцию по одному
     */
    public static void loadInput(LinkedList<StudyGroup> linkedList){
        inputFilePath();
        setSeparator(",");
        try {
            bufferedReader = new BufferedReader(new FileReader(getFilePath().trim()));
            String line=bufferedReader.readLine();
            while((line = bufferedReader.readLine())!=null){
                StudyGroup studyGroup = read((line.split(getSeparator())));
                if(studyGroup!=null)
                    linkedList.add(studyGroup);
                else
                    break;
            }
        } catch (IOException ioException) {
            CollectionManager.print("Input error! Fix the file",true);
        }
    }

    /**
     * Парсит из массива строк в элемент коллекции
     * @param values массив полей коллекции в строчном представлении
     * @return Эллемент коллекции
     */
    public static StudyGroup read(String[] values) {
        studyGroupAsker=new StudyGroupAsker(bufferedReader);
        try {
            for(int i=0;i<values.length;i++){
                if(values[i].isEmpty())
                    throw new FileInputException();
            }
            String name = values[0];
            Coordinates coordinates = new Coordinates(Integer.parseInt(values[1]), Long.parseLong(values[2]));
            int studentsCount = Integer.parseInt(values[3]);
            FormOfEducation formOfEducation = (FormOfEducation) studyGroupAsker.askStudyGroupEnum(values[4]);
            Semester semester = (Semester) studyGroupAsker.askStudyGroupEnum(values[5]);
            Person groupAdmin = new Person();
            groupAdmin.setName(values[6]);
            groupAdmin.setPassportID(values[7]);
            Location location = new Location(Long.parseLong(values[8]), Long.parseLong(values[9]), Long.parseLong(values[10]), values[11]);
            groupAdmin.setLocation(location);
            if(semester==null || formOfEducation==null) {
                CollectionManager.print("It is incorrect Enum format! Fix the file\nElement in not added",true);
                return null;
            }
            return new StudyGroup(name, coordinates, studentsCount, formOfEducation, semester, groupAdmin);
        } catch (NumberFormatException e) {
            CollectionManager.print("It is incorrect number format! Fix the file\nElement is not added", true);
        } catch (FileInputException e) {
            CollectionManager.print("Element adding to file is failed. Check the element fields!",true);
        }
        return null;
    }
}
