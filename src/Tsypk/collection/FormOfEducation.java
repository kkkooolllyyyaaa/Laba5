package Tsypk.collection;

import Tsypk.collection.*;

public enum FormOfEducation {
    DISTANCE_EDUCATION("DISTANCE_EDUCATION"),
    FULL_TIME_EDUCATION("FULL_TIME_EDUCATION"),
    EVENING_CLASSES("EVENING_CLASSES");

    private String url;

    FormOfEducation(String url){
        this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public static void printValues(){
        System.out.println("List of FormOfEducation enum values:");
        for(FormOfEducation fOE : FormOfEducation.values()){
            System.out.println(fOE.getUrl());
        }
    }
}
