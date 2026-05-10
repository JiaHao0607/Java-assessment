package com.generation;

import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.Scanner;

public class Main
{

    public static void main( String[] args )
        throws ParseException
    {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner( System.in );
        int option = 0;
        do
        {
            PrinterHelper.showMainMenu();
            option = Integer.parseInt(scanner.nextLine());
            switch ( option )
            {
                case 1:
                    registerStudent( studentService, scanner );
                    break;
                case 2:
                    findStudent( studentService, scanner );
                    break;
                case 3:
                    gradeStudent( studentService, scanner );
                    break;
                case 4:
                    enrolStudentToCourse( studentService, courseService, scanner );
                    break;
                case 5:
                    showStudentsSummary( studentService, scanner );
                    break;
                case 6:
                    showCoursesSummary( courseService, scanner );
                    break;
                case 7:
                    showPassedCourses(studentService, scanner);
                    break;
            }
        }
        while ( option != 8 );
    }

    private static void enrolStudentToCourse( StudentService studentService, CourseService courseService,
                                               Scanner scanner )
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        System.out.println( student );
        System.out.println( "Insert course ID" );
        String courseId = scanner.next();
        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }
        System.out.println( course );
        courseService.enrolStudent( courseId, student );
        studentService.enrolToCourse( studentId, course );
        System.out.println( "Student with ID: " + studentId + " enroled successfully to " + courseId );

    }

    private static void showCoursesSummary( CourseService courseService, Scanner scanner )
    {
        courseService.showSummary();
    }

    private static void showStudentsSummary( StudentService studentService, Scanner scanner )
    {
        studentService.showSummary();
    }

    private static void gradeStudent( StudentService studentService, Scanner scanner )
    {
        System.out.println("Enter student ID: ");
        String studentID = scanner.next();
        Student student = studentService.findStudent(studentID);

        if(student != null){
            System.out.printf("Enter the course ID for Student ID: %s", studentID);
            String courseID = scanner.next();
            boolean isAttendingCourse = student.isAttendingCourse(courseID);

            if(isAttendingCourse){
                System.out.printf("Enter the score for course ID: %s", courseID);
                double score = scanner.nextDouble();

                if(score < 0 || score > 9){
                    System.out.println("Invalid score entered");
                }
                else{
                    System.out.println(student.setGrade(courseID, score));
                }
            }
            else{
                System.out.println("Student is not attending this course.");
            }
        }
        else{
            System.out.println("Student with ID " + studentID + " not found");
        }
    }

    private static void showPassedCourses(StudentService studentService, Scanner scanner){
    System.out.println( "Enter student ID: " );
        String studentID = scanner.next();
        Student student = studentService.findStudent( studentID );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );

            // Show the courses the student has passed
            studentService.showPassedCourses(student);
        }
        else
        {
            System.out.println( "Student with ID " + studentID + " not found" );
        }    
    }

    private static void findStudent( StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );
        }
        else
        {
            System.out.println( "Student with Id = " + studentId + " not found" );
        }
    }

    private static void registerStudent( StudentService studentService, Scanner scanner )
        throws ParseException
    {
        Student student = PrinterHelper.createStudentMenu( scanner );
        studentService.subscribeStudent( student );
    }
}
