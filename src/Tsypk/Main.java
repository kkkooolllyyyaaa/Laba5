package Tsypk;

import Tsypk.client.Client;
import Tsypk.collection.CollectionManager;
import Tsypk.commands.CommandReader;
import Tsypk.commands.CommandReaderInterface;
import Tsypk.utils.CSVFileWorker;
import Tsypk.utils.FileWorker;

public class Main {
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        FileWorker fileWorkerInterface = new CSVFileWorker(collectionManager);
        CommandReaderInterface commandReader = new CommandReader(collectionManager, fileWorkerInterface);
        Client client = new Client(commandReader);
        client.interactiveMode();
    }
}
