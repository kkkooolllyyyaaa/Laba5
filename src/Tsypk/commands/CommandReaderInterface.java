package Tsypk.commands;

import Tsypk.utils.LimitedQueue;

public interface CommandReaderInterface {
    LimitedQueue<String> history = new LimitedQueue(5);
    void start();
    boolean readCommand();
    static LimitedQueue<String> getHistory(){
        return history;
    }
    static void addToHistory(String str) {
        history.add(str);
    }

}
