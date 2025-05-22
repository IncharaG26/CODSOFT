package StudentManagement;

import java.util.HashMap;
import java.util.Map;

public class StudentManagementSystem {
    Map<String, Student> students;

    public StudentManagementSystem() {
        students = new HashMap<>();
    }

    public boolean addStudent(Student s) {
        if (students.containsKey(s.getRollNumber())) {
            return false; // duplicate roll number
        }
        students.put(s.getRollNumber(), s);
        return true;
    }

    public Student searchStudent(String rollNumber) {
        return students.get(rollNumber);
    }

    public boolean updateStudent(String rollNumber, Student updated) {
        if (!students.containsKey(rollNumber)) {
            return false;
        }
        students.put(rollNumber, updated);
        return true;
    }

    public boolean removeStudent(String rollNumber) {
        if (!students.containsKey(rollNumber)) {
            return false;
        }
        students.remove(rollNumber);
        return true;
    }

    public Map<String, Student> getAllStudents() {
        return students;
    }
}
