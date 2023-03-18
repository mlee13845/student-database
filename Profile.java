package com.example.tuitonfx;
/**
    This program allows users to construct their Profile objects, access each of the components which include
    student first name, student last name and student date of birth in the form of an object. Finally, this program also
    implements and Overrides the compareTo and equals method for comparing two Profile objects as well as a toString
    method for constructing a string with the users information in a specific format.

    @author Michael Lee
 */
public class Profile implements Comparable<Profile>{
    private String lname;
    private String fname;
    private Date dob;

    /**
     * Constructor for Profile object.
     *
     * @param fname First name of student.
     * @param lname Last name of student.
     * @param dob Date object with student date of birth.
     */
    public Profile(String fname, String lname, Date dob){
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }

    /**
     * Getter method that allows access to the private last name variable.
     *
     * @return Last name of student.
     */
    public String get_lname(){
        return this.lname;
    }

    /**
     * Getter method that allows access to the private first name variable.
     *
     * @return First name of student.
     */
    public String get_fname(){
        return this.fname;
    }

    /**
     * Getter method that allows access to the private date object.
     *
     * @return Date object that specifies student's date of birth.
     */
    public Date get_dob(){
        return this.dob;
    }

    /**
     * Compare the object of type Profile.
     *
     * @param obj The object to be compared.
     * @return 1, -1, 0 depending on how one Profile object compares to another.
     */
    @Override
    public int compareTo(Profile obj) {
        int match = 0;
        int less = -1;
        int greater = 1;
        if(this.lname.compareTo(obj.lname) < match){
            return less;
        }else if(this.lname.compareTo(obj.lname) > match){
            return greater;
        }else{
            if(this.fname.compareTo(obj.fname) < match){
                return less;
            }else if(this.fname.compareTo(obj.fname) > match){
                return greater;
            }else{
                if(this.dob.compareTo(obj.dob) < match){
                    return less;
                }else if (this.dob.compareTo(obj.dob) > match){
                    return greater;
                }else{
                    return match;
                }
            }
        }
    }
    /**
     * Compares objects to see if they are the same.
     * @param obj Object of any given type.
     * @return True or False depending on object in argument are exactly the same down to the type.
     */
    @Override
    public boolean equals(Object obj) {
        int match = 0;
        if(obj instanceof Profile){
            Profile profile_obj = (Profile)obj;
            if(this.lname.equalsIgnoreCase(profile_obj.lname) == true && this.fname.equalsIgnoreCase(profile_obj.fname) == true){
                if(this.dob.compareTo(profile_obj.dob) == match){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * This method takes Profile object and format into string.
     *
     * @return Concatenated string of information related to the Profile object.
     */
    @Override
    public String toString() {
        String combined_str = "";
        combined_str = this.fname + " " + this.lname + " " + this.dob.toString();
        return combined_str;
    }

}
