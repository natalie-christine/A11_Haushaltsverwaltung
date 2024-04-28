import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQuery {



    // Methode zum Suchen von Personen eines Haushalts mit Haustieren
    public List<Person> getPeopleInHouseholdWithPets(int householdID) {
        List<Person> peopleWithPets = new ArrayList<>();

        String sql = "SELECT p.* FROM personen p JOIN pets pe ON p.ID = pe.ownerID WHERE p.householdID = ?";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, householdID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("Name");
                    String lastname = resultSet.getString("Lastname");
                    String gender = resultSet.getString("Gender");
                    LocalDate birthday = resultSet.getDate("Birthday").toLocalDate();
                    int householdId = resultSet.getInt("householdID");

                    // Erstelle eine neue Person und füge sie der Liste hinzu
                    Person person = new Person(id, name, lastname, gender, birthday, householdId);
                    peopleWithPets.add(person);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting people in household with pets: " + e.getMessage());
        }

        return peopleWithPets;
    }
    public List<Person> getPeopleInHousehold(int householdID) {
        List<Person> peopleInHousehold = new ArrayList<>();

        String sql = "SELECT * FROM personen WHERE householdID = ?";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, householdID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("Name");
                    String lastname = resultSet.getString("Lastname");
                    String gender = resultSet.getString("Gender");
                    LocalDate birthday = resultSet.getDate("Birthday").toLocalDate();
                    int householdId = resultSet.getInt("householdID");

                    // Erstelle eine neue Person und füge sie der Liste hinzu
                    Person person = new Person(id, name, lastname, gender, birthday, householdId);
                    peopleInHousehold.add(person);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting people in household: " + e.getMessage());
        }

        return peopleInHousehold;
    }
}
