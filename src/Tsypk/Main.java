package Tsypk;

import Tsypk.client.Client;
import Tsypk.commands.CommandReader;

public class Main {
    public static void main(String[] args) {
        CommandReader commandReader = new CommandReader();
        Client client = new Client(commandReader);
        client.interactiveMode();
    }
}
