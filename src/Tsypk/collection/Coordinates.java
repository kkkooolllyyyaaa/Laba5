package Tsypk.collection;

public class Coordinates {
    private int x; //Значение поля должно быть больше -393
    private Long y; //Значение поля должно быть больше -741, Поле не может быть null

    public Coordinates(int x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(){}

    public boolean setX(int x){
        if(x>-393) {
            this.x = x;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean setY(Long y){
        if(y==null)
            return false;
        else if(y>-741) {
            this.y = y;
            return true;
        }
        else {
            return false;
        }
    }

    public int getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
