package Tsypk.commands;

import Tsypk.utils.LimitedQueue;

public interface CommandReaderInterface {
    LimitedQueue<String> history = new LimitedQueue(5);

    static LimitedQueue<String> getHistory() {
        return history;
    }

    static void addToHistory(String str) {
        history.add(str);
    }

    void start();

    boolean readCommand();

}
