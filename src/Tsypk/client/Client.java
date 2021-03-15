package Tsypk.client;

import Tsypk.commands.CommandReaderInterface;

public class Client {
    private final CommandReaderInterface commandReader;

    public Client(CommandReaderInterface commandReader) {
        this.commandReader = commandReader;
    }

    public void interactiveMode() {
        commandReader.start();
        while (commandReader.readCommand()) ;
    }
}
