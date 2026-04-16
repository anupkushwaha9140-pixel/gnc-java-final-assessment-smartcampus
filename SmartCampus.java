
mport java.util.*;

// Main class containing menu-driven system
public class SmartCampus {

    static Scanner sc = new Scanner(System.in);

    // List to store students
    static ArrayList<Student> students = new ArrayList<>();

    // List to store courses
    static ArrayList<Course> courses = new ArrayList<>();

    // HashMap to store enrollments
    // Key = studentId, Value = list of courseIds
    static HashMap<Integer, ArrayList<Integer>> enrollments = new HashMap<>();

    public static void main(String[] args) {

        while (true) {

            // Display menu
            System.out.println("\n===== SmartCampus Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student");
            System.out.println("4. View Students");
            System.out.println("5. View Enrollments");
            System.out.println("6. Search Student");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            int choice = -1;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine(); // Clear the invalid input from buffer
                continue;
            }

            // Perform action based on user choice
            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    addCourse();
                    break;

                case 3:
                    enrollStudent();
                    break;

                case 4:
                    viewStudents();
                    break;

                case 5:
                    viewEnrollments();
                    break;

                case 6:
                    searchStudent();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Method to add student
    static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        // Add student to list
        students.add(new Student(id, name, email));

        System.out.println("Student added successfully!");
    }

    // Method to add course
    static void addCourse() {
        try {
            System.out.print("Enter Course ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Course Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Course Fee: ");
            double fee = sc.nextDouble();

            // Validate fee
            if (fee < 0) {
                throw new InvalidFeeException("Fee cannot be negative!");
            }

            // Add course to list
            courses.add(new Course(id, name, fee));

            System.out.println("Course added successfully!");

        } catch (InvalidFeeException e) {
            // Handle custom exception
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to enroll student in course
    static void enrollStudent() {

        System.out.print("Enter Student ID: ");
        int sid = sc.nextInt();

        System.out.print("Enter Course ID: ");
        int cid = sc.nextInt();

        // Fetch names and validate existence
        String studentName = getStudentName(sid);
        String courseName = getCourseName(cid);

        if (studentName.equals("Unknown Student") || courseName.equals("Unknown Course")) {
            System.out.println("Error: Student or Course ID not found!");
            return;
        }

        // Create list if student not present
        enrollments.putIfAbsent(sid, new ArrayList<>());

        // Add course to student's list
        enrollments.get(sid).add(cid);

        // Start thread for processing enrollment
        EnrollmentProcessor thread = new EnrollmentProcessor(studentName, courseName);
        thread.start();
    }

    // Method to search for a student
    static void searchStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        for (Student s : students) {
            if (s.studentId == id) {
                System.out.println("Found: " + s);
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // Method to display all students
    static void viewStudents() {
        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Method to display enrollments
    static void viewEnrollments() {
        System.out.println("\n--- Enrollment List ---");

        for (Integer sid : enrollments.keySet()) {
            System.out.print("Student ID " + sid + " enrolled in: ");

            for (Integer cid : enrollments.get(sid)) {
                System.out.print(cid + " ");
            }
            System.out.println();
        }
    }

    // Helper method to get student name
    static String getStudentName(int id) {
        for (Student s : students) {
            if (s.studentId == id)
                return s.name;
        }
        return "Unknown Student";
    }

    // Helper method to get course name
    static String getCourseName(int id) {
        for (Course c : courses) {
            if (c.courseId == id)
                return c.courseName;
        }
        return "Unknown Course";
    }
}