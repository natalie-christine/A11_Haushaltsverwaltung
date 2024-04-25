import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDatabaseMySQL implements AddressDAO {
    public List<Address> listAddresses() {
        List<Address> addressList = new ArrayList<>();

        String sql = "SELECT * FROM address";

        try (Connection connection = MySQLConnector.getInstance();
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

    public void createAddress( String houseAddress, int number, int plz, String place) {
        String sql = "INSERT INTO address ( Street, number, PLZ, Place) VALUES (?, ?, ?, ?)";


        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, houseAddress);
            statement.setInt(2, number);
            statement.setInt(3, plz);
            statement.setString(4, place);


            statement.executeUpdate();
            System.out.println("Address created successfully!");
        } catch (SQLException e) {
            System.err.println("Error address: " + e.getMessage());
        }
    }

    public void deleteAddress(Address deleteAddress) {
        String sql = "DELETE FROM address WHERE ID = ?";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, deleteAddress.getId());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Address successfully deleted.");
            } else {
                System.out.println("Error deleting address: No rows affected.");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting address: " + e.getMessage());
        }
    }

    public List<Address> searchAddress(String searchAddress) {

        List<Address> addressList = new ArrayList<>();

        String sql = "SELECT * FROM address WHERE Street like ? OR PLZ like  ? OR Place like  ?";

        try (Connection connection = MySQLConnector.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, searchAddress);
            statement.setString(2, searchAddress);
            statement.setString(3, searchAddress);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String street = resultSet.getString("Street");
                    int number = resultSet.getInt("Number");
                    int plz = resultSet.getInt("PLZ");
                    String place = resultSet.getString("Place");

                    Address address = new Address(id, street, number, plz, place);
                    addressList.add(address);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error searching for address: " + e.getMessage());
        }

        if (addressList.isEmpty()) {
            throw new NullPointerException("Address not found");
        }

        return addressList;
    }
}

