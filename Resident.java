package com.example.tuitonfx;

/**
 * This program allows user to construct their Resident object that inherits
 * the properties from Student object.
 *
 * @author Michael Lee
 */
public class Resident extends Student{
    private int scholarship;
    public Resident(Profile profile, Major major, int creditCompleted, int scholarship){
        super(profile,major,creditCompleted);
        this.scholarship = scholarship;
    }
    public double tuitionDue(int creditsEnrolled){
        int tuition = 12536;
        int uni_fee = 3268;
        int full_time = 12;
        int part_time_cost = 404;
        double total_due = 0;
        double discount = .8;
        int limit_cred = 16;
        if(creditsEnrolled < 12){
            total_due = ((discount*uni_fee) + (part_time_cost*creditsEnrolled));
            return total_due;
        }else if(creditsEnrolled<=limit_cred){
            total_due = tuition + uni_fee - this.scholarship;
            return total_due;
        }
        int extra_cred = creditsEnrolled - limit_cred;
        total_due = tuition + uni_fee + (part_time_cost *extra_cred) - this.scholarship;
        return total_due;

    }


    @Override
    public String printType() {
        return "(Resident)";
    }

    public boolean isResident(){
        return true;
    }

    public String specific_type(){
        return "(resident)";
    }



}
