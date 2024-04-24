import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class PersonManager {
    private Map<String, Person> personList = new HashMap<>();

    private void addPerson(Person person) {
        personList.put(person.getName(), person);

    }

    public Person createPerson( String name, String lastname) {
        Person newPerson = new Person(name, lastname);
        addPerson(newPerson);
        return newPerson;



    }
    public Person createPerson( String name, String lastname, LocalDate birthday, Gender gender) {
        Person newPerson = new Person(name, lastname, birthday, gender);
        addPerson(newPerson);
        return newPerson;
    }
    public Person createPerson( String name, String lastname, LocalDate birthday, Gender gender, Address address) {
        Person newPerson = new Person(name, lastname, birthday, gender, address);
        addPerson(newPerson);
        return newPerson;
    }

    public static final DateTimeFormatter birthdayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public void removePerson (String deletePerson) {

        personList.remove(deletePerson);
    }

    public void printPersonList() {

        for (Person person : personList.values()) {
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

