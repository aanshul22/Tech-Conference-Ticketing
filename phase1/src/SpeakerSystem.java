import java.util.Scanner;


public class SpeakerSystem extends UserSystem{
    public SpeakerSystem (Presenter p, UserManager uMan, EventManager eMan, MessageManager mMan, RoomManager rMan) {
        super(p, uMan, eMan, mMan, rMan);
    }

    public void run(String username){
        Scanner scan = new Scanner(System.in);

        while(true){
            presenter.printSpeakerMenu();
            String speakerChoice = scan.nextLine();

            switch (speakerChoice){
                case "0":
                    presenter.printLoggedOut();
                    break;
                case "1":
                    while (true) {
                        presenter.printSpeakerMessageMenu();
                        String messageChoice = scan.nextLine();
                        speakerHelperMessageSystem(username, messageChoice, scan);
                        if(!messageChoice.equals("b")) {
                            presenter.printInvalidInput();
                        } else {
                            break;
                        }
                    }
                case "2":

                default:
            }
        }
    }

    public void speakerHelperMessageSystem(String username, String choice, Scanner scan) {
        super.helperMessageSystem(username, choice, scan);
        if (choice.equals("4")) {

        }
    }
}
