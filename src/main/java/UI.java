
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static java.time.format.DateTimeFormatter.ofPattern;

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
    private final HouseholdManager householdManager;

    public UI(PersonManager personManager, PetManager petManager, AddressManager addressManager, HouseholdManager householdManager) {
        this.personManager = personManager;
        this.petManager = petManager;
        this.addressManager = addressManager;
        this.householdManager = householdManager;
    }


    public boolean evaluateMainMenuInput() throws InvalidPersonNameException {
        boolean play = true;
        printOption(choices);
        Scanner sc = new Scanner(System.in);


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
                            System.out.println("Name: ");
                            String newName1 = checkName(sc.nextLine());
                            System.out.println(" ");
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
                            System.out.println(" ");
                            System.out.println("Nachname: ");
                            String newLastname2 = checkName(sc.nextLine());
                            System.out.println(" ");
                            System.out.println("Geschlecht: ");
                            String newGender2 = sc.nextLine();
                            System.out.println(" ");
                            System.out.println("Geburtsdatum: (zb. 16/08/1996)");
                            LocalDate newBirthday2 = LocalDate.parse(sc.next(), ofPattern("dd/MM/yyyy"));

                            personManager.createPerson(newName2, newLastname2, newGender2, newBirthday2);
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
                String deletePerson = sc.next();

                List<Person> deletePersonList = personManager.searchPerson(deletePerson);
                if (deletePersonList.isEmpty()) {
                    System.out.println("Person nicht gefunden");
                } else {
                    System.out.println("Welche Person soll gelöscht werden? (Index eingeben, 0 beendet)");
                    for (int i = 0; i < deletePersonList.size(); i++) {
                        System.out.println((i + 1) + ". " + deletePersonList.get(i));
                    }
                    int userChoice = sc.nextInt();
                    if (userChoice == 0) {
                        break;
                    } else {
                        Person personToDelete = deletePersonList.get(userChoice - 1);
                        personManager.deletePerson(personToDelete);
                    }
                }
                break;


            case 3:
                System.out.println("Hier kannst du eine Person Bearbeiten");
                System.out.println("Gebe den Namen ein:");

                String updatePerson = sc.next();

                List<Person> searchPerson1 = personManager.searchPerson(updatePerson);
                if (searchPerson1 == null) {
                    System.out.println("Person nicht gefunden");
                } else {
                    System.out.println("Welche Person soll bearbeitet werden? (Index eingeben, 0 beendet)");

                    for (int i = 0; i < searchPerson1.size(); i++) {
                        System.out.println((i + 1) + ". " + searchPerson1.get(i));
                    }
                    int userChoice = sc.nextInt();
                    Person personToUpdate = searchPerson1.get(userChoice - 1);

                    int id = personToUpdate.getId();
                    System.out.println("Name: ");
                    String newName3 = checkName(sc.next());
                    System.out.println("Nachname: ");
                    String newLastname3 = checkName(sc.next());
                    System.out.println("Geschlecht: ");
                    String newGender3 = sc.next();
                    System.out.println("Geburtsdatum: (zb. 16/08/1996)");
                    LocalDate newBirthday3 = LocalDate.parse(sc.next(), ofPattern("dd/MM/yyyy"));

                    personManager.updatePerson(id, newName3, newLastname3, newGender3, newBirthday3);
                }
                break;


            case 4:
                System.out.println("Haustiere werden angezeigt");
                petManager.printPetList();
                break;

            case 5:
                System.out.println("Haustier erstellen");

                try {
                    System.out.println("Tierart: ");
                    String animalSpecies = checkName(sc.next());
                    System.out.println("Name: ");
                    String name = checkName(sc.next());
                    System.out.println("Geschlecht: ");
                    String gender = sc.next();
                    System.out.println("Geburtsdatum: (zb. 16/08/1996)");
                    LocalDate birthday = LocalDate.parse(sc.next(), ofPattern("dd/MM/yyyy"));

                    petManager.createPet(animalSpecies, name, gender, birthday);
                    System.out.println("Haustier wurde erstellt");

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Bitte eine entsprechende Eingabe treffen!");
                }
                break;


            case 6:
                System.out.println("Hier kannst du eine Haustier entfernen:");
                System.out.println("Welches Haustier soll entfernt werden?");
                String deletePet = sc.next();

                List<Pet> deletePetList = petManager.searchPet(deletePet);
                if (deletePetList.isEmpty()) {
                    System.out.println("Haustier nicht gefunden");
                } else {
                    for (int i = 0; i < deletePetList.size(); i++) {
                        System.out.println((i + 1) + ". " + deletePetList.get(i));
                    }
                    System.out.println("Welche Haustier soll gelöscht werden? (Index eingeben)");

                    int userChoice = sc.nextInt();
                    if (userChoice == 0) {
                        break;
                    } else {
                        Pet petToDelete = deletePetList.get(userChoice - 1);
                        petManager.removePet(petToDelete);
                    }

                }
                break;

            case 7:
                System.out.println("Hier kannst du ein Haustier bearbeiten");
                System.out.println("Welches Haustier soll entfernt werden?");
                String updatePet = sc.next();

                List<Pet> updatePetList = petManager.searchPet(updatePet);
                if (updatePetList.isEmpty()) {
                    System.out.println("Haustier nicht gefunden");
                } else {
                    System.out.println("Welche Haustier soll bearbeitet werden? (Index eingeben)");
                    for (int i = 0; i < updatePetList.size(); i++) {
                        System.out.println((i + 1) + ". " + updatePetList.get(i));
                    }
                    int userChoice = sc.nextInt();
                    Pet petToUpdate = updatePetList.get(userChoice - 1);

                    int id = petToUpdate.getId();
                    System.out.println("Tierart: ");
                    String animalSpecies = checkName(sc.next());
                    System.out.println("Name: ");
                    String name = checkName(sc.next());
                    System.out.println("Geschlecht: ");
                    String gender = sc.next();
                    System.out.println("Geburtsdatum: (zb. 16/08/1996)");
                    LocalDate birthday = LocalDate.parse(sc.next(), ofPattern("dd/MM/yyyy"));

                    petManager.updatePet(id, animalSpecies, name, gender, birthday);
                }


                break;

            case 8:
                System.out.println("Haushalte werden angezeigt");
                householdManager.listHouseholds();

                break;
            case 9:
                System.out.println("Erstelle einen neuen Haushalt");
                System.out.println("Name des neues Haushalts");
                String householdName = sc.next();
                System.out.println("An welcher Adresse soll der Haushalt erstellt werden?");
                String householdAddress = sc.next();

                List<Address> householdList = addressManager.searchAddress(householdAddress);
                System.out.println("Bitte gib die Stadt oder Straße ein");
                if (householdList.isEmpty()) {
                    System.out.println("Adresse nicht gefunden");
                } else {
                    for (int i = 0; i < householdList.size(); i++) {
                        System.out.println((i + 1) + ". " + householdList.get(i));
                    }
                    System.out.println("An welcher Adresse soll der Haushalt erstellt werden? (Index eingeben)");


                    int userChoice = sc.nextInt();
                    if (userChoice == 0) {
                        break;
                    } else {
                        Address addressToHousehold = householdList.get(userChoice - 1);

                        householdManager.createHousehold(householdName,addressToHousehold.getId());

                    }

                }


                break;

            case 10:
                System.out.println("Haushalt entfernen");

                break;

            case 11:

                System.out.println("Welcher Haushalt soll bearbeitet werden? (Haushaltsname oder ID) ");

                String searchHousehold1 = sc.next();
                Household householdToUpdate;
                
                List<Household> householdList2 = householdManager.searchHousehold(searchHousehold1);
                if (householdList2.isEmpty()) {
                    System.out.println("Haushalt nicht gefunden");
                } else {
                    System.out.println("Welcher Haushalt soll bearbeitet werden?");
                    for (int i = 0; i < householdList2.size(); i++) {
                        System.out.println((i + 1) + ". " + householdList2.get(i));
                    }
                }

                int userChoice3 = sc.nextInt();
                if (userChoice3 == 0) {
                    break;
                } else {
                   householdToUpdate = householdList2.get(userChoice3 - 1);
                }
                int userUpdatingHousehold;
                do {
                    System.out.println("1. Person hinzufügen");
                    System.out.println("2. Person entfernen");
                    System.out.println("3. Haustier hinzufügen");
                    System.out.println("4. Haustier entfernen");
                    System.out.println("5. Haushaltsnamen ändern");

                    System.out.println("0. Beenden");
                    System.out.print("Bitte auswählen: ");
                     userUpdatingHousehold = sc.nextInt();

                    switch (userUpdatingHousehold) {
                        case 1:
                            System.out.println("Gebe den Namen ein:");
                            String personName = sc.next();

                            List<Person> searchPerson = personManager.searchPerson(personName);
                            if (searchPerson == null) {
                                System.out.println("Person nicht gefunden");
                            } else {
                                System.out.println("Welche Person soll dem Haushalt hinzugefügt werden? (Index eingeben)");
                                for (int i = 0; i < searchPerson.size(); i++) {
                                    System.out.println((i + 1) + ". " + searchPerson.get(i));
                                }
                            int userChoice = sc.nextInt();
                            if (userChoice == 0) {
                                break;
                            } else {
                                Person addPersonToHousehold = searchPerson.get(userChoice - 1);
                                householdManager.addHouseholdMember(householdToUpdate, addPersonToHousehold);
                            }
                    }

                            break;
                        case 2:
                            System.out.println("Gebe den Namen ein:");
                            String personName2 = sc.next();

                            List<Person> searchPerson2 = personManager.searchPerson(personName2);
                            if (searchPerson2 == null) {
                                System.out.println("Person nicht gefunden");
                            } else {
                                System.out.println("Welche Person soll dem Haushalt entfernt werden? (Index eingeben)");
                                for (int i = 0; i < searchPerson2.size(); i++) {
                                    System.out.println((i + 1) + ". " + searchPerson2.get(i));
                                }

                                int userChoice4 = sc.nextInt();
                                if (userChoice4 == 0) {
                                    break;
                                } else {
                                    Person removePersonFromHousehold = searchPerson2.get(userChoice4 - 1);
                                    householdManager.removeHouseholdMember(householdToUpdate, removePersonFromHousehold);
                                }
                            }
                           
                            break;
                        case 3:
                            System.out.println("Gebe den Namen ein:");

                            String petName = sc.next();

                            List<Pet> addingPetList = petManager.searchPet(petName);
                            if (addingPetList.isEmpty()) {
                                System.out.println("Haustier nicht gefunden");
                            } else {
                                System.out.println("Welches Haustier soll zum Haushalt hinzugefügt werden?");
                                for (int i = 0; i < addingPetList.size(); i++) {
                                    System.out.println((i + 1) + ". " + addingPetList.get(i));
                                }
                                int userChoice = sc.nextInt();
                                if (userChoice == 0) {
                                    break;
                                } else {
                                    Pet addPetToHousehold = addingPetList.get(userChoice - 1);
                                    householdManager.addHouseholdPet(householdToUpdate,addPetToHousehold);
                                }
                            }

                            break;
                        case 4:
                            System.out.println("Gebe den Namen ein:");

                            String removingPet = sc.next();

                            List<Pet> removingPetList = petManager.searchPet(removingPet);
                            if (removingPetList.isEmpty()) {
                                System.out.println("Haustier nicht gefunden");
                            } else {
                                System.out.println("Welches Haustier soll vom Haushalt entfernt werden?");
                                for (int i = 0; i < removingPetList.size(); i++) {
                                    System.out.println((i + 1) + ". " + removingPetList.get(i));
                                }
                                int userChoice = sc.nextInt();
                                if (userChoice == 0) {
                                    break;
                                } else {
                                    Pet removingFromHousehold = removingPetList.get(userChoice - 1);
                                    householdManager.removeHouseholdPet(householdToUpdate,removingFromHousehold);
                                }
                            }

                            break;
                        case 5:
                            System.out.println("Wie soll der Haushalt benannt werden?");
                            String newhouseholdName = sc.next();
                            householdManager.changeHouseholdName(householdToUpdate, newhouseholdName );
                            break;

                        case 0:
                            System.out.println("Beenden");
                            break;
                        default:
                            System.out.println("Ungültige Eingabe");
                    }
                } while (userUpdatingHousehold != 0);

                break;


            case 12:
                System.out.println("Adressen werden angezeigt");
                addressManager.printAddress();
                System.out.println();
                break;

            case 13:
                System.out.println("Bitte geben sie hier eine neue Adresse ein: ");

                try {
                    System.out.println("Straße:");
                    String newStreet = checkName(sc.next());
                    System.out.println("Hausnummer:");
                    int number = checkData(sc.next());
                    System.out.println("Postleitzahl: ");
                    int newPlz = checkData(sc.next());
                    System.out.println("Ort");
                    String newPlace = checkName(sc.next());

                    addressManager.createAddress(newStreet, number, newPlz, newPlace);

                    System.out.println("Adresse wurde hinzugefügt");

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Bitte eine entsprechende Eingabe treffen!");
                }

                break;

            case 14:
                System.out.println("Hier kannst du eine Adresse entfernen:");
                System.out.println("Welche Adresse soll entfernt werden?");
                String deleteAddress = sc.next();

                List<Address> deleteAddressList = addressManager.searchAddress(deleteAddress);
                if (deleteAddressList.isEmpty()) {
                    System.out.println("Adresse nicht gefunden");
                } else {
                    for (int i = 0; i < deleteAddressList.size(); i++) {
                        System.out.println((i + 1) + ". " + deleteAddressList.get(i));
                    }
                    System.out.println("Welche Adresse soll gelöscht werden? (Index eingeben)");

                    int userChoice = sc.nextInt();
                    if (userChoice == 0) {
                        break;
                    } else {
                        Address addressToDelete = deleteAddressList.get(userChoice - 1);
                        addressManager.removeAddress(addressToDelete);
                    }

                }
                break;

            case 15:
                System.out.println("Hier kannst du eine Adresse bearbeiten");

                System.out.println("Welche Adresse möchtest du bearbeiten?");
                String updateAddress = sc.next();

                List<Address> updateAddressList = addressManager.searchAddress(updateAddress);
                if (updateAddressList.isEmpty()) {
                    System.out.println("Adresse nicht gefunden");
                } else {
                    for (int i = 0; i < updateAddressList.size(); i++) {
                        System.out.println((i + 1) + ". " + updateAddressList.get(i));
                    }

                    System.out.println("Welche Adresse soll bearbeitet werden? (Index eingeben)");
                    int userChoice = sc.nextInt();
                    Address addressToUpdate = updateAddressList.get(userChoice - 1);

                    int addressID = addressToUpdate.getId();
                    System.out.println("Straße:");
                    String newStreet = checkName(sc.next());
                    System.out.println("Hausnummer:");
                    int number = checkData(sc.next());
                    System.out.println("Postleitzahl: ");
                    int newPlz = checkData(sc.next());
                    System.out.println("Ort");
                    String newPlace = checkName(sc.next());

                    addressManager.updateAddress(addressID, newStreet, number, newPlz, newPlace);

                    System.out.println("Adresse wurde geändert.");

                }
                break;

            case 16:
                System.out.println("Hier kannst du nach einer Person suchen:");
                System.out.println("Gebe den Namen ein:");

                String personName = sc.next();

                List<Person> searchPerson = personManager.searchPerson(personName);
                if (searchPerson == null) {
                    System.out.println("Person nicht gefunden");
                } else {
                    for (Person foundPerson : searchPerson) {
                        System.out.println(foundPerson);
                    }
                }
                break;


            case 17:
                System.out.println("Hier kannst du nach einem Haustier suchen:");
                System.out.println("Gebe den Namen ein:");

                String petName = sc.next();

                List<Pet> petList = petManager.searchPet(petName);
                if (petList.isEmpty()) {
                    System.out.println("Haustier nicht gefunden");
                } else {
                    for (int i = 0; i < petList.size(); i++) {
                        System.out.println((i + 1) + ". " + petList.get(i));
                    }
                }
                break;

            case 18:
                System.out.println("Hier kannst du nach einem Haushalt suchen:");
                System.out.println("Nach welchem Haushalt möchtest du suchen? (Haushaltsname oder ID)");

                String searchHousehold = sc.next();

                List<Household> householdList3 = householdManager.searchHousehold(searchHousehold);
                if (householdList3.isEmpty()) {
                    System.out.println("Haushalt nicht gefunden");
                } else {
                    System.out.println("Welcher Haushalt soll bearbeitet werden?");
                    for (int i = 0; i < householdList3.size(); i++) {
                        System.out.println((i + 1) + ". " + householdList3.get(i));
                    }



                }
                break;

            case 19:
                System.out.println("Hier kannst du nach einer Adresse suchen:");

                System.out.println("Nach welcher Adresse möchtest du suchen?");

                String searchAddress = sc.next();

                List<Address> addressList = addressManager.searchAddress(searchAddress);
                if (addressList.isEmpty()) {
                    System.out.println("Adresse nicht gefunden");
                } else {
                    for (int i = 0; i < addressList.size(); i++) {
                        System.out.println((i + 1) + ". " + addressList.get(i));
                    }
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
