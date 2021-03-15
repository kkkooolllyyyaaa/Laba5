package Tsypk.collection;

import Tsypk.exceptions.InvalidFieldException;

public interface StudyGroupCreaterInterface {
    void setName(String name) throws InvalidFieldException;

    void setCoordinateX(int x) throws InvalidFieldException;

    void setCoordinateY(Long y) throws InvalidFieldException;

    void setStudentsCount(int studentsCount) throws InvalidFieldException;

    void setFormOfEducation(FormOfEducation formOfEducation) throws InvalidFieldException;

    void setSemester(Semester semester) throws InvalidFieldException;

    void setGAName(String name) throws InvalidFieldException;

    void setGAPassportID(String passportID) throws InvalidFieldException;

    void setGALocation(Location location);

    void setGALocationX(long x);

    void setGALocationY(Long y) throws InvalidFieldException;

    void setGALocationZ(Long z) throws InvalidFieldException;

    void setGALocationName(String name) throws InvalidFieldException;

    StudyGroup getStudyGroup();

    Enum checkStudyGroupEnum(String str) throws InvalidFieldException;

    Long askStudyGroupId();
}
