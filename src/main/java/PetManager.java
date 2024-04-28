import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetManager {



    //   update


    PetDatabaseMySQL petDatabaseManager = new PetDatabaseMySQL();

    private Map<String, Pet> petList = new HashMap<>();



    public void createPet(String animalSpecies,  String name, String gender, LocalDate birthday) {
        petDatabaseManager.createPet( animalSpecies,name, gender,birthday);
    }
    private void addPet(Pet pet) {
        petList.put(pet.getName(), pet);
    }


    public void removePet (Pet deletePet) {
        petDatabaseManager.deletePet(deletePet);
    }

    public void printPetList() {

        List<Pet> petList = petDatabaseManager.listPets();

       for (Pet pet : petList) {
            System.out.println(pet);

        }

    }


    public List <Pet> searchPet(String petName) {

        List<Pet> petList = petDatabaseManager.searchPet(petName);

        if (petList.isEmpty()) {
            throw new NullPointerException("Pet not found");
        }
        return petList;
    }



    public void updatePet(int id, String animalSpecies, String name, String gender, LocalDate birthday) {

        petDatabaseManager.updatePet(id, animalSpecies, name, gender, birthday);
    }
}
