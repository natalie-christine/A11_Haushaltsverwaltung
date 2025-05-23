import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/haushaltsverwaltung";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    DatabaseQuery databaseQuery = new DatabaseQuery();

    public List<Person> PeopleInHouseholdWithPets(Household selectHousehold) {
       int householdID =  selectHousehold.getID();
        List<Person> peopleWithPets = databaseQuery.getPeopleInHouseholdWithPets(householdID);

        return peopleWithPets;
    }
}