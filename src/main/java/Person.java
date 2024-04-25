import java.time.LocalDate;

public class Person {
    private Address address;
    private String name;
    private String lastname;
    private LocalDate birthday;
    private String gender;
    private int id;


    private static int idCounter = 12;




    public static int getIdCounter() {
        return idCounter++;
    }

    Person(int id, String name, String lastname) {
        this.id = idCounter++;
        this.name = name;
        this.lastname = lastname;


    }
    Person( int id, String name, String lastname, String gender, LocalDate birthday) { /// ,Address adress)
        this.id = idCounter++;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.gender = gender;
      //  this.adress = adress;
    }
/*    Person(String name, String lastname, LocalDate birthday, Gender gender) {
       this.id = idCounter++;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.gender = gender;

    }*/

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
