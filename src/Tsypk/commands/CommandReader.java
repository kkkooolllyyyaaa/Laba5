package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.*;
import Tsypk.utils.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс, отвечающий за общение Client и CollectionManager
 * Является пунктом упрравления для всех команд
 */
public class CommandReader {
    protected HashMap<String, AbstractCommand> commandMap;
    protected String userCommand = "";
    protected String[] updatedUC;
    protected static CollectionManager manager;
    private StudyGroupAsker studyGroupAsker;
    protected static LimitedQueue<String> history = new LimitedQueue<>(5);
    private static boolean isExit = false;

    public CommandReader() {
        studyGroupAsker = new StudyGroupAsker(new BufferedReader(new InputStreamReader(System.in)));
        manager = new CollectionManager();
        commandMap = new HashMap<>();
        commandMap.put("add", new AddCommand(manager, studyGroupAsker));
        commandMap.put("clear", new ClearCommand(manager));
        commandMap.put("count_less_than_semester_semester_enum", new CountLessThanSemesterEnumCommand(manager, studyGroupAsker));
        commandMap.put("execute_script", new ExecuteScriptCommand(manager));
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
     * Метод, обеспечивающий чтение команды в строковом формате
     *
     * @return !isExit
     */
    public boolean readCommand() {
        Scanner commandReader = new Scanner(System.in);
        try {
            userCommand = commandReader.nextLine();
        } catch (NoSuchElementException e) {
            println("You can't input this");
            return false;
        }
        updatedUC = userCommand.trim().split(" ", 2);
        if (commandMap.containsKey(updatedUC[0])) {
            commandMap.get(updatedUC[0]).execute(updatedUC);
            history.add(updatedUC[0]);
        } else if (!updatedUC[0].equals(""))
            println("Данной команды не существует. Наберите 'help' для справки.");
        return !isExit;
    }

    /**
     * Метод, который начинает работу приложения с прочтения csv файла
     */
    public void start() {
        Scanner hasFile = new Scanner(System.in);
        println("Do you wanna start app work with csv file input? (yes/no)");
        String input = hasFile.next();
        while (!input.equals("yes") && !input.equals("no")) {
            println("(yes/no)");
            input = hasFile.next();
        }
        if (input.matches("yes")) {
            CSVReader.loadInput(CollectionManager.getStudyGroups());
        }
        println("The app is ready to work");
    }

    /**
     * Метод, устанавливающий флаг выхода
     */
    public static void quit() {
        isExit = true;
    }

    /**
     * Метод обеспечивающий вывод сообщений CommandReader
     *
     * @param s
     */
    public static void println(String s) {
        System.out.println(s);
    }

    /**
     * @return history
     */
    public static LimitedQueue<String> getHistory() {
        return history;
    }

    /**
     * Добавить команду к истории выполненных команд
     * Нужен для добавления выполненных команд от скрипта
     *
     * @param str
     */
    public static void addToHistory(String str) {
        history.add(str);
    }

    /**
     * @return isExit
     */
    public static boolean isIsExit() {
        return isExit;
    }
}