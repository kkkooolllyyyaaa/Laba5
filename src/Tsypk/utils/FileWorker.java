package Tsypk.utils;

import Tsypk.collection.StudyGroup;

import java.util.LinkedList;

public interface FileWorker {
    String getFilePath();

    void inputFilePath();

    void write(String[] str);

    StudyGroup read(String[] values);

    void loadInput(LinkedList<StudyGroup> linkedList);

    String[] parseToString(LinkedList<StudyGroup> linkedList);
}
