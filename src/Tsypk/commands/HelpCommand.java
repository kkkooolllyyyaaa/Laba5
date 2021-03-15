package Tsypk.commands;

import java.util.HashMap;

public class HelpCommand extends AbstractCommand {
    private final HashMap<String, AbstractCommand> commandMap;

    public HelpCommand(HashMap<String, AbstractCommand> commandMap) {
        super("help", " : вывести справку по доступным командам");
        this.commandMap = commandMap;
    }

    @Override
    public void execute(String[] args) {
        for (String it : commandMap.keySet()) {
            System.out.println(commandMap.get(it).getName() + commandMap.get(it).getDescription());
        }
    }
}
