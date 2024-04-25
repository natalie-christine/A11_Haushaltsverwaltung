import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UI {
    final String[][] choices = {

//{ IDX, choice,},
            {"0", "Personen Anzeigen"},
            {"1", "Person Hinzufügen"},
            {"2", "Person Entfernen"},
            {"3", "Person Bearbeiten"},


            {"4", "Haustiere Anzeigen"},
            {"5", "Haustier Hinzufügen"},
            {"6", "Haustier Entfernen"},
            {"7", "Haustier Bearbeiten"},

            {"8", "Haushalte Anzeigen"},
            {"9", "Haushalt Hinzufügen"},
            {"10", "Haushalt Entfernen"},
            {"11", "Haushalt Bearbeiten"},

            {"12", "Adressen Anzeigen"},
            {"13", "Adresse Hinzufügen"},
            {"14", "Adresse Entfernen"},
            {"15", "Adresse Bearbeiten"},


            {"16", "Nach Person Suchen"},
            {"17", "Nach Haustier Suchen"},
            {"18", "Nach Haushalte Suchen"},
            {"19", "Nach Adresse Suchen"},

            {"20", "Exit"},

    };
    private final PersonManager personManager;
    private final PetManager petManager;
    private final AddressManager addressManager;

    public UI(PersonManager personManager, PetManager petManager, AddressManager addressManager) {
        this.personManager = personManager;
        this.petManager = petManager;
        this.addressManager = addressManager;

    }


    public boolean evaluateMainMenuInput() {
        boolean play = true;
        printOption(choices);
        Scanner sc = new Scanner(System.in);
        Person person;


        int userInput = sc.nextInt();

        switch (userInput) {
            case 0:
                System.out.println("Deine Personen werden angezeigt");
                personManager.printPersonList();
                System.out.println();
                break;

            case 1:
                System.out.println("Hier kannst du eine neue Person hinzufügen:");
                System.out.println("Welche Daten sind vorhanden? (1 oder 2) ");
                System.out.println("1. Vornamen und Nachnamen");
                System.out.println("2. Vornamen, Nachnamen, Geschlecht und Geburtsdatum");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        try {

                            int newID = PersonManager.getID();
                            System.out.println("Name: ");
                            String newName1 = checkName(sc.nextLine());
                            System.out.println(" ");
                            System.out.println("Nachname: ");
                            String newLastname1 = checkName(sc.nextLine());

                            personManager.createPerson(newID, newName1, newLastname1);
                            System.out.println("Person wurde hinzugefügt");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Bitte eine entsprechende Eingabe treffen!");
                        }
                        break;
                    case 2:

                        try {
                            int newID = PersonManager.getID();
                            System.out.println("Name: ");
                            String newName2 = checkName(sc.nextLine());
                            System.out.println(" ");
                            System.out.println("Nachname: ");
                            String newLastname2 = checkName(sc.nextLine());
                            System.out.println(" ");
                            System.out.println("Geschlecht: ");
                            String newGender2 = sc.nextLine();
                            System.out.println(" ");
                            System.out.println("Geburtsdatum: (zb. 16/08/1996)");
                            LocalDate newBirthday2 = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                            personManager.createPerson(newID, newName2, newLastname2, newGender2, newBirthday2);
                            System.out.println("Person wurde hinzugefügt");

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Bitte eine entsprechende Eingabe treffen!");
                        }
                        break;
                    default:
                        System.out.println("Ungültige Eingabe");
                        break;
                }
                break;


            case 2:
                System.out.println("Hier kannst du eine Person entfernen:");
                System.out.println("Welches Person soll entfernt werden?");
                String deletePerson = sc.nextLine();

                person = personManager.searchPerson(deletePerson);
                if (person == null) {
                    System.out.println("Person nicht gefunden");
                } else {
                    personManager.removePerson(deletePerson);
                    System.out.println("Person gelöscht");
                }


            case 3:


                break;
            case 4:
                System.out.println("Haustiere werden angezeigt");
                petManager.printPetList();
                System.out.println();
                break;
            case 8:

                break;

            case 5:

                System.out.println("Haustier erstellen");

                try {
                    int newID = PetManager.getID();
                    System.out.println("Tierart: ");
                    String animalSpecies = checkName(sc.next());
                    System.out.println("Name: ");
                    String name = checkName(sc.next());
                    System.out.println("Geschlecht: ");
                    String gender = sc.next();
                    System.out.println("Geburtsdatum: (zb. 16/08/1996)");
                    LocalDate birthday = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    petManager.createPet(newID, animalSpecies,name, gender,birthday);
                    System.out.println("Haustier wurde erstellt");

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Bitte eine entsprechende Eingabe treffen!");
                }
                break;


            case 12:
                System.out.println("Adressen werden angezeigt");
                addressManager.printAddress();
                System.out.println();
                break;

            case 13:
                System.out.println("Bitte geben sie hier eine neue Adresse ein: ");

                try {
                    int newAddressID = Address.getAddressID();
                    System.out.println("Straße:");
                    String newStreet = checkName(sc.next());
                    System.out.println("Hausnummer:");
                    int number = checkData(sc.next());
                    System.out.println("Postleitzahl: ");
                    int newPlz = checkData(sc.next());
                    System.out.println("Ort");
                    String newPlace = checkName(sc.next());

                    AddressManager addressManager = new AddressManager();
                    addressManager.createAddress(newAddressID, newStreet, number, newPlz, newPlace);

                    System.out.println("Adresse wurde hinzugefügt");

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Bitte eine entsprechende Eingabe treffen!");
                }

                break;


            case 16:
                System.out.println("Hier kannst du nach einer Person suchen:");
                System.out.println("Gebe den Namen ein:");

                String personName = sc.nextLine();

                Person searchPerson = personManager.searchPerson(personName);
                if (searchPerson == null) {
                    System.out.println("Person nicht gefunden");
                } else {
                    System.out.println(searchPerson);
                }
                break;

            case 20:
                System.out.println("Auf Wiedersehen! ");
                play = false;
                break;

            default:

                System.out.println("Ungültige Eingabe");

        }
        return play;
    }

    public String checkName(String userInput) throws InvalidPersonNameException {

        char[] userinputs = userInput.toCharArray();
        for (int i = 0; i < userInput.length(); i++) {
            if ((userinputs[i] >= '0' && userinputs[i] <= '9') || (userInput.matches(".*\\d.*"))) {
                throw new InvalidPersonNameException();
            }

        }
        return userInput;

    }

    public int checkData(String userInput) {
        char[] userinputs = userInput.toCharArray();

        for (int i = 0; i < userInput.length(); i++) {
            if (!(userinputs[i] >= '0' && userinputs[i] <= '9')) {
                throw new NumberFormatException("Nö!");
            }
        }
        return Integer.parseInt(userInput);
    }


    private void printOption(String[][] choices) {

        String formatter = "\t%s \t %s";

        System.out.println("Was möchtest du tun?");

        for (String[] choice : choices) {
            System.out.printf(formatter, choice[0], choice[1]);
            System.out.println();
        }
    }
}
