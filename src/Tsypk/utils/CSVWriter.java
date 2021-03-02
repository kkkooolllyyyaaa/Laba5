package Tsypk.utils;

import Tsypk.collection.StudyGroup;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Класс, который обеспечивает записывание в csv файл
 * файл имеет вид a;b;c;d,...,z или a,b,c,d,...,z
 * Наследник CSVFileWorker
 */
public class CSVWriter extends CSVFileWorker {
    /**
     * Метод, обеспечивающий запись в файл, использую BufferedWriter
     * @param str
     */
    public static void saveToFile(String[] str){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getFilePath()));
            for(int i=0;i<str.length;i++){
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
     * Метод, обеспечивающий за строкове представление элементов коллекции в csv файле
     * @param linkedList Коллекция
     * @return Строковое представление
     */
    public static String[] parseToString(LinkedList<StudyGroup> linkedList){
        String[] toSave = new String[linkedList.size()];
        int cnt=0;
        for(StudyGroup sg : linkedList){
            toSave[cnt]= sg.getName();
            toSave[cnt]+=','+String.valueOf(sg.getCoordinates().getX());
            toSave[cnt]+=','+String.valueOf(sg.getCoordinates().getY());
            toSave[cnt]+=','+String.valueOf(sg.getStudentsCount());
            toSave[cnt]+=','+sg.getFormOfEducation().getUrl();
            toSave[cnt]+=','+sg.getSemesterEnum().getUrl();
            toSave[cnt]+=','+sg.getGroupAdmin().getName();
            toSave[cnt]+=','+sg.getGroupAdmin().getPassportID();
            toSave[cnt]+=','+String.valueOf(sg.getGroupAdmin().getLocation().getX());
            toSave[cnt]+=','+String.valueOf(sg.getGroupAdmin().getLocation().getY());
            toSave[cnt]+=','+String.valueOf(sg.getGroupAdmin().getLocation().getZ());
            toSave[cnt]+=','+sg.getGroupAdmin().getLocation().getName();
            toSave[cnt]+=','+String.valueOf(sg.getId());
            toSave[cnt]+=','+sg.getCreationDate().toString();
            cnt++;
        }
        return toSave;
    }
}
