// Create a class to store the details of a project
public class Project {
    // Create attributes for your class//the 
    public String projectNumber;
    public String projectName;
    public String buildingType;
    public String physicalAddress;
    public int erfNumber;
    public double totalFee;
    public double amountPaid;
    public String deadline;
    public Persons architect;
    public Persons contractor;
    public Persons customer;
    public String status;

    // Create a constructor for your class
    public Project(String projectNumber, String projectName, String buildingType, String physicalAddress, int erfNumber,
                   double totalFee, double amountPaid, String deadline, Persons architect, Persons contractor,
                   Persons customer, String status){
        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.buildingType = buildingType;
        this.physicalAddress = physicalAddress;
        this.erfNumber = erfNumber;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
        this.deadline = deadline;
        this.architect = architect;
        this.contractor = contractor;
        this.customer = customer;
        this.status = status;
    }

    // Create a method to display your project
    public String toString() {
        String output = "Project number: " + projectNumber;
        output += "\nProject name: " + projectName;
        output += "\nBuilding type:" + buildingType;
        output += "\nPhysical Address:" + physicalAddress;
        output += "\nERF number:" + erfNumber;
        output += "\nTotal fee: R" + totalFee;
        output += "\nAmount paid to date: R" + amountPaid;
        output += "\nDeadline date:" + deadline;
        output += "\n\nArchitect details";
        output += "\n"+architect;
        output += "\n\nContractor details";
        output += "\n"+ contractor;
        output += "\n\nCustomer details";
        output += "\n"+ customer;
        output += "\n\nStatus: " + status;

        // Return the output for your ToString method//
        return output;}


    // Create your setters and getters
    public void setAmountPaid(double amountPaid) {
        this.amountPaid += amountPaid;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    public Persons getContractor() {
        return contractor;
    }
    public String getprojectName() {return projectName;}
    public String getprojectNumber() {return projectNumber;}
    public String getStatus() {return status;}
    public Persons getCustomer(){return customer;}

    public String toFile() {
        return  projectNumber + ", "+ projectName + ", " +  physicalAddress + ", " + buildingType + ", "+ erfNumber + ", "
                + totalFee + ", " + amountPaid + ", " + deadline + ", " + architect.toFile() + ", " + contractor.toFile()
                + ", " + customer.toFile() + ", " + status;
    }
}
