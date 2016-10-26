/**
 * Created by Пользователь on 22.10.2016.
 */
public class Group {
    private int groupId;        //Поле ID группы
    private String nameGroup;   //Поле Имя группы
    private String curator;     //Поле Куратор группы
    private String speciality;  //Поле Специальность группы

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public String getCurator() {
        return curator;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return nameGroup;
    }
}
