import java.time.LocalDate;

public class Person {
    private int householdID;
    private Address address;
    private String name;
    private String lastname;
    private LocalDate birthday;
    private String gender;
    private int id;


    Person(int householdID, String name, String lastname) {
        this.householdID = householdID;
        this.name = name;
        this.lastname = lastname;
    }

    public Person(int id, String name, String lastname, String gender, LocalDate birthday, int householdId) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.gender = gender;
        this.householdID = householdId;
    }

    public int getId() {
        return id;
    }

    Person( int id, String name, String lastname, String gender, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("%03d %-20s %-12s %-6s %-6s %d", id, name, lastname, birthday, gender, householdID);
    }


}
