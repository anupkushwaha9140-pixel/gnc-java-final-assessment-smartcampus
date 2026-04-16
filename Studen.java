/*As an Admin,
I want to manage students and courses,
So that I can track enrollments efficiently.
As a System,
I should handle errors and process requests asynchronously,
So that the application is reliable.
 */

// class to store student details
public class Student {

    int studentId; // Unique ID of student
    String name; // Student name
    String email; // Student email

    // initialize student object
    public Student(int studentId, String name, String email) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
    }

    // Method to display student details
    public String toString() {
        return studentId + " - " + name + " - " + email;
    }
}