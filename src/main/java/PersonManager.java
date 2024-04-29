import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonManager {



    PersonDAO personDatabaseManager = new PersonDatabaseMySQL();


    public void createPerson( String name, String lastname) {
        Person person = new Person( name, lastname);
        personDatabaseManager.createPerson(person);
    }
    public void createPerson( String name, String lastname,  String gender,LocalDate birthday) {
        Person person = new Person(name, lastname, gender, birthday);
        personDatabaseManager.createPerson(person);
    }

    public void deletePerson(Person deletePerson) {
      personDatabaseManager.deletePerson(deletePerson);
    }

    public void printPersonList() {

        List<Person> personList = personDatabaseManager.listPersons();

        for (Person person : personList) {
            System.out.println(person);
        }
    }

    public List<Person> searchPerson(String personName) {

        List<Person> personList = personDatabaseManager.searchPerson(personName);
        if (personList.isEmpty()) {
            throw new NullPointerException("Person not found");
        }
        return personList;
    }

public void updatePerson ( int id, String name, String lastname,  String gender,LocalDate birthday) {
       Person person = new Person(id, name, lastname, gender, birthday);
    personDatabaseManager.updatePerson(person);
}
}

