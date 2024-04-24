import java.time.LocalDate;
import java.util.Scanner;

public class UI {
    final String[][] choices = {

//{ IDX, choice,},
            {"0", "Personen anzeigen"},
            {"1", "Person Hinzufügen"},
            {"2", "Person Entfernen"},
            {"3", "Platzhalter"},
            {"4", "Platzhalter"},
            {"5", "Nach Person Suchen"},
            {"6", "Exit"},

    };
    private final PersonManager personManager;

    public UI(PersonManager personManager) {
        this.personManager = personManager;
    }

    public boolean evaluateMainMenuInput() {
        boolean play = true;
        printOption(choices);
        Scanner sc = new Scanner(System.in);
        Person person;


        char userInput = sc.nextLine().charAt(0);

        switch (userInput) {
            case '0':
                System.out.println("Deine Personen werden angezeigt");
                personManager.printPersonList();
                System.out.println();
                break;

            case '1':
                System.out.println("Hier kannst du eine neue Person hinzufügen:");
                System.out.println("Welche Daten sind vorhanden? (1, 2 oder 3) ");
                System.out.println("1. Vornamen und Nachnamen");
                System.out.println("2. Vornamen, Nachnamen, Geschlecht und Geburtsdatum, Adresse");
                System.out.println("3. Vornamen, Nachnamen, Geschlecht und Geburtsdatum");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        try {
                            System.out.println("Name: ");
                            String newName1 = checkName(sc.nextLine());
                            System.out.println("Nachname: ");
                            String newLastname1 = checkName(sc.nextLine());
                            personManager.createPerson(newName1, newLastname1);
                            System.out.println("Person wurde hinzugefügt");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Bitte eine entsprechende Eingabe treffen!");
                        }

                        break;
                    case 2:
                        try {
                            System.out.println("Name: ");
                            String newName2 = checkName(sc.nextLine());
                            System.out.println("Nachname: ");
                            String newLastname2 = checkName(sc.nextLine());
                            System.out.println("Geburtsdatum: (zb. 16/08/1996)");
                            LocalDate newBirthday2 = LocalDate.parse(sc.nextLine());
                            System.out.println("Geschlecht: ");
                            Gender newGender2 = Gender.valueOf(sc.nextLine().toUpperCase());
                            System.out.println("Straße:");
                            String newStreet = checkName(sc.nextLine());
                            System.out.println("Hausnummer:");
                            int number = checkData(sc.nextLine());
                            System.out.println("Postleitzahl: ");
                            int newPlz = checkData(sc.nextLine());
                            System.out.println("Ort");
                            String newPlace = checkName(sc.nextLine());
                            Address address = new Address(newStreet, number, newPlz, newPlace);

                            personManager.createPerson(newName2, newLastname2, newBirthday2, newGender2, address);
                            System.out.println("Person wurde hinzugefügt");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Bitte eine entsprechende Eingabe treffen!");
                        }

                        break;

                    case 3:
                        try {
                            System.out.println("Name: ");
                            String newName3 = checkName(sc.nextLine());
                            System.out.println("Nachname: ");
                            String newLastname3 = checkName(sc.nextLine());
                            System.out.println("Geburtsdatum: (zb. 16/08/1996)");
                            LocalDate newBirthday3 = LocalDate.parse(sc.nextLine());
                            System.out.println("Geschlecht: ");
                            Gender newGender3 = Gender.valueOf(sc.nextLine().toUpperCase());
                            personManager.createPerson(newName3, newLastname3, newBirthday3, newGender3);
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


            case '2':
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


            case '3':


                break;

            case '4':

                break;

            case '5':
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

            case '6':
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
