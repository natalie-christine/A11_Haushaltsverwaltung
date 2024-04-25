import java.time.LocalDate;

public class Person {
    private Address address;
    private String name;
    private String lastname;
    private LocalDate birthday;
    private String gender;
    private int id;


    Person(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    Person(int id, String name, String lastname, String gender, LocalDate birthday) { /// ,Address adress)
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.gender = gender;
        //  this.address = address;
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
        return String.format("%03d %-20s %-12s %-6s %-6s %s", id, name, lastname, birthday, gender, address);
    }


}
