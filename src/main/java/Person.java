import java.time.LocalDate;

public class Person {
    private Address adress;
    private String name;
    private String lastname;
    private LocalDate birthday;
    private Gender gender;
    private int id;


    private static int idCounter = 1;


    public int getId() {
        return id;
    }
    Person(String name, String lastname) {
        this.id = idCounter++;
        this.name = name;
        this.lastname = lastname;


    }
    Person(String name, String lastname, LocalDate birthday, Gender gender, Address adress) {
        this.id = idCounter++;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.gender = gender;
        this.adress = adress;
    }
    Person(String name, String lastname, LocalDate birthday, Gender gender) {
        this.id = idCounter++;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("%03d %-20s %-12s %-6s %-6s %s", id, name, lastname, birthday, gender, adress);
    }


}
