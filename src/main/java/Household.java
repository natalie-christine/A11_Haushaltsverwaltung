import java.util.ArrayList;
import java.util.List;

public class Household {

    private int id;
    private String name;
    private int addressID;
    private List<Person> members;
    private List<Pet> pets;

    public Household(int id, String name, int addressID) {
        this.id = id;
        this.name = name;
        this.addressID = addressID;
        this.members = new ArrayList<>();
        this.pets = new ArrayList<>();
    }



    public void addMember(Person person) {
        members.add(person);
    }

    public void removeMember(Person person) {
        members.remove(person);
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public void removePet(Pet pet) {
        pets.remove(pet);
    }

    @Override
    public String toString() {
        return String.format("%03d %-20s %6d %-6s %-6s ", id, name, addressID, members, pets);
    }

    public int getID() {
        return id;
    }
}

