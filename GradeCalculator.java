import java.util.Scanner;
public class GradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of subjects : ");
        int no_sub = sc.nextInt();
        int marks[] = new int[no_sub];
        int total = 0;

        for(int i = 0; i< no_sub;i++){
            System.out.print("Enter the marks for the subject "+ (i+1)+"(out of 100) : ");
            marks[i] = sc.nextInt();

            if(marks[i] < 0 || marks[i] >100){
                System.out.println("Invalid!, please enter valid marks.");
                i--;
                continue;
            }
            total += marks[i];
        }
    

        double average = (double) total/no_sub;

        int gradeLevel = (int) average/10;
        String grade;

        switch(gradeLevel){
            case 10:
            case 9:
                grade = "A";
                break;
            case 8:
                grade = "B";
                break;
            case 7:
                grade = "C";
                break;
            case 6: 
                grade = "D";
                break;
            default:
                grade = "F";
        }

        System.out.println("------RESULT------");
        System.out.println("Total Marks: " + total + " out of " + (no_sub * 100));
        System.out.printf("Average Percentage: %.2f%%\n", average);
        System.out.println("Grade: " + grade);

        sc.close();




    }
    
}
