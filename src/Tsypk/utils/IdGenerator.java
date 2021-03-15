package Tsypk.utils;

import Tsypk.collection.CollectionManager;

import java.util.HashSet;

public interface IdGenerator {
    static long generateId(CollectionManager collectionManager) {
        HashSet<Long> idList = collectionManager.getIdList();
        long id = (long) (Math.random() * 666666);
        while (idList.contains(id)) {
            id = (long) (Math.random() * 666666);
        }
        idList.add(id);
        return id;
    }
}