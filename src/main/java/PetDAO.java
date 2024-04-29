
import java.time.LocalDate;
import java.util.List;

public interface PetDAO {

    public List<Pet> listPets();

    public void createPet(Pet pet);
    public List<Pet> searchPet(String petName);

    public void deletePet(Pet deletePet);

    void updatePet(Pet pet);
}
