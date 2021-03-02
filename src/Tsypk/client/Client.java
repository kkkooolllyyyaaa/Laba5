package Tsypk.client;

import Tsypk.commands.CommandReader;

public class Client {
    private CommandReader commandReader;

    public Client(CommandReader commandReader) {
        this.commandReader = commandReader;
    }

    public void interactiveMod(){
        commandReader.start();
        while(commandReader.readCommand());
    }
}
