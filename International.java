package com.example.tuitonfx;

public  class International extends NonResident{
    private boolean isStudyAbroad;
    public International(Profile profile, Major major, int creditCompleted, boolean isStudyAbroad){
        super(profile, major, creditCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }

    @Override
    public boolean isValid(int creditEnrolled) {
        int cred_threshold = 12;
        if(this.isStudyAbroad){
            if(creditEnrolled<=12){
                return true;
            }
            return false;
        }
        if(creditEnrolled>=12){
            return true;
        }
        return false;

    }

    @Override
    public double tuitionDue(int creditsEnrolled) {
        int tuition = 29737;
        int uni_fee = 3268;
        int cred_threshold = 12;
        int charging_cred = 16;
        int cost = 966;
        double total_due = 0;
        int health_fee = 2650;
        if(this.isStudyAbroad){
            if(creditsEnrolled<=cred_threshold){
                total_due =  uni_fee + health_fee;
                return total_due;
            }
            return 0.0;
        }
        if(creditsEnrolled >= cred_threshold){
            if(creditsEnrolled >= charging_cred){
                total_due = tuition + uni_fee + health_fee+ (cost*(creditsEnrolled - charging_cred));
                return total_due;
            }
            total_due = tuition + uni_fee + health_fee;
            return total_due;
        }
        return 0.0;
    }
    @Override
    public String printType() {
        if(isStudyAbroad){
            return"(International studentstudy abroad)";
        }
        return "(International student)";
    }

    public String specific_type(){
        if (isStudyAbroad) {

            return "(non-resident) (international:study abroad)";
        }
        return "(non-resident) (international)";
    }

}
