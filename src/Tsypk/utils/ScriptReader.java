package Tsypk.utils;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.StudyGroupCreater;
import Tsypk.commands.*;
import Tsypk.exceptions.ScriptException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Класс, обеспечивающий считывание из текствого фалйа, для выполнения скрипта (последовательности команд)
 */
public class ScriptReader {
    private static final List<String> scriptPaths = new ArrayList<>();
    private static String filePath;
    private static BufferedReader bufferedReader;
    private HashMap<String, AbstractCommand> commandMap;

    public ScriptReader(CollectionManager collectionManager) {
        commandMap = new HashMap();
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath.trim()));
        } catch (FileNotFoundException e) {
            System.err.println("File " + filePath.trim() + " is not found!");
        }
        StudyGroupCreater studyGroupCreater = new StudyGroupCreater(bufferedReader, collectionManager, true);
        commandMap = new HashMap<>();
        commandMap.put("add", new AddCommand(collectionManager, studyGroupCreater));
        commandMap.put("clear", new ClearCommand(collectionManager));
        commandMap.put("count_less_than_semester_semester_enum", new CountLessThanSemesterEnumCommand(collectionManager, studyGroupCreater));
        commandMap.put("execute_script", new ExecuteScriptFileCommand(collectionManager));
        commandMap.put("exit", new ExitCommand());
        commandMap.put("history", new HistoryCommand());
        commandMap.put("info", new InfoCommand(collectionManager));
        commandMap.put("print_ascending", new PrintAscendingCommand(collectionManager));
        commandMap.put("remove_by_id", new RemoveByIdCommand(collectionManager, studyGroupCreater));
        commandMap.put("remove_first", new RemoveFirstCommand(collectionManager));
        commandMap.put("remove_greater", new RemoveGreaterCommand(collectionManager, studyGroupCreater));
        commandMap.put("save", new SaveCommand(collectionManager));
        commandMap.put("show", new ShowCommand(collectionManager));
        commandMap.put("sum_of_students_count", new SumOfStudentsCountCommand(collectionManager));
        commandMap.put("update", new UpdateCommand(collectionManager, studyGroupCreater));
        commandMap.put("help", new HelpCommand(commandMap));
    }

    /**
     * Метод, отвечающий за ввод пути файла
     */
    public static void inputFilePath() throws ScriptException {
        print("Enter the path to file:");
        Scanner scanFilePath = new Scanner(System.in);
        filePath = scanFilePath.nextLine().trim();
        File file = new File(filePath);
        while (!file.exists() || file.isDirectory()) {
            print("File path is incorrect, please, try again:");
            filePath = scanFilePath.nextLine();
            file = new File(filePath);
        }
        scriptPaths.add(filePath);
        print("The path to the file is successfully entered");
    }

    /**
     * Метод отвечающий за чтение пути файла из файла
     *
     * @throws ScriptException
     */
    public static void inputFilePathScript() throws ScriptException {
        try {
            filePath = bufferedReader.readLine();
            File file = new File(filePath);
            if (file.exists() && !file.isDirectory()) {
                if (!checkPath(filePath))
                    scriptPaths.add(filePath);
                else
                    throw new ScriptException();
            }
        } catch (IOException ioException) {
            print("Input Error!");
        }
    }

    /**
     * Метод, проверяющий, был ли использован раннее скрипт
     *
     * @param path
     * @return
     */
    public static boolean checkPath(String path) {
        if (scriptPaths.contains(path))
            return true;
        else {
            scriptPaths.add(path);
            return false;
        }
    }

    /**
     * Очищает историю использованных скриптов
     */
    public static void clearPaths() {
        scriptPaths.clear();
    }

    /**
     * Обеспечивает общение клиента и ScriptReader
     *
     * @param s
     */
    public static void print(String s) {
        System.out.print(s);
    }

    /**
     * Основной метод, проходящийся по командам скрипта
     */
    public boolean readCommand() {
        try {
            String userCommand = bufferedReader.readLine();
            while (userCommand != null) {
                String[] updatedUC = userCommand.trim().split(" ", 2);
                if (commandMap.containsKey(updatedUC[0])) {
                    commandMap.get(updatedUC[0]).execute(updatedUC);
                    CommandReaderInterface.addToHistory(updatedUC[0]);
                } else {
                    print("Данной команды не существует. Наберите 'help' для справки.");
                }
                userCommand = bufferedReader.readLine();
            }
        } catch (IOException e) {
            print("Input Error! Fix the file");
        }
        return false;
    }
}
