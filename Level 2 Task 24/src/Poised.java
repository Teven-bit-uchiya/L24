import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * This class is the main class of the application.
 * It is responsible for the main menu and the user interaction.
 * It also contains the main method.
 * @author Poised
 */
public class Poised {
    //method to get string data from user and return it to the calling method as a string value
    private static String getStrData(String instruction){
        Scanner sc = new Scanner(System.in);
        System.out.println(instruction);
        return sc.nextLine();
    }
    /** method to get integer data from user and return it to the calling method as an integer value
     * @param instruction
     * @return
     */
    private static int getintData(String instruction){
        while(true){
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println(instruction);
                int num = sc.nextInt();
                return num;
            }catch(Exception ex){
                System.out.println("You have enter an invalid number, try again");
            }
        }
    }
    /**method to get double data from user and return it to the calling method as a double value
    while loop to ensure that the user enters a double value**/
    public static double getdoubleData(String instruc){
        while(true){
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println(instruc);
                double num = sc.nextDouble();
                return num;
            }catch(Exception ex){
                System.out.println("You have enter an invalid number, try again");
            }
        }
    }
    /**main method to run the program
    //create an object of the ReadFromFile class
    //call the readProjectDataFile method to read the data from the project text file
    //create an object of the WriteToFile class
    //create an object of the ProjectList class**/
    public static void main(String[] args) throws  Exception {

        ReadFromFile readData = new ReadFromFile();
        readData.readProjectDataFile();
        WriteToFile writefile = new WriteToFile();
        ProjectList projectlist = ProjectList.getOnlyList();

/**while loop to ensure that the user can keep using the program until they choose to exit**/
        while (true) {
            System.out.println("""
                    1: Create a new project:\s
                    2: Update a project’s details:\s
                    3: View Incomplete projects:\s
                    4: View overdue projects:\s
                    5: View all projects:\s
                    6: Finalise or delete a project:\s
                    7: Quit:\s""");
            /** Display this menu to the user**/
            Scanner sc = new Scanner(System.in);

            // Ask the user to make a choice
            System.out.print("Make your choice: ");
            int userChoice = Integer.parseInt(sc.nextLine());

            // Execute the following functions according to the user input
            if (userChoice == 1) {
                // Call the function to create a new project
                Project proj = newProject();
                // Add the new project to the list
                projectlist.addProject(proj);
                writefile.writeToFile();//call the writeToFile method to write the data to the project text file
//if the user chooses to update a project
            } else if (userChoice == 2) {
                System.out.println("Select a project that you want to edit using the number below");
                projectlist.choiceList();
                int projectChoice = getintData("");
                Project c = projectlist.getProject(projectChoice);
                System.out.print(c);
                System.out.println("""
                        Select one of the following options below:
                        1 - Change the due date:\s
                        2 - Update amount paid to date:\s
                        3 - Change the contractor's details:\s
                        4 - Exit""");
                //get the user's choice
                //if the user chooses to change the due date
                int choice2 = getintData("");
                if (choice2 == 1) {
                    boolean correctFormat;//boolean variable to check if the date is in the correct format
                    String date;
                    do {
                        String newDuedate = getStrData("Enter new due date in the following format:(yyyy-MM-dd) ");
                        try {//try catch block to check if the date is in the correct format
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date dateObj = sdf.parse(newDuedate);//parse the date to the correct format
                            sdf.applyPattern("yyyy-MM-dd");
                            date = sdf.format(dateObj);//format the date to the correct format
                            c.setDeadline(date);//set the new due date
                            System.out.println(c);//print the project details
                            writefile.writeToFile();//call the writeToFile method to write the data to the project text file
                            correctFormat = true;
                        } catch (Exception e) {
                            correctFormat = false;
                        }
                    } while (!correctFormat);//while loop to ensure that the user enters a date in the correct format
                } else if (choice2 == 2) {//if the user chooses to update the amount paid to date
                    double newAmountPaid = getdoubleData("Enter additional amount paid");
                    c.setAmountPaid(newAmountPaid);
                    System.out.println(c);
                    writefile.writeToFile();
                } else if (choice2 == 3) {
                    String newContractorsDetails = String.valueOf(getPersonsData());
                    c.getContractor().setContractor(newContractorsDetails);
                    System.out.println(c);
                    writefile.writeToFile();
                } else if (choice2 == 4) {
                    System.out.println("Goodbye!!!");
                    System.exit(0);
                }
            } else if (userChoice == 3) {
                projectlist.incompleteProjects();
            } else if (userChoice == 4) {
                projectlist.viewOverdue();
            } else if (userChoice == 5) {
                projectlist.printAll();
            } else if (userChoice == 6) {
                System.out.println("1. Delete a project\n" +
                        "2. Finalise a project\n" +
                        "3. Exit");
                int choice3 = getintData("");
                if (choice3 == 1){
                    System.out.println("Select a project that you want to delete using the number below");
                    projectlist.choiceList();
                    int projectChoice = getintData("");
                    Project c = projectlist.getProject(projectChoice);
                    System.out.print(c);
                    ProjectList.deleteProject(projectChoice);
                    writefile.writeToFile();
                }else if(choice3 == 2) {
                    System.out.println("Select a project that you want to finalise using the number below");
                    projectlist.choiceList();
                    int projectChoice = getintData("");
                    boolean correctIndex = false;
                    do {
                        try{
                            Project c = projectlist.getProject(projectChoice);
                            System.out.print(c);
                            ProjectList.finaliseProject(projectChoice);
                            break;}catch (Exception e){
                            System.out.println("Invalid index,please select the correct index");
                        }
                    }while(!correctIndex);
                }else if(choice3 == 3){
                    System.out.println("Goodbye!!!");
                    System.exit(0);
                }else {
                    System.out.println("Invalid choice");
                }
            } else if (userChoice == 7) {
                // Exit the program and write the updated list into a file when the user chooses exit on the menu
                System.out.println("Goodbye!!!");
                System.exit(0);
            } else {
                // Print out an error message if the user enters an invalid choice
                System.out.println("Invalid choice");
            }
        }

    }

    /** Create a function to capture details to create a new project
    /**
     * Method to capture details to create a new project
     * @return Project
     */
    public static Project newProject() throws ParseException {

        Scanner details = new Scanner(System.in);

        // Ask the user to enter the project number
        System.out.println("Enter the project number: ");
        String newProjectNumber = details.nextLine();

        // Ask the user to enter the name for the project
        System.out.println("Enter the name for the project: ");
        String newProjectName = details.nextLine();

        // Ask the user to enter the building type
        System.out.println("Enter the building type(House/Apartment/Store): ");
        String newBuildingType = details.nextLine();

        // Ask the user to enter the physical address of the project
        System.out.println("Enter the physical address of the project: ");
        String newPhysicalAddress = details.nextLine();

        // Ask the user to enter the ERF number for the project
        System.out.println("Enter the ERF number for the project: ");
        int newErfNumber = Integer.parseInt(details.nextLine());


        // Ask the user to enter the total fee for the project
        System.out.println("Enter the total fee for the project: ");
        double newTotalFee = Double.parseDouble(details.nextLine());

        // Ask the user to enter the amount paid to date
        System.out.println("Enter the amount paid to date for the project: ");
        double newAmountPaid = Double.parseDouble(details.nextLine());

        // Ask the user to enter the deadline for the project
        boolean correctFormat = false;
        String date = null;
        do {

            System.out.println("Enter the deadline for the project in the following format:(yyyy-MM-dd) ");
            String newDeadline = details.nextLine();

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateObj = sdf.parse(newDeadline);
                sdf.applyPattern("yyyy-MM-dd");
                date = sdf.format(dateObj);
                correctFormat = true;
            } catch (Exception e) {
                correctFormat = false;
            }
        } while (!correctFormat);
        // Ask the user enter the architect's details
        System.out.println("Enter the architect details: ");
        Persons architect = getPersonsData();

        // Ask the user to enter the contractor's details
        System.out.println("Enter the contractor details: ");
        Persons contractor = getPersonsData();

        // Ask the user to enter the customer's details
        System.out.println("Enter the customer details: ");
        Persons customer = getPersonsData();
        // If the user doesn't enter a name for the project then use the customer's surname as the project's name
        if (newProjectName.equals("")) {
            newProjectName = customer.getSurname();
        }
        // Ask the user to enter the deadline for the project
        System.out.println("Enter the status for the project:(Complete/Incomplete) ");
        String newStatus = details.nextLine();

        // Return the new created project
        return new Project(newProjectNumber, newProjectName, newBuildingType, newPhysicalAddress, newErfNumber,
                newTotalFee, newAmountPaid, date, architect, contractor, customer, newStatus);

    }


    // Create a function to capture the person's details
    // This function is used to capture the details of the architect, contractor and customer
    /**
     * Method to return a person's details
     * @return
     */
    public static Persons getPersonsData() {

        Scanner personsDetails = new Scanner(System.in);

        // Ask the user to enter the person's name
        System.out.println("Enter the person's name: ");
        String newPersonName = personsDetails.nextLine();

        // Ask the user to enter the person's surname
        System.out.println("Enter the person's surname: ");
        String newPersonSurname = personsDetails.nextLine();

        // Ask the user to enter the person's telephone number
        System.out.println("Enter the telephone number: ");
        String newTelephoneNumber = personsDetails.nextLine();

        // Ask the user to enter the person's email address
        System.out.println("Enter the email address: ");
        String newPersonEmailAddress = personsDetails.nextLine();

        // Ask the user to enter the person's physical address
        System.out.println("Enter the physical address of the person: ");
        String newPersonPhysicalAddress = personsDetails.nextLine();

        // Return the new created person's details
        return new Persons(newPersonName, newPersonSurname, newTelephoneNumber, newPersonEmailAddress,
                newPersonPhysicalAddress);
    }
}

