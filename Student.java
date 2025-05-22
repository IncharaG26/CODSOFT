package StudentManagement;

public class Student {
    private String name;
    private String rollNumber;
    private String grade;
    private int age;

    public Student(String name, String rollNumber, String grade, int age) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
               "Roll Number: " + rollNumber + "\n" +
               "Grade: " + grade + "\n" +
               "Age: " + age + "\n";
    }
}
