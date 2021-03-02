package Tsypk.utils;

import Tsypk.collection.CollectionManager;
import java.io.File;
import java.util.Scanner;

/**
 * Основной класс для работы с csv файлом
 */
public class CSVFileWorker {
    private static String filePath;
    private static String separator;
    /**
     * Вспомогательный метод, реализующий ввод пути на считываемый csv файл.
     */
    public static void inputFilePath() {
        CollectionManager.print("Enter the path to file:",true);
        Scanner scanFilePath = new Scanner(System.in);
        CSVFileWorker.filePath = scanFilePath.nextLine();
        File file = new File(filePath.trim());
        while(!file.exists() || file.isDirectory()){
            CollectionManager.print("File path is incorrect, please, try again:",true);
            CSVFileWorker.filePath=scanFilePath.nextLine();
            file=new File(filePath.trim());
        }
        CollectionManager.print("The path to the file is successfully entered",true);
    }

    /**
     * Получить значения разделителя, который используется для CSV файла.
     * @return separator разделитель.
     */
    public static String getSeparator() {
        return separator;
    }

    /**
     * Установить значение разделителя, которое будет использоваться для CSV файла.
     * @param separator разделитель.
     */
    public static void setSeparator(String separator) {
        CSVFileWorker.separator = separator;
    }

    /**
     * @return filePath
     */
    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String s){
        filePath=s;
    }
}
