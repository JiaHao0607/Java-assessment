package com.generation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student
    extends Person
    implements Evaluation
{
    private double average;

    private final List<Course> courses = new ArrayList<>();

    private final Map<String, Course> approvedCourses = new HashMap<>();

    // Create a separate hashmap to store the course and credit
    private final Map<String, Double> courseGrade = new Hashmap<>(); // <courseID, credit>

    public Student( String id, String name, String email, Date birthDate )
    {
        super( id, name, email, birthDate );
    }

    public void enrolToCourse( Course course )
    {
        //TODO (Done) implement this method
        if(!courses.contains(course)){
        courses.add(course);
        registerApprovedCourse(course);
        }
    }

    public void registerApprovedCourse( Course course )
    {
        approvedCourses.put( course.getCode(), course );
    }

    public boolean isCourseApproved( String courseCode )
    {
        //TODO implement this method
        return false;
    }

    // CHALLENGE IMPLEMENTATION: Read README.md to find instructions on how to solve.

    public String setGrade(String courseID, double score){
        if(approvedCourses.containsKey(courseID)){
            if(!courseGrade.containsKey(courseID)){
                courseGrade.put(courseID, score);
                return String.format("Score for Course ID: %s has been recorded successfully", courseID);
            }
            else{
                return String.format("A score has already been recorded for Course ID: %s", courseID);
            }
        }

        return String.format("The student did not take Course ID: %s", courseID);
    }
    public List<Course> findPassedCourses( Course course )
    {
        //TODO implement this method
        return null;
    }

    public boolean isAttendingCourse( String courseCode )
    {
        //TODO (Done) implement this method
        return approvedCourses.containsKey(courseCode);
    }

    @Override
    public double getAverage()
    {
        return average;
    }

    @Override
    public List<Course> getApprovedCourses()
    {
        //TODO implement this method
        // Return courses the student is taking
        List<Course> listedCourses = new ArrayList<>();

        if(!approvedCourses.isEmpty()){
            approvedCourses.forEach((courseId, course)->{
                listedCourses.add(course);
            });
        }

        return listedCourses;
    }
    }

    @Override
    public String toString()
    {
        return "Student {" + super.toString() + "}";
    }
}
