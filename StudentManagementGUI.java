package StudentManagement;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class StudentManagementGUI extends JFrame {
    private StudentManagementSystem sms;

    private JTextField tfName, tfRoll, tfGrade, tfAge;
    private JTextArea taDisplay;
    private JButton btnAdd, btnUpdate, btnDelete, btnSearch, btnDisplayAll;

    public StudentManagementGUI() {
        sms = new StudentManagementSystem();
        initComponents();
    }

    private void initComponents() {
        setTitle("Student Management System");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel for form inputs
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 8, 20));
        formPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));

        formPanel.add(new JLabel("Name:"));
        tfName = new JTextField();
        formPanel.add(tfName);

        formPanel.add(new JLabel("Roll Number:"));
        tfRoll = new JTextField();
        formPanel.add(tfRoll);

        formPanel.add(new JLabel("Grade:"));
        tfGrade = new JTextField();
        formPanel.add(tfGrade);

        formPanel.add(new JLabel("Age:"));
        tfAge = new JTextField();
        formPanel.add(tfAge);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 8, 40));

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnSearch = new JButton("Search");
        btnDisplayAll = new JButton("Display All");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnDisplayAll);

        // Text area for displaying results
        taDisplay = new JTextArea();
        taDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taDisplay);

        // Add listeners
        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnSearch.addActionListener(e -> searchStudent());
        btnDisplayAll.addActionListener(e -> displayAllStudents());

        // Layout main frame
        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    // Helper: Validate input fields
    private boolean validateInputs(boolean checkRollUnique) {
        String name = tfName.getText().trim();
        String roll = tfRoll.getText().trim();
        String grade = tfGrade.getText().trim();
        String ageStr = tfAge.getText().trim();

        if (name.isEmpty() || roll.isEmpty() || grade.isEmpty() || ageStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!roll.matches("R\\d+")) {
            JOptionPane.showMessageDialog(this, "Roll Number must start with 'R' followed by digits.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (checkRollUnique && sms.searchStudent(roll) != null) {
            JOptionPane.showMessageDialog(this, "Roll Number already exists.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int age = Integer.parseInt(ageStr);
            if (age <= 0) {
                JOptionPane.showMessageDialog(this, "Age must be a positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Age must be a valid integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    // Clear input fields
    private void clearFields() {
        tfName.setText("");
        tfRoll.setText("");
        tfGrade.setText("");
        tfAge.setText("");
    }

    // Button actions
    private void addStudent() {
        if (!validateInputs(true)) return;

        String name = tfName.getText().trim();
        String roll = tfRoll.getText().trim();
        String grade = tfGrade.getText().trim();
        int age = Integer.parseInt(tfAge.getText().trim());

        Student student = new Student(name, roll, grade, age);
        if (sms.addStudent(student)) {
            JOptionPane.showMessageDialog(this, "Student added successfully.");
            clearFields();
            displayAllStudents();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add student.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
        String roll = tfRoll.getText().trim();
        if (roll.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Roll Number to update.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student existing = sms.searchStudent(roll);
        if (existing == null) {
            JOptionPane.showMessageDialog(this, "Student not found with roll number " + roll, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validateInputs(false)) return;

        String name = tfName.getText().trim();
        String grade = tfGrade.getText().trim();
        int age = Integer.parseInt(tfAge.getText().trim());

        Student updatedStudent = new Student(name, roll, grade, age);
        if (sms.updateStudent(roll, updatedStudent)) {
            JOptionPane.showMessageDialog(this, "Student updated successfully.");
            clearFields();
            displayAllStudents();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update student.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        String roll = tfRoll.getText().trim();
        if (roll.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Roll Number to delete.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete student " + roll + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (sms.removeStudent(roll)) {
                JOptionPane.showMessageDialog(this, "Student removed successfully.");
                clearFields();
                displayAllStudents();
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void searchStudent() {
        String roll = tfRoll.getText().trim();
        if (roll.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Roll Number to search.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = sms.searchStudent(roll);
        if (student != null) {
            taDisplay.setText(student.toString());
            // Optionally fill the fields with student data:
            tfName.setText(student.getName());
            tfGrade.setText(student.getGrade());
            tfAge.setText(String.valueOf(student.getAge()));
        } else {
            taDisplay.setText("Student not found.");
        }
    }

    private void displayAllStudents() {
        Map<String, Student> allStudents = sms.getAllStudents();

        if (allStudents.isEmpty()) {
            taDisplay.setText("No students found.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Student s : allStudents.values()) {
            sb.append(s.toString()).append("\n-------------------\n");
        }
        taDisplay.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagementGUI().setVisible(true);
        });
    }
}
