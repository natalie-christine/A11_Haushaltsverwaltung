import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetManager {



    //   update

    DatabaseManager databaseManager = new DatabaseManager();

    private Map<String, Pet> petList = new HashMap<>();

    public static int getID() {
        int newID =  Pet.getIdCounter();
        return newID;
    }


    public Pet createPet(int id,String animalSpecies,  String name, String gender, LocalDate birthday) {
        Pet newPet = new Pet(id, animalSpecies,name, gender,birthday);
        databaseManager.createPet(id, animalSpecies,name, gender,birthday);
        addPet(newPet);
        return newPet;
    }
    private void addPet(Pet pet) {
        petList.put(pet.getName(), pet);
    }


    public void removePet (String deletePet) {
        petList.remove(deletePet);
    }

    public void printPetList() {

        List<Pet> petList = databaseManager.listPets();

       for (Pet pet : petList) {
            System.out.println(pet);

        }


    }

    public Pet searchPet(String personName) {
        Pet pet = petList.getOrDefault(personName, null);
        if (pet == null) {
            throw new NullPointerException("Pet not found");
        }
        return pet;
    }




}
