public class Main {


    public static void main(String[] args) throws InvalidPersonNameException {

        boolean play;

        PersonManager personManager = new PersonManager();
        PetManager petManager = new PetManager();
        AddressManager addressManager = new AddressManager();
        HouseholdManager householdManager = new HouseholdManager();

        //Init.init(personManager,petManager, addressManager);

        UI ui = new UI(personManager, petManager, addressManager, householdManager);

        do {
            play = ui.evaluateMainMenuInput();
        } while (play);
    }
}
