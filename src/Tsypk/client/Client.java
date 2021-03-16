package Tsypk.client;

import Tsypk.commands.CommandReaderInterface;

/**
 * Класс клиента
 */
public class Client {
    private final CommandReaderInterface commandReader;

    public Client(CommandReaderInterface commandReader) {
        this.commandReader = commandReader;
    }

    /**
     * Начинает работу приложения
     */
    public void interactiveMode() {
        commandReader.start();
        while (commandReader.readCommand()) ;
    }
}
