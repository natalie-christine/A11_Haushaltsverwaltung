import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HouseholdDatabaseMySQL {


    public void createHousehold(String householdName, int addressID) {

        String sql = "INSERT INTO households ( Name, AddressID) VALUES (?, ?)";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement insertStatement = connection.prepareStatement(sql)) {

            insertStatement.setString(1, householdName);
            insertStatement.setInt(2, addressID);

            int rowsAffected = insertStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Household created successfully!");
            } else {
                System.out.println("Error creating household: No rows affected.");
            }
        } catch (SQLException e) {
            System.err.println("Error creating household: " + e.getMessage());
        }
    }


    public void addPersonToHousehold(int householdID, int personID) {

        String SQL = "UPDATE personen SET householdsID = ? WHERE ID = ?";
        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            // Weise die Person dem Haushalt zu
            statement.setInt(1, householdID);
            statement.setInt(2, personID);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Person successfully added to household.");
            } else {
                System.out.println("Error adding person to household: No rows affected.");
            }

        } catch (SQLException e) {
            System.err.println("Error adding person to household: " + e.getMessage());
        }
    }


    public List<Household> listHouseholds() {
        List<Household> householdsList = new ArrayList<>();

        String sql = "SELECT * FROM households";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String householdName = resultSet.getString("name");
                int addressID = resultSet.getInt("addressID");

                Household household = new Household(id, householdName, addressID);
                householdsList.add(household);
            }
        } catch (SQLException e) {
            System.err.println("Error listing persons: " + e.getMessage());
        }

        return householdsList;
    }

    public List<Household> searchHousehold(String name) {
        List<Household> householdList = new ArrayList<>();

        String sql = "SELECT * FROM households WHERE Name = ? OR ID = ?";


        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            try {
                int id = Integer.parseInt(name);
                statement.setString(1, "");
                statement.setInt(2, id);
            } catch (NumberFormatException e) {
                statement.setString(1, name);
                statement.setInt(2, 0);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    int householdID = resultSet.getInt("ID");
                    String householdName = resultSet.getString("Name");
                    int addressID = resultSet.getInt("AddressID");

                    Household household = new Household(householdID, householdName, addressID);
                    householdList.add(household);

                } else {
                    System.out.println("Keine Übereinstimmung");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching household: " + e.getMessage());
        }

        return householdList;
    }

    public void removeFromHousehold(int householdID, int personID) {

        String SQL = "UPDATE personen SET householdsID = NULL WHERE ID = ? AND householdsID = ?";
        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, personID);
            statement.setInt(2, householdID);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Person successfully removed household.");
            } else {
                System.out.println("Error removing person from household: No rows affected.");
            }

        } catch (SQLException e) {
            System.err.println("Error removing person to household: " + e.getMessage());
        }
    }

    public void addPetToHousehold(int householdID, int petID) {

        String SQL = "UPDATE pets SET householdID = ? WHERE ID = ?";
        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, householdID);
            statement.setInt(2, petID);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pet successfully added to household.");
            } else {
                System.out.println("Error adding pet to household: No rows affected.");
            }

        } catch (SQLException e) {
            System.err.println("Error adding pet to household: " + e.getMessage());
        }
    }

    public void removePetFromHousehold(int householdID, int petID) {

        String SQL = "UPDATE pets SET householdID = NULL WHERE ID = ? AND householdID = ?";
        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, petID);
            statement.setInt(2, householdID);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pet successfully removed from household.");
            } else {
                System.out.println("Error removing pet from household: No rows affected.");
            }

        } catch (SQLException e) {
            System.err.println("Error removing from household: " + e.getMessage());
        }
    }

    public void changeHouseholdName(int householdID, String newhouseholdName) {
        String SQL = "UPDATE households SET name = ? WHERE ID = ?";
        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setString(1, newhouseholdName);
            statement.setInt(2, householdID);


            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Household successfully changed.");
            } else {
                System.out.println("Error updating name from household: No rows affected.");
            }

        } catch (SQLException e) {
            System.err.println("Error updating name from household: " + e.getMessage());
        }
    }

    public void addPetToPerson(int householdID, int personenID) {
        String SQL = "UPDATE pets SET personenID = ? WHERE ID = ?";
        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, personenID);
            statement.setInt(2, householdID);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pet successfully added to Person.");
            } else {
                System.out.println("Error adding pet to person: No rows affected.");
            }

        } catch (SQLException e) {
            System.err.println("Error adding pet to person: " + e.getMessage());
        }
    }

    public List<Person> getPeopleInHousehold(int householdID) {
        List<Person> peopleInHousehold = new ArrayList<>();

        String sql = "SELECT * FROM personen WHERE householdsID = ?";

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
                    int householdsId = resultSet.getInt("householdsID");

                    // Erstelle eine neue Person und füge sie der Liste hinzu
                    Person person = new Person(id, name, lastname, gender, birthday, householdsId);
                    peopleInHousehold.add(person);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting people in household: " + e.getMessage());
        }

        return peopleInHousehold;
    }
}





