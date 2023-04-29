import error.InvalidInputException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CredentialsValidator validator = new CredentialsValidator();
    private static final List<Student> students = new ArrayList<>();
    private static final StudentParser parser = new StudentParser();


    public void showInterface(){
        printMessage("Learning Progress Tracker");
        while (scanner.hasNextLine()){
            String nextLine = takeInput();
            handleInput(nextLine);

        }
    }

    public void handleInput(String input){
        if (input.isBlank()){
            printMessage("No input");
        }
        else if (input.equals("exit")){
            printMessage("Bye!");
            System.exit(0);
        }
        else if (input.equals("add students")) {
            printMessage("Enter student credentials or 'back' to return");
            addStudent();
        }
        else if (input.equals("back")){
            printMessage("Enter 'exit' to exit the program.");
        }
        else {
            printMessage("Unknown command!");
        }
    }

    public void addStudent(){
        String input;
        do {
            input = scanner.nextLine();
            if (input.equals("back")){
                printMessage("Total " + students.size() + " students have been added.");
                break;
            }
            else {
                try{
                    Student newStudent = parser.parse(input);
                    students.add(newStudent);
                    printMessage("The student has been added");
                }catch (InvalidInputException e){
                    printMessage(e.getMessage());
                }

            }
        }
        while (true);
    }

    private String takeInput(){
        return scanner.nextLine();
    }

    private void printMessage(String message){
        System.out.println(message);
    }

}
