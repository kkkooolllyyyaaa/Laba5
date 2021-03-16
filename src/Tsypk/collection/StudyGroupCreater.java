package Tsypk.collection;

import Tsypk.exceptions.InvalidFieldException;
import Tsypk.utils.IdGenerator;
import Tsypk.utils.InputChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * Класс, обеспечивающий ввод элементов StudyGroups
 * Обеспечивает валидность вводимых полей
 */
public class StudyGroupCreater implements StudyGroupCreaterInterface {
    private final CollectionManager manager;
    private final boolean isScript;
    private BufferedReader reader;
    private String line;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int studentsCount; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null

    public StudyGroupCreater(BufferedReader bufferedReader, CollectionManager manager, boolean isScript) {
        this.manager = manager;
        reader = bufferedReader;
        this.isScript = isScript;
    }

    @Override
    public void setName(String name) throws InvalidFieldException {
        if (name == null || name.equals(""))
            throw new InvalidFieldException("Invalid value for StudyGroup name");
        else
            this.name = name;
    }

    @Override
    public void setCoordinateX(int x) throws InvalidFieldException {
        if (x <= -393)
            throw new InvalidFieldException("Invalid value for StudyGroup Coordinate x");
        if (coordinates == null)
            this.coordinates = new Coordinates();
        coordinates.setX(x);
    }

    @Override
    public void setCoordinateY(Long y) throws InvalidFieldException {
        if (y == null || y <= -741)
            throw new InvalidFieldException("Invalid value for StudyGroup Coordinate y");
        if (coordinates == null)
            this.coordinates = new Coordinates();
        coordinates.setY(y);
    }

    @Override
    public void setStudentsCount(int studentsCount) throws InvalidFieldException {
        if (studentsCount <= 0)
            throw new InvalidFieldException("Invalid value for StudyGroup studentsCount");
        else
            this.studentsCount = studentsCount;
    }

    @Override
    public void setFormOfEducation(FormOfEducation formOfEducation) throws InvalidFieldException {
        if (formOfEducation == null)
            throw new InvalidFieldException("Invalid value for StudyGroup formOfEducation");
        else
            this.formOfEducation = formOfEducation;

    }

    @Override
    public void setSemester(Semester semester) throws InvalidFieldException {
        if (semester == null)
            throw new InvalidFieldException("Invalid value for StudyGroup semester");
        else
            this.semesterEnum = semester;

    }

    @Override
    public void setGAName(String name) throws InvalidFieldException {
        if (name == null || name.equals(""))
            throw new InvalidFieldException("Invalid value for StudyGroup GroupAdmin name");
        else {
            if (groupAdmin == null)
                groupAdmin = new Person();
            groupAdmin.setName(name);
        }

    }

    @Override
    public void setGAPassportID(String passportID) throws InvalidFieldException {
        if (passportID == null)
            throw new InvalidFieldException("Invalid value for StudyGroup GroupAdmin passportID");
        else if (groupAdmin == null)
            groupAdmin = new Person();
        groupAdmin.setPassportID(passportID);
    }

    @Override
    public void setGALocation(Location location) {
        groupAdmin.setLocation(location);
    }

    @Override
    public void setGALocationX(long x) {
        if (groupAdmin.getLocation() == null)
            setGALocation(new Location());
        groupAdmin.getLocation().setX(x);
    }

    @Override
    public void setGALocationY(Long y) throws InvalidFieldException {
        if (groupAdmin.getLocation() == null)
            setGALocation(new Location());
        else if (y == null)
            throw new InvalidFieldException("Invalid value for StudyGroup GroupAdmin Location x");
        groupAdmin.getLocation().setY(y);
    }

    @Override
    public void setGALocationZ(Long z) throws InvalidFieldException {
        if (groupAdmin.getLocation() == null)
            setGALocation(new Location());
        else if (z == null)
            throw new InvalidFieldException("Invalid value for StudyGroup GroupAdmin Location z");
        groupAdmin.getLocation().setZ(z);
    }

    @Override
    public void setGALocationName(String gAName) throws InvalidFieldException {
        if (groupAdmin.getLocation() == null)
            groupAdmin.setLocation(new Location());
        else if (gAName == null)
            throw new InvalidFieldException("Invalid value for StudyGroup GroupAdmin Location name");
        groupAdmin.getLocation().setName(gAName);
    }

    @Override
    public StudyGroup getStudyGroup() {
        return new StudyGroup(name, coordinates, studentsCount, formOfEducation, semesterEnum, groupAdmin, IdGenerator.generateId(manager));
    }

    /**
     * Обработка ввода Enum'ов StudyGroup
     *
     * @param str
     * @return 'Semester' or 'FormOfEducation' or 'null'
     */
    @Override
    public Enum checkStudyGroupEnum(String str) throws InvalidFieldException {
        for (Semester smstr : Semester.values()) {
            if (str.equalsIgnoreCase(smstr.getUrl())) {
                return smstr;
            }
        }
        for (FormOfEducation fOE : FormOfEducation.values()) {
            if (str.equalsIgnoreCase(fOE.getUrl())) {
                return fOE;
            }
        }
        throw new InvalidFieldException("There is no enum named " + str);
    }

