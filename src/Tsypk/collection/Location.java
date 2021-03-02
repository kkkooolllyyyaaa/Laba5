package Tsypk.collection;

public class Location {
    private long x;
    private Long y; //Поле не может быть null
    private Long z; //Поле не может быть null
    private String name; //Поле не может быть null

    public Location(long x, long y, long z, String name){
        this.x=x;
        this.y=y;
        this.z=z;
        this.name=name;
    }

    public Location(){}

    public boolean setX(long x) {
        this.x = x;
        return true;
    }

    public boolean setY(Long y) {
        if(y==null)
            return false;
        else {
            this.y = y;
            return true;
        }
    }

    public boolean setZ(Long z) {
        if(z==null)
            return false;
        else {
            this.z = z;
            return true;
        }
    }

    public boolean setName(String name) {
        if(name==null)
            return false;
        else if(name.length()>0){
            this.name = name;
            return true;
        }
        else
            return false;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }
}
