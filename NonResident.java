package com.example.tuitonfx;

public class NonResident extends Student{
    public NonResident(Profile profile, Major major, int creditCompleted){
        super(profile,major,creditCompleted);
    }

    public double tuitionDue(int creditsEnrolled){
        int tuition = 29737;
        int uni_fee = 3268;
        int full_time = 12;
        int part_time_cost = 966;
        double total_due = 0;
        double discount = .8;
        int limit_cred = 16;
        if(creditsEnrolled < 12){
            total_due = ((discount*uni_fee) + (part_time_cost*creditsEnrolled));
            return total_due;
        }else if(creditsEnrolled<=limit_cred){
            total_due = tuition + uni_fee;
            return total_due;
        }
        total_due = tuition + uni_fee + (part_time_cost *(creditsEnrolled-limit_cred));
        return total_due;
    }
    @Override
    public boolean isResident() {
        return false;
    }
    @Override
    public String printType() {
        return "(Non-Resident)";
    }
    public String specific_type(){
        return "(non-resident)";
    }
}
