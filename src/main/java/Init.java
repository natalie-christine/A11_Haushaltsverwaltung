import java.time.LocalDate;

public class Init {
    public  static void init (PersonManager personManager) {
        personManager.createPerson("Natalie", "Schöfecker", LocalDate.parse("16/08/1996",PersonManager.birthdayFormatter),Gender.FEMALE, new Address("Hasnerstraße",29, 4020, "Linz"));
        Person Ivan = personManager.createPerson("Ivan", "Safonov", LocalDate.parse("21/12/1997",PersonManager.birthdayFormatter),Gender.MALE,new Address("Muterweg", 7,4020,"Linz"));
        personManager.createPerson("Daniel", "Cojocariu", LocalDate.parse("29/09/2002", PersonManager.birthdayFormatter), Gender.MALE, new Address("Planetenweg", 95, 5020, "Salzburg"));
        Person Martin = personManager.createPerson("Martin", "Friesenecker", LocalDate.parse("07/12/1988",PersonManager.birthdayFormatter),Gender.MALE, new Address("Milchstraße",4, 1010, "Wien"));
        Person Matthias = personManager.createPerson("Matthias", "Schöfecker", LocalDate.parse("05/12/2020",PersonManager.birthdayFormatter),Gender.MALE,new Address("Sesamenstraße", 1, 9020, "Insbruck"));
        Person Thomas = personManager.createPerson("Thomas", "Fröhlich", LocalDate.parse("16/07/1995",PersonManager.birthdayFormatter),Gender.MALE, new Address("Cometenweg", 8, 3587, "Irgentwo"));


    }
}
