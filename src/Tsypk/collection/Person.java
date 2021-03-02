package Tsypk.collection;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Поле не может быть null
    private Location location; //Поле может быть null

    public Person(String name, String passportID, Location location) {
        this.name = name;
        this.passportID = passportID;
        this.location = location;
    }

    public Person() {};

    public boolean setName(String name) {
        if(name==null)
            return false;
        else if(name.length()>0) {
            this.name = name;
            return true;
        }
        else
            return false;
    }

    public boolean setPassportID(String passportID) {
        if(passportID==null)
            return false;
        else if (passportID.length() > 0) {
            this.passportID = passportID;
            return true;
        } else {
            return false;
        }
    }

    public boolean setLocation(Location location) {
        if(location==null)
            return false;
        else {
            this.location = location;
            return true;
        }
    }

    public String getName() {
        return name;
    }

    public String getPassportID() {
        return passportID;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", passportID='" + passportID + '\'' +
                ", location=" + location.toString() +
                '}';
    }
}
