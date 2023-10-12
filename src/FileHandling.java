import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandling {
    public List<String> read_file(){
        List<String> output = new ArrayList<>();
        try{
            File myObject = new File("todos.txt");
            Scanner myReader = new Scanner(myObject);

        while(myReader.hasNextLine()){
            output.add(myReader.nextLine());
        }
        myReader.close();
        }catch(FileNotFoundException e){
            System.out.println("An error occurred!");
        }
        return output;
    }
//    public void create_file(String fileName){
//        try {
//            File myObj = new File(fileName);
//            if (myObj.createNewFile()) {
//                System.out.println("File created: " + myObj.getName());
//            } else {
//                System.out.println("File already exists.");
//            }
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//        }
//    }
    public void write_file(String content, Boolean override){
        try {
            FileWriter myWriter = new FileWriter("todos.txt", !override);
            myWriter.write(content);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");

        }
    }
}
