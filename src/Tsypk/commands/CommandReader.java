package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.StudyGroupCreater;
import Tsypk.utils.CSVFileWorker;
import Tsypk.utils.FileWorker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс, отвечающий за общение Client и CollectionManager
 * Является пунктом упрравления для всех команд
 */
public class CommandReader implements CommandReaderInterface{
    private final CollectionManager manager;
    private final FileWorker csvFileWorker;
    private final HashMap<String, AbstractCommand> commandMap;
    private static boolean isExit = false;

    public CommandReader() {
        manager = new CollectionManager();
        StudyGroupCreater studyGroupCreater = new StudyGroupCreater(new BufferedReader(new InputStreamReader(System.in)), manager, false);
        csvFileWorker = new CSVFileWorker(manager);
        commandMap = new HashMap<>();
        commandMap.put("add", new AddCommand(manager, studyGroupCreater));
        commandMap.put("clear", new ClearCommand(manager));
        commandMap.put("count_less_than_semester_semester_enum", new CountLessThanSemesterEnumCommand(manager, studyGroupCreater));
        commandMap.put("execute_script", new ExecuteScriptCommand(manager));
        commandMap.put("exit", new ExitCommand(manager));
        commandMap.put("history", new HistoryCommand());
        commandMap.put("info", new InfoCommand(manager));
        commandMap.put("print_ascending", new PrintAscendingCommand(manager));
        commandMap.put("remove_by_id", new RemoveByIdCommand(manager, studyGroupCreater));
        commandMap.put("remove_first", new RemoveFirstCommand(manager));
        commandMap.put("remove_greater", new RemoveGreaterCommand(manager, studyGroupCreater));
        commandMap.put("save", new SaveCommand(manager));
        commandMap.put("show", new ShowCommand(manager));
        commandMap.put("sum_of_students_count", new SumOfStudentsCountCommand(manager));
        commandMap.put("update", new UpdateCommand(manager, studyGroupCreater));
        commandMap.put("help", new HelpCommand(commandMap));
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
    public void println(String s) {
        System.out.println(s);
    }

    /**
     * Метод, обеспечивающий чтение команды в строковом формате
     *
     * @return !isExit
     */
    @Override
    public boolean readCommand() {
        Scanner commandReader = new Scanner(System.in);
        String userCommand = "";
        try {
            userCommand = commandReader.nextLine();
        } catch (NoSuchElementException e) {
            println("You can't input this\nThe work of App will be stopped");
            return false;
        }
        String[] updatedUserCommand = userCommand.trim().split(" ", 2);
        if (commandMap.containsKey(updatedUserCommand[0])) {
            commandMap.get(updatedUserCommand[0]).execute(updatedUserCommand);
            CommandReaderInterface.addToHistory(updatedUserCommand[0]);
        } else if (!updatedUserCommand[0].equals(""))
            println("Данной команды не существует. Наберите 'help' для справки.");
        return !isExit;
    }

    /**
     * Метод, который начинает работу приложения с прочтения csv файла
     */
    @Override
    public void start() {
        Scanner fileInput = new Scanner(System.in);
        println("Do you wanna start app work with csv file input? (yes/no)");
        String input = fileInput.nextLine();
        while (!input.equals("yes") && !input.equals("no")) {
            println("(yes/no)");
            input = fileInput.nextLine();
        }
        if (input.matches("yes")) {
            csvFileWorker.loadInput(manager.getStudyGroups());
        }
        println("The app is ready to work");
    }
}