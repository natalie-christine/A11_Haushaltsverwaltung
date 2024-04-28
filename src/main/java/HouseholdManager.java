import java.util.List;

public class HouseholdManager {

    HouseholdDatabaseMySQL householdDatabaseMySQL = new HouseholdDatabaseMySQL();


    public void createHousehold(String householdName, int addressToHousehold) {
        householdDatabaseMySQL.createHousehold(householdName, addressToHousehold);
    }

    public void listHouseholds() {

        List<Household> householdslist = householdDatabaseMySQL.listHouseholds();

        for (Household household : householdslist) {
            System.out.println(household);
        }

    }
    public List<Household> searchHousehold(String Name) {

        List<Household> householdList = householdDatabaseMySQL.searchHousehold(Name);
        if (householdList.isEmpty()) {
            throw new NullPointerException("Household not found");
        }
    /*    else {
            for (Household household : householdList) {
                System.out.println(household);
            }
        }*/
        return householdList;
    }
    

    public void addHouseholdMember(Household household, Person person) {
        int id = household.getID();
        householdDatabaseMySQL.addPersonToHousehold(id, person.getId());
    }

    public void removeHouseholdMember(Household household, Person person) {
        int householdID = household.getID();
        int personID = person.getId();
        householdDatabaseMySQL.removeFromHousehold(householdID,personID);
    }

    public void addHouseholdPet(Household household, Pet pet) {
        int householdID = household.getID();
        int petID = pet.getId();
        householdDatabaseMySQL.addPetToHousehold(householdID,petID);
    }

    public void removeHouseholdPet(Household household, Pet pet) {
        int householdID = household.getID();
        int petID = pet.getId();
        householdDatabaseMySQL.removePetFromHousehold(householdID,petID);
    }

    public void changeHouseholdName(Household household, String householdName) {
        int householdID = household.getID();
        householdDatabaseMySQL.changeHouseholdName(householdID, householdName);
    }

}
