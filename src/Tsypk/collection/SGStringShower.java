package Tsypk.collection;

public class SGStringShower {
    public static String toStrView(StudyGroup st){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("StudyGroup{id="+st.getId()+
                ", name='"+st.getName()+
                "', coordinates=Coordinates{x="+st.getCoordinates().getX()+
                ", y="+st.getCoordinates().getY()+
                "}, creationDate="+st.getCreationDate().toString()+
                ", studentsCount="+st.getStudentsCount()+
                ", formOfEducation="+st.getFormOfEducation()+
                ", semesterEnum="+st.getSemesterEnum()+
                ", groupAdmin=Person{name='"+st.getGroupAdmin().getName()+
                "', passportID='"+st.getGroupAdmin().getPassportID()+
                "', location=Location{x="+st.getGroupAdmin().getLocation().getX()+
                ", y="+st.getGroupAdmin().getLocation().getY()+
                ", z="+st.getGroupAdmin().getLocation().getZ()+
                ", name='" +st.getGroupAdmin().getName()+
                "'}}}\n");
        return stringBuilder.toString();
    }
}

