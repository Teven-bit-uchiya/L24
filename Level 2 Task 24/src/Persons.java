
// Create a class called Persons to store a person's details
public class Persons {

    /**
     * Create attributes for your class
     */
    public String name;
    public String surname;
    private String telephoneNumber;
    private String emailAddress;
    public String physicalAddress;

    /**
     * Create a constructor for your class
     * @param name
     * @param surname
     * @param telephoneNumber
     * @param emailAddress
     * @param physicalAddress
     */
    public Persons(String name, String surname, String telephoneNumber, String emailAddress, String physicalAddress){
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
        this.physicalAddress = physicalAddress;
    }

    // Create a method to display the person's
    public String toString() {
        String output = "Name: " + name;
        output += "\nTelephone number: " + telephoneNumber;
        output += "\nEmail address:" + emailAddress;
        output += "\nPhysical Address:" + physicalAddress;

        return output;}

    // Create your setters and getters
    public String getSurname() {
        return surname;
    }

// Create a class called Persons to store a person's details
    public String toFile(){
        return name + ", "+ surname + ", "+
                telephoneNumber + ", "+ emailAddress + ", " + physicalAddress;
    }

    public void setContractor(String contractor) {
    }
}






