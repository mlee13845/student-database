package com.example.tuitonfx;

public class TriState extends NonResident{
    private String state;
    public TriState(Profile profile, Major major, int creditsCompleted, String state){
        super(profile, major,creditsCompleted);
        this.state = state;
    }
    @Override
    public double tuitionDue(int creditsEnrolled){
        String ny = "NY";
        String cc = "CT";
        int state_discount = 0;
        int tuition = 29737;
        int uni_fee = 3268;
        int full_time = 12;
        int part_time_cost = 966;
        double total_due = 0;
        double discount = .8;
        int limit_cred = 16;
        if(creditsEnrolled < full_time){
            total_due = ((discount*uni_fee) + (part_time_cost*creditsEnrolled));
            return total_due;
        }else if(creditsEnrolled<=limit_cred){
            total_due = tuition + uni_fee;
        }else{
            total_due = tuition + uni_fee + (part_time_cost *(creditsEnrolled-limit_cred));
        }
        if(this.state.equalsIgnoreCase(ny)){
            state_discount = 4000;
        }else if(this.state.equalsIgnoreCase(cc)){
            state_discount = 5000;
        }
        double total_cost = total_due- state_discount;
        return total_cost;
    }
    @Override
    public String printType() {
        return "(TriState)";
    }

    public String specific_type(){
        return "(non-resident) (tri-state:"+this.state+")";
    }

}
