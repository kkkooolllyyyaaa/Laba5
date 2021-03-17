package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.utils.CSVFileWorkerInterface;
import Tsypk.utils.FileWorker;

import java.io.File;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс, отвечающий за общение Client и CollectionManager
 * Является пунктом упрравления для всех команд
 */
public class CommandReader implements CommandReaderInterface {
    private final CollectionManager manager;
    private final FileWorker csvFileWorkerInterface;
    private final HashMap<String, AbstractCommand> commandMap;

    public CommandReader(CollectionManager collectionManager, FileWorker fileWorker) {
        manager = collectionManager;
        csvFileWorkerInterface = fileWorker;
        commandMap = new HashMap<>();
    }

    /**
     * Метод обеспечивающий вывод сообщений CommandReader
     *
     * @param s
     */
    public final void println(String s) {
        System.out.println(s);
    }

    /**
     * Метод, обеспечивающий чтение команды в строковом формате
     *
     */
    @Override
    public void readCommand() {
        Scanner commandReader = new Scanner(System.in);
        String userCommand = "";
        try {
            userCommand = commandReader.nextLine();
        } catch (NoSuchElementException e) {
            println("You can't input this\nThe work of App will be stopped");
        }
        String[] updatedUserCommand = userCommand.trim().split(" ", 2);
        if (commandMap.containsKey(updatedUserCommand[0])) {
            commandMap.get(updatedUserCommand[0]).execute(updatedUserCommand);
            CommandReaderInterface.addToHistory(updatedUserCommand[0]);
        } else if (!updatedUserCommand[0].equals(""))
            println("Данной команды не существует. Наберите 'help' для справки.");
    }

    /**
     * Метод, который начинает работу приложения с прочтения csv файла
     */
    @Override
    public void start() {
        try {
            Scanner fileInput = new Scanner(System.in);
            println("Do you wanna start app work with csv file input? (yes/no)");

            String input = fileInput.nextLine();
            while (!input.equals("yes") && !input.equals("no")) {
                println("(yes/no)");
                input = fileInput.nextLine();
            }
            if (input.matches("yes")) {
                csvFileWorkerInterface.loadInput(manager.getStudyGroups());
            }
            println("The app is ready to work");
        } catch (NoSuchElementException e) {
            println("^D is forbidden input");
        }
    }

    /**
     * Добавить команду
     *
     * @param commandName - название команды
     * @param command     - сама команда
     */
    @Override
    public void addCommand(String commandName, AbstractCommand command) {
        commandMap.put(commandName, command);
    }

    /**
     * Получить Collection Manager
     *
     * @return manager
     */
    @Override
    public CollectionManager getManager() {
        return manager;
    }

    @Override
    public HashMap<String, AbstractCommand> getCommandMap() {
        return commandMap;
    }
}