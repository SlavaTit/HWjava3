package homeworkLesson3TitSlava;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/*Написать программу которая будет записывать Ф.И.О. студентов в файл.
1. Если файла списка студентов еще нет - он должен быть создан автоматически
2. Добавить возможность добавлять студентов в файл таким образом, чтоб к существующему списку был добавлен новый студент.
3. Если студент с такой фамилией есть он не добавляется в файл*/

public class SaveStudents {
    public static void main(String[] args) throws IOException {
        WorkWithFile student = new WorkWithFile();
        student.createFile();
        student.addStudent();
    }
     static class WorkWithFile {
        private int count = 0;
        private String firstName;
        private String lastName;
        private File file;
        private List<String> allStudents = new ArrayList<>();
        private List<String> onlyLast = new ArrayList<>();

        private File createFile() {
            File file = new File("slavaTit.txt");
            if (!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } this.file = file;
              return file;
            }
        public String setFirstName(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter First Name: ");
        String firstName = scan.nextLine();
            for (char c: firstName.toCharArray()) {
                if (Character.isDigit(c)) {
                    System.out.println("Digits are not allowed!");
                    return setFirstName();
                }
            }
        this.firstName = firstName;
        return firstName;
        }
        public String setLastName(){
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter Last Name: ");
            String lastName = scan.nextLine();
            for (char c: lastName.toCharArray()) {
                if (Character.isDigit(c)) {
                    System.out.println("Digits are not allowed!");
                    return setLastName();
                }
            }
            this.lastName = lastName;
            return lastName;
        }
        private List copyFileToList(){
            try {
                Scanner scan = new Scanner(file);
                while (scan.hasNextLine()) {
                    String sb = scan.nextLine();
                    allStudents.addAll(Arrays.asList(sb.split("0")));
                }
            } catch (NoSuchElementException | FileNotFoundException e) {
                System.out.println("Missing element");
            }
            return allStudents;
        }
        private List copyLastNameToList(){
            for(String i: allStudents){
                count++;
                if(count%2==0){
                    onlyLast.add(i);
                }else onlyLast.remove(i);
            }
            return onlyLast;
        }
        private void fileWriter() throws IOException {
            FileWriter pw = new FileWriter(file, true);
            pw.append(firstName +"0"+ lastName +"0");
            pw.close();
        }
        public void addStudent() throws IOException {
            copyFileToList();
            copyLastNameToList();
            setFirstName();
            setLastName();
            if(onlyLast.contains(lastName))
            {
                System.out.println("A student with provided last name exists");
            } else {
                fileWriter();
                System.out.println("Student saved");
            }

        }
    }
}
