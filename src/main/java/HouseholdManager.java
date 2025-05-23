import java.util.List;

public class HouseholdManager{

    HouseholdDAO householdDatabaseMySQL = new HouseholdDatabaseMySQL();


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
        return householdList;
    }




    public void addPersonToHousehold(Household household, Person person) {
        int id = household.getID();
        householdDatabaseMySQL.addPersonToHousehold(id, person.getId());
    }

    public void removeFromHousehold(Household household, Person person) {
        int householdID = household.getID();
        int personID = person.getId();
     //   householdDatabaseMySQL.removeFromHousehold(householdID,personID);
        householdDatabaseMySQL.removeFromHousehold(household,person);
    }



    public void addPetToHousehold(Household household, Pet pet) {
      /*  int householdID = household.getID();
        int petID = pet.getId();
        householdDatabaseMySQL.addPetToHousehold(householdID,petID);*/
        householdDatabaseMySQL.addPetToHousehold(household,pet);
    }


public void addPetToPerson(Pet pet, Person person) {
  /*  int petID = pet.getID();
    int personenID = person.getId();
    householdDatabaseMySQL.addPetToPerson(petID, personenID);*/
    householdDatabaseMySQL.addPetToPerson(pet, person);


}

    public void removePetFromHousehold(Household household, Pet pet) {
        /*int householdID = household.getID();
        int petID = pet.getId();
        householdDatabaseMySQL.removePetFromHousehold(householdID,petID);*/
        householdDatabaseMySQL.removePetFromHousehold(household,pet);
    }

    public void changeHouseholdName(Household household, String householdName) {
       // int householdID = household.getID();
        householdDatabaseMySQL.changeHouseholdName(household, householdName);
    }

    public List<Person> getPeopleInHousehold(Household householdToUpdate) {
        int householdID = householdToUpdate.getID();
       List <Person> peopleInHouseholds =  householdDatabaseMySQL.getPeopleInHousehold(householdID);

        return peopleInHouseholds;
    }
}
