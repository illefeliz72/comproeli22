import java.util.ArrayList;
import java.io.*;

public class AttendanceApp {

    public static void addStudent(ArrayList<Student> students, String name) {
        students.add(new Student(name));
    }

    public static boolean recordAttendance(ArrayList<Student> students, String studentName, int mark) {
        for (Student s : students) {
            if (s.name.equalsIgnoreCase(studentName)) {
                s.attendanceMarks.add(mark);
                return true;
            }
        }
        return false;
    }

    public static double getAttendancePercentage(Student student) {
        if (student.attendanceMarks.size() == 0) {
            return 0.0;
        }

        int present = 0;
        for (int mark : student.attendanceMarks) {
            if (mark == 1) {
                present++;
            }
        }

        return (present * 100.0) / student.attendanceMarks.size();
    }

    public static String getDisplayInfo(Student student) {
        return "Name: " + student.name + ", Attendance: " +
                String.format("%.2f", getAttendancePercentage(student)) + "%";
    }

    public static void displayAllStudents(ArrayList<Student> students) {
        for (Student s : students) {
            System.out.println(getDisplayInfo(s));
        }
    }

    public static void saveStudents(ArrayList<Student> students, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student s : students) {
                StringBuilder line = new StringBuilder();
                line.append(s.name);
                for (int mark : s.attendanceMarks) {
                    line.append(",").append(mark);
                }
                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static ArrayList<Student> loadStudents(String filename) {
        ArrayList<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    Student s = new Student(parts[0]);
                    for (int i = 1; i < parts.length; i++) {
                        try {
                            s.attendanceMarks.add(Integer.parseInt(parts[i]));
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid attendance mark for " + s.name);
                        }
                    }
                    students.add(s);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return students;
    }

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();

        addStudent(students, "John Doe");

        addStudent(students, "Jane Smith");

        recordAttendance(students, "John Doe", 1);

        recordAttendance(students, "John Doe", 0);

        recordAttendance(students, "John Doe", 1);

        boolean result = recordAttendance(students, "Mike Brown", 1);

        if (!result) {
            System.out.println("Student not found: Mike Brown");
        }

        saveStudents(students, "attendance.txt");

        ArrayList<Student> loadedStudents = loadStudents("attendance.txt");

        System.out.println("Loaded Students:");
        displayAllStudents(loadedStudents);

        ArrayList<Student> errorTest = loadStudents("nonexistent.txt");
    }
}