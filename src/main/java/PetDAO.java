
import java.time.LocalDate;
import java.util.List;

public interface PetDAO {

    public List<Pet> listPets();

    public void createPet(String animalSpecies,  String name, String gender, LocalDate birthday);
    public List<Pet> searchPet(String petName);

    public void deletePet(Pet deletePet);

    void updatePet(int id, String animalSpecies, String name, String gender, LocalDate birthday);
}
