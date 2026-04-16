// class to store course details
public class Course {

    int courseId; // Unique course ID
    String courseName; // Name of course
    double fee; // Course fee

    // initialize course object
    public Course(int courseId, String courseName, double fee) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.fee = fee;
    }

    // Method to display course details
    public String toString() {
        return courseId + " - " + courseName + " - ₹" + fee;
    }
}
