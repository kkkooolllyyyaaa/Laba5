package Tsypk.collection;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Класс, обеспечивающий ввод элементов StudyGroup
 */
public class StudyGroupAsker {
    private BufferedReader reader;
    public StudyGroupAsker(BufferedReader bufferedReader){
        reader=bufferedReader;
    }

    /**
     * Обработка ввода пользователя
     * @return Study Group
     */
    public StudyGroup askStudyGroup() {
        StudyGroup studyGroup = new StudyGroup();
        try {
            CollectionManager.print("Input Study Group:", true);

            CollectionManager.print("Input Study Group Name: ", false);
            while (!studyGroup.setName(reader.readLine().trim())) {
                CollectionManager.print("Study Group Name can't be null or void\nInput Study Group name: ", false);
            }

            CollectionManager.print("Input Study Group Coordinates:", true);
            Coordinates coordinates = new Coordinates();

            CollectionManager.print("Input Coordinate X: ", false);
            String s = reader.readLine().trim();
            while (!InputChecker.checkInt(s) || !coordinates.setX(Integer.parseInt(s))) {
                CollectionManager.print("Coordinate X can't be null; must be greater than -393; should be int\nInput Coordinate X: ", false);
                s = reader.readLine().trim();
            }
            coordinates.setX(Integer.parseInt(s));

            CollectionManager.print("Input Coordinate Y: ", false);
            s = reader.readLine().trim();
            while (!InputChecker.checkLong(s) || !coordinates.setY(Long.parseLong(s))) {
                CollectionManager.print("Coordinate Y can't be null; must be greater than -741; should be Long\nInput Coordinate Y: ", false);
                s = reader.readLine().trim();
            }
            coordinates.setY(Long.parseLong(s));
            studyGroup.setCoordinates(coordinates);

            CollectionManager.print("Input Study Group studentsCount: ", false);
            s = reader.readLine().trim();
            while (!InputChecker.checkInt(s) || !studyGroup.setStudentsCount(Integer.parseInt(s))) {
                CollectionManager.print("Study Group studentsCount can't be null; must be greater than 0; should be int\nInput Study Group studentsCount : ", false);
                s = reader.readLine().trim();
            }
            studyGroup.setStudentsCount(Integer.parseInt(s));


            CollectionManager.print("Input Study Group FormOfEducation: ", true);
            FormOfEducation.printValues();
            while (!studyGroup.setFormOfEducation((FormOfEducation) askStudyGroupEnum(reader.readLine().trim()))) {
                CollectionManager.print("Study Group FormOfEducation can't be null or invalid\nInput Study Group FormOfEducation: ", true);
            }

            CollectionManager.print("Input Study Group Semester: ", true);
            Semester.printValues();
            while (!studyGroup.setSemesterEnum((Semester) askStudyGroupEnum(reader.readLine().trim()))) {
                CollectionManager.print("Study Group Semester can't be null or invalid\nInput Study Group Semester ", true);
            }

            CollectionManager.print("Input Study Group groupAdmin:", true);
            Person admin = new Person();

            CollectionManager.print("Input groupAdmin name: ", false);
            while (!admin.setName(reader.readLine().trim())) {
                CollectionManager.print("groupAdmin name can't be null or void\nInput groupAdmin name: ", false);
            }

            CollectionManager.print("Input groupAdmin passportId: ", false);
            while (!admin.setPassportID(reader.readLine().trim())) {
                CollectionManager.print("groupAdmin passportId can't be null\nInput groupAdmin passportId: ", false);
            }

            CollectionManager.print("Input groupAdmin Location:", true);
            Location location = new Location();

            CollectionManager.print("Input Location X: ", false);
            s = reader.readLine().trim();
            while (!(InputChecker.checkLong(s))) {
                CollectionManager.print("Location X can't be null; should be long\nInput Location X: ", false);
                s = reader.readLine().trim();
            }

            CollectionManager.print("Input Location Y: ", false);
            s = reader.readLine().trim();
            while (!InputChecker.checkLong(s)) {
                CollectionManager.print("Location Y can't be null; should be Long\nInput Coordinate Y: ", false);
                s = reader.readLine().trim();
            }
            location.setY(Long.parseLong(s));


            CollectionManager.print("Input Location Z: ", false);
            s = reader.readLine().trim();
            while (!InputChecker.checkLong(s)) {
                CollectionManager.print("Location Z can't be null; should be Long\nInput Coordinate Z: ", false);
                s = reader.readLine().trim();
            }
            location.setZ(Long.parseLong(s));

            CollectionManager.print("Input Location Name: ", false);
            while (!location.setName(reader.readLine().trim())) {
                CollectionManager.print("Location Name can't be null\nInput Location Name: ", false);
            }
            admin.setLocation(location);
            studyGroup.setGroupAdmin(admin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studyGroup;
    }

    /**
     * Обработка ввода Enum'ов StudyGroup
     * @param str
     * @return 'Semester' or 'FormOfEducation' or 'null'
     */
    public Enum askStudyGroupEnum(String str) {
        for (Semester smstr : Semester.values()) {
            if (CollectionManager.isEquivalent(str, smstr.getUrl())) {
                return smstr;
            }
        }
        for (FormOfEducation fOE : FormOfEducation.values()) {
            if (CollectionManager.isEquivalent(str, fOE.getUrl())) {
                return fOE;
            }
        }
        return null;
    }

    /**
     * Обработка ввода Id Study Group
     * @return Long id
     */
    public Long askStudyGroupId(){
        String s;
        try {
            CollectionManager.print("Input Study group id: ", false);
            s = reader.readLine();
            if (InputChecker.checkLong(s.trim()))
                return Long.parseLong(s);
            else
                System.out.println("Study group id can't be null; should be long");
        } catch (IOException e){
            System.out.println("Input error");
        }
        return null;
    }
}
