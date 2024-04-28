import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PetDatabaseMySQL implements PetDAO {
    public List<Pet> listPets() {

        List<Pet> petList = new ArrayList<>();
        String sql = "SELECT * FROM pets";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String species = resultSet.getString("Species");
                String name = resultSet.getString("Name");
                String gender = resultSet.getString("Gender");
                LocalDate birthday = resultSet.getDate("Birthday").toLocalDate();

                System.out.println("ID: " + id);
                System.out.println("Species: " + species);
                System.out.println("Name: " + name);
                System.out.println("Gender: " + gender);
                System.out.println("Birthday: " + birthday);
                System.out.println();

                Pet pet = new Pet(id, species, name, gender, birthday);
                petList.add(pet);


            }
        } catch (SQLException e) {
            System.err.println("Error fetching pets: " + e.getMessage());
        }
        return petList;
    }

    public void createPet(String animalSpecies,  String name, String gender, LocalDate birthday) {
        String sql = " INSERT INTO pets( Species, Name, Gender, Birthday )VALUES (?, ?, ?, ?)";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, animalSpecies);
            statement.setString(2, name);
            statement.setString(3, gender);
            statement.setDate(4, java.sql.Date.valueOf(birthday));



            statement.executeUpdate();
            System.out.println("Pet created successfully!");
        } catch (SQLException e) {
            System.err.println("Error creating pet: " + e.getMessage());
        }
    }

    public List<Pet> searchPet(String petName) {
        List<Pet> petList = new ArrayList<>();

        String sql = "SELECT * FROM pets WHERE Name like  ? OR Species like  ?";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, petName);
            statement.setString(2,petName);


            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String species = resultSet.getString("Species");
                    String name = resultSet.getString("Name");
                    String gender = resultSet.getString("Gender");
                    java.sql.Date sqlDate = resultSet.getDate("Birthday");
                    LocalDate birthday = (sqlDate != null) ? sqlDate.toLocalDate() : null;


                    Pet pet = new Pet(id, species, name, gender, birthday);
                    petList.add(pet);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error searching for pet: " + e.getMessage());
        }

        if (petList.isEmpty()) {
            throw new NullPointerException("Pet not found");
        }

        return petList;

    }

    public void deletePet(Pet deletePet) {

        String sql = "DELETE FROM pets WHERE ID = ?";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, deletePet.getId());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pet successfully deleted.");
            } else {
                System.out.println("Error deleting pet: No rows affected.");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting pet: " + e.getMessage());
        }
    }


    public void updatePet(int id, String animalSpecies, String name, String gender, LocalDate birthday) {

        String sql = "UPDATE pets SET Species = ?, Name = ?, Gender = ? , Birthday = ?  WHERE ID = ?";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(5,id);
            statement.setString(1, animalSpecies);
            statement.setString(2, name);
            statement.setString(3, gender);
            statement.setDate(4, java.sql.Date.valueOf(birthday));



            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pet successfully updated.");
            } else {
                System.out.println("Error updating pet: No rows affected.");
            }

        } catch (SQLException e) {
            System.err.println("Error updating pet: " + e.getMessage());
        }
    }

}
