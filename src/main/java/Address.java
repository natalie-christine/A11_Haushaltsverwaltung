public class Address {

    private String street;
    private int number;
    private int plz;
    private String place;


    public Address(String houseAddress, int number, int plz, String place) {

        this.street = houseAddress;
        this.number = number;
        this.plz = plz;
        this.place = place;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public int getPlz() {
        return plz;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-12d %-6d %-6s", street, number, plz, place);
    }
}
