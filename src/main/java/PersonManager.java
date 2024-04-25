import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonManager {
    private Map<String, Person> personList = new HashMap<>();

    DatabaseManager databaseManager = new DatabaseManager();

    public static int getID() {
       int newID =  Person.getIdCounter();
        return newID;
    }

    private void addPerson(Person person) {
        personList.put(person.getName(), person);

    }

    public Person createPerson(int id, String name, String lastname) {
        Person newPerson = new Person(id, name, lastname);
        addPerson(newPerson);

        databaseManager.createPerson(id,name, lastname);
        return newPerson;



    }
    public Person createPerson(int id, String name, String lastname,  String gender,LocalDate birthday) {
        Person newPerson = new Person(id, name, lastname,gender, birthday );
        addPerson(newPerson);

        databaseManager.createPerson(id, name, lastname, gender, birthday);

        return newPerson;
    }


    public static final DateTimeFormatter birthdayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public void removePerson (String deletePerson) {

        personList.remove(deletePerson);
    }

    public void printPersonList() {

        List<Person> personList = databaseManager.listPersons();

        for (Person person : personList) {
            System.out.println(person);
        }
    }

    public Person searchPerson(String personName) {
        Person person = personList.getOrDefault(personName, null);
        if (person == null) {
            throw new NullPointerException("Person not found");
        }
        return person;
    }
}

