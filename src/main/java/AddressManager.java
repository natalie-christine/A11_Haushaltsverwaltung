import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressManager {

 AddressDatabaseMySQL addressDatabaseManager = new AddressDatabaseMySQL();
    private Map<String, Address> addressList = new HashMap<>();
    public void createAddress( String houseAddress, int number, int plz, String place) {
        addressDatabaseManager.createAddress(houseAddress, number, plz, place);
    }


    public void printAddress() {
        List<Address> addressList = addressDatabaseManager.listAddresses();
        for (Address address : addressList) {
            System.out.println(address);
        }
    }

    public List<Address> searchAddress(String searchAddress) {
        List<Address> addressList = addressDatabaseManager.searchAddress(searchAddress);
        return addressList;
    }

    public void removeAddress (Address deleteAddress) {
       addressDatabaseManager.deleteAddress(deleteAddress);
    }



    public void updateAddress(int addressID, String newStreet, int number, int newPlz, String newPlace) {
        addressDatabaseManager.updateAddress(addressID, newStreet, number, newPlz, newPlace);
    }




}
