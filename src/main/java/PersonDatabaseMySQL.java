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
                Integer householdsID = resultSet.getInt("householdsID");
                Person person = new Person(id, firstName, lastName, gender, birthday, householdsID);
                personList.add(person);
            }
        } catch (SQLException e) {
            System.err.println("Error listing persons: " + e.getMessage());
        }

        return personList;
    }

/*    public void createPerson( Person person) {
        String sql = "INSERT INTO Personen ( Name, Lastname) VALUES ( ?, ?)";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, person.getName());
            statement.setString(2, person.getLastname());


            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();

            System.out.println("Person created successfully!");
            //return rs.getInt(1);
        } catch (SQLException e) {
            System.err.println("Error creating person: " + e.getMessage());
        }
    }*/

    public void createPerson( Person person) {
        String sql = "INSERT INTO Personen (Name, Lastname, Gender, Birthday) VALUES (?, ?, ?, ?)";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, person.getName());
            statement.setString(2, person.getLastname());
            statement.setString(3, person.getGender());
            statement.setDate(4, Date.valueOf(person.getBirthday().format(birthdayFormatter)));


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
                    int householdID = resultSet.getInt("householdsID");
                    Person person = new Person(id, firstName, lastName, gender, birthday, householdID);
                    personList.add(person);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error searching for person: " + e.getMessage());
        }

        if (personList.isEmpty()) {
            throw new NullPointerException("Pet not found");
        }

        return personList;
    }

    @Override
    public void updatePerson(Person person) {
        String sql = "UPDATE personen SET Name = ?, Lastname = ?, Birthday = ?, Gender = ? WHERE ID = ?";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, person.getName());
            statement.setString(2, person.getLastname());
            statement.setDate(4, Date.valueOf(person.getBirthday().format(birthdayFormatter)));
            statement.setString(4, person.getGender());
            statement.setInt(5, person.getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Person successfully updated.");
            } else {
                System.out.println("Error updating person: No rows affected.");
            }

        } catch (SQLException e) {
            System.err.println("Error updating person: " + e.getMessage());
        }
    }


}
