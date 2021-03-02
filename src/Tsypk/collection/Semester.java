package Tsypk.collection;

import Tsypk.collection.*;

public enum Semester {
    FIRST("FIRST",1),
    SECOND("SECOND",2),
    FOURTH("FOURTH",4),
    SIXTH("SIXTH",6),
    SEVENTH("SEVENTH",7);

    private final int value;
    private final String url;
    Semester(String url, int value){
        this.value=value;
        this.url=url;
    }
    public int getValue() {
        return value;
    }

    public String getUrl() {
        return url;
    }

    public static void printValues(){
        System.out.println("List of SEMESTER enum values:");
        for(Semester smstr : Semester.values()){
            System.out.println(smstr.getUrl());
        }
    }
}
