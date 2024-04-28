
import java.util.List;

public interface HouseholdDAO {
    public void createHousehold(String householdName, int addressToHousehold);
    public void listHouseholds();
    public List<Household> searchHousehold(String Name);

    public void addPersonToHousehold(Household household, Person person);

    public void removeFromHousehold(Household household, Person person);


    public void addPetToHousehold(Household household, Pet pet);

    public void removePetFromHousehold(Household household, Pet pet);
    public void changeHouseholdName(Household household, String householdName);

}



