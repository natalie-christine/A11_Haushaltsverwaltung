
import java.util.List;

public interface AddressDAO  {

    public List<Address> listAddresses();

    public void createAddress( String houseAddress, int number, int plz, String place);

    public void deleteAddress(Address deleteAddress);

    public List<Address> searchAddress(String searchAddress);

    void updateAddress(int addressID, String newStreet, int number, int newPlz, String newPlace);
}
