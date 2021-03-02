package Tsypk.utils;

import Tsypk.exceptions.ScriptException;
import Tsypk.collection.CollectionManager;
import Tsypk.collection.*;
import Tsypk.commands.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Класс, обеспечивающий считывание из текствого фалйа, для выполнения скрипта (последовательности команд)
 */
public class ScriptReader {
    private static String filePath;
    private static BufferedReader bufferedReader;
    private static final List<String> scriptPaths=new ArrayList<>();
    private StudyGroupAsker studyGroupAsker;
    private HashMap<String, AbstractCommand> commandMap;
    private CollectionManager manager;
    private String userCommand;
    private String[] updatedUC;

    public ScriptReader(CollectionManager collectionManager){
        manager=collectionManager;
        commandMap = new HashMap();
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath.trim()));
        } catch (FileNotFoundException e) {
            System.err.println("File "+filePath.trim()+" is not found!");
        }
        studyGroupAsker = new StudyGroupAsker(bufferedReader);
        commandMap = new HashMap<>();
        commandMap.put("add", new AddCommand(manager, studyGroupAsker));
        commandMap.put("clear", new ClearCommand(manager));
        commandMap.put("count_less_than_semester_semester_enum", new CountLessThanSemesterEnumCommand(manager,studyGroupAsker));
        commandMap.put("execute_script", new ExecuteScriptFileCommand(manager));
        commandMap.put("exit", new ExitCommand(manager));
        commandMap.put("help", new HelpCommand(manager));
        commandMap.put("history", new HistoryCommand(manager));
        commandMap.put("info", new InfoCommand(manager));
        commandMap.put("print_ascending", new PrintAscendingCommand(manager));
        commandMap.put("remove_by_id", new RemoveByIdCommand(manager, studyGroupAsker));
        commandMap.put("remove_first", new RemoveFirstCommand(manager));
        commandMap.put("remove_greater", new RemoveGreaterCommand(manager, studyGroupAsker));
        commandMap.put("save", new SaveCommand(manager));
        commandMap.put("show", new ShowCommand(manager));
        commandMap.put("sum_of_students_count", new SumOfStudentsCountCommand(manager));
        commandMap.put("update", new UpdateCommand(manager, studyGroupAsker));
    }

    /**
     * Основной метод, проходящийся по командам скрипта
     */
    public boolean readCommand() {
        try {
            userCommand = bufferedReader.readLine();
            while (userCommand != null) {
                updatedUC = userCommand.trim().split(" ", 2);
                if (commandMap.containsKey(updatedUC[0])) {
                    commandMap.get(updatedUC[0]).execute(updatedUC);
                    CommandReader.addToHistory(updatedUC[0]);
                } else {
                    println("Данной команды не существует. Наберите 'help' для справки.");
                }
                userCommand = bufferedReader.readLine();
            }
        } catch (IOException e){
            println("Input Error! Fix the file");
        }
        return false;
    }

    /**
     * Метод, отвечающий за ввод пути файла
     */
    public static void inputFilePath() throws ScriptException {
        CollectionManager.print("Enter the path to file:", true);
        Scanner scanFilePath = new Scanner(System.in);
        filePath = scanFilePath.nextLine();
        File file = new File(filePath);
        while (!file.exists() || file.isDirectory()) {
            CollectionManager.print("File path is incorrect, please, try again:", true);
            filePath = scanFilePath.nextLine();
            file = new File(filePath);
        }
        scriptPaths.add(filePath);
        CollectionManager.print("The path to the file is successfully entered", true);
    }

    /**
     * Метод отвечающий за чтение пути файла из файла
     * @throws ScriptException
     */
    public static void inputFilePathScript() throws ScriptException{
        try {
            filePath= bufferedReader.readLine();
            File file = new File(filePath);
            if(file.exists() && !file.isDirectory()){
                if(!checkPath(filePath))
                    scriptPaths.add(filePath);
                else
                    throw new ScriptException();
            }
        } catch (IOException ioException) {
            println("Input Error!");
        }
    }
    /**
     * Метод, проверяющий, был ли использован раннее скрипт
     * @param path
     * @return
     */
    public static boolean checkPath(String path) {
        if(scriptPaths.contains(path))
            return true;
        else{
            scriptPaths.add(path);
            return false;
        }
    }

    /**
     * Очищает историю использованных скриптов
     */
    public static void clearPaths(){
        scriptPaths.clear();
    }

    /**
     * Обеспечивает общение клиента и ScriptReader
     * @param s
     */
    public static void println(String s){
        System.out.println(s);
    }


}
