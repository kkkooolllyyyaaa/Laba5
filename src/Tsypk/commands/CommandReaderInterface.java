package Tsypk.commands;

import Tsypk.collection.CollectionManager;
import Tsypk.utils.LimitedQueue;

import java.util.HashMap;

public interface CommandReaderInterface {
    LimitedQueue<String> history = new LimitedQueue(5);

    static LimitedQueue<String> getHistory() {
        return history;
    }

    static void addToHistory(String str) {
        history.add(str);
    }

    void start();

    void readCommand();

    void addCommand(String commandName, AbstractCommand command);

    CollectionManager getManager();

    HashMap<String, AbstractCommand> getCommandMap();

}
