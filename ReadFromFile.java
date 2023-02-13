import java.io.File;
import java.util.Scanner;
class ReadFromFile{
    //Attributes
    ProjectList projectdataList;

    public ReadFromFile() {
        projectdataList = ProjectList.getOnlyList();
    }

    public void readProjectDataFile(){
        try{
            File inFile = new File("Projectdatalist.txt");
            Scanner ins = new Scanner(inFile);

            while(ins.hasNext()){
                String projectData = ins.nextLine();
                String[] dataList = projectData.split(", ");
                String projectNumber = dataList[0];
                int erfNumber = Integer.parseInt(dataList[4]);
                double totalFee = Double.parseDouble(dataList[5]);
                double amountPaid = Double.parseDouble(dataList[6]);
                String projectName = dataList[1];
                String deadline = dataList[7];
                String buildingType = dataList[2];
                String physicalAddress = dataList[3];
                String status = dataList[23];

                String archiName = dataList[8];
                String archiSurname = dataList[9];
                String archiTele = dataList[10];
                String archiEmail = dataList[11];
                String archiAddress = dataList[12];
                Persons architect = new Persons(archiName, archiSurname, archiTele, archiEmail, archiAddress);

                String contractorName = dataList[13];
                String contractorSurname = dataList[14];
                String contractorTele = dataList[15];
                String contractorEmail = dataList[16];
                String contractorAddress = dataList[17];
                Persons contractor =new Persons(contractorName, contractorSurname, contractorTele, contractorEmail, contractorAddress);

                String customerName = dataList[18];
                String customerSurname = dataList[19];
                String customerTele = dataList[20];
                String customerEmail = dataList[21];
                String customerAddress = dataList[22];
                Persons customer = new Persons(customerName, customerSurname, customerTele, customerEmail, customerAddress);

                Project proj = new Project(projectNumber, projectName, buildingType, physicalAddress, erfNumber,
                        totalFee, amountPaid, deadline, architect, contractor, customer, status);
                //add the object of college to the list
                projectdataList.addProject(proj);
            }
        }catch(Exception ex){
            System.out.println("Reading from the file has failed.");
        }
    }
}
