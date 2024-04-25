import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PersonDAO {
    public List<Person> listPersons();
    public void createPerson( String firstName, String lastName);
    public void createPerson( String firstName, String lastName, String gender, LocalDate birthday);
    public void deletePerson(Person deletePerson);
    public List<Person> searchPerson(String personName);
}
