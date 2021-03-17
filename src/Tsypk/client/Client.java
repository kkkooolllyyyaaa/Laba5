package Tsypk.client;

import Tsypk.collection.CollectionManager;
import Tsypk.collection.StudyGroupCreater;
import Tsypk.collection.StudyGroupCreaterInterface;
import Tsypk.commands.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Класс клиента
 */
public class Client {
    private final CommandReaderInterface commandReader;
    private final boolean isRunning = true;

    public Client(CommandReaderInterface commandReader) {
        this.commandReader = commandReader;
        CollectionManager manager = commandReader.getManager();
        StudyGroupCreaterInterface studyGroupCreater = new StudyGroupCreater(new BufferedReader(new InputStreamReader(System.in)), manager, false);
        commandReader.addCommand("add", new AddCommand(manager, studyGroupCreater));
        commandReader.addCommand("clear", new ClearCommand(manager));
        commandReader.addCommand("count_less_than_semester_semester_enum", new CountLessThanSemesterEnumCommand(manager, studyGroupCreater));
        commandReader.addCommand("execute_script", new ExecuteScriptCommand(manager));
        commandReader.addCommand("exit", new ExitCommand());
        commandReader.addCommand("history", new HistoryCommand());
        commandReader.addCommand("info", new InfoCommand(manager));
        commandReader.addCommand("print_ascending", new PrintAscendingCommand(manager));
        commandReader.addCommand("remove_by_id", new RemoveByIdCommand(manager, studyGroupCreater));
        commandReader.addCommand("remove_first", new RemoveFirstCommand(manager));
        commandReader.addCommand("remove_greater", new RemoveGreaterCommand(manager, studyGroupCreater));
        commandReader.addCommand("save", new SaveCommand(manager));
        commandReader.addCommand("show", new ShowCommand(manager));
        commandReader.addCommand("sum_of_students_count", new SumOfStudentsCountCommand(manager));
        commandReader.addCommand("update", new UpdateCommand(manager, studyGroupCreater));
        commandReader.addCommand("help", new HelpCommand(commandReader.getCommandMap()));
    }

    /**
     * Начинает работу приложения
     */
    public final void interactiveMode() {
        commandReader.start();
        while (isRunning) commandReader.readCommand();
    }
}
