package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentService
{
    private final Map<String, Student> students = new HashMap<>();

    public StudentService(){
        subscribeStudent(new Student("001", "John", "johndoe@gmail.com", new Date("01/01/2000")));
        subscribeStudent(new Student("002", "Jane", "janesmith@hotmail.com", new Date("06/07/2000")));
        subscribeStudent(new Student("003", "Jessica", "jessica@yahoo.com", new Date("06/07/2000")));
    }

    public void subscribeStudent( Student student )
    {
        students.put( student.getId(), student );
    }

    public Student findStudent( String studentId )
    {
        if ( students.containsKey( studentId ) )
        {
            return students.get( studentId );
        }
        return null;
    }

    public boolean isSubscribed( String studentId )
    {
        //TODO implement this method
        return false;
    }

    public void showSummary()
    {
        //TODO implement
        // Show each student's information, along with the course(s) taken
        System.out.println("Students' Information:");
        students.forEach((studentId, student) -> {
            System.out.println(student);

            List<Course> studentCourses = student.getApprovedCourses();

            if(studentCourses.size() > 0)
                System.out.println("Student has courses.");
            else
                System.out.println("Student has no courses.");

        });
    }

    public void enrolToCourse( String studentId, Course course )
    {
        if ( students.containsKey( studentId ) )
        {
            students.get( studentId ).enrolToCourse( course );
        }
    }

    public void showPassedCourses(Student student){
        List<Course> passedCourses = student.findPassedCourses();

        if(passedCourses.size() > 0){
            System.out.println("Courses the student has passed: ");
            passedCourses.forEach((course)->{
                System.out.println(course);
            });
        }
        else{
            System.out.println("The student did not pass any courses.");
        }
    }


}
