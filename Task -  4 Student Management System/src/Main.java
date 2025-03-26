import java.io.*;
import java.util.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fullName;
    private int id;
    private String level;

    // Constructor
    public Student(String fullName, int id, String level) {
        this.fullName = fullName;
        this.id = id;
        this.level = level;
    }

    // Getters
    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getLevel() { return level; }

    // Setters for updating student details
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setLevel(String level) { this.level = level; }

    // Display student details
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + fullName + " | Level: " + level;
    }
}

class StudentManager {
    private LinkedHashMap<Integer, Student> studentRecords = new LinkedHashMap<>();
    private final String FILE_PATH = "student_data.bin";

    // Constructor to load students when program starts
    public StudentManager() {
        loadStudentData();
    }

    // Add a student
    public void enrollStudent(String fullName, int id, String level) {
        if (studentRecords.containsKey(id)) {
            System.out.println("Student with ID " + id + " already exists.");
            return;
        }
        studentRecords.put(id, new Student(fullName, id, level));
        saveStudentData();
        System.out.println("Student added successfully!");
    }

    // Remove a student
    public void expelStudent(int id) {
        if (studentRecords.remove(id) != null) {
            saveStudentData();
            System.out.println("Student removed.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Search for a student
    public Student findStudent(int id) {
        return studentRecords.get(id);
    }

    // Display all students
    public void listAllStudents() {
        if (studentRecords.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            studentRecords.values().forEach(System.out::println);
        }
    }

    // Save students to file
    private void saveStudentData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(studentRecords);
        } catch (IOException e) {
            System.out.println("Error storing student data.");
        }
    }

    // Load students from file
    private void loadStudentData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            studentRecords = (LinkedHashMap<Integer, Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            studentRecords = new LinkedHashMap<>();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            // User menu
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Register a Student");
            System.out.println("2. Remove a Student");
            System.out.println("3. Search Student by ID");
            System.out.println("4. View All Students");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int option = input.nextInt();
            input.nextLine(); // Consume newline

            if (option == 1) {
                System.out.print("Enter Student Name: ");
                String name = input.nextLine();
                System.out.print("Enter Student ID: ");
                int id = input.nextInt();
                input.nextLine();
                System.out.print("Enter Grade Level: ");
                String grade = input.nextLine();

                if (name.isEmpty() || grade.isEmpty()) {
                    System.out.println("Invalid input! Name and Grade cannot be empty.");
                } else {
                    manager.enrollStudent(name, id, grade);
                }
            } else if (option == 2) {
                System.out.print("Enter Student ID to Remove: ");
                int removeId = input.nextInt();
                manager.expelStudent(removeId);
            } else if (option == 3) {
                System.out.print("Enter Student ID to Search: ");
                int searchId = input.nextInt();
                Student foundStudent = manager.findStudent(searchId);
                System.out.println(foundStudent != null ? foundStudent : "Student not found.");
            } else if (option == 4) {
                manager.listAllStudents();
            } else if (option == 5) {
                System.out.println("Exiting system. Have a great day!");
                input.close();
                return;
            } else {
                System.out.println("Invalid choice! Please select a valid option.");
            }
        }
    }
}
