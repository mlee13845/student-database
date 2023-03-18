package com.example.tuitonfx;
/**
    This program constructs and allows access to the major enum object. It also implements and two getter methods that
    allow access to the two private String variables

    @author Moises Manrique Michael Lee
*/
    public enum Major {
        CS("01:198", "SAS"),
        MATH("01:640", "SAS"),
        EE("14:332", "SOE"),
        ITI("04:547", "SC&I"),
        BAIT("33:136", "RBS");
        private final String code;
        private final String school;
        /**
            This method is a constructor for the Major object with 2 fields

            @param code code of major
            @param school  school associated with major

         */
        Major(String code, String school ) {
            this.code = code;
            this.school = school;
        }
        /**
            This method allows access to private variable code
         */
        public String getCode(){
            return this.code;
        }
        /**
            This method allows access to private variable school
         */
        public String getSchool(){
            return this.school;
        }


    }

