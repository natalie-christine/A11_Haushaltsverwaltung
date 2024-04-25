public class Main {


    public static void main(String[] args) {

        boolean play;

        PersonManager personManager = new PersonManager();
        PetManager petManager = new PetManager();
        AddressManager addressManager = new AddressManager();

        //Init.init(personManager,petManager, addressManager);

        UI ui = new UI(personManager, petManager, addressManager);

        do {
            play = ui.evaluateMainMenuInput();
        } while (play);
    }
}
