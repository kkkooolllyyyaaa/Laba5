package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.exceptions.ScriptException;
import Tsypk.utils.ScriptReader;

public class ExecuteScriptFileCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public ExecuteScriptFileCommand(CollectionManager collectionManager) {
        super("execute_script", " : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        try {
            ScriptReader.inputFilePathScript();
            ScriptReader scriptReader = new ScriptReader(collectionManager);
            collectionManager.executeScript(scriptReader);
        } catch (ScriptException e) {
            System.err.println("You have recursion! Fix the script file");
            CollectionManager.print("Script is not invoked successfully", true);
        }
    }
}