    /**
     * Обработка ввода Id Study Group
     *
     * @return Long id
     */
    @Override
    public Long askStudyGroupId() {
        String s;
        try {
            System.out.print("Input Study group id: ");
            s = reader.readLine();
            if (InputChecker.checkLong(s.trim()))
                return Long.parseLong(s);
            else
                System.out.println("Study group id can't be null; should be long");
        } catch (IOException e) {
            System.out.println("Input error");
        }
        return null;
    }

    /**
     * Ввод всех полей
     */
    public void inputFieldsFile() {
        askName();
        askCoordinateX();
        askCoordinateY();
        askStudentsCount();
        askFormOfEducation();
        askSemester();
        askGAName();
        askGAPassportID();
        askGALocationX();
        askGALocationY();
        askGALocationZ();
        askGALocationName();
    }

    public void askName() {
        print("Input Study Group Name: ");
        try {
            setName(inputLine());
        } catch (InvalidFieldException e) {
            println("Study Group Name should be String, can't be null");
            if (!isScript)
                askName();
        }
    }

    public void askCoordinateX() {
        print("Input Study Group Coordinate X: ");
        try {
            setCoordinateX(Integer.parseInt(inputLine()));
        } catch (InvalidFieldException | NumberFormatException e) {
            println("Study Group Coordinate X should be int and greater than -393");
            if (!isScript)
                askCoordinateX();
        }
    }

    public void askCoordinateY() {
        print("Input Study Group Coordinate Y: ");
        try {
            setCoordinateY(Long.parseLong(inputLine()));
        } catch (InvalidFieldException | NumberFormatException e) {
            println("Study Group Coordinate Y should be Long and greater than -741, can't be null");
            if (!isScript)
                askCoordinateY();
        }
    }

    public void askStudentsCount() {
        print("Input Study Group students count: ");
        try {
            setStudentsCount(Integer.parseInt(inputLine()));
        } catch (InvalidFieldException | NumberFormatException e) {
            println("Study Group students count should be int and greater than 0");
            if (!isScript)
                askStudentsCount();
        }
    }

    public void askFormOfEducation() {
        FormOfEducation.printValues();
        print("Input Study Group Form Of Education: ");
        try {
            setFormOfEducation((FormOfEducation) checkStudyGroupEnum(inputLine()));
        } catch (InvalidFieldException e) {
            println("There is no in Study Group Form Of Education value: " + line + "\nField Form Of Education can't be null");
            if (!isScript)
                askFormOfEducation();
        }
    }

    public void askSemester() {
        Semester.printValues();
        print("Input Study Group Semester: ");
        try {
            setSemester((Semester) checkStudyGroupEnum(inputLine()));
        } catch (InvalidFieldException e) {
            println("There is no in Study Group Semester value: " + line + "\nField Semester can't be null");
            if (!isScript)
                askSemester();
        }
    }

    public void askGAName() {
        print("Input Study Group Group Admin name: ");
        try {
            setGAName(inputLine());
        } catch (InvalidFieldException e) {
            println("Study Group Group Admin name can't be void or null");
            if (!isScript)
                askGAName();
        }
    }

    public void askGAPassportID() {
        print("Input Study Group Group Admin passportID: ");
        try {
            setGAPassportID(inputLine());
        } catch (InvalidFieldException e) {
            println("Study Group Group Admin passportID can't be null");
            if (!isScript)
                askGAPassportID();
        }
    }

    public void askGALocationX() {
        print("Input Study Group Group Admin Location X: ");
        try {
            setGALocationX(Long.parseLong(inputLine()));
        } catch (NumberFormatException e) {
            println("Study Group Group Admin Location should be long");
            if (!isScript)
                askGALocationX();
        }

    }

    public void askGALocationY() {
        print("Input Study Group Group Admin Location Y: ");
        try {
            setGALocationY(Long.parseLong(inputLine()));
        } catch (NumberFormatException | InvalidFieldException e) {
            println("Study Group Group Admin Location should be Long, can't be null");
            if (!isScript)
                askGALocationY();
        }
    }

    public void askGALocationZ() {
        print("Input Study Group Group Admin Location Z: ");
        try {
            setGALocationZ(Long.parseLong(inputLine()));
        } catch (NumberFormatException | InvalidFieldException e) {
            println("Study Group Group Admin Location should be Long, can't be null");
            if (!isScript)
                askGALocationZ();
        }
    }

    public void askGALocationName() {
        print("Input Study Group Admin Location name: ");
        try {
            setGALocationName(inputLine());
        } catch (InvalidFieldException e) {
            println("Study Group Group Admin Location name should be String, can't be null");
            if (!isScript)
                askGALocationName();
        }
    }

    /**
     * Прочитать строку
     *
     * @return line
     */
    public String inputLine() {
        try {
            line = reader.readLine().toLowerCase().trim();
        } catch (IOException ioException) {
            println(ioException.getMessage());
        }
        return line;
    }

    /**
     * Обеспечивает ввод всех полей и передачу экземпляра StudyGroup
     *
     * @return studyGroup
     */
    public StudyGroup askStudyGroup() {
        inputFieldsFile();
        return getStudyGroup();
    }

    /**
     * Обеспечивает общение StudyGroupCreater с переносом строки
     *
     * @param message
     */
    public void println(String message) {
        System.out.println(message);
    }

    /**
     * Обеспеичвает общение StudyGroupCreater без переноса строки
     *
     * @param message
     */
    public void print(String message) {
        System.out.print(message);
    }

    /**
     * Setter
     *
     * @param reader
     */
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }
}
