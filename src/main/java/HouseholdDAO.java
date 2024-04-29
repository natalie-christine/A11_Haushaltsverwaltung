
import java.util.List;

public interface HouseholdDAO {
    public void createHousehold(String householdName, int addressToHousehold);
    public List<Household> listHouseholds();
    public List<Household> searchHousehold(String Name);

    public void addPersonToHousehold(int householdId, int personId);

    public void removeFromHousehold(Household household, Person person);


    public void addPetToHousehold(Household household, Pet pet);

    void addPetToHousehold(Pet pet, Household household);

    public void removePetFromHousehold(Household household, Pet pet);
    public void changeHouseholdName(Household household, String householdName);

    List<Person> getPeopleInHousehold(int householdID);

    void addPetToPerson(Pet pet, Person person);
}



