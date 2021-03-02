package Tsypk.collection;

import Tsypk.collection.*;

import java.time.ZonedDateTime;
import java.util.Objects;

public class StudyGroup implements Comparable<StudyGroup>{
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int studentsCount; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null

    public StudyGroup(String name,
                      Coordinates coordinates,
                      int studentsCount,
                      FormOfEducation formOfEducation,
                      Semester semesterEnum,
                      Person groupAdmin) {
        this.name = name;
        this.coordinates = coordinates;
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;

        this.creationDate=ZonedDateTime.now();
        this.id= CollectionManager.generateId();
    }
    public StudyGroup(){
        this.creationDate=ZonedDateTime.now();
        this.id= CollectionManager.generateId();
    };

    public boolean  setName(String name) {
        if(name==null){
            return false;
        }
        else if(name.length()==0){
            return false;
        }
        else {
            this.name = name;
            return true;
        }
    }

    public boolean setCoordinates(Coordinates coordinates) {
        if(coordinates==null)
            return false;
        else {
            this.coordinates = coordinates;
            return true;
        }
    }

    public boolean setStudentsCount(int studentsCount) {
        if(studentsCount>0) {
            this.studentsCount = studentsCount;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean setFormOfEducation(FormOfEducation formOfEducation) {
        if(formOfEducation==null)
            return false;
        else
            this.formOfEducation = formOfEducation;
        return true;
    }

    public boolean setSemesterEnum(Semester semesterEnum) {
        if(semesterEnum==null)
            return false;
        else {
            this.semesterEnum = semesterEnum;
            return true;
        }
    }

    public void setGroupAdmin(Person groupAdmin) {
        if(groupAdmin==null)
            throw new NullPointerException();
        else
            this.groupAdmin = groupAdmin;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates.toString() +
                ", creationDate=" + creationDate.getHour()+':'+creationDate.getMinute()+':'+creationDate.getSecond()+' '+creationDate.getDayOfMonth()+' '+creationDate.getMonth()+' '+creationDate.getYear() +
                ", studentsCount=" + studentsCount +
                ", formOfEducation=" + formOfEducation +
                ", semesterEnum=" + semesterEnum +
                ", groupAdmin=" + groupAdmin.toString() +
                '}';
    }

    @Override
    public int compareTo(StudyGroup o) {
        return (int)(getId()-o.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
