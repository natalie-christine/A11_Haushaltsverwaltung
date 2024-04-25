import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/haushaltsverwaltung";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public void createPerson(int id, String firstName, String lastName) {
        String sql = "INSERT INTO Personen (ID, Name, Lastname) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1,id);
            statement.setString(2, firstName);
            statement.setString(3, lastName);


            statement.executeUpdate();
            System.out.println("Person created successfully!");
        } catch (SQLException e) {
            System.err.println("Error creating person: " + e.getMessage());
        }
    }

        public void createPerson(int ID, String firstName, String lastName, String gender, LocalDate birthday) {
            String sql = "INSERT INTO Personen (ID,Name, Lastname, Gender, Birthday) VALUES (?, ?, ?, ?,?)";

            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1,ID);
                statement.setString(2, firstName);
                statement.setString(3, lastName);
                statement.setString(4, String.valueOf(gender));
                statement.setDate(5, java.sql.Date.valueOf(birthday));



                statement.executeUpdate();
                System.out.println("Person created successfully!");
            } catch (SQLException e) {
                System.err.println("Error creating person: " + e.getMessage());
            }
        }

    public void createPet(int id,String animalSpecies,  String name, String gender, LocalDate birthday) {
        String sql = " INSERT INTO pets(ID, Species, Name, Gender, Birthday )VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.setString(2, animalSpecies);
            statement.setString(3, name);
            statement.setString(4, gender);
            statement.setDate(5, java.sql.Date.valueOf(birthday));



            statement.executeUpdate();
            System.out.println("Pet created successfully!");
        } catch (SQLException e) {
            System.err.println("Error creating pet: " + e.getMessage());
        }
    }

    public List<Pet> listPets() {

        List<Pet> petList = new ArrayList<>();
        String sql = "SELECT * FROM pets";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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

                Pet pet = new Pet(id, species,name, gender,birthday);
                petList.add(pet);


            }
        } catch (SQLException e) {
            System.err.println("Error fetching pets: " + e.getMessage());
        }
        return petList;
    }


    public List<Person> listPersons() {
        List<Person> personList = new ArrayList<>();

        String sql = "SELECT * FROM personen";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String firstName = resultSet.getString("Name");
                String lastName = resultSet.getString("Lastname");
                String gender = resultSet.getString("Gender");
                //LocalDate birthday = resultSet.getDate("Birthday").toLocalDate();
                java.sql.Date sqlDate = resultSet.getDate("Birthday");
                LocalDate birthday = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                Person person = new Person(id,firstName, lastName, gender, birthday);
                personList.add(person);
            }
        } catch (SQLException e) {
            System.err.println("Error listing persons: " + e.getMessage());
        }

        return personList;
    }
    public void createAddress(int addressID,  String houseAddress, int number, int plz, String place) {
        String sql = "INSERT INTO address (ID, Street, number, PLZ, Place) VALUES (?, ?, ?, ?, ?)";


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1,addressID);
            statement.setString(2,houseAddress );
            statement.setInt(3,number );
            statement.setInt(4, plz);
            statement.setString(5, place);


            statement.executeUpdate();
            System.out.println("Person created successfully!");
        } catch (SQLException e) {
            System.err.println("Error address: " + e.getMessage());
        }
    }
    public List<Address> listAddresses() {
        List<Address> addressList = new ArrayList<>();

        String sql = "SELECT * FROM address";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String houseAddress = resultSet.getString("Street");
               int number = resultSet.getInt("Number");
                int plz = resultSet.getInt("PLZ");
                String place = resultSet.getString("Place");

                Address address = new Address(ID, houseAddress, number, plz, place);
                addressList.add(address);
            }
        } catch (SQLException e) {
            System.err.println("Error listing address: " + e.getMessage());
        }

        return addressList;
    }


}
