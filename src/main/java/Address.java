public class Address {

    private String street;

    private int housenumber;
    private int plz;
    private String place;
    private int addressID;


    public Address(int addressID, String houseAddress, int number, int plz, String place) {
        this.addressID= addressID;
        this.street = houseAddress;
        this.housenumber = number;
        this.plz = plz;
        this.place = place;

    }


    @Override
    public String toString() {
        return String.format( "%03d %-20s %-12d %-6d %-6s",addressID, street, housenumber, plz, place);
    }


    public int getId() {
        return addressID;
    }
}

