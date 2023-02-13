import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.NoSuchElementException;

public class ProjectList {

    //object containing a list of projects
    static ArrayList<Project> projectList;

    private static ProjectList onlyList = null;

    private ProjectList() {
        //create the instance of the object of project.
        projectList = new ArrayList<>();
    }

    public static ProjectList getOnlyList() {
        if (onlyList == null) {
            onlyList = new ProjectList();
        }
        return onlyList;
    }

    public static void deleteProject(int index) {
        if (index<= projectList.size()){
            projectList.remove(index);}
        else {
            System.out.println("Index selected is out of bounds");
        }
    }

    public void choiceList() {
        int counter = 1;
        if(projectList!=null) {
            for (Project co : projectList) {
                System.out.println(counter + ". " + co.getprojectNumber() + " - " + co.getprojectName());
                counter++;
            }
        }else {
            System.out.println("There are no projects in your text file");
        }
    }

    public void viewOverdue() throws Exception {
        int counter = 1;
        for (Project co : projectList) {
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate today = LocalDate.now();
            Date d1 = sdformat.parse(co.deadline);
            Date d2 = sdformat.parse(String.valueOf(today));

            if(d1.compareTo(d2) < 0) {
                System.out.println(counter + ". " + co.getprojectNumber() + " - " + co.getprojectName() + "\n");
            }
            counter++;

        }
    }

    public void incompleteProjects() {
        int counter = 1;
        for (Project co : projectList) {
            if (co.getStatus().equalsIgnoreCase("Incomplete")) {
                System.out.println(counter + ". " + co.getprojectNumber() + " - " + co.getprojectName() + "\n");
            }
            counter++;
        }
    }

    public Project getProject(int index) {
        return projectList.get(index);
    }

    //add new project object
    public void addProject(Project c) {//add new project object to the list of projects in the project list object 
        projectList.add(c);//add the object of college to the list of colleges in the system
    }

    //to print all objects to the console
    public void printAll() {
        try{
            for (Project co : projectList) {
                System.out.println("\n-------\n" + co + "\n-----------\n\n");
            }
        }catch (NoSuchElementException elementException){
            System.out.println("No elements in your list/text file");
        }
    }

    public String toFile() {
        String data = "";
        for (Project co : projectList) {
            data += co.toFile() + "\n";
        }
        data = data.substring(0, data.length() - 1);
        return data;

    }

    public static void finaliseProject(int ignoredIndex) {
        double amountStillToPay;
        for (Project co : projectList) {
            Persons customerDetails = co.getCustomer();
            if (co.amountPaid != 0) {
                amountStillToPay = co.totalFee - co.amountPaid;
                try{
                    Formatter outFile = new Formatter("CompletedProjects.txt");
                    outFile.format("Customer Details\n" + customerDetails + "\nOutstanding amount\n" + amountStillToPay);
                    outFile.close();
                }catch(Exception ex){
                    System.out.println("Writting to the file has failed due to unknown error");
                }
            } else {
                System.out.println("This project has been finalised");
            }
        }
    }
}
