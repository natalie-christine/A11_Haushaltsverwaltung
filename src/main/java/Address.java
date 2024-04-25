public class Address {
    private static int addressIDCounter = 9;

    private String street;

    private int housenumber;
    private int plz;
    private String place;
    private int addressID;


    public Address(int addressID, String houseAddress, int number, int plz, String place) {
        this.addressID= addressIDCounter++;
        this.street = houseAddress;
        this.housenumber = number;
        this.plz = plz;
        this.place = place;

    }
    public static int getAddressID() { return addressIDCounter++;}

    public String getStreet() {
        return street;
    }

    public int gethousenumber() {
        return housenumber;
    }

    public int getPlz() {
        return plz;
    }

    public String getPlace() {
        return place;
    }


    @Override
    public String toString() {
        return String.format( "%03d %-20s %-12d %-6d %-6s",addressIDCounter, street, housenumber, plz, place);
    }





    }

