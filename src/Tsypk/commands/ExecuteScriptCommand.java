package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.exceptions.ScriptException;
import Tsypk.utils.ScriptReader;

public class ExecuteScriptCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public ExecuteScriptCommand(CollectionManager collectionManager) {
        super("execute_script", " : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        try {
            ScriptReader.inputFilePath();
            ScriptReader scriptReader = new ScriptReader(collectionManager);
            scriptReader.readCommand();
            ScriptReader.clearPaths();
        } catch (ScriptException e){
            System.err.println("You have recursion! Fix the script file ");
        }
    }
}