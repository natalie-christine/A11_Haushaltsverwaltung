import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PersonDatabaseMySQL implements PersonDAO {

    public static final DateTimeFormatter birthdayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public List<Person> listPersons() {
        List<Person> personList = new ArrayList<>();

        String sql = "SELECT * FROM personen";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String firstName = resultSet.getString("Name");
                String lastName = resultSet.getString("Lastname");
                String gender = resultSet.getString("Gender");
                java.sql.Date sqlDate = resultSet.getDate("Birthday");
                LocalDate birthday = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                Person person = new Person(id, firstName, lastName, gender, birthday);
                personList.add(person);
            }
        } catch (SQLException e) {
            System.err.println("Error listing persons: " + e.getMessage());
        }

        return personList;
    }

    public void createPerson( String firstName, String lastName) {
        String sql = "INSERT INTO Personen ( Name, Lastname) VALUES ( ?, ?)";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);


            statement.executeUpdate();
            System.out.println("Person created successfully!");
        } catch (SQLException e) {
            System.err.println("Error creating person: " + e.getMessage());
        }
    }

    public void createPerson( String firstName, String lastName, String gender, LocalDate birthday) {
        String sql = "INSERT INTO Personen (Name, Lastname, Gender, Birthday) VALUES (?, ?, ?, ?)";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, String.valueOf(gender));
            statement.setDate(4, java.sql.Date.valueOf(birthday));


            statement.executeUpdate();
            System.out.println("Person created successfully!");
        } catch (SQLException e) {
            System.err.println("Error creating person: " + e.getMessage());
        }
    }
    public void deletePerson(Person deletePerson) {

        String sql = "DELETE FROM personen WHERE ID = ?";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, deletePerson.getId());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Person successfully deleted.");
            } else {
                System.out.println("Error deleting person: No rows affected.");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting person: " + e.getMessage());
        }
    }

    public List<Person> searchPerson(String personName) {
        List<Person> personList = new ArrayList<>();

        String sql = "SELECT * FROM personen WHERE Name = ? OR Lastname = ?";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, personName);
            statement.setString(2, personName);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String firstName = resultSet.getString("Name");
                    String lastName = resultSet.getString("Lastname");
                    String gender = resultSet.getString("Gender");
                    java.sql.Date sqlDate = resultSet.getDate("Birthday");
                    LocalDate birthday = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                    Person person = new Person(id, firstName, lastName, gender, birthday);
                    personList.add(person);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error searching for person: " + e.getMessage());
        }

        if (personList.isEmpty()) {
            throw new NullPointerException("Person not found");
        }

        return personList;
    }


}
