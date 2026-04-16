//Thread class to simulate asynchronous enrollment processing
public class EnrollmentProcessor extends Thread {

    String studentName;
    String courseName;

    // initialize thread data
    public EnrollmentProcessor(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }

    // run() method executes when starts
    public void run() {
        try {
            //
            System.out.println("Processing enrollment...");
            Thread.sleep(2000);

            // successful enrollment
            System.out.println(studentName + " enrolled in " + courseName);

        } catch (InterruptedException e) {
            System.out.println("Thread interrupted!");
        }
    }
