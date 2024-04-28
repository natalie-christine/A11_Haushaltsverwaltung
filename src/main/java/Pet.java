import java.time.LocalDate;

public class Pet {

    private String animalSpecies;
    private int id;
    private String name;

    private String gender;
    private LocalDate birthday;

    private Address address;
    private Household householdId;

private int personenID;

    public Pet(int id,String animalSpecies,  String name, String gender, LocalDate birthday, int personenID) { // ,householdId
        this.id = id;
        this.animalSpecies = animalSpecies;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.personenID = personenID;

    }
    public Pet(int id,String animalSpecies,  String name, String gender, LocalDate birthday) {
        this.id = id;
        this.animalSpecies = animalSpecies;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;

    }


    public String getName() {
        return name;
    }

    public int getId() { return id;
    }
    public int getID() { return id;
    }
    @Override
    public String toString() {
        return String.format("%03d %10s %-12s %-6s %-6s %d",id, animalSpecies, name, gender,birthday, personenID );

    }



}