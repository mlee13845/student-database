package com.example.tuitonfx;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
    This program allows users to construct their Date objects, access each of the components which includes
    student birth month, student birthday and student birth year. Additionally, an isValid method is used to determine
     if the date presented is a valid date on the calendar.Finally, this program also
    implements and Overrides the compareTo and equals method for comparing two Date objects as well as a toString
    method for constructing a string with the users information in a specific format

    @author Michael Lee
 */
public class Date implements Comparable<Date> {
    public static final int JANUARY = 31;
    public static final int FEBRUARY = 28;
    public static final int LEAP_YEAR = 29;
    public static final int MARCH = 31;
    public static final int APRIL = 30;
    public static final int MAY = 31;
    public static final int JUNE = 30;
    public static final int JULY = 31;
    public static final int AUGUST = 31;
    public static final int SEPTEMBER = 30;
    public static final int OCTOBER = 31;
    public static final int NOVEMBER = 30;
    public static final int DECEMBER = 31;
    public static final int Jan = 1;
    public static final int Feb = 2;
    public static final int Mar = 3;
    public static final int Apr = 4;
    public static final int Ma = 5;
    public static final int Jun = 6;
    public static final int Jul = 7;
    public static final int Aug = 8;
    public static final int Sept = 9;
    public static final int Oct = 10;
    public static final int Nov = 11;
    public static final int Dec = 12;


    private int year;
    private int month;
    private int day;

    /**
     * This method uses the calendar import to create a current date object
     */
    public Date(){
        int month_offset = 1;
        Calendar gcal = Calendar.getInstance();
        int curr_day = gcal.get(Calendar.DATE);
        int curr_month = gcal.get(Calendar.MONTH);
        curr_month = curr_month + month_offset;
        int curr_year = gcal.get(Calendar.YEAR);
        this.day = curr_day;
        this.month = curr_month;
        this.year = curr_year;
    }
    /**
     * Date object constructor that reads a date in the form of (Month/Day/Year)
     * and separates the day, month and year to create the Date object
     *
     * @param date Birthdate of student
     */
    public Date(String date){
        int start_num_string;
        String temp_str = "";
        String obj_month = "";
        String obj_day = "";
        String obj_years = "";
        int end_of_day = 5;
        int end_of_month = 2;
        int end_of_year = 9;
        StringTokenizer total_date = new StringTokenizer(date,"-");
        obj_month = total_date.nextToken();
        obj_day = total_date.nextToken();
        obj_years = total_date.nextToken();
        this.month = Integer.parseInt(obj_month);
        this.day = Integer.parseInt(obj_day);
        this.year = Integer.parseInt(obj_years);
    }
    /**
     * Getter method that allows access to the private variable month
     *
     * @return The month of the student birthday
    */
    public int get_month(){
        return this.month;
    }
    /**
     * Getter method that allows for access to the private variable day
     *
     * @return The day of the student's birthday
    */
    public int get_day(){
        return this.day;
    }
    /**
     * Getter method that allows for access to the private variable year
     *
     * @return The year of the student's birthday
    */
    public int get_year(){
        return this.year;
    }
    /**
     * Checks to see if date is a valid date on the calendar
     *
     * @return boolean that indicates if date is valid
     */
    public boolean isValid(){
        int four = 4; int hundred = 100; int fhundred = 400;int zero = 0;
        int real_month = 0;
        if(this.month == Jan){
            real_month = JANUARY;
        }else if(this.month == Feb){
            real_month = FEBRUARY;
        }else if(this.month == Mar){
            real_month = MARCH;
        }else if(this.month == Apr){
            real_month = APRIL;
        }else if(this.month == Ma){
            real_month = MAY;
        }else if(this.month == Jun){
            real_month = JUNE;
        }else if(this.month == Jul){
            real_month = JULY;
        }else if(this.month == Aug){
            real_month = AUGUST;
        }else if(this.month == Sept){
            real_month = SEPTEMBER;
        }else if(this.month == Oct){
            real_month = OCTOBER;
        }else if(this.month == Nov){
            real_month = NOVEMBER;
        }else if(this.month == Dec){
            real_month = DECEMBER;
        }else{
            if(this.year%four == zero && this.year % hundred == zero && this.year % fhundred == zero){
                        real_month = LEAP_YEAR;
            }
        }
        if(real_month == zero || this.day> real_month){
            return false;
        }else{
            if(this.day > zero && this.year > zero){return true;}
            return false;
        }
    }
    /**
     * Compares Date objects to determine which date is greater
     *
     * @param obj The Date object that is compared to
     * @return 1, -1 or 0 depending on which Date object is greater
     */
    @Override
    public int compareTo(Date obj) {

        if(this.year < obj.year){
            return -1;
        }else if(this.year > obj.year){
            return 1;
        }else{
            if(this.month < obj.month){
                return -1;
            }else if(this.month > obj.month){
                return 1;
            }else{
                if(this.day < obj.day){
                    return -1;
                }else if(this.day > obj.day){
                    return 1;
                }else{
                    return 0;
                }
            }
        }
    }
    /**
     * Checks to see if the argument object is of the same type as comparing object as well
     * as checking to see if the objects are the same
     *
     * @param obj object of type Object to be compared to see if it is equal
     * @return boolean true or false of whether the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Date){
            Date date_obj = (Date) obj;
            if(this.month == date_obj.month && this.day == date_obj.day){
                if(this.year == date_obj.year){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * The Date object being converted into a string in the format (month/day/year)
     */
    @Override
    public String toString() {
        String concat_str = "";
        concat_str = this.month + "/" + this.day + "/" + this.year;
        return concat_str;
    }

//    /**
//     * Testbed main method for edge birthday edge cases
//     *
//     * @throws FileNotFoundException Exception object that indicates file could not be found
//     */
//    public static void main(String[]args)throws FileNotFoundException{
//        File file = new File("testDate.txt");
//        Scanner scan = new Scanner(file);
//        while(scan.hasNextLine()){
//            Date newDate = new Date(scan.nextLine());
//            System.out.println(newDate.isValid());
//        }
//    }
}
