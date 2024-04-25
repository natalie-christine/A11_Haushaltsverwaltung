import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressManager {

    DatabaseManager databaseManager = new DatabaseManager();
    private Map<String, Address> addressList = new HashMap<>();
    public Address createAddress(int addressID,  String houseAddress, int number, int plz, String place) {
        Address newAddress = new Address(addressID, houseAddress, number, plz, place);
        addAddress(newAddress);

        databaseManager.createAddress(addressID, houseAddress, number, plz, place);
        return newAddress;
    }

    private void addAddress(Address newAddress) {
        addressList.put(newAddress.getStreet(), newAddress);
    }


    public void printAddress() {
        List<Address> addressList = databaseManager.listAddresses();

        for (Address address : addressList) {
            System.out.println(address);
        }

    }
}
