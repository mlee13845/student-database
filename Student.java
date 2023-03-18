package com.example.tuitonfx;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
    This program allows users to construct their Student objects, access each of the components which include
    a profile object, major object and current grade based on number of credits. Finally, this program also
    implements and Overrides the compareTo and equals method for comparing two Student objects as well as a toString
    method for constructing a string with the users information in a specific format

    @author Michael Lee
 */
public abstract class Student implements Comparable<Student>{
    private Profile profile;
    private Major major;
    private int creditCompleted;
    /**
     * Constructor of the Student object including the Profile object, major enum and credit completed.
     *
     * @param profile Profile object of the student
     * @param major Major enum object of student
     * @param creditCompleted number of credits taken by Student
    */
    public Student (Profile profile, Major major, int creditCompleted){
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;

    }
    public boolean isValid(int creditEnrolled){
        int minimum_cred = 3;
        int maximum_cred = 24;
        if(creditEnrolled>=minimum_cred && creditEnrolled <=maximum_cred){
            return true;
        }
        return false;
    }

    public abstract double tuitionDue(int creditsEnrolled);
    public abstract boolean isResident();
    public abstract String printType();
    public abstract String specific_type();
    public boolean isPartTime(int creditsEnrolled) {
        int full_time = 12;
        if(creditsEnrolled<full_time){
            return true;
        }
        return false;
    }

    /**
     * Getter method for the private Profile object
     *
     * @return profile object component of the Student object
    */
    public int getCreditCompleted(){
        return this.creditCompleted;
    }
    public void setCredit(int creditsCompleted){
        this.creditCompleted = creditsCompleted;
    }
    public Profile getProfile(){
        return this.profile;
    }
    /**
     * Getter method for the private Major object
     *
     * @return major object component of the Student object
    */
    public Major getMajor(){
        return this.major;
    }

    /**
     * Setter method for private Major object
     *
     * @param major major object that is to be assigned to specific Student object
    */
    public void setMajor(Major major){
        this.major = major;
    }

    /**
     * Assigns a grade level given number of credits
     *
     * @return A string of either Freshman, Sophomore, Junior or Senior based on number of credits taken
     */
    public String getGrade(){
        int freshman_credit = 30;
        int sophomore_credit = 60;
        int junior_credit = 90;
        if(this.creditCompleted < freshman_credit){
            return "Freshman";
        }else if(freshman_credit>= this.creditCompleted && this.creditCompleted<sophomore_credit){
            return "Sophomore";
        }else if(sophomore_credit>= this.creditCompleted && this.creditCompleted < junior_credit){
            return "Junior";
        }else{
            return "Senior";
        }
    }

    /**
     * Compares objects together
     *
     * @param obj the Student object to be compared.
     * @return -1,0,1 based on comparison
     */
    @Override
    public int compareTo(Student obj) {
        return this.profile.compareTo(obj.getProfile());
    }

    /**
     * Checks to see if the obj of type Object is a consistent type and similar to compared object
     *
     * @param obj the object meant to be equal to
     * @return true or fale based on if they are the same
     */
    @Override
    public boolean equals(Object obj) {
        int match = 0;
        if(obj instanceof Student ){
            Student student_obj = (Student)obj;
            if(this.profile.compareTo(student_obj.profile) == match && this.creditCompleted == student_obj.creditCompleted && this.major.getCode().equals(student_obj.major.getCode()) == true){
                if(this.major.getSchool().equals(student_obj.getMajor().getSchool()) && this.major.equals(student_obj.major)){
                    return true;
                }
                return true;
            }
        }
        return false;
    }
    /**
     * Formulates a string based on the Student object
     *
     * @return concatenated string that displays information related to the Student object
    */
    @Override
    public String toString() {
        String compiled_string = "";
        int freshman_credit = 30;
        int sophomore_credit = 60;
        int junior_credit = 90;
        String grade_level = "";
        if(this.creditCompleted < freshman_credit){
            grade_level = "Freshman";
        }else if(freshman_credit>= this.creditCompleted && this.creditCompleted<sophomore_credit){
            grade_level = "Sophomore";
        }else if(sophomore_credit>= this.creditCompleted && this.creditCompleted < junior_credit){
            grade_level = "Junior";
        }else{
            grade_level = "Senior";
        }
        compiled_string =this.profile.toString() + "(" + this.major.getCode() + " " +this.major + " " + this.major.getSchool() +") " + "credits completed: " + this.creditCompleted + " (" +grade_level + ")";
        return compiled_string;
    }


}
