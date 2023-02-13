import java.io.FileWriter;

class WriteToFile{
    //attributes
    private ProjectList projectList;

    public WriteToFile() {
        projectList = ProjectList.getOnlyList();
    }

    public void writeToFile(){
        try{
            FileWriter fw=new FileWriter("Projectdatalist.txt");
            fw.write(projectList.toFile());
            fw.close();
        }catch(Exception e){System.out.println(e);}
    }
}
