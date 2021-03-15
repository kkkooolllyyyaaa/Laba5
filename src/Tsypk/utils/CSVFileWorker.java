package Tsypk.utils;

import Tsypk.collection.*;
import Tsypk.exceptions.InvalidFieldException;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Основной класс для работы с csv файлом
 */
public class CSVFileWorker implements CSVFileWorkerInterface, FileWorker {
    private String filePath;
    private String separator;
    private BufferedReader bufferedReader;
    private final CollectionManager manager;

    public CSVFileWorker(CollectionManager collectionManager) {
        manager = collectionManager;
    }

    /**
     * Вспомогательный метод, реализующий ввод пути на считываемый csv файл.
     */
    @Override
    public void inputFilePath() {
        print("Enter the path to file:");
        Scanner scanFilePath = new Scanner(System.in);
        filePath = scanFilePath.nextLine().trim();
        File file = new File(filePath);
        while (!file.exists() || file.isDirectory()) {
            print("File path is incorrect, please, try again:");
            filePath = scanFilePath.nextLine();
            file = new File(filePath.trim());
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            print("File path is incorrect, please try again");
        }
        print("The path to the file is successfully entered");
    }

    /**
     * Парсит из массива строк в элемент коллекции
     *
     * @param values массив полей коллекции в строчном представлении
     * @return Эллемент коллекции
     */
    @Override
    public StudyGroup read(String[] values) {
        StudyGroupCreaterInterface studyGroupCreater = new StudyGroupCreater(bufferedReader, manager, false);
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i].trim();
            if (values[i].isEmpty()) {
                print("You has empty field in the file!");
                return null;
            }
        }
        try {
            studyGroupCreater.setName(values[0]);

            if (InputChecker.checkInt(values[1]))
                studyGroupCreater.setCoordinateX(Integer.parseInt(values[1]));
            else
                throw new InvalidFieldException("Coordinate X should be int");
            if (InputChecker.checkLong(values[2]))
                studyGroupCreater.setCoordinateY(Long.parseLong(values[2]));
            else
                throw new InvalidFieldException("Coordinate Y should be Long");
            if (InputChecker.checkInt(values[3]))
                studyGroupCreater.setStudentsCount(Integer.parseInt(values[3]));
            else
                throw new InvalidFieldException("Students count should be int and greater than 0");
            studyGroupCreater.setFormOfEducation((FormOfEducation) studyGroupCreater.checkStudyGroupEnum(values[4]));
            studyGroupCreater.setSemester((Semester) studyGroupCreater.checkStudyGroupEnum(values[5]));
            studyGroupCreater.setGAName(values[6]);
            studyGroupCreater.setGAPassportID(values[7]);
            if (InputChecker.checkLong(values[8]))
                studyGroupCreater.setGALocationX(Long.parseLong(values[8]));
            else
                throw new InvalidFieldException("Group Admin Location X should be long");
            if (InputChecker.checkLong(values[9]))
                studyGroupCreater.setGALocationY(Long.parseLong(values[9]));
            else
                throw new InvalidFieldException("Group Admin Location Y should be Long");
            if (InputChecker.checkLong(values[10]))
                studyGroupCreater.setGALocationZ(Long.parseLong(values[10]));
            else
                throw new InvalidFieldException("Group Admin Location Z should be Long");
            studyGroupCreater.setGALocationName(values[11]);
        } catch (InvalidFieldException e) {
            print(e.getMessage());
            return null;
        }
        return studyGroupCreater.getStudyGroup();
    }

    /**
     * Читает построчно файл csv и добавляет элементы в коллекцию по одному
     */
    @Override
    public void loadInput(LinkedList<StudyGroup> linkedList) {
        inputFilePath();
        setSeparator(",");
        try {
            bufferedReader = new BufferedReader(new FileReader(getFilePath().trim()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lines = line.split(getSeparator());
                if (lines.length != 12 && lines.length != 14) {
                    print("You have the wrong number of fields, element will not added to the collection");
                    continue;
                }
                StudyGroup studyGroup = read(lines);
                if (studyGroup != null) {
                    print("Element " + studyGroup.getName() + " is successfully added to the collection");
                    linkedList.add(studyGroup);
                } else {
                    print("StudyGroup instance is not added to the collection");
                }
            }
        } catch (IOException e) {
            print("Incorrect file path\nFix the file");
        }
    }

    /**
     * Метод, обеспечивающий за строкове представление элементов коллекции в csv файле
     *
     * @param linkedList Коллекция
     * @return Строковое представление
     */
    @Override
    public String[] parseToString(LinkedList<StudyGroup> linkedList) {
        String[] toSave = new String[linkedList.size()];
        int cnt = 0;
        for (StudyGroup sg : linkedList) {
            toSave[cnt] = sg.getName();
            toSave[cnt] += ',' + String.valueOf(sg.getCoordinates().getX());
            toSave[cnt] += ',' + String.valueOf(sg.getCoordinates().getY());
            toSave[cnt] += ',' + String.valueOf(sg.getStudentsCount());
            toSave[cnt] += ',' + sg.getFormOfEducation().getUrl();
            toSave[cnt] += ',' + sg.getSemesterEnum().getUrl();
            toSave[cnt] += ',' + sg.getGroupAdmin().getName();
            toSave[cnt] += ',' + sg.getGroupAdmin().getPassportID();
            toSave[cnt] += ',' + String.valueOf(sg.getGroupAdmin().getLocation().getX());
            toSave[cnt] += ',' + String.valueOf(sg.getGroupAdmin().getLocation().getY());
            toSave[cnt] += ',' + String.valueOf(sg.getGroupAdmin().getLocation().getZ());
            toSave[cnt] += ',' + sg.getGroupAdmin().getLocation().getName();
            toSave[cnt] += ',' + String.valueOf(sg.getId());
            toSave[cnt] += ',' + sg.getCreationDate().toString();
            cnt++;
        }
        return toSave;
    }

    /**
     * Метод, обеспечивающий запись в файл, использую BufferedWriter
     *
     * @param str
     */
    @Override
    public void write(String[] str) {
        try {
            String saveFilePath = System.getenv("testTTT");
            if (saveFilePath == null) {
                print("The path in the environment variable is incorrect");
                inputFilePath();
                saveFilePath = getFilePath();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(saveFilePath));
            for (int i = 0; i < str.length; i++) {
                bufferedWriter.write(str[i]);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Получить значения разделителя, который используется для CSV файла.
     *
     * @return separator разделитель.
     */
    @Override
    public String getSeparator() {
        return separator;
    }

    /**
     * Установить значение разделителя, которое будет использоваться для CSV файла.
     *
     * @param separator разделитель.
     */
    @Override
    public void setSeparator(String separator) {
        this.separator = separator;
    }

    /**
     * @return filePath
     */
    @Override
    public String getFilePath() {
        return filePath;
    }

    /**
     * Обеспечивает общение FileWorker и клиента
     *
     * @param message
     */
    public void print(String message) {
        System.out.println(message);
    }
}
